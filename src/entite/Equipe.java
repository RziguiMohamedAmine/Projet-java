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
public class Equipe {
    private int id;
    private String nom;
    private String logo;
    private String nom_entreneur;
    private String niveau;
    private String stade;
    public Equipe() {
    }

    public Equipe(String nom, String logo, String nom_entreneur, String niveau,String stade) {
        this.nom = nom;
        this.logo = logo;
        this.nom_entreneur = nom_entreneur;
        this.niveau = niveau;
        this.stade=stade;
    }

    
     public Equipe(String nom) {
        this.nom = nom;
      
    }

    public Equipe(int id, String nom, String logo, String nom_entreneur, String niveau,String stade) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
        this.nom_entreneur = nom_entreneur;
        this.niveau = niveau;
        this.stade=stade;
    }

     public Equipe(int id, String nom, String logo,String stade) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
        this.stade=stade;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNom_entreneur() {
        return nom_entreneur;
    }

    public void setNom_entreneur(String nom_entreneur) {
        this.nom_entreneur = nom_entreneur;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
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
        final Equipe other = (Equipe) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", nom=" + nom + ", logo=" + logo + ", nom_entreneur=" + nom_entreneur + ", niveau=" + niveau + ", stade=" + stade + '}';
    }

  
    
    
    
    
    
    
}
