# Loop Refresher

Loops are a useful tool to repeat certain actions so that the action is only defined once. They can do things like solving for a number, combining multiple strings, populating an array with several numbers, and much more.

For example, say we are ordering at a restaurant that sells burritos, and we have $4267 in our wallet. One vegetarian burrito costs $7 (including tax). We could whip up a script to figure out how many we can order:

```
public class BurritoCalculator{
    public static int getBurritoQuantity(int burritoCost) {
        int budget = 4267;

        int numBurritos = 0;
        while (budget >= burritoCost) {
          numBurritos++;
          budget -= burritoCost;
        }
        
        return numBurritos;
    }
    
     public static void main(String []args){
        System.out.println(getBurritoQuantity(7));
     }
}
```

Here, we start with 0 burritos. We can use the numBurritos variable to keep track of the total number of burritos we can purchase. Every time we add another burrito to purchase, we will want to subtract the cost of the burrito from our total budget, and do this until we don’t have any more left for a burrito.

Instead of writing these two lines over and over, we can use a while loop to repeat these steps for us. These steps will continue to run as long as the condition remains true, so we want the condition to evaluate to false when we no longer have any money left for a burrito.

## 1. Leftover Funds
Now you might wonder: “This means I will have money left over, but I want to use all my money.” How can we tweak .hasLeftoverFunds() to give us that information?

```
public class BurritoCalculator {
  public static boolean hasLeftoverFunds(int burritoCost) {
    int budget = 100;
    int numBurritos = 0;
    
    while (budget >= burritoCost) {
      numBurritos++;
      budget -= burritoCost;
    }

    System.out.println("Remaining budget: " + budget);
  }
    
  public static void main(String []args) {
    // Below are some sample values you can use to run your code.
    // When you're ready, uncomment the following lines one at a time to test each case. Before running each test case, think about what value you expect to see printed out.
    System.out.println(hasLeftoverFunds(7));
    System.out.println(hasLeftoverFunds(10));

    return budget > 0;
  }
}
```
