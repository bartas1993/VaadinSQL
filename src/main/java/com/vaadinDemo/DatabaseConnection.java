package com.vaadinDemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    public static Connection con() throws IOException, ClassNotFoundException {
    Connection connect = null;
    try{
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("C:\\Users\\barte\\OneDrive\\Desktop\\vaaddemo\\users.db");
    }
    catch(SQLException a)
    {
        a.printStackTrace();
    }

        return null;
    }
}
