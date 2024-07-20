# Conditional Statements and Comparison Operators.

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
