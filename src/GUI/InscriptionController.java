/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.protocol.Resultset;
import com.sun.xml.internal.bind.WhiteSpaceProcessor;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.UserService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class InscriptionController implements Initializable {

    private TextField textFieldNom;
    private TextField textFieldPrenom;
    private TextField textFieldEmail;
    private TextField textFieldPass;
    private TextField textFieldTel;
    
    private TextField textFieldImage;
    private TextField textFieldId;
    private TextField textFieldRole;
    private UserService UserService;
    private TableView<User> tvUser;
    private TableColumn<User, String> colNom;
    private TableColumn<User, String> colPrenom;
    private TableColumn<User, String> colEmail;
    private TableColumn<User, String> colPass;
    private TableColumn<User, Integer> colTel;
    private TableColumn<User, String> colImage;
    private TableColumn<User,Integer> colId;
    private TableColumn<User,String> colRole;
    
    ObservableList<User> ListM = FXCollections.observableArrayList();
    int index = -1 ;
    Connection conn= null;
    Resultset rs= null;
    PreparedStatement pst= null;
    
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
    private TableView<?> tvUser1;
    @FXML
    private TableColumn<?, ?> colNomUser;
    @FXML
    private TableColumn<?, ?> colPrenomUser;
    @FXML
    private TableColumn<?, ?> colEmailUser;
    @FXML
    private TableColumn<?, ?> colPassUser;
    @FXML
    private TableColumn<?, ?> colTelUser;
    @FXML
    private TableColumn<?, ?> colImageUser;
    @FXML
    private TableColumn<?, ?> colRoleUser;
    @FXML
    private Button BtnUpdateUser;
    @FXML
    private TextField tfId;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.UserService = new UserService();
       colNom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        colPass.setCellValueFactory(new PropertyValueFactory<User,String>("pass"));
        colTel.setCellValueFactory(new PropertyValueFactory<User,Integer>("tel"));
        colImage.setCellValueFactory(new PropertyValueFactory<User,String>("image"));   
        colId.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        colRole.setCellValueFactory(new PropertyValueFactory<User,String>("role"));
       ListM.addAll(UserService.getAll());
       tvUser.setItems(ListM);
    
       
       
    }    

    private void OnCreate(ActionEvent event) {
        int tel;
        String nom =textFieldNom.getText();
        String prenom = textFieldPrenom.getText();
        String email = textFieldEmail.getText();
        String pass = textFieldPass.getText();
        
        try{
            tel = Integer.parseInt(textFieldTel.getText());
        }catch(NumberFormatException ex){
            System.out.println("le numero de tel doit être un entier ");
         return;
        }
        
        
        String image = textFieldImage.getText();
        
        
        
        
        User u7 =new User(nom, prenom, email, pass, tel, image);
        UserService.insert(u7);
       
        Refresh();
       
    }

    private void OnUpdate(ActionEvent event) {
         int tel;
         int id;
        String nom =textFieldNom.getText();
        String prenom = textFieldPrenom.getText();
        String email = textFieldEmail.getText();
        String pass = textFieldPass.getText();
       
        
        try{
            tel = Integer.parseInt(textFieldTel.getText());
        }catch(NumberFormatException ex){
            System.out.println("le numero de tel doit être un entier ");
         return;
        }
        
        
        String image = textFieldImage.getText();
         try{
            id = Integer.parseInt(textFieldId.getText());
        }catch(NumberFormatException ex){
            System.out.println("  doit être un entier ");
         return;
        }
        
        String role = textFieldRole.getText();
        
        User u7 =new User(id,nom, prenom, email, pass, tel, image,role);
        UserService.update(u7);
        
       
    Refresh();
        
        
       
    }

    private void OnDelete(ActionEvent event) {
        User u3= null;
        u3=tvUser.getSelectionModel().getSelectedItem();
        UserService.delete(u3);
        Refresh();
    }

    private void Refresh() {
        ListM.clear();
        ListM.addAll(UserService.getAll());
        tvUser.setItems(ListM);
     
       
}

    @FXML
    private void onMouseClicked(MouseEvent event) {
        
    int tel;
     User user= tvUser.getSelectionModel().getSelectedItem();
        textFieldNom.setText(user.getNom());
        textFieldPrenom.setText(user.getPrenom());
        textFieldEmail.setText(user.getEmail());
        textFieldPass.setText(user.getPass());
        textFieldTel.setText(""+user.getTel());
        textFieldImage.setText(user.getImage());
        textFieldId.setText(""+user.getId());
        textFieldRole.setText(user.getRole());
        
    }

    
    
}
    
    

