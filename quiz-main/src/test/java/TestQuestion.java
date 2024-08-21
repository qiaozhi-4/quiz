import com.quiz.Main;
import com.quiz.entity.Question;
import com.quiz.mapper.QuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p>
 * TestQuestion
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/23 15:46
 */
@SpringBootTest(classes = Main.class)
public class TestQuestion {
    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void selectRandomQuestions() {
        final List<Question> questions = questionMapper.selectRandomQuestionsByUserIdAndNumber(1,10);
        System.out.println(questions.toString());
    }
}
