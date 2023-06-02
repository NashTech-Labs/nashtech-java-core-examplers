## Observability exampler supported by nashtech core

The exampler will demonstrate how to setup the observability in the application using the nashtech core observability module

### Highlevel architecture

![image](https://user-images.githubusercontent.com/102946997/234876183-17a00636-0599-4125-815d-d95c19eaec66.png)

### Nashtech core observability module

Include the nashtech-observability and nashtech-logging depndency to the pom.

````
 <dependency>
    <groupId>com.nashtechglobal</groupId>
     <artifactId>nashtech-observability</artifactId>
     <version>1.0-SNAPSHOT-SNAPSHOT</version>
  </dependency>
  <dependency>
    <groupId>com.nashtechglobal</groupId>
     <artifactId>nashtech-logging</artifactId>
     <version>1.0-SNAPSHOT-SNAPSHOT</version>
  </dependency>
````   

### How to run the exampler
1. Start up the observability stack (for demonstration purposes, we are using the provided Grafana, Tempo, and Loki stack) and wait for it to start.
 ````
 docker compose up -d
 ````
* To access Prometheus go to http://localhost:9090/
* To access Grafana go to http://localhost:3000/

2. Run the exempler
 ````
 mvn spring-boot:run -pl :nashtech-observability-exampler
 ````
3. Hit the following request from the preferred client
````
http://localhost:7654/v1/observation/endpoint/{arg}
````
4. Go to Grafana, go to dashboards, and click on the Logs, Traces, Metrics dashboard.
