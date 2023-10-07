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
| /api/users | POST | Saves a new User to the database. | User JSON |
| /api/users/{id} | GET | Retrieves a User by its ID. | |
| /api/users | GET | Retrieves all Users in the database. | |
| /api/users/{id}/purchases | GET | Retrieves all Purchases made by a User. | |
| /api/users/{id} | PUT | Updates an existing User by its ID. Doesn't update purchases. | User JSON |
| /api/users/{id} | DELETE | Deletes a User by its ID. | |
  
# Item Endpoints:

| URL | METHOD | DESCRIPTION | BODY |
| - | - | - | - |
| /api/items | POST | Saves a new Item to the database. | Item JSON |
| /api/items/{id} | GET | Retrieves an Item by its ID. | |
| /api/items/{id}/purchases | GET | Retrieves all Purchases for an Item by its ID. | |
| /api/items/{id} | GET | Retrieves all Items in database. | |
| /api/items/{id} | PUT | Updates an existing Item by its ID. | Item JSON |
| /api/items/{id}/quantity | PATCH | Updates the quantity of Item by a given amount. | Integer |
| /api/items/{id} | DELETE | Deletes an Item by its ID. | |

# Example JSON Bodies:

User (Also contains list of Purchase entities):    
```json  
{
    "name": "John Doe",
    "email": "john.doe@example.com"
}
```  
Purchase:  
```json
{
    "userId": 1,
    "itemId": 1,
    "purchaseDate": "2023-10-06"
}
```
Item:  
```json  
{
    "name": "Item A",
    "description": "A cool item.",
    "quantity": 1,
    "price": 19.99
}
```

