/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg3a11javaproj;

import java.net.URL;
import java.util.ResourceBundle;

import entite.OrderItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Ghazi
 */
public class OrderCardController  {

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label quantity;

    @FXML
    private Label price;

    public void setData(OrderItem orderItem){
        image.setImage(new Image(getClass().getResourceAsStream(orderItem.getProduct().getImage())));
        name.setText(orderItem.getProduct().getName());
        quantity.setText(String.valueOf(orderItem.getQuantity()));
        price.setText(String.valueOf(orderItem.getProduct().getPrice()));
    }

}
