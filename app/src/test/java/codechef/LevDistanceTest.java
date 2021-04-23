package codechef;

import org.junit.Test;
import static org.junit.Assert.*;


public class LevDistanceTest {
    
    @Test public void testLev() {
        assertEquals("lev handles empty src", 3, LevDistance.getLev("", "abc"));
        assertEquals("lev handles empty dest", 3, LevDistance.getLev("abc", ""));
        assertEquals("lev handles substitution", 4, LevDistance.getLev("abcabcabca", "AbcAbcAbcA"));
        assertEquals("lev handles deletion", 5, LevDistance.getLev("dabcdabcdabcdad", "abcabcabca"));
        assertEquals("lev handles insert", 5, LevDistance.getLev("abcabcabca", "dabcdabcdabcdad"));        
    }
}
