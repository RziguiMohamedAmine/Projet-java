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
import java.sql.Timestamp;
import java.util.List;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Houssem Charef
 */
public class MatchService implements IService<Match> {

    private final Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public MatchService() {
        cnx = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Match match) {
        String sql = "insert into matchs values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
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
            ps.setTimestamp(10, match.getDate());
            ps.setLong(11, match.getNb_spectateur());
            ps.setString(12, match.getSaison());
            ps.setInt(13, match.getRound());
            System.out.println(ps);
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
                + "id_arbitre2=?,id_arbitre3=?,id_arbitre4=?,date=?,nb_spectateur=? WHERE id=?; ";

        String sql_win = "UPDATE classment SET nb_totale_but=nb_totale_but+? , nb_point=nb_point+3, nb_win=nb_win+1 , nb_totale_but_recu=nb_totale_but_recu+? WHERE id_equipe=? and saison like ?;";

        String sql_draw = "UPDATE classment SET nb_totale_but=nb_totale_but+? , nb_point=nb_point+1, nb_draw=nb_draw+1, nb_totale_but_recu=nb_totale_but_recu+? WHERE id_equipe=? and saison like ?;";

        String sql_lose = "UPDATE classment SET nb_totale_but=nb_totale_but+? , nb_point=nb_point+0 ,nb_lose=nb_lose+1, nb_totale_but_recu=nb_totale_but_recu+? WHERE id_equipe=? and saison like ?;";

        if (match.getNb_but1() > match.getNb_but2()) {
            sql = sql + sql_win + sql_lose;

        } else if (match.getNb_but1() < match.getNb_but2()) {
            sql = sql + sql_lose + sql_win;

        } else {
            sql = sql + sql_draw + sql_draw;

        }

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
            ps.setTimestamp(10, match.getDate());
            ps.setLong(11, match.getNb_spectateur());
            ps.setInt(12, match.getId());

            ps.setInt(13, match.getNb_but1());
            ps.setInt(14, match.getNb_but2());
            ps.setInt(15, match.getEquipe1().getId());
            ps.setString(16, match.getSaison());
            ps.setInt(17, match.getNb_but2());
            ps.setInt(18, match.getNb_but1());

            ps.setInt(19, match.getEquipe2().getId());
            ps.setString(20, match.getSaison());

            int result = ps.executeUpdate();
            System.out.println("match update");
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
        String sql = "select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, e1.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.email email_arbitre1,b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, "
                + "b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.email email_arbitre2,b2.image image_arbitre2, "
                + "b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3,b3.email email_arbitre3, "
                + "m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, b4.image image_arbitre4, b4.email email_arbitre4,b4.age age_arbitre4 ,m.date, m.nb_spectateur, "
                + "m.equipe1 id_equipe1 ,e1.nomeq nom_equipe1,e1.logo logo_equipe1, e1.nom_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nomeq nom_equipe2,e2.logo logo_equipe2, "
                + "e2.nom_entreneur entreneur_equipe2, e1.niveau niveau_equipe2,m.saison, m.round FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 "
                + "INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4) m INNER JOIN "
                + "role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id  "
                + "ORDER BY date;";
//select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.email email_arbitre1,b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.email email_arbitre2,b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3,b3.email email_arbitre3, m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, b4.image image_arbitre4, b4.email email_arbitre4,b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nomeq nom_equipe1,e1.logo logo_equipe1, e1.nom_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nomeq nom_equipe2,e2.logo logo_equipe2, e1.nom_entreneur entreneur_equipe2, e1.niveau niveau_equipe2,m.saison FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id  ORDER BY date;        
        List<Match> list = new ArrayList<>();
        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();

                match.setId(rs.getInt("id_match"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setDate(rs.getTimestamp("date"));
                match.setSaison(rs.getString("saison"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setRound(rs.getInt("round"));
                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1"), rs.getString("stade")));
                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1"), rs.getString("email_arbitre1")));
                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2"), rs.getString("email_arbitre2")));
                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(53), rs.getString(54), rs.getString(55)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3"), rs.getString("email_arbitre3")));
                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(56), rs.getString(57), rs.getString(58)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4"), rs.getString("email_arbitre4")));
                list.add(match);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list.get(0));
        return list;
    }

    @Override
    public Match getOne(int id) {
//        String sql = "select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, "
//                + "b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, "
//                + "b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3 , m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, "
//                + "b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nom nom_equipe1,e1.logo logo_equipe1, e1.id_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,"
//                + "m.equipe2 id_equipe2,e2.nom nom_equipe2,e2.logo logo_equipe2, e1.id_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 "
//                + "INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 "
//                + "INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4 where m.id = ?) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 "
//                + "on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;";
//        Match match = new Match();
//
//        try {
//            ps = cnx.prepareStatement(sql);
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                match.setId(rs.getInt("id_match"));
//                match.setNb_but1(rs.getInt("nb_but1"));
//                match.setNb_but2(rs.getInt("nb_but2"));
//                match.setStade(rs.getString("stade"));
//                match.setDate(rs.getTimestamp("date"));
//                match.setNb_spectateur(rs.getInt("nb_spectateur"));
//                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1")));
//                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
//                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(41), rs.getString(42), rs.getString(43)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1")));
//                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(44), rs.getString(45), rs.getString(46)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2")));
//                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3")));
//                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4")));
//                return match;
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
        return null;
    }

    public List<Match> getMatchsByDate(LocalDate date) {

        String sql = "select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.email email_arbitre1,b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, "
                + "b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.email email_arbitre2,b2.image image_arbitre2, "
                + "b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3,b3.email email_arbitre3, "
                + "m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, b4.image image_arbitre4, b4.email email_arbitre4,b4.age age_arbitre4 ,m.date, m.nb_spectateur, "
                + "m.equipe1 id_equipe1 ,e1.nomeq nom_equipe1,e1.logo logo_equipe1, e1.nom_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nomeq nom_equipe2,e2.logo logo_equipe2, "
                + "e2.nom_entreneur entreneur_equipe2, e1.niveau niveau_equipe2,m.saison, m.round FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 "
                + "INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4 where EXTRACT(day from ?) = EXTRACT(day from date) and EXTRACT(month from ?) = EXTRACT(month from date) and EXTRACT(year from ?) = EXTRACT(year from date)) m INNER JOIN "
                + "role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id  "
                + "ORDER BY date;";
        List<Match> list = new ArrayList<>();
        try {
            ps = cnx.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(date.atStartOfDay()));

            ps.setTimestamp(2, Timestamp.valueOf(date.atStartOfDay()));
            ps.setTimestamp(3, Timestamp.valueOf(date.atStartOfDay()));

            System.out.println(ps);

            rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();

                match.setId(rs.getInt("id_match"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setStade(rs.getString("stade"));
                match.setDate(rs.getTimestamp("date"));
                match.setRound(rs.getInt("round"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1")));
                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1"), rs.getString("email_arbitre1")));
                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2"), rs.getString("email_arbitre2")));
                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(53), rs.getString(54), rs.getString(55)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3"), rs.getString("email_arbitre3")));
                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(56), rs.getString(57), rs.getString(58)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4"), rs.getString("email_arbitre4")));
                list.add(match);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list);
        return list;
    }

    public List<Match> getMatchFuture() {

        String sql = "select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, e1.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.email email_arbitre1,b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, "
                + "b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.email email_arbitre2,b2.image image_arbitre2, "
                + "b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3,b3.email email_arbitre3, "
                + "m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, b4.image image_arbitre4, b4.email email_arbitre4,b4.age age_arbitre4 ,m.date, m.nb_spectateur, "
                + "m.equipe1 id_equipe1 ,e1.nomeq nom_equipe1,e1.logo logo_equipe1, e1.nom_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nomeq nom_equipe2,e2.logo logo_equipe2, "
                + "e2.nom_entreneur entreneur_equipe2, e1.niveau niveau_equipe2,m.saison, m.round FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 "
                + "INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4 where date >?) m INNER JOIN "
                + "role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id  "
                + "ORDER BY date;";
        List<Match> list = new ArrayList<>();
        try {
            ps = cnx.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(LocalDate.now().atStartOfDay()));

            System.out.println(ps);

            rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();

                match.setId(rs.getInt("id_match"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setDate(rs.getTimestamp("date"));
                match.setRound(rs.getInt("round"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1"), rs.getString("stade")));
                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1"), rs.getString("email_arbitre1")));
                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2"), rs.getString("email_arbitre2")));
                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(53), rs.getString(54), rs.getString(55)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3"), rs.getString("email_arbitre3")));
                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(56), rs.getString(57), rs.getString(58)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4"), rs.getString("email_arbitre4")));
                list.add(match);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list);
        return list;
    }
//
//    public List<Match> getmatchsByEquipe(Equipe e) {
//
//        String sql = "select * from (SELECT m.id id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, "
//                + "b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, "
//                + "b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3 , m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, "
//                + "b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nom nom_equipe1,e1.logo logo_equipe1, e1.id_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,"
//                + "m.equipe2 id_equipe2,e2.nom nom_equipe2,e2.logo logo_equipe2, e1.id_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM matchs m INNER JOIN equipe e1 ON e1.id = m.equipe1 "
//                + "INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 "
//                + "INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4 WHERE ? in (m.equipe1, m.equipe2)) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 "
//                + "on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;";
//        List<Match> list = new ArrayList<>();
//        Match match = new Match();
//        try {
//            ps = cnx.prepareStatement(sql);
//            ps.setInt(1, e.getId());
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                match.setId(rs.getInt("id_match"));
//                match.setNb_but1(rs.getInt("nb_but1"));
//                match.setNb_but2(rs.getInt("nb_but2"));
//                match.setStade(rs.getString("stade"));
//                match.setDate(rs.getTimestamp("date"));
//                match.setNb_spectateur(rs.getInt("nb_spectateur"));
//                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1")));
//                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
//                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(41), rs.getString(42), rs.getString(43)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1")));
//                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(44), rs.getString(45), rs.getString(46)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2")));
//                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3")));
//                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4")));
//                list.add(match);
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//
//    }

    public List<Match> reverseListOrderAndEquipe(List<Match> matchList, int nbRound) {

        List<Match> matchListReverse = new ArrayList<>();

        for (Match match : matchList) {
            Match match2 = new Match();
            match2.setArbiter1(match.getArbiter1());
            match2.setArbiter2(match.getArbiter2());
            match2.setArbiter3(match.getArbiter3());
            match2.setArbiter4(match.getArbiter4());
            match2.setDate(Timestamp.from(match.getDate().toInstant().plusSeconds(3600 * 24 * 7 * 20)));
            match2.setEquipe1(match.getEquipe2());
            match2.setEquipe2(match.getEquipe1());
            match2.setId(match.getId());
            match2.setNb_but1(match.getNb_but1());
            match2.setNb_but2(match.getNb_but2());
            match2.setNb_spectateur(match.getNb_spectateur());
            match2.setRound(nbRound + match.getRound());
            match2.setSaison(match.getSaison());
            match2.setStade(match.getStade());
            matchListReverse.add(match2);
        }
        Collections.reverse(matchListReverse);

        return matchListReverse;
    }

    public boolean tirage_au_sort(String saison, Timestamp DateDebut) {
        String sql = "INSERT INTO matchs(equipe1, equipe2, nb_but1, nb_but2, stade, id_arbitre1, id_arbitre2, id_arbitre3, id_arbitre4, date, nb_spectateur, saison, round) VALUES ";
        EquipeService equipeService = new EquipeService();
        List<Equipe> equipeList = new ArrayList<>(equipeService.getAll());

        List<Equipe> equipeList1 = new ArrayList<>();
        List<Equipe> equipeList2 = new ArrayList<>();
        List<Match> matchList = new ArrayList<>();
        Instant roundDate = DateDebut.toInstant();

        try {

            for (int i = 0; i < equipeList.size() / 2; i++) {
                equipeList1.add(equipeList.get(i));
                equipeList2.add(equipeList.get(equipeList.size() - i - 1));
            }
            int nombreRound = equipeList.size() - 1;
            int nbrMatchParRound = equipeList.size() / 2;

            for (int i = 0; i < nombreRound; i++) {
                Instant matchDate = roundDate;

                for (int j = 0; j < nbrMatchParRound; j++) {
                    Match match = new Match();
                    match.setEquipe1(equipeList1.get(j));
                    match.setEquipe2(equipeList2.get(j));
                    match.setNb_but1(-1);
                    match.setNb_but2(-1);
                    match.setSaison(saison);

                    match.setArbiter1(new Arbitres(1));
                    match.setArbiter2(new Arbitres(2));
                    match.setArbiter3(new Arbitres(3));
                    match.setArbiter4(new Arbitres(4));

                    match.setNb_spectateur(10000);
                    match.setDate(Timestamp.from(matchDate));
                    match.setStade(equipeList1.get(j).getSatde());
                    match.setRound(i + 1);
                    matchList.add(match);
                    if (j % 3 == 0) {
                        matchDate = roundDate.plusSeconds(3600 * 24 * (j / 3));
                    } else {
                        matchDate = matchDate.plusSeconds(3600 * 2);

                    }
                }
                Equipe equipe1 = equipeList1.get(equipeList2.size() - 1);
                Equipe equipe2 = equipeList2.get(0);

                for (int k = 0; k < equipeList2.size() - 1; k++) {

                    equipeList2.set(k, equipeList2.get(k + 1));
                }
                for (int k = equipeList2.size() - 1; k > 1; k--) {
                    equipeList1.set(k,
                            equipeList1.get(k - 1));

                }

                equipeList1.set(1, equipe2);
                equipeList2.set(equipeList2.size() - 1, equipe1);
                roundDate = roundDate.plusSeconds(3600 * 24 * 7);

            }

            List<Match> matchListReverse = new ArrayList<>(reverseListOrderAndEquipe(matchList, nombreRound));
            matchList.addAll(matchListReverse);
            for (Match m : matchList) {
                sql += "(" + m.getEquipe1().getId() + "," + m.getEquipe2().getId() + ", " + m.getNb_but1() + ", " + m.getNb_but2() + ",'"
                        + m.getStade() + "'," + m.getArbiter1().getId() + "," + m.getArbiter2().getId() + "," + m.getArbiter3().getId() + ","
                        + m.getArbiter4().getId() + ",'" + m.getDate() + "'," + m.getNb_spectateur() + ",'" + m.getSaison() + "'," + m.getRound() + "),";
            }

            sql = sql.substring(0, sql.length() - 1);
            sql += ";INSERT INTO classment( id_equipe,saison) VALUES ";
            for (Equipe e : equipeList) {
                sql += "(" + e.getId() + ",'" + saison + "'),";
            }
            sql = sql.substring(0, sql.length() - 1);
            ps = cnx.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);

        }

        return true;
    }
//    public boolean FoundInRound(Match matchToInsert, List<Match> matchList, int nbrMatchParRound) {
//        int listLong = matchList.size();
//        Match match = new Match();
//        int indexFirstMatchInRound = ((listLong / nbrMatchParRound) * nbrMatchParRound);
//
//        if (indexFirstMatchInRound % nbrMatchParRound == 0) {
//            return false;
//        }
//
//        for (int i = indexFirstMatchInRound; i < listLong; i++) {
//            match = matchList.get(i);
////            System.out.println(match.getEquipe1().getId() + " " + match.getEquipe2().getId());
////            System.out.println(matchToInsert.getEquipe1().getId() + " " + matchToInsert.getEquipe2().getId());
//
//            if (match.getEquipe1().equals(matchToInsert.getEquipe1())
//                    || match.getEquipe1().equals(matchToInsert.getEquipe2())
//                    || match.getEquipe2().equals(matchToInsert.getEquipe1())
//                    || match.getEquipe2().equals(matchToInsert.getEquipe2())) {
////                System.out.println(true);
//                return true;
//            }
//        }
////        System.out.println("false");
//        return false;
//    }
//    public boolean FoundInRound(Match matchToInsert, List<Match> matchList, int round) {
//        int listLong = matchList.size();
//        Match match = new Match();
////        int indexFirstMatchInRound = ((listLong / nbrMatchParRound) * nbrMatchParRound);
////
//
//        for (int i = 0; i < listLong; i++) {
//            match = matchList.get(i);
////            System.out.println(match.getEquipe1().getId() + " " + match.getEquipe2().getId());
////            System.out.println(matchToInsert.getEquipe1().getId() + " " + matchToInsert.getEquipe2().getId());
//
//            if (match.getRound() == round) {
////                System.out.println(match.getRound());
//                if (match.getEquipe1().getId() == matchToInsert.getEquipe1().getId()
//                        || match.getEquipe1().getId() == matchToInsert.getEquipe2().getId()
//                        || match.getEquipe2().getId() == matchToInsert.getEquipe2().getId()
//                        || match.getEquipe2().getId() == matchToInsert.getEquipe1().getId()) {
////                System.out.println(true);
//                    return true;
//                }
//            }
//
//        }
////        System.out.println("false");
//        return false;
//    }
//
//    public List<Match> reverseListOrderAndEquipe(List<Match> matchSortedList) {
//        List<Match> matchSortedReverse = new ArrayList<>(matchSortedList);
//        Collections.reverse(matchSortedReverse);
//        for (Match match : matchSortedReverse) {
//            Equipe e1 = match.getEquipe1();
//            match.setEquipe1(match.getEquipe2());
//            match.setEquipe2(e1);
//        }
//        return matchSortedReverse;
//    }
//
//    public boolean tirage_au_sort(String saison, Timestamp DateDebut) throws SQLException {
//        String sql = "INSERT INTO matchs(equipe1, equipe2, nb_but1, nb_but2, stade, id_arbitre1, id_arbitre2, id_arbitre3, id_arbitre4, date, nb_spectateur, saision, round) VALUES ";
//        EquipeService equipeService = new EquipeService();
//        List<Equipe> equipeList = new ArrayList<>(equipeService.getAll());
//
//        List<Match> matchList = new ArrayList<>();
//        List<Match> matchSortedList = new ArrayList<>();
//        Timestamp match_date = DateDebut;
//
//        try {
//            for (int i = 0; i < equipeList.size(); i++) {
//                for (int j = i; j < equipeList.size(); j++) {
//                    if (i != j) {
//                        Match match = new Match();
//
//                        match.setEquipe1(equipeList.get(i));
//                        match.setEquipe2(equipeList.get(j));
//                        match.setNb_but1(-1);
//                        match.setNb_but2(-1);
//                        match.setSaison(saison);
//
//                        match.setArbiter1(new Arbitres(6));
//                        match.setArbiter2(new Arbitres(6));
//                        match.setArbiter3(new Arbitres(6));
//                        match.setArbiter4(new Arbitres(6));
//
//                        match.setNb_spectateur(10000);
//                        match.setDate(new Timestamp(23333333));
//                        match.setStade("sssss");
//
//                        matchList.add(match);
//                    }
//                }
//            }
//
////            Collections.shuffle(matchList);
//            int nombreRound = matchList.size() / (equipeList.size() / 2);
//            int nbrMatchParRound = equipeList.size() / 2;
//
//            for (int i = 0; i < nombreRound; i++) {
//                int round = i + 1;
//                for (int j = 0; j < nbrMatchParRound; j++) {
//                    int compteur = 0;
//                    System.out.println("-----------------------------------------");
//                    while (FoundInRound(matchList.get(compteur), matchSortedList, round)) {
//                        System.out.println(compteur);
//                        compteur++;
//                    }
//                    if (!FoundInRound(matchList.get(compteur), matchSortedList, round)) {
//                        Match match_to_add = matchList.get(compteur);
//                        match_to_add.setRound(round);
//                        match_to_add.setDate(match_date);
//                        matchSortedList.add(match_to_add);
//                        matchList.remove(compteur);
////                        System.out.println(matchList);
//                        if (j == 1 || j == 5) {
//                            Instant date = match_date.toInstant();
//                            date = date.plusSeconds(24 * 3600);
//                            match_date = Timestamp.from(date);
//
//                        } else {
//                            Instant date = match_date.toInstant();
//                            date = date.plusSeconds(2 * 3600);
//                            match_date = Timestamp.from(date);
//                        }
//                    }
//
//                    Instant date = match_date.toInstant();
//                    date = date.plusSeconds((5 * 24 * 3600) - 2 * 3600);
//                    match_date = Timestamp.from(date);
//                }
//
//            }
////            List<Match> matchSortedReverse = reverseListOrderAndEquipe(matchSortedList);
////            matchSortedList.addAll(matchSortedReverse);
//            for (Match m : matchSortedList) {
//                sql += "(" + m.getEquipe1().getId() + "," + m.getEquipe2().getId() + ", " + m.getNb_but1() + ", " + m.getNb_but2() + ",'"
//                        + m.getStade() + "'," + m.getArbiter1().getId() + "," + m.getArbiter2().getId() + "," + m.getArbiter3().getId() + ","
//                        + m.getArbiter4().getId() + ",'" + m.getDate() + "'," + m.getNb_spectateur() + ",'" + m.getSaison() + "'," + m.getRound() + "),";
//            }
//            sql = sql.substring(0, sql.length() - 1);
////            sql += ";INSERT INTO classment( id_equipe,saison) VALUES ";
////            for (Equipe e : equipeList) {
////                sql += "(" + e.getId() + ",'" + saison + "'),";
////            }
////            sql = sql.substring(0, sql.length() - 1);
//            ps = cnx.prepareStatement(sql);
//            ps.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
//
//        } finally {
////            for (Match m : matchSortedList) {
////                sql += "(" + m.getEquipe1().getId() + "," + m.getEquipe2().getId() + ", " + m.getNb_but1() + ", " + m.getNb_but2() + ",'"
////                        + m.getStade() + "'," + m.getArbiter1().getId() + "," + m.getArbiter2().getId() + "," + m.getArbiter3().getId() + ","
////                        + m.getArbiter4().getId() + ",'" + m.getDate() + "'," + m.getNb_spectateur() + ",'" + m.getSaison() + "'," + m.getRound() + "),";
////            }
////            sql = sql.substring(0, sql.length() - 1);
//////            sql += ";INSERT INTO classment( id_equipe,saison) VALUES ";
//////            for (Equipe e : equipeList) {
//////                sql += "(" + e.getId() + ",'" + saison + "'),";
//////            }
//////            sql = sql.substring(0, sql.length() - 1);
////            ps = cnx.prepareStatement(sql);
////            ps.executeUpdate();
//        }
//        return true;
//    }
}
