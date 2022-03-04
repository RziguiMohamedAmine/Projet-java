/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import service.RolesServices;
import entite.Roles;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author R I B
 */
public class AjoutModifRoleController implements Initializable {

   
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button Ajoutid;
       private ObservableList<Roles> roleList = FXCollections.observableArrayList();
 private RolesServices RolesServices;
    @FXML
    private Label label;
    @FXML
    private TextField idField;
    @FXML
    private TextField rolrField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        RolesServices = new RolesServices();
        
     
    }
    
      

    public void setButton() {
   label.setText("mise à jour Produit");
        Ajoutid.setText("Mise à jour");
        }

    @FXML
    private void AjoutR(ActionEvent event) {
    //    int id = Integer.parseInt(idField.getText());
        String role = rolrField.getText();
        String description = descriptionField.getText();
           Roles r= new Roles(role, description);
           System.out.println(r);
        if (Ajoutid.getText().equals("Mise à jour")){
           if(RolesServices.update(r)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert mise à jour");
           alert.setContentText("role mise à jour");
           alert.show();
        }}else{
            if(RolesServices.insert(r)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert ajout");
           alert.setContentText("role ajouter");
           alert.show();
        }
         

        }
    }
     public void setTextField(Roles r){
        System.out.println(r);
        
       // idField.setText(r.getId()+"");
        rolrField.setText(r.getRole());
        
        descriptionField.setText(r.getDescription());
       
    }

    
}