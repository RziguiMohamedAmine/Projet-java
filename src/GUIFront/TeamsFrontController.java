/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entite.Equipe;
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
import service.EquipeService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class TeamsFrontController implements Initializable {

     EquipeService es=new EquipeService();
     List<Equipe> equipeList = new ArrayList<>(); 
      Equipe equipe;
     listener l;
    
    @FXML
    private VBox chosenTeam;
    @FXML
    private Label nomequipe;
    @FXML
    private ImageView imageequipe;
    @FXML
    private Label entreneur;
    @FXML
    private Label stade;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
   
    
    
    
     private void setChosenTeam(Equipe e) throws FileNotFoundException {
        nomequipe.setText(e.getNom());
 
       Image  image = new Image(new FileInputStream(e.getLogo()));
        imageequipe.setImage(image);
        entreneur.setText(e.getNom_entreneur());
        stade.setText(e.getStade());
//        chosenTeam.setStyle("-fx-background-color: #857777;\n" +
//                "    -fx-background-radius: 30;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO       
      equipeList.addAll(es.getAll());
      //System.out.println(equipeList.size());
       // System.out.println(equipeList);
        if(equipeList.size()>0)
        {
          try {
              // System.out.println(equipeList);
              setChosenTeam(equipeList.get(0));
          } catch (FileNotFoundException ex) {
              Logger.getLogger(TeamsFrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
            l=new listener() {
                @Override
                public void onClickListener(Equipe e) {
                    try {
                        setChosenTeam(e);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TeamsFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
        
        int column=0;
        int row =1;
        //System.out.println(equipeList.size());
        try {
        for(int i=0;i<equipeList.size();i++)
        { 
              //System.out.println(equipeList.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("EquipeOne.fxml"));
            
             AnchorPane anchorpane=fxmlLoader.load();
           
            
            EquipeOneController eqone=fxmlLoader.getController();
            eqone.setData(equipeList.get(i),l);
            
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
            GridPane.setMargin(anchorpane,new Insets(equipeList.size()));
        }
        
        } catch (IOException ex) {
                    ex.getMessage();
        } 
            
      
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
