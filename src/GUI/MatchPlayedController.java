/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.Equipe;
import entite.Joueur;
import entite.JoueurMatch;
import entite.Match;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import service.EquipeService;
import service.JoueurMatchService;
import service.JoueurService;
import service.MatchService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class MatchPlayedController implements Initializable {
      
      EquipeService es=new EquipeService();
      JoueurService js=new JoueurService();
      MatchService ms=new MatchService();
      JoueurMatchService jms = new JoueurMatchService();
      
      ObservableList<JoueurMatch> MatchList = FXCollections.observableArrayList(); 
      
      Joueur joueur=new Joueur();
      Equipe e=new Equipe();
      JoueurMatch jm = null;
   
    @FXML
    private TableColumn<JoueurMatch,String> match;
    @FXML
    private TableColumn<JoueurMatch,String> date;
    @FXML
    private TableColumn<JoueurMatch,String> equipe;
    @FXML
    private TableColumn<JoueurMatch,String> but;
    @FXML
    private TableColumn<JoueurMatch,String> jaune;
    @FXML
    private TableColumn<JoueurMatch,String> rouge;
    @FXML
    private TableView<JoueurMatch> matchTable;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println(joueur);
    }    
    
     private void refreshTable() {
       
          MatchList.clear();
          MatchList.addAll(js.getScoreJoueur(joueur));
          matchTable.setItems(MatchList);
          
       
    }
     
      private void LoadDate()
    {
        refreshTable();
      // String s = jm.getMatch().getEquipe1().getNom()+"-"+jm.getMatch().getEquipe2().getNom();
      
        match.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getMatch().getEquipe1().getNom()+"-"+cellData.getValue().getMatch().getEquipe2().getNom()));
        date.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getMatch().getDate().toString()));
        equipe.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getJoueur().getEquipe().getNom()));
        but.setCellValueFactory(new PropertyValueFactory<>("nb_but"));
        jaune.setCellValueFactory(new PropertyValueFactory<>("jaune"));
        rouge.setCellValueFactory(new PropertyValueFactory<>("rouge"));
       
                
          matchTable.setItems(MatchList);
    }
      
      
      
      
      
       void setTextField(int id,String nome, String prenome, String postee, String nationalite, LocalDate datee, float taillee, float poidse, String imagee, Equipe equipee) 
     {
        joueur.setId(id);
        joueur.setNom(nome);
        joueur.setPrenom(prenome);
        joueur.setPoste(postee);
        joueur.setNationalite(nationalite);
        joueur.setDate_naiss(datee);
        joueur.setTaille(taillee);
        joueur.setPoids(poidse);
        joueur.setImage(imagee);
        joueur.setEquipe(equipee);
        LoadDate();
    }
 
    
}
