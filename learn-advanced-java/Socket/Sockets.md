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
