/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.Equipe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.text.IconView;
import service.EquipeService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class EquipeDetailsController implements Initializable {

      EquipeService es=new EquipeService();
      ObservableList<Equipe> equipeList = FXCollections.observableArrayList(); 
      Equipe equipe=null;
    
    
    @FXML
    private TableView<Equipe> equipeTable;
    @FXML
    private TableColumn<Equipe,String> nom;
    @FXML
    private TableColumn<Equipe,Image> logo;
    @FXML
    private TableColumn<Equipe,String> coach;
    @FXML
    private TableColumn<Equipe,String> level;
    @FXML
    private TableColumn<Equipe,String> edit;
    
    @FXML
    private ImageView logoview;
    @FXML
    private Button update;
 
    @FXML
    private TextField logoupdate;
    @FXML
    private TextField nomupdate;
    @FXML
    private TextField coachupdate;
    @FXML
    private TextField levelupdate;
    @FXML
    private TableColumn<Equipe,String> stade;
    @FXML
    private TextField stadeupdate;
    @FXML
    private TableColumn<Equipe,String> mapp;
    @FXML
    private Button save;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          LoadDate();
           update.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                +"-fx-background-color:#00E676;"
                        );
          save.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                +"-fx-background-color:#00E676;"
                        );
    }    

    private void refreshTable() {
       
          equipeList.clear();
          equipeList.addAll(es.getAll());
          equipeTable.setItems(equipeList);
       
    }

    private void LoadDate()
    {
        
       
         refreshTable();
         
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        logo.setCellValueFactory(new PropertyValueFactory<>("logo"));
        coach.setCellValueFactory(new PropertyValueFactory<>("nom_entreneur"));
        level.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        stade.setCellValueFactory(new PropertyValueFactory<>("stade"));
        
        Callback<TableColumn<Equipe, String>, TableCell<Equipe, String>> cellFoctory = (TableColumn<Equipe, String> param) -> {
            // make cell containing buttons
            final TableCell<Equipe, String> cell = new TableCell<Equipe, String>() {
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
                        FontAwesomeIconView logoIcon = new FontAwesomeIconView(FontAwesomeIcon.IMAGE);
                        FontAwesomeIconView SquadIcon = new FontAwesomeIconView(FontAwesomeIcon.USERS);
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
                        SquadIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                      
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                           
                                equipe = equipeTable.getSelectionModel().getSelectedItem();
                                es.delete(equipe);
                                refreshTable();  

                        });
                         editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             equipe = equipeTable.getSelectionModel().getSelectedItem();
                             getSelected(equipe.getNom(),equipe.getLogo(),equipe.getNom_entreneur(),equipe.getNiveau(),equipe.getStade());
                            
                           

                        });
                         
                          SquadIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             equipe = equipeTable.getSelectionModel().getSelectedItem();                                        
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Squad.fxml"));
                            
                            try {
                                loader.load();
                               
                            } catch (IOException ex) {
                                Logger.getLogger(JoueurDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                               SquadController SquadController = loader.getController();                          
                           SquadController.setTextField(equipe.getId(),equipe.getNom(),equipe.getLogo(),equipe.getNom_entreneur(),equipe.getNiveau(),equipe.getStade());
                           
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                          

                        }); 
                         
                          logoIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             equipe = equipeTable.getSelectionModel().getSelectedItem();
                             Image image;
                            try {
                                image = new Image(new FileInputStream(equipe.getLogo()));
                                logoview.setImage(image); 
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(EquipeDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                                     
                        });
                        
                      
                        HBox managebtn = new HBox(editIcon, deleteIcon,logoIcon,SquadIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(logoIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(SquadIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         edit.setCellFactory(cellFoctory);   
            Callback<TableColumn<Equipe, String>, TableCell<Equipe, String>> cellFoctory1 = (TableColumn<Equipe, String> param) -> {
            // make cell containing buttons
            final TableCell<Equipe, String> cell = new TableCell<Equipe, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView mapIcon = new FontAwesomeIconView(FontAwesomeIcon.MAP_MARKER);
                    
                     
                        mapIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                                           
                         
                         mapIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             equipe = equipeTable.getSelectionModel().getSelectedItem();                                        
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("map.fxml"));
                            
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
                          

                        }); 
                     
                        
                      
                        HBox managebtn = new HBox(mapIcon);
                        managebtn.setStyle("-fx-alignment:center");                       
                        HBox.setMargin(mapIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         
         mapp.setCellFactory(cellFoctory1);
         equipeTable.setItems(equipeList);
         
         
    }
    
    private void getAddView(MouseEvent event)
    {
         Parent parent;
          try 
          {
              parent = FXMLLoader.load(getClass().getResource("addEquipe.fxml"));
               Scene scene = new Scene(parent);
               Stage stage =new Stage();
               stage.setScene(scene);
               stage.initStyle(StageStyle.UTILITY);
               stage.show();
          } 
          
          catch (IOException ex) {
              System.out.println(ex.getMessage());
          }
            

    }
    
    
      void getSelected(String name, String logo, String coach,String level,String stade) {

        
        nomupdate.setText(name);
        logoupdate.setText(logo);
        coachupdate.setText(coach);
        levelupdate.setText(level);
        stadeupdate.setText(stade);

    }
    
    
    
    
    private void Close(MouseEvent event) 
    {
         Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
         stage.close();
    }

  

    @FXML
    private void updatee(MouseEvent event) 
    {   
        
         int id=equipe.getId();
         String name =nomupdate.getText();
         String logo =logoupdate.getText();
         String coach=coachupdate.getText();
         String level=levelupdate.getText();
         String stade=stadeupdate.getText();
           Equipe e=new Equipe(name,logo,coach,level,stade);
           e.setId(id);
            es.update(e);
             refreshTable();  
             clean();
    }
    
    
     private void clean() {
        nomupdate.setText(null);
        logoupdate.setText(null);
        coachupdate.setText(null);
        levelupdate.setText(null);
        stadeupdate.setText(null);
    }
    
    private StackPane contentArea;
   

    @FXML
    private void getlogo(ActionEvent event) 
    {
          JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        logoupdate.setText(filename);
        Image image;
          
    }

    @FXML
    private void save(ActionEvent event) {
        
        String name =nomupdate.getText();
         String logo =logoupdate.getText();
         String coach=coachupdate.getText();
         String level=levelupdate.getText();
         String stade=stadeupdate.getText();
          
               if(name.isEmpty()||logo.isEmpty()||coach.isEmpty()||level.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all data");
            alert.showAndWait();
        }
        else
        {
                
            Equipe e=new Equipe(name,logo,coach,level,stade);
             es.insert(e);
             refreshTable();  
             clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Equipe Ajouté");
            alert.showAndWait();
            
        }
    }
    

    
    
    
}
