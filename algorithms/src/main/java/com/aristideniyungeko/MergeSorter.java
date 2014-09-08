package com.aristideniyungeko;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 7/8/13
 */
public class MergeSorter {
   public int[] sort(int[] input) {

      int[] output;

      // Base case
      if (input.length <= 1) {
         output = input;
      }
      // Recursion
      else {
         // Integer division takes care of splitting odd length arrays into a left of length
         // 1 less than the right
         int[] leftHalfSorted = sort(Arrays.copyOfRange(input, 0, input.length / 2));
         int[] rightHalfSorted = sort(Arrays.copyOfRange(input, input.length / 2, input.length));

         // Merge
         output = merge(leftHalfSorted, rightHalfSorted);
      }

      return output;
   }

   public int[] merge(int[] left, int[] right) {
      int[] output = new int[left.length + right.length];

      int iLeft = 0;
      int iRight = 0;

      for (int k = 0; k < output.length; k++) {
         if (iLeft == left.length) {
            System.arraycopy(right, iRight, output, k, output.length - k);
            k = output.length;
         } else if (iRight == right.length) {
            System.arraycopy(left, iLeft, output, k, output.length - k);
            k = output.length;
         } else if (left[iLeft] < right[iRight]) {
            output[k] = left[iLeft];
            iLeft++;
         } else {
            output[k] = right[iRight];
            iRight++;
         }
      }

      return output;
   }
}
