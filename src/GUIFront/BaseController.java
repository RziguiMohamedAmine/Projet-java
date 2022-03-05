/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import GUI.AjouterMatchController;
import GUI.BilletAjoutModifyController;
import entite.Match;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class BaseController implements Initializable {

    Listener<Match> l;
    Match match;

    public static BaseController baseController;

    @FXML
    private Pane scroll;
    @FXML
    private VBox sidebar;
    @FXML
    private Button reserverBilletButton;
    @FXML
    private ImageView imageViewEquipe1;
    @FXML
    private Label nomEquipe1;
    @FXML
    private Label Resultat;
    @FXML
    private Label Date;
    @FXML
    private ImageView imageViewEquipe2;
    @FXML
    private Label nomEquipe2;
    @FXML
    private Label entreneurEquipe1;
    @FXML
    private Label entreneurEquipe2;
    @FXML
    private Label arbitre;
    @FXML
    private Label stade;
    @FXML
    private DatePicker DatePiker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        baseController = this;
        Parent root;
        DatePiker.setValue(LocalDate.now());
        try {
            root = FXMLLoader.load(getClass().getResource("MatchsDisplay.fxml"));

            scroll.getChildren().removeAll();
            scroll.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setMatchSideBare(Match match) {
        this.match = match;
        try {
            Date.setText(match.getDate().toString());
            nomEquipe1.setText(match.getEquipe1().getNom());
            nomEquipe2.setText(match.getEquipe2().getNom());
            if (match.getNb_but1() == -1) {
                Resultat.setText(" - ");

            } else {
                Resultat.setText(match.getNb_but1() + " - " + match.getNb_but2());

            }
            entreneurEquipe1.setText(match.getEquipe1().getNom_entreneur());
            entreneurEquipe2.setText(match.getEquipe2().getNom_entreneur());
            stade.setText(match.getEquipe1().getSatde());
            arbitre.setText(match.getArbiter1().getNom());
            String absolutePath = new File("").getAbsolutePath();
            Image image1;

            image1 = new Image(new FileInputStream(absolutePath + "\\src\\GUI\\images\\" + match.getEquipe1().getLogo()));

            imageViewEquipe1.setImage(image1);
            Image image2 = new Image(new FileInputStream(absolutePath + "\\src\\GUI\\images\\" + match.getEquipe2().getLogo()));
            imageViewEquipe2.setImage(image2);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reserverBilet(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/BilletAjoutModify.fxml"));

            Parent root = loader.load();
            BilletAjoutModifyController billetAjoutModifyController = loader.getController();

            billetAjoutModifyController.setButton("Ajouter");

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AjouterMatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadGestionBillet() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("BilletDisplay.fxml"));

            scroll.getChildren().removeAll();
            scroll.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setButtonVisible() {
        reserverBilletButton.setVisible(true);
    }

    public void LoadClassment() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ClassmentDisplay.fxml"));

            scroll.getChildren().removeAll();
            scroll.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDatePikervalue() {
        DatePiker.getValue();
    }

    public void setSideBarVesibility(boolean visible) {
        this.sidebar.setVisible(visible);
    }

    @FXML
    private void loadmatchbyDate(ActionEvent event) {
        baseController = this;
        Parent root;
        DatePiker.setValue(LocalDate.now());
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("MatchsDisplay.fxml"));

            root = fxml.load();
            MatchsDisplayController matchsDisplayController = fxml.getController();

            scroll.getChildren().removeAll();
            scroll.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
