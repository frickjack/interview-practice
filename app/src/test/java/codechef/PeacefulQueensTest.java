package codechef;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;
import java.util.logging.*;


public class PeacefulQueensTest {
    private final static Logger log = Logger.getLogger(PeacefulQueensTest.class.getName());
    @Test public void testPeacefulQueens() {
        final var result = PeacefulQueens.placeQueens(4);
        log.log(Level.INFO, "Got following peaceful queen boards:");
        for (var it: result) {
            log.log(Level.INFO, it.toString());
        }
        assertTrue("PeacefulQueens got some boards", !result.isEmpty());
    }
}
