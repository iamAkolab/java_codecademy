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
### Extra challenge
How would you modify your solution to return the cheapest item in the array of groceryPrices?

* Hint
Our current solution looks for the biggest item in the grocery list. To find the smallest, we just need to reverse the condition that checks for the biggest item.

### Super extra challenge:
Now, let’s assume that each item costs at least $0.00. How would you modify your solution to return the cost of the item that, when removed, allows you to spend the most money? (Remember, the total should still stay within the limits of your budget.)

* Hint
There are a couple of ways to do this. One way is to expand on our solution for the previous challenges with some modifications. As we iterate through each item in the array, what constraints are we adding on that would make an item eligible for removal?

* Hint
What extra steps can we take to find the smallest item among the eligible items for removal?

## 3. Lengthy Groceries
You recall a conversation you had with your friend, who mentioned that they are allergic to foods that have more than 5 letters in the name. How many items in the grocery list is your friend allergic to?

Update the .getNumAlllergicItems() method to count up the number of items in groceryList that has more than 5 letters. Set it to numAllergicItems.

* Hint
Strings have a .length() method that will return the number of characters in the string.
```
public class GroceryList {
  public static int getNumAllergicItems(String[] groceryList) {
    int numAllergicItems = 0;

    for (int i = 0; i < groceryList.length; i++) {
      if (groceryList[i].length() > 5) {
      numAllergicItems++;
    }
  }


    return numAllergicItems;
  }

  public static void main(String[] args) {
    // Below is a sample test case you can use to run your code.
    // Try playing around with different values in the array to test edge cases
    String[] groceryList = {"apple", "milk", "banana", "bananas", "chocolate"};

    System.out.println(getNumAllergicItems(groceryList));
  }
}
---Output: 3
```

## 4. Special Item
As you enter the store, a greeter congratulates you on being their 99th customer! Any item in your cart that ends with 99 cents will make your entire bill $0.00!

Update the .hasSpecialItem() method to return true if there is an item in your list that ends with 99 cents, and false if there is not an item that ends with 99 cents.

Hint
There are a couple of ways you can determine if a number ends with $0.99.
- One way is to cast the number as a String and check the last two characters. Check out the substring documentation here.
- Another way could be to use the mod, %, operator and check if you can mod the price by 1. Before you check for equality, you may need to isolate the first two decimal places, which you can do by multiplying by 100 and using Math.floor().

```
public class GroceryList {
  public static boolean hasSpecialItem(double[] groceryPrices) {

     for (double itemCost : groceryPrices) {
    String itemCostStr = String.valueOf(itemCost);
    if (itemCostStr.length() > 1 && itemCostStr.substring(itemCostStr.length() - 2, itemCostStr.length()).equals("99")) {
      return true;
    }
  }


    return false;
  }

  public static void main(String[] args) {
    // Below is a sample test case you can use to run your code.
    // Try playing around with different values in the array to test edge cases
    double[] groceryList = {10.0, 89.9, 8.99, 2.34};
    System.out.println(hasSpecialItem(groceryList));
  }
}
--- Output: true
```

## 5. Alphabetical Items
The grocery store organizes all of its items in ascending alphabetical order. Before you head out, you want to check to make sure that your list is alphabetized so you can pick up your items more quickly. For example, apples are in the first aisle, bean sprouts are in the second, followed by bubble gum, and zucchini comes last. When comparing characters, spaces come before any alphabetical letter (e.g. bean sprouts come before beans).

Update the isAlphabetized() method to return true if the list is in ascending alphabetical order or false if it is not.

* Hint
Think about the different edge cases the grocery list could have. If there was one or no items in the groceryList, what should we return?

* Hint
If there are two or more items in the array, then we have to check for alphabetical order. We will need to compare two consecutive items at any given time.
Strings have a .compareTo() method that will return a positive number if the string in the argument is greater than the first string, and negative if the string in the argument is less than the first string. Check out the documentation here.

* Hint
Since we’re trying to compare two elements of an array, how can we avoid an IndexOutOfBounds error? Should the two counters that access the consecutive items have to traverse the entire array?

```
public class GroceryList {
  public static boolean isAlphabetized(String[] groceryList) {
    
    if (groceryList.length > 1) {
    String lastGroceryItem = groceryList[0];

    for (int i = 1; i < groceryList.length; i++) {
      if (lastGroceryItem.compareTo(groceryList[i]) > 0) {
        return false;
      }

      lastGroceryItem = groceryList[i];
    }
  }
    return true;
  }

  public static void main(String[] args) {
    // Below is a sample test case you can use to run your code.
    // Try playing around with different values in the array to test edge cases
    String[] groceryList = {"apples", "banana", "bananas", "chocolate"};
    System.out.println(isAlphabetized(groceryList));
  }
}
--- Output: true
```

### Extra challenge
Given a groceryList, create a method that orders your grocery list in ascending alphabetical order and returns the alphabetized list.

* Hint
One way to look at this problem is to think about how we would find the "smallest" element, or the element that should come first in the list? From there, we can perform a swap with the first element. Then, we'll want to find the "smallest" element among the elements (excluding the first one), perform a swap, and move on to the third element.

Given the above, how can we use loops to repeat the same actions for every element in the array?

## 6. Duplicate Items
Looking at your grocery list, you suspect there may be duplicates. To make sure you don’t buy more items than you need, how would you check if there is more than one instance of any item in the array?

For simplicity, we can assume that any items that are capitalized are different from items with letters that are all lower case. For example, “apples” are not the same as “appLes”.

Update .hasDuplicateItems() to return a boolean if there are duplicate items in the list.

* Hint
There are several ways we can approach this. One straightforward way is to think about how we would solve this problem if we had one string and check if there was another instance of the string in the array.

From there, we can expand it to two strings, then three, and so on. What repetition do we start to see, and can we use loops to remove duplicate code?

* Hint
Using our approach from the last hint, we can apply the same principle to our array here. Instead of having two separate arrays, we can reuse the same array and iterate through the same array twice using nested loops.

```
public class GroceryList {
  public static boolean hasDuplicates(String[] groceryList) {
    for (int i = 0; i < groceryList.length - 1; i++) {
    for (int j = i + 1; j < groceryList.length; j++) {
      if (groceryList[i] == groceryList[j]) {
       return true; 
      }
    }
  }
    return false;
  }

  public static void main(String[] args) {
    // Below is a sample test case you can use to run your code.
    // Try playing around with different values in the array to test edge cases
    String[] groceryList = {"apples", "banana", "Apples", "chocolate"};
    System.out.println(hasDuplicates(groceryList));
  }
}
--- Output: False
```

### Extra challenge
Say you have a grocery list (represented by an array), and a list of items in your pantry (a separate array). How would you check to make sure your grocery list does not contain items that are already in your pantry?

* Hint
In the original challenge, we're able determine if there are duplicates within the same array. We could apply the same concept by thinking about the pantry list as the "same" list as the grocery list. The biggest difference we would need to account for is the possibility of differing array lengths.

## 7. Reverse Grocery List
WOOF! While you are looking at your grocery list, your dog ran across your lap and rearranged the order of the items in your groceryList. This time, it caused the items in your groceryList to be rearranged in reverse order. For example, your original list looked like this: ["banana", "chocolate", "cookies", "ice cream"], but now it looks like this: ["ice cream", "cookies", "chocolate", "banana"].

Without creating a new array, update the .reverseGroceries() method to reverse the groceriesList back to normal.

* Hint
One thing we can observe about reversed arrays is that the first element is where the last element should be, and the last element is where the first element should be. The second element is where the second to last element should be, and the second to last element is where the second element should be, and so on.
Hint
Using the observation above, we actually don’t need to traverse the entire array. We could have a counter to run through the first half of the array, and a separate counter to run through the second half of the array but backwards.

*Hint
When swapping items, you'll need a temporary variable to hold your value while you replace the items. For example, if I want to swap a and b, I'll need to store the value of a into c before I transfer b into a:
int a = 0;
int b = 1;
int c = a;
int a = b;
int b = c;

```
public class GroceryList {
  public static void reverseGroceries(String[] groceryList) {
    int j = groceryList.length - 1;
  for (int i = 0; i < groceryList.length/2; i++) {
    String temp = groceryList[i];
    groceryList[i] = groceryList[j];
    groceryList[j] = temp;
    j--;
  }

  }


  public static void main(String[] args) {
    // Below is a sample test case you can use to run your code.
    // Try playing around with different values in the array to test edge cases
    String[] groceryList = {"apples", "banana", "Apples", "chocolate"};
    reverseGroceries(groceryList);

    for (String item : groceryList) {
      System.out.println(item);
    }
  }
}
--- Output:
chocolate
Apples
banana
apples
```
