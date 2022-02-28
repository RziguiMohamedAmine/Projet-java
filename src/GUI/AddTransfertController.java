/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Equipe;
import entite.Joueur;
import entite.Transfert;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import service.EquipeService;
import service.JoueurService;
import service.TransfertService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AddTransfertController implements Initializable {
      Joueur joueur=null;
      EquipeService es =new EquipeService();
      JoueurService js=new JoueurService();
      TransfertService ts = new TransfertService();
      ObservableList<Equipe> equipeList = FXCollections.observableArrayList();
      ObservableList<Joueur> joueurList = FXCollections.observableArrayList();
    
    
    
    @FXML
    private ComboBox<Equipe> boxancien;
    @FXML
    private ComboBox<Equipe> boxnouveau;
    @FXML
    private ComboBox<Joueur> boxjoueur;
    @FXML
    private TextField ancienfld;
    @FXML
    private TextField nouveaufld;
    @FXML
    private TextField joueurfld;
    @FXML
    private Button save;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         equipeList.addAll(es.getAll());
         joueurList.addAll(js.getAll());
        boxancien.setItems(equipeList);
        boxnouveau.setItems(equipeList);
        boxjoueur.setItems(joueurList);
        
        StringConverter<Equipe> converter = new StringConverter<Equipe>() {
            @Override
            public String toString(Equipe object) {
                return object.getNom();
            }

            @Override
            public Equipe fromString(String string) {
                return null;
            }
        };
        
        
        
         StringConverter<Joueur> converter1 = new StringConverter<Joueur>() {
            @Override
            public String toString(Joueur object) {
                return object.getNom();
            }

            @Override
            public Joueur fromString(String string) {
                return null;
            }
        };

        boxancien.setConverter(converter);
        boxnouveau.setConverter(converter);
        boxjoueur.setConverter(converter1);
    }    

    @FXML
    private void save(MouseEvent event) {
         Equipe ea=boxancien.getSelectionModel().getSelectedItem();
         ancienfld.setText(String.valueOf(ea));
         
         Equipe en=boxnouveau.getSelectionModel().getSelectedItem();
         nouveaufld.setText(String.valueOf(en));
         
         Joueur j=boxjoueur.getSelectionModel().getSelectedItem();
         joueurfld.setText(String.valueOf(j));
         
         String jou=joueurfld.getText();
         String ae=ancienfld.getText();
         String ne=nouveaufld.getText();
          if(jou.isEmpty()||ae.isEmpty()||ne.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all data");
            alert.showAndWait();
        }
        else
        {
            
            Transfert t=new Transfert(ea, en,j);
            ts.insert(t);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Transfert Ajouté");
            alert.showAndWait();
            
        }
        
    }
    
    
    
      private void clean() {
        ancienfld.setText(null);
        nouveaufld.setText(null);
        joueurfld.setText(null);
       
    }

    @FXML
    private void select(ActionEvent event) {
        String e=boxancien.getSelectionModel().getSelectedItem().getNom();
        ancienfld.setText(e);
    }

    @FXML
    private void select1(ActionEvent event) {
         String e=boxnouveau.getSelectionModel().getSelectedItem().getNom();
        nouveaufld.setText(e);
    }

    @FXML
    private void selectjou(ActionEvent event) {
         String e=boxjoueur.getSelectionModel().getSelectedItem().getNom();
        joueurfld.setText(e);
    }

    @FXML
    private void transfertliste(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("TransfertDetails.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(JoueurDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                          
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
            
    }
    
    
    
    
    
    
    
}
