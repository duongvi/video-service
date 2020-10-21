# Simple Video Service
This is a project that was developed as part of the course "Building Cloud Services with the Java Spring Framework" offered by Vanderbilt University through Coursera, which is licensed under the Apache License, Version 2.0. The structure of the application was provided by Dr. Jules White of Vanderbilt University and my job was to implement the controller on the server side to provide functionality described in https://github.com/juleswhite/mobile-cloud-asgn2 . 

This project helped me get an introduction to the Spring Framework and Spring Boot to build web applications in Java. 

# Main Contributions
Below, the main Java classes which have been added/modified are briefly described. 
## MainController
This is where the core functionality of the application was implemented, using the Spring Framework. Thanks to using the Spring Data API, more specifically a JPA Repository for, for persistence, the implementation of this controller was much simpler than it otherwise would have been. The assignment was to provide the methods described in: https://github.com/juleswhite/mobile-cloud-asgn2, in our web application.

## VideoRepository
This interface serves as the repository for the videos. It is a type of CRUD (Create, Read, Update, Delete) repository that works like a relational (SQL) database. Leveraging the functionality provided by Spring, it was only necessary to add the method names in such a manner, so that Spring could recognize what they are supposed to do.
