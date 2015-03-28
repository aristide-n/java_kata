package com.aristideniyungeko.search_and_sort_algorithms;

import java.util.Arrays;

public class QuickSort {
   public static void quickSort(int[] arr, int left, int right) {
      if ((right - left) > 0) {
         int pivotIndex = partition(arr, left, right);
         quickSort(arr, left, pivotIndex - 1);
         quickSort(arr, pivotIndex, right);
      }
   }

   private static int partition(int[] arr, int left, int right) {
      int pivot = arr[(left + right) / 2]; // select pivot in the middle

      while (left <= right) {
         while (arr[left] < pivot) {
            left++;
         }
         while (arr[right] > pivot) {
            right--;
         }

         // swap
         if (left <= right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
         }
      }

      return left;
   }

   /**
    * Doctests:
    >>> my_list = [2, 3, 7, 8, 0, 9, 3, 7, 4, 8, 9]
    >>> sort (my_list, 0, len(my_list) - 1)
    >>> print my_list
    [0, 2, 3, 3, 4, 7, 7, 8, 8, 9, 9]
    >>> my_list = [5, 4, 2, 7, 1, 3]
    >>> sort (my_list, 0, len(my_list) - 1)
    >>> print my_list
    [1, 2, 3, 4, 5, 7]
    >>> my_list = [4, 0, 5, 1]
    >>> sort (my_list, 0, len(my_list) - 1)
    >>> print my_list
    [0, 1, 4, 5]
    */
   public static void main(String[] args) {
      int[] inputFirst = {};
      int[] outputFirst = {};

      int[] inputSecond = {1};
      int[] outputSecond = {1};

      int[] inputThird = {2, 3, 7, 8, 0, 9, 3, 7, 4, 8, 9};
      int[] outputThird = {0, 2, 3, 3, 4, 7, 7, 8, 8, 9, 9};

      int[] inputFourth = {5, 4, 2, 7, 1, 3};
      int[] outputFourth = {1, 2, 3, 4, 5, 7};

      int[] inputFifth = {4, 0, 5, 1};
      int[] outputFifth = {0, 1, 4, 5};

      quickSort(inputFirst, 0, inputFirst.length - 1);
      if (Arrays.equals(outputFirst, inputFirst)) {
         System.out.println("Pass test 1");
      }

      quickSort(inputSecond, 0, inputSecond.length - 1);
      if (Arrays.equals(outputSecond, inputSecond)) {
         System.out.println("Pass test 2");
      }

      quickSort(inputThird, 0, inputThird.length - 1);
      if (Arrays.equals(outputThird, inputThird)) {
         System.out.println("Pass test 3");
      }

      quickSort(inputFourth, 0, inputFourth.length - 1);
      if (Arrays.equals(outputFourth, inputFourth)) {
         System.out.println("Pass test 4");
      }

      quickSort(inputFifth, 0, inputFifth.length - 1);
      if (Arrays.equals(outputFifth, inputFifth)) {
         System.out.println("Pass test 5");
      }
   }
}
