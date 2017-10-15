/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartuni.classes.entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import smartuni.classes.common.SmartUniMain;
import smartuni.database.MySqlConnector;
import smartuni.ui.Dashboard;

/**
 *
 * @author Shalitha Suranga
 */
public class Admin {
    private int id;
    private String username;
    private String password;
    
    public Admin(int id){
       try {
            Connection db=MySqlConnector.getConnection();
            Statement st=db.createStatement();
            ResultSet result=st.executeQuery("SELECT * FROM sm_user WHERE id='"+id+"';");
            if(result.next()){
                this.id=Integer.parseInt(result.getString("id"));
                this.username=result.getString("username");
                this.password=result.getString("password");
            }
            else{
               
            }
       }
       catch(Exception e){
       }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
