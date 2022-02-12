/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Equipe;
import entite.Joueur;
import service.EquipeService;
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
       
 
             JoueurService js=new JoueurService();
             Joueur j1=new Joueur("xxx","yyy","kk","tun",18,2,7,"rrr",8);
             Joueur j2=new Joueur("joueur2","jjj","arriere","france",19,2,7,"tt",7);
            // ps.insert(j1);
            // ps.insert(j2);
             Joueur j3=new Joueur("updated","upd","arriere","tunnn",17,4,7,"rrr",5);
              // ps.insert(j3);
              //****************************
             //j3.setId(1);
             //ps.update(j3);
             //*******************************
             //j3.setId(5);
              //ps.delete(j3);
              //***********************
             // System.out.println(js.getAll());
             // System.out.println("votre joueur est :"+js.getOne(2));
              //***********************
              
  //*******************************equipe*************************************
  
            EquipeService es=new EquipeService();
            Equipe e1=new Equipe("ca","evvr","rfr","rrvr");
            Equipe e2=new Equipe("est","zhyr","rnyy","ryrn");
              
           // es.insert(e1);
           // es.insert(e2);
             Equipe e3=new Equipe("essupdate","vervzr22","vverv","rrvzr") ;
            // es.insert(e3);
              //*********************************** 
             // e3.setId(1);
            //  es.update(e3);
              //***********************************
              // e3.setId(3);
              // es.delete(e3);
              //***********************************
              System.out.println(es.getAll());
              System.out.println("equipe a rechercher est :"+es.getOne(2));
              
              
              
              
              
              
              
              
              
    }
    
}
