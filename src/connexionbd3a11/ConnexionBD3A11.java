/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionbd3a11;

import java.util.Scanner;
import java.sql.DriverManager;
import entite.Personne;
import entite.Arbitres;
import entite.Roles;
import java.sql.*;
import service.PersonneService;
import service.ArbitresServices;
import service.RolesServices;
import utils.Datasource;

/**
 *
 * @author wiemhjiri
 */
public class ConnexionBD3A11 {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Datasource ds1 = Datasource.getInstance();

        //   DataSource ds1= DataSource.getInstance();
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        int r;
       do{System.out.println(" ________________________________________\t");
        System.out.println(" MENU\t");
        System.out.println("1- Ajouter un arbitre\t");
        System.out.println("2- Supprimer un arbitre\t");
        System.out.println("3- Modifier un arbitre\t");
        System.out.println("4- Afficher la liste des arbitres\t");
         System.out.println("5- Ajouter un role\t");
        System.out.println("6- Supprimer un role\t");
        System.out.println("7- Modifier un role\t");
        System.out.println("8- Afficher la listes des roles\t");
          System.out.println(" ________________________________________\t");
        System.out.println("entrer le numero correspondant à votre choix");
        int a = sc1.nextInt();
        r = menu(a);
       }while(r!=0);
    }

static int menu(int x) {
        switch (x) {
        
            /*l'ajout*/
            case 1:
ArbitresServices as = new ArbitresServices();


                Scanner sc = new Scanner(System.in);
                Scanner scr = new Scanner(System.in);
                Scanner scr1 = new Scanner(System.in);
                Scanner scr2 = new Scanner(System.in);
                Scanner scr3 = new Scanner(System.in);
                System.out.println("vous avez selectionné‚ le choix " + x);
                String nom,prenom,image;
                int age,id_role;
                System.out.println("le id_role :");
                id_role = sc.nextInt();
                System.out.println("le nom:");
                nom = scr.nextLine();
                System.out.println("le prenom :");
                prenom = scr1.nextLine();
                System.out.println("l'image :");
                image = scr2.nextLine();
                System.out.println("l'age :");
                age = scr3.nextInt();
                

                Arbitres a = new Arbitres(nom, prenom,id_role,image,age);
                as.insert(a);

                break;
     /*SUPPRISSION*/
            case 2:
                System.out.println("vous avez selectionné‚ le choix " + x);
                Scanner scr7 = new Scanner(System.in);
                int i;
                System.out.println("le id :");
                i = scr7.nextInt();
                ArbitresServices as1= new ArbitresServices();
                Arbitres a1 = new Arbitres(i);
                as1.delete(a1);
               // System.out.println(as1.read());
                break;
            case 3:
              int id1,idr,age1;
              String nom1, prenom1,image1;
                System.out.println("vous avez selectionné‚ le choix " + x);
                ArbitresServices as2 = new ArbitresServices();
                 Scanner scr4 = new Scanner(System.in);
                 
                 Scanner scr5 = new Scanner(System.in);
                System.out.println("le id :");
                  id1 = scr4.nextInt();
                 System.out.println("le  nom :");
                  nom1 = scr5.nextLine();
                System.out.println("le prenom :");
                prenom1 = scr5.nextLine(); 
                System.out.println("le id :");
                  idr = scr4.nextInt();
                  System.out.println("l'image:");
                  image1 = scr5.nextLine();
                  System.out.println("l'age :");
                  age1 = scr4.nextInt();

                Arbitres a3 = new Arbitres(id1, nom1, prenom1,idr,image1,age1);
                as2.update(a3);
                        System.out.println("mise a jour avec succes :");
    
               
       
                
                break;
            case 4:
                System.out.println("vous avez selectionné‚ le choix " + x);
                ArbitresServices as3= new ArbitresServices();
                System.out.println("\t\n");
                System.out.println(as3.read());
                
                break;
                    case 5:
RolesServices rs = new RolesServices();


               
                Scanner sc2 = new Scanner(System.in);
                System.out.println("vous avez selectionné‚ le choix " + x);
                String role , Descp;
                
                
                System.out.println("le role:");
                role = sc2.nextLine();
                System.out.println("la descp :");
                Descp = sc2.nextLine();
            
                

                Roles  r= new Roles(role,Descp);
                rs.insert(r);

                break;
                   case 6:
                System.out.println("vous avez selectionné‚ le choix " + x);
                Scanner sc3 = new Scanner(System.in);
                int ID;
                System.out.println("le id :");
                ID = sc3.nextInt();
                RolesServices rs1= new RolesServices();
                Roles r1 = new Roles(ID);
                rs1.delete(r1);
                
               System.out.println(rs1.read());
                break;
                
                   case 7:
              int ID1;
              String Descp1,role1;
                System.out.println("vous avez selectionné‚ le choix " + x);
              
                 Scanner sc4 = new Scanner(System.in);
                 Scanner sc5 = new Scanner(System.in);

                System.out.println("le id :");
                  id1 = sc4.nextInt();
                 System.out.println("le role :");
                  role1 = sc5.nextLine();
                System.out.println("la descp:");
                 Descp1 = sc5.nextLine();
                 RolesServices rs3 = new RolesServices();
                Roles p3 = new Roles(id1, role1, Descp1);
                rs3.update(p3);
                        System.out.println("mise a jour avec succes :");
    
               
       
                
                break;
                   case 8 :
                System.out.println("vous avez selectionné‚ le choix " + x);
                RolesServices rs2= new RolesServices();
                System.out.println("\t\n");
                System.out.println(rs2.read());
                
                break;
            default: {
                System.out.println("vous n'avez selectionné‚ aucun choix ");
            }
        }

        return x;
 }
    
}
