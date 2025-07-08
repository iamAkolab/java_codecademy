public class Main{
  public static void main(String[] args) {
      Pho phoChay = new Pho();
      System.out.println(phoChay.shape);

    Ramen yasaiRamen = new Ramen();
    //System.out.println(yasaiRamen.ingredients);
    System.out.println(yasaiRamen.isTasty());  

    Spaetzle kaesespaetzle = new Spaetzle();
    kaesespaetzle.cook();

    Noodle spaghetti, ramen, pho;
    
    spaghetti = new Spaghetti();
    ramen = new Ramen();
    pho = new Pho();
        
    Noodle[] allTheNoodles = {spaghetti, ramen, pho};
    for(Noodle noodle: allTheNoodles){
      System.out.println(noodle.getCookPrep());
    }   
  }
}
