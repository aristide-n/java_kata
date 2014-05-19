package algorithms;

import java.util.Arrays;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 7/16/13
 */
public class ClosestPairFinderTester {
   public static void main(String[] args) {
      ClosestPairFinder finder = new ClosestPairFinder();

      // Base case examples
      Point2D[] firstInput = {new Point2D.Double(4, 3), new Point2D.Double(3, 5), new Point2D.Double(5, 4)};
      Point2D[] firstOutput = {new Point2D.Double(4, 3), new Point2D.Double(5, 4)};
      if (Arrays.asList(firstOutput).containsAll(Arrays.asList(finder.findClosestPair(firstInput)))) {
         System.out.println("First base case test passed");
      }

      Point2D[] secondInput = {new Point2D.Double(5, 4), new Point2D.Double(4, 3), new Point2D.Double(2, 1)};
      Point2D[] secondOutput = {new Point2D.Double(4, 3), new Point2D.Double(5, 4)};
      if (Arrays.asList(secondOutput).containsAll(Arrays.asList(finder.findClosestPair(secondInput)))) {
         System.out.println("Second base case test passed");
      }

      // Recursion examples

      // 1: Split pair
      Point2D[] rFirstInput = {new Point2D.Double(2, 1),
                               new Point2D.Double(80, 20),
                               new Point2D.Double(800, 200),
                               new Point2D.Double(8000, 2000),
                               new Point2D.Double(80000, 20000),
                               new Point2D.Double(8, 2),
                               new Point2D.Double(4, 3),
                               new Point2D.Double(3, 5),
                               new Point2D.Double(50, 40),
                               new Point2D.Double(500, 400),
                               new Point2D.Double(5000, 4000),
                               new Point2D.Double(50000, 40000),
                               new Point2D.Double(5, 4),
                               new Point2D.Double(5, 4),
                               new Point2D.Double(7, 6)};

      Point2D[] rFirstOutput = {new Point2D.Double(4, 3), new Point2D.Double(5, 4)};
      if (Arrays.asList(rFirstOutput).containsAll(Arrays.asList(finder.findClosestPair(rFirstInput)))) {
         System.out.println("First recursion test passed");
      }

      // 2: pair in left half
      Point2D[] rSecondInput = {new Point2D.Double(5, 4),
                                new Point2D.Double(8, 2),
                                new Point2D.Double(4, 3),
                                new Point2D.Double(2, 1),
                                new Point2D.Double(3, 5),
                                new Point2D.Double(7, 6)};
      Point2D[] rSecondOutput = {new Point2D.Double(5, 4), new Point2D.Double(4, 3)};
      if (Arrays.asList(rSecondOutput).containsAll(Arrays.asList(finder.findClosestPair(rSecondInput)))) {
         System.out.println("Second recursion test passed");
      }
   }
}
