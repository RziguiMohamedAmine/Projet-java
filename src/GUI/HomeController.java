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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class HomeController implements Initializable {

    private UserService UserService;
    private Parent fxml;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button modcpt;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.UserService = new UserService();
    }    

    @FXML
    private void OnOut(ActionEvent event) {
         try {
            fxml = FXMLLoader.load(getClass().getResource("Main.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Loginuser1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxml);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void modecpt(ActionEvent event) throws IOException {
      
           fxml = FXMLLoader.load(getClass().getResource("UpdateUser.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxml);
            stage.setScene(scene);
            stage.show();
    }
    
}
