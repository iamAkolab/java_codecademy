# Variables and Types.
## Declaring a Variable
To declare a variable with a value, use the following syntax:
```
dataType variableName = value;
```
To declare a variable without a value, use this syntax instead:
```
dataType variableName;
```
To assign or change a variable value, state the name of the variable followed by the assignment operator (=) and the new value:
```
variableName = newValue;
```
## Types of Data
In Java, there are several primitive, or built-in, data types:

### int
ints store whole number values between -2147483648 and 2147483647.
```
int moonLanding = 1969;
```
### double
doubles store decimal number values between 4.9E-324 and 1.7976931348623157E+308:
```
double PI = 3.14;
```
### boolean
booleans store true or false values:
```
boolean isRaining = true;
boolean isSunny = false;
```
### char
chars store single character values:
```
char firstLetter = 'a';
```

## Manipulating Number-Based Variables
### Arithmetic Operators
Arithmetic Operators can be used to change the value of a number-based variable. These operators can be used for addition (+), subtraction (-), multiplication (*), division (/), and getting the remainder (%):
```
int num = 3 + 2; // num now equals 5
num = num - 1; // num now equals 4
num = 4 * 4; // num now equals 16
num = num / 2; // num now equals 8
num = num % 5; // num now equals 3
```
### Compound Assignment Operators
Compound assignment operators perform an arithmetic operation on a variable and then reassigns its value in one step. The compound assignment operator is made up of one arithmetic operator immediately followed by the assignment operator:
```
int num = 3;
num += 2; // num now equals 5
num -= 1; // num now equals 4
num *= 4; // num now equals 16
num /= 2; // num now equals 8
num %= 5; // num now equals 3
```
### Increment and Decrement Operators
The increment operator ++ increases a number value by 1 while the decrement operator decreases a number value by 1:

// Increment:
int age = 47;
age++; // age now equals 48
// Decrement:
int apples = 7;
apples--; // apples now equals 6

## Strings
Another common data type in Java is the String. Strings are a reference data type because they are objects from the String class. Strings hold sequences of characters contained within double quotes (").
```
// Option 1 for declaring a String
String greetings = "Greetings, earthlings!";
// Option 2 for declaring a String
String request = new String("Take me to your leader.");
```

# final Keyword
To declare a variable with a value that cannot be manipulated, we need to use the final keyword. For example, the year we were born will always stay the same. There’s no way we can change that information. A value like this in our code should be unchangeable.
To use the final keyword, prepend final to a variable declaration like so:
```
final int yearBorn = 1968;
```
When we declare a variable using final, the value cannot be changed; any attempts at doing so will cause an error to occur:
```
error: cannot assign a value to final variable yearBorn
```
# Casting and Converting
## Numerical-Based Casting
When we use casting to change the data type of number-based values, it can impact how much data the value holds. If there is less data in the value after casting, it’s considered narrow type casting. If the value contains more data after casting, it’s classified as widening type casting.

In order to cast, we need to use a casting operator to alert the compiler that we want to create a temporary value that is a converted data type. Two casting operators we will focus on are (int) and (double).

### Narrow Type Casting
To convert a double to an int, we would place the casting operator (int) in front of a value we would like to convert.

For example:
```
double numDouble = 12.99;
System.out.println("Double value: " + numDouble);
// convert a double to int
int numInt = (int) numDouble;
System.out.println("Int value: " + numInt);
```
This would output:
```
Double value: 12.99
Int value: 12
```
When we convert a double to an int, the digits to the right of the decimal point are truncated. Of course, when we see a value like 12.99, we know that it’s numerically closer in value to 13 than it is to 12. Java does not automatically round the value. If we want to round our positive value to the nearest integer, we can use (int)(value + 0.5); in the case that our number is negative, we would instead use (int)(x - 0.5):
```
double numDouble = 12.99;
System.out.println("Double value: " + numDouble);
// convert a double to an int rounded to nearest value
int numInt = (int) (numDouble + 0.5);
System.out.println("Int value: " + numInt);
```
This would output:
```
Double value: 12.99
Int value: 13
```

It’s important to be aware that there are limitations to when working with whole number, or int, values in Java. There is a limited amount of data that can be stored in an int — 4 bytes. We can use Integer.MIN_VALUE and Integer.MAX_VALUE to find the minimum and maximum value of an int:
```
System.out.println("Min: " + Integer.MIN_VALUE); // Prints: Min: -2147483648
System.out.println("Max: " + Integer.MAX_VALUE); // Prints: Max: 2147483647
```

### Widening Type Casting
If we were to use casting to change an int value to a double, we’d need to implement the (double) casting operator in our code:
```
int numInt = 9;
System.out.println("Int value: " + numInt);
// convert an int to a double
System.out.println("Double value: " + (double)numInt);
```
This will output:
```
Int value: 9
Double value: 9.0
```
In the program above, we did not change the type of numInt in the second print statement; instead, we created a temporary value when we casted numInt as a double. If we wanted to permanently save that value, we could create a new variable like double newValue = (double)numInt.

Unlike in narrow type casting, an int value can automatically get cast to a double value in Java code. For example, this occurrence can happen when we create an arithmetic expression that references a double and an int:
```
System.out.println(100.0 / 5); // Prints: 20.0
```
Java automatically cast the value as a double even though one of the values in the expression was an int.

We can also cast the value of an expression as a double even if both values in the expression are ints. To do this, we’ll place (double) in front of an expression that is contained in its own set of parentheses like so:
```
System.out.println((double)(100 / 5)); // Prints: 20.0
```
### Converting Integer and String Values
In Java, we can convert an int value to a String value and vice versa. This doesn’t count as casting because a String is not a primitive type value, but it can be useful to know how to perform this conversion. To change the value of an int into a String, we need to use the valueOf() method from the String class:
```
int numInt = 15;
System.out.println("Int value: " + numInt);
// Convert int value to String
String numString = String.valueOf(numInt);
System.out.println("String value: " + numString);
```
This will output:
```
Int value: 15
String value: 15
```
Even though the output looks the same, the second output shows us a String value instead of an int value.

If we wanted to turn a String value into an int, we would need to call the parseInt() method from the Integer class. In order to call this method, we will reference the class Integer and then call the method parseInt() by using the dot operator (.). Altogether, this call would look like this Integer.parseInt(argumentValue).

Let’s see this in action by converting the String value "12" into an int:
```
String numString = "12";
// convert String to int
int numInt = Integer.parseInt(numString);
System.out.println("The int value: " + numInt); // Prints: The int value: 12
// we can now use the value like any other int
 numInt++;
System.out.println(numInt); // Prints: 13
```
