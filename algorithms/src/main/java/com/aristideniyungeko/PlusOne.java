package com.aristideniyungeko;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 4/16/13
 */

//http://leetcode.com/onlinejudge#question_66
public class PlusOne {

   public int[] plusOne(int[] digits) {
      // The are 3 cases:
      // 1 2 3 4 + 1 = 1235, 1299 + 1 = 1300, and 99999 + 1 = 10000
      int step = 10;
        
      for (int i = 1; i <= digits.length && step == 10; i++) {
         step = digits[digits.length - i] + 1;
            
         if (step == 10) {
             digits[digits.length - i] = 0;
         } else {
            digits[digits.length - i] = step;
         }
      }
        
     int[] output;
        
     if (step == 10) {
         output = new int[digits.length + 1];
         output[0] = 1;
         System.arraycopy(digits, 0, output, 1, digits.length);
     } else {
         output = digits;
     }
        
     return output;
   }
}
