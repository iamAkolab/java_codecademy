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
