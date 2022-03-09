/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Classment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ClassmentService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class ClassmentGestionController implements Initializable {

    ClassmentService classmentService;
    ObservableList<Classment> classmentList = FXCollections.observableArrayList();
    ObservableList<String> saisonList = FXCollections.observableArrayList();

    @FXML
    private TableView<Classment> classmentTableView;
    @FXML
    private TableColumn<Classment, String> nomEquipe;
    @FXML
    private TableColumn<Classment, String> nombrePoint;
    @FXML
    private TableColumn<Classment, String> totaleBut;
    @FXML
    private TableColumn<Classment, String> TotaleButRecu;
    @FXML
    private TableColumn<Classment, String> nbGange;
    @FXML
    private TableColumn<Classment, String> nbNull;
    @FXML
    private TableColumn<Classment, String> nbPredre;
    @FXML
    private TableColumn<Classment, String> rang;
    @FXML
    private TableColumn<Classment, String> saison;
    @FXML
    private ComboBox<String> saisonComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        classmentService = new ClassmentService();
        saisonList.addAll(classmentService.getAvalableSaison());
        saisonComboBox.setItems(saisonList);
        saisonComboBox.getSelectionModel().selectFirst();
        LoadData();

    }

    public void refreshTable() {
        String saison = saisonComboBox.getSelectionModel().getSelectedItem();
        classmentList.clear();
        classmentList.addAll(classmentService.getAllBySaison(saison));
        classmentTableView.setItems(classmentList);

    }

    private void LoadData() {

        refreshTable();
//        @FXML
//    private TableColumn<Classment, String> nomEquipe;
//    @FXML
//    private TableColumn<Classment, String> nombrePoint;
//    @FXML
//    private TableColumn<Classment, String> totaleBut;
//    @FXML
//    private TableColumn<Classment, String> TotaleButRecu;
//    @FXML
//    private TableColumn<Classment, String> nbGange;
//    @FXML
//    private TableColumn<Classment, String> nbNull;
//    @FXML
//    private TableColumn<Classment, String> nbPredre;
        nomEquipe.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getEquipe().getNom()));
        nombrePoint.setCellValueFactory(new PropertyValueFactory<>("nb_point"));
        totaleBut.setCellValueFactory(new PropertyValueFactory<>("nb_totale_but"));

        TotaleButRecu.setCellValueFactory(new PropertyValueFactory<>("nb_totale_but_recu"));
        nbGange.setCellValueFactory(new PropertyValueFactory<>("nb_win"));
        nbNull.setCellValueFactory(new PropertyValueFactory<>("nb_draw"));
        nbPredre.setCellValueFactory(new PropertyValueFactory<>("nb_loose"));
        rang.setCellValueFactory(new PropertyValueFactory<>("rang"));
        saison.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getSaison().substring(0, 4) + "/" + cellData.getValue().getSaison().substring(4, 8)));

    }

    @FXML
    private void changeSaison(ActionEvent event) {
        refreshTable();
    }
}
