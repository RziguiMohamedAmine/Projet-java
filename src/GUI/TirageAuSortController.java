/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.MatchService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class TirageAuSortController implements Initializable {

    @FXML
    private TextField saisonTextField;

    MatchService matchservice;
    @FXML
    private DatePicker dateField;
    AjouterMatchController gestionMatchController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matchservice = new MatchService();
    }

    public void initializeMatchController(AjouterMatchController gestionMatchController) {
        this.gestionMatchController = gestionMatchController;
    }

    @FXML
    private void tirageAuSort(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("cette action n'est pas tu doit faire attention au sasie de saison");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            String saison = saisonTextField.getText();

            if (saison.matches("^[0-9][0-9][0-9][0-9]/[0-9][0-9][0-9][0-9]") && Integer.parseInt(saison.substring(0, 4)) + 1 == Integer.parseInt(saison.substring(5, 9))) {
                saison = saison.replaceAll("/", "");
                Timestamp millis = Timestamp.valueOf(dateField.getValue().atStartOfDay());
                System.out.println(millis);
                matchservice.tirage_au_sort(saison, millis);

                gestionMatchController.refreshTable();

            } else {
                alert.setContentText("format de saison est erroné");
                alert.show();
            }
        }
    }

}
