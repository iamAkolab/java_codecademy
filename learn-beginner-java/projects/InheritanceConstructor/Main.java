public class Main{
  public static void main(String[] args) {
      Pho phoChay = new Pho();
      System.out.println(phoChay.shape);

    Ramen yasaiRamen = new Ramen();
    //System.out.println(yasaiRamen.ingredients);
    System.out.println(yasaiRamen.isTasty());  

    Spaetzle kaesespaetzle = new Spaetzle();
    kaesespaetzle.cook();
  }
}
