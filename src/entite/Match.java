/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Houssem Charef
 */
public class Match {

    private int id, nb_but1, nb_but2;
    private String stade;
    private long nb_spectateur;
    private Equipe equipe1, equipe2;
    private Arbitres arbiter1, arbiter2, arbiter3, arbiter4;
    private Timestamp date;
    private String saison;
    private int round;

    public Match() {
    }

    public Match(int nb_but1, int nb_but2, String stade, long nb_spectateur, Equipe equipe1, Equipe equipe2, Arbitres id_arbiter1, Arbitres id_arbiter2, Arbitres id_arbiter3, Arbitres id_arbiter4, Timestamp date) {
        this.nb_but1 = nb_but1;
        this.nb_but2 = nb_but2;
        this.stade = stade;
        this.nb_spectateur = nb_spectateur;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.arbiter1 = id_arbiter1;
        this.arbiter2 = id_arbiter2;
        this.arbiter3 = id_arbiter3;
        this.arbiter4 = id_arbiter4;
        this.date = date;
    }

    public Match(int nb_but1, int nb_but2, String stade, long nb_spectateur, Equipe equipe1, Equipe equipe2, Arbitres id_arbiter1, Arbitres id_arbiter2, Arbitres id_arbiter3, Arbitres id_arbiter4, Timestamp date, String saison, int Round) {
        this.nb_but1 = nb_but1;
        this.nb_but2 = nb_but2;
        this.stade = stade;
        this.nb_spectateur = nb_spectateur;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.arbiter1 = id_arbiter1;
        this.arbiter2 = id_arbiter2;
        this.arbiter3 = id_arbiter3;
        this.arbiter4 = id_arbiter4;
        this.date = date;
        this.saison = saison;
        this.round = Round;
    }

    public Match(int id, int nb_but1, int nb_but2, String stade, long nb_spectateur, Equipe equipe1, Equipe equipe2, Arbitres id_arbiter1, Arbitres id_arbiter2, Arbitres id_arbiter3, Arbitres id_arbiter4, Timestamp date) {
        this.id = id;
        this.nb_but1 = nb_but1;
        this.nb_but2 = nb_but2;
        this.stade = stade;
        this.nb_spectateur = nb_spectateur;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.arbiter1 = id_arbiter1;
        this.arbiter2 = id_arbiter2;
        this.arbiter3 = id_arbiter3;
        this.arbiter4 = id_arbiter4;
        this.date = date;
    }

    public Match(int id, int nb_but1, int nb_but2, String stade, long nb_spectateur, Equipe equipe1, Equipe equipe2, Arbitres id_arbiter1, Arbitres id_arbiter2, Arbitres id_arbiter3, Arbitres id_arbiter4, Timestamp date, String saison) {
        this.id = id;
        this.nb_but1 = nb_but1;
        this.nb_but2 = nb_but2;
        this.stade = stade;
        this.nb_spectateur = nb_spectateur;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.arbiter1 = id_arbiter1;
        this.arbiter2 = id_arbiter2;
        this.arbiter3 = id_arbiter3;
        this.arbiter4 = id_arbiter4;
        this.date = date;
        this.saison = saison;
    }

    public Match(int id, int nb_but1, int nb_but2, String stade, long nb_spectateur, Equipe equipe1, Equipe equipe2, Arbitres arbiter1, Arbitres arbiter2, Arbitres arbiter3, Arbitres arbiter4, Timestamp date, String saison, int round) {
        this.id = id;
        this.nb_but1 = nb_but1;
        this.nb_but2 = nb_but2;
        this.stade = stade;
        this.nb_spectateur = nb_spectateur;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.arbiter1 = arbiter1;
        this.arbiter2 = arbiter2;
        this.arbiter3 = arbiter3;
        this.arbiter4 = arbiter4;
        this.date = date;
        this.saison = saison;
        this.round = round;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.equipe1);
        hash = 23 * hash + Objects.hashCode(this.equipe2);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Match other = (Match) obj;
        if (!Objects.equals(this.equipe1, other.equipe2)) {
            return false;
        }
        if (!Objects.equals(this.equipe2, other.equipe1)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", nb_but1=" + nb_but1 + ", nb_but2=" + nb_but2 + ", stade=" + stade + ", nb_spectateur=" + nb_spectateur + ", equipe1=" + equipe1 + ", equipe2=" + equipe2 + ", arbiter1=" + arbiter1 + ", arbiter2=" + arbiter2 + ", arbiter3=" + arbiter3 + ", arbiter4=" + arbiter4 + ", date=" + date + ", saison=" + saison + ", round=" + round + '}';
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_but1() {
        return nb_but1;
    }

    public void setNb_but1(int nb_but1) {
        this.nb_but1 = nb_but1;
    }

    public int getNb_but2() {
        return nb_but2;
    }

    public void setNb_but2(int nb_but2) {
        this.nb_but2 = nb_but2;
    }

    public long getNb_spectateur() {
        return nb_spectateur;
    }

    public void setNb_spectateur(long nb_spectateur) {
        this.nb_spectateur = nb_spectateur;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public Arbitres getArbiter1() {
        return arbiter1;
    }

    public void setArbiter1(Arbitres arbiter1) {
        this.arbiter1 = arbiter1;
    }

    public Arbitres getArbiter2() {
        return arbiter2;
    }

    public void setArbiter2(Arbitres arbiter2) {
        this.arbiter2 = arbiter2;
    }

    public Arbitres getArbiter3() {
        return arbiter3;
    }

    public void setArbiter3(Arbitres arbiter3) {
        this.arbiter3 = arbiter3;
    }

    public Arbitres getArbiter4() {
        return arbiter4;
    }

    public void setArbiter4(Arbitres arbiter4) {
        this.arbiter4 = arbiter4;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void getEquipe1(Equipe equipe2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}