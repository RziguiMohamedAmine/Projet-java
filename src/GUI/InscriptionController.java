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
import java.sql.Timestamp;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.UserService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPass;
    @FXML
    private TextField textFieldTel;
    @FXML
    private TextField textFieldRole;
    private UserService UserService;
    @FXML
    private TableView<User> tvUser;
    @FXML
    private TableColumn<User, String> colNom;
    @FXML
    private TableColumn<User, String> colPrenom;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colPass;
    @FXML
    private TableColumn<User, Integer> colTel;
    @FXML
    private TableColumn<User,Integer> colId;
    @FXML
    private TableColumn<User,String> colRole;
    
    ObservableList<User> ListM = FXCollections.observableArrayList();
    int index = -1 ;
    Connection conn= null;
    Resultset rs= null;
    PreparedStatement pst= null;
    
    @FXML
    private Button BtnOk;
    @FXML
    private Button BtnUpdate;
    @FXML
    private Button BtnDelete;
    @FXML
    private DatePicker dat;
    @FXML
    private Parent fxml;
    private Stage stage;
    private Scene scene;

    

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
        colId.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        colRole.setCellValueFactory(new PropertyValueFactory<User,String>("role"));
       ListM.addAll(UserService.getAll());
       tvUser.setItems(ListM);
    
       
       
    }    

    @FXML
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
        if(nom.isEmpty()||email.isEmpty()||pass.isEmpty()||prenom.isEmpty())
        {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs obligatoires ");
            alert.showAndWait(); 
        }
        else
        {      
        User u7 =new User(nom, prenom, email, pass, tel);
        UserService.insert(u7);
       
        Refresh();
        }
        
       
       
    }

    @FXML
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
        
         
        
        String role = textFieldRole.getText();
        if(nom.isEmpty()||email.isEmpty()||prenom.isEmpty())
        {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs obligatoires ");
            alert.showAndWait(); 
        }
        else
        {
        User u7 =new User(nom, prenom, email, pass, tel,role);
        UserService.update(u7);
        
       
    Refresh();
        }
        
       
    }

    @FXML
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
        textFieldRole.setText(user.getRole());
        
    }

    @FXML
    private void block(ActionEvent event) {
        User u3= null;
        u3=tvUser.getSelectionModel().getSelectedItem();
        UserService.block(dat.getValue(), u3);
        Refresh();
    }

    @FXML
    private void onBan(ActionEvent event) {
        User u3= null;
        u3=tvUser.getSelectionModel().getSelectedItem();
        UserService.ban(u3);
        Refresh();
    }

    @FXML
    private void OnAccueil(ActionEvent event) {
         try {
            fxml = FXMLLoader.load(getClass().getResource("home.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxml);
            stage.setScene(scene);
            stage.show();
       
        
    }
    

    
    
}
    
    

