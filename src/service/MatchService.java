/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Match;
import java.util.List;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Houssem Charef
 */
public class MatchService implements IService<Match> {

    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public MatchService() {
        cnx = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Match match) {
        String sql = "insert into matchs values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, match.getEquipe1());
            ps.setInt(2, match.getEquipe2());
            ps.setInt(3, match.getNb_but1());
            ps.setInt(4, match.getNb_but2());
            ps.setString(5, match.getStade());
            ps.setInt(6, match.getId_arbiter1());
            ps.setInt(7, match.getId_arbiter2());
            ps.setInt(8, match.getId_arbiter3());
            ps.setInt(9, match.getId_arbiter4());
            ps.setDate(10, match.getDate());
            ps.setLong(11, match.getNb_spectateur());
            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Match match) {
        String sql = "UPDATE matchs SET equipe1=?,equipe2=?,nb_but1=?,nb_but2=?,stade=?,id_arbitre1=?,"
                + "id_arbitre2=?,id_arbitre3=?,id_arbitre4=?,date=?,nb_spectateur=? WHERE id=?";
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, match.getEquipe1());
            ps.setInt(2, match.getEquipe2());
            ps.setInt(3, match.getNb_but1());
            ps.setInt(4, match.getNb_but2());
            ps.setString(5, match.getStade());
            ps.setInt(6, match.getId_arbiter1());
            ps.setInt(7, match.getId_arbiter2());
            ps.setInt(8, match.getId_arbiter3());
            ps.setInt(9, match.getId_arbiter4());
            ps.setDate(10, match.getDate());
            ps.setLong(11, match.getNb_spectateur());
            ps.setInt(12, match.getId());

            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Match match) {
        String sql = "DELETE FROM matchs WHERE id=?";

        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, match.getId());

            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Match> getAll() {
        String sql = "SELECT * FROM matchs";
        List<Match> list = new ArrayList<>();
        Match m = new Match();
        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                m.setDate(rs.getDate("date"));
                m.setEquipe1(rs.getInt("equipe1"));
                m.setEquipe2(rs.getInt("equipe2"));
                m.setId(rs.getInt("id"));
                m.setId_arbiter1(rs.getInt("id_arbitre1"));
                m.setId_arbiter2(rs.getInt("id_arbitre2"));
                m.setId_arbiter3(rs.getInt("id_arbitre3"));
                m.setId_arbiter4(rs.getInt("id_arbitre4"));
                m.setNb_but1(rs.getInt("nb_but1"));
                m.setNb_but2(rs.getInt("nb_but2"));
                m.setNb_spectateur(rs.getInt("nb_spectateur"));
                m.setStade(rs.getString("stade"));
                list.add(m);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Match getOne(int id) {
        String sql = "SELECT * FROM matchs WHERE id=?";
        Match m = new Match();
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            m.setDate(rs.getDate("date"));
            m.setEquipe1(rs.getInt("equipe1"));
            m.setEquipe2(rs.getInt("equipe2"));
            m.setId(rs.getInt("id"));
            m.setId_arbiter1(rs.getInt("id_arbitre1"));
            m.setId_arbiter2(rs.getInt("id_arbitre2"));
            m.setId_arbiter3(rs.getInt("id_arbitre3"));
            m.setId_arbiter4(rs.getInt("id_arbitre4"));
            m.setNb_but1(rs.getInt("nb_but1"));
            m.setNb_but2(rs.getInt("nb_but2"));
            m.setNb_spectateur(rs.getInt("nb_spectateur"));
            m.setStade(rs.getString("stade"));

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

}
