/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.Joueur;
import entite.Transfert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.EquipeService;
import service.JoueurService;
import service.TransfertService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class TransfertDetailsController implements Initializable {
    
      EquipeService es=new EquipeService();
      JoueurService js=new JoueurService();
      TransfertService ts=new TransfertService();
      ObservableList<Transfert> transfertList = FXCollections.observableArrayList(); 
      Transfert t=null;
    
    @FXML
    private TableView<Transfert> joueurTable;
    @FXML
    private TableColumn<Transfert,String> nom;
    @FXML
    private TableColumn<Transfert,String> prenom;
    @FXML
    private TableColumn<Transfert,String> ancien;
    @FXML
    private TableColumn<Transfert,String> nouveau;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       LoadDate();
    }   
    
    
      private void refreshTable() {
       
          transfertList.clear();
          transfertList.addAll(ts.getAll());
          joueurTable.setItems(transfertList);
       
    }
      
     private void LoadDate()
    {
        
       
         refreshTable();
         
       
        nom.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getJoueur().getNom()));
        prenom.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getJoueur().getPrenom()));
        ancien.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getAncieneq().getNom()));
        nouveau.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getNouveaueq().getNom()));
        
        
        
         joueurTable.setItems(transfertList);
       
    }
      
      
      
      
      
      
      
      
    
}
