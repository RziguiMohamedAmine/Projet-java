/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg3a11javaproj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entite.Order;
import entite.OrderItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.OrderItemService;

/**
 * FXML Controller class
 *
 * @author Ghazi
 */
public class OrderVboxController  {

    @FXML
    private VBox orderDetailsVbox;
    
    @FXML
    private Label id;

    @FXML
    private Label price;
    
    @FXML
    private Label date;

    @FXML
    private HBox orderItemsHBox;
    private String dateToString(Order order){
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");  
        return dateFormat.format(order.getDate());  
    }
    public void setData(Order order){
        id.setText("#"+String.valueOf(order.getId()));
        date.setText(dateToString(order));
        float sum=0; // total price
        orderItemsHBox.setVisible(false);
        orderItemsHBox.setManaged(false);
        OrderItemService orderItemService = new OrderItemService();
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList=orderItemService.getOrderItems(order.getId());
        if (!orderItemList.isEmpty())
        {
            try{

                for (int i=0; i<orderItemList.size();i++){
                    sum+=orderItemList.get(i).getQuantity()*orderItemList.get(i).getProduct().getPrix();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("OrderItemVBox.fxml"));
                    VBox cardBox = fxmlLoader.load();
                    new OrderItemVBoxController();
                    OrderItemVBoxController cardController = fxmlLoader.getController();
                    cardController.setData(orderItemList.get(i));
                    orderItemsHBox.getChildren().add(cardBox);
                    
                    orderDetailsVbox.setOnMouseClicked(e->{
                        orderItemsHBox.setManaged(!orderItemsHBox.isManaged());
                        orderItemsHBox.setVisible(!orderItemsHBox.isVisible());
                    });
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        price.setText("TND " + sum);
    }
}