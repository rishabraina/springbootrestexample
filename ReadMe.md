
For this project -:

Technology used - : Spring Boot , Spring Data JPA, MySQL Database 

Instructuions -:

1.) Import this project as  a maven project in your eclipse. You should have STS plugin installed in your eclipse if you are using eclipse else for Intellij it should work without any plugin.

2.) Install MySql onto your system and run mysql server. The settings and user name and password have been specified in application.properties file , but if you are running your mysql server on a different port other than the default port you have to change that port number in application.properties file too.

3.) Running your spring boot app should create two tables with their DDL's being specified by the model classes.

There will be two tables created 
product_info
product_price

Idea behind two tables is to maintain segregation i.e all the details related  to product information should be kept in one table and all details related to product price should be kept in seperate table.
They are both related via a foriegn key constraint.

4.) I have used Advanced REST Client in order to test my APIS. But automated tests can be created.You can download rest client on chrome. 

2.)  REST API -:

GET
http://localhost:8080/product/{productId} -> will fetch response e.g {"id":11,"current_price":{"value":13.49,"currency_code":"USD"}}

PUT 
http://localhost:8080/product/{productId} -> REquest Bodye.g {"id":11,"current_price":{"value":13.49,"currency_code":"USD"}}

GET
http://localhost:8080/product/productInfo/{productId} -> will fetch all details related to product id.

POST
http://localhost:8080/product -> Create entry into product info table . Request Body e.g {"product_desc":"boolmberg","product_name":"happy"}

POST
http://localhost:8080/product/{productId}/price -> Creates price entries related to product id in product_price table.
RequestBody - {"currency_code":"EUR","value":54.46}

DELETE
http://localhost:8080/product/{productId} -> Deleted the specified product entry from both tables. 
