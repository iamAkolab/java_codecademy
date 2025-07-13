# What are Sockets in Java?
In this article, we are going to learn about socket programming in Java. To do this, we will:

* Review client-server architecture
* Introduce the definition of a socket
* Go over Transmission Control Protocol (TCP) To start this journey, let’s talk about client-server architecture.

## Client-server Architecture
If you went through the servlets in Java content before this, this section will be a bit of a review. Feel free to skip to the next section if this is repetitive. Let’s start by defining some key terms:

* Client - A tool (computer or software) used to request data from a server.
* Server - A tool (computer or software) used to respond to client requests.
* Request/Response - Communication model used by a computer to communicate over a network.

When we type in a website into our browser and hit enter, our browser (or client) makes a request to the server. The server performs the necessary steps (like pulling records from a database) needed to fulfill the request and sends it back to the client in a response. This process is known as the client-server architecture.

![Server](https://github.com/iamAkolab/java_codecademy/blob/main/learn-advanced-java/Socket/Server.jpg)

## Transmission Control Protocol
Before diving into what sockets are, we have one more concept to go over: transport protocols for HTTP response/requests. The one used by sockets in Java is called Transmission Control Protocol (TCP)

TCP is a communication protocol that enables applications to exchange messages over a network and ensure the successful delivery of exchanged data packets. Due to its reliability, TCP is the favored protocol for many types of common applications.
![TCP](https://github.com/iamAkolab/java_codecademy/blob/main/learn-advanced-java/Socket/TCP.jpg)

## Introducing Sockets
A socket is an endpoint that essentially allows us to connect the client and server using transmission control protocol. The TCP layer can identify where the data should be sent to since a socket is tied to a specific port number. The combination of the IP address and port number is the endpoint.

So, why is this important? Sockets essentially allow one computer to communicate with many different clients at once!

## Conclusion
Great job learning about what sockets in Java are and how they in the client-server architecture. As we continue with the unit we’ll learn how to build a socket program from scratch to fulfill different types of tasks and get a more in-depth look at the power behind sockets.

# Socket Programming in Java
In this article, we are going to create a simple socket program in Java where the client sends a text and the server receives and prints it. This article will be split into the following sections:

* Writing the server side of the program
* Writing the client side of the program
* Running the program

Follow along off-platform, so you can understand the code and see the magic happen in real-time! Download the starter files here https://static-assets.codecademy.com/Courses/learn-advanced-java/sockets/first-socket.zip.

## Server Side
Within the starter folder, open FirstServer.java. You should see the following code:
```
import java.io.*;
import java.net.*;

public class FirstServer {
  public static void main(String[] args) {
    
    // your code here

  }
}
```

All of our code should sit in the part that says // your code here. Let’s get started!

The first thing we have to do is create a ServerSocket which allows us to implement a socket on the server side. Use the following line of code to do this:
```
ServerSocket firstServerSocket = new ServerSocket(6868);
```
Here, we have created a ServerSocket named firstServerSocket with port number 6868. Remember that the port number acts like the id for a socket, meaning that the port number on the client side will need to match the port number on the server side.

To establish the connection, we need the following line of code:
```
Socket socketConnection = firstServerSocket.accept()
```
The .accept() method allows the ServerSocket class to take in incoming messages.

Next we need to set up the logic so that the program can catch the message. To do this, we need to use DataInputStream. Let’s look at the following line of code:
```
DataInputStream dataStreamIn = new DataInputStream(socketConnection.getInputStream());
```
Here we are creating an input stream called dataStreamIn. This code will allow the program to grab the data being streamed within the socket connection between the client and server.

Next, we need to read the data caught by dataStreamIn using the .readUTF() method:
```
String readString = (String)dataStreamIn.readUTF();
```
Finally, we need to close the socket connection:
```
firstServerSocket.close();
```
It can also be helpful to wrap your code in a try/catch exception like the following one:
```
 try { 

\\ server code here

} catch(Exception e){System.out.println(e);}
```
If you want to check your code, click the dropdown below:

## Final Server Code
```
import java.io.*;
import java.net.*;

public class FirstServer {
  public static void main(String[] args) {
    try { 
      ServerSocket firstServerSocket = new ServerSocket(6868);
      Socket socketConnection = firstServerSocket.accept();//establishes connection 

      DataInputStream dataStreamIn = new DataInputStream(socketConnection.getInputStream());

      String readString = (String)dataStreamIn.readUTF();
      System.out.println("message = " + readString);

      firstServerSocket.close();

    } catch(Exception e){System.out.println(e);}
  }
}
```
Now that we have our server side code set up, let’s work on the client side of the program.

## Client Side
The client side code will look very similar to the server side. Open up the starter code for FirstClient.java. You should see the following code:
```
import java.io.*;
import java.net.*;

public class FirstClient {
  public static void main(String[] args) {

    \\ your code here

  }
}
```
All of our code should sit in the part that says // your code here. Let’s get started!

The first step is to create a socket on the client side:
```
Socket clientSocket = new Socket("localhost",6868);
```
Make sure this socket has the same port number as the socket on the server side.

Next, we need to create a DataOutputStream variable. Just as we needed a DataInputStream variable to read the data on the server side, we need a DataOutputStream variable to send the data from the client side.
```
DataOutputStream dataStreamOut = new DataOutputStream(clientSocket.getOutputStream());
```
We then use the .writeUTF() method to write our message:
```
dataStreamOut.writeUTF("Happy Coding!");
```
We made our message "Happy Coding!", but feel free to make your message whatever you want it to be!

Next, we need to use the .flush() method from the DataOutputStream class. From Java’s documentation, .flush() does the following:
Flushes this output stream and forces any buffered output bytes to be written out. The general contract of flush is that calling it is an indication that, if any bytes previously written have been buffered by the implementation of the output stream, such bytes should immediately be written to their intended destination.

```
dataStreamOut.flush();
```
Finally, we need to close dataStreamOut and clientSocket:
```
dataStreamOut.close();
clientSocket.close();
```
Just like for the server code, it can also be helpful to wrap your code in a try/catch exception like the following one:
```
 try { 

\\ client code here

} catch(Exception e){System.out.println(e);}
```
If you want to check your code, click the dropdown below:
### Final Client Code
```
import java.io.*;
import java.net.*;

public class FirstClient {
  public static void main(String[] args) {
    try {   
      Socket clientSocket=new Socket("localhost",6868);
    
      DataOutputStream dataStreamOut = new DataOutputStream(clientSocket.getOutputStream());

      dataStreamOut.writeUTF("Happy Coding!");
      dataStreamOut.flush();

      dataStreamOut.close();
      clientSocket.close();

    } catch(Exception e){System.out.println(e);}
  }
}
```

### Running the Program
Now that our code is all set up, let’s test our program. To do this, open two separate terminal/command prompt windows.

In one window, move to the correct directory and run the following commands back-to-back:
```
javac FirstServer.java
java FirstServer
```
In the separate terminal, run the following commands back-to-back:
```
javac FirstClient.java
java FirstClient
```
In the first terminal you should see the following output (or unique message you setup):
```
message= Happy Coding!
```
You now have a fully functioning Java socket program! Happy coding!
