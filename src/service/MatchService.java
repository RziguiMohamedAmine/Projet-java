/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Equipe;
import entite.Match;
import java.util.List;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
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
        String sql = "insert into matchs values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = cnx.prepareStatement(sql);
            System.out.println(match);
            ps.setInt(1, match.getEquipe1().getId());
            ps.setInt(2, match.getEquipe2().getId());
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
            return result >= 1;

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
            ps.setInt(1, match.getEquipe1().getId());
            ps.setInt(2, match.getEquipe2().getId());
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
            return result >= 1;

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
            return result >= 1;

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Match> getAll() {
        String sql = "SELECT * FROM matchs m "
                + "INNER JOIN equipe e1 ON e1.id = m.equipe1 "
                + "INNER JOIN equipe e2 ON e2.id = m.equipe2";
//        SELECT * FROM matchs m
//INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1
//INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2
//INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3
//INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4

        List<Match> list = new ArrayList<>();
        Match m = new Match();
        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                m.setDate(rs.getDate("date"));
                m.setEquipe1(new Equipe(rs.getInt(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
                m.setEquipe2(new Equipe(rs.getInt(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22)));
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
        String sql = "SELECT * FROM matchs m "
                + "INNER JOIN equipe e1 ON e1.id = m.equipe1 "
                + "INNER JOIN equipe e2 ON e2.id = m.equipe2 "
                + "WHERE m.id=?";
        Match m = new Match();

        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                m.setDate(rs.getDate("date"));
                m.setEquipe1(new Equipe(rs.getInt(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
                m.setEquipe2(new Equipe(rs.getInt(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22)));
                m.setId(rs.getInt("id"));
                m.setId_arbiter1(rs.getInt("id_arbitre1"));
                m.setId_arbiter2(rs.getInt("id_arbitre2"));
                m.setId_arbiter3(rs.getInt("id_arbitre3"));
                m.setId_arbiter4(rs.getInt("id_arbitre4"));
                m.setNb_but1(rs.getInt("nb_but1"));
                m.setNb_but2(rs.getInt("nb_but2"));
                m.setNb_spectateur(rs.getInt("nb_spectateur"));
                m.setStade(rs.getString("stade"));
                return m;

            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Match> getMatchsByDate(String date) {
        String sql = "SELECT * FROM matchs m "
                + "INNER JOIN equipe e1 ON e1.id = m.equipe1 "
                + "INNER JOIN equipe e2 ON e2.id = m.equipe2 "
                + "WHERE date = ?";
        List<Match> list = new ArrayList<>();
        Match m = new Match();
        try {
            ps = cnx.prepareStatement(sql);
            Date d = Date.valueOf(date);
            System.out.println(d);
            ps.setDate(1, d);
            rs = ps.executeQuery();

            while (rs.next()) {
                m.setDate(rs.getDate("date"));
                m.setEquipe1(new Equipe(rs.getInt(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
                m.setEquipe2(new Equipe(rs.getInt(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22)));
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

    public List<Match> getmatchsByEquipe(Equipe e) {
        String sql = "SELECT * FROM matchs m "
                + "INNER JOIN equipe e1 ON e1.id = m.equipe1 "
                + "INNER JOIN equipe e2 ON e2.id = m.equipe2 "
                + "WHERE ? in (m.equipe1, m.equipe2)";
        List<Match> list = new ArrayList<>();
        Match m = new Match();
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, e.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                m.setDate(rs.getDate("date"));
                m.setEquipe1(new Equipe(rs.getInt(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
                m.setEquipe2(new Equipe(rs.getInt(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22)));
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

}