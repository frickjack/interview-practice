package codechef;

import java.util.*;


/**
 * "Elements of Programming Interviews" 17.9 p319
 */
public final class PickUpCoins {

    public static final class Range {
        public final int start;
        public final int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
            if (start > end || start < 0) {
                throw new IllegalArgumentException("Invalid range (" + start + "," + end + ")");
            }
        }

        @Override
        public int hashCode() {
            return this.end * 31 + this.start;
        }

        @Override
        public boolean equals(Object other) {
            if (null != other && other instanceof Range) {
                final var b = (Range) other;
                return b.end == end && b.start == start;
            }
            return false;
        }
    }

    static int maxPickup(List<Integer> coinList, Range range, int rangeTotal, Map<Range,Integer> db) {
        if (range.start - range.end == 1) {
            return Math.max(coinList.get(range.end), coinList.get(range.start));
        } else if (range.start == range.end) {
            return coinList.get(range.end);
        } else if (db.containsKey(range)) {
            return db.get(range);
        }

        // pick the better of the 2 possible moves
        // move1 == this player picks up the "start" coin
        final Range move1 = new Range(range.start + 1, range.end);
        final int opponentScore1 = maxPickup(coinList, move1, rangeTotal - coinList.get(range.start), db);
        // move2 == this player picks up the "end" coin
        final Range move2 = new Range(range.start, range.end - 1);
        final int opponentScore2 = maxPickup(coinList, move2, rangeTotal - coinList.get(range.end), db);
        final int result = rangeTotal - Math.min(opponentScore1, opponentScore2);
        return result;
    }

    /**
     * Find the max score of the first player
     * in a game of picking up an even number
     * of coins alternately from the ends of
     * a list of coins assuming both players pursue
     * the best course available
     * 
     * @param coinList
     * @return score of first player
     */
    public static int maxPickup(List<Integer> coinList) {
        final Map<Range, Integer> db = new HashMap<>();
        final int total;
        {
            int acc = 0;
            for (int it: coinList) { acc += it; }
            total = acc;
        }
        return maxPickup(coinList, new Range(0, coinList.size() - 1), total, db);
    }

}
