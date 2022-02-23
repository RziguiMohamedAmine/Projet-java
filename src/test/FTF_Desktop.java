/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entite.Arbitres;
import entite.Billet;
import entite.Equipe;
import entite.Match;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import service.BilletService;
import service.ClassmentService;
import service.MatchService;

/**
 *
 * @author Houssem Charef
 */
public class FTF_Desktop {
    
    public static void main(String[] args) {
        
        MatchService ms = new MatchService();
        //**************************match**************************

        Equipe e1 = new Equipe(4, "ddd", "ssss", "dddd", "dddd");
        Equipe e2 = new Equipe(1, "ddd", "ssss", "dddd", "dddd");
        Arbitres a1 = new Arbitres(6);
        Arbitres a2 = new Arbitres(7);
        Arbitres a3 = new Arbitres(8);
        Arbitres a4 = new Arbitres(9);

//        Match m2 = new Match(1, 1, 1, "stade", 10, e1, e2, a1, a2, a3, a4, new Timestamp(), "20202021");
//        ms.update(m2);
//        ms.insert(m2);
//        ms.update(m2);
//        ms.delete(m2);
//        System.out.println(ms.getAll());
//        System.out.println(ms.getOne(1));
//        List<Match> list = ms.getMatchsByDate("2022-02-15");
//        System.out.println(list.size());
//        List<Match> list = ms.getmatchsByEquipe(e2);
//        System.out.println(list);
        //**************************billet**************************
        Match m = ms.getOne(21088);
        m.setId(21088);
        Billet b = new Billet(11, m, "m33", 10);
        BilletService bs = new BilletService();
//        bs.insert(b);
//        bs.update(b);
//        bs.delete(b);
//        bs.getAll();
//        System.out.println(bs.getOne(12));
//        System.out.println(ms.getMatchsByDate("2022-02-16"));
//        System.out.println(ms.getmatchsByEquipe(e2));
//        Timestamp millis = Timestamp.valueOf(LocalDateTime.of(2022, Month.FEBRUARY, 25, 16, 30));
//        System.out.println(ms.tirage_au_sort("20202021", millis));
//        ClassmentService cs = new ClassmentService();
//        System.out.println(cs.getAllBySaison("20202021"));

//        System.out.println(bs.billet_disponible(m));
        System.out.println(bs.reserverBillet(b));
        
    }
    
}
