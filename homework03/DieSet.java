/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DieSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DieSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DieSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DieSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DieSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DieSet( int startingCount, int startingSides ) {
     if ( startingCount < 1 || startingSides < 4) throw new IllegalArgumentException("DieSet requirements are not met");

     sides = startingSides;
     count = startingCount;

      ds = new Die[ count ];

      for (int i = 0; i < count ; i++) {
        ds[i] = new Die(sides);
      }

      // for (Die d : ds) {
      //   d = new Die(sides);
      // }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
     int sum = 0;

     for (Die d : ds) {
      sum += sides;
     }

     return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
     for (int i = 0; i < count - 1 ; i++) {
       System.out.println(String.format("Roll %d:\nValue: %d", i, ds[i].roll()));
     }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      return ds[dieIndex].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      return ds[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DieSet instance
   */
   public String toString() {
     //most loops the call methods on the instantces of the array, look above
     StringBuffer output = new StringBuffer();

     System.out.println(String.format("DieSet stats:\n DiceSet Sides: %d\n DiceSet Count: %d", sides, count));
     for (Die d : ds) {
       System.out.println(d.toString());
       output.append(String.format("[%d]", d.getValue()));
     }
     return output.toString();
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DieSet ds ) {
      return ds.toString();
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DieSet ds1 ) {
     boolean output = false;

     //check
     if (this.sum() == ds1.sum() && this.sides == ds1.sides && this.count == ds1.count) {
       output = true;
     }

     return output;
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      System.out.println("Test for DieSet Constructor");
      try {
        DieSet testDieSet1 = new DieSet(4, 6);
        System.out.println(testDieSet1.toString());
      } catch (Exception error) { throw new IllegalArgumentException("DieSet Constructor test"); }

      System.out.println("Test for sum()");
      try {
        DieSet testDieSet2 = new DieSet(5, 6);
        System.out.println(String.format("Sum: %d", testDieSet2.sum()));
      } catch (Exception error) { throw new IllegalArgumentException("sum() test"); }

      try {
        DieSet testDieSet3 = new DieSet(6, 10);
        System.out.println(String.format("Sum: %d", testDieSet3.sum()));
      } catch (Exception error) { throw new IllegalArgumentException("sum() test"); }

      System.out.println("Test for roll()");
      try {
        DieSet testDieSet4 = new DieSet(10, 20);
        System.out.println("Rolling testDieSet4");
        testDieSet4.roll();
      } catch (Exception error) { throw new IllegalArgumentException("roll() test"); }

      try {
        DieSet testDieSet5 = new DieSet(5, 15);
        System.out.println("Rolling testDieSet5");
        testDieSet5.roll();
      } catch (Exception error) { throw new IllegalArgumentException("roll() test"); }

      System.out.println("Test for rollIndividual()");
      try {
        DieSet testDieSet6 = new DieSet(6, 6);
        System.out.println(String.format("Rolled new value at index 1:\n%d", testDieSet6.rollIndividual(1)));
        System.out.println(String.format("Rolled new value at index 3\n%d", testDieSet6.rollIndividual(3)));
      } catch (Exception error) { throw new IllegalArgumentException("rollIndividual() test"); }

      System.out.println("Test for getIndividual()");
      try {
        DieSet testDieSet7 = new DieSet(5, 6);
        System.out.println(String.format("Got %d at index 1",testDieSet7.getIndividual(1)));
        System.out.println(String.format("Got %d at index 3",testDieSet7.getIndividual(3)));
      } catch (Exception error) { throw new IllegalArgumentException("getIndividual() test"); }

      System.out.println("Test for public instance toString()");
      try {
        DieSet testDieSet8 = new DieSet(8, 6);
        System.out.println(testDieSet8.toString());
      } catch (Exception error) { throw new IllegalArgumentException("public instance toString()"); }

      System.out.println("Test for class instance toString()");
      try {
        DieSet testDieSet9 = new DieSet(6, 6);
        System.out.println(DieSet.toString(testDieSet9));
      } catch (Exception error) { throw new IllegalArgumentException("class instance toString()"); }

      System.out.println("Test for isIdentical()");
      try {
        DieSet testDieSet10 = new DieSet(7, 6);
        DieSet testDieSet11 = new DieSet(7, 6);
        DieSet testDieSet12 = new DieSet(9, 6);
        System.out.println(true == testDieSet10.isIdentical(testDieSet11) ? "identical" : "not identical");
        System.out.println(false == testDieSet10.isIdentical(testDieSet12) ? "not identical" : "identical");
      } catch (Exception error) { throw new IllegalArgumentException(""); }
   }

}
