/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.Joueur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 *
 * @author moham
 */


public class JoueurService implements IService<Joueur>{//relation entre entite et base de données
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public JoueurService()
    {
        conn=DataSource.getInstance().getCnx();
    }
    
    
    
    
    
//    @Override
//    public Boolean insert(Joueur p) 
//    {
//      String req="insert into personne (nom,prenom) values ('"+p.getNom()+"','"+p.getPrenom()+"')";
//        try {
//             ste=conn.createStatement();
//             ste.executeUpdate(req);
//        } catch (SQLException ex) {
//            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//     return true;
//    }
    
    
    
    @Override
    public boolean insert(Joueur j)
    {
        String req="insert into joueur (nom,prenom,poste,nationalite,age,taille,poids,image,id_equipe) values (?,?,?,?,?,?,?,?,?)";
        Boolean inserted=false;
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,j.getNom());
            pst.setString(2,j.getPrenom());
            pst.setString(3,j.getPoste());
            pst.setString(4,j.getNationalite());
            pst.setInt(5,j.getAge());
            pst.setFloat(6,j.getTaille());
            pst.setFloat(7,j.getPoids());
            pst.setString(8,j.getImage());
            pst.setInt(9,j.getEquipe().getId());
            inserted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }
    
    
    
    @Override
     public boolean update(Joueur j) 
    {
      String req="UPDATE joueur SET nom=?,prenom=?,poste=?,nationalite=?,age=?,taille=?,poids=?,image=?,id_equipe=? WHERE id=?";
       Boolean updated=false;
        try {
            pst=conn.prepareStatement(req);
         
            pst.setString(1,j.getNom());
            pst.setString(2,j.getPrenom());
            pst.setString(3,j.getPoste());
            pst.setString(4,j.getNationalite());
            pst.setInt(5,j.getAge());
            pst.setFloat(6,j.getTaille());
            pst.setFloat(7,j.getPoids());
            pst.setString(8,j.getImage());
            pst.setInt(9,j.getEquipe().getId());
            pst.setInt(10,j.getId());
            
            updated=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return updated;
    }

    
    
    
    
    @Override
     public boolean delete(Joueur j) 
    {
      String req="DELETE FROM joueur WHERE id=?";
      Boolean deleted=false;
        try {
             pst=conn.prepareStatement(req);
             pst.setInt(1,j.getId());
             deleted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return deleted;
    }

  

    @Override
    public List<Joueur> getAll() {
        String req="select *from joueur ";
        List<Joueur> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
                list.add(new Joueur(rs.getInt("id"),rs.getString(2),rs.getString("prenom")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
        
         }


    @Override
    public Joueur getOne(int id) {
        String req="select * from joueur where id=?";
        Joueur j=null;
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,id);
            EquipeService es =new EquipeService();
            rs = pst.executeQuery();
             if (rs.next())
             {              
                 j = new Joueur(rs.getInt(1), rs.getString(2), rs.getString(3), 
                 rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7),
                 rs.getFloat(8),rs.getString(9),es.getOne(rs.getInt(1)));
                
             }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return j;
        
    }
}
