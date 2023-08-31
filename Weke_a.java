import java.util.Random;
/** 
* Assignment 3 weke_a family. 
* @author Masaya Oda
* @since 9/27/22
**/

public class Weke_a extends Oama implements ColorChangeable {

   // Constant instance variables, only add if changed from superclass

   /** constant maximum length for this Ia.
   * This is the state record 
   * Private because not inherited.
   */
   private static final double waMaxLength = 30.0;
   
   /** constant minimum length for this Ia. 
   * Private because not inherited.
   */
   private static final double waMinLength = 7.0;
   
   /**
   * Constructor for making Weke'a with random length.
   * Uses Oama superclass constructor.
   * Uses dietItems array from super class.
   * Randomly sets length within allowed values after calling constructor.
   * Randomize sex.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength.
   * Must send minLength  through as a temp length or throws exception.
   */
   public Weke_a() {
      super("Weke'a", "Square-spot Goatfish", "Mulloidichthys flavolineatus", 
          waMaxLength, waMinLength, 
         waMinLength, (waMinLength * 2), dietItems,  "white with yellow stripe and black spot", 
             "white", "male or female"); 
      //method is in superclass but will use max, min length set above
      this.initLength();
      this.initSex();
        
   }
   
   /**
   * Constructor for making Weke'a with a given length.
   * Uses Oama superclass constructor.
   * Uses dietItems array from super class.
   * Randomly sex and sets length within allowed values after calling constructor.
   * @param length fish's length
   * @throws FishSizeException if length exceeds maxLength or is less than minLength.
   */
   public Weke_a(double length) {
      super("Weke'a", "Square-spot Goatfish", "Mulloidichthys flavolineatus", 
          waMaxLength, waMinLength, 
         length, (length * 2), dietItems,  "white with yellow stripe and black spot", 
             "white", "male or female"); 
      
      this.initSex();
         
   }
   
   //Weke_a do not change to another type, so we overwrite the grow method from the base class.
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
    
   /**
   * Weke_a don't level up, this is the terminal type.
   * method is required but shouldn't do anything
   * @return same fish 
   */
   public Weke_a levelUp() {
      return this;
   }
   
   //==================== required by ColorChangeable interface =============
  
   /** Changes the Weke'as Color.
   * @param bodyColor changes fish's body color
   * @param finColor Changes fish's fin color
   */
   public void setColor(String bodyColor, String finColor) {
      this.bodyColor = bodyColor;
      this.finColor = finColor;
   }


}