/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Objects;

/**
 *
 * @author R I B
 */
public class Roles {
   private int id ;
   private String role ;
   private String Descp ; 

    public Roles() {
    }

    public Roles(int id) {
        this.id = id;
    }

    public Roles(int id, String role, String Descp) {
        this.id = id;
        this.role = role;
        this.Descp = Descp;
    }

    public Roles(String role, String Descp) {
        this.role = role;
        this.Descp = Descp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescp() {
        return Descp;
    }

    public void setDescp(String Descp) {
        this.Descp = Descp;
    }

    @Override
    public String toString() {
        return "Roles{\n" + "id=" + id + ", role=" + role + ", Descp=" + Descp + '}';
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
        final Roles other = (Roles) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.Descp, other.Descp)) {
            return false;
        }
        return true;
    }
   
}
