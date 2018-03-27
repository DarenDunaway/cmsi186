import java.net.InetAddress;

public class SoccerSim {

  private static String getUserAddress() {
    String userAddress = "192.00.00.1";
    try {
      userAddress = InetAddress.getLocalHost().toString();
    } catch(Exception error) {
      System.out.println(String.format("HOST ERROR: %s", error.getMessage()));
    }

    return userAddress;
  }

  private static String ballStats(Ball ball) {
    return String.format("\n%s\n", ball.toString());
  }

  private static void logReport(String report, Ball[] balls) {
    System.out.println(report);
    for (Ball ball : balls) {
      System.out.println(ballStats(ball));
    }
  }

  private static double evaluateArguments(String[] args, Ball[] balls) {
    double timeSlice = 1.0;

    if (args.length == 0) {
      throw new IllegalArgumentException("You must include command line arguments!");
    }

    if (args.length < 4 || args.length % 4 > 1) {
      throw new IllegalArgumentException("Argument length must be valid for a correct simulation.");
    }

    if (args.length % 4 == 1) {
      try {
        timeSlice = Double.parseDouble(args[args.length - 1]);
      } catch (Exception error) {
        throw new IllegalArgumentException("[ Evaluate your Time Slice value! ]");
      }
    }

    if (timeSlice <= 0.0) {
      throw new IllegalArgumentException("[ Evaluate your Time Slice value! ]");
    }

    for(int i = 0; i < args.length / 4; i++) {
      try {
        double x = Double.parseDouble(args[4*i]);
        double y = Double.parseDouble(args[4*i+1]);
        double dxdt = Double.parseDouble(args[4*i+2]);
        double dydt = Double.parseDouble(args[4*i+3]);

        balls[i] = new Ball(x, y, dxdt, dydt);
      } catch(Exception error) {
        throw new IllegalArgumentException(String.format("ERROR: %s", error.getMessage()));
      }
    }

    return timeSlice;
  }

  private static void simulationCountdown() {
    double countdownValue = 3;
    System.out.println("Simulation begins in:");
    while(countdownValue > 0) {
      try {
        System.out.println((int)countdownValue);
        Thread.sleep(1000);
        countdownValue--;
      } catch(InterruptedException error) {
        error.printStackTrace();
      }
    }
  }

  private static boolean ballsAreActive(Ball[] balls) {
    for (Ball ball : balls) {
      if (ball.isActive()) return true;
    }
    return false;
  }

  private static boolean collisionDetected(Ball[] balls, Field field, Pole pole, Timer time) {
    for(int i = 0; i < balls.length; i++) {
      if (!balls[i].isInBounds(field.getWidth(), field.getHeight())) {
        return true;
      } else if (balls[i].getPosition() == pole.getPosition()) {
        return true;
      }
      for(int j = i + 1; j < balls.length; j++) {
        if (balls[i].getPosition() == balls[j].getPosition()) {
          System.out.println(String.format("CONTACT @%s",time.toString()));
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Ball[] balls = new Ball[args.length / 4];
    double ts = evaluateArguments(args, balls);

    Timer timer = new Timer(0, 0, 0, ts);
    Field field = new Field();
    Pole pole = new Pole();

    System.out.println(String.format("Welcome to SoccerSim %s!\n", getUserAddress()));

    logReport(String.format("INITIAL DATA at %s", timer.toString()), balls);

    simulationCountdown();

    while(!collisionDetected(balls, field, pole, timer) && ballsAreActive(balls)) {
      timer.tick(ts);
      for (Ball ball : balls) {
        ball.move(ts);
      }
      logReport(String.format("UPDATE at %s", timer.toString()), balls);
    }


  }
}
