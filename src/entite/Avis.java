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
public class Avis {
    private int id;
    private User user;
    private produit produit;
    private float avis;

    public Avis() {
    }

    public Avis(int id, User user, produit produit, float avis) {
        this.id = id;
        this.user = user;
        this.produit = produit;
        this.avis = avis;
    }

    public Avis(User user, produit produit, float avis) {
        this.user = user;
        this.produit = produit;
        this.avis = avis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public float getAvis() {
        return avis;
    }

    public void setAvis(float avis) {
        this.avis = avis;
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
        final Avis other = (Avis) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.produit, other.produit)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", user=" + user + ", produit=" + produit + ", avis=" + avis + '}';
    }

    
    
}
