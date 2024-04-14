package com.quiz.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * JwtUtils
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 20:54
 */
@Log4j2
public class JWTUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24;//token过期时间   24小时
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";//密钥
    public static final String TOKEN_KEY = "token";
    public static final String CLAIM_ID = "id";//方便从jwtToken中获取生成的字段
    public static final String CLAIM_ACCOUNT = "account";//方便从jwtToken中获取生成的字段

    public static String getJwtToken(String id, String account) {

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("jacob-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim(CLAIM_ID, id)
                .claim(CLAIM_ACCOUNT, account)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     * 根据token，判断token是否存在与有效
     *
     * @param jwtToken token字符串
     * @return token存在与有效返回 true, 反之 false
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据request判断token是否存在与有效（也就是把token取出来罢了）
     *
     * @param request 请求
     * @return token存在与有效返回true, 反之false
     */
    public static boolean checkToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(JWTUtils.TOKEN_KEY);
        return checkToken(jwtToken);
    }

    /**
     * 获取token的claimName对应值 (生成token时的参数对应)
     *
     * @param request   请求
     * @param claimName 生产token时的字段名称
     * @return claimName对应的值
     */
    public static String getClaimValueByClaimName(HttpServletRequest request, String claimName) {
        String jwtToken = request.getHeader(JWTUtils.TOKEN_KEY);
        if (StringUtils.isEmpty(jwtToken)) return "";
        try {
            // 这里解析可能会抛异常，所以try catch来捕捉
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            return (String) claims.get(claimName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("JWT字符串格式不符合!");
            return "";
        }
    }

    /**
     * 获取token的id值 (生成token时的id)
     *
     * @param request 请求
     * @return id字符串
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        return getClaimValueByClaimName(request, CLAIM_ID);
    }

    /**
     * 获取token的account值 (生成token时的account)
     *
     * @param request 请求
     * @return account字符串
     */
    public static String getMemberAccountByJwtToken(HttpServletRequest request) {
        return getClaimValueByClaimName(request, CLAIM_ACCOUNT);
    }
}
