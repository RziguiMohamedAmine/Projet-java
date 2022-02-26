/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class HomeController implements Initializable {

    @FXML
    private StackPane contentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try{
            Parent fxml=FXMLLoader.load(getClass().getResource("EquipeDetails.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        }catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }    

    
    
    
    
    
    @FXML
    private void Close(MouseEvent event) 
    {
         Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
         stage.close();
    }
 @FXML
       private void home(ActionEvent event) {
        
    }
    @FXML
    private void gestion_equipes(ActionEvent event) {
          try{
            Parent fxml=FXMLLoader.load(getClass().getResource("EquipeDetails.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        }catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

  

    @FXML
    private void AjouterJoueur(ActionEvent event) {
          try{         
            Parent fxml=FXMLLoader.load(getClass().getResource("Addjoueur.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        }
     catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
            
    }

    @FXML
    private void afficherJoueurs(ActionEvent event) 
    {
           try{         
            Parent fxml=FXMLLoader.load(getClass().getResource("JoueurDetails.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        }
     catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
            
    }

    @FXML
    private void AjouterEquipes(ActionEvent event) {
        try{         
            Parent fxml=FXMLLoader.load(getClass().getResource("AddEquipe.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        }
     catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}