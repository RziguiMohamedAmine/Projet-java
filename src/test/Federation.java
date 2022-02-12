/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Personne;
import entite.Role;
import entite.User;
import utils.DataSource;
import service.PersonneService;
import service.RoleService;
import service.UserService;

/**
 *
 * @author Houssem Charef
 */
public class Federation {
    
    public static void main(String[] args) {
        User u1=new User("hamdi","aouichaoui","hamdi@esprit.tn","0000","ggv",75,"ggl");
        User u2=new User("fhhfhf","tuitt","vkfkf@gjk","00","kjfk",85,"78");
        UserService ps =new UserService();
       
        //ps.insert(u1);
        // ps.insert(u2);
        //ps.update(u1);
        //u2.setId(8);
        //User u10=new User(8, "kjhjk", "bjh", "hjkhh", "jhj", "515", 1, "jhjhj");
       // ps.update(u10);
          //ps.delete(u10);
         // System.out.println(ps.getAll());
          //System.out.println(ps.getOne(10));
        Role r1=new Role("admin");
        Role r2=new Role("simple utilisateur");
        RoleService rs =new RoleService();
        //rs.insert(r1);
        //rs.insert(r2);
        Role r3=new Role(32,"simple");
        //rs.update(r3);
        //rs.delete(r3);
        System.out.println(rs.getAll());
        System.out.println(rs.getOne(29));
        
    }
    
}
