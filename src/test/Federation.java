/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Avis;
import entite.User;
import entite.categorie;
import entite.produit;
import service.ServiceAvis;
import utils.DataSource;
import service.ServiceProduit;
import service.ServiceCategorie;
import service.UserService;

/**
 *
 * @author Houssem Charef
 */
public class Federation {
    
    public static void main(String[] args) {
       
       ServiceCategorie sc = new ServiceCategorie();
        ServiceProduit sp = new ServiceProduit();
        ServiceAvis sa=new ServiceAvis();
        UserService su =new UserService();
        
//        produit p = new produit(1,"hmed", "image", 0, "description", 2);
         User u1=new User(2,"hamdi","aouichaoui","hamdi@esprit.tn","0000","87521221","admin","image");
         //su.insert(u1);
//         Avis a=new Avis(u1,p,9);
      //**********ajout***********
       //sp.insert(p);
       //sc.insert(new categorie(1,"Pantalon"));
      
       //************Suppression************
      // p.setId(12);
       //sp.delete(p);
      
      //*********modification**********
   //   sp.update(new produit(13, "nom", "image", 70, "description", 0));
      
      

       //***********affichage**********  
      // System.out.println(sp.getAll());
      // System.out.println(sp.getOnebyname("hmed"));
//        sa.insert(a);
        
        System.out.println(sa.getAvisByProduit(18)); 
          System.out.println(sa.getCountAverageAvisByProduit(18)); 

    }
    
}
