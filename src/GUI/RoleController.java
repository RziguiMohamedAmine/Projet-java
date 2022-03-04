/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import entite.Roles;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.RolesServices;

/**
 * FXML Controller class
 *
 * @author R I B
 */
public class RoleController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private TableView<Roles> roleTable;
    @FXML
    private TableColumn<Roles, String> id;
    @FXML
    private TableColumn<Roles, String> role;
    @FXML
    private TableColumn<Roles, String> descrp;
    private RolesServices RolesServices;
  
    ObservableList<Roles> roleList = FXCollections.observableArrayList();
   Roles Roles;
    @FXML
    private TableColumn<Roles, String> action;

    /**
     * Initializes the controller class.
     */
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
// TODO

 RolesServices = new RolesServices();
        LoadData();
    }    

    @FXML
    private void ajouter_role(ActionEvent event) {
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutModifRole.fxml"));

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
    public void refreshTable() {

        roleList.clear();
        roleList.addAll(RolesServices.read());
        System.out.println(roleList);
        roleTable.setItems(roleList);

    }

    private void LoadData() {
  refreshTable();
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        descrp.setCellValueFactory(new PropertyValueFactory<>("description"));
        Callback<TableColumn<Roles, String>, TableCell<Roles, String>> cellFoctory = (TableColumn<Roles, String> param) -> {
        //     make cell containing buttons
            final TableCell<Roles, String> cell = new TableCell<Roles, String>() {
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

                            Roles = roleTable.getSelectionModel().getSelectedItem();
                            RolesServices.delete(Roles);
                            refreshTable();
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                Roles= roleTable.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutModifRole.fxml"));

                                Parent root;
                                root = loader.load();

                                AjoutModifRoleController AjoutModifRoleController = loader.getController();
                                AjoutModifRoleController.setTextField(Roles);
                                AjoutModifRoleController.setButton();
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AjoutModifRoleController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

//                        logoIcon.setOnMouseClicked((MouseEvent event) -> {
//
//                             equipe = equipeTable.getSelectionModel().getSelectedItem();
//                             Image image;
//                            try {
//                                image = new Image(new FileInputStream(equipe.getLogo()));
//                                logoview.setImage(image); 
//                            } catch (FileNotFoundException ex) {
//                                Logger.getLogger(EquipeDetailsController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        });

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
}
