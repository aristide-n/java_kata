package com.aristideniyungeko.item1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JDBC like Service provider framework example
 */
public class DriverManager {
   private DriverManager() {}

   private static final Map<String, Driver> drivers = new ConcurrentHashMap<>();

   public static void registerDriver(final String name, final Driver driver) {
      drivers.put(name, driver);
   }

   public static Connection getConnection(final String driverName) {
      Driver d = drivers.get(driverName);

      if (d == null) {
         throw new IllegalArgumentException("No diver registered with name: " + driverName);
      }

      return d.newConnection();
   }
}
