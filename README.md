# Development of REST API for CRUD Operation on User Entity

This project is a Spring Boot application that implements a RESTful API for performing CRUD (Create, Read, Update, Delete) operations on a User entity. 
The User entity has fields for name, id, and email. Unit testing is performed on service and controller layer using Junit5 and Mockito. The API is documented using Swagger 
by implementing SpringOpenAPI.

## Features

- **Create User**: Allows the creation of a new user with a unique ID, name, and email.
- **Read User**: Retrieves user information by ID or email.
- **Get All Users** : Retrieves all users from the database
- **Update User**: Modifies user information (name and/or email) based on the provided ID.
- **Delete User**: Deletes a user record based on the provided ID.

## Technologies Used
- **Spring Web**: For implementing REST services.
- **Lombok**: To reduce boilerplate code.
- **Spring Boot DevTools**: For auto-restarting the server during development.
- **MySQL**: As the primary database for storing user information.
- **H2 Database**: Used for testing purposes.
- **JUnit and Mockito**: For unit and integration testing.
- **Spring OpenAPI**: For generating Swagger documentation.

## Installation
1. **Clone the Repository**:
   ```bash
   --git clone https://github.com/yourusername/yourproject.git
   --cd yourproject
2. **Build the project**:
   ```bash
      mvn clean install
3. **Run the Application**:
    ```bash
      mvn spring-boot:run
4. **Access the Swagger UI for documentation**:
    http://localhost:9090/swagger-ui/index.html
## Testing
```bash
 mvn test
   
   
   

