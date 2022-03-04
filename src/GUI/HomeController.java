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
 * @author R I B
 */
public class HomeController implements Initializable {

    @FXML
    private StackPane contentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         try {
//            Parent fxml = FXMLLoader.load(getClass().getResource("Role.fxml"));
//            contentArea.getChildren().removeAll();
//            contentArea.getChildren().setAll(fxml);
//
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        // TODO
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
    private void gestion_role(ActionEvent event) {
         try {
            Parent fxml = FXMLLoader.load(getClass().getResource("Role.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectGestionMatch(ActionEvent event) {
    }

    @FXML
    private void redirectGestionProduit(ActionEvent event) {
    }

    void setButton(String ajouter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void gestion_arbitre(ActionEvent event) {
         try {
            Parent fxml = FXMLLoader.load(getClass().getResource("Arbitre.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
