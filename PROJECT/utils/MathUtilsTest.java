import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    public static class MathUtils {
        public static int add(int a, int b) {
            return a + b;
        }

        public static int subtract(int a, int b) {
            return a - b;
        }
    }

    @Test
    public void testAddition() {
        assertEquals(8, MathUtils.add(3, 5));
    }

    @Test
    public void testSubtraction() {
        assertEquals(5, MathUtils.subtract(8, 3));
    }
}
