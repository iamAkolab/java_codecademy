// Build A Droid
// We’ve set up a robot workshop to build some droids.

public class Droid {
    int batteryLevel;
    String name;

  public static void main(String[] args) {
    Droid codey = new Droid("Codey");
    System.out.println(codey);

  }

  public Droid(String droidName) {
    name = droidName;
    batteryLevel = 100;
  }

  public void energyReport() {
    System.out.println(batteryLevel);
  }

  public int energyTransfer() {
    return batteryLevel;
  }

  public String toString() {
    return  "Hello, Im the droid:" + name;
  }
}
