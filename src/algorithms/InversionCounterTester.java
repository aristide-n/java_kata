package algorithms;

import java.util.Arrays;
import java.util.HashMap;

public class InversionCounterTester {
    private InversionCounter inversionCounter = new InversionCounter();
    private HashMap result = new HashMap();

    public static void main(String[] args){
               
        Method ok =.getClass().getMethod("ok");
        int[] inputArray = {};
        int[] outputArray = {};
        int count = 0;

        if (ok(new Int[](), new Int[](), 0)){
            System.out.println("Base case: empty array works");
        }

        // int[] secondInput = {4};
        // int[] secondOutputArray = {4};
        // int secondOutputCount = 0;

        // HashMap secondResult = inversionCounter.countInversions(secondInput);
        // if((Arrays.equals(secondOutputArray, (int[])secondResult.get("array"))) &&
        //         (secondOutputCount == (Integer)secondResult.get("count")))
        // {
        //     System.out.println("Base case: array of length 1 works");
        // }

        // int[] thirdInput = {2,4,6,5,1,3};
        // int[] thirdOutputArray = {1,2,3,4,5,6};
        // int thirdOutputCount = 8;

        // HashMap thirdResult = inversionCounter.countInversions(thirdInput);
        // if((Arrays.equals(thirdOutputArray, (int[])thirdResult.get("array"))) &&
        //         (thirdOutputCount == (Integer)thirdResult.get("count")))
        // {
        //     System.out.println("Counting random array works");
        // }

        // int[] inputArray = {8, 7, 6, 5, 4, 3, 2, 1};
        // int[] outputArray = {1, 2, 3, 4, 5, 6, 7, 8};
        // int count = 28;

        // HashMap result = inversionCounter.countInversions(inputArray);
        // if((Arrays.equals(outputArray, (int[])result.get("array"))) &&
        //         (count == (Integer)result.get("count")))
        // {
        //     System.out.println("Counting array with max inversions works");
        // }
    }

    public boolean ok(int[] inputArray, int[] outputArray, int count) {
        result = inversionCounter.countInversions(inputArray);
        if((Arrays.equals(outputArray, (int[])result.get("array"))) &&
                (count == (Integer)result.get("count")))
        {
            return true;
        }

        return false;
    }
}
