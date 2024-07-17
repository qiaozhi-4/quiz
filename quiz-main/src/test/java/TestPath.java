import com.quiz.Main;
import com.quiz.service.IPathService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * TestPath
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/14 11:14
 */
@SpringBootTest(classes = Main.class)
public class TestPath {
    @Autowired
    private IPathService pathService;

    @Test
    public void test() {
        pathService.updateAllPath().forEach(System.out::println);

        pathService.getAllPath().forEach(System.out::println);
    }


}
