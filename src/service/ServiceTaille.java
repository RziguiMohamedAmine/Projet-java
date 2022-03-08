/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.produit;
import entite.taille;
import java.sql.*;
import utils.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sof
 */
public class ServiceTaille implements IService<taille>{
    
      private Connection conn;
      private Statement ste;
      PreparedStatement pst;
      ResultSet rs;
    
    public ServiceTaille() {
        conn = DataSource.getInstance().getCnx();
    }
    
    

    @Override
    public boolean insert(taille t) {
        boolean insert=false;
        String req = "insert into taille (nom, id_produit, stock) values ('" + t.getNom()+ "', "+t.getProd().getId()+","+t.getStock()+")";
          
       try {
      
            ste = conn.createStatement();
            insert=ste.executeUpdate(req)>0;

        } catch (SQLException ex) {
            Logger.getLogger(ServiceTaille.class.getName()).log(Level.SEVERE, null, ex);
        }  return insert;    }

    @Override
    public boolean update(taille t) {
        boolean update=false;
        String req ="update taille set nom = ?, id_produit=?, stock=? where id = ?";
         
       
           try {
             pst= conn.prepareStatement(req);
             pst.setString(1, t.getNom());
             pst.setInt(2, t.getProd().getId());
             pst.setInt(3, t.getStock());

             
             pst.setInt(4, t.getId());
             
             update=pst.executeUpdate()>0;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceTaille.class.getName()).log(Level.SEVERE, null, ex);
         } return update;    }

    @Override
    public boolean delete(taille t) {
        boolean del=false;
           String req="delete from taille where id = ?";
        try {
            pst = conn.prepareStatement(req);
             pst.setInt(1, t.getId());
             del=pst.executeUpdate()>0;
         } catch (SQLException ex) {
             Logger.getLogger(ServiceTaille.class.getName()).log(Level.SEVERE, null, ex);
         } return del;    }

    @Override
    public List<taille> getAll() {
        List<taille> list = new ArrayList<>();
        
        try {
            
        String req ="select t.id,t.nom nom_taille, t.stock stock_taille, p.nom nom_produit, p.image, p.prix, p.description, p.stock,p.code,p.id id_produit from taille t join produit p on p.id = t.id_produit";
        
         ste = conn.createStatement();
        ResultSet rs = ste.executeQuery(req);
         
        while(rs.next()){
         taille t= new taille();
         t.setId(rs.getInt("id"));
         t.setNom(rs.getString("nom_taille"));
         t.setStock(rs.getInt("stock_taille"));
         produit p = new produit(
                         rs.getInt("id_produit"), 
                         rs.getString("nom_produit"),
                         rs.getString("image"),
                         rs.getFloat("prix"),
                         rs.getString("description"),
                         null, 
                         rs.getInt("stock"),
                         rs.getInt("code")
                        );
         t.setProd(p);
         list.add(t);
         
         
        }
        } catch (SQLException ex) {
             Logger.getLogger(ServiceTaille.class.getName()).log(Level.SEVERE, null, ex);
         } return list ; 
    }

    @Override
    public taille getOne(int id) {
       String req ="select t.id,t.nom nom_taille, t.stock stock_taille, p.nom nom_produit, p.image, p.prix, p.description, p.stock,p.code,p.id id_produit from taille t join produit p on p.id = t.id_produit";
       taille t= new taille();
 
       try {
         ste = conn.createStatement();
        ResultSet rs = ste.executeQuery(req);
         
         t.setId(rs.getInt("id"));
         t.setNom(rs.getString("nom_taille"));
         t.setStock(rs.getInt("stock_taille"));
         produit p = new produit(
                         rs.getInt("id_produit"), 
                         rs.getString("nom_produit"),
                         rs.getString("image"),
                         rs.getFloat("prix"),
                         rs.getString("description"),
                         null, 
                         rs.getInt("stock"),
                         rs.getInt("code")
                        );
         t.setProd(p);
         
         
        } catch (SQLException ex) {
             Logger.getLogger(ServiceTaille.class.getName()).log(Level.SEVERE, null, ex);
         }
            
        return t;    
    }
    
    public List<taille> getTaillesProd(produit p) {
        List<taille> list = new ArrayList<>();
        
        try {
            
        String req ="select t.id,t.nom nom_taille, t.stock stock_taille, p.nom nom_produit, p.image, p.prix, p.description, p.stock,p.code,p.id id_produit from taille t join produit p on p.id = t.id_produit where id_produit="+p.getId();
         ste = conn.createStatement();
        ResultSet rs = ste.executeQuery(req);
         
        while(rs.next()){
         taille t= new taille();
         t.setId(rs.getInt("id"));
         t.setNom(rs.getString("nom_taille"));
         t.setStock(rs.getInt("stock_taille"));
        
         t.setProd(p);
         list.add(t);
         
         
        }
        } catch (SQLException ex) {
             Logger.getLogger(ServiceTaille.class.getName()).log(Level.SEVERE, null, ex);
         } return list ; 
    }
    
}
