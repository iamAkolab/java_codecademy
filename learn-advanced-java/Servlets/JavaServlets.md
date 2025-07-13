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

## Understanding the icecream-app
Let’s go through what the icecream-app/ directory looks like and what type of files we expect in each subdirectory. When you first open the icecream-app/ directory, you’ll notice:

* WEB-INF/ directory.
* HTML file(s).

The WEB-INF/ folder contains everything needed to run our web application and consists of the following subdirectories and files:
* src/ - Contains java source (.java) files.
* classes/ - Contains Java bytecode (.class) files.
* lib/ - Contains JARs needed by our web application.
* web.xml - An XML deployment descriptor file needed by Tomcat to deploy our servlets. We can define things like servlets, URL mappings, and more.

We now can get started creating our first servlet.

#Creating a Servlet
Java makes creating a servlet easy by providing the GenericServlet and HttpServlet classes which we can extend to create a custom servlet. Using a protocol-specific subclass (like HttpServlet) is recommended to create our servlets. We’ll start by creating a homepage for our ice cream web application.

In our text editor, let’s create a new file called HomeServlet.java and save it in the src/ directory of our WEB-INF/ directory. We’ll need to add some imports to our file in order to work with servlets, so let’s add the following imports to the top of our file:
```
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
```
Now let’s define a class called HomeServlet that extends that HttpServlet class we’ve imported and a private static final serialVersionUID.
```
public class HomeServlet extends HttpServlet {
  private static final long serialVersionUID = 100L;  // Used for serialization since `HttpServlet` implements `Serializable`. 
}
```
We’d like our HomeServlet to serve us an HTML page when we go to our URL in our browser. Recall that our browser will make a request to the server, and the server will process the request and return a response to the client. To accomplish this, we’ll need to implement the doGet() method of HttpServlet like:
```
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  PrintWriter out = response.getWriter();
  out.println("<html>");
  out.println("<head><title>My Icecream Shop!</title></head>");
  out.println("<body>");
  out.println("<h1>Welcome To My Ice Cream Parlor</h1>");
  out.println("</body></html>");
}
```
Note that our implementation of doGet():
* Takes the HTTP request and response objects as parameters.
* Throws the ServletException (servlet was not able to handle GET request) and IOException (an input or output error occurred).
* Uses PrintWriter to create a simple HTML page with a header welcoming our visitors.

Great job defining our first servlet; we can now move on to registering our servlet to a URL so our server knows which servlet to invoke to process a specific request.

## Web.xml
In order to make a servlet “visible” to our server, we’ll need to configure a URL to the servlet so that our server knows to invoke it when a request to that URL comes in. We can do this using the web.xml file provided in the WEB-INF/ directory of our icecream-app/ directory.

The web.xml file is the deployment descriptor for our web application. In this file, we can register our servlet classes, define the URLs those servlets map to, and many other things. We’ll only be using the <servlet> and <servlet-mapping> tags in this tutorial.

Let’s go ahead and place the following snippet in our web.xml file:
```
  <servlet>
    <servlet-name>homeServlet</servlet-name>
    <servlet-class>HomeServlet</servlet-class>
  </servlet>
```
Note that to register a servlet we:

* Use the <servlet>...</servlet> tag.
* Define the child tag, <servlet-name>homeServlet</servlet-name>, which will define our reference to this servlet and can be any name.
* Define the child tag <servlet-class>HomeServlet</servlet-class>, which will define the class this servlet definition maps to. Note that if your servlet is in a package you need specify the package path.

Finally, we’ll need to tell our server which URL to invoke our HomeServlet for. Let’s do this by placing the following snippet after the <servlet></servlet> block we added previously in our web.xml file:
```
  <servlet-mapping>
    <servlet-name>homeServlet</servlet-name>
    <url-pattern>/home</url-pattern>
  </servlet-mapping>
```
In order to map a URL to a servlet we:

* Use the servlet-mapping>...</servlet-mapping> tag.
* Define the child tag, <servlet-name>homeServlet</servlet-name>, which defines which servlet (using the name we defined using <servlet-name>), this URL will map to.
* Define the child tag, <url-pattern>/home</url-pattern>, which defines the servlet path this servlet will handle.

Nice work defining the web.xml; we can now test our servlet.

## Testing the Servlet
Let’s get our ice-cream app ready to deploy by first compiling HomeServlet.java. Let’s open the CMD (Windows) or terminal (Linux/ OS X) and change our directory to the bin/ directory in Tomcat.

An example Windows CMD or Linux/ OS X terminal command would look like this:
```
cd Documents\apache-tomcat-9.0.64\bin   # Windows
cd Documents/apache-tomcat-9.0.64/bin   # Linux/ OS X
```
Note that your path may be different depending on where you extracted Tomcat to.

In order to compile HomeServlet.java file, we’ll need to use the javac command and specify the classpath to the Tomcat servlet-api.jar and specify to place the generated .class file in the classes/ directory of WEB-INF/.

Our command will look like this:
```
javac -cp  lib/servlet-api.jar -d webapps/icecream-app/WEB-INF/classes webapps/icecream-app/WEB-INF/src/HomeServlet.java
```
Let’s review what exactly is going on here:
* javac - The java compiler command-line tool.
* -cp lib/servlet-api.jar - The javac option to specify where the compiler can look for our imported classes (like HttpServlet).
* -d webapps/icecream-app/WEB-INF/classes - The javac option to specify where to place the generated .class file.
* webapps/icecream-app/WEB-INF/src/HomeServlet.java - The path to our .java file that we wish to compile.

At this point, we’re ready to start Tomcat and see our web page. Let’s execute the startup.bat (Windows) or startup.sh (Linux/ OS X). Once Tomcat has started let’s go to our browser and navigate to:

http://localhost:8080/icecream-app/home

Note that the path to our servlet is http://localhost:8080/icecream-app/home instead of http://localhost:8080/home (this will give us a 404 not found error) because we need to specify the context path of our /home path we defined in our web.xml. The context path in this example is the name of our web application project directory, icecream-app.

# Servlet Architecture and Lifecycle
## Introduction
In this article, we’ll compare servlets to the Common Gateway Interface (CGI) and go more in-depth about servlet architecture. We’ll go over the following:

* CGI vs Servlet architecture
* Servet Container
* Servlet lifecycle

Let’s begin by comparing the CGI to Java servlets.

## CGI vs Java Servlets
Prior to the introduction of Java servlets, a server could respond to user requests using the CGI. Like servlets, the CGI would allow our web server to build responses to client requests at specific URLs but do it by running CGI scripts.
![CGI)[https://github.com/iamAkolab/java_codecademy/blob/main/learn-advanced-java/Servlets/CGI.jpg]

The CGI architecture worked okay but had some major drawbacks like:
* CGI programs were OS-dependent scripts (.bat for Windows and .sh for Linux).
* Since client requests would require the execution of a script, a new process is created on every request.
* More memory is needed on the server as each process needs its own memory space; this causes a scalability issue.
* Since the CGI ran scripts on the web server, there was a high-security risk if any user input was not properly sanitized.

Java servlets addressed many of the issues related to CGI in the following ways:
* Being OS-independent as it runs on the JVM.
* Requests are processed in new threads, which require less overhead to create than processes.
* Less memory is needed on the webserver as threads share memory.
* Servlets are written in Java and inherit all of the built-in security features making them more resilient to attacks.

The CGI can still be used today, but it is preferred to use more modern technologies like Java servlets. We’ve learned a lot about servlets, but how does our web server know to initialize or shut down our servlets? To understand this better we’ll need to discuss the servlet container.

## Servlet Container
The servlet container is a program that runs on our web server, and its “job” is to run and manage our servlets. Using Tomcat provides not only the servlet-api.jar but also a web server and a servlet container.

The servlet container keeps track of all the servlets (and other servlet-related properties) in what is known as the servlet context. A full overview of the servlet context is outside the scope of this article, but we should understand that the servlet container creates a servlet context that it uses to know what servlets are available in our application.
![Container](https://github.com/iamAkolab/java_codecademy/blob/main/learn-advanced-java/Servlets/Container.jpg)

The servlet container receives a request from the web server and does the necessary network and protocol (like HTTP) processing to make the request and response objects easily available to the servlet that should handle the request.

We know how the servlet container helps simplify our servlets by creating the Java request and response objects for us but how does it manage our servlets? This is where the servlet lifecycle comes in.

## Servlet Lifecycle
Our servlets are managed through the servlet container. The management of servlets involves initialization, invocation, and destruction. The servlet API exposes methods for the container to use like init(), service(), and destroy().
![LifeCycle](https://github.com/iamAkolab/java_codecademy/blob/main/learn-advanced-java/Servlets/LifeCycle.jpg)

The container will:

* Check if an instance of the requested servlet is available.
* If an instance is not available it will load the servlet class, create an instance of it, and call init(). Note that once a servlet is initialized it stays initialized until destroyed.
* Once an instance of a servlet is available and initialized, it will call the service() method which, in the case of an HttpServlet, takes care of calling the appropriate (GET, POST, etc) methods.

Once a servlet needs to be removed, for example when we shut down our web server, it takes care of shutting down the servlet by calling its destroy() method.

One final important note about a servlet’s init() and destroy() methods is that they can be overridden to implement custom initialization and destruction like setting up a database connection or removing a database connection respectively.
