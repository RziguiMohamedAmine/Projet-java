/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author moham
 */

public class MainFx extends Application {
    double x,y;
    @Override
    public void start(Stage primaryStage) {
          Parent root;
        
        try {
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                primaryStage.initStyle(StageStyle.UNDECORATED);
                root.setOnMousePressed(event->{
                x=event.getSceneX();
                y=event.getSceneY();
                
                });
                root.setOnMouseDragged(event->{
                primaryStage.setX(event.getScreenX()-x);
                primaryStage.setY(event.getSceneY()-y);
                });
            Scene scene = new Scene(root,1700,1000);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            
            primaryStage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
