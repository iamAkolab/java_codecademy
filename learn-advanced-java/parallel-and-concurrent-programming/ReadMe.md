# Parallel and Concurrent Programming: Lesson
## The Executor Framework

The Executor framework implements thread pooling through an Executor interface. Using it is fairly intuitive and draws on already pre-existing functionality found in the Thread class, and the executor framework provides additional 
interfaces for various implementation strategies.

Executor interfaces include:

* Executor: launch a Runnable object task
* ExecutorService: manages the lifecycle of tasks in a sub-interface of Executor
* ScheduledExecutor: schedules the execution of tasks in a sub-interface of ExecutorService
Let’s take a look at how we’ll be implementing an executor into our code. We’ll need the following imports:
```
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
```

Additionally, since we need the tasks we enqueue into the thread pool to be Runnable objects, we’ll be creating a Runnable class accordingly. To use an executor, we need to create an ExecutorService object and pass it the number of threads we’ll be allotting it:
```
private static final int N = 10;
ExecutorService executor = Executors.newFixedThreadPool(N);
```

Since the thread pool is responsible for those threads, it will automatically handle creating those and managing how they run. We just need to tell it how many to create and handle. At this point, all we need to do now is pass tasks to the executor, which we do like this (as usual, we’ll probably be putting this into a loop much like how we loop the creation and assignment of manual threads):
```
Runnable task = new RunnableTask();
executor.execute(task);
```


In the above, RunnableTask is the custom class you’ll be creating that implements Runnable. Calling executor.execute(task) will enqueue the newly created Runnable object into the thread pool, which will be processed by one of the waiting threads. Now, thanks to the ExecutorService interface, we have some useful 
methods we can call to interact with the working threads.

If we want to prevent any new tasks from being added to our executor, we can call executor.shutdown(). In this case, the threads will still work and clear out the 
queue, but this will ensure nothing new gets added after this point.

If we want to wait for the thread pool to finish executing everything in its queue, we can call executor.awaitTermination().

There are plenty more useful methods in ExecutorService, but we’ll be using just these in our basic executor. Let’s get some practice with this!

--- Main
```
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
  private static final int N = 10;
  
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(N);
    for (int i = 0; i < 500; i++) {
      Runnable task = new RunnableTask(10000000L + i);
      executor.execute(task);
    }
    // Prevent new tasks from being enqueued
    executor.shutdown();
    // Wait for all threads to finish, up until a maximum of 30 seconds before moving on
    executor.awaitTermination(30, TimeUnit.SECONDS);
    System.out.println("Finished all threads");
  }
}
```

--- RunnableTask
```
public class RunnableTask implements Runnable {

  private final long limit;
  
  RunnableTask(long limit) {
    this.limit = limit;
  }
  
  @Override
  public void run() {
    long sum = 0;
    for (long i = 1; i < limit; i++) {
      sum += i;
    }
    System.out.println(Thread.currentThread().getName() + ": " + sum);
  }
}
```

## The Fork-Join Framework
A similar interface to the executor service was added in Java 7 that included functionality to split a task into smaller subtasks and re-enqueue them into the thread pool. This is particularly useful for more intensive tasks and actually implements parallelism to do it (we’ll touch on this more in the next exercise).

This new interface is part of a Fork-Join framework and is called ForkJoinPool, which distributes a task across several worker threads and waits for a result.

Another big difference is the type of task object we’ll be passing to this pool. Instead of implementing the Runnable class, we’ll actually be extending either RecursiveAction or RecursiveTask depending on the implementation we want.

Here is the difference between the two:

RecursiveAction: does not return any result
RecursiveTask: returns a result
As the namesake suggests, the Fork-Join framework utilizes recursion to perform its subtask splitting, which we’ll limit by defining a set number of threads. This controls how many times the task is allowed to split itself since each subtask will be assigned to an individual thread.

Additionally, instead of calling execute() as we did before to add new tasks to the thread pool 
queue, we’ll be calling invoke(). Since we’re splitting a singular task across the worker threads, we only have to call invoke() once, which means we can invoke the task in the same place we create the pool itself:
```
private static final int N = 4;
ForkJoinExecutor pool = new ForkJoinPool(N);
pool.invoke(task);
```

--- Main
```
import java.math.BigInteger;
import java.time.Clock;
import java.util.concurrent.ForkJoinPool;

public class Main {
  public static void main(String[] args) {
    Clock clock = Clock.systemDefaultZone();
    long start, stop;
    MakeBigIntArray test = new MakeBigIntArray(5000);
    
    // Check the number of available processors
    int nThreads = Runtime.getRuntime().availableProcessors();
    System.out.println(nThreads);
    
    RecursiveFactorial rSum = new RecursiveFactorial(test.getList());
    ForkJoinPool pool = new ForkJoinPool(nThreads);
    
    start = clock.millis();
    pool.invoke(rSum);
    stop = clock.millis();
    
    BigInteger result = rSum.result;
    System.out.println("Time in ms: " + (stop - start));
    System.out.println("Pooled Result: " + result);
    
    BigInteger sum = new BigInteger("1");
    start = clock.millis();
    for (int i = 0; i < test.getList().length; i++) {
        sum = sum.multiply(test.getList()[i]);
    }
    stop = clock.millis();
    System.out.println("Time in ms: " + (stop - start));
    System.out.println("Serial Result: " + sum);
  }
}
```

--- MakeBigIntArray
```
import java.math.BigInteger;

public class MakeBigIntArray {
  private final BigInteger[] list;

  public MakeBigIntArray(int bounds) {
    list = new BigInteger[bounds];
    for (int i = 0; i < bounds; i++) { 
      list[i] = new BigInteger("" + (i + 1));
    }
  }

  public BigInteger[] getList() {
      return list;
  }
}
```

--- RecursiveFactorial
```
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class RecursiveFactorial extends RecursiveAction {
  private BigInteger[] list;
  public BigInteger result;

  public RecursiveFactorial(BigInteger[] array) {
      this.list = array;
  }

  @Override
  protected void compute() {
      if (list.length == 1) {
          result = list[0];
      } else {
          int midpoint = list.length / 2;
          BigInteger[] l1 = Arrays.copyOfRange(list, 0, midpoint);
          BigInteger[] l2 = Arrays.copyOfRange(list, midpoint, list.length);
          RecursiveFactorial rf1 = new RecursiveFactorial(l1);
          RecursiveFactorial rf2 = new RecursiveFactorial(l2);
          
          // Fork s1 and s2
          rf1.fork();
          rf2.fork();
          
          // Join s1 and s2 and wait for them to finish
          rf1.join();
          rf2.join();
          
          // Compute the combined result
          result = rf1.result.multiply(rf2.result);
      }
  }
}
```

## Parallelism
So far, we’ve seen that parallelism is hard to come by. The biggest reason for this is the innate issue of making something “thread-safe.” If something is thread-safe, then multiple threads can interact with it without causing problems.

Unfortunately, a big problem exists when trying to enforce this concept of thread-safe. 
Collections are not thread-safe, which include Lists, Maps, and Sets.

Multiple threads can’t manipulate a collection without introducing thread interference or memory consistency errors. The former occurs when one thread overwrites the results of another thread in an unpredictable way, which can cause unexpected results when reading the altered data. The latter occurs when different threads have inconsistent views of what should be the same data.

And even if we introduce synchronization to make these collections thread-safe, doing so then also introduces thread contention. And thread contention directly contradicts parallelism due to it preventing threads from interacting with a resource while it’s being used by another thread.

So… how did Fork-Join get around this?

As you saw in the previous exercise, the Fork-Join framework was the first framework we’ve worked with so far that legitimately implements parallelism into its processing. The way it does this is through something called aggregate operations, which performs all the partitioning and combining of solutions during Java runtime.

We won’t get into exactly what aggregate operations are or how they work, but just know they’re one key way to implement parallelism. Another key way, which we will get into, is something called parallel streams.

Note: remember that parallelism doesn’t automatically result in faster performance than serial processing. while aggregate operations and parallel streams more easily implement parallelism, it is still the coder’s responsibility to determine if their application is even suitable for parallelism.

--- Parallelism
```
import java.util.*;
import java.util.stream.*;
  
public class Parallelism {
  public static void main(String args[]) {
    List<String> welcome = Arrays.asList("Welcome","to","streams!");
    welcome.stream().forEach(y->System.out.println(y));
  }
}
```


## Java Streams
To explain what parallel streams are, we need to first talk about what exactly a Java Stream is. Java Streams were introduced in Java 8, and are used to process a collection of objects which can be pipelined to produce a desired result.

A stream itself is not a data structure, it instead takes its input from 
collections, array, or other I/O channels. Additionally, streams don’t change the original data structure and only return results based on pipelined methods.

In a stream, intermediate operations are lazily executed and return results in the form of additional streams, which allow for the pipelining of those results. Terminal operations mark the end of the stream and return the final result.

An operation is lazy if its value is evaluated only when it is required. The reason why it does this is to save the processing of actual stream contents until the terminal operation commences. This enables the Java
compiler and runtime to optimize how they process streams.

Let’s go over some different types of operations.

Intermediate Operations:


* map : returns a stream consisting of the results after applying a given function to the elements of this stream
```
List numbers = Arrays.asList(1,2,3,4);
List square = numbers.stream().map(x->x*x).collect(Collectors.toList());
```

* filter: selects elements as per a Predicate passed as an argument
```
List names = Arrays.asList(“Alpha”,”Beta”,”Omega”);
List find = names.stream().filter(s->s.startsWith(“O”)).collect(Collectors.toList());
```

* sorted: sorts the stream
```
List names = Arrays.asList(“Omega”,”Alpha”,”Beta”);
List sorted = names.stream().sorted().collect(Collectors.toList());
```


Terminal Operations:

* collect: returns the result of the intermediate operations performed on the stream
```
List numbers = Arrays.asList(1,2,3,4,2);
Set squareSet = numbers.stream().map(x->x*x).collect(Collectors.toSet());
```

* forEach: iterates through every element of the stream
```
List numbers = Arrays.asList(1,2,3,4);
numbers.stream().map(x->x*x).forEach(y->System.out.println(y));
```

* reduce: reduces the elements of a stream to a single value (this takes a BinaryOperator as a parameter)
```
List numbers = Arrays.asList(1,2,3,4);
int even = numbers.stream().filter(x->x%2==0).reduce(0,(ans,i)->ans+i);
```

In the above example, ans is a variable that is assigned the value 0 initially, then i is added to it.

Remember, everything a stream does has no effect on the original value of the object initially passed to it.

--- Main
```
import java.util.*;
import java.util.stream.*;
  
public class Main {
  public static void main(String args[]) {  
    // Create a list of integers
    List<Integer> number = Arrays.asList(1,2,3,4);
  
    // Demonstration of map method
    List<Integer> square = number.stream().map(x -> x*x).collect(Collectors.toList());
    System.out.println("Squares: " + square);
  
    // Create a list of String
    List<String> names = Arrays.asList("Omega","Alpha","Beta");
  
    // Demonstration of filter method
    List<String> find = names.stream().filter(s->s.startsWith("O")).collect(Collectors.toList());
    System.out.println("Name starts with O: " + find);
  
    // Demonstration of sorted method
    List<String> sorted = names.stream().sorted().collect(Collectors.toList());
    System.out.println("Sorted names: " + sorted);
  
    // create a list of integers
    List<Integer> numbers = Arrays.asList(1,2,3,4,1,2);
  
    // Collect method returns a set
    Set<Integer> squareSet = numbers.stream().map(x->x*x).collect(Collectors.toSet());
    System.out.println("Square set: " + squareSet);
  
    // Demonstration of forEach method
    number.stream().map(x->x*x).forEach(y->System.out.println("  intermediate square: " + y));
  
    // Demonstration of reduce method
    int even = number.stream().filter(x->x%2==0).reduce(0,(ans,i)-> ans+i);
  
    System.out.println("Reduce evens: " + even);
  }
}
```

## Java Parallel Streams
Now that we know what Java Streams are and how they work, we can start to understand why they’re good for parallel processing. They ultimately don’t alter the original object passed to them, which helps with the thread-safe problem.

Additionally, Java normally has one stream for processing code, which uses sequential execution and is the default when performing normal threading.

Java Parallel Streams divide a running process into multiple streams that execute in parallel on separate cores, returning a combined result of all the individual outcomes. However, the order of execution is not under our control.

This means we want to try to only use parallel streams when the order of execution doesn’t matter.

To create a parallel stream, we need to invoke the operation Collection.parallelStream. Implementing this may look something like this:
```
File file = new File(filePath);
List<String> textLines = Files.readAllLines(file.toPath());
textLines.parallelStream().forEach(System.out::println);
```


Alternatively, we can also invoke the operation BaseStream.parallel. Implementing this may look something like this:
```
File file = new File(filePath);
Stream<String> textLines = Files.lines(file.toPath());
textLines.parallel().forEach(System.out::println);
textLines.close();
```
