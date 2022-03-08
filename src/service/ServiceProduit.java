/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.categorie;
import java.sql.*;
import utils.DataSource;
import entite.produit;
import entite.taille;
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
     PreparedStatement pst;
      ResultSet rs;

    public ServiceProduit() {
        conn = DataSource.getInstance().getCnx();
    }


    @Override
    public boolean insert(produit t) {
          boolean insert=false;
        String req = "insert into produit (nom,image,prix,description,id_cat, stock,code) values (?,?,?,?,?,?,? )";
       try {
                        PreparedStatement ps = conn.prepareStatement(req);

               ps.setString(1, t.getNom());
             ps.setString(2, t.getImage());
             ps.setFloat(3, t.getPrix());
             ps.setString(4, t.getDescription());
             ps.setInt(5, t.getCat().getId());
             ps.setInt(6, t.getStock());
             ps.setInt(7, t.getCode());
             
             
             
            insert=ps.executeUpdate()>0;

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }  return insert;
    }
    

    @Override
    public boolean update(produit t) {
        
         boolean update=false;
        String req ="update produit set nom = ? , image = ? , prix = ? , description = ? , id_cat = ?, stock=stock+?,code= ? where id = ?";
         
        try {
             PreparedStatement ps = conn.prepareStatement(req);
             ps.setString(1, t.getNom());
             ps.setString(2, t.getImage());
             ps.setFloat(3, t.getPrix());
             ps.setString(4, t.getDescription());
             ps.setInt(5, t.getCat().getId());
             ps.setInt(6, t.getStock());
             ps.setInt(7, t.getCode());
             ps.setInt(8, t.getId());
             
           
             
             update=ps.executeUpdate()>0;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
         } return update;
    }

    
     public boolean updatecode(int id) {
        
         boolean update=false;
        String req ="update produit set code= ? where id = ?";
         
        try {
             PreparedStatement ps = conn.prepareStatement(req);
            
             ps.setInt(1, id*1000+12*6);
             ps.setInt(2, id);
             
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
            
        String req ="SELECT p.code,p.stock,p.id id_produit, p.nom nom_produit, p.image,p.prix ,\n" +
      " p.description, p.id_cat, c.nom nom_cat FROM produit "
                + "p  JOIN categorie c on p.id_cat=c.id ";
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);
         
        while(rs.next()){
         produit p= new produit();
         p.setId(rs.getInt("id_produit"));
         p.setNom(rs.getString("nom_produit"));
         p.setPrix(rs.getFloat("prix"));
         p.setDescription(rs.getString("description"));
         categorie c= new categorie(rs.getInt("id_cat"), rs.getString("nom_cat"));
         p.setCat(c);
         p.setImage(rs.getString("image"));
         p.setStock(rs.getInt("stock"));
         p.setCode(rs.getInt("code"));
        
  
         list.add(p);
        }
        } catch (SQLException ex) {
             Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
         }
       return list ;     }

    @Override
    public produit getOne(int id) {
        String req="SELECT  p.id id_produit, p.nom nom_produit, p.image,p.prix ,"
                + "p.description, p.id_cat, c.nom nom_categorie, p.stock,p.code,p.id_t FROM produit p JOIN categorie c on p.id_cat=c.id where p.id=?";
        produit p=null;
        try {
            pst= conn.prepareStatement(req);
            pst.setInt(1,id);
                       
            rs = pst.executeQuery();
             if (rs.next())
             {       
                 categorie c= new categorie(rs.getInt("id_cat"), rs.getString("nom_categorie"));

                 p = new produit(
                         rs.getInt(1), 
                         rs.getString(2),
                         rs.getString(3),
                         rs.getFloat(4),
                         rs.getString(5),
                         c, 
                         rs.getInt("stock"),
                         rs.getInt("code")
                        )
                                 ;     
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return p;

    }
    
    
    
     public produit getOnebyname(String nom) {
        String req="SELECT p.id id_produit, p.nom nom_produit, p.image,p.prix ,"
                + "p.description, p.id_cat, c.nom nom_categorie FROM produit p JOIN categorie c on p.id_cat=c.id where p.nom=?";
        produit p=null;
        try {
            pst= conn.prepareStatement(req);
            pst.setString(1,nom);
                       
            rs = pst.executeQuery();
             if (rs.next())
             {     
                categorie c= new categorie(rs.getInt("id_categorie"), rs.getString("nom_categorie"));
                 p = new produit(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getString(5),c);     
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return p;

    }
     
     public int getLastProduct(){
          String req="SELECT max(id) id FROM produit";
        int p = 0;
        try {
            pst= conn.prepareStatement(req);
            rs = pst.executeQuery();
             if (rs.next())
             {     
                p=rs.getInt("id");
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return p;
     }
    
    
    
    

 }
