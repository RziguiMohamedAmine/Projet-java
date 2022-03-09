/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import GUI.MatchAjoutModifyController;
import entite.Billet;
import entite.Match;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class MatchItemController {

    private Match match;
    private Billet billet;

    @FXML
    private Label date;
    @FXML
    private ImageView logoEquipe1;
    @FXML
    private Label equipe1Label;
    @FXML
    private Label equipe2Label;
    @FXML
    private Label butEquipe2Label;
    @FXML
    private Label butEquipe1Label;
    @FXML
    private ImageView logoEquipe2;
    @FXML
    private AnchorPane MatchItem;

    /**
     * Initializes the controller class.
     */
    Listener<Match> lm;

    public void setDate(Match m) {
        try {
            date.setText(m.getDate() + "");

            equipe1Label.setText(m.getEquipe1().getNom());
            equipe2Label.setText(m.getEquipe2().getNom());
            if (m.getNb_but1() == -1) {
                butEquipe1Label.setText("-");

            } else {
                butEquipe1Label.setText(m.getNb_but1() + "");

            }
            if (m.getNb_but2() == -1) {
                butEquipe2Label.setText("-");

            } else {
                butEquipe2Label.setText(m.getNb_but2() + "");

            }

            String absulutePath = new File("").getAbsolutePath();
            Image image1 = new Image(new FileInputStream(absulutePath + "\\src\\GUI\\images\\" + m.getEquipe1().getLogo()));

            logoEquipe1.setImage(image1);
            Image image2 = new Image(new FileInputStream(absulutePath + "\\src\\GUI\\images\\" + m.getEquipe2().getLogo()));

            logoEquipe2.setImage(image2);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MatchItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openMatchDetails(MouseEvent event) {

        BaseController baseController = BaseController.baseController;
        baseController.setMatchSideBare(match);
    }

    public void setMatch(Match m) {
        this.match = m;
    }

    void setBillet(Billet b) {
        billet = b;
    }

    void setDataBillet(Billet b) {
        try {
            date.setText(b.getMatch().getDate() + "");

            equipe1Label.setText(b.getMatch().getEquipe1().getNom());
            equipe2Label.setText(b.getMatch().getEquipe2().getNom());

            butEquipe1Label.setText("BLOC: " + b.getBloc());

            butEquipe2Label.setText("Prix: " + b.getPrix());

            String absulutePath = new File("").getAbsolutePath();
            Image image1 = new Image(new FileInputStream(absulutePath + "\\src\\GUI\\images\\" + b.getMatch().getEquipe1().getLogo()));

            logoEquipe1.setImage(image1);
            Image image2 = new Image(new FileInputStream(absulutePath + "\\src\\GUI\\images\\" + b.getMatch().getEquipe2().getLogo()));

            logoEquipe2.setImage(image2);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MatchItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
