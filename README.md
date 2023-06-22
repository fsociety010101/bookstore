# Bookstore web application in *Spring* & *React* 

Simple CRUD web application using ***React, Spring Boot*** & ***MySQL*** running in ***Docker***. 

## Prerequisites:
  1. *Docker*
  2. *Java JDK 1.8+*
  3. *Maven 3.5+*
  4. *NodeJS 18.16.1+*

## Steps to start application
- ### MySQL Database:
  ```
  docker-compose up
  ```
- ### Spring Backend Server:
  ```
  ./mvn spring-boot:run
  ```
  or simply run through IDE of choice e.g. *IntelliJ*.

- ### React Frontend Application:
  - Install all dependecies:
    ```
    npm install
    ```
  - Start the application:
    ```
    npm start
    ```
