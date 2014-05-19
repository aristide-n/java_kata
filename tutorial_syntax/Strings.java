package tutorial_syntax;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 6/8/13
 */
public class Strings {
   public static void main(String[] args) {
      String match = "Test";

      if (0 == args.length) {
         System.out.println("No args");
      } else if (match.equals(args[0])) {
         System.out.println("It's a match!");
      } else {
         System.out.println("No match!");
      }
   }
}
