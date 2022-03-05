/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class CreateController implements Initializable {

    @FXML
    private TextField tfNomUser;
    @FXML
    private TextField tfPrenomUser;
    @FXML
    private TextField tfEmailUser;
    @FXML
    private TextField tfPassUser;
    @FXML
    private TextField tfTelUser;
    @FXML
    private Button btnCreer;
    private UserService UserService;
    @FXML
    private Parent fxml;
    private Stage stage;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.UserService = new UserService();
    }    

    @FXML
    private void ONCreer(ActionEvent event) {
         int tel;
        String nom =tfNomUser.getText();
        String prenom = tfPrenomUser.getText();
        String email = tfEmailUser.getText();
        String pass = tfPassUser.getText();
        
        try{
            tel = Integer.parseInt(tfTelUser.getText());
        }catch(NumberFormatException ex){
            System.out.println("le numero de tel doit être un entier ");
         return;
        }
        
        
        
         if(nom.isEmpty()||email.isEmpty()||pass.isEmpty()||prenom.isEmpty())
        {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs obligatoires ");
            alert.showAndWait(); 
        }
         else{
      
        User u7 =new User(nom, prenom, email, pass, tel);
        UserService.insert(u7);
        try {
            fxml = FXMLLoader.load(getClass().getResource("inscription.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxml);
            stage.setScene(scene);
            stage.show();
         
         }
        
       
        
    }
    
}
