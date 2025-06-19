## Spring - Wiring Beans
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