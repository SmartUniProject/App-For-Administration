/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartuni.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Shalitha Suranga
 */
public class MySqlConnector {
    
    private static final String DB_USER="root";
    private static final String DB_PASSWORD="";
    private static final String DB_URL="jdbc:mysql://localhost:3306/smartuni";
    private static Connection db=null;

    public static Connection getConnection(){
        //Connection db=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            db=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            
        }
        return db;
    }
}
