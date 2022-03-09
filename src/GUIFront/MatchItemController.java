/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;


import entite.Match;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class MatchItemController {

    private Match match;


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
//            Image image1 = new Image(new FileInputStream(absulutePath + "\\src\\GUI\\images\\" + m.getEquipe1().getLogo()));
            Image image1 = new Image(new FileInputStream(m.getEquipe1().getLogo()));



            logoEquipe1.setImage(image1);
//            Image image2 = new Image(new FileInputStream(absulutePath + "\\src\\GUI\\images\\" + m.getEquipe2().getLogo()));

            Image image2 = new Image(new FileInputStream(m.getEquipe2().getLogo()));


            logoEquipe2.setImage(image2);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MatchItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    public void setMatch(Match m) {
        this.match = m;
    }

   

    

}
