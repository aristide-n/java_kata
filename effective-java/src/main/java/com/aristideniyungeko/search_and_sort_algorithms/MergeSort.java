package com.aristideniyungeko.search_and_sort_algorithms;

import java.util.Arrays;
public class MergeSort {
   public static void mergeSort(int[] arr) {
      int[] helper = new int[arr.length];
      mergeSort(arr, helper, 0, arr.length - 1);
   }

   private static void mergeSort(int[] arr, int[] helper, int low, int high) {
      // sentinel to stop division for 1 element array
      if (low < high) {
         int middle = (low + high) / 2;
         mergeSort(arr, helper, low, middle);
         mergeSort(arr, helper, middle + 1, high);
         merge(arr, helper, low, middle, high);
      }
   }

   private static void merge(int[] arr, int[] helper, int low, int middle, int high) {
      System.arraycopy(arr, low, helper, low, (high - low) + 1);
      int helperLeft = low;
      int helperRight = middle + 1;
      int current = low;

      while (helperLeft <= middle && helperRight <= high) {
         if (helper[helperLeft] < helper[helperRight]) {
            arr[current] = helper[helperLeft];
            helperLeft++;
         } else {
            arr[current] = helper[helperRight];
            helperRight++;
         }
         current++;
      }

      int remaining  = middle - helperLeft;

      if (remaining >= 0) {
         System.arraycopy(helper, helperLeft, arr, current, remaining + 1);
      }
   }

   public static void main(String[] args) {
      int[] inputFirst = {};
      int[] outputFirst = {};

      int[] inputSecond = {1};
      int[] outputSecond = {1};

      int[] inputThird = {4, 6, 2, 7, 2, 9, 3, 5};
      int[] outputThird = {2, 2, 3, 4, 5, 6, 7, 9};

      int[] inputFourth = {4, 6, 2, 7, 2, 9, 3, 5, 8};
      int[] outputFourth = {2, 2, 3, 4, 5, 6, 7, 8, 9};

      mergeSort(inputFirst);
      if (Arrays.equals(outputFirst, inputFirst)) {
         System.out.println("Base case length 0 works");
      }

      mergeSort(inputSecond);
      if (Arrays.equals(outputSecond, inputSecond)) {
         System.out.println("Base case length 1 works");
      }

      mergeSort(inputThird);
      if (Arrays.equals(outputThird, inputThird)) {
         System.out.println("All even length sorting works");
      }

      mergeSort(inputFourth);
      if (Arrays.equals(outputFourth, inputFourth)) {
         System.out.println("Odd length sorting works");
      }
   }
}
