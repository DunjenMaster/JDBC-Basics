package com.utkarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JDBCConnectionMain{
    public static void  main(String[] args)
    {
     SpringApplication.run(JDBCConnectionMain.class, args);
     System.out.println("JDBC Connection Application Started Successfully!");
    }

}