package com.utkarsh;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration //-> This tells Spring that this class is a configuration class, which means it contains settings and properties for the application. It will be automatically detected and registered as a Spring bean. This is where you define beans and their configurations, so Spring knows how to create and manage them.
@ConfigurationProperties(prefix = "db") //-> This tells Spring to read properties from the application.properties file that start with "db" and map them to this class. For example, if you have a property like db.url=jdbc:postgresql://localhost:5432/demo, it will be mapped to the url field in this class.
@Getter
@Setter
public class JDBCConnectionConfiguration {
    private String url;
    private String userName;
    private String password;

}
