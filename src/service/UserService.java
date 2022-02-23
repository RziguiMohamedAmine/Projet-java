/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import com.mysql.cj.protocol.Message;
import utils.DataSource;
import entite.User;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Properties;



/**
 *
 * @author hamdi
 */
public class UserService implements IService<User>{
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
   
    public UserService() {
        conn=DataSource.getInstance().getCnx();
    }
    
    
   public boolean insert(User u){
       String req="insert into user (nom,prenom,email,pass,tel,role,image) values (?,?,?,?,?,?,?)";
        boolean inserted=false;
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,u.getNom());
            pst.setString(2,u.getPrenom());
            pst.setString(3,u.getEmail());
            pst.setString(4,doHashing(u.getPass()));
            pst.setString(5,u.getTel());
            pst.setString(6,u.getRole());
            pst.setString(7,u.getImage());
            inserted=pst.executeUpdate()>0;
        
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
  }


  

    @Override
    public boolean update(User u) {
        String req="UPDATE user SET nom=?,prenom=?,email=?,pass=?,tel=?,role=?,image=? WHERE id=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);
            
            pst.setString(1,u.getNom());
            pst.setString(2,u.getPrenom());
            pst.setString(3,u.getEmail());
            pst.setString(4,doHashing(u.getPass()));
            pst.setString(5,u.getTel());
            pst.setString(6,u.getRole());
            pst.setString(7,u.getImage());
            pst.setInt(8,u.getId());
            update=pst.executeUpdate()>0;

            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }return update;
}

    @Override
    public boolean delete(User u) {
         String req="DELETE FROM user WHERE id=?";
         boolean delete=false;
        try {
            pst =conn.prepareStatement(req);
            pst.setInt(1,u.getId());
            delete=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return delete;
    }

    @Override
    public List<User> getAll() {
        String req="select * from user";
         List<User> list=new ArrayList<>();
        try {
           
            ste=conn.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
              list.add(new User(rs.getInt("id"), rs.getString(2),rs.getString("prenom"),rs.getString("email"),"",rs.getString("tel"),rs.getString("role"),rs.getString("image")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    
    
    
    @Override
    public User getOne(int id)
    {
        String req="select * from user where id=?";
        User u = null;
        try 
        {
            pst=conn.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            if(rs.next())
            {
                u=new User(rs.getInt(1),rs.getString(2),rs.getString(3),
              rs.getString(4),"",rs.getString(6),
                        rs.getString(7),rs.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
     
    }
    public  String doHashing (String pass) {
  try {
   MessageDigest ms = MessageDigest.getInstance("MD5");
   
   ms.update(pass.getBytes());
   
   byte[] resultByteArray = ms.digest();
   
   StringBuilder sb = new StringBuilder();
   
   for (byte b : resultByteArray) {
    sb.append(String.format("%02x", b));
   }
   
   return sb.toString();
   
  } catch (NoSuchAlgorithmException e) {
   e.printStackTrace();
  }
  
  return "";
 }
    
   public boolean block(Timestamp date, User user){
       
       String req="UPDATE user SET block=? WHERE id=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);
            
            pst.setTimestamp(1,date);
            pst.setInt(2,user.getId());
           
            update=pst.executeUpdate()>0;

            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
       
   }
   
  
   
   public boolean changePassForgetPassword(String email, String code, String newPass){
       String req="UPDATE user SET pass=?, forgetpassCode=null WHERE email=? and forgetpassCode=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);

            pst.setString(1,doHashing(newPass));
                        pst.setString(2,email);

            pst.setString(3,code);
           
            update=pst.executeUpdate()>0;
            

            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
   }
   public boolean ban(User user){
        String req="UPDATE user SET ban=1 WHERE id=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);
            
            pst.setInt(1,user.getId());
           
            update=pst.executeUpdate()>0;

            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
       
   }
public boolean login(String email, String pass){
    String hashedPass = doHashing(pass);
    int count = 0;
    Timestamp nowDate=Timestamp.from(Instant.now());
    String req="select * from user where email = '"+email+"' and pass= '"+hashedPass+"' and (block<'"+nowDate+"' or block is null) and ban = 0";
    
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            if(rs.next()){
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count!=0;
    
}    


}	
