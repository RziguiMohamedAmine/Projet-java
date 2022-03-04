/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

       
import entite.*;
import service.*;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JSpinner;
import java.sql.Date;
import java.time.LocalDate;

/**
 * FXML Controller class
 *
 * @author R I B
 */
public class ArbitreController implements Initializable {

    @FXML
    private TableView<Arbitres> ArbitreTable;
    @FXML
    private TableColumn<Arbitres, String> idField;
    @FXML
    private TableColumn<Arbitres, String> nomField;
    @FXML
    private TableColumn<Arbitres, String> prenomField;
    @FXML
    private TableColumn<Arbitres, String> idroleField;
    @FXML
    private TableColumn<Arbitres, String> imageField;
    @FXML
    private TableColumn<Arbitres, String> ageField;
    @FXML
    private TableColumn<Arbitres, String> AvisField;
    @FXML
    private TableColumn<Arbitres, String> actionField;
    @FXML
    private Button buttonarb;
    @FXML
    private Button buttonStat;
    private ArbitresServices ArbitresServices;
    ObservableList<Arbitres> ARBList = FXCollections.observableArrayList();
    Arbitres Arbitres;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArbitresServices = new ArbitresServices();
        LoadData();
    }    
private void refreshTable() {

        ARBList.clear();
        ARBList.addAll(ArbitresServices.read());
        System.out.println(ARBList);
        ArbitreTable.setItems(ARBList);
    }
    @FXML
    private void ajoutARB(ActionEvent event) {
        
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutModifARB.fxml"));

            Parent root = loader.load();
                        System.out.println("test");

//         AjoutModifRoleController Home = loader.getController();
//
//            Home.setButton("ajouter");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
           
        }
    }

    @FXML
    private void statiq(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            stage.setTitle("Statistiques");
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
    }

    private void LoadData() {
refreshTable();
       
        idField.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomField.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomField.setCellValueFactory(new PropertyValueFactory<>("prenom"));    
            idroleField.setCellValueFactory(new PropertyValueFactory<>("id_role"));    
        imageField.setCellValueFactory(new PropertyValueFactory<>("image"));    
        ageField.setCellValueFactory(new PropertyValueFactory<>("age"));
      AvisField.setCellValueFactory(new PropertyValueFactory<>("email"));
Callback<TableColumn<Arbitres, String>, TableCell<Arbitres, String>> cellFoctory = (TableColumn<Arbitres, String> param) -> {
        //     make cell containing buttons
            final TableCell<Arbitres, String> cell = new TableCell<Arbitres, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
              //      that cell created only on non-empty rows
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

                           Arbitres  = ArbitreTable.getSelectionModel().getSelectedItem();
                            ArbitresServices.delete(Arbitres);
                            refreshTable();
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                Arbitres= ArbitreTable.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutModifARB.fxml"));

                                Parent root;
                                root = loader.load();

                                AjoutModifARBController AjoutModifARBController = loader.getController();
                                AjoutModifARBController.setTextField(Arbitres);
                                AjoutModifARBController.setButton();
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AjoutModifARBController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                     
                        );
                        
                      
                  

                        logoIcon.setOnMouseClicked((MouseEvent event) -> {

                             
                            try {

                                Arbitres= ArbitreTable.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("stats.fxml"));

                                Parent root;
                                root = loader.load();

                                StatsController StatsController = loader.getController();
                               //StatsController.setTextField(Arbitres);
                                //StatsController.setButton();
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                     
                        );
                     

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
        actionField.setCellFactory(cellFoctory);

    }
    
}
