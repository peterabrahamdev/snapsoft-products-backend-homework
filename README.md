# Snapsoft Products Backend Homework - Peter Abraham

This application solves an algorithmic problem of calculating the product array, excluding the current index, in three different ways. 
The results of each calculation are stored in a PostgreSQL database along with a comment and a timestamp. 
The application exposes API endpoints to perform the calculations and retrieve the calculations' history.

## Requirements
- Java 22 or later
- Maven
- PostgreSQL

## Setup
To run the application locally:
1. Clone this repository.
2. Configure the PostgreSQL database according to the configuration set in the `src/main/resources/application.yaml`.
3. Build the project using the Maven CLI: `mvn clean package`.
4. Run the project: `mvn spring-boot:run`.

## API Endpoints
- `POST /api/calculate/a`: Calculates the product array using method A, which utilizes memoization to store and reuse computed results. It enhances efficiency by avoiding redundant calculations for identical input arrays, therefore if the result is cached, the method completes with an O(1) time complexity.
- `POST /api/calculate/b`: Calculates the product array using method B, using nested loops for iteration. This approach results in an O(N^2) time complexity.
- `POST /api/calculate/b`: Calculates the product array using method C, utilizing an efficient two-pass approach. It initializes a result array and computes prefix products in the first pass, and suffix products in the second pass to populate the final result. Using the aforementioned in-place modification algorithm the time complexity results in O(N).
- `GET /api/history`: Retrieves a list of objects from the service based on an optional search term (that enables filtering), responding with the appropriate records.

## Interacting with API
- It is recommended using an API development platform such as Postman to send HTTP requests and interact with the endpoints.
- Examples for the calculations (`POST`):
  ```JSON
  {
    "inputArray": [1, 2, 3, 6, 9, 100],
    "comment": "This is a test comment for a calculation."
  }
  ```

  ```JSON
  {
    "inputArray": [],
    "comment": "I will result in a handled error."
  }
  ```
- Examples for the history (`GET`):
  ```
  http://localhost:3000/api/history
  ```

  ```
  http://localhost:3000/api/history?search=this%20is%20a%20test
  ```
  
