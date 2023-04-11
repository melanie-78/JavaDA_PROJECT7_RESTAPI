# Poseidon

## Context

At Poseidon inc, a financial company created in 2012 which has set up Poseidon, a software that aims to generate more transactions for institutional investors aggregates 
information from many sources and streamlines the communication and use of this information between the front and back office. 
the objective is to complete the code so as to develop the engine of Poseidon.

## Technical

1. Framework: Spring Boot v2.6.3
2. Java 8
2. Spring security
4. OAuth2.0
5. Thymeleaf
6. Javax validators
5. JUnit 5.8.2

## Setup with Intellij IDE
1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Add folders
    - Source root: src/main/java
    - View: src/main/resources
4. Use MySQL database as configuration in application.properties

## Write Unit Test
1. Create unit and integration test and place in package com.nnk.springBoot in folder test > java 
2. Execute unit test by the command mvn test
