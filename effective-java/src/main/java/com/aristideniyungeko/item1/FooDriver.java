package com.aristideniyungeko.item1;

public class FooDriver implements Driver {
   @Override
   public Connection newConnection() {
      return new FooConnection();
   }
}
