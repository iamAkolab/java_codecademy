# What Are Java Servlets?
## Introduction
When we go to a website like codecademy.com, we often see dynamically loaded or personalized, content based on a variety of factors like:

* A user’s access level.
* A user’s interactions with the web page.
* The time of day.
How does a web page know what data to dynamically load? To understand this, we need to dive deeper into what happens behind the scenes when we first type and go to a website like codecademy.com in our browser.

## Making Requests
Let’s start by defining some key terms in the example:

* Client - A tool (computer or software) used to request data from a server.
* Server - A tool (computer or software) used to respond to client requests.
* Request/Response - The communication model used by computers to communicate over a network.

When we type in a website into our browser and hit enter, our browser (or client) makes a request (asks for the webpage at codecademy.com) to the server. The server performs the necessary steps (like pulling records from a database) needed to fulfill the request and sends it back to the client in the response. This process is known as the client-server architecture.

Now, where do servlets fit in this picture? Let’s get into that.

## Servlets
A Java servlet is a Java class whose sole purpose is to receive a network request and response object (typically HTTP) and construct the necessary response. A servlet allows us to create dynamic responses (like a dynamic web page) based on a request sent by a client. Servlets make it easy for us to create web applications incorporated with all the benefits of Java, like its portability and security.

![icecream](https://github.com/iamAkolab/java_codecademy/blob/main/learn-advanced-java/Servlets/Ice%20cream.jpg)

In the example, there is a customer placing an order for ice cream on their smartphone. The customer can pick their flavor of ice cream and the size of the cup or cone. Once the order is placed, the order taker sends the order to the ice cream factory worker. We can map this process using the client-server architecture like:

* The client is the user’s smartphone.
* The request is the ice cream order sent to the ice cream shop.
* The ice cream shop order taker receives the order (request) and passes it on to the ice cream factory worker who can fill this request. The servlet would be the worker who takes the ice cream order (request), creates the ice cream, and sends it back (response) to the customer.

Notice that the servlet lives inside the server/servlet container (we’ll learn more about this later) and its sole job is to receive a request it understands and build the appropriate response.
