/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Billet;
import entite.Match;
import java.sql.Date;
import service.BilletService;
import service.MatchService;

/**
 *
 * @author Houssem Charef
 */
public class FTF_Desktop {

    public static void main(String[] args) {

        MatchService ms = new MatchService();
        //                                      match

//        Match m;
//        long millis = System.currentTimeMillis();
//        m = new Match(1, 2, "stade", 60000, 1, 2, 3, 6, 5, 8, new Date(millis));
//        System.out.println(ms.insert(m));
        long millis = System.currentTimeMillis();
        Match m2 = new Match(1, 10, 20, "stade", 10, 1, 2, 3, 6, 5, 8, new Date(millis));
//        ms.update(m2);
//        ms.delete(m2);
//        System.out.println(ms.getAll());

//        System.out.println(ms.getOne(2));
        //                              billet
        Billet b = new Billet(2, m2, "m33", 20000);

        BilletService bs = new BilletService();

//        bs.insert(b);
        bs.update(b);
        //        bs.delete(b);
//        System.out.println(bs.getAll());
        System.out.println(bs.getOne(10));
    }

}
