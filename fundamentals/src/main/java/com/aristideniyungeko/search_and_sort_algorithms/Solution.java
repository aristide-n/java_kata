package com.aristideniyungeko.search_and_sort_algorithms;

//Use a binary search:
//
// Staring at middle element, if it is magic return the index
// if A[i] > i, do binary search on left half,
// otherwise search right half
// return -1 if not found

//Use a binary search:
//
// Staring at middle element, if it is magic return the index
// if A[i] > i, do binary search on left half,
// otherwise search right half
// return -1 if not found

public class Solution {
   public static int getMagicIndex(int[] a) {
      if (a == null) {
         return -1;
      }

      return getMagicIndex(a, 0, a.length - 1);
   }

   private static int getMagicIndex(int[] a, int low, int high) {
      if (low <= high) {
         int middle = (low + high) / 2;

         if (middle == a[middle]) {
            return middle;
         } else if (a[middle] > middle) {
            return getMagicIndex(a, low, middle -1);
         } else {
            return getMagicIndex(a, middle + 1, high);
         }
      }

      return -1; // not found
   }

   public static void main(String[] args) {

      int[] test0 = {1,2,3};
      int[] test1 = {-1, 1, 3, 4,7,9,11};
      int[] test2 = {};
      int[] test3 = {-1, 0, 1, 2, 3, 5, 7};
      int[] test4 = null;

      assert getMagicIndex(test0) == -1;
      assert getMagicIndex(test1) == 1;
      assert getMagicIndex(test2) == -1;
      assert getMagicIndex(test3) == 5;
      assert getMagicIndex(test4) == -3;
   }
}