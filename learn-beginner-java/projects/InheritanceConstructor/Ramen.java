public class Ramen extends Noodle {
  Ramen() {
    super(30.0, 0.3, "flat", "wheat flour");  
  }
  
  public boolean isTasty(){
    return false;
  }

    @Override
  public String getCookPrep() {
    return "Boil ramen for 5 minutes in broth, then add meat, mushrooms, egg, and vegetables.";
  }
}
