package codechef;

import java.util.*;

/**
 * Given a set of closed intervals, find the
 * smallest set that covers all the intervals.
 * 
 * Elements of Programming Interviews, p331
 */
public final class CoverIntervals {
    public static final class Interval {
        public final int start;
        public final int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval(Interval other) {
            this.start = other.start;
            this.end = other.end;
        }
    }

    /**
     * Approach: greedy
     * <ul>
     *   <li> sort the intervals by start date
     *   <li> starting with the first interval,
     *        greedily add intervals to the window
     *        that touches the first interval
     * </ul>
     * 
     * @param intervals to cover - (start, end) inclusive
     * @return minimum set of times that touch all the intervals
     */
    public static Set<Integer> findCover(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return Collections.emptySet();
        }

        final Set<Integer> result = new HashSet<>();
        final List<Interval> sortedByStart = new ArrayList<>(intervals);
        Collections.sort(
            intervals,
            (a, b) -> Integer.compare(a.start, b.start)
        );

        int windowStart = Integer.MIN_VALUE;
        int windowEnd = Integer.MAX_VALUE;

        for (final Interval it : sortedByStart) {
            if (it.start > windowEnd) {
                // interval is outside the window
                result.add(windowStart);
                windowStart = it.start;
                windowEnd = it.end;
            } else {
                if (it.start > windowStart) {
                    windowStart = it.start;
                }
                if (it.end < windowEnd) {
                    windowEnd = it.end;
                }
            }
        }
        result.add(windowStart);
        return result;
    }
}
