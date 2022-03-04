/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.Avis;
import entite.produit;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.ServiceAvis;
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author sof
 */
public class ProduitGestionController implements Initializable {
ObservableList<produit> produitList = FXCollections.observableArrayList();
    produit Produit;
    ServiceAvis serviceAvis;
    private ServiceProduit serviceProduit;

    @FXML
    private TableView<produit> produitTableView;
    @FXML
    private TableColumn<produit, String> nom;
    @FXML
    private TableColumn<produit, String> image;
    @FXML
    private TableColumn<produit, String> description;
    @FXML
    private TableColumn<produit, String> prix;
    @FXML
    private TableColumn<produit, String> categorie;
    @FXML
    private TableColumn<produit, String> action;
    @FXML
    private TableColumn<produit, String> stock;
       
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceProduit = new ServiceProduit();
        serviceAvis = new ServiceAvis();
        LoadData();
    }    
    public void refreshTable() {

        produitList.clear();
        produitList.addAll(serviceProduit.getAll());
        System.out.println(produitList);
        produitTableView.setItems(produitList);
        

    }
    
   private void LoadData() {

        refreshTable();
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        categorie.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getCat().getNom()));

        Callback<TableColumn<produit, String>, TableCell<produit, String>> cellFoctory = (TableColumn<produit, String> param) -> {
            // make cell containing buttons
            final TableCell<produit, String> cell = new TableCell<produit, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView logoIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        logoIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            Produit = produitTableView.getSelectionModel().getSelectedItem();
                            serviceProduit.delete(Produit);
                            refreshTable();
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                Produit = produitTableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitAjoutModif.fxml"));

                                Parent root;
                                root = loader.load();

                                ProduitAjoutModifController produitAjoutModifController = loader.getController();
                                produitAjoutModifController.setTextField(Produit);
                                produitAjoutModifController.setButton();
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitGestionController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        logoIcon.setOnMouseClicked((MouseEvent event) -> {
                            produit p = (produit)produitTableView.getSelectionModel().getSelectedItem();
                              List<Float> list=  serviceAvis.getCountAverageAvisByProduit(p.getId());
                              
                              Alert alert= new Alert(AlertType.INFORMATION);
                              alert.setContentText("moyenne de avis = "+ list.get(1) + "\n nombre des personnes qui on donnée des avis: "+ Math.round(list.get(0)));
                              alert.show();
//                            
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon, logoIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(logoIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        action.setCellFactory(cellFoctory);
    }

    @FXML
    private void redirectProduitAjoutEdit(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitAjoutModif.fxml"));

            Parent root = loader.load();
            ProduitAjoutModifController produitAjoutModifController=(ProduitAjoutModifController)loader.getController();
            produitAjoutModifController.initializeController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
    
}
