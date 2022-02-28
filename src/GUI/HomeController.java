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
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author sof
 */
public class HomeController implements Initializable {

    @FXML
    private StackPane contentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("ProduitGestion.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void gestion_joueurs(ActionEvent event) {
    }

    @FXML
    private void gestion_equipes(ActionEvent event) {
    }

    @FXML
    private void redirectGestionMatch(ActionEvent event) {
    }
    
    @FXML
    private void redirectGestionProduit(ActionEvent event) {
         try {
            Parent fxml = FXMLLoader.load(getClass().getResource("ProduitGestion.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    
}
