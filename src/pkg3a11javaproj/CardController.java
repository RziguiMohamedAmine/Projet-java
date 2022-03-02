/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg3a11javaproj;

import entite.Order;
import entite.OrderItem;
import entite.Product;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.OrderItemService;

/**
 * FXML Controller class
 *
 * @author Ghazi
 */
public class CardController {

    @FXML
    private Button addToCartBtn;

    @FXML
    private VBox box;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;





    public void setCardData(Product product, Order order, TableView<OrderItem> cart){
        productImage.setImage(new Image(getClass().getResourceAsStream(product.getImage())));
        productName.setText(product.getName());
        productPrice.setText("TND " + String.valueOf(product.getPrice()));
        
        addToCartBtn.setOnAction(event -> {
            OrderItemService orderItemService = new OrderItemService();
            OrderItem orderItem = new OrderItem(order, product, 1);
            orderItemService.insert(orderItem);
            if ( !cart.getItems().contains(orderItem))
                    cart.getItems().add(orderItem);
            else {
                for (OrderItem o : cart.getItems())
                    if (orderItem.equals(o))
                        o.incrementQuantity();
                cart.refresh();
            }
            
        });
    }
    
}
