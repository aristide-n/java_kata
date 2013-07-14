package algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 7/8/13
 */
public class InversionCounterTester {
    public static void main(String[] args){
        InversionCounter inversionCounter = new InversionCounter();

        int[] firstInput = {};
        int[] firstOutputArray = {};
        int firstOutputCount = 0;

        HashMap firstResult = inversionCounter.countInversions(firstInput);
        if((Arrays.equals(firstOutputArray, (int[])firstResult.get("array"))) &&
           (firstOutputCount == (Integer)firstResult.get("count")))
        {
            System.out.println("Base case: empty array works");
        }

        int[] secondInput = {4};
        int[] secondOutputArray = {4};
        int secondOutputCount = 0;

        HashMap secondResult = inversionCounter.countInversions(secondInput);
        if((Arrays.equals(secondOutputArray, (int[])secondResult.get("array"))) &&
                (secondOutputCount == (Integer)secondResult.get("count")))
        {
            System.out.println("Base case: array of length 1 works");
        }

        int[] thirdInput = {2,4,6,5,1,3};
        int[] thirdOutputArray = {1,2,3,4,5,6};
        int thirdOutputCount = 8;

        HashMap thirdResult = inversionCounter.countInversions(thirdInput);
        if((Arrays.equals(thirdOutputArray, (int[])thirdResult.get("array"))) &&
                (thirdOutputCount == (Integer)thirdResult.get("count")))
        {
            System.out.println("Counting random array works");
        }

        int[] fourthInput = {8, 7, 6, 5, 4, 3, 2, 1};
        int[] fourthOutputArray = {1, 2, 3, 4, 5, 6, 7, 8};
        int fourthOutputCount = 28;

        HashMap fourthResult = inversionCounter.countInversions(fourthInput);
        if((Arrays.equals(fourthOutputArray, (int[])fourthResult.get("array"))) &&
                (fourthOutputCount == (Integer)fourthResult.get("count")))
        {
            System.out.println("Counting array with max inversions works");
        }
    }
}
