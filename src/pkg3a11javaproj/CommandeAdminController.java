/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg3a11javaproj;

import entite.Order;
import entite.State;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import service.OrderService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Ghazi
 */
public class CommandeAdminController implements Initializable{
     @FXML
    private VBox allUsersOrdersVBOX;

     private  OrderService orderService = new OrderService();
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        List<User> usersList = new ArrayList<>();
        UserService userService = new UserService();
        usersList=userService.getAll();
        
        //usersList.add(71);
       // usersList.add(5);
        
        if (!usersList.isEmpty())
        {
            try{

                for (int i=0; i<usersList.size();i++){
                    if (!orderService.getUserOrders(usersList.get(i).getId(), State.placed.toString()).isEmpty())
                    {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("adminVbox.fxml"));
                    VBox cardBox = fxmlLoader.load();
                    new AdminVboxController();
                    AdminVboxController cardController = fxmlLoader.getController();
                    cardController.setData(usersList.get(i).getId());
                    allUsersOrdersVBOX.getChildren().add(cardBox);
                    }
                    
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
