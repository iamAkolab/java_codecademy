# What is Threading?
## Introduction
When you first start out programming, it’s likely that you are presented with sequential programs. These types of programs run instructions in a defined order, with a beginning and an end. Only one instruction is being performed at a time at any given moment.

For most applications, a primary requirement is that they be fast and efficient. To help meet this goal, we have to implement concurrent programs. These types of programs can support multiple flows of execution at the same time. This capability can help a programmer write code that completes two or more tasks at the same time!

Concurrency is a more advanced domain in programming, but modern programming languages have made it easier to get started with. Though there are different ways of achieving concurrency, the solution that is most commonly used in the application layer of a program is “threading.”

## What is a thread?
The building blocks of concurrent programs or processes are threads. Like sequential programs, a single thread has a beginning and an end, and only a single point of execution. However, multiple threads can run at the same time! A program or process usually has multiple threads running simultaneously, leveraging the context of the program, acting on shared resources between other threads, and ensuring that any one thread does not impede the other threads from completing a task.

![OperatingSystem]()

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
