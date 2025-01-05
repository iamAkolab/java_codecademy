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
Oh no! You spent most of your money on burritos, which puts you over budget given all of the groceries on the list.

All the items in the grocery list cost a total of $33.09, but you only have $31.89. Given that every item in the list costs at least $1.20 and there is at least one item in the list, you can remove one item to stay within your budget. Update the .getMostExpensiveItemCost() method to find the most expensive item in the array of groceryPrices, and return the item.

* Hint
Our aim is to find the largest number in the entire list. If we were to do this manually, we would look at each number, one by one, and compare that number to the maximum number we have so far.

* Hint
We can translate our manual steps into code. This is where a loop can come in handy (to iterate through each element in the array). The comparisons can be done in the loop, so we don’t have to write each comparison.

```
public class GroceryList {
  public static double getMostExpensiveItemCost(double[] groceryPrices) {
    // We can set the maxCost initially to the first item without an IndexOutOfBounds error because we can assume there is at least one item in the array.
    double maxCost = groceryPrices[0];
    
    for (double itemCost : groceryPrices) {
    if (maxCost < itemCost) {
      maxCost = itemCost;
    }
    }
    return maxCost;
  }

  public static void main(String[] args) {
    // Below is a sample test case you can use to run your code.
    // Try playing around with different values in the array to test edge cases
   double[] groceryPrices = {10.0, 12.3, 8.45, 2.34};
   System.out.println(getMostExpensiveItemCost(groceryPrices));
  }
}

--- output: 12.3
```
## Extra challenge
How would you modify your solution to return the cheapest item in the array of groceryPrices?

* Hint
Our current solution looks for the biggest item in the grocery list. To find the smallest, we just need to reverse the condition that checks for the biggest item.

## Super extra challenge:
Now, let’s assume that each item costs at least $0.00. How would you modify your solution to return the cost of the item that, when removed, allows you to spend the most money? (Remember, the total should still stay within the limits of your budget.)

* Hint
There are a couple of ways to do this. One way is to expand on our solution for the previous challenges with some modifications. As we iterate through each item in the array, what constraints are we adding on that would make an item eligible for removal?

* Hint
What extra steps can we take to find the smallest item among the eligible items for removal?
