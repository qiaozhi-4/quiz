import com.quiz.Main;
import com.quiz.dto.UserDTO;
import com.quiz.entity.User;
import com.quiz.entity.UserAuth;
import com.quiz.mapper.UserMapper;
import com.quiz.service.IUserService;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Test
    public void getUserDtoByAccount() {
        val userDto = userService.getUserDtoByAccount("user_d02c3a68");
        System.out.println(userDto);
    }

    @Test
    public void testModel() {
        final User user = UserDTO.defUser().setUsername("测试");
        log.info(user);
//        user.insert();
        log.info(user);
        final UserAuth userAuth = UserAuth.builder().userId(1).provider("测试").providerId("445454455454").build();
        log.info(userAuth);
        userAuth.insert();
        log.info(userAuth);
    }

    @Test
    public void selectUserDtoByAccount() {
        final UserDTO admin = userMapper.selectUserDtoByAccount("admin");
        System.out.println(admin.toString());
        final UserDTO user = userMapper.selectUserDtoByAccount("user");
        System.out.println(user.toString());
    }
}
