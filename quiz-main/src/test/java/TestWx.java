import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.quiz.Main;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * TestWx
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/19 12:34
 */
@SpringBootTest(classes = Main.class)
@Log4j2
public class TestWx {
    @Autowired
    private WxMaService wxMaService;

    @Test
    public void test1() throws WxErrorException {
        final WxMaUserService userService = wxMaService.getUserService();
        final WxMaJscode2SessionResult sessionInfo = userService
                .getSessionInfo("0b3Sbh000xX0YR1RR9400u2ZAt0Sbh0p");
        log.debug(sessionInfo);
    }
}
