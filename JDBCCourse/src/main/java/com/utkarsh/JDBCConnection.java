/* TODO:  Steps to connect to the database using JDBC:
        Step1 - import package
        Step2 - load and register your driver
        Step3 - create the connection
        Step4 - create the statement
        Step5 - process the results
        Step6 - close the connection
*/

package com.utkarsh;


import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Class.forName;
import static java.sql.DriverManager.getConnection;

public class JDBCConnection{
        public static void main(String[] args)
        {
            //Database URL, username and password initialization.
            String url = "jdbc:postgresql://localhost:5432/demo";
            String username = "postgres";
            String password = "";

            try{
                //Step1 - import package - Done in the import statement above

                //Step2 - Load and register your driver
                Class.forName("org.postgresql.Driver");

                //Step3 - Create the Connection
                Connection con = DriverManager.getConnection(url, username, password);
                System.out.println("Connection established successfully!");



            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
}