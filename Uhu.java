import java.util.Random;
/**
* Uhu object class.
* This is the third and final phase of Uhu life
* It can be male, female, or supermale
* The size is 12 inches and up
* @author Jesse Lee Navas-Huang
* @version 1.0
* @since 10/3/22
*/
public class Uhu extends Panuhunuhu {
   
   // Constant instance variables, only add if changed from superclass
   /** constant maximum length for this Ia.
   * This is the state record 
   * Private because not inherited.
   */
   private static final double UHUMAXLENGTH = 30.0;
   /** constant minimum length for this Ia. 
   * Private because not inherited.
   */
   private static final double UHUMINLENGTH = 12.0;
    
   
 
   /**
   * Constructor for making Uhu with random length 
   * uses I_a superclass constructor
   * uses dietItems array from super class
   * randomly sets length within allowed values after calling constructor.
   * This is the fourth phase of the species and always female
   * @throws FishSizeException if length is less than minLength
   * Must send minLength through as a temp length or throws exception
   */
   public Uhu() {
      super("Uhu", "Scarus psittacus", UHUMAXLENGTH, UHUMINLENGTH, 
         UHUMINLENGTH, (UHUMINLENGTH * 2), dietItems,  "gray or blue-green", 
         "gray or reddish gray or blue", "male or female or supermale"); 
      //method is in superclass but will use max, min length set above
      this.initLength(); 
      this.changeSex(); 
      this.setColor(bodyColor, finColor); 
      
   }
   
   /**
   * Constructor for making Uhu with a given length 
   * uses I_a superclass constructor
   * uses dietItems array from super class
   * randomly sets length within allowed values after calling constructor.
   * This is the fourth phase of the species and always female.
   * @param length This fish's length
   * @throws FishSizeException if length is less than minLength
   */
   public Uhu(double length) {
      super("Uhu", "Scarus psittacus", UHUMAXLENGTH, UHUMINLENGTH, 
         length, (length * 2), dietItems,  "gray or blue-green", 
         "gray or reddish gray or blue", "male or female or supermale");
      this.changeSex(); 
      this.setColor(bodyColor, finColor); 
    
   
   }
   /** 
   * Protected constructor for subclasses.
   * This allows subclass specific variables  
   * to pass through to I'a superclass constructor.
   *
   * In this species, the English name
   * remain the same throughout so not needed to pass from subclasses.
   *
   *@param name This fish type's Hawaiian name.
   *@param scientificName This fish's Scientific name. 
   *@param maxLength The maximum length this name applies.
   *@param minLength The minimum length this name applies.
   *@param length This fish's size.
   *@param weight This fish's weight
   *@param diet The diet preference of this fish type.
   *@param bodyColor The body color of this fish.
   *@param finColor The fin color of this fish.
   *@param sex The sex of this fish.
   *@throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   protected Uhu(String name, String scientificName, double maxLength, double minLength, 
         double length, double weight, String[] diet, String bodyColor, String finColor, 
         String sex) {
       
      super(name, scientificName, maxLength, minLength, 
         length, weight, diet, bodyColor, finColor, sex);
   }

   
   //Uhu do not change to another type, so we overwrite the grow method from the base class.
   /**
   * Should be used by eat method to increase length of fish. 
   * Should determine a new length and internally call setLength
   * Does not throw FishSizeException because this is the final type of this fish
   */
   protected void grow() {
      Random ran = new Random();
      double lengthIncrease = ran.nextDouble() * growthRate;
   
      //calculate a new length by adding a random value between 0 and growthRate
      this.length = this.length + lengthIncrease;
      this.weight = this.length * 2;
   }
    
   /** returns the same fish
   * Uhu don't level up, this is the terminal type.
   * method is required but shouldn't do anything
   * @return A new Uhu
   */
   public Uhu levelUp() {
   
      return new Uhu(this.length);
   
   }
    //============== required by SexChangable interface ===============================
   
   /** 
   * changes this Ohua's sex
   * Ohua cannot change sex within their size group. Can only change at levelUp
   */
   public void changeSex() {
      Random ran = new Random();
      int flip = ran.nextInt(2);
      
      if (flip == 0) {
         this.sex = "male";
      } else {
         this.sex = "female";
      }
      if (this.sex == "female" && this.length > 20) {
         this.sex = "supermale";
      }
   }
   
   
   /**
   * returns the reproductive mode of this sex changing fish.
   * @return REPRODUCTIVEMODE The fish's reproductive mode
   */
   public String getReproductiveMode() {
      return REPRODUCTIVEMODE;
   }
  
  //============== required by ColorChangeable interface =============================== 
   /**
   * Changes this Ohua's color.
   * @param bodyColor The fish's body color
   * @param finColor The fish's fin color
   */
   public void setColor(String bodyColor, String finColor) {
      Random ran = new Random();
      int flip = ran.nextInt(2);
      
      if (this.sex == "supermale") {
         this.bodyColor = "blue-green";
         this.finColor = "blue";
      } else {
         this.bodyColor = "gray";
         if (flip == 0) {
            this.finColor = "gray";
         } else {
            this.finColor = "reddish gray";
         }
      }
   }

   
       //============= Required by Fishable ==================
   /**
    * Returns the methods of catching this fish.
    * @return an array of fishing methods.
    */
   public String[] getCatchMethods() {
      String[] validCatchMeth = {"throw net", "spear", "pole"};
      return validCatchMeth;
   
   }
 
   
   
}
