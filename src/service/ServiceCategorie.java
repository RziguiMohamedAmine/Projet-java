/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.*;
import utils.DataSource;
import entite.categorie;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sof
 */
public class ServiceCategorie implements IService<categorie> {

    
    private Connection conn;
     private Statement ste;

    public ServiceCategorie() {
        conn = DataSource.getInstance().getCnx();
    }
    
    
    @Override
    public boolean insert(categorie t) {
        boolean insert=false;
        String req = "insert into categorie (nom) values ('" + t.getNom()+ "')";
          
       try {
      
            ste = conn.createStatement();
            insert=ste.executeUpdate(req)>0;

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }  return insert;

    }

    @Override
    public boolean update(categorie t) {
        boolean update=false;
        String req ="update categorie set nom = ? where id = ?";
         
       
           try {
             PreparedStatement ps = conn.prepareStatement(req);
             ps.setString(1, t.getNom());
             
             ps.setInt(6, t.getId());
             
             update=ps.executeUpdate()>0;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
         } return update;
             

    }

    @Override
    public boolean delete(categorie t) {
        boolean del=false;
           String req="delete from categorie where id = ?";
        try {
             PreparedStatement ps = conn.prepareStatement(req);
             ps.setInt(1, t.getId());
             del=ps.executeUpdate()>0;
         } catch (SQLException ex) {
             Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
         } return del;
    }

    @Override
    public List<categorie> getAll() {
         List<categorie> list = new ArrayList<>();
        
        try {
            
        String req ="select * from categorie";
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);
         
        while(rs.next()){
         categorie p= new categorie();
         p.setId(rs.getInt("id"));
         p.setNom(rs.getString("nom"));
        
         list.add(p);
         
         
        }
        } catch (SQLException ex) {
             Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
         } return list ; 
    }

    @Override
    public categorie getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
