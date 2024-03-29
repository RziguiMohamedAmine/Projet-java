/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.Billet;
import entite.Match;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.BilletService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class BilletTableController implements Initializable {

    private BilletService billetService;
    private ObservableList<Billet> billetList = FXCollections.observableArrayList();
    private Billet billet;
    private BilletTableController billetTableController;
    @FXML
    private TableColumn<Billet, String> idBillet;
    @FXML
    private TableColumn<Billet, String> bloc;
    @FXML
    private TableColumn<Billet, String> prix;
    @FXML
    private TableColumn<Billet, String> equipe1;
    @FXML
    private TableColumn<Billet, String> equipe2;
    @FXML
    private TableColumn<Billet, String> stade;
    @FXML
    private TableColumn<Billet, String> date;
    @FXML
    private TableView<Billet> billetTableView;
    @FXML
    private TableColumn<Billet, String> action;
    @FXML
    private TextField rechercheTextFiled;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billetTableController = this;
        billetService = new BilletService();
        LoadData();
    }

    public void refreshTable() {

        billetList.clear();
        billetList.addAll(billetService.getAll());
        FilteredList<Billet> filterData = recherche(billetList);
        String a = rechercheTextFiled.getText();
        rechercheTextFiled.setText("");
        rechercheTextFiled.setText(a);
        billetTableView.setItems(filterData);

    }

    private void LoadData() {

        refreshTable();
        idBillet.setCellValueFactory(cellData
                -> new SimpleStringProperty("B" + cellData.getValue().getId() * 3600 + ""));
        bloc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        stade.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getMatch().getEquipe1().getSatde()));
        equipe1.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getMatch().getEquipe1().getNom()));
        equipe2.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getMatch().getEquipe2().getNom()));

        date.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getMatch().getDate().toString()));

        Callback<TableColumn<Billet, String>, TableCell<Billet, String>> cellFoctory = (TableColumn<Billet, String> param) -> {
            // make cell containing buttons
            final TableCell<Billet, String> cell = new TableCell<Billet, String>() {
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
                            billet = billetTableView.getSelectionModel().getSelectedItem();

                            Alert alert = new Alert(AlertType.CONFIRMATION, "Confirmation de supprision ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                            alert.showAndWait();

                            if (alert.getResult() == ButtonType.YES) {
                                billet = billetTableView.getSelectionModel().getSelectedItem();
                                billetService.delete(billet);
                                refreshTable();
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                billet = billetTableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("BilletAjoutModify.fxml"));

                                Parent root;
                                root = loader.load();

                                BilletAjoutModifyController billetAjoutModifyController = loader.getController();
                                billetAjoutModifyController.initializeTextField(billet);
                                billetAjoutModifyController.setButton("Update");
                                billetAjoutModifyController.initializeBilletController(billetTableController);
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AjouterMatchController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        logoIcon.setOnMouseClicked((MouseEvent event) -> {

//                             equipe = equipeTable.getSelectionModel().getSelectedItem();
//                             Image image;
//                            try {
//                                image = new Image(new FileInputStream(equipe.getLogo()));
//                                logoview.setImage(image); 
//                            } catch (FileNotFoundException ex) {
//                                Logger.getLogger(EquipeDetailsController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
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
//        tableViewMatchs.setItems(matchList);

    }

    private FilteredList<Billet> recherche(ObservableList billetList) {
        FilteredList<Billet> filterData = new FilteredList<Billet>(billetList, b -> true);
        rechercheTextFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(billetSearchModel -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String serachKeeyword = newValue.toLowerCase();
                if (((Billet) billetSearchModel).getBloc().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Billet) billetSearchModel).getMatch().getEquipe2().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Billet) billetSearchModel).getMatch().getEquipe2().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Billet) billetSearchModel).getMatch().getStade().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Billet) billetSearchModel).getMatch().getDate().toString().toLowerCase().contains(serachKeeyword)) {
                    return true;
                }

                return false;
            });

        });

        return filterData;
    }

    @FXML

    public void RedirectToAjoutMatch() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BilletAjoutModify.fxml"));

            Parent root = loader.load();
            BilletAjoutModifyController billetAjoutModifyController = loader.getController();

            billetAjoutModifyController.setButton("Ajouter");
            billetAjoutModifyController.initializeBilletController(this);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AjouterMatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
