# BankApp
Application allows user to create clients within system using postgresql database
All clients have possibility to have any number of accounts with some amount on balance

Appliaction can be used through implemented UI form or through direct API calls:
## Usage 
~~~java
  POST @/api/client/save
  # Request body: 
      {"name": String,
       "address": String,
       "age": int }
  # Response body contains data of created entity:
      {"id": Long,
       "name": String,
       "address": String,
       "age": int }
~~~     

       
  
