/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entite.Billet;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import service.BilletService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class BilletDisplayController implements Initializable {

    @FXML
    private GridPane gridPane;
    BilletService billetService;
    List<Billet> billetList;
    Billet b;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billetService = new BilletService();
        billetList = billetService.getBilletByUser(71);
        int row = 0;
        try {
            AnchorPane anchorPane1;
            AnchorPane anchorPane2;
            MatchItemController matchItemController;

            FXMLLoader fxmlloader1 = new FXMLLoader();

            fxmlloader1.setLocation(getClass().getResource("RoundBare.fxml"));
            anchorPane1 = fxmlloader1.load();
            RoundBareController roundBareController = fxmlloader1.getController();
            roundBareController.setLabel("Billet Active");
            gridPane.add(anchorPane1, 0, row);
            row++;
            if (billetList.size() > 0) {
                for (int i = 0; i < billetList.size(); i++) {

                    b = billetList.get(i);
                    System.out.println(b);

                    if (b.getMatch().getDate().after(Timestamp.valueOf(LocalDateTime.now()))) {
                        FXMLLoader fxmlloader2 = new FXMLLoader();

                        fxmlloader2.setLocation(getClass().getResource("MatchItem.fxml"));
                        anchorPane2 = fxmlloader2.load();
                        matchItemController = fxmlloader2.getController();
                        matchItemController.setBillet(b);

                        matchItemController = fxmlloader2.getController();
                        matchItemController.setDataBillet(b);
                        matchItemController.setMatch(b.getMatch());

                        gridPane.add(anchorPane2, 0, row);
                        row++;
                    }

                }
            }
            System.out.println(row + "" + billetList.size());

            if (row == 1) {
                Label label = new Label("Pas de Donnée");
                label.setStyle("-fx-font-size:20px");

                gridPane.add(label, 1, row);
                row++;
            }

            fxmlloader1 = new FXMLLoader();
            fxmlloader1.setLocation(getClass().getResource("RoundBare.fxml"));
            anchorPane1 = fxmlloader1.load();
            roundBareController = fxmlloader1.getController();
            roundBareController.setLabel("Billet Non Active");
            gridPane.add(anchorPane1, 0, row);
            row++;

            System.out.println(row + " " + billetList.size());

            if (row >= billetList.size() + 2) {
                Label label = new Label("Pas de Donnée");
                label.setStyle("-fx-font-size:20px");

                gridPane.add(label, 1, row);
            } else {

                for (int i = 0; i < billetList.size(); i++) {

                    b = billetList.get(i);
                    if (b.getMatch().getDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
                        FXMLLoader fxmlloader2 = new FXMLLoader();

                        fxmlloader2.setLocation(getClass().getResource("MatchItem.fxml"));
                        anchorPane2 = fxmlloader2.load();
                        matchItemController = fxmlloader2.getController();
                        matchItemController.setBillet(b);

                        matchItemController = fxmlloader2.getController();
                        matchItemController.setDataBillet(b);
                        matchItemController.setMatch(b.getMatch());

                        gridPane.add(anchorPane2, 0, row);
                        row++;
                    }

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(MatchsDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
