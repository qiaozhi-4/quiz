import com.quiz.Main;
import com.quiz.entity.User;
import com.quiz.entity.UserAuth;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * TestUser
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/21 18:10
 */
@SpringBootTest(classes = Main.class)
@Log4j2
public class TestUser {

    @Test
    public void testModel() {
        final User user = User.defUser().setUsername("测试");
        log.info(user);
//        user.insert();
        log.info(user);
        final UserAuth userAuth = UserAuth.builder().userId(1).provider("测试").providerId("445454455454").build();
        log.info(userAuth);
        userAuth.insert();
        log.info(userAuth);
    }
}