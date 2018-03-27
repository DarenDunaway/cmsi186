public class Field {

  private static double[] dimensions = new double[2];

  public Field() {
    dimensions[0] = 400;
    dimensions[1] = 400;
  }

  public Field(double width, double height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width or Height has to be greater than 1");
    }

    dimensions[0] = width;
    dimensions[1] = height;
  }

  public double getWidth() {
    return dimensions[0];
  }

  public double getHeight() {
    return dimensions[1];
  }

  public String toString() {
    String width = Double.toString(dimensions[0]);
    String height = Double.toString(dimensions[1]);

    return "Field Dimensions:\nWidth: " + width + "\nHeight: " + height;
  }

  public static void main(String[] args) {
    System.out.println("Field tests\n");
    System.out.println("Testing instance with no values\n");
    try {
      Field field0 = new Field();
      System.out.println(field0.toString());
    } catch(Error error) {
      throw error;
    }
    try {
      Field field1 = new Field();
      System.out.println(field1.toString());
    } catch(Error error) {
      throw error;
    }
    try {
      Field field2 = new Field();
      System.out.println(field2.toString());
    } catch(Error error) {
      throw error;
    }
    System.out.println("\nTesting instance with values\n");
    try {
      Field field3 = new Field(300, 500);
      System.out.println(field3.toString());
    } catch(Error error) {
      throw error;
    }
    try {
      Field field4 = new Field(604, 323);
      System.out.println(field4.toString());
    } catch(Error error) {
      throw error;
    }
    try {
      Field field5 = new Field(323.3, 4333.2);
      System.out.println(field5.toString());
    } catch(Error error) {
      throw error;
    }
  }
}
