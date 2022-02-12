/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Equipe;
import entite.Joueur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author moham
 */
public class EquipeService implements IService<Equipe> {
    
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    
      
    public EquipeService()
    {
        conn=DataSource.getInstance().getCnx();
    }
    
     @Override
    public boolean insert(Equipe e)
    {
        String req="insert into equipe (nom,logo,nom_entreneur,niveau) values (?,?,?,?)";
        Boolean inserted=false;
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,e.getNom());
            pst.setString(2,e.getLogo());
            pst.setString(3,e.getNom_entreneur());
            pst.setString(4,e.getNiveau());
        
            inserted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }
    
    
      @Override
     public boolean update(Equipe e) 
    {
      String req="UPDATE equipe SET nom=?,logo=?,nom_entreneur=?,niveau=? WHERE id=?";
       Boolean updated=false;
        try {
            pst=conn.prepareStatement(req);
          
            pst.setString(1,e.getNom());
            pst.setString(2,e.getLogo());
            pst.setString(3,e.getNom_entreneur());
            pst.setString(4,e.getNiveau());
            pst.setInt(5,e.getId());
           
            updated=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return updated;
    }

      
    @Override
     public boolean delete(Equipe e) 
    {
      String req="DELETE FROM equipe WHERE id=?";
      Boolean deleted=false;
        try {
             pst=conn.prepareStatement(req);
             pst.setInt(1,e.getId());
             deleted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return deleted;
    }

    
     
     
     
    @Override
    public List<Equipe> getAll() {
        String req="select *from equipe ";
        List<Equipe> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
                list.add(new Equipe(rs.getInt("id"),rs.getString(2),rs.getString("logo"),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
        
         }
    
    
     @Override
    public Equipe getOne(int id) {
        String req="select * from equipe where id=?";
        Equipe e=null;
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();
             if (resultSet.next()) {              
                    e = new Equipe(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5));
                }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return e;
        
    }
    
    
    
    
    
    
    
    
    
}
