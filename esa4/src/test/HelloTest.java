import org.junit.Assert.assertEquals;
import org.junit.Test;
import de.vwms2019.esa4.Hello;

public class HelloTest {
    @Test
    public void evaluatesMessageString() {
        String messageParameter = "Test";
        String message = Hello.computeMessageString(messageParameter);
        assertEquals("Hello Test!", message);
    }
}