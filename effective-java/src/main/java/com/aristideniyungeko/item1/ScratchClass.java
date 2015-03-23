package com.aristideniyungeko.item1;

import java.util.Scanner;

/**
 * Use Class<>.newInstance() as an Abstract factory
 */
public class ScratchClass {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      System.out.println("Enter text followed by Enter, type :q to finish:");
      while (sc.hasNext()) {
         String next = sc.next();

         if (next.equals(":q")) {
            break;
         }

         System.out.println(next);
      }
   }
}
