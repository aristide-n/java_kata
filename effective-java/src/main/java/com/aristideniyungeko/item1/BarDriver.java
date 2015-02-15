package com.aristideniyungeko.item1;

public class BarDriver implements Driver {
   @Override
   public Connection newConnection() {
      return new BarConnection();
   }
}
