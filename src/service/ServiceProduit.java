/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.*;
import utils.DataSource;
import entite.produit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sof
 */
public class ServiceProduit implements IService<produit>{
    
     private Connection conn;
     private Statement ste;

    public ServiceProduit() {
        conn = DataSource.getInstance().getCnx();
    }


    @Override
    public boolean insert(produit t) {
          boolean insert=false;
        String req = "insert into produit (nom,image,prix,description,id_cat) values ('" + t.getNom() + "',"
        + "'" + t.getImage()+ "','" + t.getPrix()+ "','" + t.getDescription()+ "','" + t.getId_cat()+ "')";
          
       try {
      
            ste = conn.createStatement();
            insert=ste.executeUpdate(req)>0;

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }  return insert;
    }
    

    @Override
    public boolean update(produit t) {
        
         boolean update=false;
        String req ="update produit set nom = ? , image = ? , prix = ? , description = ? , id_cat = ? where id = ?";
         
        try {
             PreparedStatement ps = conn.prepareStatement(req);
             ps.setString(1, t.getNom());
             ps.setString(2, t.getImage());
             ps.setFloat(3, t.getPrix());
             ps.setString(4, t.getDescription());
             ps.setInt(5, t.getId_cat());
             ps.setInt(6, t.getId());
             
             update=ps.executeUpdate()>0;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
         } return update;
    }

    @Override
    public boolean delete(produit t) {
             
           boolean del=false;
           String req="delete from produit where id = ?";
        try {
             PreparedStatement ps = conn.prepareStatement(req);
             ps.setInt(1, t.getId());
             del=ps.executeUpdate()>0;
         } catch (SQLException ex) {
             Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
         } return del;
    }

    @Override
    public List<produit> getAll() {
        List<produit> list = new ArrayList<>();
        
        try {
            
        String req ="select * from produit";
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);
         
        while(rs.next()){
         produit p= new produit();
         p.setId(rs.getInt("id"));
         p.setNom(rs.getString("nom"));
         p.setPrix(rs.getFloat("prix"));
         p.setDescription(rs.getString("description"));
         p.setId_cat(rs.getInt("id_cat"));
         list.add(p);
         
         
        }
        } catch (SQLException ex) {
             Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
         }
       return list ;     }

    @Override
    public produit getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 }
