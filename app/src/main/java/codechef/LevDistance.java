package codechef;

import java.util.Map;
import java.util.HashMap;

/**
 * Levenstein Distance -
 * p304 Elements of Programming Interviews
 */
public final class LevDistance {
    
    private static int levHelper(String src, String dest, Map<String, Map<String, Integer>> db) {
        if (src.equals(dest)) {
            return 0;
        }
        if (! db.containsKey(src)) {
            db.put(src, new HashMap<String, Integer>());
        }
        final Map<String, Integer> cache = db.get(src);
        if (cache.containsKey(dest)) {
            return cache.get(dest);
        }
        // let's compute the distance by
        // performing each possible operation
        int it;
        final int srcSize = src.length();
        final int destSize = dest.length();

        // find the largest matching prefix
        for (it=0; it < srcSize && it < destSize && src.charAt(it) == dest.charAt(it); ++it) {
        }

        final int result;
        if (srcSize == it) {
            // insert all the remaining dest chars
            result = destSize - it;
        } else if (destSize == it) {
            // remove the excessive the src chars
            result = srcSize - it;
        } else {
            // perform an edit, and try again
            final int deleteResult = 1 + levHelper(src.substring(it+1), dest.substring(it), db);
            final int insertResult = 1 + levHelper(dest.charAt(0) + src.substring(it), dest.substring(it), db);
            final int substResult = 1 + levHelper(dest.charAt(0) + src.substring(it+1), dest.substring(it), db);
            result = Math.min(deleteResult, Math.min(insertResult, substResult));
        }

        cache.put(dest, result);
        return result;
    }

    /**
     * Lev distance by insert/delete/sub
     * 
     * @param src
     * @param dest
     * @return number of edits
     */
    public static int getLev(String src, String dest) {
        return levHelper(src, dest, new HashMap<>());
    }
}
