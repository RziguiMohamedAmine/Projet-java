/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package federation;

import java.util.Scanner;
import java.sql.DriverManager;

import entite.Arbitres;
import entite.Roles;
import entite.Match;
import java.sql.*;

import service.*;
import utils.DataSource;

/**
 *
 * @author wiemhjiri
 */
public class Federation {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        DataSource ds1 = DataSource.getInstance();

 matchArbitres ma= new matchArbitres();     
 RolesServices rs= new RolesServices();
 ArbitresServices as=new ArbitresServices();
 //Roles r= new Roles(2,"Juge internationale","veiller au bon déroulement des compétitions dans le respect du règlement");
 //Roles r1= new Roles(3,"arbitre 1","veiller au bon déroulement des compétitions dans le respect du règlement");
 //Roles r2= new Roles(4,"arbitre 2","veiller au bon déroulement des compétitions dans le respect du règlement");
 Roles r3= new Roles("arbitre assistant","veiller au bon déroulement des compétitions dans le respect du règlement");

 
 rs.insert(r3);
// rs.insert(r2);
//System.out.println(rs.read());
System.out.println(as.read());
 System.out.println("\t\t");
//System.out.println(ma.getARBparRole(4));
//System.out.println(ma.rechercherParNom("ben sallem"));


 //Arbitres a= new Arbitres(38,"ahmed","bensalleh",r1,"image1",37,"ahmed.bensalleh@gmail.com");
//Arbitres a1 = new Arbitres(50,"ahmed", "ben sellem",r2,"image1",37,1);
//System.out.println(ma.getmatchyARB(a));
 //as.insert(a1);
 //as.update(a1); 
 //as.delete(a1);
 //System.out.println(as.read());
 //System.out.println(as.readById(33));
 //System.out.println("----------------------------------\n");
 //affiche nombre des participation des arbitres dans les matchs
System.out.println(ma.getStatArbitreByCartons(32)); 
 //System.out.println("----------------------------------\n");
//recherche par nom
// System.out.println(ma.getOnebyname("jamel"));
Mailing.sendMail("sratsirine4@gmail.com");
//*-----

}
    
    
    
    
    
    
}