package com.aristideniyungeko;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 4/16/13
 */
public class PlusOneTester {


   public static void main(String[] args) {
      PlusOne adder = new PlusOne();

      int[] testInputFirst = {1, 2, 3, 5};
      int[] testOutputFirst = {1, 2, 3, 6};

      if (Arrays.equals(testOutputFirst, adder.plusOne(testInputFirst))) {
         System.out.println("Simple addition works!");
      }

      int[] testInputSecond = {9, 9, 9};
      int[] testOutputSecond = {1, 0, 0, 0};

      if (Arrays.equals(testOutputSecond, adder.plusOne(testInputSecond))) {
         System.out.println("Tricky addition works!");
      }
   }
}
