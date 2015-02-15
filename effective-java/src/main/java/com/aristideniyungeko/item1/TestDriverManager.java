package com.aristideniyungeko.item1;

public class TestDriverManager {
   public static void main(String[] args) {
      DriverManager.registerDriver("foo", new FooDriver());
      DriverManager.registerDriver("bar", new BarDriver());
      Connection fooConn = DriverManager.getConnection("foo");
      Connection barConn = DriverManager.getConnection("bar");
      fooConn.connect();
      barConn.connect();
      fooConn.disconnect();
      barConn.disconnect();
      DriverManager.getConnection("illegal");

   }
}
