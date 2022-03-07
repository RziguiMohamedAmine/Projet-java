/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Equipe;
import entite.JoueurMatch;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author moham
 */
public class JoueurMatchService implements IService<JoueurMatch> {
    
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public JoueurMatchService()
    {
          conn=DataSource.getInstance().getCnx();
    }

    
    
    
    
    
    @Override
    public boolean insert(JoueurMatch t) 
    {
        String req="insert into matchjoueur (id_joueur,id_match,carton_jaune,carton_rouge,nb_but) values (?,?,?,?,?)";
        Boolean inserted=false;
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,t.getJoueur().getId());
            pst.setInt(2,t.getMatch().getId());
            pst.setInt(3,t.getJaune());
            pst.setInt(4,t.getRouge());
            pst.setInt(5,t.getNb_but());
            inserted=pst.executeUpdate()>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    @Override
    public boolean update(JoueurMatch t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(JoueurMatch t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JoueurMatch> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JoueurMatch getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
}
