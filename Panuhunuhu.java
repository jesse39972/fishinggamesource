import java.util.Random;
/**
* Panuhunuhu object class.
* This is the second phase of Uhu life
* It is male or female
* The size is 4-12 inches
* @author Jesse Lee Navas-Huang
* @version 1.0
* @since 10/3/22
*/
public class Panuhunuhu extends Ohua {
   
   // Constant instance variables, only add if changed from superclass

   /** constant maximum length for this Ia. 
   * Private because not inherited.
   */
   private static final double PANUHUNUHUMAXLENGTH = 12.0;
   /** constant minimum length for this Ia. 
   * Private because not inherited.
   */
   private static final double PANUHUNUHUMINLENGTH = 4.0;
   
   
 
   /**
   * Constructor for making Panuhunuhu with random length 
   * uses I_a superclass constructor
   * uses dietItems array from super class
   * randomly sets length within allowed values after calling constructor.
   * This is the second phase of the species and is always male.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   * Must send minLength  through as a temp length or throws exception
   */
   public Panuhunuhu() {
      super("Panuhunuhu", "Scaridae", PANUHUNUHUMAXLENGTH, PANUHUNUHUMINLENGTH, 
         PANUHUNUHUMINLENGTH, (PANUHUNUHUMINLENGTH * 2), dietItems,  "gray", 
         "gray or reddish gray", "male or female"); 
      //method is in superclass but will use max, min length set above
      this.initLength();
      this.changeSex();  
      this.setColor(bodyColor, finColor); 
      
   }
   
   /**
   * Constructor for making Panuhunuhu with a given length 
   * uses I_a superclass constructor
   * uses dietItems array from super class
   * randomly sets length within allowed values after calling constructor.
   * This is the second phase of the species and is always male.
   * @param length The fish's length
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   public Panuhunuhu(double length) {
      super("Panuhunuhu", "Scaridae", PANUHUNUHUMAXLENGTH, PANUHUNUHUMINLENGTH, 
         length, (length * 2), dietItems,  "gray", "gray or reddish gray", "male or female"); 
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
   *@param weight This fish's weight.
   *@param diet The diet preference of this fish type.
   *@param bodyColor The body color of this fish.
   *@param finColor The fin color of this fish.
   *@param sex The sex of this fish.
   *@throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   protected Panuhunuhu(String name, String scientificName, double maxLength, double minLength, 
         double length, double weight, String[] diet, String bodyColor, String finColor, 
         String sex) {
       
      super(name, scientificName, maxLength, minLength, 
         length, weight, diet, bodyColor, finColor, sex);
   }
   
   /** 
   * Returns new Uhu version of this fish.
   * When a Panuhunuhu reaches maxLength, it should levelUp to a Uhu of  min size
   * min size of next size is always max size of this one
   * @return a new Uhu
   */
   public Uhu levelUp() {
   
      return new Uhu(this.maxLength);
   
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
   }
   
   /**
   * returns the reproductive mode of this sex changing fish.
   * @return REPRODUCTIVEMODE The fish's reproductive mode
   */
   public String getReproductiveMode() {
      return REPRODUCTIVEMODE;
   }



}

