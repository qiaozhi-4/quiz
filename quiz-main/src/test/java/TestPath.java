import com.quiz.Main;
import com.quiz.dto.PathDto;
import com.quiz.service.ITPathService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class TestPath {
    @Autowired
    private ITPathService pathService;

    @Test
    public void getPathDtoList() {
        final List<PathDto> pathDtoList = pathService.getPathDtoList();
        log.info(pathDtoList);
    }
}
