/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class ClassmentLabelController implements Initializable {

    @FXML
    private Label text;
    @FXML
    private ImageView logoImageView;
    @FXML
    private AnchorPane anchorepane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setLabel(String text) {
        this.text.setText(text);
    }

    public void setImage(Image image) {
        logoImageView.setImage(image);
    }

    void setStyleLabel() {
        text.setMinWidth(211);
        text.setStyle("-fx-background-color:white");
        anchorepane.setStyle("-fx-min-width: 211px");

    }

}
