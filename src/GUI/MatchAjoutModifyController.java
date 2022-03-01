/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Arbitres;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import service.EquipeService;
import entite.Equipe;
import entite.Match;
import java.sql.Timestamp;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import service.ArbitresServices;
import service.MatchService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class MatchAjoutModifyController implements Initializable {

    ObservableList<Equipe> equipeList = FXCollections.observableArrayList();
    ObservableList<Arbitres> ArbitreList = FXCollections.observableArrayList();
    AjouterMatchController gestionMatchController;
    MatchService matchService;

    @FXML
    private ComboBox<Equipe> equipe1ComboBox;
    @FXML
    private ComboBox<Equipe> equipe2ComboBox;
    @FXML
    private ComboBox<Arbitres> arbitre1ComboBox;
    @FXML
    private ComboBox<Arbitres> arbitre2ComboBox;
    @FXML
    private ComboBox<Arbitres> arbitre3ComboBox;
    @FXML
    private ComboBox<Arbitres> arbitre4ComboBox;
    @FXML
    private TextField roundField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField bnSpectateurFiled;
    @FXML
    private TextField nbBut1Field;
    @FXML
    private TextField nbBut2Field;
    @FXML
    private TextField stadeField;
    @FXML
    private TextField saisonField;
    @FXML
    private Label title;
    @FXML
    private Button submitButton;
    @FXML
    private TextField idMatch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        EquipeService equipeService = new EquipeService();
        ArbitresServices arbitreService = new ArbitresServices();
        matchService = new MatchService();
        equipeList.addAll(equipeService.getAll());

        ArbitreList.addAll(arbitreService.read());
        initializeComboBox(equipeList, equipe1ComboBox);
        initializeComboBox(equipeList, equipe2ComboBox);
        initializeArbitreComboBox(ArbitreList, arbitre1ComboBox);
        initializeArbitreComboBox(ArbitreList, arbitre2ComboBox);
        initializeArbitreComboBox(ArbitreList, arbitre3ComboBox);
        initializeArbitreComboBox(ArbitreList, arbitre4ComboBox);
        arbitre1ComboBox.getSelectionModel();

    }

    public void initializeComboBox(ObservableList<Equipe> data, ComboBox<Equipe> combobox) {
        combobox.setItems(data);
        StringConverter<Equipe> converter = new StringConverter<Equipe>() {
            @Override
            public String toString(Equipe object) {
                return object.getNom();
            }

            @Override
            public Equipe fromString(String string) {
                return null;
            }
        };

        combobox.setConverter(converter);
    }

    public void setButton(String type) {

        submitButton.setText(type);
        title.setText(type + " match");

    }

    public void initializeArbitreComboBox(ObservableList<Arbitres> data, ComboBox<Arbitres> combobox) {
        combobox.setItems(data);
        StringConverter<Arbitres> converter = new StringConverter<Arbitres>() {
            @Override
            public String toString(Arbitres object) {
                return object.getNom();
            }

            @Override
            public Arbitres fromString(String string) {
                return null;
            }
        };

        combobox.setConverter(converter);
    }

    @FXML
    private void updateOrDelete(ActionEvent event) {
        if (submitButton.getText().equals("Ajouter")) {
            ajout();
        } else {
            update();
        }
        gestionMatchController.refreshTable();
    }

    private boolean selectSameArbitre(Arbitres a1, Arbitres a2, Arbitres a3, Arbitres a4) {

        return a1.equals(a2) || a1.equals(a2) || a1.equals(a4) || a2.equals(a3) || a2.equals(a4) || a3.equals(a4);

    }

    private boolean selectSameEquipe(Equipe e1, Equipe e2) {

        return e1.equals(e2);

    }

    public void update() {
        try {
            int idMatch = Integer.parseInt(this.idMatch.getText());
            Equipe Equipe1 = equipe1ComboBox.getSelectionModel().getSelectedItem();
            Equipe Equipe2 = equipe2ComboBox.getSelectionModel().getSelectedItem();
            Arbitres arbitre1 = arbitre1ComboBox.getSelectionModel().getSelectedItem();
            Arbitres arbitre2 = arbitre2ComboBox.getSelectionModel().getSelectedItem();
            Arbitres arbitre3 = arbitre3ComboBox.getSelectionModel().getSelectedItem();
            Arbitres arbitre4 = arbitre4ComboBox.getSelectionModel().getSelectedItem();
            int round = Integer.parseInt(roundField.getText());

            LocalDate date = dateField.getValue();

            int nbSpectateur = Integer.parseInt(bnSpectateurFiled.getText());
            int nbBut1 = Integer.parseInt(nbBut1Field.getText());
            int nbBut2 = Integer.parseInt(nbBut2Field.getText());

            String stade = stadeField.getText();
            String saison = saisonField.getText();

            if (Equipe1 == null || Equipe2 == null || arbitre1 == null || arbitre2 == null || arbitre3 == null || arbitre4 == null || date == null || stade.equals("") || saison.equals("")) {
                showAlert("champ text", "erreur", "vous devez remplir tous les champs", Alert.AlertType.ERROR);

            } else {

                if (selectSameArbitre(arbitre1, arbitre2, arbitre3, arbitre4)) {
                    showAlert("erreur", "erreur", "arbitre doit etre different", Alert.AlertType.ERROR);
                } else if (selectSameEquipe(Equipe1, Equipe2)) {
                    showAlert("erreur", "erreur", "Equipe doit etre different", Alert.AlertType.ERROR);

                } else {
                    Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
                    Match match = new Match(idMatch, nbBut1, nbBut2, stade, nbSpectateur, Equipe1, Equipe2, arbitre1, arbitre2, arbitre3, arbitre4, timestamp, saison, round);
                    matchService.update(match);
                    showAlert("succes", "mise à jour Succès", "votre mise à joure effectuer avec Succès", Alert.AlertType.INFORMATION);

                }

            }
        } catch (NumberFormatException e) {
            System.out.println(e.getClass());
            showAlert("champ text format", "prix et nombre but equipe1 et nombre but equipe 2 et round", "ces champs doit etre un nombre", Alert.AlertType.ERROR);

        } catch (Exception e) {
            System.out.println(e);
            showAlert("erreur", "erreur", "erreur inconnu", Alert.AlertType.ERROR);

        }
    }

    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public void ajout() {
        try {
            Equipe Equipe1 = equipe1ComboBox.getSelectionModel().getSelectedItem();
            Equipe Equipe2 = equipe2ComboBox.getSelectionModel().getSelectedItem();
            Arbitres arbitre1 = arbitre1ComboBox.getSelectionModel().getSelectedItem();
            Arbitres arbitre2 = arbitre2ComboBox.getSelectionModel().getSelectedItem();
            Arbitres arbitre3 = arbitre3ComboBox.getSelectionModel().getSelectedItem();
            Arbitres arbitre4 = arbitre4ComboBox.getSelectionModel().getSelectedItem();
            int round = Integer.parseInt(roundField.getText());
            LocalDate date = dateField.getValue();
            int nbSpectateur = Integer.parseInt(bnSpectateurFiled.getText());
            int nbBut1 = Integer.parseInt(nbBut1Field.getText());
            int nbBut2 = Integer.parseInt(nbBut2Field.getText());

            String stade = stadeField.getText();
            String saison = saisonField.getText();
            if (Equipe1 == null || Equipe2 == null || arbitre1 == null || arbitre2 == null || arbitre3 == null || arbitre4 == null || date == null || stade.equals("") || saison.equals("")) {
                showAlert("champ text", "erreur", "vous devez remplir tous les champs", Alert.AlertType.ERROR);

            } else {
                if (selectSameArbitre(arbitre1, arbitre2, arbitre3, arbitre4)) {
                    showAlert("erreur", "erreur", "arbitre doit etre different", Alert.AlertType.ERROR);
                } else if (selectSameEquipe(Equipe1, Equipe2)) {
                    showAlert("erreur", "erreur", "Equipe doit etre different", Alert.AlertType.ERROR);

                } else {
                    Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
                    Match match = new Match(nbBut1, nbBut2, stade, nbSpectateur, Equipe1, Equipe2, arbitre1, arbitre2, arbitre3, arbitre4, timestamp, saison, round);
                    matchService.insert(match);
                    showAlert("succes", "ajout Succès", "votre ajout effectuer avec Succès", Alert.AlertType.ERROR);

                }

            }

        } catch (NumberFormatException e) {
            System.out.println(e.getClass());
            showAlert("champ text format", "prix et nombre but equipe1 et nombre but equipe 2 et round", "ces champs doit etre un nombre", Alert.AlertType.ERROR);

        } catch (Exception e) {
            System.out.println(e);
            showAlert("erreur", "erreur", "erreur inconnu", Alert.AlertType.ERROR);

        }
    }

    public void initializeMatchController(AjouterMatchController gestionMatchController) {
        this.gestionMatchController = gestionMatchController;
    }

    public void initializeTextField(Match match) {
        idMatch.setText(String.valueOf(match.getId()));
        equipe1ComboBox.getSelectionModel().select(match.getEquipe1());
        equipe2ComboBox.getSelectionModel().select(match.getEquipe2());
        arbitre1ComboBox.getSelectionModel().select(match.getArbiter1());
        arbitre2ComboBox.getSelectionModel().select(match.getArbiter2());
        arbitre3ComboBox.getSelectionModel().select(match.getArbiter3());
        arbitre4ComboBox.getSelectionModel().select(match.getArbiter4());
        roundField.setText(String.valueOf(match.getRound()));

        dateField.setValue(match.getDate().toLocalDateTime().toLocalDate());
        bnSpectateurFiled.setText(String.valueOf(match.getNb_spectateur()));
        nbBut1Field.setText(String.valueOf(match.getNb_but1()));
        nbBut2Field.setText(String.valueOf(match.getNb_but2()));

        stadeField.setText(match.getStade());
        saisonField.setText(match.getSaison());
    }

}
