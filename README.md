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
Application can be used through implemented UI form available at @/clients URL or through direct API calls:

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

## Get a list of clients
~~~
    GET @/api/clients
    
    #Return list of all existing clients
~~~

## Get a list of accounts for a particular client
~~~
    GET @/api/accounts/{id} (id - clientId)
    
    #Return list of all existing accounts for a particular client
~~~

## Save new account for a client
~~~
    POST @/api/accounts/{id}/new
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
    POST @/transaction/transfer
    # Request body:
        {"from": accountId,
         "to": accountId,
         "amount": Integer}
~~~

## Deposit to the account
~~~
    POST @/transaction/deposit/{id} (id is a accountId)
    # Request body:
        Integer number
~~~

## Withdrawal from the account
~~~
    POST @/transaction/withdrawal/{id} (id is a accountId)
    # Request body:
        Integer number
~~~

## Get list of all existing transactions
~~~
    GET @/api/transactions
    #Response body:
        List of all existing transactions
~~~

## Get list of transactions by a date range
~~~
        GET @/transactions/period
    #Request params:
        from: yyyy/MM/dd
        to: yyyy/MM/dd
    #Response body:
        list of transactions filtered ba a date range
~~~
       
  
