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
        User u1=new User("hamdi","aouichaoui","hamdi@esprit.tn","0000","87521221","admin","image");
        User u2=new User("ahmed","ahmed","ahmed@yahoo.fr","7r5dz3","87565222","simple utilisateur","image1");
        User u3=new User("ali","ali","ali@gmail.com","57flgm","65425222","simple utilisateur","image54");
        UserService ps =new UserService();
       
        //ps.insert(u1);
        //ps.insert(u2);
        //ps.insert(u3);
        User u4=new User(33,"oussema", "ali", "charef.housse@esprit.tn", "jhj56", "51585485", "admin", "image");
//        ps.update(u4);
        //ps.delete(u4);
//        ps.insert(u4);
//       System.out.println(ps.getAll());
       System.out.println(ps.getOne(34));
//        System.out.println(ps.block(new Timestamp(2022-1900, 1, 25, 0, 0, 0, 0), u4));
//ps.ban(u4);

ps.sendMailForgetPass("charef.houssem@esprit.tn");
//       System.out.println(ps.changePassForgetPassword("charef.houssem@esprit.tn", "143079", "123456"));

//         System.out.println(ps.login("charef.houssem@esprit.tn", "123456"));
         
         
        
    }

    
    
}
