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
            Parent fxml = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
//            TreeItem<String> rootItem = new TreeItem<>("gestion match");
//            TreeItem<String> branchIntem1 = new TreeItem<>("Ajouter Match");
//            TreeItem<String> branchIntem2 = new TreeItem<>("suprimer match");
//            treeView.setRoot(rootItem);
//            rootItem.getChildren().addAll(branchIntem1, branchIntem2);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void Close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

//    @FXML
//    public void selctItem() {
//        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
//
//        if (item != null) {
//            String itemText = item.getValue();
//            itemText = itemText.replaceAll("\\s+", "");
//            itemText = itemText + ".fxml";
//
//            try {
//                Parent fxml = FXMLLoader.load(getClass().getResource(itemText));
//                contentArea.getChildren().removeAll();
//                contentArea.getChildren().setAll(fxml);
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
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

    @FXML
    private void redirectGestionBillet(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("BilletTable.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
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

}
