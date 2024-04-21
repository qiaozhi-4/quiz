import org.junit.jupiter.api.Test;

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
}
