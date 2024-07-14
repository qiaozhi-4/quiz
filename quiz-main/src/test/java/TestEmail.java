import com.quiz.Main;
import com.quiz.utils.email.EmailUtil;
import com.quiz.utils.email.ToEmail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author XGeorge
 * @since 2024/7/14
 */
@SpringBootTest(classes = Main.class)
public class TestEmail {

    @Autowired
    EmailUtil emailUtil;

    @Test
    public void test1() {

        emailUtil.commonEmail(
                ToEmail.builder().tos(new String[]{"468644641@qq.com","2391943321@qq.com"})
                        .subject("使用日志")
                        .content("急急急").build()
        );
    }
}
