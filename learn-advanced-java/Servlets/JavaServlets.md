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

# Processing Requests and Building Responses
## Introduction
In this article, we’ll learn how to process client HTTP requests and build HTTP responses to process an ice cream order in our ice cream application. We’ll learn how to:

* Create a link to one of our servlets.
* Respond with an HTML page.
* Read form inputs using the HTTPRequest object.
* Build dynamic responses.

We’ll continue using our icecream-app for this article and working with our HomeServlet and an order form that has been provided. Let’s take a look at the form.html provided.

## Ice Cream Order Form
Let’s navigate to the icecream-app/ directory and open form.html in our text editor. form.html is an HTML page that we’ll use to display a form users can use to select various options for their ice cream order.

Notice this line in the HTML:
<form id="orderForm" name="orderForm" method="post" action="/icecream-app/home">

We specify:

* method="post" to direct the form to make an HTTP post request.
* action="/icecream-app/home" to direct the form data to our HomeServlet using the /home path. The post request to /home would currently fail because we have not implemented our doPost() method in HomeServlet (we’ll do this later).

One final important thing to highlight is that the WEB-INF/ directory is not publicly accessible to the client, which means that any files inside can only be accessed by the servlet. Files outside of the WEB-INF/ can be accessed by the client directly. Notice that form.html is outside of the WEB-INF/ and we do this because form.html is a static HTML file, doesn’t contain any sensitive information, and makes it slightly easier for our servlet to link to.

Let’s link our ice cream order form to our ice cream application.

## Linking a Form
We’d like for our customers to be able to order ice cream from a link on our home page. We’ll need to add a few more things to our HomeServlet.

Let’s open the file HomeServlet.java from our WEB-INF/src/ directory in our text editor. In our doGet() method we’d like to add a link to the form.html file provided in the icecream-app/ directory. Our doGet() should look like this:
```
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  PrintWriter out = response.getWriter();
  out.println("<html>");
  out.println("<head><title>My Icecream Shop!</title></head>");
  out.println("<body>");
  out.println("<h1>Welcome To My Ice Cream Parlor</h1>");
  out.println("<a href='/icecream-app/form.html'>Place An Order!</a>"); // link to form
  out.println("</body></html>");
}
```
We can now compile our servlet using the javac command (this command assumes our directory is at our Tomcat root folder) like:
```
javac -cp  lib/servlet-api.jar -d webapps/icecream-app/WEB-INF/classes webapps/icecream-app/WEB-INF/src/HomeServlet.java
```
Let’s start our Tomcat by executing startup.bat (Windows) or startup.sh (Unix). We should see the following page when we navigate to http://localhost:8080/icecream-app/home:
When we click the link we should be taken to http://localhost:8080/icecream-app/form.html and see:

Recall that clicking on order will give you an error page (we’ll fix this later) with a 405 status code because our servlet has not implemented the doPost() method.

Before implementing our doPost() we’ll need a better understanding of the various HTTP methods. Let’s get into that.

## HTTP Methods
Hypertext Transfer Protocol (or HTTP) is a communication method that, at its core, is transferring data over the internet in a client-server model. HTTP provides methods for manipulating data on the server in different ways. Some of the most used methods are:

* GET - Used to retrieve resources/data from the server.
* POST - Used to create resources on the server.
* PUT - Used to update a resource on the server.
* DELETE - Used to specify a resource on a server to delete.

HTTP methods typically respond with or without data and a status code. Some of the most used status codes are:

* 200 - Successful request.
* 201 - Successful request, and a resource was created.
* 400 - Bad request; the client sent a request that has some error.
* 401 - Unauthorized; the client has not been authenticated.
* 403 - Forbidden; the client does not have the correct access rights.
* 404 - Not found; the client cannot find the requested resource.
* 500 - Internal server error because there was an error on the server side that could not be handled.

Great job learning about HTTP methods; we can now implement our doPost() method to process an ice cream order!

## Request Parameters and Dynamic Responses
Let’s implement doPost() in HomeServlet to process an ice cream order sent by our users and provide a unique response based on their selections.

In your text editor, open HomeServlet.java and under our doGet() method let’s define the doPost() method like:
```
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
}
```
A neat thing about an HTML form is that when the order button is clicked, the browser automatically sends a POST request with the form data by placing it in the body of the request. The body of a request is a place for the client to place data to send to the server, to which the server has access.

In our doPost() method, we can make use of the getParameter() method provided by the HTTPServletRequest object to access the form data. In form.html, our input fields make use of the name attribute for example:
```
<input type="radio" id="cup" name="cupOrCone" value="cup">
```
We can use name as an argument to getParameter() to retrieve the associated value like:
```
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  String cupOrCone = request.getParameter("cupOrCone");
  String size = request.getParameter("size");
  String flavor = request.getParameter("flavor");
}
```
We now have the field values that the client chose on the form in the String variables cupOrCone, size, and flavor. At this point, we can do any type of processing we need to do like validate the form fields or do some database operation. For this tutorial, we’ll simply return a unique response back to the user based on their selection.

Let’s use PrintWriter to create a response to our user with details about their order like:
```
public void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
  String cupOrCone = request.getParameter("cupOrCone");
  String size = request.getParameter("size");
  String flavor = request.getParameter("flavor");
        
  PrintWriter out = response.getWriter();   // Get the response PrintWriter
  out.println("<html>");
  out.println("<head><title>My Icecream Shop!</title></head>");
  out.println("<body>");
  out.println(String.format("<h1>Here is your %s %s ice cream in a %s. Enjoy!</h1>", size, flavor, cupOrCone));
  out.println("</body></html>");
}
```
We can now compile our servlet using the javac command (this command assumes we are in the root Tomcat directory) as follows:
```
javac -cp  lib/servlet-api.jar -d webapps/icecream-app/WEB-INF/classes webapps/icecream-app/WEB-INF/src/HomeServlet.java 
```

Let’s start our Tomcat by executing startup.bat (Windows) or startup.sh (Unix). We should see the following page when we navigate to http://localhost:8080/icecream-app/home:

We should see the browser load a web page stating what type of ice cream our user has ordered.


## Redirecting Request and Sending Responses
Now that we know how to build dynamic responses with client request data, we need to be able to handle cases when we want to redirect a client to a static HTML page on our server or respond with an error message. HttpServletResponse objects make this easy for us by providing the methods sendRedirect() and sendError().

When our client sends a request and we notice that we’d like to redirect them to a static page on our server or even to a different website altogether (like https://www.google.com). We can redirect to a different site like:
```
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.sendRedirect("https://wwww.google.com"); 
  return; 
}
```

We can also redirect to a static page on our server (this HTML page should be in the root project directory) like:
```
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.sendRedirect("/icecream-app/example.html"); 
  return; 
}
```
We can also tell our users that an error occurred on the server side or they provided a bad request using sendError() like:
```
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.sendError(HttpServletResponse.SC_BAD_REQUEST, "There was an error in your request.");
  return; 
}
```

In the example above we:

* Used sendError() to respond with an error to a client request.
* In the first argument used HttpServletResponse constant field value, SC_BAD_REQUEST, which represents HTTP status code 400 (note there are constants for other codes as well).
* In the second argument we passed an error message that we’d like to display to our client.
Great job learning about client redirects and error responses.

## Request Sessions
We’ve done a good job at reading data from client requests and generating dynamic responses but what if we’d like to display the customer’s previous order and their current order? To accomplish this we’d need to be able to recognize the client making the call and storing their information. We can do both things by using sessions.

Sessions refer to data that is stored between calls from a specific client. Sessions allow our server to recognize who is making a request and allow us to store information specific to that client. We can make use of sessions by getting them from the HttpServletRequest object provided to our servlets like:
```
public void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
  HttpSession session = request.getSession(true);
}
```
In the example, we call getSession(true) from request, which returns the session associated with the client or creates one if it doesn’t exist yet when we provide true as an argument. If we provided false the method would return an HttpSession if it already exists (it won’t create one if it doesn’t) or return null if one doesn’t exist.

Now that we have a session associated with a client we can store and read information for the client in the session by using HttpSession methods setAttribute() and getAttribute() in our icecream example like:
```
public void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
  String cupOrCone = request.getParameter("cupOrCone");
  String size = request.getParameter("size");
  String flavor = request.getParameter("flavor");
  HttpSession session = request.getSession(true);  // Get or create session object

  String prevSize = session.getAttribute("size");  // Get previously saved size value
  String prevFlavor = session.getAttribute("flavor");  // Get previously saved flavor value
  String prevCupOrCone = session.getAttribute("cupOrCone");  // Get previously saved cupOrCone value

  session.setAttribute("size", size);  // Update size option
  session.setAttribute("flavor", flavor);  // Update flavor option
  session.setAttribute("cupOrCone", cupOrCone);  // Update cupOrCone option
}
```
In the example we:

* Call getSession(true) to get or create a session for this client.
* Call getAttribute() to retrieve the previously selected ice cream options for this client by passing in the name of the attribute. Note that this method returns null if no value is associated with the attribute name provided.
* Call setAttribute() to update or create the attribute in the session where the first argument is the name of the attribute and the second argument is the value to associate with that name. Note that the second argument can be any Java Object.

With HttpSession we’ve managed to introduce “memory” in our servlets to recognize clients and store client-specific information about them.

# Servlets Review
## Introduction
In this article, we will review servlet concepts and see how we can expand on what we’ve learned.

## Servlet Architecture Review
Let’s start by reviewing the servlet architecture and how it fits in the client-server architecture model.

Recall that the client-server architecture model is a communication model where a client makes a request to a server, and the server generates some response. A servlet lives inside the server component of this architecture.

A servlet is a Java class that provides methods to process requests and generate responses and lives within the servlet container. The servlet container is an abstraction layer between the server and servlet that manages the servlet’s lifecycle and its communication to the server.

Let’s now review how to actually create a servlet.

## Creating Servlets
Java makes creating servlets easy by providing pre-defined classes for us to extend, such as HttpServlet. By extending these classes, we can finalize our servlets by implementing certain methods like doGet(), doPost(), etc.

In order to deploy (“run”) our servlets, we’ll need a server and servlet container. We can use tools like Tomcat, which provides a server and a container that make it easy to deploy our servlets.

Using servlets allow us to leverage the power and ease of use of Java to process client request and generate responses which made the shift away from older technologies like the Common Gateway Interface (CGI) compelling.

Let’s review how we can process request data and provide responses.

## Processing Request and Generating Responses
When working with HttpServlets we receive HttpServletRequest and HttpServletResponse objects which we can use to process client request data and generate responses respectively.

HttpServletRequest provides an inherited method called getParameter(String name) which allows us to retrieve client data with a specific name (useful when processing client form data).

HttpServletResponse provides an inherited method called getWriter() which we can use to create dynamic HTML as a response. We also have access to sendRedirect(String location) which lets us redirect the client to location and sendError(int code, String message) which let’s send an error to the client with an HTTP status code and a message.

Let’s finish by talking about sessions.

## Sessions
Servlets, specifically HttpServlet, allow us to process user requests and generate responses but also let us store information about the requester across multiple requests.

Servlet requests (such as HttpServletRequest) allow us to get the requester’s HttpSession object or create it. With sessions, we can store information we’d like to keep track of over multiple calls.

## Conclusion
Great job learning about servlets and creating your first servlet project. You may take your knowledge of servlets further by learning about servlet filtering, communication between servlets, and connecting to a database.
