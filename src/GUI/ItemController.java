/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sof
 */
public class ItemController implements Initializable {

    @FXML
    private Label LabelNom;
    @FXML
    private Label LabelPrix;
    @FXML
    private ImageView img;

    private produit p;
    private Listeneritem l;
     @FXML
    private void click(MouseEvent event)
    {
        l.onClickListener(p);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setData(produit prod,Listeneritem l) throws FileNotFoundException
    {
        this.p=prod;
        this.l=l;
        LabelNom.setText(prod.getNom());
        LabelPrix.setText(String.valueOf(prod.getPrix()));
       Image  image = new Image(new FileInputStream(prod.getImage()));
        img.setImage(image);         
     
    }
}
