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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.EquipeService;
import service.JoueurService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class PlayersFrontController implements Initializable {

     EquipeService es=new EquipeService();
     JoueurService js=new JoueurService();
     List<Equipe> equipeList = new ArrayList<>();
     List<Joueur> joueurList = new ArrayList<>();
     Equipe e=new Equipe();
     Joueur joueur;
     listenerjoueur l;
    
    @FXML
    private VBox chosenTeam;
    private Label nomequipe;
    private ImageView imageequipe;
    private Label entreneur;
    private Label stade;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label nomjoueur;
    @FXML
    private ImageView imagejoueur;
    @FXML
    private Label date;
    @FXML
    private Label taille;
    @FXML
    private Label nation;
    @FXML
    private Label prenom;

    /**
     * Initializes the controller class.
     */
   
    
    
    
     private void setChosenJoueur(Joueur j) throws FileNotFoundException {
       
        prenom.setText(j.getPrenom());
        nomjoueur.setText(j.getNom());
        date.setText(j.getDate_naiss().toString());
        taille.setText(String.valueOf(j.getTaille()));
        nation.setText(j.getNationalite());
        Image  image = new Image(new FileInputStream(j.getImage()));
        imagejoueur.setImage(image);

//        chosenTeam.setStyle("-fx-background-color: #857777;\n" +
//                "    -fx-background-radius: 30;");
    }
       private void LoadDate()
    {
        joueurList.addAll(js.getjoueurbyequipe(e));
      //System.out.println(equipeList.size());
       // System.out.println(equipeList);
        if(joueurList.size()>0)
        {
          try {
              // System.out.println(equipeList);
              setChosenJoueur(joueurList.get(0));
          } catch (FileNotFoundException ex) {
              Logger.getLogger(PlayersFrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
            l=new listenerjoueur() {
                @Override
                public void onClickListener(Joueur j) {
                    try {
                        setChosenJoueur(j);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PlayersFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

           
            };
        }
        
        int column=0;
        int row =1;
        //System.out.println(equipeList.size());
        try {
        for(int i=0;i<joueurList.size();i++)
        { 
              //System.out.println(equipeList.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("JoueurOne.fxml"));
            
             AnchorPane anchorpane=fxmlLoader.load();
           
            
            JoueurOneController eqone=fxmlLoader.getController();
            eqone.setData(joueurList.get(i),l);
            
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
            GridPane.setMargin(anchorpane,new Insets(joueurList.size()));
        }
        
        } catch (IOException ex) {
                    ex.getMessage();
        } 
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO       
     
            
      
    }    
    
    
    
    void setTextField(int id,String nome, String logo, String entreneur,String niveau,String stade) 
     {
        e.setId(id);
        e.setNom(nome);
        e.setLogo(logo);
        e.setNom_entreneur(entreneur);
        e.setNiveau(niveau);
        e.setStade(stade);
        LoadDate();
    }

 
    
    
    
    
    
    
    
    
}
