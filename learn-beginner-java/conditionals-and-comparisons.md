# Conditional Statements 

Conditional statements control the flow of code execution based on the truth value of given boolean expressions. This article will cover the following topics:

* if Statements
* else Statements
* if-else Statements
* Comparison Operators
* Logical Operators

## if Statements
The if statement executes a block of code when a specified boolean expression is evaluated as true.
```
if (expression) {
  // Code to run if expression is true
}
```

## else Statements
The else statement executes a block of code when the condition inside the if statement is false. The else statement is always the last condition.
```
if (expression) {
  // Code to run if expression is true
} else {
  // Code to run if expression is false
}
```
## if-else Statements
The if-else statement evaluates another condition only when the previous conditional statements evaluate to false. There can be multiple if-else statements in a single conditional statement.
```
if (expression) {
  // Code to run if expression is true
} else if (expression) {
  // Code to run if previous expression is false and current condition is true
} else if (expression) {
  // Code to run if previous expression is false and current condition is true
} else {
  // Code to run if all previous expressions are false
}
```

```
class Conditionals {
  public static void main(String[] args) { 
    boolean breakfastTime = false;
    boolean lunchTime = false;
    boolean dinnerTime = false;
    if (breakfastTime) {
      System.out.println("Let's have waffles!");
    } else if (lunchTime) {
       System.out.println("Let's have sandwiches!");
    } else if (dinnerTime) {
       System.out.println("Let's have pizza!");
    } else {
       System.out.println("It's not time to eat!");
    }
  }
}
```

# Comparison Operators.
Comparison operators evaluate the relationship between two values in order to determine the expression as true or false.

* Less Than - x < y
* Greater Than - x > y
* Less Than or Equal To - x <= y
* Greater Than or Equal To - x >= y
* Equal To - x == y
* Not Equal To - x != y

## Logical Operators
Logical operators evaluate the truth value between two or more boolean expressions.

## AND - &&
The && operator will return true only if both boolean expressions have a true value; otherwise it will return false:
```
boolean statementA = true;
boolean statementB = false;
boolean statementC = true;

System.out.println(statementA && statementB); // Prints: false
System.out.println(statementA && statementC); // Prints: true
```

## OR - ||
The || operator will return true if at least one of the boolean expressions have a true value; otherwise it will return false:
```
boolean statementA = true;
boolean statementB = false;
boolean statementC = false;

System.out.println(statementA || statementB); // Prints: true
System.out.println(statementB || statementC); // Prints: false
```
## NOT - !
The ! operator returns the negated, or opposite, value of a boolean expression:
```
boolean statementA = true;
boolean statementB = false;
System.out.println(!statementA); // Prints: false
System.out.println(!statementB); // Prints: true
```

```
class Conditionals {
  public static void main(String[] args) {
    int a = 1;
    int b = 2;
    int c = 3;

    boolean statementA = !true;
    boolean statementB = a == c;
    boolean statementC = a + b >= b || c == b;
    boolean statementD = !(true && a == c - b);

    System.out.println(statementA);
    System.out.println(statementB);
    System.out.println(statementC);
    System.out.println(statementD);
  }
}
```
