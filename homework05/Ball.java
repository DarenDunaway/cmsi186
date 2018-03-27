public class Ball {

  private double[] position = new double[2];
  private double[] velocity = new double[2];

  public Ball() {
    position[0] = Math.round((Math.random() * (399 - 1)) + 1);
    position[1] = Math.round((Math.random() * (399 - 1)) + 1);
    velocity[0] = Math.round((Math.random() * (3 - 1)) + 1);
    velocity[1] = Math.round((Math.random() * (4 - 1)) + 1);
  }

  public Ball(double x, double y, double dxdt, double dydt) {
    position[0] = x;
    position[1] = y;
    velocity[0] = dxdt;
    velocity[1] = dydt;
  }

  public double getXPosition() {
    return position[0];
  }

  public double getYPosition() {
    return position[1];
  }

  public double[] getPosition() {
    return position;
  }

  public double getXVelocity() {
    return velocity[0];
  }

  public double getYVelocity() {
    return velocity[1];
  }

  public double[] getVelocity() {
    return velocity;
  }

  public double[] updateSpeed(double time) {
    velocity[0] -= velocity[0] * 0.01 * time;
    velocity[1] -= velocity[1] * 0.01 * time;
    return velocity;
  }

  public void move(double time) {
    position[0] += velocity[0];
    position[1] += velocity[1];
    velocity = updateSpeed(time);
  }

  public boolean isActive() {
    return 1.0 <= velocity[0] * 12.0 && 1.0 <= velocity[1] * 12.0;
  }

  public boolean isInBounds(double fieldWidth, double fieldHeight) {
    if (
        position[0] >= fieldWidth || position[0] < 0 ||
        position[1] >= fieldHeight  || position[1] < 0
       ) {
      System.out.println("OUT OF BOUNDS!");
      return false;
    }

    return true;
  }

  public String toString() {
    String x = Double.toString(position[0]);
    String y = Double.toString(position[1]);
    String dxdt = Double.toString(velocity[0]);
    String dydt = Double.toString(velocity[1]);

    String result = String.format("Position:\t(%s, %s)\n", x, y);

    if (!isActive()) {
      result += "AT REST";
    } else {
      result += String.format("Velocity:\t<%s, %s>\n", dxdt, dydt);
    }

    return result;
  }

  public static void main(String args[]) {
    System.out.println("Ball tests\n");
    System.out.println("Testing instance with no values\n");
    try {
      Ball ball0 = new Ball();
      System.out.printf("Testing for random position \n%s\n", ball0.toString());
    } catch(Exception error) {
      System.out.printf("ERROR:\n%s", error.getMessage());
    }
    System.out.println("\nTesting instance with values\n");
    try {
      Ball ball1 = new Ball(50, 30, 2, 6);
      System.out.printf("Testing for position (50,30)\n%s\n", ball1.toString());
    } catch(Exception error) {
      System.out.printf("ERROR:\n%s", error.getMessage());
    }
    try {
      Ball ball2 = new Ball(2, 45, 3, 7);
      System.out.printf("Testing for position (2,45)\n%s\n", ball2.toString());
    } catch(Exception error) {
      System.out.printf("ERROR:\n%s", error.getMessage());
    }
    try {
      Ball ball3 = new Ball(48.9, 92.4, 4, 8);
      System.out.printf("Testing for position (48.9,92.4)\n%s\n", ball3.toString());
    } catch(Exception error) {
      System.out.printf("ERROR:\n%s", error.getMessage());
    }
    try {
      Ball ball4 = new Ball(100.094, 20.203, 5, 9);
      System.out.printf("Testing for position (100.094,20.203)\n%s\n", ball4.toString());
    } catch(Exception error) {
      System.out.printf("ERROR:\n%s", error.getMessage());
    }
    System.out.println("\nTesting for ball movement.\n");
    try {
      Ball ball5 = new Ball(20, 50, 6, 10);
      System.out.println(String.format("Position before movement:\n%s", ball5.toString()));
      ball5.move(1.0);
      ball5.move(1.0);
      ball5.move(5.0);
      ball5.move(7.0);
      ball5.move(20.0);
      ball5.move(18.0);
      ball5.move(200.0);
      ball5.move(18.0);
      System.out.println(String.format("Position after movement:\n%s", ball5.toString()));
      System.out.println(true == ball5.isActive() ? "Ball is moving!" : "Ball is at rest!");
    } catch(Exception error) {
      System.out.printf("ERROR:\n%s", error.getMessage());
    }
    try {
      Ball ball6 = new Ball(100, 104, 10, 10);
      System.out.println(String.format("Position before movement:\n%s", ball6.toString()));
      ball6.move(1.0);
      ball6.move(2.0);
      ball6.move(1.0);
      System.out.println(String.format("Position after movement:\n%s", ball6.toString()));
      System.out.println(true == ball6.isActive() ? "Ball is moving!" : "Ball is at rest!");
      System.out.println(true == ball6.isInBounds(500.0, 500.0) ? "Ball is still in bounds!" : "Ball is out of bounds.");
    } catch(Exception error) {
      System.out.printf("ERROR:\n%s", error.getMessage());
    }
    try {
      Ball ball7 = new Ball(150, 120, 3, 6);
      Ball ball8 = new Ball(112, 180, 2, 3);
      Ball ball9 = new Ball(150, 120, 4, 7);
      System.out.printf("Starting Position for ball7:\n%s\n", ball7.toString());
      System.out.printf("Starting Position for ball8:\n%s\n", ball8.toString());
      System.out.printf("Starting Position for ball9:\n%s\n", ball9.toString());
      while(
      (ball7.isInBounds(400, 400) && ball7.isActive()) &&
      (ball8.isInBounds(400, 400) && ball8.isActive()) &&
      (ball9.isInBounds(400,400) && ball8.isActive())) {
        ball7.move(1.0);
        ball8.move(2.0);
        ball9.move(3.0);

        System.out.println(String.format("(Ball 7) Position after movement:\n%s", ball7.toString()));
        System.out.println(String.format("(Ball 8) Position after movement:\n%s", ball8.toString()));
        System.out.println(String.format("(Ball 9) Position after movement:\n%s", ball9.toString()));
      }
    } catch (Exception error) {
      System.out.printf("ERROR:\n%s", error.getMessage());
    }
  }
}
