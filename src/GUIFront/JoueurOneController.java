/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;


import GUIFront.*;
import entite.Equipe;
import entite.Joueur;
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
public class JoueurOneController  {

    private Equipe equipe;
    private Joueur joueur;
    private Label namelabel;
    @FXML
    private Label prenom;
    @FXML
    private Label nomjoueur;
    @FXML
    private void click(MouseEvent event)
    {
        l.onClickListener(joueur);
    }
            
            
    @FXML
    private ImageView img;
    private listenerjoueur l;
    /**
     * Initializes the controller class.
     */
  
    
    public void setData(Joueur joueur,listenerjoueur l) throws FileNotFoundException
    {
        this.joueur=joueur;
        this.l=l;
        nomjoueur.setText(joueur.getNom());
        prenom.setText(joueur.getPrenom());
       Image  image = new Image(new FileInputStream(joueur.getImage()));
        img.setImage(image);         
     
    }
    
}
