/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Joueur;
import entite.Transfert;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author moham
 */
public class TransfertService implements IService<Transfert>{
     private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public TransfertService()
    {
        conn=DataSource.getInstance().getCnx();
    }
    
    
     @Override
      public boolean insert(Transfert t)
    {
        String req="insert into transfert (id_ancieneq,id_nouveaueq,id_joueur) values (?,?,?)";
        Boolean inserted=false;
         JoueurService js=new JoueurService();
        try {
            
            pst=conn.prepareStatement(req);
            pst.setInt(1,t.getAncieneq().getId());
            pst.setInt(2,t.getNouveaueq().getId());
            pst.setInt(3,t.getJoueur().getId());
            Joueur j=new Joueur (t.getJoueur().getId(),t.getJoueur().getNom(),t.getJoueur().getPrenom(),t.getJoueur().getPoste(),
                    t.getJoueur().getNationalite(),t.getJoueur().getDate_naiss(),t.getJoueur().getTaille(),t.getJoueur().getPoids(),
                    t.getJoueur().getImage(),t.getNouveaueq());
            js.update(j);
            inserted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    @Override
    public boolean update(Transfert t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Transfert t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transfert> getAll() {
        String req="select * from transfert";
        List<Transfert> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs=ste.executeQuery(req);
            EquipeService es =new EquipeService();
            JoueurService js= new JoueurService();
            while(rs.next())
            { 
                list.add(new Transfert(rs.getInt("id"),es.getOne(rs.getInt("id_ancieneq")),es.getOne(rs.getInt("id_nouveaueq")),js.getOne(rs.getInt("id_joueur"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
    }

    @Override
    public Transfert getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
