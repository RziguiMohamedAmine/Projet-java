/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Objects;
import entite.Roles;
//Rolesimport javax.management.relation.Role;

/**
 *
 * @author R I B
 */
public class Arbitres {

    private int id;
    private String nom;
    private String prenom;
    private Roles id_role;
    private String image;
    private int age;
    private int Avis;

    public Arbitres(int id, String nom, String prenom, Roles id_role, String image, int age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.id_role = id_role;
        this.image = image;
        this.age = age;
    }

    public Arbitres(int id, String nom, String prenom, Roles id_role, String image, int age, int Avis) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.id_role = id_role;
        this.image = image;
        this.age = age;
        this.Avis = Avis;
    }

    public Arbitres() {
    }

    public Arbitres(int id) {
        this.id = id;
    }

    public Arbitres(String nom, String prenom, Roles id_role, String image, int age, int Avis) {
        this.nom = nom;
        this.prenom = prenom;
        this.id_role = id_role;
        this.image = image;
        this.age = age;
        this.Avis = Avis;
    }

    public Arbitres(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
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

    public Roles getId_role() {
        return id_role;
    }

    public void setId_role(Roles id_role) {
        this.id_role = id_role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAvis() {
        return Avis;
    }

    public void setAvis(int Avis) {
        this.Avis = Avis;
    }

    @Override
    public String toString() {
        return "Arbitres{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", id_role=" + id_role + ", image=" + image + ", age=" + age + ", Avis=" + Avis + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Arbitres other = (Arbitres) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (this.Avis != other.Avis) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.id_role, other.id_role)) {
            return false;
        }
        return true;
    }

}
