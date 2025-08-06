## Getting Started

1. In this code base we are going to estabilish the code with MySQL database.
2. With all the steps we have implemneted in the JDBC connectivity with Postgress from IntelijIdea Ide; we'll implement the same in this but with MySQL JDBC connection.

## Steps followed to establish the connection with Statement interface!
3. Steps from estabilishing the connection to closing it:
    a. Register the driver connection(automatically happens in Jdbc now.)
    b. With Drivermanager class call the getConnection() method with Connection reference and pass the parameter here such as url,hostname,password. Or you can avoid the hardcoding of those and use application.properties file.
    c. Then use the statement interface with createStatement initially to let the database know we will be sending request to database.
    d. Followed by using the reference of ResultSet interface with statement's executeQuery() method and pass the SQL query in it. This will fetch the data from the database and return it with the help of .next() method as it allows to move the cursor  in next line and check if it holds some value. 
    e. It's time to close the connection with .close() method.

4. Every steps will be inside the try-catch block and handelling the exception.

## Steps followed to estabilish the connection with PreparedStatament interface!
5. From Step 3a to 3b will be same. 
6. Now we will use the PreparedStatement(which is child interface to Statement) with preparedStatement() method. 
    -> This method has an advantage, let say if you don't know what parameter/value will be sent by user, then you can use "?" for it. And call the set methods per the data type it fetch it accordingly.
    -> Most important it will help preventing the SQL injection attacks and to handle dynamic queries more efficently. 
7. Followed by calling the execute() method which will fetch the data. 
8. Finally, close the connection with .close() method. 

Remember: To handle the Exception in both the cases with try-catch block. 