/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Equipe;
import entite.Joueur;
import entite.JoueurMatch;
import entite.Match;
import entite.Transfert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
//import javafx.util.converter.LocalDateStringConverter;
//import java.time.LocalDateTime;
import service.EquipeService;
import service.JoueurMatchService;
import service.JoueurService;
import service.MatchService;
import service.TransfertService;

/**
 *
 * @author moham
 */
public class Federation {
    
      /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws ParseException {
      //  LocalDate ddd = new LocalDate
       
            EquipeService es=new EquipeService();
            JoueurService js=new JoueurService();
            JoueurMatchService jms=new JoueurMatchService();
            MatchService mss = new MatchService();
            TransfertService ts=new TransfertService();
            
            
            
            Equipe e1=new Equipe(1,"ca","evvr","rfr","rrvr","ss");
            Equipe e2=new Equipe(2,"est","zhyr","rnyy","ryrn","dd");
            Equipe e3=new Equipe(3,"ess","vervzr22","vverv","rrvzr","svr") ;
            
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
       
             LocalDate dd=LocalDate.of(2022,Month.JANUARY,25);
         
             Joueur j1=new Joueur(1,"xxx","yyy","kk","tun",dd,2,7,"rrr",e2);
             Joueur j2=new Joueur(3,"joueur2","jjj","arriere","france",dd,2,7,"tt",e2);
             Joueur j4=new Joueur(4,"jrelequ","jjj","attaquant","france",dd,2,7,"tt",e2);
             Joueur j3=new Joueur("updated","upd","arriere","tunnn",dd,4,7,"rrr",null);
              
               js.insert(j1);
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
              //System.out.println("les joueurs d'equipe "+e2.getNom()+" sont:"+js.getjoueurbyequipe(e2));
  
               //System.out.println(js.getJoueurParPoste("arriere"));
               //System.out.println(js.getJoueurParNat("tun"));
              // Match m2 = new Match(6, 10, 20, "stade", 10, e1, e2, 6, 7, 8, 9, sdo);
              // Match m3 = new Match(5, 10, 20, "stade", 10, e1, e2, 6, 7, 8, 9, sdo); 
               //mss.insert(m3);
           //   JoueurMatch jm1 =new JoueurMatch(j2,m3,1,0,3);
             // JoueurMatch jm2 =new JoueurMatch(j4,m2,1,0,3);
              //System.out.println(js.getJoueurLibre());
               System.out.println(js.getScoreJoueur(j4)); 
             // jms.insert(jm2);
             //System.out.println("top scorers:"+js.TopScorer());
              Transfert t=new Transfert(e2, e3, j2);
              //ts.insert(t);
              
    }
    
}
