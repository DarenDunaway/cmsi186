import java.lang.Math;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

  public static int count;
  public static int sides;
  public static int highScore;
  public static int score = 0;

  public static DieSet userDieSet;

  public static void evaluateInputtedCount() {
    System.out.println("Welcome to High Roll! Enter the amount of dice for your DieSet!");

    Scanner inputtedCount = new Scanner(System.in);

    if (inputtedCount.hasNextInt()) {
      count = inputtedCount.nextInt();
      return;
    };

    System.out.println("You must input a integer!");
    System.exit(0);
  }

  public static void evaluateInputtedSides() {
    System.out.println("Enter the amount of sides for the die in your DieSet");

    Scanner inputtedSides = new Scanner(System.in);

    if (inputtedSides.hasNextInt()) {
      sides = inputtedSides.nextInt();
      return;
    };

    System.out.println("You must input a integer!");
    System.exit(0);
  }

  public static void createDieSet() {
    userDieSet = new DieSet(count, sides);
    System.out.println(userDieSet.toString());
  }

  public static void promptMainMenu() {
    BufferedReader inputtedOption = new BufferedReader(new InputStreamReader( System.in ));

    while (true) {
      System.out.println("Main menu: Choose an option:\n[1] ROLL ALL THE DICE\n[2] ROLL A SINGLE DIE\n[3] CALCULATE THE SCORE FOR THIS SET\n[4] SAVE THIS SCORE AS HIGH SCORE\n[5] DISPLAY THE HIGH SCORE\n[Q] Quit");
      try {
        String option = inputtedOption.readLine();
        switch (option.charAt(0)) {
            case '1':
              score = score + userDieSet.sum();
              userDieSet.roll();
              break;
            case '2':
              score = score + userDieSet.rollIndividual((int) Math.random() * (count - 1));
              break;
            case '3':
              System.out.println(String.format("Score: %d", score));
              break;
            case '4':
              highScore = score;
              System.out.println(String.format("New Highscore: %d", highScore));
              break;
            case '5':
              System.out.println(String.format("Your highscore is %d", highScore));
              break;
            case 'Q':
            case 'q':
              System.out.println("Goodbye!");
              System.exit(0);
            default:
              System.out.println("You must input a integer!");
              break;
        }
      } catch (Exception error) {
        throw new IllegalArgumentException(error);
      }
    }
  }

  public static void main(String args[]) {
    evaluateInputtedCount();
    evaluateInputtedSides();
    createDieSet();
    promptMainMenu();
  }
}
