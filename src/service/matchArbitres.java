/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Arbitres;
import entite.Equipe;
import entite.Match;
import entite.Roles;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author R I B
 */
public class matchArbitres {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
     private PreparedStatement st;
    private ResultSet rs ,r;

    public matchArbitres() {
        conn = DataSource.getInstance().getCnx();
    }

  public List<Integer> CountmatchByarbitres(int id){
        String  req="SELECT COUNT(*) as count FROM matchs WHERE id_arbitre1=? or id_arbitre2=? or id_arbitre3=? or id_arbitre4=? ";
        List<Integer> list = new ArrayList<>();
        try {
            pst= conn.prepareStatement(req);
            pst.setInt(1,id);
            pst.setInt(2,id);
            pst.setInt(3,id);
            pst.setInt(4,id);
       //  System.out.println(pst);
            rs = pst.executeQuery();
            Arbitres a = new Arbitres();
          
            if (rs.next())
             {           
                 int count = rs.getInt("count");
                 list.add((int) count);
                 
                 
                 
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(matchArbitres.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return list;
    }
  
  
 public List<Arbitres> rechercherParNom(String nom) throws SQLException{
        List<Arbitres> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM arbitre where nom LIKE '"+nom+"' or prenom LIKE '"+nom+"' ";
            Statement pst = conn.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
               Arbitres a = new Arbitres();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
            //    a.setId_role(new Roles(rs.getInt("id"),rs.getString("role"),rs.getString("Dscrp")));
                a.setImage(rs.getString("image"));
                a.setAge(rs.getInt("age"));
                a.setEmail(rs.getString("email"));
                list.add(a);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
  
  public List<Arbitres> getARBparRole(int id_role) {
        String req="select * from arbitre where id_role=?";
        
        List<Arbitres> list =new ArrayList<>();
         ArbitresServices es =new ArbitresServices();
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,id_role);
            rs = pst.executeQuery();
             while (rs.next())
             {                                     RolesServices s =new RolesServices();

                 list.add(new Arbitres(rs.getInt(1), rs.getString(2), rs.getString(3), 
                 s.read(rs.getInt("id_role")),rs.getString(5),rs.getInt(6),rs.getString(7)));
                
             }
        } catch (SQLException ex) {
            Logger.getLogger(matchArbitres.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return list;
        
    }
  
    public List<Match> getmatchyARB(Arbitres a) {
        String sql = "SELECT * FROM matchs m  "
                + "INNER JOIN arbitre a1 ON a1.id = m.id_arbitre1   "
               + "INNER JOIN arbitre a2 ON a2.id = m.id_arbitre2 "
                + "INNER JOIN arbitre a3 ON a3.id = m.id_arbitre3 "
                + "INNER JOIN arbitre a4 ON a4.id = m.id_arbitre4 " 
                + "WHERE ? in (m.id_arbitre1, m.id_arbitre2,m.id_arbitre3, m.id_arbitre4 )";
        List<Match> list = new ArrayList<>();
        Match m = new Match();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, a.getId());
           
            rs = pst.executeQuery();
            while (rs.next()) {
             //   m.setDate(rs.getDate("date"));
            //   m.setEquipe1(new Equipe(rs.getInt(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
                m.setId(rs.getInt("id"));
                m.setNb_but1(rs.getInt("nb_but1"));
                m.setNb_but2(rs.getInt("nb_but2"));
                m.setStade(rs.getString("stade"));
                m.setDate(rs.getTimestamp("date"));
              m.setNb_spectateur(rs.getInt("nb_spectateur"));
               // m.setEquipe1(new Equipe(rs.getInt("id"), rs.getString("nom"), rs.getString("logo"), rs.getString("entreneur"), rs.getString("niveau")));
               // m.setEquipe2(new Equipe(rs.getInt("id"), rs.getString("nom"), rs.getString("logo"), rs.getString("entreneur"), rs.getString("niveau")));
             //  m.setArbiter1(new Arbitres(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), new Roles(rs.getInt("id"), rs.getString("role"), rs.getString("Descp")), rs.getString("image"), rs.getInt("age"), rs.getInt("Avis")));
                //m.setArbiter2(new Arbitres(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), new Roles(rs.getInt("id"), rs.getString("role"), rs.getString("Descp")), rs.getString("image"), rs.getInt("age"),rs.getInt("Avis")));
                //m.setArbiter3(new Arbitres(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), new Roles(rs.getInt("id"), rs.getString("role"), rs.getString("Descp")), rs.getString("image"), rs.getInt("age"),rs.getInt("Avis")));
               // m.setArbiter4(new Arbitres(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), new Roles(rs.getInt("id"), rs.getString("role"), rs.getString("Descp")), rs.getString("image"), rs.getInt("age"),rs.getInt("Avis")));
                list.add(m);

            }

        } catch (SQLException ex) {
            Logger.getLogger(matchArbitres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    
    /**
     *
     * @param id
     * @return
     */
    public List<Double>  getStatArbitreByCartons(int id) {
     
     String sql = "SELECT  sum(carton_jaune) as cj , sum(carton_rouge) as cr from matchjoueur where id_match in ( select id from matchs where id_arbitre1=?) ";
           List<Double> list = new ArrayList<>();
        try {
            pst= conn.prepareStatement(sql);
            pst.setInt(1,id);
          
            
            rs = pst.executeQuery();
            Arbitres a = new Arbitres();
          
            if (rs.next())
             {           
               
                int cr = rs.getInt("cr");
                 int cj =rs.getInt("cj");
                 double sum = cr + cj;
                 double scj =  (float) (cj/sum)*100;
                 double scr = (int) (cr/sum)*100;
                System.out.println("stat des cartons rouge , stat des cartons jaune");
                
                 list.add((double) scr);
               
                 list.add((double) scj);
                // list.add((int) sum);
                
             }
             
            } catch (SQLException ex) {
            Logger.getLogger(matchArbitres.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return list;
 
 
 }
         
}
 

    
    
  