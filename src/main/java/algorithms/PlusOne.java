package algorithms;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 4/16/13
 */

//http://leetcode.com/onlinejudge#question_66
public class PlusOne {

   public int[] plusOne(int[] input) {
      int step = 10;

      for (int i = 1; i <= input.length && step == 10; i++) {
         step = input[input.length - i] + 1;

         if (step == 10) {
            input[input.length - i] = 0;
         } else {
            input[input.length - i] = step;
         }
      }

      int[] output;

      if (step == 10) {
         output = new int[input.length + 1];
         output[0] = 1;
         System.arraycopy(input, 0, output, 1, input.length);
      } else {
         output = input;
      }

      return output;
   }
}
