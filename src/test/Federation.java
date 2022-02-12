/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Joueur;
import utils.DataSource;
import service.JoueurService;

/**
 *
 * @author moham
 */
public class Federation {
    
      /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       
        
          Joueur j1=new Joueur("Ali","salah","arriere","tun",18,2,7,"rrr",8);
          JoueurService ps=new JoueurService();
          
          Joueur j2=new Joueur("updated","upd","arriere","tun",19,2,7,"rrr",8);
           Joueur j3=new Joueur("a recherche","u","att","tun",19,2,7,"rrr",8);
          // p2.setId(5);
          ps.insert(j3);
        //  ps.update(p1);
           //ps.insert(p2);
          // ps.delete(p2);
         // System.out.println(ps.getAll());
          System.out.println("votre joueur est :"+ps.getOne(6));
         
    }
    
}
