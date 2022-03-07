/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author hamdi
 */
public class User {
int id;
String nom;
String prenom;
String email;
String pass;
int tel;
String role;
public static User session;

    public User() {
    }

    public User(String nom, String prenom, String pass, int tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.pass = pass;
        this.tel = tel;
    }

    
    public User(int id) {
        this.id = id;
    
    }

    public User(int id, String nom, String prenom, String email, String pass, int tel ,String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pass = pass;
        this.tel = tel;
        this.role = role;
    }

    public User(String nom, String prenom, String email, String pass, int tel,String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pass = pass;
        this.tel = tel;
        this.role = role;
       
    }
    
    

    public User(String nom, String prenom, String email, String pass, int tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pass = pass;
        this.tel = tel;
        
    }
    


    public User(int id, String nom, String prenom, String email, String pass, int tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pass = pass;
        this.tel = tel;
        
        
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.prenom);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.pass);
        hash = 67 * hash + this.tel;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (this.tel != other.tel) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", pass=" + pass + ", tel=" + tel + ",role=" + role + '}';
    }

    

    
   
    
    

    

     
    }

    

   
       
   


    
    

