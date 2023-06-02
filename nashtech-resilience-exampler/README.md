# NashTech Resilience Exampler 

### Architect Diagram 

![diagram](https://i.postimg.cc/KvJ48HdH/Microsoft-Teams-image-1.png)


### Overview

Nashtech exampler provides you some cross-cutting functionalities

### Functionalities offered by Nashtech Resilience

1. **getCircuitBreakerExternalApiResponse() :** This method takes ExamplerApiRequest type of request and return the
   ExamplerApiResponse type response and its also provide the circuit breaker functionality with fallback implemented respectively


   The default configurations for the circuitbreaker

   | Configuration                         | Default value |
   |---------------------------------------|---------------|
   | slidingWindowType                     | COUNT_BASED   |
   | failureRateThreshold                  | 50%           |
   | waitDurationInOpenState               | 60s           |
   | minimumNumberOfCalls                  | 100           |
   | permittedNumberOfCallsInHalfOpenState | 10            |
   | maxWaitDurationInHalfOpenStat         | 0s            |

   We can override these configuration from the application.yaml file as we implemented in our exampler .
   
   | Configuration                         | Override value |
   |---------------------------------------|----------------|
   | slidingWindowType                     | COUNT_BASED    |
   | failureRateThreshold                  | 50%            |
   | waitDurationInOpenState               | 100s           |
   | minimumNumberOfCalls                  | 5              |
   | permittedNumberOfCallsInHalfOpenState | 10             |
   | maxWaitDurationInHalfOpenStat         | 2s             |

2.  **callExternalApiWithRateLimiter() :** This method takes ExamplerApiRequest type of request and return the
    ExamplerApiResponse type response and its also provide the RateLimiter functionality with fallback implemented respectively


   The default configurations for the ratelimiter

   | Configuration      | Default value |
   |--------------------|---------------|
   | timeoutDuration    | 5s            |
   | limitRefreshPeriod | 500ns         |
   | limitForPeriod     | 50            |

   We can override these configuration from the application.yaml file as we implemented in our exampler.

   | Configuration      | Override value |
   |--------------------|----------------|
   | timeoutDuration    | 10s            |
   | limitRefreshPeriod | 100s           |
   | limitForPeriod     | 2              |

3.  **callExternalApiWithRetry() :** This method takes ExamplerApiRequest type of request and return the
    ExamplerApiResponse type response and its also provide the retry functionality with fallback implemented respectively


   The default configurations for the retry
   
   | Configuration | Default value |
   |---------------|---------------|
   | maxAttempts   | 3             |
   | waitDuration  | 500ms         |
   
   We can override these configuration from the application.yaml file as we implemented in our exampler.
   
   | Configuration | Override value  |
   |---------------|-----------------|
   | maxAttempts   | 3               |
   | waitDuration  | 1000ms          |

4.  **callExternalApiWithBulkHead() :** This method takes ExamplerApiRequest type of request and return the
    ExamplerApiResponse type response and its also provide the bulkhead functionality with fallback implemented respectively


   The default configurations for the bulkhead

   | Configuration      | Default value |
   |--------------------|---------------|
   | maxConcurrentCalls | 25            |
   | maxWaitDuration    | 0             |

   We can override these configuration from the application.yaml file as we implemented in our exampler.

   | Configuration      | Override value   |
   |--------------------|------------------|
   | maxConcurrentCalls | 20               |
   | maxWaitDuration    | 10ms             |

5. **getCircuitBreakerAndRetryExternalApiResponse() :** This method takes ExamplerApiRequest type of request and return the
   ExamplerApiResponse type response and its also provide the circuitbreaker and retry combine functionality with fallback implemented respectively

### How to run the application

1. Firstly you have to up the docker

 ```
  sudo docker-compose up -d
```
2. then run command
 ```
 mvn spring-boot:run -pl nashtech-resilience-exampler
```


### How to use Nashtech Resilience in your application

1. Run the command

    ```
    mvn clean install
    ```

2. It will create a jar file, then we can use this jar in the other external application.   
   For example, we can add dependency of Nashtech resilience in our example application

    ```
    <dependency>
       <groupId>com.nashtechglobal</groupId>
       <artifactId>nashtech-resilience</artifactId>
       <version>${project.parent.version}</version>
    </dependency>
    ```

3. Now, you will be able to access the **Resilience Service** and all the function present in it by creating an instance
   of it.