package codechef;


import java.util.*;

public class NumCarries {
    
    /**
     * Number to list of digits
     */
    public static List<Integer> toListOfDigitsReversed(int num) {
        final List<Integer> numbers = new ArrayList<>();
        int left = num;
        while (left > 0) {
            int digit = left % 10;
            left = Math.floorDiv(left, 10);
            numbers.add(digit);
        }
        return numbers;
    }


    public static int numCarries(int a, int b) {
        final List<Integer> aDigitsReversed = toListOfDigitsReversed(a);
        final List<Integer> bDigitsReversed = toListOfDigitsReversed(b);

        final int numDigits = 10;
        for (int i=aDigitsReversed.size(); i < numDigits; ++i) {
            aDigitsReversed.add(0);
        }
        for (int i=bDigitsReversed.size(); i < numDigits; ++i) {
            bDigitsReversed.add(0);
        }

        int carry = 0;
        int numCarryOps = 0;

        for (int i=0; i < numDigits; ++i) {
            final int colTotal = aDigitsReversed.get(i) + bDigitsReversed.get(i) + carry;
            carry = Math.floorDiv(colTotal, 10);
            if (carry > 0) {
                numCarryOps += 1;
            }
        }
        return numCarryOps;
    }

}
