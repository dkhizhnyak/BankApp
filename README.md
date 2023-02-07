# BankApp
# Requirements
    It's required to configure connection to a postgresql database
    It can be achieved by setting up a local postgresql server or running docker
    Then you must specify URL path in "application.properties" file in "spring.datasource.url" propertie (also ensure
    that your "spring.datasource.username", "spring.datasource.password" properties also set to a valid values)
    
Application allows user to create clients within system using postgresql database
All clients have possibility to have any number of accounts with some amount on balance

First you need to create a client, then create account for existing client, then you have possibility to deposit, withdrawal 
and transfer money from one account to another.
Application can be used through implemented UI form or through direct API calls:

## Save new client
~~~
  POST @/api/client/save
  # Request body: 
      {"name": String,
       "address": String,
       "age": Integer }
  # Response body contains data of created entity:
      {"id": Integer,
       "name": String,
       "address": String,
       "age": Integer }
~~~     

## Save new account for a client
~~~
    POST @/accounts/{id}/new
    # Request body:
        {"id": Integer,
         "amount": Integer}
    # Response body contains created account entity:
        {"id": Integer,
         "Client": Data of related client,
         "Balance": Integer}
~~~

## Transfer to another account
~~~
    Post @/transaction/transfer
    # Request body:
        {"from": accountId,
         "to": accountId,
         "amount": Integer}
~~~

       
  
