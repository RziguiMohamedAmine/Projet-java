/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

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
    private categorie cat;
    private int stock;
    private int code ;
    

    public produit() {
    }

    

    
    public produit(int id, String nom, String image, float prix, String description, categorie cat) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.description = description;
        this.cat = cat;
    }
     public produit(String nom, String image, float prix, String description, categorie cat) {
     
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.description = description;
        this.cat = cat;
    }

    public produit(int id, String nom, String image, float prix, String description, categorie cat, int stock) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.description = description;
        this.cat = cat;
        this.stock = stock;
      
    }

    public produit( String nom, String image, float prix, String description, categorie cat, int stock) {
        
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.description = description;
        this.cat = cat;
        this.stock = stock;
    }

    public produit(int id, String nom, String image, float prix, String description, categorie cat, int stock, int code) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.description = description;
        this.cat = cat;
        this.stock = stock;
        this.code = code;
    }

    public produit(String nom, String image, float prix, String description, categorie cat, int stock, int code) {
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.description = description;
        this.cat = cat;
        this.stock = stock;
        this.code = code;
    }

    
    
    

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public categorie getCat() {
        return cat;
    }

    public void setCat(categorie cat) {
        this.cat = cat;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
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
        final produit other = (produit) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", nom=" + nom + ", image=" + image + ", prix=" + prix + ", description=" + description + ", cat=" + cat + ", stock=" + stock + ", code=" + code + '}';
    }

    

    
    
   
    

    
    
    
    

    
    

    
    
    
    

    
}
