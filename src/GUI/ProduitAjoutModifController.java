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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.util.StringConverter;
import javax.swing.JFileChooser;
import service.ServiceCategorie;
import service.ServiceProduit;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entite.taille;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import service.ServiceTaille;

/**
 * FXML Controller class
 *
 * @author sof
 */
public class ProduitAjoutModifController implements Initializable {

    private ServiceCategorie serviceCategorie;
    private ServiceTaille serviceTaille;
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
            
        //Ajout 
        
            String nom = nomField.getText();
            String imagePath;
            
            float prix = Float.parseFloat(prixField.getText());
            String description = descriptionField.getText();
            categorie c= categorieBox.getSelectionModel().getSelectedItem();
            
            
            if(image.getText().isEmpty()){
                imagePath=p.getImage();
            }else{
                imagePath = image.getText();
            }
            
            int stock =Integer.parseInt(stockField.getText()) ;
         
            produit p=new produit(stock, nom, imagePath, prix, description, c, stock);
            System.out.println(p);
            if(actionButon.getText().equals("Mise à jour")){
            p.setId(Integer.parseInt(idField.getText()) );
            
            if(serviceProduit.update(p)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("Alert Mise à jour");
           alert.setContentText("Produit Mise à jour");
           alert.show();
        }
        }else{
            
            //barecode
            if(serviceProduit.insert(p)){
                int id=serviceProduit.getLastProduct();
                serviceProduit.updatecode(id);
                code(id*1000+12*6+"");
                
                
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("Alert Ajout");
           alert.setContentText("Produit Ajouter");
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
    
    private void code(String id)  {
        try {
        Code128Bean code128 = new Code128Bean();
        code128.setHeight(15f);
        code128.setModuleWidth(0.3);
        code128.setQuietZone(10);
        code128.doQuietZone(true);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 400, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        code128.generateBarcode(canvas,id);
        
            canvas.finish();
        

//write to png file
        String f = new File("").getAbsolutePath();
        FileOutputStream fos = new FileOutputStream(f+"\\src\\GUI\\images\\barcode"+id+".png");
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();
        

//write to pdf
//        Image png = Image.getInstance(baos.toByteArray());
//        png.setAbsolutePosition(0, 705);
//        png.scalePercent(25);
//
//        Document document;
//        document = new Document();
//        PdfPTable table = new PdfPTable(3);
//        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//        for (int aw = 0; aw < 27; aw++) {
//            Paragraph p = new Paragraph("        Product Name");
//            p.add("\n        Price:500");
////            p.add(createImageCell(png));
//            PdfPTable intable = new PdfPTable(1);
//            intable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//            intable.addCell(p);
//            intable.addCell(png);
//            intable.getDefaultCell().setBorder(0);
//            
//            table.addCell(intable);
//        }
////        table.setBorder(Border.NO_BORDER);
//        Paragraph p = new Paragraph("Product Name");
//        p.add("\nPrice:500");
//        p.add(png);
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("barcodes.pdf"));
//        document.open();
////        document.add();
//        document.add(table);
//        document.close();
//
//        writer.close();
//        
} catch (IOException ex) {
            Logger.getLogger(ProduitAjoutModifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
