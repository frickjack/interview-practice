package codechef;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumCarriesTest {
    @Test public void testNumCarries() {
        int expected = 0;
        int result = NumCarries.numCarries(123, 456);
        assertEquals("numCarries 123 456", expected, result);
        
        expected = 3;
        result = NumCarries.numCarries(555, 555);
        assertEquals("numCarries 555 555", expected, result);

        expected = 5;
        result = NumCarries.numCarries(1, 99999);
        assertEquals("numCarries 1 99999", expected, result);
    }
}
