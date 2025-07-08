class Spaetzle extends Noodle {
  Spaetzle() {
    super(3.0, 1.5, "irregular", "eggs, flour, salt");
    this.texture = "lumpy and liquid";     
  }
    // Add the new cook() method below:  
  @Override
  public void cook(){
    System.out.println("Grinding or scraping the dough into the pot of boiling water.");
    texture = "cooked";
    System.out.println("Boiling");
  }
}
