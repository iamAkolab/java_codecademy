# Loops

* while loops
* do-while loops
* for loops
* for-each loops
* break statements
* continue statements

# Loops
## while loops
while loops in Java hinge on a boolean expression that must evaluate to true in order for the loop to run:
```
int num = 0;
while (num < 20) {
  num = num + 1;
}
System.out.println(num) // Prints: 20
```
In the above case, the loop will continue to run until num is no longer less than 20.

## do-while loops
A do-while loop is very similar to a while loop. The only distinction is that a do-while loop will always execute once before evaluating the condition.
```
do {
 System.out.println("2 is equal to 4!");
} while (2 == 4);
// Prints: 2 is equal to 4!
```
If the above code loop was simply a while loop, it would never run since 2 does not equal 4. However, since this is a do-while loop, it will run once, then look at the condition and not run again.

## for loops
A for loop header is made up of the following three parts, each separated by a semicolon:

The initialization of the loop control variable.
A boolean expression.
An increment or decrement statement.
```
for (int i = 0; i <= 10; i++) {
  System.out.println(i);
}
```
The above code will print the numbers from 0 to 10 (inclusive).

## for-each loops
for-each loops, which are also referred to as enhanced loops, allow us to directly loop through each item in a list of items (like an array or ArrayList) and perform some action with each item.

For example, if we wanted to traverse an array of Strings named myArray, we could use:
```
for (int i = 0; i <= myArray.length; i++) {
  // Do something
}
```
but a for-each loop is simpler and more straightforward:
```
for (String s : myArray) {
  // Do something
}
```

## break and continue statements
break and continue are statements that are used within loops to further control the number of iterations. If we ever want to exit a loop before it finishes all its iterations or want to skip one of the iterations, we can use the break and continue keywords.

break
The break keyword is used to exit, or break, a loop. Once break is executed, the loop will stop iterating. For example:
```
for (int i = 0; i < 10; i++) {
  System.out.println(i);
  if (i == 4) {
    break;
  }
}
```

Even though the loop was set to iterate until the condition i < 10 is false, the above code will only output the numbers from 0 to 4 (inclusive) because we used break.

## continue
The continue keyword can be placed inside of a loop if we want to skip an iteration. If continue is executed, the current loop iteration will immediately end, and the next iteration will begin.

We can use the continue keyword to skip any even valued iteration:
```
int[] numbers = {1, 2, 3, 4, 5};
    
for (int i = 0; i < numbers.length; i++) {
  if (numbers[i] % 2 == 0) {
    continue;
  }
  System.out.println(numbers[i]);
}
```
This program would output 1, 3, and 5 since if a number is even, we hit a continue statement, which skips the rest of that iteration, so the print statement is skipped. As a result, we only see odd numbers printed.

```
public class Loops {
  public static void main(String[] args) {
    int num = 0;

    while (num < 10) {
      num++;
      if (num > 10) {
        break;
      }
    }

    do {
      System.out.println("Impossible!");
    } while (2 == 4);

    int[] myArray = new int[10];

    for (int i = 0; i < myArray.length; i++) {
      myArray[i] = i;
    }

    for (int x : myArray) {
      if (x % 2 == 0) {
        continue;
      }
      System.out.println(x);
    }

  }
}
//
Impossible!
1
3
5
7
9
```
