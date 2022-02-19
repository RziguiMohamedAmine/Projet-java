/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;

/**
 *
 * @author Houssem Charef
 */
public class Match {

    private int id, nb_but1, nb_but2;
    private String stade;
    private long nb_spectateur;
    private Equipe equipe1, equipe2;
    private int id_arbiter1, id_arbiter2, id_arbiter3, id_arbiter4;
    private Date date;

    public Match() {
    }

    public Match(int nb_but1, int nb_but2, String stade, long nb_spectateur, Equipe equipe1, Equipe equipe2, int id_arbiter1, int id_arbiter2, int id_arbiter3, int id_arbiter4, Date date) {
        this.nb_but1 = nb_but1;
        this.nb_but2 = nb_but2;
        this.stade = stade;
        this.nb_spectateur = nb_spectateur;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.id_arbiter1 = id_arbiter1;
        this.id_arbiter2 = id_arbiter2;
        this.id_arbiter3 = id_arbiter3;
        this.id_arbiter4 = id_arbiter4;
        this.date = date;
    }

    
    public Match(int id, Equipe equipe1, Equipe equipe2) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
    }
    public Match(int id, int nb_but1, int nb_but2, String stade, long nb_spectateur, Equipe equipe1, Equipe equipe2, int id_arbiter1, int id_arbiter2, int id_arbiter3, int id_arbiter4, Date date) {
        this.id = id;
        this.nb_but1 = nb_but1;
        this.nb_but2 = nb_but2;
        this.stade = stade;
        this.nb_spectateur = nb_spectateur;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.id_arbiter1 = id_arbiter1;
        this.id_arbiter2 = id_arbiter2;
        this.id_arbiter3 = id_arbiter3;
        this.id_arbiter4 = id_arbiter4;
        this.date = date;
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
        if (this.id != other.id) {
            return false;
        }
        if (this.equipe1 != other.equipe1) {
            return false;
        }
        if (this.equipe2 != other.equipe2) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", nb_but1=" + nb_but1 + ", nb_but2=" + nb_but2 + ", stade=" + stade + ", nb_spectateur=" + nb_spectateur + ", equipe1=" + equipe1 + ", equipe2=" + equipe2 + ", id_arbiter1=" + id_arbiter1 + ", id_arbiter2=" + id_arbiter2 + ", id_arbiter3=" + id_arbiter3 + ", id_arbiter4=" + id_arbiter4 + ", date=" + date + '}';
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

    public int getId_arbiter1() {
        return id_arbiter1;
    }

    public void setId_arbiter1(int id_arbiter1) {
        this.id_arbiter1 = id_arbiter1;
    }

    public int getId_arbiter2() {
        return id_arbiter2;
    }

    public void setId_arbiter2(int id_arbiter2) {
        this.id_arbiter2 = id_arbiter2;
    }

    public int getId_arbiter3() {
        return id_arbiter3;
    }

    public void setId_arbiter3(int id_arbiter3) {
        this.id_arbiter3 = id_arbiter3;
    }

    public int getId_arbiter4() {
        return id_arbiter4;
    }

    public void setId_arbiter4(int id_arbiter4) {
        this.id_arbiter4 = id_arbiter4;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}