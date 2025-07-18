import java.util.Scanner;

class ShopItems {
  int totalCount;

  // Nested class for non-vegan items
  class NonVegan {
  int iceCreamCount;
  int shakeCount;
  int totalCount;

  void increaseCount(int type, int count) {
    if (type == 1) iceCreamCount += count;
    else if (type == 2) shakeCount += count;
    totalCount += count;
    ShopItems.this.totalCount += count;
  }

  }

  class Vegan {
    int smoothieCount;
    int slushieCount;
    int totalCount;
    void increaseCount(int type, int count) {
    if (type == 3) smoothieCount += count;
    else if (type == 4) slushieCount += count;
    totalCount += count;
    ShopItems.this.totalCount += count;
    }
  }

}


public class IceCreamShop {

  public static void main(String[] args) {
    ShopItems shop = new ShopItems();
    ShopItems.NonVegan nonVegan = shop.new NonVegan();
    ShopItems.Vegan vegan = shop.new Vegan();

    int orderType = 0;
    Scanner input = new Scanner(System.in);
    int itemCount = 0;
    String itemName;

    System.out.println("Hello! Welcome to the ice cream shop. We are ready to take your order. For ice-cream type 1, for shakes type 2, for smoothies type 3, and for slushies type 4. If you are done ordering type 0.");
    
    try {
        orderType = input.nextInt();
      } catch (Exception e) {
        System.out.println("Please enter a valid number.");
        input.next(); // clear invalid input
        continue;
      }

    while(orderType != 0) {
      System.out.println("How many items of this item would you like to order?");
      itemCount = input.nextInt();
      if(orderType == 1){
        nonVegan.increaseCount(1, itemCount);
        itemName = "ice cream";
      } else if(orderType == 2) {
        nonVegan.increaseCount(2, itemCount);
        itemName = "shake";
      } else if(orderType == 3) {
        vegan.increaseCount(3, itemCount);
        itemName = "smoothie";
      } else if(orderType == 4) {
        vegan.increaseCount(4, itemCount);
        itemName = "slushie";
      } else {
        break;
      }

      System.out.println("Adding " + itemCount + " of the item "+ itemName + " to your order!");

      System.out.println("Type a number for the next item in your order (or 0 to finish):\n1: Ice cream\n2: Shake\n3: Smoothie\n4: Slushie");

      orderType = input.nextInt();

    }
    System.out.println("Thank you for your order! Today we have sold " + shop.totalCount + " orders of sweetness!");
    System.out.println(nonVegan.totalCount + " items have been ice cream and shakes.");
    System.out.println(vegan.totalCount + " items have been smoothies and slushies.");
  }
}
