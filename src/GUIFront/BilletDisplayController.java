/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entite.Billet;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
        billetList = billetService.getAll();
        MatchItemController matchItemController;
        try {

//            gridPane.add(anchorPane, 0, 1);
            AnchorPane anchorPane;
            AnchorPane anchorPane2;

            FXMLLoader fxmlloader2 = new FXMLLoader();

            for (int i = 0; i < billetList.size(); i++) {

                b = billetList.get(i);
                fxmlloader2 = new FXMLLoader();

                fxmlloader2.setLocation(getClass().getResource("MatchItem.fxml"));
                anchorPane2 = fxmlloader2.load();
                matchItemController = fxmlloader2.getController();
                matchItemController.setBillet(b);

                matchItemController = fxmlloader2.getController();
                matchItemController.setDataBillet(b);

                gridPane.add(anchorPane2, 0, i);

            }
        } catch (IOException ex) {
            Logger.getLogger(MatchsDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
