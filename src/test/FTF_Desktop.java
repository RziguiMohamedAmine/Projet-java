/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Billet;
import entite.Equipe;
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
        //**************************match**************************

        long millis = System.currentTimeMillis();
        Equipe e1 = new Equipe(3, "ddd", "ssss", "dddd", "dddd");
        Equipe e2 = new Equipe(1, "ddd", "ssss", "dddd", "dddd");

        Match m2 = new Match(16, 10, 20, "stade", 10, e1, e2, 6, 7, 8, 9, new Date(millis));
//        ms.insert(m2);
//        ms.update(m2);
//        ms.delete(m2);
//        System.out.println(ms.getAll());
//        System.out.println(ms.getOne(16));
        //**************************billet**************************
        Billet b = new Billet(11, m2, "m33", 10);

        BilletService bs = new BilletService();
//        bs.insert(b);
//        bs.update(b);
//        bs.delete(b);
//        System.out.println(bs.getAll());
//        System.out.println(bs.getOne(12));
//        System.out.println(ms.getMatchsByDate("2022-02-16"));
        System.out.println(ms.getmatchsByEquipe(e2));
    }

}
