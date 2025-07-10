# What is Parallel and Concurrent Programming?

The various ways to add concurrency and parallelism to your programs can be tricky and difficult to differentiate. Implementing one may not actually be implementing the other, and vice versa. Let’s discuss the vital differences between these concepts.

Humans can multitask. We can talk and drive, listen to music and read, watch an animation and eat, or all of the above at the same time if you play video games.

We, as a race, have proven time and time again that we can in fact multitask. We can focus our minds and operate on such an efficient level that even complex tasks become autonomous, allowing us to adopt additional tasks to juggle our ever-evolving mental goals.

Even a task as simple as opening a door involves a complicated series of sub-tasks that happen simultaneously. For instance, understanding a door is a door, stepping toward the door, knowing the door can even be opened, finding the doorknob, turning the doorknob, and applying enough force to open the door in the proper direction.

That’s not even half of it and yet we’re able to perform every listed subtask with a mere thought and autonomous action. When we consider the fact we can also hold a cup of coffee, maintain a conversation with someone, plan our next walking path, and avoid obstacles while simultaneously opening this door is an incredible display of human ability.

By all definitions, humans can and have always been able to “multitask,” right?

Well, technically… no. Every claim made above is actually a misrepresentation and humans can’t, by any definition, truly “multitask.”

But to explain why this is, we need to talk about concurrent and parallel programming. Or more specifically, we need to discuss the differences between concurrency and parallelism.

# Concurrent Programming
Concurrency is the act of processing more than one task at seemingly the same time on the same CPU, requiring the ability to switch between tasks. This means the actual processing of these tasks can’t happen at exactly the same time, only seemingly.

This is what humans do. Humans can’t multitask, we’re just really good at switching tasks so fast and effortlessly that we appear to be processing more than one task simultaneously. This is concurrency, but not parallelism.

# Parallel Programming
Parallelism is the act of splitting tasks into smaller subtasks and processing those subtasks in parallel, for instance across multiple CPUs at the exact same time. True parallelism happens when each thread processing a designated subtask runs on a separate CPU / CPU core / GPU core.

An important distinction to note as well is the difference between parallel execution and parallelism.

Parallel execution is the act of processing more than one task simultaneously across more than one CPU, but with the distinction that the tasks being processed stay on their designated CPU and never leave it.

This means that a program can perform concurrent parallel execution, but not implement parallelism.

To do this, multiple CPUs are used and each CPU is processing more than one task, but each individual task remains and eventually fully processes on the CPU that started it.

An easy-to-remember distinction between concurrency and parallelism looks like this: “Concurrency is about dealing with lots of things at once. Parallelism is about doing lots of things at once.”

Or in other words, concurrency is about “structure” and parallelism is about “execution.”

## Concurrency and Parallelism Combos:
Concurrency without Parallelism
More than one task is processed at seemingly the same time (concurrently), but in actuality, the application is switching between making progress on each task until the tasks are done.

There is no true parallel execution in parallel threads or CPUs.

## Parallelism without Concurrency
Only one task is worked on at a time, but that task is broken down into subtasks that are processed in parallel. This only works if each task, and its subtasks, completes before the next task is split and executed in parallel.

Neither Concurrent nor Parallel
Only one task is worked on at a time, and the task is never broken down into subtasks for parallel execution.

## Concurrent and Parallel
There are two ways to do this:

* Application starts multiple threads which are then executed on multiple CPUs
* Application both works on multiple tasks concurrently and also breaks each task down into subtasks for parallel execution. However, some of the benefits unique to each case may be lost in this scenario.

## Conclusion
Humans can’t multitask. We’ve proven this by discussing the differences between concurrent and parallel programming, and we’ve seen that we’re just really good at switching tasks very quickly.

However, applying what we’ve learned about parallelism, we can extrapolate that two humans can simultaneously work on the same task and operate in parallel. An easy example of this can be found in any typical group project.

The task, to get the project done, is broken up into subtasks that are assigned to the individuals comprising the group. This means these subtasks are processed in parallel by separate individuals, or metaphorical CPUs / threads, simultaneously. And because these subtasks are all processing the same primary task, this is an example of parallelism. If we wanted to sprinkle in some concurrency, we can assign some members of this group more than one subtask to switch between.

This analogy can delve further and further into the realm of parallel and concurrent programming simply by broadening the scope of the project.

For example, say the group we’ve been talking about is only one group, and other groups exist that are also working on their own projects and subdividing their projects into subtasks. We can also say that all projects are acting toward the completion of one large business venture, or in other words one large task.

This thought exercise only further reinforces the importance of parallel and concurrent programming and helps differentiate the two conceptually. But, if we’re capable of doing projects like this…

Then… humans can, by this particular definition, multitask.

They just have to do it together.
