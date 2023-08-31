import java.util.Random;
/**
* 'Anae object class.
* This is the fourth and final phase of Ama'ama life
* It is male or female
* The size is 12 inches and up
* @author Tynan Mathieu
* @since 10/3/2022
*/
public class Anae extends Ama_ama {
   
   // Constant instance variables, only add if changed from superclass

   /** constant maximum length for this Ia.
   * This is the state record 
   * Private because not inherited.
   */
   private static final double anaeMaxLength = 27.0;
   /** constant minimum length for this Ia. 
   * Private because not inherited.
   */
   private static final double anaeMinLength = 12.0;
   
   /**
   * Constructor for making 'Anae with random length 
   * uses I_a superclass constructor
   * uses dietItems array from super class
   * randomly sets length within allowed values after calling constructor.
   * This is the fourth phase of the species and is male or female
   * @throws FishSizeException if length is less than minLength
   * Must send minLength through as a temp length or throws exception
   */
   public Anae() {
      super("'Anae", anaeMaxLength, anaeMinLength,
         anaeMinLength, (anaeMinLength * 2), dietItems, "silver", "silver", "male or female");
      //method is in superclass but will use max, min length set above
      this.initLength();
      this.changeSex();
   }
   
   /**
   * Constructor for making 'Anae with a given length 
   * uses I_a superclass constructor
   * uses dietItems array from super class
   * randomly sets length within allowed values after calling constructor.
   * This is the fourth phase of the species and is male or female.
   * @param length The fish's size.
   * @throws FishSizeException if length is less than minLength
   */
   public Anae(double length) {
      super("'Anae", anaeMaxLength, anaeMinLength,
         length, (length * 2), dietItems, "silver", "silver", "male or female");
      this.changeSex();
   }
   
   /**
   * Constructor for making 'Anae with a given length and sex.
   * This is the fourth phase of the species and inherits the sex.
   * @param length The fish's size.
   * @param sex The sex of the fish.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   public Anae(double length, String sex) {
      super("'Anae", anaeMaxLength, anaeMinLength,
         length, (length * 2), dietItems, "silver", "silver", sex);
   }
   
   //'Anae do not change to another type, so we overwrite the grow method from the base class.
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
   * 'Anae don't level up, this is the terminal type.
   * method is required but shouldn't do anything
   * @return the same fish
   */
   public Anae levelUp() {
      return this;
   }
   
    //============== required by SexChangable interface ===============================
   
   /** 
   * changes this 'Ama'ama's sex
   * 'Ama'ama cannot change sex within their size group. Can only change at levelUp
   */
   public void changeSex() {
      Random ran = new Random();
      int flip = ran.nextInt(2);
      
      if (flip == 0) {
         this.sex = "male";
      } else {
         this.sex = "female";
      }
   }
   
   /**
   * returns the reproductive mode of this sex changing fish.
   * @return the fish's reproductive mode
   */
   public String getReproductiveMode() {
      return reproductiveMode;
   }

}
