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
    
    
public class EmailVController implements Initializable {
     private Parent fxml;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField tfForgetmail;
    @FXML
    private Button btnSend;
    private UserService UserService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.UserService = new UserService();
    }    

    @FXML
    private void OnSend(ActionEvent event) {
        String email = tfForgetmail.getText();
        
        try{
       if(UserService.sendMailForgetPass(email)==true)
       {
            
                 
           FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPass.fxml"));
           
           fxml= loader.load();
           
           NewPassController newPassController = loader.getController();
           newPassController.inisialiseEmail(email);
           
            
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
    
}
