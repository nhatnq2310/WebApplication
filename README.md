Project Overview

The Basic Web Application is a Java-based web project using JSP, Servlets, and MySQL. It follows a clean MVC pattern and includes essential CRUD operations.

Features

User authentication (login, logout).

Display dynamic content.

Basic CRUD operations for database management.

Follows a three-layer architecture.

Project Structure

- Web Pages
  - META-INF/context.xml
  - WEB-INF/web.xml
  - home.jsp
- Source Packages
  - controller
    - MainController.java
  - dao
    - DAO.java
    - DBContext.java
  - model
    - User.java

Requirements

Java 8

NetBeans 8

MySQL Database

Apache Tomcat Server

How to Run

Set up the MySQL database and configure DBContext.java.

Deploy the project on Tomcat.

Access the application via http://localhost:8080/yourAppName.
