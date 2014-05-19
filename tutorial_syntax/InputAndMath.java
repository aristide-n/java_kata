package tutorial_syntax;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 6/8/13
 */
public class InputAndMath {
   public static void main(String[] args) {
      System.out.print("How many random numbers? ");
      Scanner input = new Scanner(System.in);
      int n = input.nextInt();

      for (int i = 1; i <= n; i++) {
         System.out.println("Random number " + i + " is " + Math.random());
      }

      System.out.println();

      System.out.print("How many coin flips? ");
      n = input.nextInt();

      for (int i = 1; i <= n; i++) {
         if (1 / 2.0 > Math.random()) {
            System.out.println("Heads");
         } else {
            System.out.println("Tails");
         }
      }

   }
}
