import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;
/**
* A Fishing Game for 2 Players.
* @author Jesse, Tynan, Jessica
* @since 12/11/22
*/

public class FishingGame {
   /** Array List of Fish in pond. */
   private static  ArrayList<FishableI_a> lokoia = new ArrayList<>();
    /** Player 1's sack of fish. */
   private static  ArrayList<FishableI_a> sack1 = new ArrayList<>();
     /** Player 2's sack of fish. */
   private static  ArrayList<FishableI_a> sack2 = new ArrayList<>();
    /** Amount of fish in pond. */
   static final int POND_CAPACITY = 40;

    /** main method.
   * @param args not used.
   */
 
   public static void main(String[] args) {
      Scanner userIn = new Scanner(System.in);
      String uIn = "";
      boolean ready = false;
      
      System.out.println("************ Lets Go Fishing: A Game for Two Players ************");
      System.out.println("\n\t\t\t\t\t\t\tPress 1 To Start");
      
      while (!ready) {
         uIn = userIn.nextLine(); 
         uIn = uIn.trim();
         if (uIn.equals("1")) {
            System.out.println("Here We Go!");
            lokoia = FishingDriver.fillPond();
            FishingDriver.growFish(lokoia, 24);
            lawaia(lokoia);
            ready = true;
         }
         
      } //End while
    
   } //End main

 
   /**
   * Simulate fishing/lawai'a.
   * @param fishPond arrayList of fish to be caught
   */
   public static void lawaia(ArrayList<FishableI_a> fishPond) {
      Random randGen = new Random();  //RNG
      FishableI_a ia;   //Storing Hooked Fish
      FishableI_a release; //Storing fish to be released
      int chosenFish = 0; 
      int turnCount = 0;  //Keeping track of reeling
      int checkSize = 0;   //For removing
      boolean endLoop = false; //For releasing
      boolean chooseKeep = false;   //For choosing whether to keep or not
      boolean isCaught = false;  //Whether hooked or not 
      boolean isLegal = false;   //Whether legal or not
      int removal = 0;  //For removing
      String choice = "";  //User input
      String yesNo = "y";
      Scanner userIn = new Scanner(System.in);  //Scanner
      
      //12 months loop
      for (int i = 0; i < 12; i++) {       
         System.out.println("Start of month " + (i + 1) + ".");
         for (int p = 0; p < 2; p++) {
            turnCount = 0;
            if (p == 0) {
               System.out.println("\nPlayer 1, it is your turn!");
            } else {
               System.out.println("\nPlayer 2, it is your turn!\n");
            }  
            while (turnCount < 3) {
               System.out.println("\tPlease Choose:");
               System.out.println("\t1. Cast out for a fish.");
               System.out.println("\t2. View sack of fish.");
               System.out.println("\t3. Throw back a fish in the sack.");     
               choice = userIn.nextLine();
               choice = choice.trim();
               switch(choice) {
                  case "1": //Cast out
                     chooseKeep = false;
                     chosenFish = randGen.nextInt(POND_CAPACITY);      
                     try {
                        ia = fishPond.get(chosenFish);
                        System.out.println("You have hooked a fish!");
                              //Randomly caught or not
                        isCaught = randGen.nextBoolean();
                        if (isCaught) {
                           System.out.println("You have caught a fish!\n");
                           System.out.println(ia);
                           while (!chooseKeep) {
                              System.out.println("\nWould you like to keep this fish Y/N?");
                              yesNo = userIn.nextLine();
                              yesNo = yesNo.trim();
                              if (yesNo.equalsIgnoreCase("y")) { //Keep fish
                                 fishPond.remove(chosenFish);
                                 System.out.println("You have kept the fish.");
                                 chooseKeep = true;
                                 if (ia.isLegalSize()) {
                                    System.out.println("Your fish is legal and added to stack.\n");
                                          //Add sacks
                                    if (p == 0) {
                                       sack1.add(ia);
                                       Collections.sort(sack1, Collections.reverseOrder());
                                    } else {
                                       sack2.add(ia);
                                       Collections.sort(sack2, Collections.reverseOrder());
                                    }
                                 } else { 
                                    System.out.println("You kept an illegal fish!");
                                    System.out.println("Tickted and fishes were confiscated!\n");
                                          //Empty sacks
                                    if (p == 0) {
                                       sack1.clear();
                                    } else {
                                       sack2.clear();
                                    }
                                 }                     
                              } else if (yesNo.equalsIgnoreCase("n")) { //Release fish
                                 chooseKeep = true;
                              } else {
                                 System.out.println("You did not enter \"Y\" or \"N\". Try again.");
                              }
                           }
                        } else {
                           System.out.println("Your fish got away!\n");  
                        }    
                     } catch (IndexOutOfBoundsException ie) { //Fish has been removed already
                        System.out.println("You didn't hook anything.");
                     }
                     turnCount++;
                     break;
                  case "2": //View sack
                     if (p == 0) {
                        printSack(sack1);
                     } else {
                        printSack(sack2);
                     }
                     break;
                  case "3": //Throw back
                     endLoop = false;
                     if (p == 0) {
                        checkSize = sack1.size();
                     } else {
                        checkSize = sack2.size();
                     }
                     if (checkSize > 0) {
                        System.out.println("Here are your fish: ");
                        if (p == 0) {
                           printSack(sack1);
                        } else {
                           printSack(sack2);
                        }
                        System.out.println("Which fish would you like to remove?");
                        while (!endLoop) {
                           choice = userIn.nextLine();
                           choice = choice.trim();
                           try {
                              removal = Integer.parseInt(choice);
                              if (p == 0) {
                                 release = sack1.get(removal - 1);
                                 sack1.remove(removal - 1);
                                 System.out.println("The fish has been removed.");
                              } else {
                                 release = sack2.get(removal - 1);
                                 sack2.remove(removal - 1);
                                 System.out.println("The fish has been removed.");
                              }
                              fishPond.add(release);
                              endLoop = true;
                           } //End try
                           catch (NumberFormatException nfe) {
                              System.out.println("That was not a number. Try again!");
                           } catch (IndexOutOfBoundsException ie) {
                              System.out.println("That fish is not in your sack!");
                           } //End catch
                        } //End while
                     } //End of if (checkSize).
                     else {
                        System.out.println("There is no fish in the sack.");
                     }
                     break;
                  default: //not a valid menu entry
                     System.out.println("\n****Invalid menu choice.****");
                     System.out.println("Please choose 1, 2, or 3.\n");  
                     break;
               }
            } //End while(turnCount)
         }
         //Lets fish grow 
         FishingDriver.growFish(fishPond, 1);
         System.out.println("\n\nEnd of month " + (i + 1) + ".");
      } //End i for loop
      awards(sack1, sack2);   
   } //Close lawaia method.


  /**
  * For printing.
  * @param fishPond The ArrayList to be printed
  */
   static void printSack(ArrayList<FishableI_a> fishPond) {
      FishableI_a temp;
      if (fishPond.size() == 0) {
         System.out.println("Your sack is empty.");
      } else {
         
         for (int i = 0; i < fishPond.size(); i++) {
            temp = fishPond.get(i);
            System.out.println((i + 1) + ":" + "\t\t" + temp.getName() + "\t\t" + temp.getLength());
         }
      }
   
   } //End of printing.
   
   
   /**
   * Calculating the Winner.
   * @param fishPond Player 1's fish
   * @param fishPond2 Player 2's fish
   */
   static void awards(ArrayList<FishableI_a> fishPond, ArrayList<FishableI_a> fishPond2) {
      FishableI_a temp;
      double result1 = 0;
      double result2 = 0;
   
      for (int i = 0; i < 3; i++) {
         try {
            temp = fishPond.get(i);
            result1 = result1 + temp.getLength();
         } catch (IndexOutOfBoundsException ie) {
            System.out.println("Fish is not in the sack.");
         } 
      }
   
      for (int i = 0; i < 3; i++) {
         try {
            temp = fishPond2.get(i);
            result2 = result2 + temp.getLength();
         } catch (IndexOutOfBoundsException ie) {
            System.out.println("Fish is not in the sack.");
         } 
      }
   
      if (result1 > result2) {
         System.out.println("Player 1 Wins!");
      }   else if (result2 > result1) {
         System.out.println("Player 2 Wins!");
      }
      else {
         System.out.println("It is a Tie!");
      }
   
   } //End of awards


} //Close FishingGame.