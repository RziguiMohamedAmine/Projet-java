/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Objects;

/**
 *
 * @author moham
 */
public class Transfert {
    private int id;
    private Equipe ancieneq;
    private Equipe nouveaueq;
    private Joueur joueur;

    public Transfert() {
    }

    public Transfert(int id, Equipe ancieneq, Equipe nouveaueq, Joueur joueur) {
        this.id = id;
        this.ancieneq = ancieneq;
        this.nouveaueq = nouveaueq;
        this.joueur = joueur;
    }

    public Transfert(Equipe ancieneq, Equipe nouveaueq, Joueur joueur) {
        this.ancieneq = ancieneq;
        this.nouveaueq = nouveaueq;
        this.joueur = joueur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipe getAncieneq() {
        return ancieneq;
    }

    public void setAncieneq(Equipe ancieneq) {
        this.ancieneq = ancieneq;
    }

    public Equipe getNouveaueq() {
        return nouveaueq;
    }

    public void setNouveaueq(Equipe nouveaueq) {
        this.nouveaueq = nouveaueq;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Transfert other = (Transfert) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ancieneq, other.ancieneq)) {
            return false;
        }
        if (!Objects.equals(this.nouveaueq, other.nouveaueq)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transfert{" + "id=" + id + ", ancieneq=" + ancieneq + ", nouveaueq=" + nouveaueq + ", joueur=" + joueur + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
}
