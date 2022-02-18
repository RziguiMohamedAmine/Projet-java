/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Equipe;
import entite.Joueur;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import service.EquipeService;
import service.JoueurService;

/**
 *
 * @author moham
 */
public class Federation {
    
      /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws ParseException {
       
            EquipeService es=new EquipeService();
            JoueurService js=new JoueurService();
            
            
            Equipe e1=new Equipe(1,"ca","evvr","rfr","rrvr");
            Equipe e2=new Equipe(2,"est","zhyr","rnyy","ryrn");
            Equipe e3=new Equipe(3,"ess","vervzr22","vverv","rrvzr") ;
            
            //es.insert(e1);
            //es.insert(e2); 
            //es.insert(e3);
            //**********************
            //e1.setId(1);
           // es.update(e1);
            //************************
           // e3.setId(2);
           // es.delete(e3);
           //************************
           //System.out.println("Vos equipe:"+es.getAll()); 
           //*************************************
           // System.out.println("equipe a rechercher est :"+es.getOne(3));
        //***********************************************************************************************************
        //**********************************************************************************************************
               
        
           SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
           String dob="1990-12-01";
           java.util.Date dn=sd.parse(dob);
           long ms=dn.getTime();
           java.sql.Date sdo=new java.sql.Date(ms);
       
            
             Joueur j1=new Joueur("xxx","yyy","kk","tun",sdo,2,7,"rrr",e2);
             Joueur j2=new Joueur("joueur2","jjj","arriere","france",sdo,2,7,"tt",e2);
             Joueur j4=new Joueur("jrelequ","jjj","arriere","france",sdo,2,7,"tt",e3);
             Joueur j3=new Joueur("updated","upd","arriere","tunnn",sdo,4,7,"rrr",e2);
              
               //js.insert(j1);
               //js.insert(j2); 
               //js.insert(j4);
              
              //****************************     
             //j3.setId(1);
             // Joueur j5=new Joueur (2,"joueurmoddd","jjj","arriere","france",19,2,7,"tt",e2);
             //js.update(j3);
             //*****************************
               //j3.setId(2);
               //js.delete(j3);
              //****************************
              //System.out.println(js.getAll());
              //System.out.println("votre joueur est :"+js.getOne(3));
              //***********************
              //        ²²²²System.out.println("vos joueurs :"+js.getjoueurbyequipe(e2));
  
              
              
              
              
              
    }
    
}
