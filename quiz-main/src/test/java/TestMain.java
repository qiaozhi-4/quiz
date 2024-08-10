import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * <p>
 * TestMain
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/21 20:50
 */
public class TestMain {
    @Test
    public void test1() {
        System.out.println("TUser".replaceAll("^[TM](?=[A-Z])", ""));
        System.out.println("MUser".replaceAll("^[TM](?=[A-Z])", ""));
        System.out.println("Mue".replaceAll("^[TM](?=[A-Z])", ""));
        System.out.println("Test".replaceAll("^[TM](?=[A-Z])", ""));
    }

    @Test
    public void testAntPathMatcher() {
        final AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/permission/test3/1/1", "/permission/test3/{current:d+}/{size:d+}"));
        System.out.println(antPathMatcher.match("/permission/test3/{current:d+}/{size:d+}", "/permission/test3/1/1"));
    }

    /**
     * 获取本周周一时间
     */
    @Test
    public void testLocalDateTime() {
        /* 获取本周周一 */
        val localDateTime = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0).withNano(0);
        /* 格式化 */
        val format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("本周一的时间是：" + format);
    }
}
