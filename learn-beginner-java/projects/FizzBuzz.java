/*
Fizz Buzz is a common developer interview question; so common it’s almost cliché!

The challenge is designed to weed out 99.5% programming job candidates who cannot creatively use their coding knowledge to solve coding problems.

Write a FizzBuzz.java program that outputs numbers from 1 to 100… with a catch:

For multiples of 3, print Fizz instead of the number.
For the multiples of 5, print Buzz.
For numbers which are multiples of both 3 and 5, print FizzBuzz.
  */

public class FizzBuzz {
	public static void main(String[] args) {

    for (int i = 1; i <= 100; i++) {
      // You'll write loop body here
      
      if (i % 3 == 0 && i % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (i % 3 == 0) {
            System.out.println("Fizz");
        } else if (i % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(i);
        }
      }
  }
}
