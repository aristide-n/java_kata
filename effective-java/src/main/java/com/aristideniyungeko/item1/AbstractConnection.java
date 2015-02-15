package com.aristideniyungeko.item1;

public class AbstractConnection implements Connection{
   @Override
   public void connect() {
      System.out.println("connect " + getClass().getName());
   }

   @Override
   public void disconnect() {
      System.out.println("disconnect " + getClass().getName());
   }
}
