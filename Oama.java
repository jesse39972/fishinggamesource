import java.util.Random;
/** 
* Assignment 3 Parent class of Weke'a and Weke'Ula. 
* Masaya Oda's code from Group 3.
* @author Jessica Lee
* @since 9/27/22
*/

public class Oama extends FishableI_a implements ColorChangeable {
   
   // Constants for this fish.
   // protected will be inherited by subclasses unless replaced
   /** constant array of what the fish eats. */
   protected static final String[] dietItems = {"worms", "mollusks", "invertebrates"};
   
   /** constant maximum length for this Ia. 
   * Private because not inherited.
   */
   private static final double oamaMaxLength = 7.0;
   
   /** constant minimum length for this Ia. 
   * Private because not inherited.
   */
   private static final double oamaMinLength = 0.01;

   /**
   * Constructor for making Oama
   * uses I_a superclass constructor
   * randomly sets length within allowed values after calling constructor.
   * This is the juvenile of the species and has no sex determination.
   */
   public Oama() {
      super("'Oama", "Goatfish", "Mulloidichthys", oamaMaxLength, oamaMinLength, 0.1, 2.0, 
         dietItems, "white with yellow stripe", "yellow or white", "none");   
         
      //set random allowed length after
      this.initLength();
      
   }
   
   /**
   * Constructor for making Oama with a specific length.
   * uses I_a superclass constructor.
   * @param length this fish's length.
   * @throws FishSizeException if length is larger than maxLength or smaller than minLength.
   * This is the juvinile of the species and has no sex determination.
   */
   public Oama(double length) { 
      super("'Oama", "Goatfish", "Mulloidichthys", oamaMaxLength, 
         oamaMinLength, length, (length * 2), 
         dietItems, "white with yellow stripe", "yellow or white", "none"); 
   }
   
   /** 
   * Protected constructor for subclasses.
   * This allows subclass specific variables to pass through to I'a superclass constructor.
   * English and scientific names remain the same throughout
   * doesnt needed to pass from subclasses.
   * @param name This fish type's Hawaiian name.
   * @param englishName This fish type's English name.
   * @param scientificName This fish type's Scientific name. 
   * @param maxLength The maximum length this name applies.
   * @param minLength The minimum length this name applies.
   * @param length This fish's size.
   * @param diet The diet preference of this fish type.
   * @param bodyColor The body color of this fish.
   * @param finColor The fin color of this fish.
   * @param sex The sex of this fish.
   * @param weight The fish weight.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   protected Oama(String name, String englishName, String scientificName, 
       double maxLength, double minLength, 
         double length, double weight, String[] diet, String bodyColor, 
             String finColor, String sex) {
      
      super(name, englishName, scientificName, maxLength, minLength, 
         length, weight, diet, bodyColor, finColor, sex);  
   }
   
   /**
   * Sets the fish's initial length to between its max and min allowed values.
   */
   protected void initLength() {
      Random ran = new Random();
      this.length = this.minLength + (this.maxLength - this.minLength) * ran.nextDouble();
      this.weight = this.length * 2;
   }
   
   //++++++++++++++++ Superclass required implementations of abstract methods ++++++++++
   
   /**
   * Sets the fish's length to newLength.
   * @param newLength Fish's new length.
   * @throws FishSizeException if the new length is not within min-max length for type
   */
   public void setLength(double newLength) throws FishSizeException {  
      if (newLength >= this.minLength && newLength < this.maxLength) {
         this.length = newLength;
      } else {
         throw new FishSizeException(this.name + " must be within " + this.minLength 
               + " and " + this.maxLength + " inches long.");
      } 
   }
   
   /**
   * Sets the fish's weight to newWeight.
   * @param newWeight Fish's new weight.
   */
   public void setWeight(double newWeight) {
      this.weight = newWeight;
   }
   
   /**
   * Models eating, should call grow to cause length increases.
   * @param food A String with a potential food name
   * @throws FishFoodException if the food is not the fish's diet
   */
   public void eat(String food) {
      boolean isEdible = false;
      
      for (int i = 0; i < dietItems.length; i++) {
         if (food.equals(dietItems[i])) {
            isEdible = true;
            
         }
      }
      if (isEdible) {
         this.grow();
      } else {
         throw new FishFoodException(this.name + " does not eat " + food);
      }
   }
   
   /**
   * Should be used by eat method to increase length of fish. 
   * Should determine a new length and internally call setLength
   * @throws FishSizeException if the new length is not within min-max length for type
   *  indicates levelUp needs to be called.
   */
   protected void grow() {
      Random ran = new Random();
      double lengthIncrease = ran.nextDouble() * growthRate;
   
      //calculate a new length by adding a random value between 0 and growthRate
      double newLen = this.length + lengthIncrease;
   
      //check to see if this fish needs to level up
      if (newLen > this.maxLength) {
         throw new FishSizeException("This fish has outgrown its name, it must level up!");
      } else {
         this.length = newLen;
         this.weight = 2 * newLen; //rudimentary weight calc
      }
   }
   
   /** Returns new Weke_a or Weke_ula version of this fish.
   * When a 'Oama reaches maxLength, it should levelUp to a Weke'a or Weke'ula of min length.
   * Min size of next size is always max length of this one
   * @return a new Weke_a or Weke_ula 
   */
   public Oama levelUp() {
      Random randomGenerate = new Random();
      boolean number = randomGenerate.nextBoolean();
      
      if (number == false) {
         return new Weke_a(this.maxLength);
      
      } else {
         return new Weke_ula(this.maxLength);
      }
   }

   //==================== required by ColorChangeable interface =============

   /**
   * Changes the Oamas Color.
   * @param bodyColor changes fish's body color 
   * @param finColor changes fish's fin color
   */
   public void setColor(String bodyColor, String finColor) {
      this.bodyColor = bodyColor;
      this.finColor = finColor;
   }
   


    //============= Required by Fishable ==================
 /** 
 * There is no minimum legal size.
 * Can be taken at any size. 
 * @return true fish size.
 */
   public boolean isLegalSize() {
      return true;
   }

  /**
    * determines if Oama can be used as a baitfish.
    * @return true this fishcan be used as bait.
    */
   public boolean isBait() {
      return true;
   }

    /**
    * determines if Oama can be used to eat.
    * @return true is game fish.
    */
   public boolean isGamefish() {
      if (this.isLegalSize()) {
         return true;
      }
      return false;
   }

  /**
   * Oama season is always open.
   * @param month the current month of the year.
   * @return true since its always open.
   */
   public boolean isInSeason(String month) {
      month = month.toLowerCase();
      boolean isOk = true;
      
      switch(month) {
         case "january":
         case "february":
         case "march":
         case "april":
         case "may":
         case "june":
         case "july":
         case "august":
         case "septembner":
         case "october":
         case "november":
         case "december":
            isOk = false;
            break;
         default:
            isOk = true;
            break;
      }
      return isOk;
   }

    /**
    * Returns the methods of catching this fish.
    * @return an array of fishing methods.
    */
   public String[] getCatchMethods() {
      String[] validCatchMeth = {"net", "trap", "pole"};
      return validCatchMeth;
   }

   /**
   * Returns the limit on number of Moi you're allowed to catch.
   * @return int representing the maximum number of Moi you are allowed.
   */
   public int getBagLimit() {
      return 50;
   }   

   
   
} // End of Oama