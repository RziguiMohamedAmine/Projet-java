/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;


/**
 *
 * @author R I B
 */
public class JoueurMatch {
    private int id;
    private Joueur joueur;
    private Match match;
    private int jaune;
    private int rouge;
    private int nb_but;

    public JoueurMatch() {
    }

    public JoueurMatch(Joueur joueur, int nb_but) {
        this.joueur = joueur;
        this.nb_but = nb_but;
    }

    
    
    public JoueurMatch(int id, Joueur joueur, Match match, int jaune, int rouge, int nb_but) {
        this.id = id;
        this.joueur = joueur;
        this.match = match;
        this.jaune = jaune;
        this.rouge = rouge;
        this.nb_but = nb_but;
    }

    public JoueurMatch(Joueur joueur, Match match, int jaune, int rouge, int nb_but) {
        this.joueur = joueur;
        this.match = match;
        this.jaune = jaune;
        this.rouge = rouge;
        this.nb_but = nb_but;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

   

    public int getJaune() {
        return jaune;
    }

    public void setJaune(int jaune) {
        this.jaune = jaune;
    }

    public int getRouge() {
        return rouge;
    }

    public void setRouge(int rouge) {
        this.rouge = rouge;
    }

    public int getNb_but() {
        return nb_but;
    }

    public void setNb_but(int nb_but) {
        this.nb_but = nb_but;
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
        final JoueurMatch other = (JoueurMatch) obj;
        if (this.id != other.id) {
            return false;
        }
     
        return true;
    }

    @Override
    public String toString() {
        return "JoueurMatch{" + "id=" + id + ", joueur=" + joueur + ", match=" + match + ", jaune=" + jaune + ", rouge=" + rouge + ", nb_but=" + nb_but + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}