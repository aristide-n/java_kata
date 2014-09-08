package com.aristideniyungeko;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 7/8/13
 */
public class MergeSorterTester {
   public static void main(String[] args) {
      MergeSorter mergeSorter = new MergeSorter();

      int[] inputFirst = {};
      int[] outputFirst = {};

      int[] inputSecond = {1};
      int[] outputSecond = {1};

      int[] inputThird = {4, 6, 2, 7, 2, 9, 3, 5};
      int[] outputThird = {2, 2, 3, 4, 5, 6, 7, 9};

      int[] inputFourth = {4, 6, 2, 7, 2, 9, 3, 5, 8};
      int[] outputFourth = {2, 2, 3, 4, 5, 6, 7, 8, 9};

      if (Arrays.equals(outputFirst, mergeSorter.sort(inputFirst))) {
         System.out.println("Base case length 0 works");
      }

      if (Arrays.equals(outputSecond, mergeSorter.sort(inputSecond))) {
         System.out.println("Base case length 1 works");
      }

      if (Arrays.equals(outputThird, mergeSorter.sort(inputThird))) {
         System.out.println("All even length sorting works");
      }

      if (Arrays.equals(outputFourth, mergeSorter.sort(inputFourth))) {
         System.out.println("Odd length sorting works");
      }
   }
}
