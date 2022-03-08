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
public class taille {
    
    private int id;
    private String nom;
    private produit prod;
    private int stock;

    public taille() {
    }

    public taille(int id, String nom, produit prod, int stock) {
        this.id = id;
        this.nom = nom;
        this.prod = prod;
        this.stock = stock;
    }

    public taille(String nom, produit prod, int stock) {
        this.nom = nom;
        this.prod = prod;
        this.stock = stock;
    }
    
    

    public taille(String nom) {
        this.nom = nom;
    }

    public taille(int id, String nom) {
        this.id = id;
        this.nom = nom;
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

    public produit getProd() {
        return prod;
    }

    public void setProd(produit prod) {
        this.prod = prod;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
        final taille other = (taille) obj;
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
        return "taille{" + "id=" + id + ", nom=" + nom + ", prod=" + prod + ", stock=" + stock + '}';
    }

    
    
}
