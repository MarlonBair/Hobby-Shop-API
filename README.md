# Hobby Shop API

 RESTful API for supporting an E-Hobby Shop. This API allows for user management, inventory control, and purchase operations. 

# Installation:
 ***Requirements: JDK 17, Maven, Docker***  


 1. Clone the repository:
      
 ```sh 
git clone https://github.com/MarlonBair/Hobby-Shop-API.git
```

 2. Start the database:
      
```sh
cd Hobby-Shop-API/src/main/resources/
docker-compose up 
```

3. (In a new terminal) Start the application:
  
```sh
cd Hobby-Shop-API
mvn spring-boot:run
```
4. Access application:
     
The application, by Spring Boot default, runs on port 8080, and is accessible at http://localhost:8080

5. ***Optional*** Run tests:
  
Run in root directory (Hobby-Shop-API/): 
```sh
mvn test
```

# User Endpoints:

| URL | METHOD | DESCRIPTION | BODY |
| - | - | - | - |
| /api/users | POST | Saves a new User to the database. | User JSON |
| /api/users/{userId} | GET | Retrieves a User by its ID. | |
| /api/users | GET | Retrieves all Users in the database. | |
| /api/users/{userId}/purchases | GET | Retrieves all Purchases made by a User. | |
| /api/users/{userId} | PUT | Updates an existing User by its ID. Doesn't update purchases. | User JSON |
| /api/users/{userId} | DELETE | Deletes a User by its ID. | |
  
# Item Endpoints:

| URL | METHOD | DESCRIPTION | BODY |
| - | - | - | - |
| /api/items | POST | Saves a new Item to the database. | Item JSON |
| /api/items/{itemId} | GET | Retrieves an Item by its ID. | |
| /api/items/{itemId}/purchases | GET | Retrieves all Purchases for an Item by its ID. | |
| /api/items | GET | Retrieves all Items in database. | |
| /api/items/{itemId} | PUT | Updates an existing Item by its ID. | Item JSON |
| /api/items/{itemId}/quantity | PATCH | Updates the quantity of Item by addition of a given amount. | Integer (Quantity) |
| /api/items/{itemId} | DELETE | Deletes an Item by its ID. | |


# Purchase Endpoints:

| URL | METHOD | DESCRIPTION | BODY |
| - | - | - | - |
| /api/users/{userId}/purchases | POST | Creates a new Purchase with the given userId and itemId. | Long (itemId) |
| /api/purchases/{purchaseId} | GET | Retrieves a Purchase by its ID. | |
| /api/purchases | GET | Retrieves all Purchases in the database. | |
| /api/purchases/{purchaseId}/item | PUT | Updates the Item associated with an existing Purchase. | Long (itemId) |
| /api/purchases/{purchaseId}/user | PUT | Updates the User associated with an existing Purchase. | Long (userId) |
| /api/purchases/{purchaseId} | DELETE | Deletes a Purchase by its ID. | |


# Example JSON Bodies:

User (Also contains list of Purchase entities):    
```json  
{
    "name": "John Doe",
    "email": "john.doe@example.com"
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
Purchase:  
```json
{
    "userId": 1,
    "itemId": 1,
    "purchaseDate": "2023-10-06"
}
```
# Questions?

Contact:
* marlonbair1@gmail.com

