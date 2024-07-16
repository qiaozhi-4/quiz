import com.quiz.Main;
import com.quiz.dto.PathDto;
import com.quiz.mapper.PathMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    private PathMapper pathMapper;


}
