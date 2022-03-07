/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg3a11javaproj;

import entite.Order;
import entite.State;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.OrderService;

/**
 * FXML Controller class
 *
 * @author Ghazi
 */
public class AdminVboxController {
    @FXML
    private HBox userhbox;
    
    @FXML
    private HBox hboxtoggle;
    
    @FXML
    private VBox userVbox;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label userID;

    @FXML
    private VBox userOrdersVbox;
    
    
    public void setData(Integer userId){
        userID.setText("UID :#"+String.valueOf(userId));
        hboxtoggle.setVisible(false);
        hboxtoggle.setManaged(false);
        OrderService orderService= new OrderService();
        List<Order> ordersList = new ArrayList<>();
        ordersList=orderService.getUserOrders(userId,State.placed.toString());
        if (!ordersList.isEmpty())
        {
            try{

                for (int i=0; i<ordersList.size();i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("OrderVbox.fxml"));
                    VBox cardBox = fxmlLoader.load();
                    new OrderVboxController();
                    OrderVboxController cardController = fxmlLoader.getController();
                    cardController.setData(ordersList.get(i));
                    userOrdersVbox.getChildren().add(cardBox);
                    
                    userhbox.setOnMouseClicked(e->{
                        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        hboxtoggle.setManaged(!hboxtoggle.isManaged());
                        hboxtoggle.setVisible(!hboxtoggle.isVisible());
                    
                });
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        
    }
}
