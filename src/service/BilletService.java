/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Billet;
import entite.Equipe;
import entite.Match;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Houssem Charef
 */
public class BilletService implements IService<Billet> {

    Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public BilletService() {
        cnx = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Billet billet) {
        String sql = "insert into billet values(null, ?, ?, ?)";
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, billet.getMatch().getId());
            ps.setString(2, billet.getBloc());
            ps.setFloat(3, (float) billet.getPrix());
            int result = ps.executeUpdate();
            return result >= 1;

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Billet billet) {
        String sql = "UPDATE billet SET id_match=?,bloc=?,prix=? WHERE id=?";
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, billet.getMatch().getId());
            ps.setString(2, billet.getBloc());
            ps.setFloat(3, (float) billet.getPrix());
            ps.setInt(4, billet.getId());

            int result = ps.executeUpdate();
            return result >= 1;

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Billet billet) {
        String sql = "DELETE FROM billet WHERE id=?";

        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, billet.getId());

            int result = ps.executeUpdate();
            return result >= 1;

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Billet> getAll() {
        String sql = "SELECT * FROM billet INNER JOIN matchs ON matchs.id = billet.id_match";
        List<Billet> list = new ArrayList<>();
        Billet b = new Billet();
        Match match = new Match();

        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                b.setId(rs.getInt(1));
                match.setDate(rs.getDate("date"));
                match.setEquipe1(new Equipe());// equipeservice.getOne(rs.getInt('id_equipe'))
                match.setEquipe2(new Equipe());// equipeservice.getOne(rs.getInt('id_equipe'))
                match.setId(rs.getInt("id"));
                match.setId_arbiter1(rs.getInt("id_arbitre1"));
                match.setId_arbiter2(rs.getInt("id_arbitre2"));
                match.setId_arbiter3(rs.getInt("id_arbitre3"));
                match.setId_arbiter4(rs.getInt("id_arbitre4"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setStade(rs.getString("stade"));
                b.setMatch(match);
                b.setBloc(rs.getString("bloc"));
                b.setPrix(rs.getFloat("prix"));
                list.add(b);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Billet getOne(int id) {
        String sql = "SELECT * FROM billet INNER JOIN matchs ON matchs.id = billet.id_match WHERE billet.id=?";
        Billet billet = new Billet();
        Match match = new Match();

        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                billet.setId(rs.getInt(1));
                match.setDate(rs.getDate("date"));
                match.setEquipe1(new Equipe()); // equipeservice.getOne(rs.getInt('id_equipe'))
                match.setEquipe2(new Equipe());// equipeservice.getOne(rs.getInt('id_equipe'))
                match.setId(rs.getInt("id"));
                match.setId_arbiter1(rs.getInt("id_arbitre1"));
                match.setId_arbiter2(rs.getInt("id_arbitre2"));
                match.setId_arbiter3(rs.getInt("id_arbitre3"));
                match.setId_arbiter4(rs.getInt("id_arbitre4"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setStade(rs.getString("stade"));
                billet.setMatch(match);
                billet.setBloc(rs.getString("bloc"));
                billet.setPrix(rs.getFloat("prix"));
                return billet;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
