# Variables and Types
---
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
