/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.categorie;
import entite.produit;
import utils.DataSource;
import service.ServiceProduit;
import service.ServiceCategorie;

/**
 *
 * @author Houssem Charef
 */
public class Federation {
    
    public static void main(String[] args) {
       
       //ServiceCategorie sp = new ServiceCategorie();
        ServiceProduit sp = new ServiceProduit();
     
      //  produit p = new produit(3, "hmed", "image", 0, "description", 0);
        
      //**********ajout***********
      sp.insert(new produit(1, "sofien", "image", 59, "test1", 0));
      //  sp.insert(new categorie(1,"T-shirt"));
      
       //************Suppression************
      //  sp.supprimer(p.getId());
      
      //*********modification**********
      //sp.update(new produit(1, "nom", "image", 70, "description", 0));
      

       //***********affichage**********  
      //  System.out.println(sp.getAll());
       
        
    }
    
}
