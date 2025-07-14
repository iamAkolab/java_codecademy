# What is JNI?
This article provides a brief introduction to what JNI is, how it integrates Java with other coding languages (specifically C++), and when it may be beneficial to implement JNI into a program.

So far, we have learned concepts related to programming exclusively within the limitations set in Java. This article will show how Java is integrated with another coding language, specifically C++. JNI can be seen as the link between a compiled Java program and native languages. Native languages refer to coding languages that are close to assembly language and can be used to code on specific processors.

JNI stands for Java Native Interface. JNI is a powerful tool utilized especially to implement a coding language such as C++ that can overcome constraints in a Java program. For example, the Java language supports automatic memory allocation, but a programmer may encounter a situation in which they may choose to write a portion of their code in C++ so that they can control memory allocation for performance optimization of their program. In such a case, implementing a JNI would be helpful to the programmer because, unlike Java, native languages like C and C++ allow programmers to manipulate and manage memory allocation.

Some other reasons why a programmer may consider implementing native code into their Java program include:
* The standard Java libraries do not include certain methods that may be required to implement platform-specific features.
* You may already have features and libraries coded in another language that you would like to implement into a Java program without rewriting them in Java.

JNI is implemented on Java virtual machines (JVM). JVM is the platform on which Java code is loaded and executed. In order to run Java code with JNI, it is crucial that the Java program with JNI is correctly compiled and linked. JNI includes its own commands to assist in doing so. We will take a closer look at this in another article!

## Introduction
So far we have spent time focusing on Java as a standalone, powerful programming language. One of its greatest strengths is that it’s also able to integrate with other native programming languages through tools like JNI. JNI stands for Java Native Interface. It is a powerful tool that bridges together a Java program and native languages. Native languages are coding languages that are innately understood by the computer. Code written in a native language does not need to be altered for a processor in a computer to interpret it. Some examples of native languages include C and C++. Further along in this module, we will see how C++ code can be integrated into Java programs.

While implementing JNI may not always be the fastest or most cost-effective way to implement 
methods
Preview: Docs Loading link description
 in a program it has certain benefits that programmers may choose to apply. Some of these benefits include:

* Being able to avoid code redundancy by simply calling a native language function, instead of trying to rewrite it in Java.
* Being able to override Java’s dynamic abilities to determine things such as memory allocation manually.
* Being able to implement platform-specific features.
* Being able to access functionality that is outside the scope of the Java Standard Library.

For the rest of this exercise, we will learn more about the relevance of C++ to JNI, how to implement third-party APIs using JNI, and how to implement JNI within your own environments.

# Implementing JNI into Java
Now that we’ve understood JNI’s purpose, let’s look at how to implement JNI into Java code.

Firstly, when creating a method that is meant to be used by JNI, you will need to use the keyword native when declaring the method. Take a look at the following example:
```
private native void totalSum();
```
Notice how there is no implementation of totalSum() that is provided - there is no code block of commands that will tell a program what to do if the method is called. This is because the implementation of the method will be written in the native language that is being used on the other end of the JNI.

You may define passing arguments and return values the same way as any other method definition. For example in the following method definition:
```
public int void totalSum(double num1, double num2);
```
The method will return an int; num1 and num2 are parameters that will be passed into the method.

Another important element needed in the Java code is the following method: System.loadLibrary(). This method call is used on the Java side, usually within the main method, to load the dynamic library that holds the native code.

To implement a native method in native code, we must compile our Java program, and generate a .h file. To do this, you will run the following command in your compiler:
```
javac -h . FindSum.java
```
Running the following command will output a name file that will look like this:
 ```
FindSum.h
```

```
-- SubtractionFun.Java
public class SubtractionFun {
  static {
    System.loadLibrary("native");
  }

  private native int subtractValues(double num1, double num2);

  public static void main(String[] args) {
    double num1 = 3;
    double num2 = 1;
    int difference = new SubtractionFun().subtractValues(num1, num2);
  }
}
```

## C++ and its Relevance to JNI
Now that we have taken a look at the code required in Java, let’s shift gears and take a look at how C++ is integrated into JNI. C++ is an enhanced language that continues to hold many of the same attributes as the C language. Therefore, it can easily be implemented into a cross-platform application with Java using JNI.

We have already seen how a .h file is generated by compiling the Java program. Now let’s see how to code the native method into a C++ function by extending our example from the previous exercise.

First, we must create a .cpp file and make sure to include the generated header file using the following code:
```
#include <jni.h>
#include "FindSum.h"
```

Within the .h file, you will also find the definition of the native function which can be copied and pasted into your .cpp file. For our example it will look something like the following in your .h file:
```
JNIEXPORT jdouble JNICALL Java_FindSum_totalSum (JNIEnv *env, jobject thisObj, jdouble x, jdouble y) ;
```
Notice how when the Java compiler generated the .h file it added the prefix Java_FindSum_ to the function heading. There are also two additional parameters in the function heading. The first parameter, JNIEnv *env, points to the JVM and all the functions that are necessary to interact with it. The second parameter, jobject thisObj, points to the Java object that holds the method totalSum(). We will not be required to use these parameters for our simpler example. The next two parameters are of the type jdouble due to the mapping conventions for JNI. Take a look at the table below to see how other variable types may be mapped.

| Java	| JNI Type Conversion |
| ---- | ------------------- |
| int	 | jint  |
| boolean	| jboolean |
| Void	| Void |
| char	| jchar |
| byte |	jbyte |


 When copied over to your .cpp file and completed, the function may look like the following:
 ```
JNIEXPORT jdouble JNICALL Java_FindSum_totalSum (JNIEnv *env, jobject thisObj, jdouble x, jdouble y)
{
    double z = x * y;
    return z;
}
```

Now that we have looked at how to implement the Java and C++ sides, let’s put it all together!
```
--- SubtractionFun.Java
public class SubtractionFun {
  static {
    System.loadLibrary("native");
  }

  private native int subtractValues(double num1, double num2);

  public static void main(String[] args) {
    double num1 = 1;
    double num2 = 2;
    int difference = new SubtractionFun().subtractValues(num1, num2);
  }
}

--- SubtractionFun.h
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class SubtractionFun */

#ifndef _Included_SubtractionFun
#define _Included_SubtractionFun
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     SubtractionFun
 * Method:    subtractValues
 * Signature: (DD)I
 */
JNIEXPORT jint JNICALL Java_SubtractionFun_subtractValues
  (JNIEnv *, jobject, jdouble, jdouble);

#ifdef __cplusplus
}
#endif
#endif

--- SubtractionFun.cpp
#include <jni.h>
#include "SubtractionFun.h"

JNIEXPORT jint JNICALL Java_SubtractionFun_subtractValues (JNIEnv *, jobject obj, jdouble x, jdouble y){
    int difference = x-y;
    return difference;
  }
```
# Implementing JNI in Your Environment
JNI is a powerful and complex tool that extends the capabilities of a Java application. Its intricacy and many moving parts may seem intimidating, so we’ve broken down the basics of implementing JNI in your own environment into the following overarching steps:

1. Write your Java program. Make sure to use the keyword native when declaring any native methods. Here’s an example: public native void method();. Native methods don’t contain any code inside them because they are implemented in native code such as C or C++. You will also need to use the following code in your main method: System.loadLibrary().
2. Compile your Java program. To compile your code use the following command argument in your terminal: javac Program.java.
3. Use your Java compiler to generate .h files for the native code. Use the following argument in the terminal: javah Program. Alternatively you may use the following command: javac -h . Program.java to compile and generate headers.
4. Write your native code. Make sure to include all native methods generated through the .h file.
5. Compile your native code. The steps to compile native code are outlined in the table at the end of this article.
6. Run your executable code. The steps to run executables are outlined in the table at the end of this article.

Some main benefits of implementing JNI include programming to implement platform-specific features and functionality that is outside the scope of the Java Standard Library. Since JNI applications are platform-specific, running them looks different based on which operating system your computer is using. Before running a program on any computer, you can start by running the following command: echo $JAVA_HOME to confirm that the JAVA_HOME variable has been set. You will also require the following:

1. Java compiler: An example of a Java compiler includes calling the command javac _____ from the terminal.
2. Java Virtual Machine (JVM): A JVM is commonly pre-installed on a computer. It is the means by which a Java application can run on different operating systems.
3. Native header files: These are generated when compiling the Java program using the -h command.
4. Native method C generator: The native method in C++ is generated in the header file.
5. All library files that have been called in your program.

Now let’s take a look at specific steps based on operating systems that will help us run applications that use JNI in the table below.

| Operating System |	Steps |
| ---------------- | ----- |
| Windows	| 1. Compile the C++ file and create shared libraries using the following command: gcc -Wl,--kill-at -shared -IC:/JDK/include -IC:/JDK/include/win32 -IC:/dll -o C:/JNI_project/mynativelib.dll C:/JNI_project/jnitest.cpp |
| | 2. Run your program with the following command in your terminal: java -Djava.library.path=C:JNI_project FindSum |
| Linux	| 1. Compile the C++ file and create shared libraries using the following command: gcc -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux -I/home/user1/JNI_project/ -o /home/user1/JNI_project/mynativelib.so /home/user1/JNI_project/FindSum.cpp |
| | 2. Run your program with the following command in your terminal: java -Djava.library.path=/home/user1/JNI_project FindSum |

![cpp](https://github.com/iamAkolab/java_codecademy/blob/main/learn-advanced-java/JavaNativeInterface/cpp%20jni.jpg)

# Implementing third-party APIs
So far we have seen how to code our own C++ functions and link them to JNI in a program. However, sometimes you may find it useful to use functions already implemented into third-party APIs. Third-party APIs are application programming interfaces that are designed by third parties (often large corporations) to add functionality to your own applications. You may find a lot of examples, particularly within mobile development such as Android development, where third-party APIs are used to add services such as location, Google services, etc. to a phone’s operating system. Third-party API integration is a great way to save time by allowing programmers to reuse methods that have already been created instead of developing their own implementation to deliver similar, if not the same, functionality.

Third-party APIs can be linked to Java applications using JNI. To do that it is crucial that all third-party .dll, .jar, and .so 
files are placed in the native directory on the computer you are running your program on. It is also important that you include the path to the third-party files in the compilation of your program. Take a look at the following example of code to run in your terminal:

```
-Djava.library.path=pathname
```
where pathname refers to the path leading to the folder housing the dynamic library files.
