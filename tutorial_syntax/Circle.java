package tutorial_syntax;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 6/8/13
 */
public class Circle {
   public Circle() {
      // Nothing to do
   }

   public Circle(double radius) {
      setRadius(radius);
   }

   public double setRadius(double radius) {
      myRadius = radius;
      return myRadius;
   }

   public String toString() {
      return "my radius is: " + myRadius;
   }

   private double myRadius;
}
