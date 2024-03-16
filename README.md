# **Order Service**
Order management service.

## **Requirements**
* JDK 17+
* Maven 3
* MySQL

## **Prerequisites**
Set these environment variables containing database credentials and user service url(OAUTH server).
`ORDER_SERVICE_DB_URL=jdbc:mysql://<host>:3306/<db_name>;
ORDER_SERVICE_DB_USERNAME=<db_username>;
ORDER_SERVICE_DB_PASSWORD=<db_password>
USER_SERVICE_URL=<oauth_url>
PRODUCT_SERVICE_URL=<product_service_base_url>`

## **Running the application locally**
`mvn spring-boot:run`

It will run on port 8484

To create jar and execute jar file:
`mvn package`

This will create jar file inside target\e-commerce-1.0.0.jar
To execute jar file:

`java -jar e-commerce-1.0.0.jar`

## **Endpoints:**
1. To get orders by orderId
   GET orders/<orderId>
2. To create order
   POST orders
   `{title:<>, description : <>, price : <>, }`
   
