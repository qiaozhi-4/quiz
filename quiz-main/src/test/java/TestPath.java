import com.quiz.Main;
import com.quiz.service.ITPathService;
import com.quiz.service.impl.TPathServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * TestPath
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/14 11:14
 */
@SpringBootTest(classes = Main.class)
@Log4j2
public class TestPath {
    @Autowired
    private ITPathService pathService;
    @Test
    public void getPermissionNameByPath(){
        final String permissionNameByPath = pathService.getPermissionNameByPath("/t-permission/test1");
        log.info(permissionNameByPath);
    }
}
