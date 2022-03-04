/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import utils.DataSource;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.util.List;
import entite.*;
//import entite.Personne;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
        /**
 *
 * @author R I B
 */
public class ArbitresServices implements IService<Arbitres> {
 private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
     private PreparedStatement st;
    private ResultSet rs;

    public ArbitresServices() {
        conn = DataSource.getInstance().getCnx();
    }
    
    @Override
    public boolean insert(Arbitres a) {
     
        String req = "insert into arbitre (nom,prenom,id_role,image,age,email) values (?,?,?,?,?,?)";
       Boolean inserted=false;
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,a.getNom());
            pst.setString(2,a.getPrenom());
           if(a.getId_role()==null){
               pst.setNull(3,1);

            }else{
              pst.setInt(3,a.getId_role().getId());

            }
      
            pst.setString(4,a.getImage());
           pst.setInt(5,a.getAge());
           pst.setString(6,a.getEmail());
            inserted=pst.executeUpdate()>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        //Obtain only one instance of the SystemTray object
    
    
        return inserted;

    }

    @Override
    public boolean update(Arbitres a) {
      String req="UPDATE arbitre SET nom=?,prenom=?,id_role=?,image=?,age=?,email=? WHERE id=?";

            Boolean updated=false;
        try {
            pst=conn.prepareStatement(req);
         
            pst.setString(1,a.getNom());
            pst.setString(2,a.getPrenom());
            pst.setInt(3,a.getId_role().getId());
          
            pst.setString(4,a.getImage());
          pst.setInt(5,a.getAge());
          pst.setString(6,a.getEmail());
            pst.setInt(7,a.getId());
            
            updated=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
     return updated;     }

    @Override
    public boolean delete(Arbitres a) {
     String req="DELETE FROM arbitre WHERE id=?";
      Boolean deleted=false;
        try {
             pst=conn.prepareStatement(req);
             pst.setInt(1,a.getId());
             deleted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
     return deleted; }

    @Override
    public List<Arbitres> read() {
  String req="select * from arbitre";
        List<Arbitres> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs=ste.executeQuery(req);
            RolesServices s =new RolesServices();
            while(rs.next())
            { 
                list.add(new Arbitres(rs.getInt("id"),rs.getString(2),rs.getString("prenom"),s.read(rs.getInt("id_role")),rs.getString("image"),rs.getInt("age"),rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    }

    @Override
    public Arbitres readById(int id) {
   
   
   
     String req="select * from arbitre where id=?";
        Arbitres a=null;
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();
             if (resultSet.next()) {              
                    a = new Arbitres(resultSet.getInt(1), resultSet.getString(2), 
                          resultSet.getString(3));
                }
        } catch (SQLException ex) {
            Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return a;
}
    
  
}