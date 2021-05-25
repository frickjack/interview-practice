package codechef;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;


public class PickUpCoinsTest {
    @Test public void testPickUpCoins() {
        final List<Integer> coinList = Arrays.asList(
                25, 5, 10, 5, 10, 5, 10, 25, 1, 25, 1, 25, 1, 25, 5, 10
            );
            /*
            25, 10
            5, 10
            5, 10
            5, 10
            25, 5
            25, 1,
            25, 1,
            25, 1
            */
        final int expected = 140;
        final int result = PickUpCoins.maxPickup(coinList);
        assertEquals("PickUpCoins found expected score", expected, result);
    }
}
