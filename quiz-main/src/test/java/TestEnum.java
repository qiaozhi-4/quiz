import com.quiz.enumerate.PermissionEnum;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <p>
 * TestEnum
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/6 上午9:59
 */
public class TestEnum {

    @Test
    public void testEnum() {
        val enums = PermissionEnum.values();
        Arrays.stream(enums).forEach(e->{
            System.out.println(e.getName());
            System.out.println(e.getDescribe());
            System.out.println(e.ordinal());
            System.out.println();
        });
    }
}
