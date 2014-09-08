package tutorial_syntax;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 6/8/13
 */
public class ClArgs {
   public static void main(String[] args) {

      // Just print
      if (args.length > 1) {
         System.out.println("First arg: " + args[0]);
         System.out.println("Second arg: " + args[1]);
      } else {
         System.out.println("Two args please!");
      }

      // Change
      if (args.length > 0) {
         for (int i = args.length - 1; i >= 0; i--) {
            System.out.println("UPPER Arg " + i + " is " + args[i].toUpperCase());
         }
      }

   }
}
