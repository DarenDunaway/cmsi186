public class Pole {
  private static double[] position = new double[2];

  public Pole() {
    position[0] = Math.round((Math.random() * (399 - 1)) + 1);
    position[1] = Math.round((Math.random() * (399 - 1)) + 1);
  }

  public Pole(double x, double y) {
    position[0] = x;
    position[1] = y;
  }

  public static double getXPosition() {
    return position[0];
  }

  public static double getYPosition() {
    return position[1];
  }

  public static double[] getPosition() {
    return position;
  }

  public String toString() {
    String xPosition = Double.toString(getXPosition());
    String yPosition = Double.toString(getYPosition());

    return String.format("Pole position:\n(%s, %s)", xPosition, yPosition);
  }

  public static void main(String[] args) {
    System.out.println("Pole tests\n");
    System.out.println("Testing instance with no values\n");
    try {
      Pole pole0 = new Pole();
      System.out.println(pole0.toString());
    } catch(Exception error) {
      throw error;
    }
    try {
      Pole pole1 = new Pole();
      System.out.println(pole1.toString());
    } catch(Exception error) {
      throw error;
    }
    try {
      Pole pole2 = new Pole();
      System.out.println(pole2.toString());
    } catch(Exception error) {
      throw error;
    }
    System.out.println("\nTesting instance with no values\n");
    try {
      Pole pole3 = new Pole(323, 234);
      System.out.println(pole3.toString());
    } catch(Exception error) {
      throw error;
    }
    try {
      Pole pole4 = new Pole(23209, 3294.3);
      System.out.println(pole4.toString());
    } catch(Exception error) {
      throw error;
    }
    try {
      Pole pole5 = new Pole(9034, 320.09);
      System.out.println(pole5.toString());
    } catch(Exception error) {
      throw error;
    }
  }
}
