/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entite.Classment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
        baseController.setHboxVesibility(false);
        classmentService = new ClassmentService();
        saisonList.addAll(classmentService.getAvalableSaison());
        saisonComboBox.setItems(saisonList);
        saisonComboBox.getSelectionModel().selectFirst();
        String saison = saisonComboBox.getSelectionModel().getSelectedItem();
        classmentList.addAll(classmentService.getAllBySaison(saison));
        loadData();
    }

    private AnchorPane createLabel(String text) throws IOException {
        FXMLLoader fxmlloader1 = new FXMLLoader();
        fxmlloader1.setLocation(getClass().getResource("ClassmentLabel.fxml"));

        AnchorPane label = fxmlloader1.load();

        ClassmentLabelController classmentLabelController = fxmlloader1.getController();
        classmentLabelController.setLabel(text);
        return label;
    }

    private AnchorPane createLabel(String text, String path) throws IOException {
        FXMLLoader fxmlloader1 = new FXMLLoader();
        fxmlloader1.setLocation(getClass().getResource("ClassmentLabel.fxml"));
        AnchorPane label = fxmlloader1.load();
        ClassmentLabelController classmentLabelController = fxmlloader1.getController();
        classmentLabelController.setLabel(text);
        classmentLabelController.setStyleLabel();
        String absolutePath = new File("").getAbsolutePath();
        Image image = new Image(new FileInputStream(absolutePath + "/src/GUI/images/" + path));
        classmentLabelController.setImage(image);
        return label;
    }

    private void loadData() {
        AnchorPane anchorPane;
        try {

            for (int i = 0; i < classmentList.size(); i++) {

                clasment = classmentList.get(i);

                anchorPane = createLabel(clasment.getRang() + "");
                ClassmentgridPane.add(anchorPane, 0, i + 2);

                anchorPane = createLabel(clasment.getEquipe().getNom() + "", clasment.getEquipe().getLogo());
                ClassmentgridPane.add(anchorPane, 1, i + 2);

                anchorPane = createLabel(clasment.getNb_point() + "");
                ClassmentgridPane.add(anchorPane, 2, i + 2);

                anchorPane = createLabel(clasment.getNb_loose() + "");
                ClassmentgridPane.add(anchorPane, 3, i + 2);

                anchorPane = createLabel(clasment.getNb_draw() + "");
                ClassmentgridPane.add(anchorPane, 4, i + 2);

                anchorPane = createLabel(clasment.getNb_win() + "");
                ClassmentgridPane.add(anchorPane, 5, i + 2);
                anchorPane = createLabel(clasment.getNb_totale_but() + "");
                ClassmentgridPane.add(anchorPane, 6, i + 2);
                anchorPane = createLabel(clasment.getNb_totale_but_recu() + "");
                ClassmentgridPane.add(anchorPane, 7, i + 2);

                anchorPane = createLabel(clasment.getSaison() + "");
                ClassmentgridPane.add(anchorPane, 8, i + 2);

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void loadSaisonData(ActionEvent event) {
        classmentList.clear();
        String saison = saisonComboBox.getSelectionModel().getSelectedItem();
        classmentList.addAll(classmentService.getAllBySaison(saison));
        loadData();
    }
}
