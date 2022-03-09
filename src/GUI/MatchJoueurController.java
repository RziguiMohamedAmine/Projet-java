/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Equipe;
import entite.Joueur;
import entite.JoueurMatch;
import entite.Match;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import service.EquipeService;
import service.JoueurMatchService;
import service.JoueurService;
import service.MatchService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class MatchJoueurController implements Initializable {

      JoueurMatch mj=null;
      EquipeService es=new EquipeService();
      MatchService ms=new MatchService();
      JoueurMatchService mjs = new JoueurMatchService();
      JoueurService js=new JoueurService();
      ObservableList<Joueur> JoueurList = FXCollections.observableArrayList();
      ObservableList<Match> MatchList = FXCollections.observableArrayList();
      
      
 
    @FXML
    private TextField stadefld;
    @FXML
    private ComboBox<Joueur> joueur;
    @FXML
    private ComboBox<Match> Match;
    @FXML
    private TextField nomjoueur;
    @FXML
    private TextField match;
    @FXML
    private TextField jaune;
    @FXML
    private TextField rouge;
    @FXML
    private TextField buts;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         JoueurList.addAll(js.getAll());
        joueur.setItems(JoueurList);
        StringConverter<Joueur> converter = new StringConverter<Joueur>() {
            @Override
            public String toString(Joueur object) {
                return object.getNom();
            }

            @Override
            public Joueur fromString(String string) {
                return null;
            }
        };

        joueur.setConverter(converter);
        
        
          MatchList.addAll(ms.getAll());
        Match.setItems(MatchList);
        StringConverter<Match> converter1 = new StringConverter<Match>() {
            @Override
            public String toString(Match object) {
                return object.getEquipe1().getNom()+"-"+object.getEquipe2().getNom();
            }

            @Override
            public Match fromString(String string) {
                return null;
            }
        };

        Match.setConverter(converter1);
        
        
        
    }    

   
   

   

    @FXML
    private void save(ActionEvent event) {
        
        Joueur j=joueur.getSelectionModel().getSelectedItem();
        nomjoueur.setText(String.valueOf(j));
        
        Match m=Match.getSelectionModel().getSelectedItem();
        match.setText(String.valueOf(m));
        
        int jaunee= Integer.parseInt(jaune.getText());
        int rougee= Integer.parseInt(rouge.getText());
        int butt= Integer.parseInt(buts.getText());
        
        
        
            if(String.valueOf(jaune).isEmpty())
            {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setHeaderText(null);
                 alert.setContentText("Please fill all data");
                 alert.showAndWait();
            }
            else{
           JoueurMatch jmm =new JoueurMatch(j,m,jaunee,rougee,butt);
            mjs.insert(jmm);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Joueur Ajouté");
            alert.showAndWait();
            
            }
        
        
        
        
        
        
    }
    

    private void clean() {
        nomjoueur.setText(null);
        match.setText(null);
        jaune.setText(null);
        rouge.setText(null);
        buts.setText(null);
    }

    @FXML
    private void selectjoueur(ActionEvent event) {
        String j=joueur.getSelectionModel().getSelectedItem().getNom();
        nomjoueur.setText(j);
    }

    @FXML
    private void selectmatch(ActionEvent event) {
         String m=Match.getSelectionModel().getSelectedItem().getEquipe1().getNom()+"-"+Match.getSelectionModel().getSelectedItem().getEquipe2().getNom();
         match.setText(m);
    }

  
    
}
