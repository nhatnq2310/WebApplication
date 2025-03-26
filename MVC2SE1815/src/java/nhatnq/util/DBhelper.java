/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatnq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DBhelper {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        //1. Load driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. create connection String (dia chi ket noi database)
        //syntax:protocol: name_dbServer://ip:port;databaseName= name_DB
        String url = "jdbc:sqlserver://"
                +"localhost:1433;" 
                +"databaseName=NHATNQ";
        //3. Open connection from driver manager
        Connection con = DriverManager.getConnection(url , "sa", "12345");
        
        return con;
    }
}
