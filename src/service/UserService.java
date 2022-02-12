/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import utils.DataSource;
import entite.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
       String req="insert into user (nom,prenom,email,pass,tel,id_role,image) values (?,?,?,?,?,?,?)";
        boolean inserted=false;
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,u.getNom());
            pst.setString(2,u.getPrenom());
            pst.setString(3,u.getEmail());
            pst.setString(4,u.getPass());
            pst.setString(5,u.getTel());
            pst.setInt(6,u.getId_role());
            pst.setString(7,u.getImage());
            inserted=pst.executeUpdate()>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
  }


  

    @Override
    public boolean update(User u) {
        String req="UPDATE user SET nom=?,prenom=?,email=?,pass=?,tel=?,id_role=?,image=? WHERE id=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);
            System.out.println(u);
            
            pst.setString(1,u.getNom());
            pst.setString(2,u.getPrenom());
            pst.setString(3,u.getEmail());
            pst.setString(4,u.getPass());
            pst.setString(5,u.getTel());
            pst.setInt(6,u.getId_role());
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
              list.add(new User(rs.getInt("id"), rs.getString(2),rs.getString("prenom"),rs.getString("email"),rs.getString("pass"),rs.getString("tel"),rs.getInt("id_role"),rs.getString("image")));
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
              rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getInt(7),rs.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
     
    }

   

    

   
}
