/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import GUI.JoueurDetailsController;
import GUI.MapController;
import GUI.SquadController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.Equipe;
import entite.Joueur;
import entite.JoueurMatch;
import entite.Match;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.EquipeService;
import service.JoueurService;
import service.MatchService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class TeamsFrontController implements Initializable {

     EquipeService es=new EquipeService();
     MatchService ms=new MatchService();
     
     List<Equipe> equipeList = new ArrayList<>(); 
      Equipe equipe;
     listener l;
     JoueurService js=new JoueurService();
     List<Joueur> joueurList = new ArrayList<>();
      Joueur joueur;
     listenerjoueur lj;    
     Equipe e=new Equipe();
  List<JoueurMatch> scorer = new ArrayList<>();
  List<Match> matchList =new ArrayList<>();
  
  
     JoueurMatch jm;
     listenerTop lt;
  
     Equipe equ=null;
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
    @FXML
    private Button effectif;
    @FXML
    private TextField searchfld;

    /**
     * Initializes the controller class.
     */
   
    AnchorPane anchorpane;
    @FXML
    private Label nomj;
    @FXML
    private Button teams;
    @FXML
    private Label date;
    @FXML
    private Label nation;
    @FXML
    private Label nb_but;
    @FXML
    private Label but;
    @FXML
    private FontAwesomeIconView map;
    @FXML
    private Button matchs;
    
       private void setChosenJoueurScorer(JoueurMatch j) throws FileNotFoundException {
        map.setVisible(false);
         nomj.setText(j.getJoueur().getNom());
         nomequipe.setText(j.getJoueur().getPrenom());
          date.setText("date naissance");
         entreneur.setText(j.getJoueur().getDate_naiss().toString());      
         stade.setText(j.getJoueur().getNationalite());
        Image  image = new Image(new FileInputStream(j.getJoueur().getImage()));
        imageequipe.setImage(image);
        nb_but.setText("Nombre de buts");
        but.setText(" "+j.getNb_but());
         nation.setText("Nationalité");
//        chosenTeam.setStyle("-fx-background-color: #857777;\n" +
//                "    -fx-background-radius: 30;");
    }
       
       
     private void setChosenJoueur(Joueur j) throws FileNotFoundException {
       
        nomj.setText(j.getPrenom());
        nomequipe.setText(j.getNom());
        entreneur.setText(j.getDate_naiss().toString());
        nb_but.setText("Taille");
        but.setText(String.valueOf(j.getTaille()));
       // taille.setText(String.valueOf(j.getTaille()));
        stade.setText(j.getNationalite());
        Image  image = new Image(new FileInputStream(j.getImage()));
        imageequipe.setImage(image);
        date.setText("date naissance");
        nation.setText("Nationalité");
        map.setVisible(false);
//        chosenTeam.setStyle("-fx-background-color: #857777;\n" +
//                "    -fx-background-radius: 30;");
    }
    
     private void setChosenTeam(Equipe e) throws FileNotFoundException {
          map.setVisible(true);
         this.equipe = e;
         nomequipe.setText(e.getNom());
          Image  image = new Image(new FileInputStream(e.getLogo()));
        imageequipe.setImage(image);
        entreneur.setText(e.getNom_entreneur());
        stade.setText(e.getStade());
       map.setStyle(  " -fx-cursor: hand ;"
                       + "-glyph-size:28px;"
                       + "-fx-fill:#000000;");
        date.setText("entreneur");
        nation.setText("Stade");
        
//        chosenTeam.setStyle("-fx-background-color: #857777;\n" +
//                "    -fx-background-radius: 30;");
    }
    
     void data()
     {
         
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
            
             anchorpane=fxmlLoader.load();
           
         
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
        
        //*********************************************************************
        //**********************************************************************
        
             searchfld.textProperty().addListener((observable, oldValue, newValue) -> {
           //System.out.println("textfield changed from " + oldValue + " to " + newValue);
            
             grid.getChildren().clear();
             anchorpane.getChildren().clear();
                equipeList.clear();
            String nom=newValue;
           equipeList.addAll(es.Search(newValue));
           
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
        
        int column1=0;
        int row1=1;
        //System.out.println(equipeList.size());
        try {
        for(int i=0;i<equipeList.size();i++)
        { 
              //System.out.println(equipeList.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("EquipeOne.fxml"));
            
             anchorpane=fxmlLoader.load();
           
         
            EquipeOneController eqone=fxmlLoader.getController();
            eqone.setData(equipeList.get(i),l);
            
            if(column1==3)
            {
                column1=0;
                row1++;
            }
            
            grid.add(anchorpane,column1++,row1);
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
});  
      
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO       
      data();
    }    

    @FXML
    private void effectif(ActionEvent event) throws FileNotFoundException {
        
       effectif.setVisible(false);
      
             grid.getChildren().clear();
             anchorpane.getChildren().clear();
             setChosenTeam(equipe);
              joueurList.addAll(js.getjoueurbyequipe(equipe));          
             
             if(joueurList.size()>0)
        {
          try {
              // System.out.println(equipeList);
              setChosenJoueur(joueurList.get(0));
          } catch (FileNotFoundException ex) {
              Logger.getLogger(TeamsFrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
            lj=new listenerjoueur() {
                @Override
                public void onClickListener(Joueur j) {
                    try {
                        setChosenJoueur(j);
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
        for(int i=0;i<joueurList.size();i++)
        { 
              //System.out.println(equipeList.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("JoueurOne.fxml"));
            
             AnchorPane anchorpane=fxmlLoader.load();
           
            
            JoueurOneController eqone=fxmlLoader.getController();
            eqone.setData(joueurList.get(i),lj);
            
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

     @FXML
 private void top(ActionEvent event) {
      
        scorer.clear();
       grid.getChildren().clear();
        anchorpane.getChildren().clear();
       scorer.addAll(js.TopScorer());
     if(scorer.size()>0)
        {
          try {
              // System.out.println(equipeList);
              setChosenJoueurScorer(scorer.get(0));
          } catch (FileNotFoundException ex) {
              Logger.getLogger(TeamsFrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
          lt= new listenerTop() {
                @Override
                public void onClickListenerr(JoueurMatch e) {
                    try {
                        setChosenJoueurScorer(e);
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
        for(int i=0;i<scorer.size();i++)
        { 
             // System.out.println(scorer.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("TopScorer.fxml"));
            
             AnchorPane anchorpane=fxmlLoader.load();
           
            
            TopScorerController eqone=fxmlLoader.getController();
            eqone.setData(scorer.get(i),lt);
            
            if(column==1)
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
            GridPane.setMargin(anchorpane,new Insets(scorer.size()));
        }
        
        } catch (IOException ex) {
                    ex.getMessage();
        } 
     
 } 
    

    @FXML
    private void teams(ActionEvent event) {
            
                effectif.setVisible(true);
               nb_but.setText("");
                but.setText("");
                grid.getChildren().clear();
                anchorpane.getChildren().clear();
                equipeList.clear();
                joueurList.clear();
                data();
    }

    @FXML
    private void map(MouseEvent event) throws FileNotFoundException {
       
                          setChosenTeam(equipe);
                          String s=equipe.getStade();
                          System.out.println(s);
                             FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/map.fxml"));
                            
                            try {
                                loader.load();
                               
                            } catch (IOException ex) {
                                Logger.getLogger(JoueurDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                               MapController mapController = loader.getController();                          
                              mapController.setTextField(equipe.getStade());
                              //System.out.println(equipe.getStade());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
        
    }

    @FXML
    private void matchs(ActionEvent event) throws FileNotFoundException {
           
            grid.getChildren().clear();
            
             anchorpane.getChildren().clear();
             setChosenTeam(equipe);
             matchList.clear();
             
              matchList.addAll(ms.getmatchsByEquipe(equipe.getNom())); 

          
             int column=0;
             int row =1;
        //System.out.println(equipeList.size());
        try {
        for(int i=0;i<matchList.size();i++)
        { 
            
            System.out.println(matchList.get(i).getEquipe1().getNom());
             System.out.println(matchList.get(i).getEquipe2().getNom());
              //System.out.println(equipeList.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MatchItem.fxml"));
            
             AnchorPane anchorpane=fxmlLoader.load();
           
            
            MatchItemController eqone=fxmlLoader.getController();
            eqone.setMatch(matchList.get(i));
            eqone.setDate(matchList.get(i));
            
            if(column==1)
            {
                column=0;
                row++;
            }
            
            grid.add(anchorpane,column++,row); 
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
            GridPane.setMargin(anchorpane,new Insets(matchList.size()));
        }
        
        } catch (IOException ex) {
                    ex.getMessage();
        } 
             
        
    }
   

   
    
    

    
    
    
    
    
    
    
    
    
    
}
