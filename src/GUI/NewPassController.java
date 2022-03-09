/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class NewPassController implements Initializable {

    /**
     * Initializes the controller class.
     */
    String email;
    @FXML
    private TextField tfCode;
    @FXML
    private TextField tfNewPass;
    @FXML
     private Parent fxml;
    private Stage stage;
    private Scene scene;
    private UserService UserService;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    public void inisialiseEmail(String email){
    this.UserService = new UserService();    
    this.email= email;
    }

    @FXML
    private void OnNewPass(ActionEvent event) {
        String code = tfCode.getText();
        String Newpass = tfNewPass.getText();
        try{
       if(UserService.changePassForgetPassword(email,code,Newpass)==true)
       {
            
                 
            fxml = FXMLLoader.load(getClass().getResource("home.fxml"));
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
