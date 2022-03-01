/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TextField tfNewImage;
    @FXML
    private TableView<User> tvUser1;
    @FXML
    private TableColumn<User,String> colNomUser;
    @FXML
    private TableColumn<User,String> colPrenomUser;
    @FXML
    private TableColumn<User,String> colEmailUser;
    @FXML
    private TableColumn<User,String> colPassUser;
    @FXML
    private TableColumn<User,Integer> colTelUser;
    @FXML
    private TableColumn<User,String> colImageUser;
    @FXML
    private TableColumn<User,String> colRoleUser;
    @FXML
    private Button BtnUpdateUser;
    @FXML
    private TextField tfId;
    private UserService UserService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.UserService = new UserService();
       colNomUser.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
        colPrenomUser.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        colEmailUser.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        colPassUser.setCellValueFactory(new PropertyValueFactory<User,String>("pass"));
        colTelUser.setCellValueFactory(new PropertyValueFactory<User,Integer>("tel"));
        colImageUser.setCellValueFactory(new PropertyValueFactory<User,String>("image"));   
        colRoleUser.setCellValueFactory(new PropertyValueFactory<User,String>("role"));
       User u7 =new User(index, nom, prenom, email, pass, index, image);
        UserService.getOne();
       tvUser1.setItems(User);
    }    

    @FXML
    private void onMouseClicked(MouseEvent event) {
        int tel;
     User user= tvUser1.getSelectionModel().getSelectedItem();
        tfNewNom.setText(user.getNom());
        tfNewPrenom.setText(user.getPrenom());
        tfNewPass.setText(user.getPass());
        tfNewTel.setText(""+user.getTel());
        tfNewImage.setText(user.getImage());
        tfId.setText(""+user.getId());
      
    }

    @FXML
    private void OnUpdateUser(ActionEvent event) {
        int tel;
         int id;
        String nom =tfNewNom.getText();
        String prenom = tfNewPrenom.getText();
        String pass = tfNewPass.getText();
       
        
        try{
            tel = Integer.parseInt(tfNewTel.getText());
        }catch(NumberFormatException ex){
            System.out.println("le numero de tel doit être un entier ");
         return;
        }
        
        
        String image = tfNewImage.getText();
         try{
            id = Integer.parseInt(tfId.getText());
        }catch(NumberFormatException ex){
            System.out.println("  doit être un entier ");
         return;
        }
        
        
        
       User u7 =new User(id,nom, prenom, pass, tel, image);
        UserService.updateuser(u7);
        
    }
    
}
