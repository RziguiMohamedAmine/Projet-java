/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.Match;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.MatchService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class AjouterMatchController implements Initializable {
    
    List<Match> listMatch;
    ObservableList<Match> matchList = FXCollections.observableArrayList();
    MatchService matchService = new MatchService();
    Match match;
    
    @FXML
    TableView<Match> tableViewMatchs;
    
    @FXML
    private TableColumn<Match, String> nom_equipe1;
    @FXML
    private TableColumn<Match, String> nom_equipe2;
    @FXML
    private TableColumn<Match, String> Arbitre1;
    @FXML
    private TableColumn<Match, String> Arbitre2;
    @FXML
    private TableColumn<Match, String> Arbitre3;
    @FXML
    private TableColumn<Match, String> Arbitre4;
    @FXML
    private TableColumn<Match, String> stade;
    @FXML
    private TableColumn<Match, Timestamp> date;
    @FXML
    private TableColumn<Match, String> saison;
    @FXML
    private TableColumn<Match, ImageView> logo_equipe1;
    @FXML
    private TableColumn<Match, String> action;
    @FXML
    private TextField rechercheTextFiled;
    @FXML
    private StackPane contentArea;
    @FXML
    private TableColumn<?, ?> logo_equipe2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matchService = new MatchService();
        LoadData();
        
    }
    
    public void refreshTable() {
        
        matchList.clear();
        matchList.addAll(matchService.getAll());
        FilteredList<Match> filterData = recherche(matchList);
        String a = rechercheTextFiled.getText();
        rechercheTextFiled.setText("");
        rechercheTextFiled.setText(a);
        tableViewMatchs.setItems(filterData);
        
    }
    
    private void LoadData() {
        
        refreshTable();
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        stade.setCellValueFactory(new PropertyValueFactory<>("stade"));
        saison.setCellValueFactory(new PropertyValueFactory<>("saison"));
        nom_equipe1.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getEquipe1().getNom()));
        nom_equipe2.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getEquipe2().getNom()));
        
        Arbitre1.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getArbiter1().getNom()));
        
        Arbitre2.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getArbiter2().getNom()));
        
        Arbitre3.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getArbiter3().getNom()));
        
        Arbitre4.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getArbiter4().getNom()));
//        logo_equipe1.setCellValueFactory(cellData
//                -> new ObservableValue<ImageView>() {
//            @Override
//            public void addListener(ChangeListener<? super ImageView> arg0) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void removeListener(ChangeListener<? super ImageView> arg0) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public ImageView getValue() {
//                return cellData.getValue().getEquipe1().getImageViewLogo();
//            }
//
//            @Override
//            public void addListener(InvalidationListener arg0) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void removeListener(InvalidationListener arg0) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//        ImageView equipe1Logo = new ImageView(new Image(this.getClass().getResourceAsStream("./images/logo_ftf.png")));
////        match = (Match) tableViewMatchs.getSelectionModel().getSelectedCells();
////        match.getEquipe1().setImageViewLogo(equipe1Logo);
//        FilteredList<Match> filterData = recherche(matchList);
//        tableViewMatchs.setItems(filterData);

        action.setCellFactory(createActionButton());
        
    }
    
    private FilteredList<Match> recherche(ObservableList matchList) {
        FilteredList<Match> filterData = new FilteredList<Match>(matchList, b -> true);
        rechercheTextFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(matchSearchModel -> {
                
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String serachKeeyword = newValue.toLowerCase();
                if (((Match) matchSearchModel).getEquipe1().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Match) matchSearchModel).getEquipe2().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Match) matchSearchModel).getStade().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Match) matchSearchModel).getEquipe2().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Match) matchSearchModel).getArbiter1().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Match) matchSearchModel).getArbiter2().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Match) matchSearchModel).getArbiter3().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Match) matchSearchModel).getArbiter4().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Match) matchSearchModel).getSaison().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if (((Match) matchSearchModel).getDate().toString().toLowerCase().contains(serachKeeyword)) {
                    return true;
                }
                
                return false;
            });
            
        });
        
        return filterData;
    }
    
    private Callback<TableColumn<Match, String>, TableCell<Match, String>> createActionButton() {
        Callback<TableColumn<Match, String>, TableCell<Match, String>> cellFoctory = (TableColumn<Match, String> param) -> {
            // make cell containing buttons
            final TableCell<Match, String> cell = new TableCell<Match, String>() {
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
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirmation de supprision ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                            alert.showAndWait();
                            
                            if (alert.getResult() == ButtonType.YES) {
                                match = tableViewMatchs.getSelectionModel().getSelectedItem();
                                matchService.delete(match);
                                refreshTable();
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                
                                match = tableViewMatchs.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchAjoutModify.fxml"));
                                
                                Parent root;
                                root = loader.load();
                                
                                MatchAjoutModifyController matchAjoutModifyController = loader.getController();
                                matchAjoutModifyController.initializeTextField(match);
                                matchAjoutModifyController.setButton("Update");
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
        return cellFoctory;
    }
    
    @FXML
    public void RedirectToAjoutMatch() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchAjoutModify.fxml"));
            
            Parent root = loader.load();
            MatchAjoutModifyController matchAjoutModifyController = loader.getController();
            
            matchAjoutModifyController.setButton("Ajouter");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterMatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void tirageAuSort(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TirageAuSort.fxml"));
            
            Parent root = loader.load();
            TirageAuSortController tirageAuSortController = loader.getController();
            tirageAuSortController.initializeMatchController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterMatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
