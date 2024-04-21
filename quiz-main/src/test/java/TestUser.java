import com.quiz.Main;
import com.quiz.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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
        final User user = User.builder().username("测试").createdAt(LocalDateTime.now()).build();
        log.info(user);
        user.insert();
        log.info(user);
    }
}
