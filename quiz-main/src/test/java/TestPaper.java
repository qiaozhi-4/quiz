import com.quiz.Main;
import com.quiz.dto.PaperDto;
import com.quiz.entity.Paper;
import com.quiz.entity.Tag;
import com.quiz.mapper.PaperMapper;
import com.quiz.mapper.QuestionMapper;
import com.quiz.service.IPaperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * <p>
 * TestPaper
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/24 16:43
 */
@SpringBootTest(classes = Main.class)
public class TestPaper {
    @Autowired
    IPaperService paperService;
    @Autowired
    PaperMapper paperMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Test
    public void selectPaperById() {
        final PaperDto paperDto = paperMapper.selectPaperById(1);
        System.out.println(paperDto.toString());
    }

    @Test
    public void savePaperInfo() {
        final Paper paper = Paper.builder()
                .creatorUserId(3)
                .coverUrl("https://img2.imgtp.com/2023/10/26/rQkEoyFQ.jpg")
                .title("测试试卷1")
                .answers(getAnswers())
                .build();
        final PaperDto paperDto = PaperDto.convertOf(paper)
                .setQuestions(questionMapper.selectRandomQuestions())
                .setTags(getTags());
        final Boolean aBoolean = paperService.addPaperInfo(paperDto);
        System.out.println(aBoolean);
    }

    /**
     * 生成随机10个答案下标
     */
    private String getAnswers() {
        final StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(random.nextInt(4) + 1).append("@@");
        }
        return stringBuilder.toString();
    }

    /**
     * 随机生成 1-3 个标签
     */
    private List<Tag> getTags() {
        final Set<Tag> tags = new HashSet<>();
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
        for (int i = 0; i < randomNumber; i++) {
            tags.add(Tag.builder().tagId(random.nextInt(5) + 1).build());
        }
        return new ArrayList<>(tags);
    }
}
