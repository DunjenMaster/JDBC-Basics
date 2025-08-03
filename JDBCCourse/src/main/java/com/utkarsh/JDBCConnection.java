/* TODO:  Steps to connect to the database using JDBC:
        Step1 - import package
        Step2 - load and register your driver
        Step3 - create the connection
        Step4 - create the statement
        Step5 - process the results
        Step6 - close the connection
*/

package com.utkarsh;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.Class.forName;
import static java.sql.DriverManager.getConnection;

@Service //-> This tells Spring that this class is a service, which means it contains business logic and can be used by other parts of the application. It will be automatically detected and registered as a Spring bean. This marks a class as something that does work for your app, and Spring will manage it for you.
public class JDBCConnection{

    @Autowired //-> This tells Spring to inject the JDBCConnectionConfiguration bean into this class, so you can use its properties. This means Spring will automatically create and give you the needed object of JDBCConnectionConfiguration, so you don’t have to use new to make it yourself.
    private JDBCConnectionConfiguration dbProps;

    @PostConstruct //-> This tells Spring to run the method right after everything is set up and ready, so you can do any final setup work.
        public void connect()
        {
            try{

                //Step3 - Create the Connection
                Connection con = DriverManager.getConnection(dbProps.getUrl(), dbProps.getUserName(), dbProps.getPassword());
                System.out.println("Connection established successfully!");

                // ================================================================================================================================================================================================================================

                //Step4 - Create the statement : This is not needed for just establishing a connection, but is used when you want to execute SQL queries.
                Statement st = con.createStatement();

                // ================================================================================================================================================================================================================================
                //Step5: CRUD OPERATIONS: We are going to delete a record into the database and then retrieve it.
                boolean status = st.execute("DELETE FROM student WHERE sname='Rahul' OR sid=5");
                System.out.println(status);
                /*Note: The execute method in JDBC returns a boolean:
                        true if the result is a ResultSet (e.g., for SELECT queries)
                        false if the result is an update count or no result (e.g., for INSERT, UPDATE, DELETE)
                        So,when you run an INSERT statement with execute, it returns false because there is no ResultSet—but the data is still inserted. This is normal behavior
                */

                // ================================================================================================================================================================================================================================

                //Step6 -  Close the connection
                con.close();
                System.out.println("Connection closed successfully!");

            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
}