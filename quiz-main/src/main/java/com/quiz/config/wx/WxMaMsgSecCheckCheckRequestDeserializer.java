package com.quiz.config.wx;

import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * <p>
 * WxMaMsgSecCheckCheckRequestDeserializer 微信安全检查请求体反序列化器
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/23 下午2:08
 */
public class WxMaMsgSecCheckCheckRequestDeserializer extends JsonDeserializer<WxMaMsgSecCheckCheckRequest> {
    @Override
    public WxMaMsgSecCheckCheckRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        return WxMaMsgSecCheckCheckRequest.builder()
                .version(node.path("version").asText("2"))
                .openid(node.path("openid").asText(""))
                .scene(node.path("scene").asInt(1))
                .content(node.path("content").asText(""))
                .nickname(node.path("nickname").asText(""))
                .title(node.path("title").asText(""))
                .signature(node.path("signature").asText(""))
                .build();
    }
}
