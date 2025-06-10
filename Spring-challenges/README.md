# 🌱 Mini Challenges to Learn Spring

Hands-on mini challenges to solidify understanding of Spring Framework concepts through coding, not just theory


## 🚀 Challenge 1: Simple Message Sender

Build a Spring-based message app that sends a message to the console with a timestamp using Spring beans.

### 🧠 What You’ll Learn
- How to define a service using `@Service`
- How to inject it into a component using `@Autowired`
- How bean scope affects object identity



## 🔁 Challenge 2: Bean Scope Exploration

Create a bean with `@Scope("prototype")` and another with the default (singleton). Inject and log their hashcodes.

### 🧠 What You’ll Learn
- Difference between `singleton` and `prototype` scopes
- How Spring manages bean lifecycles
- How scope affects object identity
- How to verify scope behavior via `hashCode()`

### 📋 Tasks

```java
public class CounterService {
    private int count = 0;
    public void increment() { count++; }
    public int getCount() { return count; }
}
```

- Inject it twice into a component — are they the same?    
- Toggle between @Scope("prototype") and the default
    
## 🛠️ Challenge 3: Manual Bean with @Bean
Use a `@Configuration` class to manually declare beans using `@Bean` methods instead of stereotype annotations.

### 🧠 What You’ll Learn
*   Manual vs annotation-based bean configuration   
*   How to wire beans without using `@Component`, `@Service`, etc.
*   Returning interfaces for better abstraction and testability
    
### 📋 Tasks
```java
@Configuration
public class AppConfig {
    @Bean
    public GreetingService greetingService() {
        return new GreetingService("Hi there");
    }
}
```

*   Inject and use `GreetingService` in another component   
*   Return an interface type (e.g., `IGreetingService`)
*   Compare with @Component-based config
    

## 📩 Challenge 4: Notification Service with Interface

Define a `Notifier` interface with multiple implementations and inject one using `@Qualifier`.

### 🧠 What You’ll Learn
*   Abstraction via interfaces
*   Handling multiple beans of the same type
*   Using `@Qualifier` for selective injection
*   Extending with minimal coupling
    

### 📋 Tasks
```java
public interface Notifier {
    void send(String message);
}

@Service
@Qualifier("email")
public class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}
```

*   Add `SMSNotifier`, `PushNotifier`, etc.
*   Inject with `@Qualifier("email")`
*   Bonus: Inject all using Map
    

## 🕵️ Challenge 5: AOP Logging Aspect
Create an aspect that logs every method call using `@Before`, `@After`, and `@Around`.

### 🧠 What You’ll Learn

*   Cross-cutting concerns using Spring AOP
*   Using `@Aspect`, `@Before`, `@After`, `@Around`
*   How Spring applies AOP with proxies
*   Logging and profiling service methods
    
### 📋 Tasks
```java
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.*Service.*(..))")
    public void logBefore() {
        System.out.println("Method call started");
    }
}
```
*   Apply to a sample `UserService`
*   Add `@Around` to log execution time
*   Bonus: Log method name and parameters
    

## 📝 Challenge 6: To-Do List Web App (No DB)
Create a basic web app with a task form and a list view using Spring MVC and in-memory storage.

### 🧠 What You’ll Learn
*   Spring MVC request mapping and form handling
*   Using `@Controller`, `@GetMapping`, `@PostMapping`
*   Template rendering with `Thymeleaf` or `JSP`
*   Managing in-memory state via a singleton `TaskService`
    
### 📋 Tasks
*   Create a Task POJO (e.g., id, title, done)
*   Add controller endpoints: `/addTask`, `/tasks`
*   Use Thymeleaf for form submission and display
    
## 🧱 Challenge 7: MVC + Service Layer + Abstraction
Refactor your To-Do app into layers: controller, service interface, service implementation.

### 🧠 What You’ll Learn

*   Proper separation of concerns
*   Benefits of interface-driven design
*   Injecting service interfaces into controllers
*   Using `@ModelAttribute` for binding
    
### 📋 Tasks
*   Create `TaskService` interface
*   Implement it in `TaskServiceImpl`
*   Inject into `TaskController`
*   Bind form input using `@ModelAttribute("task")`
