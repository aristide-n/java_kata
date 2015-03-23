package com.aristideniyungeko.data_structures;

import java.util.Arrays;

/**
 * Min Heap
 */
public class Heap {
   public void heapify(Comparable[] arr) {
      for (int i = arr.length / 2; i >= 0; i--) {
         siftDown(arr, i, arr.length - 1);
      }
   }

   public void siftDown(Comparable[] arr, int pos, int size) {
      Comparable temp = arr[pos];
      int childPos;

      for (; 2 * pos <= size; pos = childPos) {
         childPos = 2 * pos;

         if (childPos != size && arr[childPos].compareTo(arr[childPos + 1]) > 0) {
            childPos++;
         }

         if (temp.compareTo(arr[childPos]) > 0) {
            arr[pos] = arr[childPos];
         } else {
            break;
         }
      }
      arr[pos] = temp;
   }

   public void heapSort(Comparable[] arr) {
      heapify(arr);

      for (int i = arr.length -1; i > 0; i--) {
         // swap with min
         Comparable temp = arr[i];
         arr[i] = arr[0];
         arr[0] = temp;
         // restore min heap
         siftDown(arr, 0, i-1);
      }
   }

   public static void main(String[] args) {
      Heap minHeap = new Heap();

      Integer[] arr = {1,3,4,7,6,5,8,9,0,2};

      System.out.println(Arrays.toString(arr));

      minHeap.heapify(arr);

      System.out.println(Arrays.toString(arr));

      minHeap.heapSort(arr);

      System.out.println(Arrays.toString(arr));
   }
}