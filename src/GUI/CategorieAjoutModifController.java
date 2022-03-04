/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.categorie;
import entite.produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import service.ServiceCategorie;
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author sof
 */
public class CategorieAjoutModifController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextField idField;
    
    private ServiceCategorie serviceCategorie;
    private ObservableList<categorie> categorieList = FXCollections.observableArrayList();
    @FXML
    private Button actionButon;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceCategorie = new ServiceCategorie();
        categorieList.addAll(serviceCategorie.getAll());
        
        StringConverter<categorie> converter = new StringConverter<categorie>() {
            @Override
            public String toString(categorie object) {
                return object.getNom();
            }

            @Override
            public categorie fromString(String string) {
                return null;
            }
        };
        // TODO
    }    
       @FXML
    private void updateOrAdd(ActionEvent event) {
        String nom = nomField.getText();
            
            categorie c= new categorie(nom);
            
        if(actionButon.getText().equals("Mise à jour")){
            c.setId(Integer.parseInt(idField.getText()) );
            if(serviceCategorie.update(c)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert Mise à jour");
           alert.setContentText("categorie Mise à jour");
           alert.show();
        }
        }else{
            if(serviceCategorie.insert(c)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert ajout");
           alert.setContentText("categorie ajouter");
           alert.show();
        }

        
       }
        
        
    }
    
    public void setButton(){
        label.setText("Mise à jour categorie");
        actionButon.setText("Mise à jour");
    }
    public void setTextField(categorie c){
        System.out.println(c);
        idField.setText(c.getId()+"");
        nomField.setText(c.getNom());
        
    }
    
}
