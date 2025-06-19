## Spring - Bean Scopes and Life Cycle
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