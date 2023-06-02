## WebFlux with MongoDB CRUD Operations

This Repository demonstrates a simple CRUD (Create, Read, Update, Delete) operations using Spring WebFlux with MongoDB.

### Architecture Diagram
![image](https://user-images.githubusercontent.com/102946997/235067623-9489d856-c00b-42b7-b528-32984593bd89.png)

### Environment Variables:
Here are the environment variables used in this application:

| ENVIRONMENT VARIABLE   | Value            |
|------------------------|------------------|
| PROFILE                | local            |
| MONGO_DB_USERNAME      | nashtech         |
| MONGO_DB_PASSWORD      | nashtech         |
| MONGO_DB_CLUSTER       | localhost:27017  |
| Mongo_DB_DATABASE      | ReactiveEntityDB |
| MONGO_DB_AUTH_DATABASE | admin            |

### Endpoints:

- Get all Reactive Entities
    - **Get** : http://localhost:8081/reactive/entities

      Response Body:
       ```
       [
          {
             "id": "101",
             "testingStringData": "Entity 1",
             "testingLongData": 202101,
             "testingDateData": "2023-04-12T22:30:00.000Z"
          },
          {
             "id": "102",
             "testingStringData": "Entity 2",
             "testingLongData": 303102,
             "testingDateData": "2023-04-12T21:15:20.000Z"
          }
       ]
      ```
- Get a Reactive Entity by ID
    - **Get** : http://localhost:8081/reactive/entities/101

      Response Body:
       ```
        {
           "id": "101",
           "testingStringData": "Entity 1",
           "testingLongData": 202101,
           "testingDateData": "2023-04-12T22:30:00.000Z"
        }
       ```
- Add a new Reactive Entity
    - **Post** : http://localhost:8081/reactive/entities

      Request Body:
       ```
       {
          "id": "101",
          "testingStringData": "Entity 1",
          "testingLongData": 202101,
          "testingDateData": "2023-04-12T22:30:00.000Z"
       }
       ```
      Response Body:
       ```
       {
          "id": "101",
          "testingStringData": "Entity 1",
          "testingLongData": 202101,
          "testingDateData": "2023-04-12T22:30:00.000Z"
       }
       ```

- Update an Existing Reactive Entity by ID
    - **Put** : http://localhost:8081/reactive/entities/101

      Request Body:
        ```
        {
          "id": "101",
          "testingStringData": "Entity 1",
          "testingLongData": 202101,
          "testingDateData": "2023-04-12T22:30:00.000Z"
        }
        ```

      Response Body:
      ```
      {
         "id": "101",
         "testingStringData": "Updated Entity 1",
         "testingLongData": 2000001111111000003333333,
         "testingDateData": "2023-04-13T01:00:00.000Z"
      }
      ```

- Delete a Reactive Entity by ID
    - **Delete** : http://localhost:8081/reactive/entities/101

      Response Body:
      ```
      []
      ```
These endpoints can be used to perform CRUD operations on ReactiveEntity objects.
