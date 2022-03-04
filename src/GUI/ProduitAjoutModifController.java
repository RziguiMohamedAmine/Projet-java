/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.categorie;
import entite.produit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.StringConverter;
import javax.swing.JFileChooser;
import service.ServiceCategorie;
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author sof
 */
public class ProduitAjoutModifController implements Initializable {

    private ServiceCategorie serviceCategorie;
    private ObservableList<categorie> produitList = FXCollections.observableArrayList();
    private ServiceProduit serviceProduit;
    private ProduitGestionController pc;
    private produit p;
    @FXML
    private TextField nomField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField prixField;
    @FXML
    private Button imageButton;
    @FXML
    private ComboBox<categorie> categorieBox;
    @FXML
    private TextField image;
    @FXML
    private Label label;
    @FXML
    private Button actionButon;
    @FXML
    private TextField idField;
    @FXML
    private TextField stockField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        serviceCategorie = new ServiceCategorie();
        serviceProduit = new ServiceProduit();
        produitList.addAll(serviceCategorie.getAll());
        categorieBox.setItems(produitList);
        StringConverter<categorie> converter = new StringConverter<categorie>() {
            @Override
            public String toString(categorie object) {
                return object.getNom();
            }

            @Override
            public categorie fromString(String string) {
                return null;
            }
        };

        categorieBox.setConverter(converter);
    }    

    @FXML
    private void updateOrAdd(ActionEvent event) {
        String nom = nomField.getText();
         String imagePath;
            float prix = Float.parseFloat(prixField.getText());
            String description = descriptionField.getText();
            categorie c= categorieBox.getSelectionModel().getSelectedItem();
            System.out.println(p);
            if(image.getText().isEmpty()){
                imagePath=p.getImage();
            }else{
                imagePath = image.getText();
            }
            int stock =Integer.parseInt(stockField.getText()) ;
            produit p= new produit(nom, imagePath, prix, description, c, stock);
            System.out.println(p);
        if(actionButon.getText().equals("Mise à jour")){
            p.setId(Integer.parseInt(idField.getText()) );
            
            if(serviceProduit.update(p)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert Mise à jour");
           alert.setContentText("produit Mise à jour");
           alert.show();
        }
        }else{
            if(serviceProduit.insert(p)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("alert ajout");
           alert.setContentText("produit ajouter");
           alert.show();
        }

        
       }
        
        pc.refreshTable();
    }
    
    public void setButton(){
        label.setText("Mise à jour Produit");
        actionButon.setText("Mise à jour");
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
//               imagev.setImage(imagee);
          } catch (FileNotFoundException ex) {
              System.out.println(ex.getMessage());
          }
          
    }
    
    public void setTextField(produit p){
        idField.setText(p.getId()+"");
        nomField.setText(p.getNom());
        prixField.setText(p.getPrix()+"");
        descriptionField.setText(p.getDescription());
        categorieBox.getSelectionModel().select(p.getCat());
        stockField.setText(p.getStock()+"");
        this.p=p;
        
    }
    
    public void initializeController(ProduitGestionController pc){
        this.pc=pc;
    }
    
}