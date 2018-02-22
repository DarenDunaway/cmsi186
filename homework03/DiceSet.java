/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
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
public class DiceSet {

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
   public DiceSet( int startingCount, int startingSides ) {
     if ( startingCount < 1 || startingSides < 4) throw new IllegalArgumentException("DiceSet requirements are not met");

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
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
     //most loops the call methods on the instantces of the array, look above
     StringBuffer output = new StringBuffer();

     System.out.println(String.format("DiceSet stats:\n DiceSet Sides: %d\n DiceSet Count: %d", sides, count));
     for (Die d : ds) {
       System.out.println(d.toString());
       output.append(String.format("[%d]", d.getValue()));
     }
     return output.toString();
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds1 ) {
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
      System.out.println("Test for DiceSet Constructor");
      try {
        DiceSet testDiceSet1 = new DiceSet(4, 6);
        System.out.println(testDiceSet1.toString());
      } catch (Exception error) { throw new IllegalArgumentException("DiceSet Constructor test"); }

      System.out.println("Test for sum()");
      try {
        DiceSet testDiceSet2 = new DiceSet(5, 6);
        System.out.println(String.format("Sum: %d", testDiceSet2.sum()));
      } catch (Exception error) { throw new IllegalArgumentException("sum() test"); }

      try {
        DiceSet testDiceSet3 = new DiceSet(6, 10);
        System.out.println(String.format("Sum: %d", testDiceSet3.sum()));
      } catch (Exception error) { throw new IllegalArgumentException("sum() test"); }

      System.out.println("Test for roll()");
      try {
        DiceSet testDiceSet4 = new DiceSet(10, 20);
        System.out.println("Rolling testDiceSet4");
        testDiceSet4.roll();
      } catch (Exception error) { throw new IllegalArgumentException("roll() test"); }

      try {
        DiceSet testDiceSet5 = new DiceSet(5, 15);
        System.out.println("Rolling testDiceSet5");
        testDiceSet5.roll();
      } catch (Exception error) { throw new IllegalArgumentException("roll() test"); }

      System.out.println("Test for rollIndividual()");
      try {
        DiceSet testDiceSet6 = new DiceSet(6, 6);
        System.out.println(String.format("Rolled new value at index 1:\n%d", testDiceSet6.rollIndividual(1)));
        System.out.println(String.format("Rolled new value at index 3\n%d", testDiceSet6.rollIndividual(3)));
      } catch (Exception error) { throw new IllegalArgumentException("rollIndividual() test"); }

      System.out.println("Test for getIndividual()");
      try {
        DiceSet testDiceSet7 = new DiceSet(5, 6);
        System.out.println(String.format("Got %d at index 1",testDiceSet7.getIndividual(1)));
        System.out.println(String.format("Got %d at index 3",testDiceSet7.getIndividual(3)));
      } catch (Exception error) { throw new IllegalArgumentException("getIndividual() test"); }

      System.out.println("Test for public instance toString()");
      try {
        DiceSet testDiceSet8 = new DiceSet(8, 6);
        System.out.println(testDiceSet8.toString());
      } catch (Exception error) { throw new IllegalArgumentException("public instance toString()"); }

      System.out.println("Test for class instance toString()");
      try {
        DiceSet testDiceSet9 = new DiceSet(6, 6);
        System.out.println(DiceSet.toString(testDiceSet9));
      } catch (Exception error) { throw new IllegalArgumentException("class instance toString()"); }

      System.out.println("Test for isIdentical()");
      try {
        DiceSet testDiceSet10 = new DiceSet(7, 6);
        DiceSet testDiceSet11 = new DiceSet(7, 6);
        DiceSet testDiceSet12 = new DiceSet(9, 6);
        System.out.println(true == testDiceSet10.isIdentical(testDiceSet11) ? "identical" : "not identical");
        System.out.println(false == testDiceSet10.isIdentical(testDiceSet12) ? "not identical" : "identical");
      } catch (Exception error) { throw new IllegalArgumentException(""); }
   }

}
