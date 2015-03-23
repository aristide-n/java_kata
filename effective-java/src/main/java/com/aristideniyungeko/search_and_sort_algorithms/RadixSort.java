package com.aristideniyungeko.search_and_sort_algorithms;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * base 10 radix sort algorithm.
 */
public class RadixSort {
   // Use LinkedList as a Queues
   private static LinkedList<Integer>[] buckets = new LinkedList[10];

   public static void sortLSD(int[] array, int maxSymbols) {
      for (int i = 0; i < buckets.length; i++) {
         buckets[i] = new LinkedList<>();
      }

      int divisor = 10;
      int mask = 1;

      for (int k = 0; k < maxSymbols; k++, mask *= 10, divisor *= 10){
         for (int i : array) {
            buckets[(i % divisor) / mask].add(i);
         }

         int pos = 0;
         for (LinkedList<Integer> bucket : buckets) {
            Integer value;
            while ((value = bucket.poll()) != null) {
               array[pos++] = value;
            }
         }
      }
   }

   public static void main(String... args) {
      int[] test = { 10, 1, 30, 156, 100 };

      // we choose 3, because we have 156 with 3 digits
      RadixSort.sortLSD(test, 3);

      System.out.println(Arrays.toString(test));
   }

}
