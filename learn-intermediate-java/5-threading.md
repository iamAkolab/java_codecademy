# Threading
## Introduction
So far, we have learned about threads and how they work in theory. Before we put it into practice, we should have a strong understanding of what kinds of problems multi-threaded programs are best suited to solve.

Threading can solve problems where there is a lot of waiting to be done. When one task is in a “waiting” or “blocked” state, another one can start. This is useful for programs in which a long-running computation needs to be done, or when a task must wait on a response from an external source such as fetching an image from a server.

In the following exercise, you’ll see how a program could be forced to “wait” for a task’s completion. As you complete it, start thinking about what tasks could be performed in threads, concurrently, to save some time.
```
import java.util.Arrays;
import java.util.List;

public class FortuneTeller {

  public static void main(String[] args) {

    List<Question> questions = Arrays.asList(
      new Question(Question.Difficulty.EASY, "Am I a good coder?"),
      new Question(Question.Difficulty.MEDIUM, "Will I be able to finish this course?"),
      new Question(Question.Difficulty.EASY, "Will it rain tomorrow?"),
      new Question(Question.Difficulty.EASY, "Will it snow today?"),
      new Question(Question.Difficulty.HARD, "Are you really all-knowing?"),
      new Question(Question.Difficulty.HARD, "Do I have any hidden talents?"),
      new Question(Question.Difficulty.HARD, "Will I live to be greater than 100 years old?"),
      new Question(Question.Difficulty.MEDIUM, "Will I be rich one day?"),
      new Question(Question.Difficulty.MEDIUM, "Should I clean my room?")
    );

    questions.stream().forEach(q -> {
      CrystalBall c = new CrystalBall();
      c.ask(q);
    });
  }
}

import java.lang.Thread;
import java.util.Random;

public class CrystalBall {

  private int getSleepTimeInMs(Question.Difficulty difficulty) {
    switch (difficulty) {
      case EASY:
        return 1000;
      case MEDIUM:
        return 2000;
      case HARD:
        return 3000;
      default:
        return 500;
    }
  }

  private String answer() {
    String[] answers = {
      "Signs point to yes!",
      "Certainly!",
      "No opinion",
      "Answer is a little cloudy. Try again.",
      "Surely.",
      "No.",
      "Signs point to no.",
      "It could very well be!"
    };
    return answers[new Random().nextInt(answers.length)];
  }

  private void think(Question question) {
    System.out.println("Hmm... Thinking");
    try {
      Thread.sleep(this.getSleepTimeInMs(question.getDifficulty()));
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println("Done!");
  }

  public void ask(Question question) {
    System.out.println("Good question! You asked: " + question.getQuestion());
    this.think(question);
    System.out.println("Answer: " + this.answer());
  }

}

```

## Extending the Thread Class
There are multiple ways to implement threading in Java. The first that we will try involves extending the Thread class. In Java, there is a built-in class that handles threads: java.lang.Thread. To create a thread, we create a class that extends the built-in Thread class and then overrides its public void run() method. Beyond these two requirements, the rest of our class can have anything we want in it!

The public void run() method that we override performs the actual work that our thread should be responsible for. This may be performing a long-running computation, calling out to another application or service, or other tasks that involve some waiting.

Notice that the run() method is a void and accepts no parameters. Every run() method must be of this signature. If run() requires some external information, that information must be a field declared in the class and initialized in the constructor. However, be very careful of using external information as this may present some synchronization issues.

When we defined our class in this way, we can then create an instance of our class in any other part of our program and call start() on the instance to trigger the thread’s execution.

Here’s an example of a sequential program in Java that computes the meaning of life:
```
public class HugeProblemSolver{

  public HugeProblemSolver(){
    // Required constructor used for passing information to the start() method.
  }

  private static void solveComputation(){
    // Solves random computation
    // Takes anywhere from 1 second to 10 minutes
    System.out.println("The answer is: 42");
  }

  public static void main(String[] args){
    HugeProblemSolver.solveComputation();
    HugeProblemSolver.solveComputation();
  }
}
```
In this program, we will only see the answer to the second question after the first question has been answered. A threaded solution would allow us to start the computations at the same time, and receive the answers as soon as they are available.

Let’s take a look at the threaded approach:
```
public class HugeProblemSolver extends Thread{

  public HugeProblemSolver(){
    // Required constructor used for passing information to the start() method.
  }

  private static void solveComputation(){
    // Solves random computation
    // Takes anywhere from 1 second to 10 minutes
  }

  @Override
  public void run(){
    solveComputation();
    System.out.println("The answer is: 42");
  }

  public static void main(String[] args){
    HugeProblemSolver m1 = new HugeProblemSolver();
    HugeProblemSolver m2 = new HugeProblemSolver();
    m1.start();
    m2.start();
  }
}
```
Now, you can see that all that changed was:

* Extended the Thread Class.
* Created and Overrode the run() method from Thread.
* Instantiated HugeProblemSolver and called start() which starts a new thread and searches in the class for the run() method to execute.
  
Now, both Threads we created are working on solving their own problems simultaneously. Whichever one finishes first will print to the console first. There is no need to wait on sequential order.
```
--- CrystalBall.java
import java.util.Random;


public class CrystalBall extends Thread {

  private Question question;

  public CrystalBall(Question question) {
    this.question = question;
  }

  private int getSleepTimeInMs(Question.Difficulty difficulty) {
    switch (difficulty) {
      case EASY:
        return 1000;
      case MEDIUM:
        return 2000;
      case HARD:
        return 3000;
      default:
        return 500;
    }
  }

  private String answer() {
    String[] answers = {
        "Signs point to yes!",
        "Certainly!",
        "No opinion",
        "Answer is a little cloudy. Try again.",
        "Surely.",
        "No.",
        "Signs point to no.",
        "It could very well be!"
    };
    return answers[new Random().nextInt(answers.length)];
  }

  @Override
  public void run() {
    ask(this.question);
  }

  private void think(Question question) {
    System.out.println("Hmm... Thinking");
    try {
      Thread.sleep(this.getSleepTimeInMs(question.getDifficulty()));
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println("Done!");
  }

  public void ask(Question question) {
    System.out.println("Good question! You asked: " + question.getQuestion());
    this.think(question);
    System.out.println("Answer: " + this.answer());
  }
}

--- FortuneTeller.java
import java.util.Arrays;
import java.util.List;

public class FortuneTeller {

  public static void main(String[] args) {

    List<Question> questions = Arrays.asList(
        new Question(Question.Difficulty.EASY, "Am I a good coder?"),
        new Question(Question.Difficulty.MEDIUM, "Will I be able to finish this course?"),
        new Question(Question.Difficulty.EASY, "Will it rain tomorrow?"),
        new Question(Question.Difficulty.EASY, "Will it snow today?"),
        new Question(Question.Difficulty.HARD, "Are you really all-knowing?"),
        new Question(Question.Difficulty.HARD, "Do I have any hidden talents?"),
        new Question(Question.Difficulty.HARD, "Will I live to be greater than 100 years old?"),
        new Question(Question.Difficulty.MEDIUM, "Will I be rich one day?"),
        new Question(Question.Difficulty.MEDIUM, "Should I clean my room?")
    );

    questions.stream().forEach(q -> {
      CrystalBall c = new CrystalBall(q);
      c.start();
    });
  }
}
```

## Implementing the Runnable Interface

In the previous exercise, you learned that we can convert a class to run as a thread by extending the Thread class. We can also create threaded 
classes in Java by implementing the Runnable interface.

This approach is preferred because we are only allowed to extend one class, and wasting it on Thread might not be beneficial to our program. Here, rather than extending the capability of the built-in Thread class, we just want to use its threading capability. Because of this, implementing the Runnable interface (which is what the Thread class does anyway) and passing in the object into a new Thread object is the preferred way of implementing multithreading. Here’s an example of how we would implement a Factorial thread by implementing Runnable instead of extending Thread:
```
public class Factorial implements Runnable {
 private int n;
 
 public Factorial(int n) {
   this.n = n;
 }
 
 public int compute(int n) {
   // ... the code to compute factorials
 }
 
 public void run() {
   System.out.print("Factorial of " + String.valueOf(this.n) + ":")
   System.out.println(this.compute(this.n));
 }
 
 public static void main(String[] args) {
   Factorial f = new Factorial(25);
   Factorial g = new Factorial(10);
   Thread t1 = new Thread(f);
   Thread t2 = new Thread(f);
   t1.start();
   t2.start();
 }
}
```
A more succinct way of using the Runnable interface is to use lambda expressions. This is a more modern syntax that allows us to define the run() method we want to use inline without requiring the class to implement Runnable or extend Thread. For the above example, the syntax looks like this:
```
public class Factorial {
 public int compute(int n) {
   // ... the code to compute factorials
 }
 
 public static void main(String[] args) {
   Factorial f = new Factorial();
 
   // the lambda function replacing the run method
   new Thread(() -> {
     System.out.println(f.compute(25));
   }).start();
 
   // the lambda function replacing the run method
   new Thread(() -> {
     System.out.println(f.compute(10));
   }).start();
 }
}
```
Behind the scenes, this syntax is still using the Runnable interface. This is because Java will translate this lambda syntax into something like this:
```
new Thread(new Runnable() {
 void run() {
   System.out.println(f.compute(25));
 }
}).start();
```
Using the lambda expression syntax for starting threads, we can create a threaded version of our class in only a few lines!

This syntax has several benefits:

* Our class no longer needs to extend Thread OR implement Runnable. We can make a thread from anything!
* Our class no longer needs a constructor to store arguments! Since we can pass an argument directly into the compute function when we create our thread, we no longer need to create a separate instance of our object any time we want to perform a threaded task with it.
* Our class is easier to read! The lambda syntax makes it so that people reading our code can immediately identify what task is being performed in our thread without having to read our class first to find the run() method.

Now, let’s try both of these methods of implementing Runnable with our FortuneTeller class.

Note: Sometimes you will see developers specifically import the Thread and Runnable classes from java.lang. This is not necessary since all Java programs naturally import the java.lang package anyway. It is often used to help with readability or remind an author to add a specific feature.
