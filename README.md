# Currency Converter

## How To Run Application:

* This application has two possible profiles, one for development and testing environment and other for production environment.
* This application runs with an embedded H2 database.

**1** - **Running in development/testing environment**
**1.1** - Access the application-dev.properties file and replace the phrase "Your key here" with your exchangeratesapi api key  
**1.2** - Run the main method of the ConverterApplication.java file, with the following environment variable:
**1.2.1** - spring.profiles.active=dev;

**2** - **Running in production environment**
**2.1** - Run the main method of the ConverterApplication.java file, with the following environment variables:   
**2.1.1** - spring.profiles.active=prod;  
**2.1.2** - datasource.url="*url you choose to host the H2 database*";  
**2.1.3** - datasource.user="*username to access the H2 database*";  
**2.1.4** - datasource.password="*password to access the H2 databas*e";  
**2.1.5** - jwt.secret="*random security string*";  
**2.1.6** - api.key="*your exchangeratesapi api key*";

## How To Use The Application

**1** - **Create user account**
**1.1** - Send a POST request to endpoint "/users" with following body
**1.1.1** - email (mandatory, unique)
**1.1.2** - password (mandatory, have between 8 and 24 characters)
**1.2** - Example:

**2** - **Get access token**
**2.1** - Send a POST request to endpoint "/auth" with following body
**2.1.1** - email (mandatory)
**3.1.2** - password (mandatory)
**2.2** - Example:

**3** - **Convert currencies**
**3.1** - Send a POST request to endpoint "/convertions" with following body and add the your token
**3.1.1** - originCurrency (mandatory, 3 characters)
**3.1.2** - originValue (mandatory, a decimal number)
**3.1.3** - destinyCurrency (mandatory, 3 characters)
**3.2** - Example:

**4** - **Get my past conventions**
**4.1** - Send a GET request to endpoint "/convertions" with your token
**4.2** - Example: 

