/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class UpdateUserController implements Initializable {
    int index=-1;

    @FXML
    private TextField tfNewNom;
    @FXML
    private TextField tfNewPrenom;
    @FXML
    private TextField tfNewPass;
    @FXML
    private TextField tfNewTel;
  
    @FXML
    private Button BtnUpdateUser;
    private TextField tfId;
    private UserService UserService;
     private Parent fxml;
    private Stage stage;
    private Scene scene;
    
    
    
        int id=User.session.getId();
        String nom=User.session.getNom();
        String prenom=User.session.getPrenom();
        String pass = User.session.getPass();
        int tel=User.session.getTel();
        
       User u=new User(nom,prenom,pass,tel);
    
    UserService us =new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
   
       
       tfNewNom.setText(nom);
       tfNewPrenom.setText(prenom);
       tfNewPass.setText(pass);
       tfNewTel.setText(String.valueOf(tel));
    }    


    @FXML
    private void OnUpdateUser(ActionEvent event) throws IOException {
        int tel;
         
         
         
        String nom = tfNewNom.getText();
        String prenom = tfNewPrenom.getText();
        String pass = tfNewPass.getText();
      
        
        try{
            tel = Integer.parseInt(tfNewTel.getText());
        }catch(NumberFormatException ex){
            System.out.println("le numero de tel doit être un entier ");
         return;
        }
        
       
        User u =new User(nom,prenom,pass,tel);
        u.setId(id);
        System.out.println(u);
        System.out.println(us.updateuser(u));
        System.out.println(u);
     
        
        
      fxml = FXMLLoader.load(getClass().getResource("home.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxml);
            stage.setScene(scene);
            stage.show();
        
    }
    
}
