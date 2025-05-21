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

## What are Beans in Spring?
- Beans are the objects that make up the core functionality of a Spring application
- They represent components, services or any object that is managed by the Spring Context 
- Beans can be user-defined classes or third-party classes used within the application

## Adding Beans to the Spring Context
- There are multiple ways to add beans to the Spring Context :
	- Using the `@Bean` annotation
	- Using stereotype annotations such as `@Component`, `@Service`, `@Repository`, `@Controller` to automatically detect and register beans
	- Programmatically by adding beans directly to the context using the Spring API

### Adding Beans to the Spring Context using `@Bean`
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
#### Registering a Bean
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

#### Adding Multiple Beans of Different Types to the Spring Context
- You can add multiple beans to the Spring context, each of different type
- Each bean is defined in the `@Configuration` class using the `@Bean` annotation
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
	
		// Adds the string "Hello" to the Spring Context
		@Bean
		String hello(){
			return "Hello";
		}
	
		// Adds the integer 10 to the Spring Context
		@Bean
		Integer ten(){
			return 10;
		}
	}
	```
- In this configuration:
	- We have three beans: one of type `Parrot`, one of type `String`, and one of type `Integer`.
	- These beans are identified by their method names (`parrot`, `hello`, and `ten`)

- You can also access each of these beans using the `getBean()` method in the Spring Context
	```java
	public class Main {
		public static void main(String[] args){
			var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
			Parrot p = context.getBean(Parrot.class);
			System.out.println(p.getName());
	
			String s = context.getBean(String.class);
			System.out.println(s);
	
			Integer n = context.getBean(Integer.class);
	
			System.out.println(n);
			
		}
	}
	
	
	// Output :
	// Koko
	// Hello
	// 10
	```
- **How it works:**
	- Spring searches the context for a bean of the specified type (`Parrot`, `String`, or `Integer`).
	- If found, it returns the bean. If not, it throws an exception.
#### Adding Multiple Beans of Same Types to the Spring Context
- You can also add multiple beans of the same type (e.g. multiple `Parrot` objects) to the Spring Context
- Each bean must have a unique name to avoid conflicts
	```java
	// In the config package
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	
	@Configuration
	public class ProjectConfig {
		@Bean
		Parrot parrot1(){
			var p = new Parrot();
			p.setName("Koko");
			return p;
		}
	
		@Bean
		Parrot parrot2(){
			var p = new Parrot();
			p.setName("Mini");
			return p;
		}
	
		@Bean
		Parrot parrot3(){
			var p = new Parrot();
			p.setName("Riki");
			return p;
		}
	}
	```
- But we can't access them anymore from `Main` by just using their type i.e. `Parrot.class` because Spring wouldn't know which of the three Parrot instances you're referring to and would throw a `NoUniqueBeanDefinitionException: No qualifying bean of type 'main.Parrot' available` exception
- To fix this, we must use their names to access them in the `getBean()` method i.e. `parrot1`, `parrot2`, `parrot3`
	```java
	public class Main {
	public static void main(String[] args){
			var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
			Parrot p = context.getBean("parrot2", Parrot.class);
			System.out.println(p.getName());		
		}
	}

	// Output:
	// Mini
	```
#### Setting a Bean as Primary
- If we have multiple beans of the same type in the Spring Context, we can mark one as the primary bean using the `@Primary` annotation
- This tells Spring which bean to use when you don't specify a bean name
	```java
	@Bean
	@Primary
	Parrot parrot2() {
		var p = new Parrot();
		p.setName("Miki");
		return p;
	}
	```
- Now, even if we use the `getBean(Parrot.class)`, Spring will automatically select the primary bean i.e. `parrot2`


### Adding Beans to the Spring Context using Stereotype Annotations
- Stereotype annotations provide a simpler way to add beans to the Spring Context without manually defining them in a configuration class, hence there is less code involved
- By using stereotype annotations like `@Component`, you can directly mark a class to be recognized and managed by Spring
- So, when the application creates the Spring Context, Spring automatically detects the classes annotated with `@Component`,  creates instances of the classes (beans), and adds them to its context.
- We still use a configuration class to specify the package(s) where Spring should look for these annotated classes.
#### How to use Stereotype Annotations?
1. **Mark the class with `@Component`**
	- Add the `@Component` annotation above the class definition to tell Spring to treat it as a bean
		```java
		import org.springframework.stereotype.Component;

		@Component 
		public class Parrot{
			private String name;
		
			public String getName(){
				return name;
			}
		
			public void setName(String name){
				this.name = name;
			}
		}
		```
2. **Configure Component Scanning**
	- In the Configuration Class, use the `@ComponentScan` annotation to tell Spring where to look for the classes annotated with stereotype annotations
	- The `basePackages` attribute specifies the package to be scanned
		```java
		import org.springframework.context.annotation.ComponentScan;
		import org.springframework.context.annotation.Configuration;

		@Configuration
		@ComponentScan(basePackages = "main")
		public class ProjectConfig{	
		}
		```
#### Initializing Beans with `@PostConstruct`
- By default, `@Component` only creates an instance/bean of the class, adds it to the Spring Context but doesn't initialize it 
- To initialize the bean (set values, perform setup tasks), we can use the `@PostConstruct` annotation.
- If the `@Component` class has a method annotated with `@PostConstruct`, Spring will automatically **call that method immediately after the bean is fully initialized**.
```java
@Component 
public class Parrot{
	private String name;
	
	@PostConstruct
	public void init() {
		this.name = "Kiki";
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
}
```

#### When to use `@Bean` and `@Component` to add Beans to Spring Context
- Use `@Bean` when:
	- You need fine-grained control over bean creation (e.g., method parameters)        
    - You want to add beans for external classes or third-party libraries        
    - You prefer to manage bean definitions in a centralized configuration class.        
- Use `@Component` when:    
    - You want Spring to automatically discover and register your beans        
    - Your classes are simple and don't require complex initialization        
    - You prefer a more modular and decentralized approach (beans are directly in their respective classes)

### Adding Beans to the Spring Context programatically
- If we want to register specific beans to the Spring Context, depending on specific configurations of your application, we can't filter them out using `@Bean` or `@Component`
- So, we need to use programmatic bean registrations using `registerBean()` method of the `AnnotationConfigApplicationContext` class
- To add beans programatically, we follow these steps:
1. Create an instance of the Spring Context
	```java
	var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
	```
2. Create the Bean Object Manually
	```java
	Parrot x = new Parrot()
	x.setName("Kiki");
	```
3. Use a `Supplier` to provide the Bean
	```java
	Supplier <Parrot> parrotSupplier = () -> x;
	```
	- We use a `Supplier<T>` to wrap the object
	- A `Supplier` is a functional interface from `java.util.function` 
	- It has a single method: `T get()`, which returns an object of type `T`
	- This means you can provide any logic you want in this `Supplier` for creating or configuring the bean
	- In this case, it simply returns the `Parrot` instance we created
	- Using a `Supplier` allows us to provide the object dynamically
4. Register the Bean using `registerBean()`
	```java
	context.registerBean("parrot1", Parrot.class, parrotSupplier);
	```
	- This is the key step where you register the bean manually in the Spring Context
	- The `registerBean` method has four parameters:
	    - `String beanName` - define a name for the bean you add to the Spring Context i.e. `parrot1`
	    - `Class<T> beanClass` - the class that defines the bean you add to the context e.g. if we want to add an instance of the `Parrot` class, the value for the parameter would be `Parrot.class`
	    - `Supplier<T> supplier` - this is the supplier that creates the bean instance and returns it i.e. `parrotSupplier`
	    - `BeanDefinitionCustomizer` - this is an interface we implement to configure different characteristics of the bean e.g. making it primary e.g. `bc -> bc.setPrimary(true)`. We can omit this parameter
5. Retrieve the Bean from the Spring Context
	```java
	Parrot p = context.getBean(Parrot.class);
	System.out.println(p.getName());
	
	// Output
	// Kiki
	```

**Complete code example**
```java
public class Main{
	public static void main(String[] args){
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

		Parrot x = new Parrot();
		x.setName("Kiki");

		Supplier<Parrot> parrotSupplier = () -> x;

		context.registerBean("parrot1", Parrot.class, parrotSupplier);

		Parrot p = context.getBean(Parrot.class);
		System.out.println(p.getName());
	}
}
```

## Wiring Beans
- Spring allows you to establish relationships between beans in multiple ways:
	- **Wiring**: Manually link beans by calling methods that return other beans.
	- **Auto-wiring**: Let Spring automatically provide dependencies via method parameters or annotations.
- Both these approaches are called "Dependency Injection" where Spring injects one bean into another to establish relationships

**Example**:  If we have two beans - `Person` and `Parrot` - and we want the Person to own the Parrot, then we can form a has-A relationship(i.e. Person has a parrot) between in them in the following ways

### Wiring the beans using a direct method call between the `@Bean` methods
1. **Define the classes - `Parrot` and `Person`**
	```java
	// Parrot.java
	public class Parrot {
		private String name;
	
		@Override
		public String toString() {
			return "Parrot: " + name;
		}
	
		// Getters and Setters
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	```

	```java 
	// Person.java
	public class Person {
		private String name;
		private Parrot parrot;
	
		// Getters and Setters
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
		public Parrot getParrot() {
			return parrot;
		}
		public void setParrot(Parrot parrot) {
			this.parrot = parrot;
		}
	}
	```

2. **Define the Beans using the `@Bean` annotation in the Configuration Class**
	```java
	@Configuration
	public class ProjectConfig{
		@Bean
		public Parrot parrot() {
			Parrot p = new Parrot();
			p.setName("Koko");
			return p;
		}
	
		@Bean
		public Person person() {
			Person p = new Person();
			p.setName("Ella");
			p.setParrot(parrot());
			return p;
		}
	}
	```
	- Spring calls the `parrot()` method to create the `Parrot` bean
	- It also calls the `person()` method to create the `Person` bean and calls the `parrot()` method inside it to set the parrot 
	- If the `Parrot` bean already exists in the context, Spring reuses it instead of calling the method again
	- This creates a **has-A** relationship: the `Person` has a `Parrot`
3. **Access the beans in the Main class**
	```java
	public class Main{
		public static void main(String[] args){
			var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
	
			Person person = context.getBean(Person.class);
	
			Parrot parrot = context.getBean(Parrot.class);
	
			System.out.println("Person's name: " + person.getName());
	
			System.out.println("Parrot's name: " + parrot.getName());
	
			System.out.println("Person's parrot: " + person.getParrot());
		}
	}
	
	// Output
	// Person's name: Ella
	// Parrot's name: Koko
	// Person's parrot: Parrot : Koko
	```
### Wiring the beans using the `@Bean` annotated method's parameters
- Instead of directly calling the method that defines the bean we want to refer to, we can define a parameter in the `@Bean` method of the required type, and Spring will automatically inject the appropriate bean from its context
- In this approach, it **doesn't matter** whether the referenced bean is defined using `@Bean` or `@Component`; Spring will resolve it from the context either way.
- To use the method:
	1. Add a parameter of the required bean type to your `@Bean` method: `public Person person(Parrot parrot)`
	2. Use the parameter to set the relationship between beans: `p.setParrot(parrot)`
	```java
	@Configuration
	public class ProjectConfig{
		@Bean
		public Parrot parrot() {
			Parrot p = new Parrot();
			p.setName("Koko");
			return p;
		}
	
		@Bean
		public Person person(Parrot parrot) {
			Person p = new Person();
			p.setName("Ella");
			p.setParrot(parrot); // Spring injects the parrot bean into this parameter
			return p;
		}
	}
	```
- This method uses **Dependency Injection**  
- DI is a **design pattern** where dependencies (i.e., objects a class needs to function) are **automatically provided**, rather than created manually inside the class.
- In our example, Spring injects the `Parrot` bean into the `person()` method's parameter, resolving the dependency.
- DI is an application of the **Inversion of Control (IoC)** principle, where the control of creating and managing object lifecycles is **inverted**—moved from the developer to the framework (Spring, in this case).

### Using `@Autowired` annotation to inject beans
- `@Autowired` is an annotation we use to tell Spring to **automatically inject a bean** into a class **without manually passing it** in the configuration class.
- This method is used when you **can modify the class that needs the dependency**, meaning it's not from an external library.
- You place `@Autowired` in the class where you want Spring to inject another bean.
- This approach makes the relationship between classes more obvious and is easier to manage in large applications.
- There are 3 common ways to use `@Autowired`:
	1. **Field Injection** - Common in quick examples and tutorials, but discouraged in production code
	2. **Constructor Injection** - The most recommended method in real-world applications
	3. **Setter Injection** - Rarely used in production code, has several drawbacks

#### Field Injection
- The simplest method of dependency injection, but **not ideal** for production
- It does not allow fields to be `final`, which weakens immutability and testability
**Example**
1. Create `Parrot` and `Person` classes annotated with `@Component`
	```java
	@Component
	public class Parrot{
		private String name = "Koko";
		
		// Getters and Setters
		
		@Override
		public String toString(){
			return "Parrot: " + name;
		}
	}
	```

	```java
	@Component
	public class Person {
		private String name = "Ella";

		private Parrot parrot;
		//...
	}
	```

2. Add `@Autowired` above the class field i.e. `private Parrot parrot;`
	```java
	@Autowired
	private Parrot parrot;
	//...
	}
	```
	- The `@Autowired` annotation tells Spring to automatically inject the `Parrot` bean into the `Person` class.
	- Spring looks for a `Parrot` bean in its context and sets it as the value for the `parrot` field.
	- This establishes a relationship between the two beans: `Person` depends on `Parrot`.
	- In real-world applications, it's common to use `@Autowired` with classes marked by stereotype annotations such as `@Component`, `@Service`, or `@Repository`.
3. Create a configuration class annotated with `@ComponentScan` to tell Spring where to find the components that are annotated with `@Component`
	```java
	@Configuration
	@ComponentScan(basePackages = "beans")
	public class ProjectConfig{
	
	}
	```

4. Create the `Main` class to load the Spring context and retrieve the beans as we did previously

**Why field injection is discouraged in production**
- Field injections are not desired in production code because they make it harder to write unit tests because dependencies can't be easily passed via constructors
- They also prevent immutability - we can't declare the injected field as `final` and make sure that no one can change its value after initialization 
	```java
	@Autowired
	private final Parrot parrot; // gives a compile time error
	```

#### Constructor Injection
- This method allows you to define fields as `final`, ensuring their values cannot be changed after Spring initializes them
- In constructor injection, the dependency is passed through the class constructor:
	```java
	@Component
	public class Person {
		private String name = "Ella";
		private final Parrot parrot;
	
		@Autowired
		public Person(Parrot parrot) {
			this.parrot = parrot;
		}
	}
	```
- When Spring creates a bean of type `Person`, it calls the constructor annotated with `@Autowired` and injects a `Parrot` bean from the context into the `parrot` parameter.
- This approach ensures immutability and makes the class easier to test.
- Since the dependency is provided at construction time, we can safely declare the `parrot` field as `final`

#### Setter Injection
- This approach is not commonly used by developers.
- It has more disadvantages than advantages:
    - It's harder to read and maintain.
    - It doesn't allow fields to be `final`.
    - It doesn't simplify unit testing.
- In this method, a setter method is provided for the dependency and annotated with `@Autowired`:
	```java
	public class Person {
		private String name = "Ella";
		private Parrot parrot;

		// Getters and Setters for Person
		
		@Autowired
		public void setParrot(Parrot parrot) {
			this.parrot = parrot;
		}
	}
	```
- Spring will call the setter method and inject the `Parrot` bean from the context during bean initialization

### Circular Dependency
It's better to let Spring manage the creation and injection of dependencies in our application
- This saves developers from writing boilerplate code and makes the app easier to read and understand
- But, this can also result in **circular dependency**
- A circular dependency occurs when two or more beans depend on each other in a way that creates a loop. For example:
	- To create **Bean A**, Spring needs **Bean B**.
	- But to create **Bean B**, Spring needs **Bean A**.
	- This leads to a deadlock, because Spring can't finish creating either bean without the other

**Code Example of Circular Dependency**
```java
// Parrot.java
@Component
public class Parrot {
	private final Person person;

	@Autowired
	public Parrot(Person person){
		this.person = person;
	}
}
```
- Here, `Parrot` has a dependency on `Person`
- To construct `Parrot`, Spring needs to first create a `Person`

```java
// Person.java
@Component
public class Person{
	private final Parrot parrot;

	@Autowired
	public Person(Parrot parrot){
		this.parrot = parrot;
	}
}
```
- Now `Person` also depends on `Parrot`
- So, Spring cannot create a `Person` without a `Parrot`, and it cannot create a `Parrot` without a `Person`
- Running the above causes Spring to end up in a deadlock and throw the following exception: 
	`BeanCurrentlyInCreationException: Error creating bean with name 'parrot': Requested bean is currently in creation: Is there an unresolvable circular reference?`
- We can avoid circular dependencies by not designing classes that depend on each other directly through constructors    
- Refactor the code to break the cycle. Possible solutions include:
    - Using **setter injection** for one of the dependencies.
    - Introducing a **third bean** to mediate between the two.
    - Rethinking the architecture to remove tightly coupled dependencies.

### Choosing from multiple beans in the Spring Context
- When Spring has **multiple beans of the same type**, it doesn't know **which one to inject**.
- To resolve this, you can:
    - Use the `@Primary` annotation to mark one bean as the **default** for injection.
    - Use the `@Qualifier` annotation to specify which bean to inject by name — this is often the **preferred** and more **flexible** method
**Using `@Qualifier`**
```java
@Configuration
public class ProjectConfig {

	@Bean
	@Qualifier("parrot1")
	public Parrot parrot1() {
		Parrot p = new Parrot();
		p.setName("Koko");
		return p;
	}

	@Bean
	@Qualifier("parrot2")
	public Parrot parrot2() {
		Parrot p = new Parrot();
		p.setName("Miki");
		return p;
	}

	@Bean
	public Person person(@Qualifier("parrot2") Parrot parrot) {
		Person p = new Person();
		p.setName("Ella");
		p.setParrot(parrot);
		return p;
	}
}
```
- Without `@Qualifier`, this line would look like: `p.setParrot(parrot2)`
- But, with `@Qualifier("parrot2")`, we explicitly tell Spring to inject the bean named `parrot2`
