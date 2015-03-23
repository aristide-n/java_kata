package com.aristideniyungeko.search_and_sort_algorithms;

public class BinarySearch {
   public static int binarySearch(int[] arr, int x) {
      int low = 0;
      int high = arr.length - 1;
      int mid;

      while (low <= high) {
         mid = (low + high) / 2;
         if (arr[mid] < x) {
            low = mid + 1;
         } else if (arr[mid] > x) {
            high = mid - 1;
         } else {
            return mid;
         }
      }

      return -1; // not found
   }

   public static int binarySearchRecursive(int[] arr, int x, int low, int high) {
      if (low > high) {
         return -1; // not found
      }

      int mid = (low + high) / 2;
      if (arr[mid] < x) {
         return binarySearchRecursive(arr, x, mid + 1, high);
      } else if (arr[mid] > x) {
         return binarySearchRecursive(arr, x, low, mid - 1);
      } else {
         return mid;
      }
   }

   public static void main(String[] args) {
      int[] a = {0, 2, 3, 4, 7, 7, 8, 8, 9, 9};

      assert binarySearch(a, -1) == -1;
      assert binarySearch(a, 3) == 2;

      assert binarySearchRecursive(a, -1, 0, a.length - 1) == -1;
      assert binarySearchRecursive(a, 3, 0, a.length -1) == 2;
   }
}