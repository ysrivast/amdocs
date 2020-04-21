# amdocs

There are two microservice

1.authorization-gateway-service
2.user-profile-service
3.EurekaServer

authorization-gateway-service is endpoint to access any resource, it is working as gateway.
all request need to authorised for access any resouce. authorisation is implemented by using spring security with basic auth.
authorisation can we done by USER-user and Password-12345.


we have 3 request here:-

POST /profile
PUT /profile
Delete /profile

Post service is traditional rest service which is routed thorugh zuul gateway.
PUT and DELETE are event based working on publisher and subscriber model with kafka.




swagger documentation is avaliable :- 
http://localhost:8003/user-service/swagger-ui.html#
http://localhost:8003/swagger-ui.html#/

Before Starting project make sure Kafka server is up and running. Commands are below:-

Start Apache Zookeeper-
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

Start Apache Kafka-
.\bin\windows\kafka-server-start.bat .\config\server.properties



