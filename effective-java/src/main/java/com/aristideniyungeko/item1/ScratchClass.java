package com.aristideniyungeko.item1;

/**
 * Use Class<>.newInstance() as an Abstract factory
 */
public class ScratchClass {
   public static void main(String[] args) {
       ScratchClass sc = new ScratchClass();

      Connection fooConn = sc.connectionBuilder(FooConnection.class);
      Connection barConn = sc.connectionBuilder(BarConnection.class);

      fooConn.connect();
      barConn.connect();
   }

   Connection connectionBuilder(Class<? extends Connection> c) {
      try {
         return c.newInstance();
      } catch (InstantiationException | IllegalAccessException e) {
         e.printStackTrace();
      }

      return null;
   }
}
