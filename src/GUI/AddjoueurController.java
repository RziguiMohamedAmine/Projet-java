/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Equipe;
import entite.Joueur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javax.swing.JFileChooser;
import service.EquipeService;
import service.JoueurService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AddjoueurController implements Initializable {
    Joueur joueur=null;
    EquipeService es =new EquipeService();
    JoueurService js=new JoueurService();
     ObservableList<Equipe> equipeList = FXCollections.observableArrayList();

     
    @FXML
    private Button update;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField poste;
    @FXML
    private TextField nation;
    @FXML
    private TextField date;
    @FXML
    private TextField taille;
    @FXML
    private TextField poids;
    @FXML
    private TextField image;
    @FXML
    private ImageView imagev;
    @FXML
    private ComboBox<Equipe> equipe;
    @FXML
    private TextField equipenom;
    int idjoueur;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        equipeList.addAll(es.getAll());
        equipe.setItems(equipeList);
        StringConverter<Equipe> converter = new StringConverter<Equipe>() {
            @Override
            public String toString(Equipe object) {
                return object.getNom();
            }

            @Override
            public Equipe fromString(String string) {
                return null;
            }
        };

        equipe.setConverter(converter);
      
    }    

    @FXML
    private void save(ActionEvent event) throws ParseException
    {
         String nome =nom.getText();
         String prenome =prenom.getText();
         String postee=poste.getText();
         String natione=nation.getText();
           SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
           String dob=date.getText();
           java.util.Date dn=sd.parse(dob);
           long ms=dn.getTime();
           java.sql.Date sdo=new java.sql.Date(ms);      
         Float taillee = Float.parseFloat(taille.getText());
         Float Poidse = Float.parseFloat(poids.getText());
         String imagee = image.getText();
         //String equipee = equipenom.getText();
         
         
        Equipe e=equipe.getSelectionModel().getSelectedItem();
        equipenom.setText(String.valueOf(e));

         if(nome.isEmpty()||prenome.isEmpty()||postee.isEmpty()||imagee.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all data");
            alert.showAndWait();
        }
        else
        {
            
            Joueur j=new Joueur(nome,prenome,postee,natione,sdo,taillee,Poidse,imagee,e);
            js.insert(j);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Joueur Ajouté");
            alert.showAndWait();
            
        }
        
    }

   
      private void clean() {
        nom.setText(null);
        prenom.setText(null);
        poste.setText(null);
        nation.setText(null);
        date.setText(null);
        taille.setText(null);
        poids.setText(null);
        image.setText(null);
        equipenom.setText(null);
    }

    @FXML
    private void get_image(ActionEvent event) {
         JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        image.setText(filename);
        Image imagee;
          try {
              imagee = new Image(new FileInputStream(filename));
               imagev.setImage(imagee);
          } catch (FileNotFoundException ex) {
              Logger.getLogger(AddEquipeController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
    }

    @FXML
    private void select(ActionEvent event) {
      
        String e=equipe.getSelectionModel().getSelectedItem().getNom();
        equipenom.setText(e);
       
    }
    
    
     void setTextField(int id,String nome, String prenome, String postee, String nationalite, Date datee, float taillee, float poidse, String imagee, Equipe equipee) 
     {
        idjoueur=id;
        nom.setText(nome);
        prenom.setText(prenome);
        poste.setText(postee);
        nation.setText(nationalite);
        date.setText(String.valueOf(datee));
        taille.setText(String.valueOf(taillee));
        poids.setText(String.valueOf(poidse));
        image.setText(imagee);
        equipenom.setText(String.valueOf(equipee));
        

    }

    @FXML
    private void updatee(ActionEvent event) throws ParseException {
       
        
        String nome =nom.getText();
         String prenome =prenom.getText();
         String postee=poste.getText();
         String natione=nation.getText();
           SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
           String dob=date.getText();
           java.util.Date dn=sd.parse(dob);
           long ms=dn.getTime();
           java.sql.Date sdo=new java.sql.Date(ms);      
         Float taillee = Float.parseFloat(taille.getText());
         Float Poidse = Float.parseFloat(poids.getText());
         String imagee = image.getText();
         //String equipee = equipenom.getText();
         
         
        Equipe e=equipe.getSelectionModel().getSelectedItem();
        equipenom.setText(String.valueOf(e));
           Joueur j=new Joueur(nome,prenome,postee,natione,sdo,taillee,Poidse,imagee,e);
           j.setId(idjoueur);
            js.update(j);
             clean();
         
    }
    
    
}
