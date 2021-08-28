# Currency Converter

## How To Run Application:

* This application has two possible profiles, one for development and testing environment and other for production environment.
* This application runs with an embedded H2 database.

**1** - Running in development/testing environment  
**1.1** - Access the application-dev.properties file and replace the phrase "Your key here" with your exchangeratesapi api key  
**1.2** - Run the main method of the ConverterApplication.java file, with the following environment variable: spring.profiles.active=dev

**2** - Running in production environment  
**2.1** - Run the main method of the ConverterApplication.java file, with the following environment variables:   
**2.1.1** - spring.profiles.active=prod;  
**2.1.2** - datasource.url="*url you choose to host the H2 database*";  
**2.1.3** - datasource.user="*username to access the H2 database*";  
**2.1.4** - datasource.password="*password to access the H2 databas*e";  
**2.1.5** - jwt.secret="*random security string*";  
**2.1.6** - api.key="*your exchangeratesapi api key*";
