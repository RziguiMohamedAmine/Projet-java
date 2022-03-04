/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entite.Match;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class MatchDetailsController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Match match) {
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
    }

}
