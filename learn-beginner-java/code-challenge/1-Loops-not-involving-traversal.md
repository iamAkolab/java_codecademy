# Loop Refresher..

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

## 2. Burritos Sold
The cashier gave you your order number. While you’re waiting, you chat with the cashier and they mention that yesterday, they sold a record high of burritos. You wonder, “how many burritos did they sell yesterday?”

Each customer’s order number coincidentally corresponded with the number of burritos they ordered. For example, the first customer of the day had order number 0 and ordered 0 burritos, since they only ordered water. The second customer of the day had order number 1 and ordered 1 burrito, and the last customer of the day had order number 548 and ordered 548 burritos.

Update the .getBurritosSold() method to calculate the total number of burritos sold, and store it in the totalBurritosSold variable.

```
public class BurritoCalculator {
  public static int getBurritosSold(int lastOrderNumber) {
    int totalBurritosSold = 0;

    for (int i = 0; i <= lastOrderNumber; i++) {
    totalBurritosSold += i;
  }

        
    return totalBurritosSold;
  }
    
  public static void main(String []args) {
    // Try testing your code with different arguments in the method call
    System.out.println(getBurritosSold(548));
  }
}
```

We can use our iterator variable i for two purposes. First, we use it to determine when to stop the loop. We stop the loop when i <= lastOrderNumber. But we also use it to keep track of how many burritos were sold. This is a common pattern with loops — you’ll use the iterator to control the loop, but also use it in the code inside the loop for some other purpose.

## 3. Same Orders
for loops and while loops are two common ways to repeat an action. If you haven’t used a for loop yet, try to use one for this next question.

The cashier also brought up another wacky coincidence! There were some customers that ordered the same number of burritos as you.

Every customer whose order number is divisible by 9 ordered the same number of burritos as you. How many order numbers, from order number 1 to your order number, lastOrderNumber, are divisible by 9?

Update the .getNumDivisibleOrders() method to calculate the total number of orders that ordered the same amount as you, including yourself. Make sure the result is stored in the numDivisbleOrders variable.

```
public class BurritoCalculator {
  public static int getNumDivisibleOrders(int lastOrderNumber) {
    int numDivisbleOrders = 0;

     for (int i = 1; i <= lastOrderNumber; i++) {
    if (i % 9 == 0) {
      numDivisbleOrders++;
    }
  }

    return numDivisbleOrders;
  }
  
  public static void main(String []args) {
    // Try running the getDivisibleOrders method with different order numbers.
    // Before you run your code, think about what value you expect to be printed in the output.
    System.out.println(getNumDivisibleOrders(18));
  }
}
```
Our loop looks similar to the loops we’ve used in the past. The trick here is to remember that modulo (%) is an easy way to check if a number is divisible by another number. Modulo returns the remainder of division, so if a % b equals 0, that means that a is evenly divisible by b.\

## 4. Largest Digit
You finally receive your order! Before you go, you want to tip the cashier for their service.

We want to calculate how much tip to give the cashier, which is the largest digit in your budget multiplied by 900.

In the .getTip() method, set maxDigit to the largest digit in budget. For example, if the budget is 281, then the maxDigit should be 8.
```
public class BurritoCalculator {
  public static int getTip(int budget) {
    int maxDigit = 0;
     while (budget > 0) {
    int digit = budget % 10;
    if (digit > maxDigit) {
      maxDigit = digit;
    }

    budget /= 10;
  }
        
    return maxDigit * 900;
  }
    
  public static void main(String []args) {
    // Try passing different arguments for your budget with varying numbers of digits, like 62, 374, 599, etc
    System.out.println(getTip(123));
  }
}

```
There’s a couple of things to take note of here. The first is that we use % 10 to find the digit in the ones place. For example, 73 % 10 will give you 3. After we find the ones digit, we compare that digit to our previous maximum and update that maximum accordingly. Finally, we look at the next digit by dividing by 10. This will essentially chop off the last digit — 73 / 10 will give us 7 — the 3 has been chopped off! We’ll keep doing this until we chop off the last digit. What does it mean to chop off the last digit? Well if we divide 7 by 10, we’ll end up with 0. So we know we have digits to cut off as long as budget > 0.

## 5. Reverse Number
Oh no! You handed the cashier $819287624, but they entered it backwards as $426782918. While we wait for them to fix the number, we could write some code to do it for us.

In the .getReverseBudget() method, iterate through each digit in budget. Create a new integer, reverseBudget, where every digit appears in the opposite order as the digits in budget.
```
public class BurritoCalculator {
  public static int getReverseBudget(int budget) {
    int reverseBudget = 0;

    while (budget > 0) {
    int digit = budget % 10;
    budget /= 10;
    reverseBudget = (reverseBudget * 10) + digit;
  }
        
    return reverseBudget;
  }
    
  public static void main(String []args) {
    // Try passing different arguments for your budget with varying numbers of digits, like 62, 374, 599, etc
    System.out.println(getReverseBudget(123));
  }
}
```

This uses a similar strategy as the last problem. We use % 10 and / 10 to get the ones digit and then cut off the ones digit. The difference here is what we do with that digit. We want to add that digit as the ones digit of our new number. To do that we multiply our number by 10 and then add the digit. For example, if our number were 32, and we wanted to add the digit 5 to the ones place, we would do (32 * 10) + 5 to give us a total of 325. We keep doing this until we have no more digits to add.
