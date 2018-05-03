/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * File name: DynamicChangeMaker.java
  * Purpose
  * @author Tyler Ilunga
  * @version 1.0.0
  * Description:  This program provides a "DynamicChangeMaker" class DynamicChangeMaker which is the
  *               java program for the "Dynamic Programming" algorithm.
  * Notes      :  None
  * Warnings   :  None
  *
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * Revision History
  * ================
  *   Ver      Date     Modified by:  Reason for change or modification
  *  -----  ----------  ------------  ---------------------------------------------------------------------
  *  1.0.0  2018-05-03  Tyler Ilunga  Initial release; For CMSI 186 homework 07
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class DynamicChangeMaker {
  private static String[] args0Array;
  private static int[] denominations;
  private static String badData = "BAD DATA:";

  /**
   * Check for any duplicates inside of int[] iArray
   *
   * @param iArray an array of integers
   *
   * @throws IllegalArgumentException if there are any duplicates
   */
   private static void checkForDuplicates(int[] iArray) {
     Set<Integer> set = new HashSet<Integer>();

     for (int i : iArray) {
       if (set.contains(i)) {
         throw new IllegalArgumentException(String.format("%s There are more than 2 copies of the %s coin", badData, i));
       } else {
         set.add(i);
       }
     }
   }

   /**
    * Check for any duplicates inside of String[] iArray
    *
    * @param sArray an array of integers
    *
    * @throws IllegalArgumentException if there are any duplicates
    */
    private static void checkForDuplicates(String[] sArray) {
      Set<String> set = new HashSet<String>();

      for (String s : sArray) {
        if (set.contains(s)) {
          throw new IllegalArgumentException(String.format("%s There are more than 2 copies of the %s coin", badData, s));
        } else {
          set.add(s);
        }
      }
    }

    /**
     * Check for any duplicates inside of int[] iArray
     *
     * @param args an array of integers
     * @param target the target amount
     *
     * @throws IllegalArgumentException if there are any arguments out of bounds
     *
     * @return whether or not the arguments passed in are valid
     */
    private static boolean evaluateArgs(int[] args, int target) {
      boolean validArgs = false;

      try {

        DynamicChangeMaker.checkForDuplicates(args);

        if (target < 0) {
          throw new IllegalArgumentException(String.format("%s No negatives allowed for target value!", badData));
        }

        for (int i = 0; i < args.length; i++) {

          if (args[i] == 0) {
            throw new IllegalArgumentException(String.format("%s No zeros allowed for the first argument!", badData));
          } else if (args[i] < 0 ) {
            throw new IllegalArgumentException(String.format("%s No negatives allowed for the first argument!", badData));
          }

        }

        validArgs = true;

      } catch (Exception error) {
        System.out.println(String.format("%s", error.getMessage()));
      }

      return validArgs;


    }
    /**
     * Check for any duplicates inside of int[] iArray
     *
     * @param args an array of integers
     *
     * @throws IllegalArgumentException if there are any arguments out of bounds
     *
     * @return whether or not the arguments passed in are valid
     */
    private static boolean evaluateArgs(String[] args) {
      String[] args0Array = args[0].split(",");
      boolean validArgs = false;

      try {

        checkForDuplicates(args0Array);

        for (int i = 0; i < args0Array.length; i++) {

          if (args0Array[i] == "0") {
            throw new IllegalArgumentException(String.format("%s No zeros allowed for the first argument!", badData));
          } else if (Integer.parseInt(args0Array[i]) < 0 ) {
            throw new IllegalArgumentException(String.format("%s No negatives allowed for the first argument!", badData));
          }

        }

        if (args[1] == "0") {
          throw new IllegalArgumentException(String.format("%s No zeros allowed for the second argument!", badData));
        } else if (Integer.parseInt(args[1]) < 0) {
          throw new IllegalArgumentException(String.format("%s No negatives allowed for the second argument!", badData));
        }

        if (args.length > 2) {
          throw new IllegalArgumentException(String.format("%s Only two arguments are required for this program!", badData));
        }

        validArgs = true;

      } catch (Exception error) {
        System.out.println(String.format("%s", error.getMessage()));
      }

      return validArgs;
    }

    /**
     * Check for any duplicates inside of int[] iArray
     *
     * @param args0 string containing denomination values
     *
     * @return denominations as integers
     */
    public static int[] createChangeSet(String args0) {
      String[] args0Array = args0.split(",");
      int[] output = new int[args0Array.length];

      for (int j = 0; j < args0Array.length; j++) {
        output[j] = Integer.parseInt(args0Array[j]);
      }

      denominations = new int[output.length];

      for (int k = 0; k < output.length; k++) {
        denominations[k] = output[k];
      }

      System.out.println(Arrays.toString(denominations));

      return denominations;
    }

    /**
     * Check for any duplicates inside of int[] iArray
     *
     * @param rows integer for the amount of rows
     * @param columns integer for the amount of columns
     *
     * @return tuple table with rows and columns
     */
    public static Tuple[][] createTable(int rows, int columns) {
      return new Tuple[rows][columns];
    }

    /**
     * Prints lines above and below the table
     *
     * @param numberOfLines an array of integers
     */
    private static void printLines(int numberOfLines) {
      for (int i = 0; i < numberOfLines * 12; i++) {
        if (i == (numberOfLines * 12) - 1) {
          System.out.println('-');
        } else {
          System.out.print('-');
        }
      }
    }

    /**
     * Check for any duplicates inside of int[] iArray
     *
     * @param denominations an array of integers
     * @param target the target amount
     *
     * @throws IllegalArgumentException if there are any duplicates
     *
     * @return the optimal way of making change for that target amount using those denominations 
     */
    public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target) {
      Tuple optimalValue = new Tuple(denominations.length);
      Tuple[][] table = new Tuple[0][0];

      if (DynamicChangeMaker.evaluateArgs(denominations, target)) {
        table = createTable(denominations.length, (target + 1));

        // System.out.println("Inside!");

        printLines((target + 1));

        for (int i = 0; i < denominations.length; i++) {
          // System.out.print("|");
          for (int j = 0; j < target + 1; j++) {
            if (j == 0) {
              table[i][0] = new Tuple(denominations.length);
            } else {
              if (j < denominations[i]) {
                table[i][j] = Tuple.IMPOSSIBLE;
              } else {
                table[i][j] = new Tuple(denominations.length);
                table[i][j].setElement(i, 1);

                if (table[i][j - denominations[i]].isImpossible()) {
                  table[i][j] = Tuple.IMPOSSIBLE;
                } else {
                  table[i][j] = table[i][j].add(table[i][j - denominations[i]]);
                }
              }
            }

            if (i > 0) {
              if (!table[i][j].isImpossible() && !table[i-1][j].isImpossible()) {
                if (table[i][j].total() > table[i-1][j].total()) {
                  table[i][j] = table[i-1][j];
                }
              } else if (!table[i-1][j].isImpossible() && table[i][j].isImpossible()) {
                table[i][j] = table[i-1][j];
              }
            }

            optimalValue = table[i][j];

            if (j == table[i].length - 1) {
              System.out.println(String.format(" %s |", optimalValue));
            } else {
              System.out.print(String.format(" %s |", optimalValue));
            }

          }
        }

        printLines((target + 1));

        return table[denominations.length - 1][target];
      } else {
        printLines((target + 1));
        return Tuple.IMPOSSIBLE;
      }


    }

    public static void main(String[] args) {
      System.out.println("DynoChangeMaker is about to commence...");

      Tuple change = new Tuple(0);

      if (DynamicChangeMaker.evaluateArgs(args)) {
        change = DynamicChangeMaker.makeChangeWithDynamicProgramming(createChangeSet(args[0]), Integer.parseInt(args[1]));
      } else {
        change = Tuple.IMPOSSIBLE;
      }

      System.out.println(change.toString());
    }
  }
