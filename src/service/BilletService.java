/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.stripe.exception.StripeException;
import entite.Arbitres;
import entite.Billet;
import entite.Equipe;
import entite.Match;
import entite.Roles;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
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
        String sql = "insert into billet values(null, ?, ?, ?, null)";
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

    public int insertID(Billet billet) {
        String generatedColumns[] = {"ID"};

        String sql = "insert into billet values(null, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql, generatedColumns);

            ps.setInt(1, billet.getMatch().getId());
            ps.setString(2, billet.getBloc());
            ps.setFloat(3, (float) billet.getPrix());
            ps.setInt(4, billet.getId_user());
            int affectedRows = ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            if (result.next()) {
                return result.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
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
        String sql = "select * from (SELECT m.id_billet, m.bloc,m.prix_billet, m.id_match,m.nb_but1,m.nb_but2, e1.stade, m.id_arbitre1, b1.nom nom_arbitre1,b1.email email_arbitre1, b1.prenom prenom_arbitre1, "
                + "b1.id_role role_arbitre1, b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, "
                + "b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3,b2.email email_arbitre2, b3.image image_arbitre3, b3.age age_arbitre3 ,"
                + "b3.email email_arbitre3, m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4,b4.email email_arbitre4, b4.image image_arbitre4, b4.age age_arbitre4 ,"
                + "m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nomeq nom_equipe1,e1.logo logo_equipe1, e1.nomeq entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nomeq nom_equipe2,"
                + "e2.logo logo_equipe2, e1.nom_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM (SELECT b.id id_billet, b.bloc, b.prix prix_billet,b.id_match, m.equipe1, m.equipe2, m.nb_but1, "
                + "m.nb_but2, m.stade, m.id_arbitre1, m.id_arbitre2, m.id_arbitre3, m.id_arbitre4, m.date, m.nb_spectateur FROM billet b INNER JOIN matchs m ON m.id = b.id_match ) m "
                + "INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 "
                + "INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id "
                + "INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;";
//       select * from (SELECT m.id_billet, m.bloc,m.prix_billet, m.id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1,b1.email email_arbitre1, b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3,b2.email email_arbitre2, b3.image image_arbitre3, b3.age age_arbitre3 ,b3.email email_arbitre3, m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4,b4.email email_arbitre4, b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nomeq nom_equipe1,e1.logo logo_equipe1, e1.nomeq entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nomeq nom_equipe2,e2.logo logo_equipe2, e1.nom_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM (SELECT b.id id_billet, b.bloc, b.prix prix_billet,b.id_match, m.equipe1, m.equipe2, m.nb_but1, m.nb_but2, m.stade, m.id_arbitre1, m.id_arbitre2, m.id_arbitre3, m.id_arbitre4, m.date, m.nb_spectateur FROM billet b INNER JOIN matchs m ON m.id = b.id_match ) m INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;
        List<Billet> list = new ArrayList<>();

        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Billet b = new Billet();
                Match match = new Match();
                b.setId(rs.getInt("id_billet"));
                match.setId(rs.getInt("id_match"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setDate(rs.getTimestamp("date"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1"), rs.getString("stade")));
                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(48), rs.getString(49), rs.getString(50)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1"), rs.getString("email_arbitre1")));
                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(51), rs.getString(52), rs.getString(53)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2"), rs.getString("email_arbitre2")));
                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(54), rs.getString(55), rs.getString(56)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3"), rs.getString("email_arbitre3")));
                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(57), rs.getString(58), rs.getString(59)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4"), rs.getString("email_arbitre4")));
                b.setMatch(match);
                b.setBloc(rs.getString("bloc"));
                b.setPrix(rs.getFloat("prix_billet"));
                list.add(b);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Billet getOne(int id) {
//        String sql = "select * from (SELECT m.id_billet, m.bloc,m.prix_billet, m.id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1, b1.prenom prenom_arbitre1, "
//                + "b1.id_role role_arbitre1, b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, "
//                + "b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3, b3.image image_arbitre3, b3.age age_arbitre3 , "
//                + "m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4, b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,"
//                + "e1.nom nom_equipe1,e1.logo logo_equipe1, e1.id_entreneur entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nom nom_equipe2,e2.logo logo_equipe2, e1.id_entreneur entreneur_equipe2, "
//                + "e1.niveau niveau_equipe2 FROM (SELECT b.id id_billet, b.bloc, b.prix prix_billet,b.id_match, m.equipe1, m.equipe2, m.nb_but1, m.nb_but2, m.stade, m.id_arbitre1, m.id_arbitre2, m.id_arbitre3, "
//                + "m.id_arbitre4, m.date, m.nb_spectateur FROM billet b INNER JOIN matchs m ON m.id = b.id_match WHERE b.id = ? ) m INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 "
//                + "INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4) m "
//                + "INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id "
//                + "INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;";
//        Billet b = new Billet();
//        Match match = new Match();
//
//        try {
//            ps = cnx.prepareStatement(sql);
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                b.setId(rs.getInt("id_billet"));
//                match.setNb_but1(rs.getInt("nb_but1"));
//                match.setNb_but2(rs.getInt("nb_but2"));
//                match.setStade(rs.getString("stade"));
//                match.setDate(rs.getTimestamp("date"));
//                match.setNb_spectateur(rs.getInt("nb_spectateur"));
//                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1")));
//                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
//                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(44), rs.getString(45), rs.getString(46)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1")));
//                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(47), rs.getString(48), rs.getString(49)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2")));
//                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(50), rs.getString(51), rs.getString(52)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3")));
//                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(53), rs.getString(54), rs.getString(55)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4")));
//                b.setMatch(match);
//                b.setBloc(rs.getString("bloc"));
//                b.setPrix(rs.getFloat("prix_billet"));
//                return b;
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

    public List<Billet> getBilletByUser(int id) {
        String sql = "select * from (SELECT m.id_billet, m.bloc,m.prix_billet, m.id_match,m.nb_but1,m.nb_but2, e1.stade, m.id_arbitre1, b1.nom nom_arbitre1,b1.email email_arbitre1, b1.prenom prenom_arbitre1, "
                + "b1.id_role role_arbitre1, b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, "
                + "b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3,b2.email email_arbitre2, b3.image image_arbitre3, b3.age age_arbitre3 ,"
                + "b3.email email_arbitre3, m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4,b4.email email_arbitre4, b4.image image_arbitre4, b4.age age_arbitre4 ,"
                + "m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nomeq nom_equipe1,e1.logo logo_equipe1, e1.nomeq entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nomeq nom_equipe2,"
                + "e2.logo logo_equipe2, e1.nom_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM (SELECT b.id id_billet, b.bloc, b.prix prix_billet,b.id_match, m.equipe1, m.equipe2, m.nb_but1, "
                + "m.nb_but2, m.stade, m.id_arbitre1, m.id_arbitre2, m.id_arbitre3, m.id_arbitre4, m.date, m.nb_spectateur FROM billet b INNER JOIN matchs m ON m.id = b.id_match where id_user=?) m "
                + "INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 "
                + "INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id "
                + "INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;";
//       select * from (SELECT m.id_billet, m.bloc,m.prix_billet, m.id_match,m.nb_but1,m.nb_but2, m.stade, m.id_arbitre1, b1.nom nom_arbitre1,b1.email email_arbitre1, b1.prenom prenom_arbitre1, b1.id_role role_arbitre1, b1.image image_arbitre1, b1.age age_arbitre1 , m.id_arbitre2, b2.nom nom_arbitre2, b2.prenom prenom_arbitre2, b2.id_role role_arbitre2, b2.image image_arbitre2, b2.age age_arbitre2 , m.id_arbitre3, b3.nom nom_arbitre3, b3.prenom prenom_arbitre3, b3.id_role role_arbitre3,b2.email email_arbitre2, b3.image image_arbitre3, b3.age age_arbitre3 ,b3.email email_arbitre3, m.id_arbitre4, b4.nom nom_arbitre4, b4.prenom prenom_arbitre4, b4.id_role role_arbitre4,b4.email email_arbitre4, b4.image image_arbitre4, b4.age age_arbitre4 ,m.date, m.nb_spectateur, m.equipe1 id_equipe1 ,e1.nomeq nom_equipe1,e1.logo logo_equipe1, e1.nomeq entreneur_equipe1, e1.niveau niveau_equipe1,m.equipe2 id_equipe2,e2.nomeq nom_equipe2,e2.logo logo_equipe2, e1.nom_entreneur entreneur_equipe2, e1.niveau niveau_equipe2 FROM (SELECT b.id id_billet, b.bloc, b.prix prix_billet,b.id_match, m.equipe1, m.equipe2, m.nb_but1, m.nb_but2, m.stade, m.id_arbitre1, m.id_arbitre2, m.id_arbitre3, m.id_arbitre4, m.date, m.nb_spectateur FROM billet b INNER JOIN matchs m ON m.id = b.id_match ) m INNER JOIN equipe e1 ON e1.id = m.equipe1 INNER JOIN equipe e2 ON e2.id = m.equipe2 INNER JOIN arbitre b1 ON b1.id = m.id_arbitre1 INNER JOIN arbitre b2 ON b2.id = m.id_arbitre2 INNER JOIN arbitre b3 ON b3.id = m.id_arbitre3 INNER JOIN arbitre b4 ON b4.id = m.id_arbitre4) m INNER JOIN role_arbitre r1 on m.role_arbitre1=r1.id INNER JOIN role_arbitre r2 on m.role_arbitre2=r2.id INNER JOIN role_arbitre r3 on m.role_arbitre3=r3.id INNER JOIN role_arbitre r4 on m.role_arbitre4=r4.id;
        List<Billet> list = new ArrayList<>();

        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Billet b = new Billet();
                Match match = new Match();
                b.setId(rs.getInt("id_billet"));
                match.setId(rs.getInt("id_match"));
                match.setNb_but1(rs.getInt("nb_but1"));
                match.setNb_but2(rs.getInt("nb_but2"));
                match.setDate(rs.getTimestamp("date"));
                match.setNb_spectateur(rs.getInt("nb_spectateur"));
                match.setEquipe1(new Equipe(rs.getInt("id_equipe1"), rs.getString("nom_equipe1"), rs.getString("logo_equipe1"), rs.getString("entreneur_equipe1"), rs.getString("niveau_equipe1"), rs.getString("stade")));
                match.setEquipe2(new Equipe(rs.getInt("id_equipe2"), rs.getString("nom_equipe2"), rs.getString("logo_equipe2"), rs.getString("entreneur_equipe2"), rs.getString("niveau_equipe2")));
                match.setArbiter1(new Arbitres(rs.getInt("id_arbitre1"), rs.getString("nom_arbitre1"), rs.getString("prenom_arbitre1"), new Roles(rs.getInt(48), rs.getString(49), rs.getString(50)), rs.getString("image_arbitre1"), rs.getInt("age_arbitre1"), rs.getString("email_arbitre1")));
                match.setArbiter2(new Arbitres(rs.getInt("id_arbitre2"), rs.getString("nom_arbitre2"), rs.getString("prenom_arbitre2"), new Roles(rs.getInt(51), rs.getString(52), rs.getString(53)), rs.getString("image_arbitre2"), rs.getInt("age_arbitre2"), rs.getString("email_arbitre2")));
                match.setArbiter3(new Arbitres(rs.getInt("id_arbitre3"), rs.getString("nom_arbitre3"), rs.getString("prenom_arbitre3"), new Roles(rs.getInt(54), rs.getString(55), rs.getString(56)), rs.getString("image_arbitre3"), rs.getInt("age_arbitre3"), rs.getString("email_arbitre3")));
                match.setArbiter4(new Arbitres(rs.getInt("id_arbitre4"), rs.getString("nom_arbitre4"), rs.getString("prenom_arbitre4"), new Roles(rs.getInt(57), rs.getString(58), rs.getString(59)), rs.getString("image_arbitre4"), rs.getInt("age_arbitre4"), rs.getString("email_arbitre4")));
                b.setMatch(match);
                b.setBloc(rs.getString("bloc"));
                b.setPrix(rs.getFloat("prix_billet"));
                list.add(b);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean billet_disponible(Match m) {

        String sql = "select count(*) < (SELECT nb_spectateur from matchs where id = ?) billet_disponible from billet where id_match = ?;";
        boolean billetDisponible = false;
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, m.getId());
            ps.setInt(2, m.getId());

            rs = ps.executeQuery();
            if (rs.next()) {
                billetDisponible = rs.getBoolean("billet_disponible");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return billetDisponible;

    }

    public boolean reserverBillet(Billet billet) {

        if (billet_disponible(billet.getMatch())) {

            int id = this.insertID(billet);
            billet.setId(id);
            QRcodeGen(billet.toString(), id);
            Mailing mail = new Mailing();
            mail.envoyerMail("charef.houssem@esprit.tn", billet);
            return true;
        }

        return false;
    }

    public void QRcodeGen(String input, int id) {
        try {
            ByteArrayOutputStream output = QRCode.from(input).to(ImageType.JPG).withSize(500, 500).stream();
            String absolutePath = new File("").getAbsolutePath();
            File f = new File(absolutePath + "\\src\\GUI\\QRCode\\" + id + ".jpg");
            FileOutputStream fos;

            fos = new FileOutputStream(f);

            fos.write(output.toByteArray());
            fos.flush();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BilletService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BilletService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
