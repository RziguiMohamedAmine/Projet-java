/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
public class LoginuserController implements Initializable {

    @FXML
    private TextField textFieldemailUser;
    @FXML
    private TextField textFieldpassUser;
    @FXML
    private Button BtnLogin;
    private UserService UserService;
    private String nom;
    private String prenom;
    private String tel;
    private String image;
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
    private void OnLogin(ActionEvent event) {
        String email = textFieldemailUser.getText();
        String pass = textFieldpassUser.getText();
        try{
       if(UserService.login(email,pass)==true)
       {
            
                 
            fxml = FXMLLoader.load(getClass().getResource("inscription.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxml);
            stage.setScene(scene);
            stage.show();

            }
       else
               {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("login failed");
            alert.showAndWait(); 
               }
                
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
       }
       
        
    }

    @FXML
    private void onForgot(ActionEvent event) {
        
       
       
            
                 
        try {
            fxml = FXMLLoader.load(getClass().getResource("EmailV.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginuserController.class.getName()).log(Level.SEVERE, null, ex);
        }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxml);
            stage.setScene(scene);
            stage.show();

            
    }

    @FXML
    private void OnCreate(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("Create.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginuserController.class.getName()).log(Level.SEVERE, null, ex);
        }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxml);
            stage.setScene(scene);
            stage.show();
    }
    
}
