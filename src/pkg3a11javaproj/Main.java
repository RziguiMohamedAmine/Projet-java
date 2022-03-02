/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package pkg3a11javaproj;

import entite.Order;
import entite.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.OrderItemService;
import service.OrderService;

/**
 *
 * @author Ghazi
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("newCart.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        OrderService orderService = new OrderService();
        User user = new User(4567);

        Order order = new Order();
        order = orderService.getOrInitUserCart(user);
        System.out.println(order);


        OrderItemService orderItemService = new OrderItemService();
        //orderItemService.insert(new OrderItem(order.getId(),1,1));
        //orderItemService.insert(new OrderItem(order.getId(),2,2));
        //orderItemService.insert(new OrderItem(order.getId(),3,1));


        System.out.println("Shopping Cart :"+orderItemService.getOrderItems(order.getId()));
        //orderService.placeOrder(order);
        System.out.println("Best Sellers :"+orderItemService.getBestSellers(4));
        orderItemService.basketMarketAnalysis(orderItemService.getOrderItems(order.getId()));
    }
    
}
