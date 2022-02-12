/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moham
 */
public class DataSource {
    
   private  String url="jdbc:mysql://localhost:3306/ftf";
   private  String login="root";
   private  String pwd="";
   
   private  Connection cnx;
   private static DataSource instance;
   
    private DataSource() 
    {
        
       try 
       {
           cnx=DriverManager.getConnection(url, login, pwd);
       } 
       
       catch (SQLException ex) 
       {
           Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    
    public static DataSource getInstance()
    {
        if(instance==null)
            instance=new DataSource();
        return instance;
    }
    
    public Connection getCnx()
    {
        return cnx;
    }

   
}
