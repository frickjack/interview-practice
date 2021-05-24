package codechef;

import java.util.*;


/**
 * "Elements of Programming Interviews" 19.7 p360
 */
public final class StringPath {

    private static char[] alphabet = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z'
    };

    /**
     * Find the shortest path (transforming one character at a time)
     * from start to end through the dictionary.
     * Shortest path is a BFS (Djikstra)
     * 
     * @param start
     * @param end
     * @param dictionary
     * @param db
     * @return -1 if no path is available
     */
    public static int shortestPath(String start, String end, Set<String> dictionary) {
        if (start.length() != end.length()) {
            return -1;
        }
        if (start.equals(end)) {
            return 0;
        }
        final Map<String, Integer> visitedDb = new HashMap<>();
        final Queue<String>        bfsQ = new LinkedList<>();

        visitedDb.put(start, 0);
        bfsQ.add(start);

        while (!bfsQ.isEmpty()) {
            final String it = bfsQ.remove();
            final int distance = visitedDb.get(it);

            // explore every mutation, and pick the min
            for (int i=0; i < start.length(); ++i) {
                final String prefix = it.substring(0, i);
                final String suffix = it.substring(i+1);
                for (int j=0; j < alphabet.length; ++j) {
                    final String neighbor = prefix + alphabet[j] + suffix;
                    if (neighbor.equals(end)) {
                        return distance + 1;
                    }
                    if (dictionary.contains(neighbor) && !visitedDb.containsKey(neighbor)) {
                        visitedDb.put(neighbor, distance+1);
                        bfsQ.add(neighbor);
                    }
                }
            }
        }
        return -1;
    }

}
