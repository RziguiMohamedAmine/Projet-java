/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.*;
import utils.DataSource;
import entite.Personne;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Houssem Charef
 */
public class PersonneService {

    private Connection conn;
    private Statement ste;

    public PersonneService() {
        conn = DataSource.getInstance().getCnx();
    }

    public void insertPersonne(Personne p) {
        String req = "insert into personne (nom,prenom) values ('" + p.getNom() + "','" + p.getPrenom() + "')";

        try {

            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
