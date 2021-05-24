package codechef;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;


public class StringPathTest {
    @Test public void testStringPath() {
        final Set<String> dictionary = new HashSet<>(
            Arrays.asList(
                "bat", "cot", "dag", "dog", "dot", "cat"
            )
        );
        final int expected = 3;
        final int result = StringPath.shortestPath("cat", "dog", dictionary);
        assertEquals("StringPath found shortest path", expected, result);
    }
}
