# Threading Refresher

Back in Intermediate Java, we learned how to do some Threading. Let’s do a quick refresher!

In Java, threads are part of a program that follow their paths of execution independently. They can improve performance for processes that can be split into code that can run concurrently.

There are two main ways to create threads:

* Extending the Thread class
* Implementing the Runnable interface

## Extending Thread
Thread is a built-in class that can handle threading, imported through java.lang.Thread. The class that extends Thread will become threadable and can then override the built-in .run() method found in the parent class.

Using this approach also comes with the ability to .start() and .sleep() the created threads.

For example, let’s say we have a custom Factorial class that extends Thread. To use this class like a thread, we can do the following:
```
Factorial f = new Factorial(25);
Factorial g = new Factorial(10);
f.start();
g.start();
```

## Implementing Runnable
This is often the preferred method over extending Thread because it allows you to extend other parent classes so long as you still include the necessary .run() method from Runnable. It’s also more intuitive to implement instead of extending because we’re not trying to extend the functionality of threads here, we’re just trying to use the functionality of threads.

Since the class itself is no longer technically a Thread, we can’t use .start() or other thread functionality on it like we can in the previous approach. However, when creating threads, we can actually pass an object of type Runnable to the Thread class which will then be used by that thread.

Doing this grants us access to all Thread functionality without extending from Thread because we’re simply using the actual Thread class itself.

For example, let’s say our previous custom Factorial class implements Runnable now. To use this new class like a thread, we can do the following:
```
Factorial f = new Factorial(25);
Factorial g = new Factorial(10);
Thread t1 = Thread(f);
Thread t2 = Thread(g);
t1.start();
t2.start();
```

## Interacting with Threads
In either of the previous approaches, using the Thread class grants us several means to interact with newly created and actively running threads.

We’ve already covered in this article how we can use .start() to start the processing of a created thread, but there are many more things we can do.

We can use:

* .sleep() to pause a thread’s processing for a set amount of time
* .wait() to pause a thread’s processing until a condition is met
* .isAlive() to check if a thread is still processing or if it’s finished
* .join() to wait for a thread to finish and join back into the main thread
* .notifyAll() to unpause all waiting threads

## Managing Shared Resources
When running threads access resources, it’s important to know how to control how many threads can be interacting with and altering a resource at any given time. A program needs to account for this to prevent race conditions and other unpredictable situations that could arise.

The quickest way to achieve resources that are thread-safe is to use the synchronized keyword. However, also note that nonblocking algorithms can be used to avoid too much synchronization.

When using synchronized, Java will ensure the given instance of that object will only be accessible by one thread at a time. An entire class, method, or variable object can all be synchronized by simply adding the keyword to the object declaration.

For example:
```
// Class
synchronized(this);

// Method
synchronized void method(int n) {
  // Method body
}

// Variable object
private final Object lock = new Object();
synchronized(lock) {
  // Lock object body
}
```

## Conclusion
Working with threads is a straightforward process. We can create them by either extending Thread itself or implementing Runnable and passing the runnable into the Thread class directly. In either case, we gain the ability to interact with threads before and during their execution, which gives us a lot of control over all the running processes.

To avoid issues involving race conditions, we can utilize the synchronized keyword or utilize a nonblocking algorithm if we’re trying to avoid the locking of resources.

Threading can solve many problems, especially advanced ones, but always remember that just because something can be threaded doesn’t mean it should! It’s up to you, the programmer, to figure out if threading can optimize a process or if other approaches should be considered.
