import java.util.Random;
/**
* Ohua fish object class.
* Smallest type version of Uhu fish
* Parent class to Panuhunuhu
* @author Jesse Lee Navas-Huang
* @version 1.0
* @since 10/3/22
*/
public class Ohua extends FishableI_a implements SexChangeable, ColorChangeable {

   // Constants for this fish.
   // protected will be inherited by subclasses unless replaced
   /** constant reproductive mode for this Ia. */
   protected static final String REPRODUCTIVEMODE = "Protogynous";
   /** constant array of what the fish eats. */
   protected static final String[] dietItems = {"algae"};

   
   /** constant maximum length for this Ia. 
   * Private because not inherited.
   */
   private static final double OHUAMAXLENGTH = 4.0;
   /** constant minimum length for this Ia. 
   * Private because not inherited.
   */
   private static final double OHUAMINLENGTH = 0.01;
   

   
   /**
   * Constructor for making Ohua
   * uses I_a superclass constructor
   * randomly sets length within allowed values after calling constructor.
   * This is the juvenile of the species and has no sex determination.
   */
   public Ohua() {
      super("'Ohua", "Parrotfish", "Scaridae", OHUAMAXLENGTH, OHUAMINLENGTH, 0.1, 2.0, 
         dietItems, "Gray", "Gray or Reddish Gray", "none");   
      //set random allowed length after
      this.initLength();
      this.setColor(bodyColor, finColor);
      
      
   }
   
   /**
   * Constructor for making Ohua with a specific length.
   * uses I_a superclass constructor
   *@param length The Ohua's length.
   * @throws FishSizeException if length is larger than maxLength or smaller than minLength.
   * This is the juvenile of the species and has no sex determination.
   */
   public Ohua(double length) {
      super("'Ohua", "Parrotfish", "Scaridae", OHUAMAXLENGTH, OHUAMINLENGTH, length, (length * 2), 
         dietItems, "Gray", "Gray or Reddish Gray", "none");
      this.setColor(bodyColor, finColor);       
   }

   
   /** 
   * Protected constructor for subclasses.
   * This allows subclass specific variables  
   * to pass through to I'a superclass constructor.
   *
   * In this species, the English name
   * remains the same throughout so not needed to pass from subclasses.
   *
   *@param name This fish type's Hawaiian name.
   *@param scientificName This fish's Scientific name. 
   *@param maxLength The maximum length this name applies.
   *@param minLength The minimum length this name applies.
   *@param length This fish's size.
   *@param weight This fish's weight.
   *@param diet The diet preference of this fish type.
   *@param bodyColor The body color of this fish.
   *@param finColor The fin color of this fish.
   *@param sex The sex of this fish.
   *@throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   protected Ohua(String name, String scientificName, double maxLength, double minLength, 
         double length, double weight, String[] diet, String bodyColor, String finColor, 
         String sex) {
       
      super(name, "Parrotfish", scientificName, maxLength, minLength, 
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
   * @param newLength This fish's new length
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
   * @param newWeight The fish's new weight.
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
      //loop through what fish eats and determine if edible
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
   
   /** returns new Panuhunuhu version of this fish.
   * When a ohua reaches maxLength, it should levelUp to a Panuhunuhu of  min length
   * min size of next size is always max length of this one
   * @return a new Panuhunuhu
   */
   public Panuhunuhu levelUp() {
   
      return new Panuhunuhu(this.maxLength);
   
   }
   
      
   //============== required by SexChangable interface ===============================
   
   /** 
   * changes this Ohua's sex
   * Ohua cannot change sex within their size group. Can only change at levelUp
   */
   public void changeSex() {
      throw new UnsupportedOperationException("Ohua cannot change sex.");
   }
   
   /**
   * returns the reproductive mode of this sex changing fish.
   * @return REPRODUCTIVEMODE
   */
   public String getReproductiveMode() {
      return REPRODUCTIVEMODE;
   }
   
   //============== required by ColorChangeable interface =============================== 
   /**
   * Changes this Ohua's color.
   * @param bodyColor This fish's body color
   * @param finColor This fish's fin color
   */
   public void setColor(String bodyColor, String finColor) {
      Random ran = new Random();
      int flip = ran.nextInt(2);
      
      if (flip == 0) {
         this.finColor = "gray";
      } else {
         this.finColor = "reddish gray";
      }
   }
  
  
      //============= Required by Fishable ==================
 /** 
 * determines if the fish is legal to keep due to length.
 * Baby size handles this for all Moi sizes.
 * Moi must be 11 inches or more to take. 
 * @return true If this fish is legal size to keep.
 */
   public boolean isLegalSize() {
      if (this.length >= 12) {
         return true;
      } 
      return false;
   }
	       
    /**
    * determines if Moi can be used as a baitfish.
    * @return false this fish type is not used for bait for other fishing.
    */
   public boolean isBait() {
      return false;
   }
	       
    /**
    * determines if Moi can be used to eat.
    * @return true if is legal size, false otherwise.
    */
   public boolean isGamefish() {
      if (this.isLegalSize()) {
         return true;
      }
      return false;
   }
   
   /**
   * Moi season is Sept - May. No catching Jun - Aug
   * @param month the current month of the year.
   * @return true if fish is okay to take, false otherwise.
   */
   public boolean isInSeason(String month) {
      return true;
   }
   
    /**
    * Returns the methods of catching this fish.
    * @return an array of fishing methods.
    */
   public String[] getCatchMethods() {
      return null;
   }
   
   /**
   * Returns the limit on number of Moi you're allowed to catch.
   * @return int representing the maximum number of Moi you are allowed.
   */
   public int getBagLimit() {
      return Integer.MAX_VALUE;
   }   


}
