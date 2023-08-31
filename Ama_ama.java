import java.util.Random;
/**
* 'Ama'ama object class.
* This is the third phase of 'Ama'ama life.
* It is male or female
* The size is 8 - 12 inches
* @author Tynan Mathieu
* @since 10/3/2022
*/
public class Ama_ama extends Kahaha {
   
   // Constant instance variables, only add if changed from superclass

   /** constant maximum length for this Ia. 
   * Private because not inherited.
   */
   private static final double amaamaMaxLength = 12.0;
   /** constant minimum length for this Ia. 
   * Private because not inherited.
   */
   private static final double amaamaMinLength = 8.0;
   
   /**
   * Constructor for making 'Ama'ama with random length 
   * uses I_a superclass constructor
   * uses dietItems array from super class
   * randomly sets length within allowed values after calling constructor.
   * This is the third phase of the species and is male or female.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   * Must send minLength through as a temp length or throws exception
   */
   public Ama_ama() {
      super("'Ama'ama", amaamaMaxLength, amaamaMinLength,
         amaamaMinLength, (amaamaMinLength * 2), dietItems, "silver", "silver", "male or female");
      //method is in superclass but will use max, min length set above
      this.initLength();
      this.changeSex();
   }
   
   /**
   * Constructor for making 'Ama'ama with a given lengt.
   * uses I_a superclass constructor
   * uses dietItems array from super class
   * This is the third phase of the species and is male or female.
   * @param length The fish's size.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   public Ama_ama(double length) {
      super("'Ama'ama", amaamaMaxLength, amaamaMinLength,
         length, (length * 2), dietItems, "silver", "silver", "male or female");
      this.changeSex();
   }
   
   /**
   * Constructor for making 'Ama'ama with a given length and sex.
   * This is the third phase of the species and inherits the sex.
   * @param length The fish's size.
   * @param sex The sex of the fish.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   public Ama_ama(double length, String sex) {
      super("'Ama'ama", amaamaMaxLength, amaamaMinLength,
         length, (length * 2), dietItems, "silver", "silver", sex);
   }
   
   /** 
   * Protected constructor for subclasses.
   * This allows subclass specific variables  
   * to pass through to I'a superclass constructor.
   *
   * In this species, the English and scientific names
   * remain the same throughout so not needed to pass from subclasses.
   *
   * @param name This fish type's Hawaiian name.
   * @param maxLength The maximum length this name applies.
   * @param minLength The minimum length this name applies.
   * @param length This fish's size.
   * @param weight This fish's weight.
   * @param diet The diet preference of this fish type.
   * @param bodyColor The body color of this fish.
   * @param finColor The fin color of this fish.
   * @param sex The sex of this fish.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   protected Ama_ama(String name, double maxLength, double minLength, double length, 
         double weight, String[] diet, String bodyColor, String finColor, String sex) {
      
      super(name, maxLength, minLength, 
         length, weight, diet, bodyColor, finColor, sex);
   }
   
   /** returns new 'Anae version of this fish.
   * When a Ama'ama reaches maxLength, it should levelUp to a 'Anae of min size
   * min size of next size is always max size of this one
   * @return a new Anae
   */   
   public Anae levelUp() {
      return new Anae(this.maxLength);
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
   
    //============= Required by Fishable ==================
    /**
    * Returns the methods of catching this fish.
    * @return an array of fishing methods.
    */
   public String[] getCatchMethods() {
      String[] validCatchMeth = {"net", "pole"};
      return validCatchMeth;  
   }
}