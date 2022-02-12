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
public class Billet {

    private int id;
    private Match match;
    private String bloc;
    private double prix;

    public Billet() {
    }

    public Billet(Match match, String bloc, double prix) {
        this.match = match;
        this.bloc = bloc;
        this.prix = prix;
    }

    public Billet(int id, Match match, String bloc, double prix) {
        this.id = id;
        this.match = match;
        this.bloc = bloc;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Billet{" + "id=" + id + ", match=" + match + ", bloc=" + bloc + ", prix=" + prix + '}';
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
        final Billet other = (Billet) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.match, other.match)) {
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

}
