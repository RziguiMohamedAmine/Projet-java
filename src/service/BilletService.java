/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Billet;
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
        String sql = "SELECT * FROM billet";
        List<Billet> list = new ArrayList<>();
        Billet b = new Billet();
        MatchService matchService = new MatchService();
        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                b.setId(rs.getInt("id"));
                b.setMatch(matchService.getOne(rs.getInt("id_match")));
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
        String sql = "SELECT * FROM billet WHERE id=?";
        Billet b = new Billet();
        MatchService matchService = new MatchService();
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                b.setId(rs.getInt("id"));
                b.setMatch(matchService.getOne(rs.getInt("id_match")));
                b.setBloc(rs.getString("bloc"));
                b.setPrix(rs.getFloat("prix"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

}
