/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final int MINIMUM_TIME_SLICE_VALUE = 0;
   private static final int MAXIMUM_TIME_SLICE_VALUE = 1800;
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MIDWAY_DEGREE_VALUE = 180.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double MINIMUM_DEGREE_VALUE = 0.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

   private static double seconds;
   private static double totalSeconds;
   private static double minutes;
   private static double hours;

  /**
   *  Constructor goes here
   */
   public Clock() {
     seconds = 0.0;
     minutes = 0;
     hours = 0;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick(double timeSlice) {
    if(timeSlice < 0 || timeSlice > 1800) throw new IllegalArgumentException("TIMESLICE OUT OF CONSTRAINTS!");

    seconds += timeSlice;
    totalSeconds += timeSlice;
    seconds = seconds % DEFAULT_TIME_SLICE_IN_SECONDS;
    minutes = (int) (totalSeconds / DEFAULT_TIME_SLICE_IN_SECONDS) % DEFAULT_TIME_SLICE_IN_SECONDS;
    hours = (int) (totalSeconds / 3600);

    if (minutes >= 60) {
      minutes = minutes - 60;
    }

    if (hours == 24) {
      hours = 0;
    }

    return totalSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      double angle = Double.parseDouble(argValue);

      try {
        if (angle < MINIMUM_DEGREE_VALUE || angle > MAXIMUM_DEGREE_VALUE) {
          throw new NumberFormatException("angle out of specified bounds");
        };
      } catch(Exception error) {
        System.out.println(String.format("validateAngleArg error: %s", error));
      }

      return angle;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
      double tsValue = Double.parseDouble(argValue);

      if (tsValue < MINIMUM_TIME_SLICE_VALUE || tsValue > MAXIMUM_TIME_SLICE_VALUE) {
        return INVALID_ARGUMENT_VALUE;
      }

      return tsValue;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHoursAngle() {
     return getTotalSeconds() * HOUR_HAND_DEGREES_PER_SECOND;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinutesAngle() {
     double minAngle = getTotalSeconds() * MINUTE_HAND_DEGREES_PER_SECOND;
     if (minAngle >= MAXIMUM_DEGREE_VALUE) minAngle = minAngle % MAXIMUM_DEGREE_VALUE;
     return minAngle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
     double differenceBetweenHands = Math.abs(getHoursAngle() - getMinutesAngle());

     if (differenceBetweenHands > MIDWAY_DEGREE_VALUE) {
       differenceBetweenHands = MAXIMUM_DEGREE_VALUE - differenceBetweenHands;
     } else if (differenceBetweenHands == MAXIMUM_DEGREE_VALUE || differenceBetweenHands == MINIMUM_DEGREE_VALUE) {
       differenceBetweenHands = MINIMUM_DEGREE_VALUE;
     }

     return differenceBetweenHands;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }

   /**
    *  Method to fetch the number of seconds
    *  @return double-precision value the seconds private variable
    */
    public double getSeconds() {
       return seconds;
    }

   /**
    *  Method to fetch the number of minutes
    *  @return int value the minutes private variable
    */
    public double getMinutes() {
       return minutes;
    }

    /**
     *  Method to fetch the number of hours
     *  @return int value the hours private variable
     */
     public double getHours() {
        return hours;
     }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
     String secs = Double.toString(getSeconds());
     String mins = Double.toString(getMinutes());
     String hrs = Double.toString(getHours());

     return hrs + ":" + mins + ":" + secs;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println("\nCLOCK CLASS TESTER PROGRAM\n" + "--------------------------\n" );
      System.out.println("  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println(String.format("    New clock created: %s", clock.toString()));
      System.out.println("    TICK: " + clock.tick(1));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(1));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(10));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(100));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(400));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(600));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(1000));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(1500));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(1));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(1));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(10));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(100));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(400));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println("    TICK: " + clock.tick(600));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(1000));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println("    TICK: " + clock.tick(1500));
      System.out.println(String.format("    Clock status: %s", clock.toString()));
      System.out.println( "    Angle Between the Hands " + clock.getHandAngle());
      System.out.println( "    Hour Hand " + clock.getHours());
      System.out.println( "    Minute Hand " + clock.getMinutes());
      System.out.println( "Testing validateAngleArg()....");
      System.out.print( "sending '0 degrees', expecting double value: 0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '10 degrees', expecting double value: 10.0" );
      try { System.out.println( (10.0 == clock.validateAngleArg( "10.0" )) ? " - got 10.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '5 degrees', expecting double value: 5.0" );
      try { System.out.println( (5.0 == clock.validateAngleArg( "5.0" )) ? " - got 5.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '20 degrees', expecting double value: 20.0" );
      try { System.out.println( (20.0 == clock.validateAngleArg( "20.0" )) ? " - got 20.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '25 degrees', expecting double value: 25.0" );
      try { System.out.println( (25.0 == clock.validateAngleArg( "25.0" )) ? " - got 25.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '50 degrees', expecting double value: 50.0" );
      try { System.out.println( (50.0 == clock.validateAngleArg( "50.0" )) ? " - got 50.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '75 degrees', expecting double value: 75.0" );
      try { System.out.println( (75.0 == clock.validateAngleArg( "75.0" )) ? " - got 75.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '100 degrees', expecting double value: 100.0" );
      try { System.out.println( (100.0 == clock.validateAngleArg( "100.0" )) ? " - got 100.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '200 degrees', expecting double value: 200.0" );
      try { System.out.println( (200.0 == clock.validateAngleArg( "200.0" )) ? " - got 200.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '300 degrees', expecting double value: 300.0" );
      try { System.out.println( (300.0 == clock.validateAngleArg( "300.0" )) ? " - got 300.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '330 degrees', expecting double value: 330.0" );
      try { System.out.println( (330.0 == clock.validateAngleArg( "330.0" )) ? " - got 330.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "sending '360 degrees', expecting double value: 360.0" );
      try { System.out.println( (360.0 == clock.validateAngleArg( "360.0" )) ? " - got 360.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
