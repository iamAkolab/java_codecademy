# Code Challenge: Loops Involving Array Traversal

Loops are incredibly powerful when used together with arrays. If you need to do things like calculate a result or perform the same action on all elements of the array, you can use loops to prevent writing the same code for every element. For example, say my friend wrote the following grocery list for you:
```
String[] groceries = new String[] {"candy corn", "chocolate", "jelly beans", "cookies"};
```
However, their handwriting is difficult to read, and you can only read it if it’s written in capital letters. So we can use a loop to translate the grocery list into something you can read.
```
String[] groceries = new String[] {"candy corn", "chocolate", "jelly beans", "cookies"};

for(String item : groceries) {
  System.out.println(item.toUpperCase());
}
```
Here, we set up a for loop to iterate through each element in the array of groceries. Inside the loop, we create a new string of all capitalized letters and print it out.

## 1. Total Cost for Groceries
Your friend gave you a grocery list with a list of prices that correspond to each item in the grocery list. This is so you can make sure you have enough money to buy all the items.

Using a loop, update the getTotalCost() method to calculate the sum of all the groceryPrices and store the result in totalCost.
```
public class GroceryList {
  public static double getTotalCost(double[] groceryPrices) {
    double totalCost = 0.0;
    for (double itemCost : groceryPrices) {
        totalCost += itemCost;
        }
    return totalCost;
  }

  public static void main(String[] args) {
    // Below are some sample values you can use to run your code.
    // When you're ready, uncomment the following lines one at a time to test each case. Before running each test case, think about what value you expect to see printed out.
    double[] groceryPrices = {10.0, 12.3, 8.45, 2.43};
    // System.out.println(getTotalCost(groceryPrices));
    
    double[] unlikelyGroceryPrices = {100.23, -100.23, 0.0, -78.54};
    // System.out.println(getTotalCost(unlikelyGroceryPrices));
  }
}

```

## 2.Most Expensive Grocery Item
