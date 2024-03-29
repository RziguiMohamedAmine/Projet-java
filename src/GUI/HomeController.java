/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUIFront.BaseController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
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
            Parent fxml = FXMLLoader.load(getClass().getResource("../GUIFront/Base.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
//           

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void Close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void home(ActionEvent event) {

    }

    @FXML
    private void redirectGestionMatch(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AjouterMatch.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

//    @FXML
//    private void redirectGestionBillet(ActionEvent event) {
//        try {
//            FXMLLoader fxml = new FXMLLoader(getClass().getResource("BilletTable.fxml"));
//            Parent root = fxml.load();
////            BaseController baseController = fxml.getController();
////            baseController.setButtonVisible();
////            baseController.LoadGestionBillet();
//            contentArea.getChildren().removeAll();
//            contentArea.getChildren().setAll(root);
//        } catch (IOException ex) {
//
//            System.out.println(ex.getMessage());
//        }
//    }
    @FXML
    private void redirectGestionBillet(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("../GUIFront/Base.fxml"));
            Parent root = fxml.load();
            BaseController baseController = fxml.getController();
            baseController.setButtonVisible();
            baseController.LoadGestionBillet();
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(root);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gestion_equipes(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("EquipeDetailss.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gestion_joueurs(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("JoueurDetails.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToClasmment(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("ClassmentGestion.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectToClasmmentClient(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("../GUIFront/Base.fxml"));
            Parent root = fxml.load();
            BaseController baseController = fxml.getController();
//            baseController.setButtonVisible();
            baseController.LoadClassment();
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(root);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

}
