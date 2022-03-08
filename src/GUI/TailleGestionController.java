
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.taille;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.ServiceTaille;

/**
 * FXML Controller class
 *
 * @author sof
 */
public class TailleGestionController implements Initializable {
    
    

   TailleGestionController tailleGestionController;
    @FXML
    private TableView<taille> tailleTableView;
    @FXML
    private TableColumn<taille, String> nom;
    @FXML
    private TableColumn<taille, String> action;
    
    private ServiceTaille servicetaille;
   
    ObservableList<taille> tailleList = FXCollections.observableArrayList();
    taille taille;
    @FXML
    private TableColumn<taille, String> Produit;
    @FXML
    private TableColumn<taille, String> stock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tailleGestionController=this;
        servicetaille = new ServiceTaille();
        LoadData();
    }    
    
    public void refreshTable() {

        tailleList.clear();
        tailleList.addAll(servicetaille.getAll());
        System.out.println(tailleList);
        tailleTableView.setItems(tailleList);

    }

    @FXML
    private void redirecttailleAjoutEdit(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TailleAjoutModif.fxml"));

            Parent root = loader.load();
            TailleAjoutModifController tailleAjoutModifController = loader.getController();
            tailleAjoutModifController.setTailleController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
    
    private void LoadData() {

        refreshTable();
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Produit.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getProd().getNom()));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        Callback<TableColumn<taille, String>, TableCell<taille, String>> cellFoctory = (TableColumn<taille, String> param) -> {
            // make cell containing buttons
            final TableCell<taille, String> cell = new TableCell<taille, String>() {
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

                            taille = tailleTableView.getSelectionModel().getSelectedItem();
                            servicetaille.delete(taille);
                            refreshTable();
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                taille = tailleTableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("TailleAjoutModif.fxml"));

                                Parent root;
                                root = loader.load();

                                TailleAjoutModifController TailleAjoutModifController = loader.getController();
                                TailleAjoutModifController.setTextField(taille);
                                TailleAjoutModifController.setButton();
                                TailleAjoutModifController.setTailleController(tailleGestionController);
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(TailleGestionController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        logoIcon.setOnMouseClicked((MouseEvent event) -> {

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
    
}
