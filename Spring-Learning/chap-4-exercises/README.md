## Spring - Using Abstractions
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
