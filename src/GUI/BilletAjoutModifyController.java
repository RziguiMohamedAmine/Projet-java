/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Billet;
import entite.Match;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import service.BilletService;
import service.MatchService;
import service.ServicePaymentStripe;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class BilletAjoutModifyController implements Initializable {

    @FXML
    private ComboBox<Match> matchComboBox;
    @FXML
    private TextField prix;
    @FXML
    private Label title;
    @FXML
    private Button submitButton;
    @FXML
    private TextField idBillet;
    @FXML
    private TextField bloc;

    private MatchService matchService;
    private BilletService billetService;
    private ObservableList<Match> matchList = FXCollections.observableArrayList();
    private BilletTableController billetTableController;
    private BilletAjoutModifyController billetAjoutModifyController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matchService = new MatchService();
        billetService = new BilletService();
        matchList.addAll(matchService.getAll());
        matchComboBox.setItems(matchList);

        StringConverter<Match> converter = new StringConverter<Match>() {
            @Override
            public String toString(Match object) {
                return object.getEquipe1().getNom() + " - " + object.getEquipe2().getNom();
            }

            @Override
            public Match fromString(String string) {
                return null;
            }
        };

        matchComboBox.setConverter(converter);

    }

    @FXML
    private void updateOrDelete(ActionEvent event) {
        if (submitButton.getText().equals("Ajouter")) {
            ajout();
        } else {
            update();
        }
        billetTableController.refreshTable();
    }

    public void initializeTextField(Billet billet) {
        matchComboBox.getSelectionModel().select(billet.getMatch());
        prix.setText(billet.getPrix() + "");
        idBillet.setText(billet.getId() + "");
        bloc.setText(billet.getBloc());

    }

    public void update() {
        try {
            Match match = matchComboBox.getSelectionModel().getSelectedItem();
            String bloc = this.bloc.getText();
            float prix = Float.parseFloat(this.prix.getText());
            int id = Integer.parseInt(idBillet.getText());

            if (match == null || bloc.equals("") || prix == 0) {
                showAlert("champ text", "erreur", "vous devez remplir tous les champs", AlertType.ERROR);
            } else {
                Billet billet = new Billet(id, match, bloc, prix);

                billetService.update(billet);
                showAlert("Succès", "Mise à jour Succès", "votre mise à jour effectuer avec Succès", AlertType.INFORMATION);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getClass());
            showAlert("champ text format", "prix", "prix doit etre un nombre", AlertType.ERROR);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            showAlert("erreur", "erreur", "erreur inconnu", AlertType.ERROR);

        }
    }

    private void showAlert(String title, String header, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public void ajout() {
        try {
            Match match = matchComboBox.getSelectionModel().getSelectedItem();
            String bloc = this.bloc.getText();
            float prix = Float.parseFloat(this.prix.getText());
            if (match == null || bloc.equals("") || prix == 0) {
                showAlert("champ text", "erreur", "vous devez remplir tous les champs", AlertType.ERROR);
            } else {
                Billet billet = new Billet(match, bloc, prix);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
                Parent root;
                root = loader.load();
                PaymentController p = loader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.showAndWait();
                if (p.getReturn()) {
                    billetService.reserverBillet(billet);
                    showAlert("succes", "ajout Succès", "votre ajout effectuer avec Succès", AlertType.INFORMATION);
                } else {
                    showAlert("error", "ajout error", "error reservation", AlertType.INFORMATION);

                }

            }
        } catch (NumberFormatException e) {
            System.out.println(e.getClass());
            showAlert("champ text format", "prix", "prix doit etre un nombre", AlertType.ERROR);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            showAlert("erreur", "erreur", "erreur inconnu", AlertType.ERROR);

        }

    }

    public void initializeBilletController(BilletTableController billetTableController) {
        this.billetTableController = billetTableController;
    }

    public void setButton(String type) {

        submitButton.setText(type);
        title.setText(type + " Billet");

    }

}
