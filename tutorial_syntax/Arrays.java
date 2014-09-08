package tutorial_syntax;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 6/8/13
 */
public class Arrays {
   public static void main(String[] args) {
      // Test two step init
      for (Circle circle : makeCircles(5)) {
         System.out.println(circle.toString());
      }
      System.out.println();

      // Two dimensional arrays
      rightTriangleArray(10);
      System.out.println();

      //Create an array of 100 random numbers. Use two-step array allocation. Print out the sum of the square roots of
      // the values.
      double[] numbers = new double[100];
      for (int i = 0; i < numbers.length; i++) {
         numbers[i] = Math.random();
      }
      double sum = 0;
      for (int i = 0; i < numbers.length; i++) {
         sum += Math.sqrt(numbers[i]);
      }
      System.out.println("The sum of random values is " + sum);
      System.out.println();
   }

   public static Circle[] makeCircles(int numCircles) {
      Circle[] circles = new Circle[numCircles];

      for (int i = 0; i < circles.length; i++) {
         circles[i] = new Circle(Math.random() * 10);
      }

      return circles;
   }

   public static void rightTriangleArray(int depth) {
      int[][] triangle = new int[depth][];

      for (int i = 0; i < triangle.length; i++) {
         triangle[i] = new int[i + 1];
      }

      for (int i = 0; i < triangle.length; i++) {
         for (int j = 0; j < triangle[i].length; j++) {
            System.out.print(triangle[i][j]);
         }
         System.out.println();
      }
   }
}
