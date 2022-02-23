/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Objects;

/**
 *
 * @author sof
 */
public class produit {
    
    private int id;
    private String nom;
    private String image;
    private float prix;
    private String description;
    private int id_cat;

    public produit() {
    }

    public produit(int id, String nom, String image, float prix, String description, int id_cat) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.description = description;
        this.id_cat = id_cat;
    }
     public produit(String nom, String image, float prix, String description, int id_cat) {
     
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.description = description;
        this.id_cat = id_cat;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public float getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", nom=" + nom + ", image=" + image + ","
                + " prix=" + prix + ", description=" + description + ", id_cat=" + id_cat + '}';
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
        final produit other = (produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (this.id_cat != other.id_cat) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
    

    
}
