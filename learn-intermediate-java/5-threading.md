# Threading...
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
```
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

--- CrystallBall.java
import java.util.Random;

public class CrystalBall extends Thread {

  /* Instance Variables */
  private Question question;

  /* Constructors */
  public CrystalBall(Question question) {
    this.question = question;
  }

  /* Instance Methods */
  @Override
  public void run() {
    ask(this.question);
  }

  public void ask(Question question) {
    System.out.println("Good question! You asked: " + question.getQuestion());
    this.think(question);
    System.out.println("Answer: " + this.answer());
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
}
```

Note: Sometimes you will see developers specifically import the Thread and Runnable classes from java.lang. This is not necessary since all Java programs naturally import the java.lang package anyway. It is often used to help with readability or remind an author to add a specific feature.

## Supervising a Thread
So far, you have seen how to start threads with the start() method and learned multiple ways to implement threads into sequential programs.

Sometimes, we want to be able to see the status of threads during their execution. In Java, the best pattern for doing so is to use a supervisor thread. This is a pattern where the main thread (or another thread) is able to watch and check on the progress of another thread, as long as it has access to the corresponding Thread instance. This kind of thread is implemented just like other threads. Supervisor threads are often used for updating the user of our program on the progress of an ongoing task.

One Thread method that a supervisor thread may use to monitor another thread is isAlive(). This method returns true if the thread is still running, and false if it has terminated. A supervisor might continuously poll this value (check it at a fixed interval) until it changes, and then notify the user that the thread has changed state. Here is an example:
```
import java.time.Instant;
import java.time.Duration;
 
public class Factorial{
 public int compute(int n){
   // the existing method to compute factorials
 }
 
 // utility method to create a supervisor thread
 public static Thread createSupervisor(Thread t){
   Thread supervisor = new Thread(() -> {
     Instant startTime = Instant.now();
     // supervisor is polling for t's status
     while (t.isAlive()) {
       System.out.println(Thread.currentThread().getName() + " - Computation still running...");
       Thread.sleep(1000);
     }
   });
 
   // setting a custom name for the supervisor thread
   supervisor.setName("supervisor");
   return supervisor;
 
 }
 
 public static void main(String[] args){
   Factorial f = new Factorial();
 
   Thread t1 = new Thread(() -> {
     System.out.println("25 factorial is...");
     System.out.println(f.compute(25));
   });
 
 
   Thread supervisor = createSupervisor(t1);
 
   t1.start();
   supervisor.start();
 
   System.out.println("Supervisor " + supervisor.getName() + " watching worker " + t1.getName());
 }
}
```
The thread labeled supervisor here is polling the status of t1 (a “worker” thread) continuously at an interval of 1000 milliseconds (one second). The supervisor checks the status of t1 using the isAlive() method in a while loop.

Additionally, the getName() method will return a unique name for a thread in the current context. This comes in handy when debugging multi-threaded programs: the programmer can better understand which thread is performing a certain task at a given moment. We can also name a thread ourselves, using the setName() method of the Thread class.

Now, let’s create a supervisor thread that polls for the status of all of the running CrystalBall threads.

```
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    CrystalBall c = new CrystalBall();
    List<Thread> threads = questions.stream().map(q -> {
      Thread t = new Thread(() -> {
        c.ask(q);
      });
      return t;
    }).collect(Collectors.toList());
    Thread supervisor = createSupervisor(threads);

    threads.stream().forEach(t -> t.start());
    supervisor.start();
  }

  public static Thread createSupervisor(List<Thread> threads) {

    Thread supervisor = new Thread(() -> {
      while (true) {
        List<String> runningThreads = threads.stream().filter(t -> t.isAlive()).map(t -> t.getName()).collect(Collectors.toList());
        System.out.println(Thread.currentThread().getName() + " - Currently running threads: " + runningThreads);
        if (runningThreads.isEmpty()) {
          break;
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          System.out.println(e);
        }
      }
      System.out.println(Thread.currentThread().getName() + " - All threads completed!");
    });
    
     // Set the name here...
    supervisor.setName("Supervisor");

    return supervisor;
  };
}

import java.util.Random;

public class CrystalBall {

  /* Instance Methods */
  public void ask(Question question) {
    System.out.println(Thread.currentThread().getName() + " - Good question! You asked: " + question.getQuestion());
    this.think(question);
    System.out.println(Thread.currentThread().getName() + " - Answer: " + this.answer());
  }

  private void think(Question question) {
    System.out.println(Thread.currentThread().getName() + " - Hmm... Thinking");
    try {
      Thread.sleep(this.getSleepTimeInMs(question.getDifficulty()));
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println(Thread.currentThread().getName() + " - Done!");
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
}
```


## Waiting for Thread Completion
Another common scenario in multi-threaded programs is to wait for a thread to complete before proceeding in a path of execution.

In other languages, this concept is called “awaiting” or “blocking”. In Java, we say that we wait for the thread to join.

When a thread joins, it means that the thread’s task is complete and the process that was initially forked off has been “joined” back into the main thread.

This is useful when we can only perform a specific task once several prerequisite tasks have been completed. Take a look at BurgerMaker.java in the editor, which describes the process of preparing a cheeseburger. A cheeseburger is generally prepared in the following way:

1. Grill hamburger patty.
2. Once the patty is properly cooked, add cheese on top to melt it.
3. Toast the burger buns.
4. Fry onions.
5. Once all ingredients are ready, assemble the burger.
   
Many of these steps can be done concurrently. However, notice that some of the steps are dependent on the completion of other steps. Step 2 requires the patty to be grilled before melting cheese on top of it. Step 5 requires all other steps to be completed before it can start (we can’t assemble a burger until everything is ready).

In BurgerMaker, we can see how join() is used to enforce dependencies between different tasks. By using start() and join() properly, we can ensure that tasks that can be performed concurrently are started. Still, tasks with dependencies are not started until all dependencies have been joined.

In this exercise, we’ll use the join() features we see in BurgerMaker.java to ensure that tasks occur in the correct order while making a cake in our new file, CakeMaker.java. CakeMaker describes baking a cake by following these steps: A cake is made in the following way:

1. The oven must be preheated before the cake can be baked.
2. The dry ingredients and wet ingredients should be mixed before the ingredients can be combined.
3. The ingredients must be combined before the cake can be baked.
4. The cake must be finished baking before the cake can be frosted.

```
import java.lang.Thread;
public class BurgerMaker {
 
 public void toastBuns() {
   try {
     System.out.println("Toasting buns...");
     Thread.sleep(2000);
   } catch (InterruptedException e) {
     System.out.println(e);
   }
 }

 public void grillPatty() {
   System.out.println("Grilling patty...");
   Thread.sleep(1500);
 }

 public void meltCheese() {
   System.out.println("Melting cheese...");
   Thread.sleep(800);
 }

 public void fryOnions() {
   System.out.println("Frying onions...");
   Thread.sleep(1000);
 }

 public void assembleBurger() {
   System.out.println("Assembling burger...");
   Thread.sleep(1500);
 }

 public static void main(String[] args) {
   BurgerMaker burgerMaker = new BurgerMaker();
   Thread t1 = new Thread(() -> burgerMaker.toastBuns());
   Thread t2 = new Thread(() -> burgerMaker.grillPatty());
   Thread t3 = new Thread(() -> burgerMaker.meltCheese());
   Thread t4 = new Thread(() -> burgerMaker.fryOnions());
   Thread t5 = new Thread(() -> burgerMaker.assembleBurger());
 
   t1.start();
   t2.start();
   t4.start();
   t2.join(); // must grill patty before melting cheese on it
   t3.start(); // ready to melt cheese
   t1.join();
   t3.join();
   t4.join();
   // must complete all other steps before assembling burger
   t5.start();
   t5.join(); // waiting for the burger assembly task to complete
   System.out.println("Burger complete!");
 }
 
}

public class CakeMaker {

    /* Instance Variables */
    private boolean whiskInUse = false;
    private boolean ovenInUse = false;
    private boolean mixingBowlInUse = false;

    /* Main Method */
    public static void main(String[] args) {

        CakeMaker c = new CakeMaker();
        try {

            Thread preheatOven = new Thread(() -> c.preheatOven(), "preheatOven");
            Thread mixDryIngredients = new Thread(() -> c.mixDryIngredients(), "mixDryIngredients");
            Thread mixWetIngredients = new Thread(() -> c.mixWetIngredients(), "mixWetIngredients");
            Thread combineIngredients = new Thread(() -> c.combineIngredients(), "combineIngredients");
            Thread bakeCake = new Thread(() -> c.bakeCake(), "bakeCake");
            Thread makeFrosting = new Thread(() -> c.makeFrosting(), "makeFrosting");
            Thread frostCake = new Thread(() -> c.frostCake(), "frostCake");

            // Add logic to start and initial.join threads here!
            // There should be a .start() and .initial.join() method call for each thread, seven in total.
            preheatOven.start();
            mixDryIngredients.start();
            mixWetIngredients.start();
            makeFrosting.start();
            mixDryIngredients.join();
            mixWetIngredients.join();
            combineIngredients.start();
            combineIngredients.join();
            preheatOven.join();
            bakeCake.start();
            makeFrosting.join();
            bakeCake.join();
            frostCake.start();
            frostCake.join();

            System.out.println("Cake complete!");
        } catch (Exception e) {
            System.out.println(e);
        }
    } // End of Main

    /* Instance Methods */
    public void preheatOven() {
        try {
            printTask("Oven pre-heating...");
            ovenInUse = true;
            Thread.sleep(10000);
            ovenInUse = false;
            printTask("Done!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void mixDryIngredients() {
        try {
            printTask("Mixing dry ingredients...");
            mixingBowlInUse = true;
            Thread.sleep(200);
            printTask("Adding cake flour");
            Thread.sleep(200);
            printTask("Adding salt");
            Thread.sleep(200);
            printTask("Adding baking powder");
            Thread.sleep(200);
            printTask("Adding baking soda");
            Thread.sleep(200);
            whiskInUse = true;
            printTask("Mixing...");
            Thread.sleep(200);
            whiskInUse = false;
            mixingBowlInUse = false;
            printTask("Done!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    };

    public void mixWetIngredients() {
        try {
            printTask("Mixing wet ingredients...");
            mixingBowlInUse = true;
            Thread.sleep(1000);
            printTask("Adding butter...");
            Thread.sleep(500);
            printTask("Adding eggs...");
            Thread.sleep(500);
            printTask("Adding vanilla extract...");
            Thread.sleep(500);
            printTask("Adding buttermilk...");
            Thread.sleep(500);
            whiskInUse = true;
            printTask("Mixing...");
            Thread.sleep(1500);
            whiskInUse = false;
            mixingBowlInUse = false;
            printTask("Done!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    };

    public void combineIngredients() {
        try {
            printTask("Combining ingredients...");
            mixingBowlInUse = true;
            Thread.sleep(1000);
            printTask("Adding dry mix to wet mix...");
            Thread.sleep(1500);
            whiskInUse = true;
            printTask("Mixing...");
            Thread.sleep(1500);
            whiskInUse = false;
            mixingBowlInUse = false;
            printTask("Done!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    };

    public void bakeCake() {
        try {
            printTask("Baking cake...");
            ovenInUse = true;
            Thread.sleep(10000);
            ovenInUse = false;
            printTask("Done!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void makeFrosting() {
        try {
            printTask("Making frosting...");
            whiskInUse = true;
            mixingBowlInUse = true;
            printTask("Adding butter...");
            Thread.sleep(200);
            printTask("Adding milk...");
            Thread.sleep(200);
            printTask("Adding sugar...");
            Thread.sleep(200);
            printTask("Adding vanilla extract...");
            Thread.sleep(200);
            printTask("Adding salt...");
            Thread.sleep(200);
            whiskInUse = false;
            mixingBowlInUse = false;
            printTask("Done!");

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void frostCake() {
        try {
            printTask("Frosting cake...");
            Thread.sleep(1500);
            printTask("Done!");

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    private void printTask(String task) {
        System.out.println(Thread.currentThread().getName() + " " + " - " + task);
    };
}
```

## Thread Synchronization
Another important scenario when working with multi-threaded programs is managing shared resources between threads.

When we access the same data from two different threads, we may cause a race condition. A race condition occurs when some inconsistency is caused by two threads trying to access the same shared data at the same time. Consider this example where two threads are attempting to increment a value simultaneously:
```
class IntegerMapper{
  public int[] array = {1, 2, 3, 4, 5};    
  public void incrementElement(int i, int j){
    array[i] += j;
  }   
}
public class Main{
  public static void main(String args[]) throws InterruptedException{
    IntegerMapper iMapper = new IntegerMapper();
    Thread thread1 = new Thread(() -> {
      for(int i = 0; i < 100; i++){
        iMapper.incrementElement(2, 4);
        try{
          Thread.sleep(10);
        }
        catch(InterruptedException exception){
          System.out.println("Error!");
        }
      }
            
    });
    Thread thread2 = new Thread(() -> {
      for(int i = 0; i < 100; i++){
        iMapper.incrementElement(2, 3);
        try{
          Thread.sleep(10);
        }
        catch(InterruptedException exception){
          System.out.println("Error!");
        }
      }
    });
        
    thread1.start();
    thread2.start();
        
    thread1.join();
    thread2.join();
        
    System.out.println(iMapper.array[2]);
  }
}
```
If we run this program multiple times, we will get a different 
output
Preview: Docs Loading link description
 each time! This is because thread1 and thread2 conflict in their access of array[2]. To update the value in the incrementElement() method, the value must be read, incremented, then saved. One thread could be reading the value while the other thread is incrementing it, leading to inconsistencies.

We can prevent race conditions on shared data by using the synchronized keyword in Java. In a threaded program, when we add synchronized to the definition of a function, it will ensure that for a given instance of a class, only one thread can run that method at a time. We can fix incrementElement() by making it synchronized like so:
```
public synchronized void incrementElement(int i, int j){
    array[i] += j;
}
```
Now, each time the program is run, it will output the same value.

Let’s see how we can use thread synchronization to solve a classic race condition problem.
```
public class Counter {
  private int c = 0;

  public int getCount() {
    return this.c;
  };

  public void setCount(int c) {
    this.c = c;
  };

  public synchronized void increment() {
    this.setCount(this.getCount() + 1);
  }

  public static void main(String[] args) throws InterruptedException {
    Counter c = new Counter();

    Thread a = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        c.increment();
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          System.out.println(e);
        }
      }
    });

    Thread b = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        c.increment();
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          System.out.println(e);
        }
      }
    });

    // Start both threads here
    a.start();
    b.start();

    // Join both threads here
    a.join();
    b.join();

    System.out.println("Counter: " + c.getCount());
  }
}
```

## Communicating Between Threads
Previously, we learned how to block thread execution using join(). However, this is only to block thread execution from the context where the thread was started. For example, if a thread was created and started in the main thread, we can only call join() on it from the main thread and wait on its completion from there.

In Java, to control thread execution from within other threads, we can use the wait(), notify(), and notifyAll() methods. These are primarily used to protect shared resources from being used by two threads at the same time or to wait until some condition has changed in a thread.

When using wait() and notifyAll(), it is important to do so in a synchronized(this) block. When we create a synchronized(this) block, we are telling Java that we want it to be the only thread accessing the fields of the class at a given moment. In the synchronized(this) block, we must:

1. Check the condition on which to wait.
2. Decide whether to wait() (block the execution of the current thread) or notifyAll() (allow other threads to check their condition again and proceed)

```
import java.lang.Thread;
 
public class OrderDinnerProcess {
 private boolean foodArrived = false;
 
 private void printTask(String task) { 
   System.out.println(Thread.currentThread().getName() + " - " + task);
 }
 
 public void eatFood() {
   printTask("Wow, I am starving!");
   try {
     synchronized (this) {
       while (!this.foodArrived) {
         printTask("Waiting for the food to arrive...");
         wait();
       }
     }
   } catch (InterruptedException e) {
     System.out.println(e);
   }
   printTask("Finally! Yum yum yum!!!");
 }
 
 public void deliverFood() {
   printTask("Driving food over...");
   try {
     Thread.sleep(5000);
     synchronized (this) {
       this.foodArrived = true;
       printTask("Arrived!");
       notifyAll();
     }
   } catch (InterruptedException e) {
     System.out.println(e);
   }
 }
 
 public static void main(String[] args) {
   OrderDinnerProcess p = new OrderDinnerProcess();
   try {
     for (int i = 0; i < 5; i++) {
       Thread eatFood = new Thread(() -> p.eatFood());
       eatFood.start();
     }
     Thread.sleep(1000);
     Thread delivery = new Thread(() -> p.deliverFood());
     delivery.start();
   } catch (InterruptedException e) {
     System.out.println(e);
   }
 }
}
```
In the example, we are simulating a family of five eager to eat some dinner! However, they can’t eat until the food has arrived. We could have controlled this behavior before by defining, starting, and joining the delivery thread before the five eatFood() threads began. However, using wait() and notifyAll(), we can start them in any order, and each eatFood() thread will wait() until a call to notifyAll() by another thread tells them to proceed to check again.

The synchronized block is used here to tell Java that only one thread should be able to read from and write to the foodArrived() field at a time.

The wait() function is used to pause execution for a thread until a call has been made to notifyAll(), which triggers another check of the condition.

The notifyAll() function is used to tell all threads that are currently waiting that they may now proceed.

In addition to being used to coordinate thread execution, we can use the synchronized(this) block, wait(), and notifyAll() to control access to shared resources. If a resource is currently in use, a thread should wait() until a call to notifyAll() is made, which indicates that the shared resource may have been released and is ready for use.

In this exercise, you’ll use the synchronized(this) block, wait(), and notifyAll() to update our CakeMaker class so that only one thread can use the mixing bowl at a given time. The other threads must wait() until the mixing bowl is released, and are instructed to continue with execution with a call to notifyAll().
```
public class CakeMaker {

  /* Instance Variables */
  private boolean whiskInUse = false;
  private boolean ovenInUse = false;
  private boolean mixingBowlInUse = false;

  /* Main Method */
  public static void main(String[] args) {

    CakeMaker c = new CakeMaker();
    try {

      Thread preheatOven = new Thread(c::preheatOven, "preheatOven");
      Thread mixDryIngredients = new Thread(c::mixDryIngredients, "mixDryIngredients");
      Thread mixWetIngredients = new Thread(c::mixWetIngredients, "mixWetIngredients");
      Thread combineIngredients = new Thread(c::combineIngredients, "combineIngredients");
      Thread bakeCake = new Thread(c::bakeCake, "bakeCake");
      Thread makeFrosting = new Thread(c::makeFrosting, "makeFrosting");
      Thread frostCake = new Thread(c::frostCake, "frostCake");

      preheatOven.start();
      mixDryIngredients.start();
      mixWetIngredients.start();
      makeFrosting.start();
      mixDryIngredients.join();
      mixWetIngredients.join();
      combineIngredients.start();
      combineIngredients.join();
      preheatOven.join();
      bakeCake.start();
      makeFrosting.join();
      bakeCake.join();
      frostCake.start();
      frostCake.join();

      System.out.println("Cake complete!");
    } catch (Exception e) {
      System.out.println(e);
    }
  } // End of Main

  /* Instance Methods */
  public void preheatOven() {
    try {
      printTask("Oven pre-heating...");
      ovenInUse = true;
      Thread.sleep(10000);
      ovenInUse = false;
      printTask("Done!");
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  public void mixDryIngredients() {
    try {
      printTask("Mixing dry ingredients...");
      synchronized(this) {
        while(mixingBowlInUse) {
          printTask("Waiting for the mixing bowl...");
          wait();
        }
        mixingBowlInUse = true;
        printTask("Using mixing bowl!");
      }
      Thread.sleep(200);
      printTask("Adding cake flour");
      Thread.sleep(200);
      printTask("Adding salt");
      Thread.sleep(200);
      printTask("Adding baking powder");
      Thread.sleep(200);
      printTask("Adding baking soda");
      Thread.sleep(200);
      whiskInUse = true;
      printTask("Mixing...");
      Thread.sleep(200);
      whiskInUse = false;
      synchronized(this) {
        printTask("Releasing mixing bowl!");
        mixingBowlInUse = false;
        notifyAll();
      }
      printTask("Done!");
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  };

  public void mixWetIngredients() {
    try {
      printTask("Mixing wet ingredients...");
      synchronized(this) {
        while(mixingBowlInUse) {
          printTask("Waiting for mixing bowl...");
          wait();
        }
        printTask("Using mixing bowl!");
        mixingBowlInUse = true;
      };
      Thread.sleep(1000);
      printTask("Adding butter...");
      Thread.sleep(500);
      printTask("Adding eggs...");
      Thread.sleep(500);
      printTask("Adding vanilla extract...");
      Thread.sleep(500);
      printTask("Adding buttermilk...");
      Thread.sleep(500);
      whiskInUse = true;
      printTask("Mixing...");
      Thread.sleep(1500);
      whiskInUse = false;
      synchronized(this) {
        printTask("Releasing mixing bowl!");
        mixingBowlInUse = false;
        notifyAll();
      }
      printTask("Done!");
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  };

  public void combineIngredients() {
    try {
      printTask("Combining ingredients...");
      mixingBowlInUse = true;
      Thread.sleep(1000);
      printTask("Adding dry mix to wet mix...");
      Thread.sleep(1500);
      whiskInUse = true;
      printTask("Mixing...");
      Thread.sleep(1500);
      whiskInUse = false;
      mixingBowlInUse = false;
      printTask("Done!");
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  };

  public void bakeCake() {
    try {
      printTask("Baking cake...");
      ovenInUse = true;
      Thread.sleep(10000);
      ovenInUse = false;
      printTask("Done!");
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  public void makeFrosting() {
    try {
      printTask("Making frosting...");
      whiskInUse = true;
      mixingBowlInUse = true;
      printTask("Adding butter...");
      Thread.sleep(200);
      printTask("Adding milk...");
      Thread.sleep(200);
      printTask("Adding sugar...");
      Thread.sleep(200);
      printTask("Adding vanilla extract...");
      Thread.sleep(200);
      printTask("Adding salt...");
      Thread.sleep(200);
      whiskInUse = false;
      mixingBowlInUse = false;
      printTask("Done!");

    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  public void frostCake() {
    try {
      printTask("Frosting cake...");
      Thread.sleep(1500);
      printTask("Done!");

    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  private void printTask(String task) {
    System.out.println(Thread.currentThread().getName() + " " + " - " + task);
  }
} // End of CakeMaker
```
