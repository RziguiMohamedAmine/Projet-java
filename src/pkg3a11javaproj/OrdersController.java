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
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import service.OrderItemService;
import service.OrderService;

public class OrdersController implements  Initializable{

    @FXML
    private VBox ordersVBox;
    
    private OrderService orderService = new OrderService();
    private OrderItemService orderItemService = new OrderItemService();
    private User user;
    private Order order;

    private void ordersUI(){
        List<Order> ordersList = new ArrayList<>();
        ordersList=orderService.getUserOrders(user.getId(),State.placed.toString());
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
                    ordersVBox.getChildren().add(cardBox);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = new User(4567);
        ordersUI();
    }

    
    
}
