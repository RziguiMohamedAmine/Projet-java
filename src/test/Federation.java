/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;



import entite.User;
import java.sql.Timestamp;
import javax.mail.internet.ParseException;
import utils.DataSource;


import service.UserService;

/**
 *
 * @author Houssem Charef
 */
public class Federation {
    
    public static void main(String[] args)throws ParseException, Exception{
        User u1=new User("hamdi","aouichaoui","hamdi@esprit.tn","0000",87521221,"image","simple utilisateur");
        User u2=new User("ahmed","ahmed","ahmed@yahoo.fr","7r5dz3",87565222,"simple utilisateur");
        User u3=new User("ali","ali","ali@gmail.com","57flgm",65425222,"image54","simple utilisateur");
        User u5=new User("salah","ahmed","hamdi.aouichaoui@esprit.tn","rffkr",25874136,"image55","simple utilisateur");
        UserService ps =new UserService();
       
        //ps.insert(u1);
        //ps.insert(u2);
        //ps.insert(u3);
        //ps.insert(u5);
        User u4=new User("fjfjf", "kfkf", "kfflf", "kfkf", 82222, "fk");
//        ps.update(u4);
        //ps.delete(u4);
//        ps.insert(u4);
//       System.out.println(ps.getAll());
      // System.out.println(ps.getOne(34));
//        System.out.println(ps.block(new Timestamp(2022-1900, 1, 25, 0, 0, 0, 0), u4));
//ps.ban(u4);

         // ps.sendMailForgetPass("hamdi.aouichaoui@esprit.tn");
       System.out.println(ps.changePassForgetPassword("hamdi.aouichaoui@esprit.tn", "512463", "123"));

     // System.out.println(ps.login("hamdi.aouichaoui@esprit.tn", "hghgh"));
         
         
        
    }

    
    
}
