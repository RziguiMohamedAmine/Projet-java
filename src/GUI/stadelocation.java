package GUI;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.sun.prism.PhongMaterial.MapType;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MarkerOptions;
import entite.Equipe;
import entite.Joueur;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import service.EquipeService;
import service.JoueurService;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.MapView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author moham
 */
public class stadelocation extends Application  {

     EquipeService es=new EquipeService();
      JoueurService js=new JoueurService();
      ObservableList<Joueur> joueurList = FXCollections.observableArrayList(); 
      Joueur joueur=null;
      Equipe e=new Equipe();
      
//    @FXML
//    GoogleMapView emapView;
//    
//    GoogleMap mapp;

    @Override
    public void start(Stage primaryStage) throws Exception {
       
        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        options.setApiKey("<AIzaSyACxUxAnBV3YDGW09ZUit6nx9Qk7H1OTNc>");
        MapView mapView = new MapView(options);

        mapView.setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
        if (status == MapStatus.MAP_STATUS_OK) {
                    final Map map = mapView.getMap();
                    map.setZoom(5.0);
                    GeocoderRequest request = new GeocoderRequest();                
                    request.setLocation(new LatLng(36.7480,10.2727));

                    mapView.getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());

                                final InfoWindow window = new InfoWindow(map);
                                window.setContent("Hello, World!");
                                window.open(map, marker);
                            }
                        }
                    });
                }
            }
        });

        Scene scene = new Scene(new BorderPane(mapView), 700, 500);
        primaryStage.setTitle("JxMaps - Hello, World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
      
   

    
}
