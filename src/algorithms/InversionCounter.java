package algorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 7/8/13
 */
public class InversionCounter {

    /**
     * This main methods reads a file having one integer per line, and prints the number of inversions
     * in the order of the integers from top to bottom
     */
    public static void main(String[] args){
        BufferedReader reader = null;
        InversionCounter counter = new InversionCounter();

        try {
            reader = new BufferedReader(new FileReader(counter.getClass().getResource("integers.txt").getFile()));

            ArrayList<Integer> intArray = new ArrayList<Integer>();
            String intString;

            while ((intString = reader.readLine()) != null) {
                intArray.add(Integer.parseInt(intString));
            }

            HashMap inversionCountResult = counter.countInversions(
                    ArrayUtils.toPrimitive(intArray.toArray(new Integer[intArray.size()])));

            System.out.println("Found " + inversionCountResult.get("count") + " inversions.");
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }


    public HashMap countInversions(int[] input){

        HashMap output = new HashMap();
        output.put("array", new int[1]);
        output.put("count", 0L);

        // Basis step
        if (input.length <= 1){
            output.put("array", input);
        }
        // Recursion
        else{
            // Integer division takes care of splitting odd length arrays into a left of length
            // 1 less than the right
            HashMap leftHalfCounted = countInversions(Arrays.copyOfRange(input, 0, input.length / 2));
            HashMap rightHalfCounted = countInversions(Arrays.copyOfRange(input, input.length / 2, input.length));

            // Count split inversions
            HashMap splitCounted = countSplitInversions((int[])leftHalfCounted.get("array"),
                                                        (int[])rightHalfCounted.get("array"));

            output.put("array", splitCounted.get("array"));
            output.put("count", ((Long)leftHalfCounted.get("count") +
                                 (Long)rightHalfCounted.get("count") +
                                 (Long)splitCounted.get("count")));
        }

        return output;
    }

    public HashMap countSplitInversions(int[] left, int[] right){
        int[] outputArray = new int[left.length + right.length];

        int iLeft = 0;
        int iRight = 0;

        long count = 0;

        for (int k = 0; k < outputArray.length; k++){
            if (iLeft == left.length){
                System.arraycopy(right, iRight, outputArray, k, outputArray.length-k);
                k = outputArray.length;
            }
            else if (iRight == right.length){
                System.arraycopy(left, iLeft, outputArray, k, outputArray.length-k);
                k = outputArray.length;
            }
            else if (left[iLeft] < right[iRight]){
                outputArray[k] = left[iLeft];
                iLeft++;
            }
            else{
                outputArray[k] = right[iRight];
                iRight++;
                count += (left.length - iLeft);
            }
        }

        HashMap outputMap = new HashMap();
        outputMap.put("array", outputArray);
        outputMap.put("count", count);

        return outputMap;
    }
}
