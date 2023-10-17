# Rock-Paper-Scissors Game App

This is a simple web application for playing a game of rock-paper-scissors. 

## Description
Two users can play a game of rock-paper-scissors by interacting with the application through a web browser.
* Code language: Java 17
* Application frameworks: Spring Boot and Thymeleaf
* Build tool: Gradle
* Test framework: JUnit and Mockito

## Getting Started

### Dependencies
Minimal prerequisites to install and run the application:
* Docker
* Git

### Installing
Note: This instruction is written for Windows users. 
1. Start Docker and open a command terminal (e.g. Git Bash) and move to the desired location for the application. 
1. Clone the application repository.
      ```
      git clone https://github.com/corneliakramer/rock-paper-scissors.git
      ```
1. Move to the application root folder.
      ```
      cd rock-paper-scissors
      ```
1. Build locally with gradle wrapper.
      ```
      ./gradlew clean build
      ```
1. Build the docker image.
      ```
      docker build -t rock-paper-scissors .
      ```

### Executing program
1. Run a docker container using the image from the installation.
      ```
      docker run --name gameApp -dp 8080:8080 rock-paper-scissors
      ```
1. Open a browser and go to http://localhost:8080/ to use the application. 

### Exiting program
1. Exit the program and remove the running container.
      ```
      docker rm -f gameApp
      ```
                    
## Authors

Cornelia Kramer Karlsson
