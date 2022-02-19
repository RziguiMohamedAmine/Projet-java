/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author moham
 */


public class Joueur {
    private int id;
    private String nom;
    private String prenom;
    private String poste;
    private String nationalite;
    private Date date_naiss;
    private float taille;
    private float poids;
    private String image;
    private Equipe equipe;

      public Joueur() {
    }

    public Joueur(String nom, String prenom, String poste, String nationalite, Date date, float taille, float poids, String image, Equipe equipe) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.nationalite = nationalite;
        this.date_naiss = date;
        this.taille = taille;
        this.poids = poids;
        this.image = image;
        this.equipe = equipe;
    }
    
     public Joueur(int id,String nom, String prenom, String poste, String nationalite, Date date, float taille, float poids, String image) {
         this.id = id;
         this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.nationalite = nationalite;
        this.date_naiss = date;
        this.taille = taille;
        this.poids = poids;
        this.image = image;
    }

    public Joueur(int id, String nom, String prenom, String poste, String nationalite, Date date, float taille, float poids, String image, Equipe equipe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.nationalite = nationalite;
        this.date_naiss = date;
        this.taille = taille;
        this.poids = poids;
        this.image = image;
        this.equipe = equipe;
    }

    public Joueur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

     public Joueur(int id, String nom, String prenom,Equipe equipe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.equipe = equipe;
    }
    
     public Joueur(String nom, String prenom,String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste=poste;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }


    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
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
        final Joueur other = (Joueur) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Joueur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", poste=" + poste + ", nationalite=" + nationalite + ", date_naiss=" + date_naiss + ", taille=" + taille + ", poids=" + poids + ", image=" + image + ", equipe=" + equipe + '}';
    }

   
    
    
    
 
    
}
