/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Objects;

/**
 *
 * @author Houssem Charef
 */
public class Classment {

    private int id;
    private Equipe equipe;
    private int nb_totale_but, nb_point;
    private String saison;
    private int nb_win, nb_draw, nb_loose;

    public Classment(Equipe equipe, int nb_totale_but, int nb_point, String saison, int nb_win, int nb_draw, int nb_loose) {
        this.equipe = equipe;
        this.nb_totale_but = nb_totale_but;
        this.nb_point = nb_point;
        this.saison = saison;
        this.nb_win = nb_win;
        this.nb_draw = nb_draw;
        this.nb_loose = nb_loose;
    }

    public Classment(int id, Equipe equipe, int nb_totale_but, int nb_point, String saison, int nb_win, int nb_draw, int nb_loose) {
        this.id = id;
        this.equipe = equipe;
        this.nb_totale_but = nb_totale_but;
        this.nb_point = nb_point;
        this.saison = saison;
        this.nb_win = nb_win;
        this.nb_draw = nb_draw;
        this.nb_loose = nb_loose;
    }

    @Override
    public String toString() {
        return "Classment{" + "id=" + id + ", equipe=" + equipe + ", nb_totale_but=" + nb_totale_but + ", nb_point=" + nb_point + ", saison=" + saison + ", nb_win=" + nb_win + ", nb_draw=" + nb_draw + ", nb_loose=" + nb_loose + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.equipe);
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
        final Classment other = (Classment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.equipe, other.equipe)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public int getNb_totale_but() {
        return nb_totale_but;
    }

    public void setNb_totale_but(int nb_totale_but) {
        this.nb_totale_but = nb_totale_but;
    }

    public int getNb_point() {
        return nb_point;
    }

    public void setNb_point(int nb_point) {
        this.nb_point = nb_point;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public int getNb_win() {
        return nb_win;
    }

    public void setNb_win(int nb_win) {
        this.nb_win = nb_win;
    }

    public int getNb_draw() {
        return nb_draw;
    }

    public void setNb_draw(int nb_draw) {
        this.nb_draw = nb_draw;
    }

    public int getNb_loose() {
        return nb_loose;
    }

    public void setNb_loose(int nb_loose) {
        this.nb_loose = nb_loose;
    }

}
