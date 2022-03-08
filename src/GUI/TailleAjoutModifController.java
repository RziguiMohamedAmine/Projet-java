/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entite.produit;
import entite.taille;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import service.ServiceProduit;
import service.ServiceTaille;

/**
 * FXML Controller class
 *
 * @author sof
 */
public class TailleAjoutModifController implements Initializable {
    private ServiceTaille serviceTaille;
    private ObservableList<taille> tailleList = FXCollections.observableArrayList();
    private ObservableList<produit> produitList = FXCollections.observableArrayList();

    private ServiceProduit serviceProduit;
    @FXML
    private Label label;
    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private Button actionButon;
    
    
    @FXML
    private ComboBox<produit> produitBox;
    @FXML
    private TextField stockField;
    private TailleGestionController tailleGestionController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceTaille = new ServiceTaille();
        serviceProduit = new ServiceProduit();
        tailleList.addAll(serviceTaille.getAll());
        produitList.addAll(serviceProduit.getAll());

        produitBox.setItems(produitList);
        
        
        
        StringConverter<produit> converter = new StringConverter<produit>() {
            @Override
            public String toString(produit object) {
                return object.getNom();
            }

            @Override
            public produit fromString(String string) {
                return null;
            }
         };
        produitBox.setConverter(converter);
       }

    @FXML
    private void updateOrAdd(ActionEvent event) {
        
        String nom = nomField.getText();
            
            taille t= new taille(nom);
            t.setStock(Integer.parseInt(stockField.getText()));
            t.setProd(produitBox.getSelectionModel().getSelectedItem());
            
        if(actionButon.getText().equals("Mise à jour")){
            t.setId(Integer.parseInt(idField.getText()) );
           
            if(serviceTaille.update(t)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert Mise à jour");
           alert.setContentText("taille Mise à jour");
           alert.show();
        }
        }else{
            if(serviceTaille.insert(t)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert ajout");
           alert.setContentText("taille ajouter");
           alert.show();
        }

        
       }
        tailleGestionController.refreshTable();
    }
    public void setButton(){
        label.setText("Mise à jour taille");
        actionButon.setText("Mise à jour");
    }
    public void setTextField(taille t){
        idField.setText(t.getId()+"");
        nomField.setText(t.getNom());
        stockField.setText(t.getStock()+"");
        produitBox.getSelectionModel().select(t.getProd());
        
    }
    public void setTailleController(TailleGestionController t) {
        this.tailleGestionController = t;
    }
    }
    

