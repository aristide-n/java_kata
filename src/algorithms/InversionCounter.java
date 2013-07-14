package algorithms;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 7/8/13
 */
public class InversionCounter {
    public HashMap countInversions(int[] input){

        HashMap output = new HashMap();
        output.put("array", new int[1]);
        output.put("count", 0);

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
            output.put("count", ((Integer)leftHalfCounted.get("count") +
                                 (Integer)rightHalfCounted.get("count") +
                                 (Integer)splitCounted.get("count")));
        }

        return output;
    }

    public HashMap countSplitInversions(int[] left, int[] right){
        int[] outputArray = new int[left.length + right.length];

        int iLeft = 0;
        int iRight = 0;

        int count = 0;

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
