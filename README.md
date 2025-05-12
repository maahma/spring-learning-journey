# Spring Mini Projects
Mini projects I will code along as I go through the course: [Java Spring Framework 6 with Spring Boot 3 with Telusko](https://www.udemy.com/course/spring-5-with-spring-boot-2/?utm_source=adwords&utm_medium=udemyads&utm_campaign=Search_DSA_Beta_Prof_la.EN_cc.ROW-English&campaigntype=Search&portfolio=ROW-English&language=EN&product=Course&test=&audience=DSA&topic=&priority=Beta&utm_content=deal4584&utm_term=_._ag_162511579404_._ad_696197165418_._kw__._de_c_._dm__._pl__._ti_dsa-1677053911088_._li_1011084_._pd__._&matchtype=&gad_source=1&gbraid=0AAAAADROdO2JM4w-2M8wwUPLsABvy9BwE&gclid=Cj0KCQjwnui_BhDlARIsAEo9GutdTjumZ37w3xKiRZuphfyeIRt7q1s0gkKSZw25jHZfWzZKxTN-7U8aArWKEALw_wcB&couponCode=2021PM20)

**NOTE**: These projects are not provided by the course

## Spring Framework Introduction
- **Project**: Simple Dependency Injection Demo
- **Description**: Create a basic console application demonstrating Dependency Injection (DI) using XML configuration, annotations, and Java-based configuration.
- **Dependencies**: None (Core Spring only).
- **Instructions**:
    - Set up a new Maven project.
    - Create a configuration class with `@Configuration` annotation.
    - Define beans using `@Bean` method.
    - Demonstrate DI using a simple `MessageService`.
  
## Working with Java-Based Configuration
- **Project**: Basic Calculator Application
- **Description**: Build a console-based calculator using Spring Beans configured with Java configuration (no XML).
- **Dependencies**: None (Core Spring only).
- **Instructions**:
    - Create a CalculatorService class with methods for addition, subtraction, etc.
    - Use `@Configuration` and `@Component` to configure beans.
    - Demonstrate usage with a main application class.
  
## Spring Boot
  - **Project**: Greeting API
  - **Description**: Create a Spring Boot application with an HTTP endpoint that returns a greeting message.
  - **Dependencies**: Spring Boot Starter Web.
  - **Instructions**:
    - Create a Spring Boot project using Spring Initializr.
    - Add a controller with a `@GetMapping` for the greeting.
    - Test the API using a browser or Postman.

## Spring JDBC
  - **Project**: Student Management Console App
  - **Description**: Develop a console application that uses Spring JDBC for CRUD operations on a Student database.
  - **Dependencies**: Spring JDBC, H2 Database (or MySQL).
  - **Instructions**:
    - Create a Student class and a StudentDAO interface.
    - Implement the DAO using JdbcTemplate.
    - Test CRUD operations using console input/output.

## Spring Boot Web
  - **Project**: Online Book Store (Basic)
  - **Description**: Build a Spring Boot web application with a simple homepage listing book titles and an "Add Book" form.
  - **Dependencies**: Spring Boot Starter Web, Thymeleaf.

## Spring AOP
  - **Project**: User Activity Logger
  - **Description**: Add a logging aspect to an existing application that tracks user actions using Aspect-Oriented Programming (AOP).
  - **Dependencies**: Spring Boot Starter AOP.
  - **Instructions**:
    - Create an Aspect class using `@Aspect` annotation.
    - Add pointcuts for logging method calls.

## Spring Security
  - **Project**: Secure User Portal
  - **Description**: Build a web app with user authentication using Spring Security.
  - **Dependencies**: Spring Boot Starter Security.
  - **Instructions**:
    - Configure security settings (in-memory users initially).
    - Add role-based access control.

## JWT and OAuth2
  - **Project**: Secure REST API with JWT
  - **Description**: Secure your REST API with JWT for authentication.
  - **Dependencies**: Spring Boot Starter Security, JWT.
 
## Docker  
  - **Project**: Containerized Spring Boot Application
  - **Description**: Dockerize a Spring Boot application.
  - **Dependencies**: Docker.

## Cloud Deployment
  - **Project**: Deploy API to AWS
  - **Description**: Deploy a Spring Boot app to AWS Elastic Beanstalk.
  - **Dependencies**: AWS CLI, AWS Elastic Beanstalk.

## Microservices
  - **Project**: E-commerce Microservices Application
  - **Description**: Build a system with Product, Order, and Payment microservices.
  - **Dependencies**: Spring Cloud, Eureka, Feign, Gateway.

## Spring AI
  - **Project**: AI-Powered Chatbot API
  - **Description**: Build a chatbot API using OpenAI API integrated with Spring Boot.
  - **Dependencies**: OpenAI API, Spring Boot Starter Web.
