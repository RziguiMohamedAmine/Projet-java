/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;


import GUIFront.*;
import GUIFront.*;
import entite.Equipe;
import entite.Joueur;
import entite.JoueurMatch;
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
public class TopScorerController  {

    private Equipe equipe;
    private Joueur joueur;
    private JoueurMatch jm;
    private Label namelabel;
    @FXML
    private Label prenom;
    @FXML
    private Label nomjoueur;   
    @FXML
    private Label prenom1;
    @FXML
    private Label nb;

    @FXML
    private void click(MouseEvent event)
    {
        l.onClickListenerr(jm);
    }
  
            
    @FXML
    private ImageView img;
    private listenerTop l;
    /**
     * Initializes the controller class.
     * @param joueur
     * @throws java.io.FileNotFoundException
     */
  
    
    public void setData(JoueurMatch joueur,listenerTop l) throws FileNotFoundException
    {
        this.jm=joueur;   
        this.l=l;
        nomjoueur.setText(joueur.getJoueur().getNom());
        prenom.setText(joueur.getJoueur().getPrenom());
        //nb_buts.setText(String.valueOf(joueur.getNb_but()));
        nb.setText(String.valueOf(joueur.getNb_but()));
        Image  image = new Image(new FileInputStream(joueur.getJoueur().getImage()));
        img.setImage(image);         
     
    }
    
}
