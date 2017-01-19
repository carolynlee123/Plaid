import org.junit.Test;
import static org.junit.Assert.*;

public class PlaidTest {

    Plaid plaid = new Plaid();

    @Test(expected = IllegalArgumentException.class)
    public void testEncodeNullInput() {
        String input = null;
        plaid.encode(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodeNullInput() {
        String input = null;
        assertEquals("", plaid.decode(input));
    }

    @Test
    public void testEncodeEmptyInput() {
        String input = "";
        assertEquals("", plaid.encode(input));
    }

    @Test
    public void testDecodeEmptyInput() {
        String input = "";
        assertEquals("", plaid.decode(input));
    }

    @Test
    public void testEncodeSpaces() {
        String input = "  ";
        assertEquals("32[2]", plaid.encode(input));
    }

    @Test
    public void testDecodeSpaces() {
        String input = "32[2]";
        assertEquals("  ", plaid.decode(input));
    }

    @Test
    public void testEncodeValidInput() {
        String input = "aa! # * gggg";
        assertEquals("97[2]33[1]32[1]42[1]32[1]103[4]", plaid.encode(input));
    }

    @Test
    public void testDecodeValidInput() {
        String input = "97[2]33[1]32[1]42[1]32[1]103[4]";
        assertEquals("aa! * gggg", plaid.decode(input));
    }
}
