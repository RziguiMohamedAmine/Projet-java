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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class DetailsController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfpass;
    @FXML
    private TextField tfTel;
    
    @FXML
    private TextField tfImage;
    @FXML
    private TextField tfRole;
    @FXML
    private Button BtnExit;
    
    @FXML
    private AnchorPane SceneExit;
     Stage stag;
     private Stage stage;
     private Scene scene;
     private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setTfNom(String value) {
        this.tfNom.setText(value);
    }

    public void setTfPrenom(String value) {
        this.tfPrenom.setText(value);
    }

    public void setTfEmail(String value) {
        this.tfEmail.setText(value);
    }

    public void setTfpass(String value) {
        this.tfpass.setText(value);
    }

    public void setTfTel(String value) {
        this.tfTel.setText(value);
    }

    

    public void setTfImage(String value) {
        this.tfImage.setText(value);
    }
    public void setTfRole(String value){
        this.tfRole.setText(value);
    }

    @FXML
    private void OnExit(ActionEvent event) {
       // try {
            //        stage =(Stage) SceneExit.getScene().getWindow();
//        System.out.println(" exit avec succès");
//        stage.close();
         try {
            root =FXMLLoader.load(getClass().getResource("inscription.fxml"));
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    
}
