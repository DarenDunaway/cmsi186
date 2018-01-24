/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CalendarStuff.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Tyler Ilunga
 *  Date          :  2018-01-18
 *  Description   :  This file provides the supporting methods for the CalendarStuff program which will
 *                   calculate
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 *  @version 1.0.2  2018-01-18  Tyler Ilunga  CMSI 186 homework 01 solutions
 */

import java.util.*;
import java.lang.Math;

public class CalendarStuff {
  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY = 0;
   private static final int MONDAY = SUNDAY + 1;
   private static final int TUESDAY = MONDAY + 1;
   private static final int WEDNESDAY = TUESDAY + 1;
   private static final int THURSDAY = WEDNESDAY + 1;
   private static final int FRIDAY = THURSDAY + 1;
   private static final int SATURDAY = FRIDAY + 1;

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY = 0;
   private static final int FEBRUARY = JANUARY + 1;
   private static final int MARCH   = FEBRUARY + 1;
   private static final int APRIL   = MARCH + 1;
   private static final int MAY   = APRIL + 1;
   private static final int JUNE   = MAY + 1;
   private static final int JULY   = JUNE + 1;
   private static final int AUGUST   = JULY + 1;
   private static final int SEPTEMBER   = AUGUST + 1;
   private static final int OCTOBER   = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER + 1;
   private static final int DECEMBER   = NOVEMBER + 1;

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */

   private static int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {

   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param  year  long containing four-digit year
   * @return       boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear(long year) {
     return (year >= 1582 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0));
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param month long containing month number, starting with "1" for "January"
   * @param year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth(long month, long year) {
     long result = 0;
     switch((int) month - 1) {
       case JANUARY:
       case MARCH:
       case MAY:
       case JULY:
       case AUGUST:
       case OCTOBER:
       case DECEMBER:
        result = 31;
        break;
      case APRIL:
      case JUNE:
      case SEPTEMBER:
      case NOVEMBER:
        result = 30;
        break;
      case FEBRUARY:
        result = CalendarStuff.isLeapYear(year) ? 29 : 28;
        break;
      default: throw new IllegalArgumentException( "Illegal month value given to 'daysInMonth()'." );
     }

      return result;
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return ((month1 == month2) && (day1 == day2) && (year1 == year2));
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */

   //ASK ABOUT OLDER VS YOUNGEER
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     int result = 0;
      if(year1 == year2) {
        if (month1 == month2) {
          if (day1 == day2) {
            result = 0;
          } else if (day1 < day2) {
            result = -1;
          } else if(day1 > day2) {
            result = 1;
          }
        } else if(month1 < month2) {
          result = -1;
        } else if (month1 > month2) {
          result = 1;
        }
      } else if(year1 > year2) {
        result = 1;
      } else if(year1 < year2) {
        result = -1;
      }

      return result;
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      return ((month > 0 && month < 13) && (day > 0 && day < 32) && (year > -1));
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
     String[] months = {
       "January", "February", "March", "April",
       "May", "June", "July", "August",
       "September", "October", "November", "December",
     };

     return months[month - 1] != null ? months[month - 1] : "Illegal month value given to 'toMonthString()'.";
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
     String result = "";
      switch( day - 1 ) {
         case 0:
            result = "Sunday";
            break;
         case 1:
           result = "Monday";
           break;
         case 2:
           result = "Tuesday";
           break;
         case 3:
           result = "Wednesday";
           break;
         case 4:
           result = "Thursday";
           break;
         case 5:
           result = "Friday";
           break;
         case 6:
           result = "Saturday";
           break;
         default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }

      return result;
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      long dayCount = 0;
      double milliseconds = 86400000;

      Calendar setFirstDate = Calendar.getInstance();
      setFirstDate.set((int) year1,(int) (month1 - 1),(int) day1);
      Calendar setSecondDate = Calendar.getInstance();
      setSecondDate.set((int) year2,(int) (month2 - 1),(int) day2);

      Date firstDate = setFirstDate.getTime();
      Date secondDate = setSecondDate.getTime();

      if (firstDate.getTime() > secondDate.getTime()) {
        dayCount = (long)Math.round(((double)(firstDate.getTime() - secondDate.getTime())/milliseconds));
      } else {
        dayCount = (long)Math.round(((double)(secondDate.getTime() - firstDate.getTime())/milliseconds));
      }

      return dayCount;
   }
}
