package codechef;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import codechef.CoverIntervals.Interval;

public class CoverIntervalsTest {
    @Test public void testCover() {
        final List<CoverIntervals.Interval> intervals =
            Arrays.asList(
                new Interval(0, 3),
                new Interval(2, 6),
                new Interval(3, 4),
                new Interval(6, 9)
            );
        final Set<Integer> expected = new HashSet<>(
            Arrays.asList(3, 6)
        );

        assertEquals("got expected cover", 
            expected, CoverIntervals.findCover(intervals)
            );
    }
    
}
