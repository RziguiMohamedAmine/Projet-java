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
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import service.EquipeService;
import service.JoueurService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class JoueurDetailsController implements Initializable {

      EquipeService es=new EquipeService();
      JoueurService js=new JoueurService();
      ObservableList<Joueur> joueurList = FXCollections.observableArrayList(); 
      Joueur joueur=null;
    
    @FXML
    private TableView<Joueur> joueurTable;
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
    private TableColumn<Joueur,String> id_eq;
    @FXML
    private TableColumn<Joueur,String> edit;
    @FXML
    private ImageView imagev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       LoadDate();
          
    }  
    
    private void refreshTable() {
       
          joueurList.clear();
          joueurList.addAll(js.getAll());
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
        id_eq.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getEquipe().getNom()));
        
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

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView logoIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                        logoIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                           
                                joueur = joueurTable.getSelectionModel().getSelectedItem();
                                js.delete(joueur);
                                refreshTable();  

                        });
                         editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             joueur = joueurTable.getSelectionModel().getSelectedItem();
                          //   getSelected(joueur.getNom(),joueur.getPrenom(),joueur.getPoste(),joueur.getNationalite(),
                            //         joueur.getDate_naiss(),joueur.getTaille(),joueur.getPoids(),joueur.getImage(),joueur.getEquipe());
                            
                           

                        });
                         
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
                         
                         
                        HBox managebtn = new HBox(editIcon, deleteIcon,logoIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
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
    
    
//        void getSelected( String nom, String prenom, String poste, String nationalite, Date date, float taille, float poids, String image, Equipe equipe) {
//
//        
//        nomupdate.setText(nom);
//        logoupdate.setText(prenom);
//        coachupdate.setText(poste);
//        levelupdate.setText(nationalite);
//        levelupdate.setText(date);
//        levelupdate.setText(taille);
//        levelupdate.setText(poids);
//        levelupdate.setText(image);
//        levelupdate.setText(equipe);
//    }
//    
    
}
