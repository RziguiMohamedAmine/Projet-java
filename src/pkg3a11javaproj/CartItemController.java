/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg3a11javaproj;

import entite.OrderItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import service.OrderItemService;

/**
 * FXML Controller class
 *
 * @author Ghazi
 */
public class CartItemController  {

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Button deleteBtn;

    @FXML
    private Spinner<Integer> quantity;

    @FXML
    private Label subtotal;
    
    public void setData(OrderItem orderItem,VBox shopppingCartVBox){
                image.setImage(new Image(getClass().getResourceAsStream(orderItem.getProduct().getImage())));
                name.setText(orderItem.getProduct().getNom());
                price.setText("TND "+String.valueOf(orderItem.getProduct().getPrix()));
                subtotal.setText("TND "+String.valueOf(orderItem.getQuantity()*orderItem.getProduct().getPrix()));
                
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,orderItem.getProduct().getStock());
        valueFactory.setValue(orderItem.getQuantity());
        quantity.setValueFactory(valueFactory);
        
        
        OrderItemService orderItemService = new OrderItemService();
        quantity.valueProperty().addListener((obs, oldValue, newValue) -> 
        {
            orderItem.setQuantity(newValue);
            orderItemService.update(orderItem);
            subtotal.setText("TND "+ String.valueOf(orderItem.getQuantity()*orderItem.getProduct().getPrix()));
            //List<OrderItem> orderItemsList= new ArrayList<>();
            //orderItemsList=orderItemService.getOrderItems(orderItem.getOrder().getId());
           /*float total=0;
            for (int i= 0;i<orderItemsList.size();i++)
                total += orderItemsList.get(i).getProduct().getPrice()*orderItemsList.get(i).getQuantity();
        
        totalPrice.setText("TND "+String.valueOf(total));*/
        });
        
        deleteBtn.setOnAction(event -> {
                                        orderItemService.delete(orderItem);
                                        shopppingCartVBox.getChildren().removeIf(e->e.getId().equals(String.valueOf(orderItem.getProduct().getId())));
                                    });
            
    }
}
