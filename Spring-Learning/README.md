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


### Implementing a Requirement with Plain Java
#### Requirement
- We need to implement a feature in an team task management app that:
	1. Allows users to publish comments on tasks
	2. Store the comments (e.g., in a database)
	3. Sends the comments via email to a specific address configured in the app
#### Design Goals
- We need to properly define responsibilities and abstractions for this feature
- And also, maintain loose coupling between objects to make future changes easier
#### Object Roles and Responsibilities
- Objects that implement business use cases are referred to as **services**  
- Since the feature involves two distinct actions — **storing a comment** and **sending a notification** — each action will be handled by a separate object, respecting the **Single Responsibility Principle**    
- Object that handles database operations is called a **repository** or **Data Access Object (DAO)**
- Object that handles external communications is called a **proxy** object
- Object that presents the type of data the app uses is called a **model** object
#### Naming and Structure
- For our program, we will use the following objects:
	- `CommentService`: Orchestrates the use case (publishing a comment)
	- `CommentRepository`: Handles storing the comment (DAO)
	- `CommentNotificationProxy`: Handles sending the notification via email (proxy)
	- `Comment`: Represents the comment 
![[Pasted image 20250527165514.png]]
#### Decoupling with Interfaces
To make the design flexible and extensible:
- We define `CommentRepository` and `CommentNotificationProxy` as **interfaces** rather than concrete classes.
- This ensures that if:
    - The storage method changes (e.g., from a database to a web service), or
    - The notification channel changes (e.g., from email to Slack)
- we can update the implementation **without modifying** the `CommentService`.
- This use of interfaces helps us follow the **Open/Closed Principle** and the **Dependency Inversion Principle**, keeping the system modular and easier to maintain.
![[Pasted image 20250527170110.png]]
#### Project Structure and Implementation
1) **Project Structure**  
	- To organize the project, we divide responsibilities into separate packages:
		- `model` for data objects
		- `repositories` for data access logic
		- `proxies` for external communication
		- `services` for business logic
	- We'll create a `Comment` class to represent a user comment, which includes two fields: the text and the author. This will be implemented as a POJO (Plain Old Java Object) — a simple class with attributes, constructors, getters, and setters, without any external dependencies.
2) **Define the `Comment` Model**
	```java
	package com.example.model;
	
	public class Comment {
	    private String author;
	    private String text;
	
	    public void setAuthor(String author){
	        this.author = author;
	    }
	
	    public String getAuthor(){
	        return author;
	    }
	
	    public void setText(String text){
	        this.text = text;
	    }
	
	    public String getText(){
	        return text;
	    }
	
	    @Override
	    public String toString(){
	        return text + " by " + author;
	    }
	}
	```

3) **Define the `CommentRepository` Interface and Implementation**  
	- We'll define an interface `CommentRepository` in the `repositories` package to abstract the storage mechanism. The class `DBCommentRepository` implements this interface and handles storing comments (here simply simulating the behavior with a print statement). This class will be stored in the same package
	- The interface declares a `storeComment(Comment comment)` method, which will be used by the `CommentService` class to fulfill the storage part of the use case. It defines the contract that `CommentService` relies on to store comments.
	```java
	// CommentRepository.java
	
	package com.example.repositories;
	import com.example.model.Comment;
	
	public interface CommentRepository {
	    
	    void storeComment(Comment comment);
	}
	```

	```java
	// DBCommentRepository.java
	package com.example.repositories;
	import com.example.model.Comment;
	
	public class DBCommentRepository implements CommentRepository {
	
	    @Override
	    public void storeComment(Comment comment){
	        System.out.println("Storing comment: " + comment.getText());
	    }
	}
	```

5) **Define the `CommentNotificationProxy` Interface and Implementation**  
	- To handle external notifications (e.g. sending an email), we define a `CommentNotificationProxy` interface and implement it with `EmailCommentNotificationProxy` in the `proxies` package. 
	```java
	// CommentNotificationProxy.java
	
	package com.example.proxies;
	import com.example.model.Comment;
	
	public interface CommentNotificationProxy {
	
	    void sendComment(Comment comment);
	}
	```

	```java
	// EmailCommentNotificationProxy.java
	package com.example.proxies;
	import com.example.model.Comment;
	
	public class EmailCommentNotificationProxy implements CommentNotificationProxy {
	    
	    @Override
	    public void sendComment(Comment comment){
	        System.out.println("Sending notification for comment " + comment.getText());
	    }
	}
	```

6) **Define the `CommentService` Class**  
	- The `CommentService` class in the `services` package encapsulates the use case of publishing a comment. It depends on `CommentRepository` and `CommentNotificationProxy`, which are injected via the constructor. 
	```java
	// CommentService.java
	package com.example.services;
	import com.example.proxies.CommentNotificationProxy;
	import com.example.repositories.CommentRepository;
	import com.example.model.Comment;

	public class CommentService {
		// Define the dependencies as attributes of the class 
		private final CommentNotificationProxy commentNotificationProxy;
		private final CommentRepository commentRepository;

		// Provide the dependencies when the object is built through the parameters of the constructor
		public CommentService(CommentNotificationProxy commentNotificationProxy, CommentRepository commentRepository){
			this.commentNotificationProxy = commentNotificationProxy;
			this.commentRepository = commentRepository;
		}

		// The method that implements the usecase that delegates the store comment and send comment responsibilties to the dependencies
		public void publishComment(Comment comment){
			commentRepository.storeComment(comment);
			commentNotificationProxy.sendComment(comment);
		}
	}
	```

### Implementing a requirement using Spring with dependency injection
- We will now apply the Spring Framework to the class design we previously implemented
- Spring enables automatic dependency injection, allowing us to work with abstractions more effectively by delegating object creation and wiring to the framework
- When deciding which objects should be part of the Spring context (i.e., managed by Spring), we must ask: **"Does this object need to be managed by the framework?"**. The main reason to add an object to the Spring context is to allow Spring to control it and further augment it with functionalities the framework provides
- The reasons for adding a class to the Spring context are:
    - It has dependencies that need to be injected
    - It is itself a dependency required by another managed class
- In our scenario, the only object that does **not** meet either of these criteria is `Comment`, since it:
    - Has no dependencies
    - Is not injected anywhere as a dependency
- All other objects in our design either require dependencies or are used as dependencies:
    - `CommentService` — depends on both `CommentRepository` and `CommentNotificationProxy`
    - `DBCommentRepository` — implements `CommentRepository` and is injected into `CommentService`
    - `EmailCommentNotificationProxy` — implements `CommentNotificationProxy` and is also injected into `CommentService`
```java
@Component
public class DBCommentRepository implements CommentRepository {

	@Override
	public void storeComment(Comment comment){
		System.out.println("Storing comment: " + comment.getText());
	}
}
```
- Marking the class with the `@Component` annotation instructs Spring to instantiate the class and add an instance as a bean in its context
```java
@Component
public class EmailCommentNotificationProxy implements CommentNotificationProxy {

	@Override
	public void sendComment(Comment comment){
		System.out.println("Sending notification for comment: " + comment.getText());
	}
}
```

```java
@Component
public class CommentService{
	private final CommentRepository commentRepository;
	private final CommentNotificationProxy commentNotificationProxy;

	public CommentService(
		CommentRepository commentRepository,
		CommentNotificationProxy commentNotificationProxy) {
		this.commentRepository = commentRepository;
		this.commentNotificationProxy = commentNotificationProxy;
		}
		
	public void publishComment(Comment comment){
		commentRepository.storeComment(comment);
		commentNotificationProxy.sendComment(comment);
	}
}
```
- Spring uses the constructor to create the bean and injects references from its context in the parameters when creating the instance
- Next, we need to tell Spring where to find the components in the `Configuration` class
```java
@Configuration
@ComponentScan(basePackages = {"proxies","services","repositories"})
public class ProjectConfiguration{
}
```
- We use the `@ComponentScan` annotation to tell Spring in which packages it has to search for the classes annotated with stereotype annotations. It doesn't include the `model` package because that doesn't contain classes annotated with stereotype annotations
- To test the setup, we create a `main` method. This will start the Spring context, grab the bean of type `CommentService` out of it and call the `publishComment(Comment comment)` method
```java
public class Main{
	public static void main(String[] args){
		var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

		var comment = new Comment();
		comment.setAuthor("Maaha");
		comment.setText("Demo comment");

		var commentService = context.getBean(CommentService.class);
		commentService.publishComment(comment);
	}
}
```

- Using Spring DI feature, we don't create the instance of the `CommentService` object and its dependencies ourselves and we don't make the relationship between them either
- In a real world scenario, when we have more than 3 classes, letting Spring manage the objects and dependencies among them makes a difference as it eliminates boilerplate code which allows us to focus on what the application does


## Bean Scopes and Life Cycle
### Scopes
#### Key Ideas:
- In Spring, **scope** defines **how many instances** of a bean Spring should create and **how long they should live** within the application context
- The 2 common scopes found in Spring apps are :
	- `singleton` - (Default)
		- Only **one instance** of the bean is created per Spring application context
		- Every time the bean is requested, **the same instance** is returned
		- Most commonly used in real-world applications for stateless services
	- `prototype`
		- A **new instance** of the bean is created **every time** it is requested from the Spring context
		- Useful for stateful objects or when each usage needs a fresh instance

### Singleton Scope
#### Key Ideas:
- In Spring, **singleton** is the **default scope** for all beans
- It means **Spring creates only one instance** of the bean per application context
- So, **every time the bean is injected or requested**, the **same instance** is returned
- The bean is created **when the context is initialized** and is assigned a unique **bean ID** (typically based on the method name or class name)(unless marked with `@Lazy`)
- However, we **can have multiple singleton instances** of the same class **if each one is registered with a different bean name** 

##### **Implementation**
- Singleton beans can be defined using either:
	- `@Bean` (in a `@Configuration` class), or
	- Stereotype annotations like:
		- `@Component`
		- `@Service` — marks business logic    
	    - `@Repository` — marks data access objects
- There’s **no difference in behavior** between using `@Bean` or stereotype annotations for singleton scope — both create one instance per context **by default**.

##### **More Stereotype Annotations**
- `@Service` 
	- Marks a class as a **service layer component** i.e. it contains **business logic**
	- Tells Spring to manage the class like a bean (just like `@Component`)
	- Use this when the class performs logic that supports the application 
	- For example:
		```java
		@Service
		public class OrderService {
		    public void placeOrder() {
		        System.out.println("Order placed!");
		    }
		}
		```
- `@Repository`
	- Handles data storage and retrieval when interacting with a database, file system etc.
	- It plays the role of a repository in our applications architecture
	- Marks a class as a data access object (DAO) 
	- We use this when we want to perform CRUD operations or interact with JPA/Hibernate
	- For example:
		```java
		@Repository
		public class OrderRepository {
		    public void saveOrder(Order order) {
		        System.out.println("Saving order to DB...");
		    }
		}
		```
##### **Important Considerations**
- The **singleton scope** assumes that **multiple components of the application will share the same object instance**, so it is important that these beans are **stateless or immutable** to avoid unwanted side effects
- In real-world applications—especially web applications—**multiple threads can access the same singleton bean simultaneously**. If these threads modify the bean’s state, it can result in a **race condition**
- A **race condition** occurs when two or more threads attempt to change a shared resource at the same time, leading to **unpredictable behavior**. To prevent this, developers would need to use **synchronization**, which singleton beans are **not designed for**
- The safest way to prevent such issues is to make singleton beans **immutable**, and **constructor injection** is a good practice to help enforce immutability

##### **Eager vs Lazy Instantiation**
- There are two main instantiation strategies in Spring for singleton beans:
	- **Eager instantiation** _(default)_
	- **Lazy instantiation**

1. **Eager Instantiation**
	- Eager instantiation is the default behavior in Spring
	- This means that all singleton beans are created as soon as the application context is initialized

2. **Lazy Instantiation**
	- With lazy instantiation, Spring delays the creation of a singleton bean until it is first requested
	- To enable this behavior, we annotate the bean with `@Lazy`:
		```java
		@Lazy
		@Service
		public class PaymentService {
		    // ...
		}
		```
##### **When to Use Which?**
- In most cases, **eager instantiation is preferred**, because:
    - It ensures that all required beans are created and available **at startup**, avoiding potential runtime errors
    - It simplifies dependency management: if one bean depends on another, Spring can ensure the dependent bean already exists
    - Any errors during bean creation are discovered **early**, when the application starts, rather than at runtime when the bean is first accessed
- Lazy instantiation can be useful in **large monolithic applications** or when you want to **optimize startup time** by delaying the creation of heavyweight or rarely used beans 

#### 💻 Code Snippet :
##### 🔸 **General Singleton Pattern in Plain Java:**
```java
public class CommentService{

	public static CommentService getInstance(){
		if (instanceHasNotYetBeenCreated()){
			createCommentServiceInstance();
		}

		return commentService;
	}
}
```
- In plain Java, we manually manage the instance to ensure **only one object** of the class is ever created
##### 🔸 **Spring Singleton Using `@Bean`:**
```java
@Configuration
public class ProjectConfig{

	@Bean
	public CommentService commentService1(){
		return new CommentService();
	}

	@Bean
	public CommentService commentService2(){
		return new CommentService();
	}
}
```
- Spring allows you to define multiple singleton beans **of the same class**, each with its own bean ID
- Even though they are of the same type, Spring treats them as **separate singleton beans**
##### 🔸 **Spring Singleton Using Stereotype Annotations:**
```java
// CommentService.java

package com.example.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repositories.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	public CommentRepository getCommentRepository(){
		return commentRepository;
	}
}
```

```java
// UserService.java
package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repositories.CommentRepository;

@Service
public class UserService {
    
    @Autowired
    private CommentRepository commentRepository;

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }
}
```

```java
// Main.java
package com.example;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.*;
import com.example.services.*;

public class Main {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

		var s1 = context.getBean(CommentService.class);
		var s2 = context.getBean(UserService.class);

		boolean b = s1.getCommentRepository() == s2.getCommentRepository();

		System.out.println(b);
	}
}
```
- The Spring context is initialized using the configuration class
- Both `CommentService` and `UserService` are automatically registered in the context using `@Service`
- Spring automatically injects the same singleton instance of `CommentRepository` into both services using `@Autowired`
- Since Spring uses **singleton scope by default**, both services share the **same repository instance**.
- As a result, the comparison prints `true`, confirming that the dependency is shared

### Prototype Scope

#### Key Ideas:
- A **prototype-scoped bean** tells Spring:
	- “Create a **new instance** of this bean **every time** it’s requested from the context.”
- We use it when:
	- We want **a fresh object** every time (e.g. something with temporary state)
    - We **don’t want to share** the same instance between different consumers
- We can define prototype beans using **either `@Bean` or stereotype annotations** (`@Component`, `@Service`, etc.) along with `@Scope("prototype")`
- For prototype beans, Spring doesn't create and manage an object instance directly
- We no longer have concurrency problems because each thread that requests the bean gets a different instance so defining mutable prototype beans is not a problem

##### When and Why we should use prototype-scoped beans in Spring
- **Problem with Singleton for Mutable Objects**
	- By default, Spring beans are **singleton** — one instance shared across the whole application.
	- If the object **stores state internally (i.e., it's mutable)** and is **used by multiple threads**, this can cause problems (race conditions, unexpected behavior).
- **Example:**
	```java
	@Component
	public class CommentProcessor {
	    private Comment comment;
	
	    public void setComment(Comment comment) {
	        this.comment = comment;
	    }
	
	    public void processComment() {
	        // logic that changes comment
	    }
	
	    public Comment getComment() {
	        return this.comment;
	    }
	}
	```
	- If this `CommentProcessor` is a **singleton**, and two users/processes try to call `sendComment()` at the same time, they might overwrite each other’s comment! 
- **Solution: Use Prototype Scope**
	- If the `CommentProcessor`:
		- Holds state (the `Comment`)
		- Modifies that state inside its methods
		- Needs a **fresh, independent instance** per use
	- Then, we **make it a prototype bean**:
		```java
		@Component
		@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
		public class CommentProcessor {
		    @Autowired
		    private CommentRepository commentRepository;
		    // ... methods
		}
		```
	- Now every time we **ask Spring for a CommentProcessor**, we get a **new object**.
##### Injecting Prototypes into Singleton Beans
- **The challenge:**
	- Our `CommentService` is a singleton.
	- If we inject the `CommentProcessor` into it **once** (like this):
		```java
		@Autowired
		private CommentProcessor processor;
	
		```
	- Then **only one processor** is created and reused. This is **not prototype behavior**.

-  **Correct Approach**
	- We **inject the Spring context itself** and **manually request a new bean inside the method**:
	```java
	@Service
	public class CommentService {
	    @Autowired
	    private ApplicationContext context;
	
	    public void sendComment(Comment c) {
	        CommentProcessor processor = context.getBean(CommentProcessor.class);
	        processor.setComment(c);
	        processor.processComment();
	        processor.validateComment();
	    }
	}
	```
	- This way, **each call to `sendComment()` gets a fresh `CommentProcessor`**.
	- You avoid race conditions.
	- The object remains **mutable**, but safe.
##### When Should You Use Prototype Beans?
- Use **prototype scope**:
	- When your bean holds **state/data that should not be shared**
	- When you're working with **legacy/mutable components**
	- When refactoring old applications that already rely on **mutable patterns**
##### Avoid prototype scope if:
- You can make your beans **stateless or immutable** 
- Your app is heavily multithreaded (consider immutability instead)

#### 💻 Code Snippet :
##### 🔸 **Spring Prototype Using `@Bean`:**
```java
// CommentService.java
@Service
public class CommentService {
    public CommentService(){
        System.out.println("CommentService instance created!");
    }
}
```

```java
// ProjectConfig.java
@Configuration
public class ProjectConfig {
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CommentService commentService(){
        return new CommentService();
    }
}
```

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    
        var cs1 = context.getBean("commentService", CommentService.class);
        var cs2 = context.getBean("commentService", CommentService.class);

        boolean b = cs1 == cs2;

        System.out.println(b);
    }
}

// Output:
// CommentService instance created!
// CommentService instance created!
// false
```
- We get a new bean every time we request it as `cs1` and `cs2` contain references to different instances
- We add `@Scope(BeanDefinition.SCOPE_PROTOTYPE)` after `@Bean` annotation to make the bean prototype-scoped
##### 🔸 **Spring Prototype Using Stereotype Annotations:**
```java
// CommentRepository.java
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommentRepository {
    
}
```

```java
// UserService.java
@Service
public class UserService {
    @Autowired
    private CommentRepository commentRepository;

    public UserService(){
        System.out.println("UserService instance created");
    }

    public CommentRepository getCommentRepository(){
        return commentRepository;
    }
}
```

```java
// CommentService.java
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentService(){
        System.out.println("CommentService instance created");
    }

    public CommentRepository getCommentRepository(){
        return commentRepository;
    }

}
```

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    
        var s1 = context.getBean("commentService", CommentService.class);
        var s2 = context.getBean("userService", UserService.class);
    
        boolean b = s1.getCommentRepository() == s2.getCommentRepository();
    
        System.out.println(b);
    }
}

// Output
// CommentService instance created
// UserService instance created
// false
```
- `CommentRepository` is a prototype bean which is injected using `@Autowired` in two `Service` beans i.e. `CommentService` and `UserService`
- Each service bean has a reference to a different instance of `CommentRepository`

