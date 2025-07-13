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

# Setting Up Our Environment for Servlets
## Introduction
For us to start working with Java servlets and make HTTP requests to them we’ll need a web server (to take requests and serve responses) and a servlet container (to run our servlets in). Thankfully, Tomcat (free and open-source), gives us both a web server and a servlet container. Through the rest of this article, we’ll get our development environment set up with Tomcat.

## Prerequisites
Before installing Tomcat, we’ll first need to ensure we have a text editor and Java 8 installed. We could also use an IDE, but some IDEs, like IntelliJ, require a paid license in order to develop Java enterprise applications. You can find related articles to help install any missing software using the following links:

* Text editor - overview
* Java 8 - overview
* Eclipse IDE for Enterprise Java and Web Developers (make sure to choose the download with this exact name) - download, overview
We’ll also need to download the starter code .zip from the following link. Be sure to extract the contents somewhere you can easily find as we’ll get more into this directory in future articles.

icecream-app starter project https://static-assets.codecademy.com/Courses/learn-advanced-java/servlets/starter-icecream-app.zip

## Installing Tomcat
Installing Tomcat9 will be slightly different depending on our operating system (OS). Regardless of our OS, we’ll download Tomcat9 through Apache’s Tomcat page tomcat download. Note that

### Windows
When downloading Tomcat9 on Windows we’ll need to pick the appropriate installation based on whether we are on a 32 or 64-bit system.

Once the download is complete we’ll need to find the Tomcat .zip in our Downloads/ directory and extract the contents to an accessible directory (like the Documents/ directory).

Finally, before testing our Tomcat installation we need to set our JAVA_HOME environment variable. This environment variable should be set to the path of our Java 8 installation.

We can start up our Tomcat9 server by opening the CMD, navigating to the Tomcat bin/ directory, and executing the startup.bat script. We should see a new Tomcat window pop up displaying log messages and eventually stating that the Tomcat server has started.

This will open up a new Tomcat Window that looks like the following:
We can now confirm our Tomcat installation by opening our browser and navigating to http://localhost:8080 and seeing the Tomcat welcome page.

### OS X
To download Tomcat9 on a Mac environment, we’ll need to choose the .tar.gz installation.

Once downloaded, we’ll need to extract the .tar.gz Tomcat file in an accessible directory (like the Documents/ directory), set JAVA_HOME to point to the path of our Java installation, and test our Tomcat installation. In the following example, we see how to complete all these steps using the terminal. We won’t go in-depth on each command being used but if you’d like to understand them more you can use the man command like man cd or man tar.

Note when using nano (terminal text editor), when we’re done defining JAVA_HOME we save and exit the file by using control x and entering y to the “Save modified buffer?” and enter on “File Name to Write:”.

### Linux
To download Tomcat9 on a Linux environment, we’ll need to choose the .tar.gz installation.

Once downloaded, we’ll need to extract the .tar.gz Tomcat file in an accessible directory (like the Documents/ directory), set JAVA_HOME to point to the path of our Java installation, and test our Tomcat installation. In the following example, we see how to complete all these steps using the terminal. We won’t go in-depth on each command being used but if you’d like to understand them more you can use the man command like man cd or man tar.

Note when using nano (terminal text editor), when we’re done defining JAVA_HOME we save and exit the file by using control x and entering y to the “Save modified buffer?” and enter on “File Name to Write:”.

Verifying Tomcat
We can verify that Tomcat was installed successfully by opening our browser and navigating to http://localhost:8080. Note that:

localhost - tells our browser to go to the server on the computer we’re currently on.
:8080 - specifies which port on our local server we’d like to hit. (8080 is where Tomcat auto deploys to). If Tomcat was installed successfully we should see the Tomcat welcome page as follows:


## The Tomcat Directory
Before we begin using Tomcat, let’s understand the folder structure in our Tomcat installation. In Tomcat we’ll see the following folders:

* bin/ - Usefull Tomcat scripts and files. Includes the startup and shutdown scripts used to start and stop Tomcat.
* conf/ - Configuration (.xml) files for the Tomcat server like server.xml which can be used to modify the port Tomcat deploys to.
* lib/ - JAR files (like servlet-api.jar) required by Tomcat to run.
* logs/ - Tomcat-generated log files are kept here
* webapps/ - Deployed applications are kept here. We can place our project directory here or convert it into a .war, which Tomcat will unzip.

# Creating a Java Servlet
## Introduction
In this article, we’ll cover how to create a Java servlet to respond to a user request for the home page of an ice cream shop. We’ll learn how to:

* Extend HttpServlet.
* Create an HTTP response.
* Define web.xml.
* Deploy the servlet locally on Tomcat.
  
Before we begin, let’s place the provided starter project, icecream-app/, in Tomcat. Locate where you extracted the starter project and copy the icecream-app/ directory into the webapps/ directory of Tomcat.

We’ll use the ice cream application project to build a simple web application with a home page for our ice cream shop and a form for our customers to order ice cream using Java servlets.
