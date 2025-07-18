# What is Threading?
## Introduction
When you first start out programming, it’s likely that you are presented with sequential programs. These types of programs run instructions in a defined order, with a beginning and an end. Only one instruction is being performed at a time at any given moment.

For most applications, a primary requirement is that they be fast and efficient. To help meet this goal, we have to implement concurrent programs. These types of programs can support multiple flows of execution at the same time. This capability can help a programmer write code that completes two or more tasks at the same time!

Concurrency is a more advanced domain in programming, but modern programming languages have made it easier to get started with. Though there are different ways of achieving concurrency, the solution that is most commonly used in the application layer of a program is “threading.”

## What is a thread?
The building blocks of concurrent programs or processes are threads. Like sequential programs, a single thread has a beginning and an end, and only a single point of execution. However, multiple threads can run at the same time! A program or process usually has multiple threads running simultaneously, leveraging the context of the program, acting on shared resources between other threads, and ensuring that any one thread does not impede the other threads from completing a task.

![OperatingSystem](https://github.com/iamAkolab/java_codecademy/blob/main/learn-intermediate-java/operatingSystems.jpg)

In the diagram above, we can see that a process can start and execute multiple threads. By default, a process has a single thread of execution called the main thread. From that initial thread, we can create more threads to launch different independent tasks. We don’t consider the process to be complete until all of the threads that it has started are finished. When a thread completes its execution, we say that it joins back into the main thread.

## When to use threads
Suppose you’re buying some clothes in an online store. When you click a button to add a shirt to your cart, several tasks may need to be performed before it lands there. This might include:

* checking the store’s inventory for that shirt
* making a record in a database to reserve that shirt for you
* seeing if there are any active sales or promotions
* finding other recommended clothing based on your purchase
* and potentially even more tasks!

While all of these are being performed, you would still want to be able to browse the rest of the site, and maybe start your next purchase for a pair of socks too!

Without threading, you would have to wait for all of those tasks to be completed sequentially before being able to perform any other actions on the site. Using a multithreaded program, however, the online store can create and track independent tasks for each of the actions required to add the shirt to the cart, while still allowing you to browse the remainder of the store. Multi-threading has created a better online shopping experience for you!

As illustrated by the online store scenario, it is best to use threading when your program has tasks that need to be performed in parallel, and you do not wish for your program’s main thread to become unresponsive or unusable while the tasks are being performed. When a program has the ability to perform more tasks in less time, we say that threading has increased its throughput.

## Context of a thread
For a program to execute properly, it needs to have several things in its context. A program’s context includes variables, function names, a history of function calls (the “call stack”), and more. A thread that runs within a program has its own context so that it can run independently without impacting other threads.

Sometimes, we have to share some information (a context) between threads as well. For example, in the online store example, each of the threads for the inventory-check task, the recommender task, and the reservation task need to know the product ID number for the shirt. Since all of the threads are launched from the same main thread (starting from when you clicked the “Add to Cart” button), they all have access to the product ID that was present in that main thread’s context.

When reading shared information in a multi-threaded program, there’s usually nothing to worry about in terms of managing shared state. However, when multiple threads are writing to the same resource, and manipulating its state, we can run into issues where multiple threads are changing a value based on a potentially out-of-date previous state. The solution to this issue is called thread synchronization. Different programming languages implement synchronization in different ways, and we will learn more about how programs can use synchronization to solve issues with managing shared state.

## Conclusion
The multi-threaded architecture and paradigm for programs is one of the most fundamental patterns in programming. Most non-trivial applications use multiple threads to ensure that the tasks that they need to run are fast and efficient, and leverage the resources of the computer that they’re running on in the most effective way. Get started with implementing some multi-threaded programs to see how it all comes together!

# Virtual Threading
## Introduction
In JDK 19, Java introduced the concept of virtual threads to be used in applications requiring concurrency. Traditionally, writing multithreading applications relied on the use of an inefficient platform thread. Virtual threads, built upon platform threads, are a significant improvement in terms of efficiency and resource allocation.

Multithreading is unavoidable! Almost every application in modern times requires some amount of multithreading as it likely relies on IO (input/output - think keyboard, mouse, a display, and any other type of device intended for human interaction), network calls (requesting information from the web), and in some cases, complex mathematical computations. Therefore, it is crucial to have an efficient way to implement multithreading.

In this article, we will explore the concept of a virtual thread, why it is more efficient than a traditional platform thread, how to share data between threads, and how to implement structured concurrency using a virtual thread.

## The Problem with Platform Threads
A platform thread, the traditional way of implementing multithreading, is inefficient because it is implemented as a thin wrapper around an OS thread. To create a thread, an operating system must allocate roughly 20MB of RAM and 1 ms of CPU processing time. Therefore, a Java program requiring many threads running concurrently will place a lot of strain on the resources of the computer it is running on. Consider the following code that runs 10,000 separate tasks implemented using platform threads:
```
public class SoManyThreads { 
  public static void main(String[] args) { 
    int numberOfThreads = 10000; 
    for (int i = 0; i < numberOfThreads; i++) { 
      Thread thread = new Thread(new Runnable() { 
        @Override 
        public void run() { 
          System.out.println(Thread.currentThread().threadId() + " is running"); 
        } 
      }); 
    thread.start(); 
    } 
  } 
} 
```
Running this many concurrent tasks using platform threads is incredibly resource-intensive and this program is likely to crash as the operating system may not be able to provide the requisite resources for 10,000 threads.

Note: This sample of code is intended to stand for a server handling 10,000 network requests. A real server must likely handle more than 10,000 network requests. This style of reserving a thread for every task is called a thread-per-request style. 

Virtual Threads
A thread that is used for a network or IO call will spend most of its time idle as it awaits the response from the network or IO device. The concept of a virtual thread is built upon the exploitation of this fact. To improve efficiency, instead of reserving one thread for each separate task, several tasks are grouped and executed on one platform thread.

A task is mounted on a thread when it is ready to execute its function and is unmounted when it is awaiting an external response. When a task is unmounted, the resources are released and allocated to another task that is ready to finish executing. With this mechanism, every task behaves as if it were its own thread, hence the name virtual thread. The Java Virtual Machine handles the grouping, mounting, and unmounting of the tasks on a platform thread.

In Java, virtual threads belong to the Thread class and are used like so:
```
Thread.ofVirtual().start(Runnable someThread);
```

Since virtual threads belong to the Thread class, we can use join() to block a parent thread from exiting until a child thread has completed executing as we would for a traditional platform thread.

Here is the code for running 10,000 threads using virtual threads instead of platform threads:
```
public class SoManyThreads { 
  public static void main(String[] args) { 
    int numberOfThreads = 10000; 
    for (int i = 0; i < numberOfThreads; i++) { 
      Thread thread = Thread.ofVirtual().start(new Runnable() { 
        @Override 
        public void run() { 
          System.out.println(Thread.currentThread().threadId() + " is running"); 
        } 
      }); 
    } 
  } 
} 
```
These threads will run efficiently. However, since many virtual threads run on one platform thread, Thread.currentThread().threadId() will print out the ID of the virtual thread’s parent platform thread. The previous code created virtual threads and ran them immediately because of the call to start(). To create virtual threads now and start them later, we do that like so:
```
public class SoManyThreads { 
  public static void main(String[] args) { 
    int numberOfThreads = 10000; 
    for (int i = 0; i < numberOfThreads; i++) { 
      Thread thread = Thread.ofVirtual().unstarted(new Runnable() { 
        @Override 
        public void run() { 
          System.out.println(Thread.currentThread().threadId() + " is running"); 
        } 
      }); 
    } 
  } 
} 
```
We can start running all of the threads by calling the start() method like so:
```
thread.start();
```

## Using Structured Concurrency with Virtual Threads
Concurrency is the technique of executing relatively independent tasks in parallel to save run time. In Java, concurrency is implemented using the ExecutorService class. Consider the following example that represents a server for an online shopping store handling requests to fetch information about an order of a particular user:
```
public class ConcurrencyExample { 
  public static void main(String[] args) { 
    ExecutorService executorService = Executors.newFixedThreadPool(3); 
    // We can assume the fetch functions have the relevant information of a user. 
    Future<String> userName = executorService.submit(fetchUserName()); 
    Future<String> userID = executorService.submit(fetchUserID()); 
    Future<String> orderNumber = executorService.submit(fetchOrderNumber()); 
    executorService.shutdown(); 
    // Get results from the completed tasks 
    try { 
      String result1 = userName.get(); 
      String result2 = userID.get(); 
      String result3 = orderNumber.get(); 
    } catch (InterruptedException | ExecutionException e) { 
      e.printStackTrace(); 
    } 
  } 
} 
```
This is a form of unstructured concurrency. Fetching a user’s username, ID, and order number can be independent operations. However, the following problem exists: if, for some reason, the user does not have a registered userName (meaning they are not registered on the site), the tasks of fetchID() and fetchOrder() will continue to execute in search of information for a non-existent user thereby wasting time and other resources. This inherent problem of unstructured concurrency leads to code that is hard to maintain and debug. To fix this problem, it is preferable to use structured concurrency instead. Structured concurrency combines the benefits of a consecutive code (if one step fails, the whole code fails) with the time-saving benefits of running concurrent code.

Virtual threads are excellent candidates for running structured concurrency as they can run several tasks on one platform thread. Virtual threads are used for structured concurrency by using the following Java code:
```
Executors.newVirtualThreadPerTaskExecutor() 
```
Here is the previous code refactored to use structured concurrency via virtual threads:
```
public class ConcurrencyExample{ 
  public static void main(String[] args) { 
    try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) { 
      // We can assume the fetch functions have the relevant information of a user 
      Future<String> userName = executorService.submit(fetchUserName()); 
      Future<String> userID = executorService.submit(fetchUserID()); 
      Future<String> orderNumber = executorService.submit(fetchOrderNumber()); 
    } 
  } 
} 
```
## Sharing Data using Scoped Values
While tasks are running, there may be a case where data between threads must be shared. In concurrent programming, the way to do this was to use a ThreadLocal variable. A ThreadLocal variable is a public static variable that belongs to the class, but a separate instance of which is created in each thread. The problem with a ThreadLocal variable used in virtual threads is that because virtual threads allow the creation of many threads, there will consequently be many instances of a ThreadLocal variable, rendering them too unmanageable due to their amount. To solve this problem, it is preferable to use a ScopedValue variable instead. A ScopedValue variable belongs to the class and does not allow the creation of multiple instances of itself.

To use a ScopedValue variable, syntax similar to the following should be added to any code using virtual threads:
```
private static final ScopedValue<String> someString = ScopedValue.newInstance(); 
ScopedValue.where(someString, "StringValue", virtualThread); 
```

## Advantages and Disadvantages of Virtual Threads
Virtual threads have proven to be helpful in applications where several tasks are slated to run concurrently, and each task awaits some form of external response, such as IO or a network call. However, there are situations in which a virtual thread cannot be used. In the case where a thread must run some form of complex calculation, it does not make sense to use a virtual thread because the task will not be idle as the CPU is under constant use. When this is the case, there is no other choice but to rely on a standard platform thread. A second scenario when a virtual thread can’t be used is when a task executes a synchronized block of code. A synchronized block of code prevents other threads from accessing a specific piece of data in the interest of protecting it from being incorrectly processed. When a virtual thread executes synchronized code, the virtual thread cannot be unmounted until the block of code is finished executing, even if the thread is idle. When this happens, we say the virtual thread is pinned to the parent platform thread. When this is the case, we lose the advantages we gain from unmounting an idle thread. In this case, we again are forced to use standard platform threads.

## Conclusion
As we have seen, virtual threads, in some cases, offer a great advantage in multithreaded programming over the traditional platform thread. By taking advantage of the idleness of some tasks as they await the response of an external resource, we can temporarily reallocate resources to other tasks, thereby allowing us to run multiple tasks on one single thread. This style of programming greatly improves concurrent programming and places less strain on computational resources. While this is a great improvement in multithreading, it is by no means a complete replacement for traditional platform threads.
