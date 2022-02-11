/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Personne;
import utils.DataSource;
import service.PersonneService;

/**
 *
 * @author Houssem Charef
 */
public class Federation {
    
    public static void main(String[] args) {
        //        DataSource ds1 = DataSource.getInstance();
        //
        //        DataSource ds2 = DataSource.getInstance();
        //        System.out.println(ds1);
        //        System.out.println(ds2);
        Personne p1 = new Personne("charef", "charef");
        PersonneService ps = new PersonneService();
        ps.insertPersonne(p1);
        
    }
    
}
