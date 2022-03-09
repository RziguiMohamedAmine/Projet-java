/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class MainController implements Initializable {

    @FXML
    private VBox vbox;
    private Parent fxml;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(getClass().getResource("loginuser1.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                
            }
        });
    }    

    @FXML
    private void open_signin(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) ->{
           
            try{
                
                fxml = FXMLLoader.load(getClass().getResource("loginuser1.fxml"));
                vbox.getChildren().removeAll();
                   

                vbox.getChildren().setAll(fxml);

            }catch(IOException ex){

                System.out.println(ex.getMessage());
                
            }
        });
    }

    @FXML
    private void open_signup(ActionEvent event) {
          TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
         
        t.setOnFinished((e) ->{
           try{
                fxml = FXMLLoader.load(getClass().getResource("Create.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        });
    }

    @FXML
    private void close(MouseEvent event) {
         Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
         stage.close();
    }
    
}
