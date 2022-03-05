/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entite.Classment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import service.ClassmentService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class ClassmentDisplayController implements Initializable {

    ClassmentService classmentService;
    ObservableList<Classment> classmentList = FXCollections.observableArrayList();
    ObservableList<String> saisonList = FXCollections.observableArrayList();
    Classment clasment;

    @FXML
    private GridPane ClassmentgridPane;
    @FXML
    private ComboBox<String> saisonComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BaseController baseController = BaseController.baseController;
        baseController.setSideBarVesibility(false);
        classmentService = new ClassmentService();
        saisonList.addAll(classmentService.getAvalableSaison());
        saisonComboBox.setItems(saisonList);
        saisonComboBox.getSelectionModel().selectFirst();
        String saison = saisonComboBox.getSelectionModel().getSelectedItem();
        classmentList.addAll(classmentService.getAllBySaison(saison));
        loadDate();
    }

    private Label createLabel(String text) throws IOException {
        FXMLLoader fxmlloader1 = new FXMLLoader();
        fxmlloader1.setLocation(getClass().getResource("ClassmentLabel.fxml"));

        Label label = fxmlloader1.load();

        ClassmentLabelController classmentLabelController = fxmlloader1.getController();
        classmentLabelController.setLabel(text);
        return label;
    }

    private void loadDate() {
        Label label;
        try {

            for (int i = 0; i < classmentList.size(); i++) {

                clasment = classmentList.get(i);

                label = createLabel(clasment.getRang() + "");
                ClassmentgridPane.add(label, 0, i + 2);

                label = createLabel(clasment.getEquipe().getNom() + "");
                ClassmentgridPane.add(label, 1, i + 2);

                label = createLabel(clasment.getNb_point() + "");
                ClassmentgridPane.add(label, 2, i + 2);

                label = createLabel(clasment.getNb_loose() + "");
                ClassmentgridPane.add(label, 3, i + 2);

                label = createLabel(clasment.getNb_draw() + "");
                ClassmentgridPane.add(label, 4, i + 2);

                label = createLabel(clasment.getNb_win() + "");
                ClassmentgridPane.add(label, 5, i + 2);
                label = createLabel(clasment.getNb_totale_but() + "");
                ClassmentgridPane.add(label, 6, i + 2);
                label = createLabel(clasment.getNb_totale_but_recu() + "");
                ClassmentgridPane.add(label, 7, i + 2);

                label = createLabel(clasment.getSaison() + "");
                ClassmentgridPane.add(label, 8, i + 2);

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
