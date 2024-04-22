import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

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
}
