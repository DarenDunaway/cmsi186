public class Timer {

  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0D;
  private static final double INVALID_ARGUMENT_VALUE = -1.0D;
  private static final double MAXIMUM_DEGREE_VALUE = 360.0D;
  private static final double MAXIMUM_TIMESLICE_VALUE = 1800.0D;
  private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834D;
  private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1D;

  private static double timeSlice;
  private static double totalSeconds;
  private static double[] timeJunque = new double[3];

  private static double hourHandDegrees = 0.0D;
  private static double minuteHandDegrees = 0.0D;

  public Timer() {
    timeSlice = 0;
    totalSeconds = 0;
    timeJunque[0] = 0;
    timeJunque[1] = 0;
    timeJunque[2] = 0;
  }

  public Timer(double seconds, double minutes, double hours, double ts) throws IllegalArgumentException {
    if (0 > seconds || 0 > minutes || 0 > hours) {
      throw new IllegalArgumentException("Initial value(s) out of bounds");
    }

    String stringTimeSlice = String.valueOf(ts);

    timeSlice = validateTimeSlice(stringTimeSlice);

    timeJunque[0] = seconds;
    timeJunque[1] = (int)(minutes);
    timeJunque[2] = (int)(hours);
  }

  public static double validateTimeSlice(String timeSlice) {
    double ts = 0.0;

    try {
      ts = Double.parseDouble(timeSlice);

      if (0 >= ts || 1800 <= ts) {
        throw new IllegalArgumentException("[ TimeSlice out of bounds! ]");
      }

    } catch(Exception error) {
      throw error;
    }

    return ts;

  }

  public double validateAngleArg(String angleValue) {
    double angle = 0.0;

    try {
      angle = Double.parseDouble(angleValue);

      if (angle >= 360.0 || angle <= 0.0) {
        throw new IllegalArgumentException("[ Angle out of bounds! ]");
      }
    } catch(Exception error) {
      throw error;
    }

    return angle;
  }

  public double[] tick(double ts) {
    double[] returnedTimeJunque = new double[3];

    String stringTimeSlice = String.valueOf(ts);

    timeSlice = validateTimeSlice(stringTimeSlice);
    totalSeconds += timeSlice;
    timeJunque[0] = returnedTimeJunque[0] = totalSeconds % DEFAULT_TIME_SLICE_IN_SECONDS;
    timeJunque[1] = returnedTimeJunque[1] = (int)((totalSeconds / DEFAULT_TIME_SLICE_IN_SECONDS) % DEFAULT_TIME_SLICE_IN_SECONDS);
    timeJunque[2] = returnedTimeJunque[2] = (int)(totalSeconds / 3600);

    // if (timeJunque[0] >= 60) {
    //   timeJunque[0] = returnedTimeJunque[0] = 0;
    //   timeJunque[1] = returnedTimeJunque[1] = timeJunque[1] + 1;
    // }
    //
    // if (timeJunque[1] >= 60) {
    //   timeJunque[1] = returnedTimeJunque[0] = 0;
    //   timeJunque[2] = returnedTimeJunque[2] = timeJunque[2] + 1;
    // }
    //
    // if (timeJunque[2] >= 24) {
    //   timeJunque[2] = returnedTimeJunque[2] = 0;
    // }

    return returnedTimeJunque;
  }

  public double getSeconds() {
    return timeJunque[0];
  }

  public double getTotalSeconds() {
    return totalSeconds;
  }

  public double getMinutes() {
    return timeJunque[1];
  }

  public double getHours() {
    return timeJunque[2];
  }

  public double getHourHandAngle() {
    hourHandDegrees = 0.00834D * getTotalSeconds();
    return hourHandDegrees;
  }

  public double getMinuteHandAngle() {
    minuteHandDegrees = 0.1D * (getMinutes() * 60.0D + getSeconds());
    return minuteHandDegrees;
  }

  public double getHandAngle() {
    double d = Math.abs(getHourHandAngle() - getMinuteHandAngle());
    if (d > 180.0D) {
      d = 360.0D - d;
    }
    return d;
  }

  public String toString() {
    String seconds = Double.toString(getSeconds());
    String minutes = Integer.toString((int)getMinutes());
    String hours = Integer.toString((int)getHours());

    return hours + ":" + minutes + ":" + seconds;
  }

  public static void main(String[] args) {
    System.out.println("Tests for Timer");
    System.out.println("Creating a new Timer");
    try {
      Timer timer0 = new Timer();
      System.out.println(String.format("Timer0:\n%s", timer0.toString()));
      System.out.println("Tests for ticks");
      timer0.tick(3);
      System.out.println(String.format("Timer0:\n%s", timer0.toString()));
      timer0.tick(10);
      System.out.println(String.format("Timer0:\n%s", timer0.toString()));
      timer0.tick(20);
      System.out.println(String.format("Timer0:\n%s", timer0.toString()));
      timer0.tick(2);
      System.out.println(String.format("Timer0:\n%s", timer0.toString()));
      timer0.tick(180);
      System.out.println(String.format("Timer0:\n%s", timer0.toString()));
    } catch(Error error) {
      System.out.println(String.format("ERROR: %s", error.getMessage()));
    }
    System.out.println("Tests for passing arguments into the Constructor");
    try {
      Timer timer1 = new Timer(10, 5, 2, 1);
      System.out.println(String.format("Timer1:\n%s", timer1.toString()));
    } catch(Error error) {
      System.out.println(String.format("ERROR: %s", error.getMessage()));
    }
    try {
      Timer timer2 = new Timer(25, 10, 4, 1.23);
      System.out.println(String.format("Timer2:\n%s", timer2.toString()));
    } catch(Error error) {
      System.out.println(String.format("ERROR: %s", error.getMessage()));
    }
    try {
      Timer timer3 = new Timer(35, 20, 9, 5.45);
      System.out.println(String.format("Timer3:\n%s", timer3.toString()));
    } catch(Error error) {
      System.out.println(String.format("ERROR: %s", error.getMessage()));
    }
    try {
      Timer timer4 = new Timer(55, 37, 17, 16);
      System.out.println(String.format("Timer4:\n%s", timer4.toString()));
    } catch(Error error) {
      System.out.println(String.format("ERROR: %s", error.getMessage()));
    }
  }
}
