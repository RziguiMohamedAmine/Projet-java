/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg3a11javaproj;

import entite.Order;
import entite.OrderItem;
import entite.produit;

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





    public void setCardData(produit produit, Order order, HBox motherHBox, TableView<OrderItem> cart){
        productImage.setImage(new Image(getClass().getResourceAsStream(produit.getImage())));
        productName.setText(produit.getNom());
        productPrice.setText("TND " + String.valueOf(produit.getPrix()));
        
          productName.setStyle("-fx-font-weight: bold");
        
        addToCartBtn.setOnAction(event -> {   
            OrderItemService orderItemService = new OrderItemService();
            OrderItem orderItem = new OrderItem(order, produit, 1);
            orderItemService.insert(orderItem);
            //System.out.println(cart.getItems().);
            if ( !cart.getItems().contains(orderItem))
                    cart.getItems().add(orderItem);
            else {
                for (OrderItem o : cart.getItems())
                    if (orderItem.equals(o))
                        o.incrementQuantity();
                
            }
            motherHBox.getChildren().removeIf(e->e.getId().equals(String.valueOf(produit.getId())));
            cart.refresh();
        });
        addToCartBtn.setStyle("-fx-background: none;"+"-fx-cursor: hand;"+"-fx-text-fill: #32a852;"+"-fx-border: none;"+"-fx-padding: 0;");
        
    }
    
}
