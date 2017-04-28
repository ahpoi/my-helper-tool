Helper App
==============

Helper app that have functions that need to be executed withing 


#### AWS KMS Encryption: ####

    - Encrypt Text 
    - Decrypt Text 

#### Symmetric Encryption:  ####
    
    - Generate Secret Key (AES)
    - Encrypt Text (AES)
    - Decrypt Text (AES)
    
#### APEX Lambda environment generator: ####

A simple Java Swing application that takes a CSV file and will generate the environment body for APEX;
It will also do the KMS encryption


    EG CSV File (Sample file is located under resources):
    
    Headers are: Key, Value, NeedsEncryption
    
    ENVIRONMENT, dev, false
    ROOT_URL, https://test.api.com, false
    API_KEY,api-key, true
    SECRET_KEY, secret-key, true
    
    Output: 
    
    {
      "environment" : {
        "ENVIRONMENT" : "dev"
        "ROOT_URL" : "https://test.api.promisepay.com",
        "API_KEY" : "AQECAHgGrFYTgmCpNonOULb4C5YlSlSCFGGNlIp9LiE..etc",
        "SECRET_KEY" : "AQECAHgGrFYTgmCpNonOULb4C5YlSlSCFGGNlIp9LiE..etc",
      }
    }
