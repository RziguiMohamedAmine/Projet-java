/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DataSource;
import entite.Classment;
import entite.Equipe;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Houssem Charef
 */
public class ClassmentService {
    
    Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public ClassmentService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public List<Classment> getAllBySaison(String saison) {
        String sql = "SELECT (Row_number() over (ORDER BY nb_point DESC)) rang, c.nb_totale_but_recu,c.id_classment,c.nb_totale_but, c.nb_point, c.saison, c.nb_win, c.nb_draw, c.nb_lose, "
                + "c.id_equipe, e.nom, e.logo, e.id_entreneur, e.niveau  FROM classment c INNER JOIN equipe e on e.id=c.id_equipe WHERE saison like ?  ";
        List<Classment> list = new ArrayList<>();
        try {
            ps = cnx.prepareStatement(sql);
            ps.setString(1, saison);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Classment classment = new Classment();
                
                classment.setEquipe(new Equipe(rs.getInt("id_equipe"), rs.getString("nom"), rs.getString("logo"), rs.getString("id_entreneur"), rs.getString("niveau")));
                classment.setId(rs.getInt("id_classment"));
                classment.setNb_draw(rs.getInt("nb_draw"));
                classment.setNb_win(rs.getInt("nb_win"));
                classment.setNb_point(rs.getInt("nb_point"));
                classment.setNb_totale_but(rs.getInt("nb_totale_but"));
                classment.setNb_totale_but_recu(rs.getInt("nb_totale_but_recu"));
                classment.setSaison(rs.getString("saison"));
                classment.setRang(rs.getInt("rang"));
                list.add(classment);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<String> getAvalableSaison() {
        
        String sql = "SELECT DISTINCT saison FROM `classment`";
        List<String> list = new ArrayList<>();
        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                list.add(rs.getString("saison"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
