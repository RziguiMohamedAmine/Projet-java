/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entite.Equipe;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class EquipeOneController  {

    private Equipe equipe;
    
    @FXML
    private Label namelabel;
    @FXML
    private void click(MouseEvent event)
    {
        l.onClickListener(equipe);
    }
            
            
    @FXML
    private ImageView img;
    private listener l;
    /**
     * Initializes the controller class.
     */
  
    
    public void setData(Equipe equipe,listener l) throws FileNotFoundException
    {
        this.equipe=equipe;
        this.l=l;
        namelabel.setText(equipe.getNom());
       Image  image = new Image(new FileInputStream(equipe.getLogo()));
        img.setImage(image);         
     
    }
    
}
