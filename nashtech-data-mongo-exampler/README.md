## MongoDB CRUD Operations

### Overview
![Untitled Diagram drawio](https://user-images.githubusercontent.com/87006481/235065193-fe50d2f2-8d53-4f25-9267-ed703ce2ef52.png)

This module demonstrates a simple CRUD (Create, Read, Update, Delete) operations using MongoDBRepository of Sdk-core.

### Environment Variables:
Here are the environment variables used in this application:

| ENVIRONMENT VARIABLE     | Value         |
|--------------------------|---------------|
| PROFILE                  | local         |
| MONGO_DB_USERNAME        | nashtech      |
| MONGO_DB_PASSWORD        | nashtech      |
| MONGO_DB_HOST            | localhost     |
| MONGO_DB_PORT            | 27017         |
| MONGO_DB_DATABASE        | mydatabase    |
| MONGO_DB_AUTH_DATABASE   | admin         |

### Properties configuration
 MongoDb Data Configuration is provided by configuration properties entries (spring.data.mongodb. *) in application.propeerties file.
 We can connect mongoDB either Mongo DB  or Via Connection String.

```
# Mongo DB
spring.profiles.active=${PROFILE}
spring.data.mongodb.host=${MONGO_DB_HOST}
spring.data.mongodb.port=${MONGO_DB_PORT}
spring.data.mongodb.username=${MONGO_DB_USERNAME}
spring.data.mongodb.password=${MONGO_DB_PASSWORD}
spring.data.mongodb.authentication-database=${MONGO_DB_AUTH_DATABASE}
spring.data.mongodb.database=${MONGO_DB_DATABASE}

# Via Connection String
spring.data.mongodb.uri=mongodb://${MONGO_DB_USERNAME}:${MONGO_DB_PASSWORD}@${MONGO_DB_HOST}:${MONGO_DB_PORT}/${MONGO_DB_DATABASE}?authSource=${MONGO_DB_AUTH_DATABASE}
......
```

### How to use Nashtech MongoDBRepository in your application -
1. Run the command in sdk-core

 ````
 mvn clean install 
 ````
2. It will create a jar file, then we can use this jar in the other external application.
   For example, we have to add dependency of Nashtech Data Mongo in our example application.

 ````
 <dependency>
    <groupId>com.nashtechglobal</groupId>
     <artifactId>nashtech-data-mongo</artifactId>
     <version>0.0.1</version>
 </dependency>
   ````   
3. Via docker-compose file

 ````
 sudo docker-compose up -d
 ````
4. Run Spring Boot application

 ````
 mvn spring-boot:run
 ````



### Endpoints:

- Get all EntityModels 
  - **Get** : http://localhost:8080/api/v1/getResults

    Response Body:
     ```
     [
        {
            "userId": "grv_1",
            "testNo": 1,
            "testStatus": "D"
        },
        {
            "userId": "grv_5",
            "testNo": 5,
            "testStatus": "A"
        }
      ]
    ```
- Get a EntityModel by ID
    - **Get** : http://localhost:8080/api/v1/getResult/1

      Response Body:
       ```
        {
            "userId": "grv_1",
            "testNo": 1,
            "testStatus": "D"
        }
       ```
- Get a EntityModel by userId
    - **Get** : http://localhost:8080/api/v1/getResult/find/grv_1

      Response Body:
       ```
        [
            {
                "userId": "grv_1",
                "testNo": 1,
                "testStatus": "D"
            }
        ]
       ```
- Add a new EntityModel 
    - **Post** : http://localhost:8080/api/v1/upsertResult

      Request Body:
       ```
        {
            "testId" : 1,
            "testUserId" : "grv_1",
            "testNo" : 5,
            "testStatus" : "D"
        }
       ```
      Response Body:
       ```
        {
            "userId": "grv_1",
            "testNo": 5,
            "testStatus": "D"
        }
       ```

- Update an Existing Reactive Entity by ID
    - **Put** : http://localhost:8080/api/v1/upsertResult

      Request Body:
        ```
         {
             "testId" : 1,
             "testUserId" : "grv_1",
             "testNo" : 10,
             "testStatus" : "A"
         }
        ```

      Response Body:
      ```
       {
            "userId": "grv_1",
            "testNo": 10,
            "testStatus": "A"
       }
      ```

- Delete a EntityModel by ID
    - **Delete** : http://localhost:8080/api/v1/deleteResult/1

      Response Body:
      ```
       "OK"
      ```
These endpoints can be used to perform CRUD operations on EntityModel objects.
