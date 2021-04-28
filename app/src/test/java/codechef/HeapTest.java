package codechef;

import org.junit.Test;
import static org.junit.Assert.*;


public class HeapTest {

    @Test public void testHeap() {
        final var heap = new Heap<Integer>((a,b) -> { return Integer.compare(a,b); });
        for (int i=0; i < 10; ++i) {
            heap.add((int)(Math.random()*1000));
        }

        for (int last = -1; ! heap.isEmpty(); ) {
            int next = heap.remove();
            //System.out.println("Heap: " + next);
            assertTrue("" + next + " > " + last, next >= last);
        }
    }
}
