/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.Equipe;
import entite.Joueur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.EquipeService;
import service.JoueurService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class SquadController implements Initializable {

      EquipeService es=new EquipeService();
      JoueurService js=new JoueurService();
      ObservableList<Joueur> joueurList = FXCollections.observableArrayList(); 
      Joueur joueur=null;
      Equipe e=new Equipe();
      int idEquipe;
    @FXML
    private TableColumn<Joueur,String> nom;
    @FXML
    private TableColumn<Joueur,String> prenom;
    @FXML
    private TableColumn<Joueur,String> poste;
    @FXML
    private TableColumn<Joueur,String> nation;
    @FXML
    private TableColumn<Joueur,Date> date;   
    @FXML
    private TableColumn<Joueur,String> taille;
    @FXML
    private TableColumn<Joueur,String> poids;  
    @FXML
    private TableColumn<Joueur,String> image;
    @FXML
    private ImageView imagev;
    @FXML
    private TableView<Joueur> joueurTable;
    @FXML
    private TableColumn<Joueur,String> edit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // System.out.println(e);
        
    }

      private void refreshTable() {
       
          joueurList.clear();
          joueurList.addAll(js.getjoueurbyequipe(e));
          joueurTable.setItems(joueurList);
       
    }

    
    private void LoadDate()
    {
        
        refreshTable();
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        poste.setCellValueFactory(new PropertyValueFactory<>("poste"));
        nation.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_naiss"));    
        taille.setCellValueFactory(new PropertyValueFactory<>("taille"));
        poids.setCellValueFactory(new PropertyValueFactory<>("poids"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
        Callback<TableColumn<Joueur, String>, TableCell<Joueur, String>> cellFoctory = (TableColumn<Joueur, String> param) -> {
            // make cell containing buttons
            final TableCell<Joueur, String> cell = new TableCell<Joueur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                     
                        FontAwesomeIconView logoIcon = new FontAwesomeIconView(FontAwesomeIcon.IMAGE);
                      
                        
                        logoIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                   
                         
                          logoIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             joueur = joueurTable.getSelectionModel().getSelectedItem();
                             Image image;
                            try {
                                image = new Image(new FileInputStream(joueur.getImage()));
                            
                                imagev.setImage(image); 
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(EquipeDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                                     
                        });
                         
                         
                        HBox managebtn = new HBox(logoIcon);
                        managebtn.setStyle("-fx-alignment:center");                  
                        HBox.setMargin(logoIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
           edit.setCellFactory(cellFoctory);
          joueurTable.setItems(joueurList);
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
