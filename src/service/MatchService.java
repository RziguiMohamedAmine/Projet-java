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
import java.util.List;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
            ps.setInt(6, match.getArbiter1().getId());
            ps.setInt(7, match.getArbiter2().getId());
            ps.setInt(8, match.getArbiter3().getId());
            ps.setInt(9, match.getArbiter4().getId());
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
            ps.setInt(6, match.getArbiter1().getId());
            ps.setInt(7, match.getArbiter2().getId());
            ps.setInt(8, match.getArbiter3().getId());
            ps.setInt(9, match.getArbiter4().getId());
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
        String sql = "select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, "
                + "b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, "
                + "b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3 , m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, "
                + "b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nom nom_equipe1,e1.logo logo_equipe1, e1.id_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,"
                + "m.equipe2 id_equipe2,e2.nom nom_equipe2,e2.logo logo_equipe2, e1.id_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 "
                + "INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 "
                + "INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 "
                + "on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;";
//select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3 , m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nom nom_equipe1,e1.logo logo_equipe1, e1.id_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nom nom_equipe2,e2.logo logo_equipe2, e1.id_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;       
        List<Match> list = new ArrayList<>();
        Match match = new Match();
        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                match.setId(rs.getInt("id_match"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setStade(rs.getString("stade"));
                match.setDate(rs.getDate("date"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1")));
                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(41), rs.getString(42), rs.getString(43)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1")));
                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(44), rs.getString(45), rs.getString(46)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2")));
                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3")));
                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4")));
                list.add(match);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Match getOne(int id) {
        String sql = "select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, "
                + "b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, "
                + "b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3 , m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, "
                + "b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nom nom_equipe1,e1.logo logo_equipe1, e1.id_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,"
                + "m.equipe2 id_equipe2,e2.nom nom_equipe2,e2.logo logo_equipe2, e1.id_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 "
                + "INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 "
                + "INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4 where m.id = ?) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 "
                + "on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;";
        Match match = new Match();

        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                match.setId(rs.getInt("id_match"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setStade(rs.getString("stade"));
                match.setDate(rs.getDate("date"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1")));
                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(41), rs.getString(42), rs.getString(43)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1")));
                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(44), rs.getString(45), rs.getString(46)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2")));
                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3")));
                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4")));
                return match;

            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Match> getMatchsByDate(String date) {
        String sql = "select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, "
                + "b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, "
                + "b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3 , m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, "
                + "b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nom nom_equipe1,e1.logo logo_equipe1, e1.id_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,"
                + "m.equipe2 id_equipe2,e2.nom nom_equipe2,e2.logo logo_equipe2, e1.id_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 "
                + "INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 "
                + "INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4 where m.date = ?) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 "
                + "on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;";
        List<Match> list = new ArrayList<>();
        Match match = new Match();
        try {
            ps = cnx.prepareStatement(sql);
            Date d = Date.valueOf(date);
            ps.setDate(1, d);
            rs = ps.executeQuery();

            while (rs.next()) {
                match.setId(rs.getInt("id_match"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setStade(rs.getString("stade"));
                match.setDate(rs.getDate("date"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1")));
                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(41), rs.getString(42), rs.getString(43)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1")));
                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(44), rs.getString(45), rs.getString(46)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2")));
                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3")));
                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4")));
                list.add(match);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Match> getmatchsByEquipe(Equipe e) {

        String sql = "select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, "
                + "b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, "
                + "b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3 , m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, "
                + "b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nom nom_equipe1,e1.logo logo_equipe1, e1.id_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,"
                + "m.equipe2 id_equipe2,e2.nom nom_equipe2,e2.logo logo_equipe2, e1.id_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 "
                + "INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 "
                + "INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4 WHERE ? in (m.equipe1, m.equipe2)) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 "
                + "on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;";
        List<Match> list = new ArrayList<>();
        Match match = new Match();
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, e.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                match.setId(rs.getInt("id_match"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setStade(rs.getString("stade"));
                match.setDate(rs.getDate("date"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1")));
                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(41), rs.getString(42), rs.getString(43)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1")));
                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(44), rs.getString(45), rs.getString(46)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2")));
                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3")));
                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4")));
                list.add(match);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public boolean tirage_au_sort() {
        String sql = "SELECT * FROM equipe e1 CROSS JOIN equipe e2 on e2.id<>e1.id";

        Set<Match> hashSet = new HashSet<>();
        List<Match> l = new ArrayList<>();
        Match m = new Match();
        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                m.setDate(rs.getDate("date"));
                m.setEquipe1(new Equipe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                m.setEquipe2(new Equipe(rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
                m.setId(rs.getInt("id"));
                m.setArbiter1(new Arbitres());
                m.setArbiter2(new Arbitres());
                m.setArbiter3(new Arbitres());
                m.setArbiter4(new Arbitres());
                m.setNb_but1(rs.getInt("nb_but1"));
                m.setNb_but2(rs.getInt("nb_but2"));
                m.setNb_spectateur(rs.getInt("nb_spectateur"));
                m.setStade(rs.getString("stade"));
                l.add(m);

            }
//            for (Match m2 : l) {
//                System.out.println(m2.getEquipe1().getId() + "  " + m2.getEquipe2().getId());
//            }
        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
