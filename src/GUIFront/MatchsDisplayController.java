/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entite.Match;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import service.MatchService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class MatchsDisplayController implements Initializable {

    List<Match> matchList;
    MatchService matchService;

    @FXML
    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matchService = new MatchService();
        matchList = new ArrayList<>();
        matchList = matchService.getAll();
        loaddata();
    }

    public void loaddata() {
        if (matchList.size() > 0) {
            try {
                int rows = 2;

                AnchorPane anchorPane;
                AnchorPane anchorPane2;

                FXMLLoader fxmlloader1 = new FXMLLoader();
                FXMLLoader fxmlloader2 = new FXMLLoader();

                fxmlloader1.setLocation(getClass().getResource("RoundBare.fxml"));
                anchorPane = fxmlloader1.load();
                RoundBareController roundBareController = fxmlloader1.getController();
                roundBareController.setLabel("Round " + matchList.get(0).getRound());
                gridPane.add(anchorPane, 0, 0);
                Match m = matchList.get(0);
                fxmlloader2.setLocation(getClass().getResource("MatchItem.fxml"));
                anchorPane2 = fxmlloader2.load();
                MatchItemController matchItemController = fxmlloader2.getController();
                matchItemController.setMatch(m);

                matchItemController.setDate(m);

                gridPane.add(anchorPane2, 0, 1);

                for (int i = 1; i < matchList.size(); i++) {
                    fxmlloader1 = new FXMLLoader();

                    if (matchList.get(i).getRound() != matchList.get(i - 1).getRound()) {

                        fxmlloader1.setLocation(getClass().getResource("RoundBare.fxml"));
                        anchorPane = fxmlloader1.load();
//                    anchorPane.setStyle("-fx-margin:10px");
                        roundBareController = fxmlloader1.getController();
                        roundBareController.setLabel("Round " + matchList.get(i).getRound());
                        gridPane.add(anchorPane, 0, rows);
                        rows++;
                    }
                    m = matchList.get(i);
                    fxmlloader2 = new FXMLLoader();

                    fxmlloader2.setLocation(getClass().getResource("MatchItem.fxml"));
                    anchorPane2 = fxmlloader2.load();
                    matchItemController = fxmlloader2.getController();
                    matchItemController.setMatch(m);

                    matchItemController = fxmlloader2.getController();
                    matchItemController.setDate(m);

                    gridPane.add(anchorPane2, 0, rows);
                    rows++;
                }
            } catch (IOException ex) {
                Logger.getLogger(MatchsDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Label label = new Label("Pas de Donnée");
            label.setStyle("-fx-font-size:20px");

            gridPane.add(label, 1, 10);
        }
    }

    public void satDate(LocalDate date) {

        matchList.clear();
        gridPane.getChildren().clear();
        matchList.addAll(matchService.getMatchsByDate(date));
        loaddata();
    }

}
