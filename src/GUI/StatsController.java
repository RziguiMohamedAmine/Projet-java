/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Arbitres;
import javafx.event.EventHandler;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javafx.util.Duration;
import utils.DataSource;
/**
 * FXML Controller class
 *
 * @author R I B
 */
public class StatsController implements Initializable {

    @FXML
    private PieChart pc1;
    
         ObservableList <PieChart.Data> ol = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  try {
           
        //      String requete = "SELECT COUNT(id_role)  ,role FROM role r ,arbitre a  WHERE a.id_role=r.id GROUP BY role";
        String requete = "SELECT arbitre.id_role, Count(*) AS Nombre_de_Fois FROM arbitre GROUP BY arbitre.id_role";
     //  String requete = "SELECT  sum(carton_jaune) as cj , sum(carton_rouge) "
       //        + "as cr from matchjoueur where id_match in ( select id from matchs where id_arbitre1=?) ";

       // String requete = "SELECT arbitre.id_role, Count(*) AS Nombre_de_Fois FROM arbitre GROUP BY arbitre.id_role";
    // String requete = "SELECT  role.role ,COUNT(*) AS Nombre_de_Fois FROM role  GROUP BY role.role";
       PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
       // ResultSet rs =  pst.executeQuery(requete);
       
       ResultSet rs =  pst.executeQuery(requete);
    
        
     
        while(rs.next()){
            
                    ol.addAll(new PieChart.Data(rs.getString(1),rs.getInt(2)));
                    pc1.setData(ol);
                    
                    pc1.setLegendSide(Side.LEFT);
                    
                    FadeTransition f = new FadeTransition(Duration.seconds(6),pc1);
                  
                    f.setFromValue(0);
                    f.setToValue(1);
                    f.play();
        }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                     for (PieChart.Data data : pc1.getData())
                     {
                         data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {
                             
                             public void handle(MouseEvent event) {
                               JOptionPane.showMessageDialog(null,"les roles   -- "+ data.getName()+ "nombre des id --" +(int)data.getPieValue());   
                             }
                         });
                     }
                     
                     
        // TODO
    }    

    void setButton() {
    Arbitres a = new Arbitres();
    a.getId();
    }
    
}
