# Learning Spring 
## Application Framework
### What is an application framework?
- Application framework is a set of pre-built software components and functionalities that provide a foundational structure for developing applications. 
- It makes it easy to develop applications by eliminating the need to write all the code from scratch, and offers reusable code for common functionalities
- This allows developers to focus on unique aspects of their application 
- Developers don't have to use all of the features that the application framework provides. They can choose which features are required based on the requirements of the application 
### Why use an application framework?
- Most applications share common requirements, such as:
	- **Logging:** Capturing error, warning, and informational messages
	- **Data Management:** Handling transactions to process data changes
	- **Security:** Implementing protection mechanisms against common vulnerabilities
	- **Communication:** Enabling interaction with other applications
	- **Optimization:** Providing features like caching and data compression
- These are considered **non-business functionalities** because they are essential but not unique to a specific application.
- The **business logic** of the code refers to the code that directly addresses the application's core purpose and user expectations. For example, in a ride-sharing application, clicking a "Book Ride" button initiates a ride request, whereas, in a social networking application, clicking "Post" shares a user’s message. This business logic is what differentiates one application from another, but it makes up only a small portion of the complete application’s code. 

- Since most applications require similar non-business functionalities like data storing, transfer, logging etc., using an application framework allows developers to avoid rewriting these non-business functionalities every time
- This results in several benefits:
	- **Efficiency:** Saves time, effort and money
	- **Reliability:** Uses well-tested code (since many apps use it) reducing the risk of bugs
	- **Community Support:** Access to a larger developer community familiar with the 
    
## What is Spring Framework?
- The Spring Framework is a widely used application framework within the Java ecosystem. 
- It provides a comprehensive set of tools and libraries to build different types of applications, e.g. large-scale backend solutions, automated testing applications etc.
- While it's primarily designed for Java, Spring also supports other JVM (Java Virtual Machine) languages, such as Kotlin
- Spring consists of an ecosystem of frameworks, each offering specialized capabilities for different application needs :
	- **Spring Core** 
		- Foundation of the Spring Frameowrk
		- Implements the design principle called **Inversion of Control (IoC)** where Spring takes responsibility for creating, configuring and managing objects
		- Includes **Spring Context** which acts as the IoC Container, managing components and dependencies 
		- Provides **Spring Expression Language (SpEL)** which helps configure **Spring Beans** and properties
	- **Spring MVC** 
		- Provides a framework for building web applications that handle HTTP requests
		- Supports RESTful APIs, form handling, and dynamic web pages
	- **Spring Data Access**
		- A module of Spring Core
		- Provides tools for connecting to SQL and NoSQL databases 
		- Allows developers to use the persistence layer with a minimum number of lines of code written
		- Includes using JDBC, integrating with object-relational mapping (ORM) frameworks like Hibernate
	- **Spring Testing** 
		- Provides tools for writing and executing unit and integrations tests for Spring applications

### When to use the Spring Framework (and When not to)
- The Spring framework can be used in various scenarios including:
	- backend applications 
	- automated testing
	- desktop applications
	- mobile applications
- While Spring is powerful, it may not be suitable in the following cases:
	- Lightweight, Serverless Applications
		- If the application has a strict size limit, such as Docker containers that need to be small and fast to deploy and destroy, Spring may add unnecessary overhead due to its dependencies 
		- These applications are called **serverless functions** since they are small, self-contained and managed by cloud providers (so we don't have much access to the way they're deployed), making it seem like they run without a server 
		- For serverless functions (like AWS Lambda), lightweight frameworks or plain Java may be a better choice
	- High Security Applications
		- For applications where security is important e.g. defense, government organizations etc., an open-source framework like Spring may be risky
		- If any discovered vulnerability in the framework becomes publicly known, it can be exploited by attackers 
	- Applications requiring excessive customization
		- If an application requires many custom configurations that you end up writing more code, using Spring may not help
	- Existing well-functioning solutions
		- If you already have a working setup that efficiently meets your needs, switching to Spring may introduce unnecessary complexity without the benefits

## What is Spring Core?
- Spring Core is the foundational module of the Spring Framework
- It provides functionalities that support all other Spring components
### Key Concepts in Spring Core
- **Inversion of Control**
	- Spring Core is built around the concept of Inversion of Control (IoC), where Spring takes control of the creation, configuration, and management of objects within your application, so, developers don't have to create objects manually 
	- Developers define configurations (XML, annotations or Java classes) that specify how the application should work, and Spring uses this configuration to initialize and manage components

- **IoC Container (Spring Context)**
	- Acts as the heart of the Spring Framework, managing the lifecycle of beans(objects) and their dependencies
	- Connects various components of your application, making them accessible to one another
	- Can also "aspect" methods - intercepting and adding behavior to them without modifying their code
- **Aspect Oriented Programming (AOP)**
	- Allows Spring to intercepts and add behavior to methods such as logging, transaction management, or security checks
	- It doesn't alter the code logic of those methods

## What is Spring Boot?
- Spring Boot is an extension of the Spring Framework
- It uses the principle "convention over configuration" which means it provides sensible default configurations for common scenarios, which developers can customize based on requirements
- This allows developers to focus on building features without spending excessive time on writing boilerplate code for setup and configuration

## What is Spring Context?
- The Spring Context is the core of the Spring Framework
- It configures and manages the lifecycle of the application’s components, known as **beans**
- It is also referred to as the **Spring IoC (Inversion of Control) Container**
- The Spring Context is responsible for:
	- Creating and managing the lifecycle of beans (objects) defined in the application	    
	- Injecting dependencies between beans using dependency injection	    
	- Handling the lifecycle of beans, including initialization and destruction
	- Providing access to beans throughout the application

### What are Beans in Spring?
- Beans are the objects that make up the core functionality of a Spring application
- They represent components, services or any object that is managed by the Spring Context 
- Beans can be user-defined classes or third-party classes used within the application
- There are multiple ways to add beans to the Spring Context :
	- Using the `@Bean` annotation
	- Using stereotype annotations such as `@Component`, `@Service`, `@Repository`, `@Controller` to automatically detect and register beans
	- Programmatically by adding beans directly to the context using the Spring API

### Creating an Instance of the Spring Context and Adding Beans
To add an instance to the Spring Context (so that Spring can manage it), we will follow these steps:

- **Step 1: Create the Class Instance**
	- First, we define a simple class (e.g., `Parrot`), which will later be added to the Spring Context.
		```java
		// creating the Parrot class
		public class Parrot{
			private String name;
		}
		```
- **Step 2: Add the `spring-context` Dependency to `pom.xml`**
	- Next, we add the necessary Spring Context dependency to our Maven configuration
		```xml
		<dependencies>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-context</artifactId>
				<version>5.2.6.RELEASE</version>
			</dependency>
		</dependencies>
		```
	- Spring is modular, meaning we don't need to include the entire framework—only the parts required
	- Adding the `spring-context` dependency instructs Maven to download only the necessary packages for using the Spring Context
	- Generally, Spring dependencies are part of the `org.springframework` group ID
- **Step 3: Create an instance of the Spring Context**
	- To create an instance of the Spring Context, we use the `AnnotationConfigApplicationContext` class, which allows us to work with annotations
		```java
		public class Main{
			public static void main(String[] args){
			
				// creates an instance of the Spring Context
				var context = new AnnotationConfigApplicationContext();
				
				// create an instance of the Parrot class	
				Parrot p = new Parrot();
			}
		}
		```
	- The `AnnotationConfigApplicationContext` class is used to create the context programmatically
	- We now have the `Parrot` instance, but it is not yet part of the Spring Context
- **Step 4: Move the parrot instance into the Spring Context/Add the bean to the Spring Context**
	- To allow Spring to manage the `Parrot` instance, we need to register it as a bean
	- To do this, we use the `@Bean` annotation which lets use define instances of classes (like `Parrot`) as beans that Spring can manage 
	- Spring only manages objects that are registered as beans in the Spring Context 
	- To register a bean, we follow some more steps
### Registering a Bean
- **Step 1: Create a Configuration Class**
	- Create a separate class in your project, within a `config` package
	- Annotate this class with `@Configuration` to indicate that it will be used to define beans and other Spring configurations
	- This class should be separate from your `Main` class (which could be in a `main` package)
		```java
		// In the config package
		import org.springframework.context.annotation.Configuration;
		
		@Configuration
		public class ProjectConfig{
		
		}
		```
	- **Why Use a Separate Configuration Class?**
		- It keeps your code organized and follows the separation of concerns principle.
		- The `@Configuration` annotation tells Spring that this class can contain bean definitions and other settings.
- **Step 2: Create a Bean Method in the Configuration Class**
	- Add a method inside the Configuration class that returns an object you want to manage as a bean.
	- Annotate this method with `@Bean`. This tells Spring that the method should be used to create a managed bean in the context.
		```java
		// In the config package
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;
		
		@Configuration
		public class ProjectConfig {
			@Bean
			Parrot parrot(){
				var p = new Parrot();
				p.setName("Koko");
				return p;
			}
		}
		```
	- **How it works:**
		- The method `parrot()` creates an instance of the `Parrot` class.
		- The `@Bean` annotation means that Spring will recognize this method as a bean provider, making `Parrot` part of the Spring Context.
		- You can define multiple `@Bean` methods in the same Configuration class.
- **Step 3: Load the Configuration and Access the Bean**
	- Initialize the Spring Context using the `AnnotationConfigApplicationContext` class, passing the Configuration class (`ProjectConfig.class`) as an argument.
	- Retrieve the bean from the context using the `getBean` method.
		```java
		public class Main {
			public static void main(String[] args){
				var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
				Parrot p = context.getBean(Parrot.class);
				System.out.println(p.getName());
			}
		}
		```
	- **Why This Step is Important:**
		- Loading the configuration ensures that Spring is aware of the beans defined in the Configuration class.
		- The `getBean` method retrieves the bean from the Spring Context, allowing us to use it in our application
