/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Equipe;
import entite.Joueur;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import service.EquipeService;
import service.JoueurService;
/**
 * FXML Controller class
 *
 * @author moham
 */
public class MapController implements Initializable {

     EquipeService es=new EquipeService();
      JoueurService js=new JoueurService();
      ObservableList<Joueur> joueurList = FXCollections.observableArrayList(); 
      Joueur joueur=null;
      Equipe e=new Equipe();
      
    @FXML
    private WebView map;

    private WebEngine engine;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        engine=map.getEngine();
       
    }    

    public void loadmap()
    {
        File f = new File("C:\\Users\\moham\\Desktop\\rades.html");
        File f2 = new File("C:\\Users\\moham\\Desktop\\menzah.html");
        File f3 = new File("C:\\Users\\moham\\Desktop\\sousse.html");
        
         switch (e.getStade()) {
             case "rades":
                 engine.load(f.toURI().toString());
                 break;
             case "menzah":
                 engine.load(f2.toURI().toString());
                 break;
             case "sousse":
                 engine.load(f3.toURI().toString());
                 break;
             default:
                 break;
         }
    }
    
    
     
    void setTextField(String stade) 
     {
      
        e.setStade(stade);
        loadmap();

    }

    
}
