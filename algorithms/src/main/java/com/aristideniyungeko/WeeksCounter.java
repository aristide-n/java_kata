package com.aristideniyungeko;

import java.util.HashMap;

/**
 *  Counts the number of full weeks across provided months in a given year. The day of January 1st is also
 *  provided for convenience
 */
public class WeeksCounter {
   public static void main(String[] args) {
      System.out.println(countWeeks(2014, "April", "May", "Wednesday"));// == 7;
      System.out.println(countWeeks(2015, "January", "April", "Thursday"));// == 15;
   }

   public static int countWeeks(int Y, String A, String B, String W) {
      //1 find date of first sunday M
      //2 Iterate months from A to B
      //3 find number of first day in month P
      //4 iterate days from P to (P+days in month)
      //5 if day D - M / 7 = 0 , it's a sunday &&
      //6  if (D + 6) < (P+days in month), the next saturday is in the same month, add 1 to number of weeks
      // && add 6 to D to jump to next Sunday
      //7 return number of weeks

      int numWeeks = 0;

      // 1
      HashMap<String, Integer> days = new HashMap<String, Integer>();
      days.put("Sunday", 0);
      days.put("Monday", 1);
      days.put("Tuesday", 2);
      days.put("Wednesday", 3);
      days.put("Thursday", 4);
      days.put("Friday", 5);
      days.put("Saturday", 6);
      int firstSunday = 1 + (7 - days.get(W));

      // 2
      int[] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
      if (Y % 4 == 0) {
         daysInMonths[1] = 29;
      }

      HashMap<String, Integer> months = new HashMap<String, Integer>();
      months.put("January", 0);
      months.put("February", 1);
      months.put("March", 2);
      months.put("April", 3);
      months.put("May", 4);
      months.put("June", 5);
      months.put("July", 6);
      months.put("August", 7);
      months.put("September", 8);
      months.put("October", 9);
      months.put("November", 10);
      months.put("December", 11);
      int startMonth = months.get(A);
      int endMonth = months.get(B);

      for (int i = startMonth; i <= endMonth; i++) {
         // 3
         int firstDayInMonth = 1;
         for (int j = 0; j < i; j++) {
            firstDayInMonth += daysInMonths[j];
         }

         // 4
         for (int k = firstDayInMonth; k < (firstDayInMonth + daysInMonths[i]); k++) {
            //5
            if (((k-firstSunday) % 7 == 0)
                  // 6
                  && ((k + 6) < (firstDayInMonth + daysInMonths[i]))) {
               numWeeks++;
               k += 6;
            }
         }
      }

      //7
      return numWeeks;
   }
}
