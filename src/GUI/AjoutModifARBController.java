/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.StringConverter;
import javax.swing.JFileChooser;

import entite.*;
import service.*;
/**
 * FXML Controller class
 *
 * @author R I B
 */
public class AjoutModifARBController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField imageField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField avisField;
    @FXML
    private ComboBox<Roles> idroleField;
    @FXML
    private Button buttonAjoutModif;
    private ObservableList<Roles> ARBList = FXCollections.observableArrayList();
 private  ArbitresServices ArbitresServices;
 private   RolesServices RolesServices;
    @FXML
    private Label label;
    @FXML
    private Button imageButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   ArbitresServices = new ArbitresServices();
        RolesServices = new RolesServices();
        ARBList.addAll(RolesServices.read());
        idroleField.setItems(ARBList);
        StringConverter<Roles> converter = new StringConverter<Roles>() {
            @Override
            public String toString(Roles object) {
return object.getRole();
            }

            @Override
            public Roles fromString(String string) {
return null;
            }

        };

        idroleField.setConverter(converter);
    
    }    
void setButton()
    {
    label.setText("mise a jour arbitres");
    buttonAjoutModif.setText("mise a jour");
   
    }
    @FXML
    private void Ajouterarb(ActionEvent event) {
       // int idv= Integer.parseInt(idField.getText());
 String nomv = (nomField.getText());
  String prenomv = (prenomField.getText());
Roles  r = idroleField.getSelectionModel().getSelectedItem();
 String imgv = (imageField.getText());
  int agev = Integer.parseInt(ageField.getText());
   String emailv = (avisField.getText());
   
Arbitres a= new Arbitres(nomv, prenomv, r, imgv,agev, emailv);
          if (buttonAjoutModif.getText().equals("mise a jour")){
              System.out.println("hhh");
          if (ArbitresServices.update(a)){
              Alert alert= new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert mse à jour");
           alert.setContentText("arbitre  mise à jour");
           alert.show();
        }}else{
            if(ArbitresServices.insert(a)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert ajout");
           alert.setContentText("arbitre ajouter");
           alert.show();
            }}

        
    }
    public void setTextField(Arbitres a){
    System.out.println(a);
    idField.setText(a.getId()+"");
     nomField.setText(a.getNom());
         prenomField.setText(a.getPrenom());
            idroleField.getSelectionModel().select(a.getId_role());
            imageField.setText(a.getImage());
            ageField.setText(a.getAge()+"");
          avisField.setText(a.getEmail()+"");
   
    }

    @FXML
    private void get_image(ActionEvent event) {
          JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        imageField.setText(filename);
        Image imagee;
          try {
              imagee = new Image(new FileInputStream(filename));
//               imagev.setImage(imagee);
          } catch (FileNotFoundException ex) {
              System.out.println(ex.getMessage());
          }
    }
}
