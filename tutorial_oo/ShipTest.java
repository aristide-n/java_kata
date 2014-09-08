package tutorial_oo;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 6/8/13
 */
public class ShipTest {
   public static void main(String[] args) {
      Ship s1 = new Ship();
      s1.position.setLocation(0.0, 0.0);
      s1.trip_time = 1.0;
      s1.speed = 1.0;
      s1.direction = 0.0; // East
      s1.name = "Belvedere";

      Ship s2 = new Ship();
      s2.position.setLocation(0.0, 1.0);
      s2.trip_time = 1.0;
      s2.speed = 2.0;
      s2.direction = 135.0; // Northwest
      s2.name = "Limpopo";

      // New s1 position
      double s1x = s1.position.getX() + (s1.trip_time * s1.speed * Math.cos(Math.toRadians(s1.direction)));
      double s1y = s1.position.getY() + (s1.trip_time * s1.speed * Math.sin(Math.toRadians(s1.direction)));
      s1.position.setLocation(s1x, s1y);

      // New s2 position
      double s2x = s2.position.getX() + (s2.trip_time * s2.speed * Math.cos(Math.toRadians(s2.direction)));
      double s2y = s2.position.getY() + (s2.trip_time * s2.speed * Math.sin(Math.toRadians(s2.direction)));
      s2.position.setLocation(s2x, s2y);

      System.out.println(s1.name + " is at (" + s1.position.getX() + ", " + s1.position.getY() + ").");
      System.out.println(s2.name + " is at (" + s2.position.getX() + ", " + s2.position.getY() + ").");

   }
}
