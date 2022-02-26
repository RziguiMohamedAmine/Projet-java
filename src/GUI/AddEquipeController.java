/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Equipe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import service.EquipeService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AddEquipeController implements Initializable {

      EquipeService es=new EquipeService();
    
    
    @FXML
    private TextField nomeqfld;
    @FXML
    private TextField logofld;
    @FXML
    private TextField coachfld;
    @FXML
    private TextField levelfld;
    @FXML
    private Button logobtn;
    @FXML
    private ImageView logov;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
   

    @FXML
    private void getlogo(ActionEvent event)
    {
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        logofld.setText(filename);
        Image image;
          try {
              image = new Image(new FileInputStream(filename));
               logov.setImage(image);
          } catch (FileNotFoundException ex) {
              Logger.getLogger(AddEquipeController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
    }

    @FXML
    private void save(ActionEvent event) {
            String name =nomeqfld.getText();
        String logo =logofld.getText();
        String coach=coachfld.getText();
        String level=levelfld.getText();
        
        if(name.isEmpty()||logo.isEmpty()||coach.isEmpty()||level.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all data");
            alert.showAndWait();
        }
        else
        {
            
            Equipe e=new Equipe(name,logo,coach,level);
            es.insert(e);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Equipe Ajouté");
            alert.showAndWait();
            
        }
    }
    

    private void clean() {
       nomeqfld.setText(null);
        logofld.setText(null);
        coachfld.setText(null);
       levelfld.setText(null);
        
    }

  
    
}
