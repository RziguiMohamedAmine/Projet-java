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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
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
    private final static int rowsPerPage = 10;

    @FXML
    private GridPane gridPane;
    @FXML
    private Pagination pagination;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matchService = new MatchService();
        matchList = new ArrayList<>();
        matchList = matchService.getMatchFuture();
        pagination.setPageCount((matchList.size() / 10) + 1);
        pagination.setPageFactory(this::createPage);

    }

    private Node createPage(int pageIndex) {

        System.out.println(matchList.size());
        gridPane.getChildren().clear();
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, matchList.size());
        List<Match> subList = matchList.subList(fromIndex, toIndex);
        if (subList.size() > 0) {
            try {
                int rows = 2;

                AnchorPane anchorPane;
                AnchorPane anchorPane2;

                FXMLLoader fxmlloader1 = new FXMLLoader();
                FXMLLoader fxmlloader2 = new FXMLLoader();

                fxmlloader1.setLocation(getClass().getResource("RoundBare.fxml"));
                anchorPane = fxmlloader1.load();
                RoundBareController roundBareController = fxmlloader1.getController();
                roundBareController.setLabel("Round " + subList.get(0).getRound());
                gridPane.add(anchorPane, 0, 0);
                Match m = subList.get(0);
                fxmlloader2.setLocation(getClass().getResource("MatchItem.fxml"));
                anchorPane2 = fxmlloader2.load();
                MatchItemController matchItemController = fxmlloader2.getController();
                matchItemController.setMatch(m);

                matchItemController.setDate(m);

                gridPane.add(anchorPane2, 0, 1);

                for (int i = 1; i < subList.size(); i++) {
                    fxmlloader1 = new FXMLLoader();

                    if (subList.get(i).getRound() != subList.get(i - 1).getRound()) {

                        fxmlloader1.setLocation(getClass().getResource("RoundBare.fxml"));
                        anchorPane = fxmlloader1.load();
                        roundBareController = fxmlloader1.getController();
                        roundBareController.setLabel("Round " + subList.get(i).getRound());
                        gridPane.add(anchorPane, 0, rows);
                        rows++;
                    }
                    m = subList.get(i);
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

        return gridPane;

    }

    public void satDate(LocalDate date) {

        matchList.clear();
        gridPane.getChildren().clear();
        matchList.addAll(matchService.getMatchsByDate(date));
        pagination.setPageCount((matchList.size() / 10) + 1);
        pagination.setPageFactory(this::createPage);

    }

    public void satnomEquipe(String nomEquipe) {

        matchList.clear();
        gridPane.getChildren().clear();
        matchList.addAll(matchService.getmatchsByEquipe(nomEquipe));
        pagination.setPageCount((matchList.size() / 10) + 1);
        pagination.setPageFactory(this::createPage);

    }

    @FXML
    private void ChangePaginationContent(MouseEvent event) {
        int Pagecount = pagination.getCurrentPageIndex();
        System.out.println(Pagecount);

        createPage(Pagecount);
    }

}
