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
       
            EquipeService es=new EquipeService();
            JoueurService js=new JoueurService();
            Equipe e1=new Equipe("ca","evvr","rfr","rrvr");
            Equipe e2=new Equipe("est","zhyr","rnyy","ryrn");
            Equipe e3=new Equipe("essupdate","vervzr22","vverv","rrvzr") ;
            e2.setId(2);
            
           // es.insert(e1);
           // es.insert(e2);
            
            
            
             Joueur j1=new Joueur("xxx","yyy","kk","tun",18,2,7,"rrr",e1);
             Joueur j2=new Joueur("joueur2","jjj","arriere","france",19,2,7,"tt",e1);
             Joueur j4=new Joueur("jrelequ","jjj","arriere","france",19,2,7,"tt",e2);
             //js.insert(j4);
            // ps.insert(j2);
             Joueur j3=new Joueur("updated","upd","arriere","tunnn",17,4,7,"rrr",e2);
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
              System.out.println("votre joueur est :"+js.getOne(2));
  //*******************************equipe*************************************
  
           
              
           // es.insert(e1);
           // es.insert(e2);
            
            // es.insert(e3);
              //*********************************** 
             // e3.setId(1);
            //  es.update(e3);
              //***********************************
              // e3.setId(3);
              // es.delete(e3);
              //***********************************
              //System.out.println(es.getAll());
             // System.out.println("equipe a rechercher est :"+es.getOne(2));
              
              
              
              
              
              
              
              
              
    }
    
}
