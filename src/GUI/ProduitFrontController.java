/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author sof
 */
public class ProduitFrontController implements Initializable {

    @FXML
    private VBox produitChoisis;
    @FXML
    private Label LabelNomProduit;
    @FXML
    private Label LabelPrixProduit;

    ServiceProduit sp=new ServiceProduit();
    List<produit> listprod=new ArrayList<>();
    produit p;
    produit pr=null;
    Listeneritem l;
    @FXML
    private ImageView ImgProduit;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    /**
     * Initializes the controller class.
     */
   
     private void setChosenProduct(produit p) throws FileNotFoundException {
         this.p = p;
         LabelNomProduit.setText(p.getNom());
          Image  image = new Image(new FileInputStream(p.getImage()));
          LabelPrixProduit.setText(String.valueOf(p.getPrix()));
        ImgProduit.setImage(image);

//        chosenTeam.setStyle("-fx-background-color: #857777;\n" +
//                "    -fx-background-radius: 30;");
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        
        listprod.addAll(sp.getAll());
     
        if(listprod.size()>0)
        {
          try {
           
              setChosenProduct(listprod.get(0));
          } catch (FileNotFoundException ex) {
              Logger.getLogger(ProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
            l=new Listeneritem() {
                @Override
                public void onClickListener(produit p) {
                    try {
                        setChosenProduct(p);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
        
        int column=0;
        int row =1;
     
        try {
        for(int i=0;i<listprod.size();i++)
        { 
              
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
            
             AnchorPane anchorpane=fxmlLoader.load();
           
            
            ItemController eqone=fxmlLoader.getController();
            eqone.setData(listprod.get(i),l);
            
            if(column==3)
            {
                column=0;
                row++;
            }
            
            grid.add(anchorpane,column++,row);
             //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
            GridPane.setMargin(anchorpane,new Insets(listprod.size()));
        }
        
        } catch (IOException ex) {
                  System.out.println(ex.getMessage());
        } 
    }    
    
}
