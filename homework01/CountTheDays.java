/**
 *  File name     :  CountTheDays.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  Tyler Ilunga
 *  Date          :  2018-01-23 (prototype)
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  Tyler Ilunga  CMSI 186 homework 01 solutions
 */

public class CountTheDays {
  public static void main(String args[]) {
    long month1 = Long.parseLong(args[0]);
    long day1 = Long.parseLong(args[1]);
    long year1 = Long.parseLong(args[2]);
    long month2 = Long.parseLong(args[3]);
    long day2 = Long.parseLong(args[4]);
    long year2 = Long.parseLong(args[5]);

    CalendarStuff.daysBetween(month1, day1, year1, month2, day2, year2);
  }
}
