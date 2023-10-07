# Hobby Shop API
***Currently Work-in-progress***  

 RESTful API for supporting an E-Hobby Shop. This API allows for user managment, purchase operations, and inventory control. 

# Installation:
 ***Requirements: JDK 17, Maven, Docker***  


 1. Clone the repository:
      
 ```sh 
git clone https://github.com/MarlonBair/Hobby-Shop-API.git
```

 2. Start the databse:
      
```sh
cd Hobby-Shop-API/src/main/resources/
docker-compose up -d 
```

3. Start the application:
  
```sh
cd ../../../
mvn spring-boot:run
```
4. Access application:
     
The application, by Spring Boot default, runs on port 8080, and is accessible by http://localhost:8080

# User Endpoints:

| URL | METHOD | DESCRIPTION | BODY |
| - | - | - | - |
| /api/users/{id} | GET | Retrieves a User by its ID. | |

| Endpoint         | Method | Description               | Parameters | Body                     | Response  | Auth      |
| ---------------  | ------ | ------------------------- | ---------- | ------------------------ | --------- | --------- |
| `/api/users`     | GET    | Retrieve list of users    | None       | None                     | 200, 404  | Bearer Token |
| `/api/users/:id` | GET    | Retrieve a specific user  | `id`       | None                     | 200, 404  | Bearer Token |
| `/api/users`     | POST   | Create a new user         | None       | `{ "name": "John" }`     | 201      | Bearer Token |



