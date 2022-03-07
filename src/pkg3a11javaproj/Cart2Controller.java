/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg3a11javaproj;

import entite.*;
import entite.produit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import service.OrderItemService;
import service.OrderService;
import service.ServiceProduit;
import service.UserService;

public class Cart2Controller implements Initializable{

    
    @FXML
    private ScrollPane bestSellersScrollPane;
    
    @FXML
    private ScrollPane motherScrollPane;

    @FXML
    private VBox motherVbox;
    
     @FXML
    private TableView<OrderItem> cart;

    @FXML
    //private TableColumn<OrderItem, Integer> product;
    private TableColumn<OrderItem, OrderItem> product;
    @FXML
    private Label totalPrice;

    @FXML
    private TableColumn<OrderItem, OrderItem> quantity;
    //TableColumn quantity = new TableColumn("Quantity");
    @FXML
    //private TableColumn<OrderItem, String> price;
    private TableColumn<OrderItem, Float> price;

  
    @FXML
    private Button placeOrderBTN;
    
    @FXML
    private VBox shopppingCartVBox;

    @FXML
    private HBox bestSellersHBox;

    @FXML
    private HBox recommendedProductsHBox;

    private OrderItemService orderItemService;
    private OrderService orderService;
    private User user;
    private Order order;
    
    
    private void bestSellersUI(){
        List<produit> bestSellersList = new ArrayList<>();

        bestSellersList=orderItemService.getBestSellers(5);
        Iterator<produit> iter = bestSellersList.iterator();
        if (!bestSellersList.isEmpty())
        {
            try{

                for (int i=0; i<bestSellersList.size();i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                    VBox cardBox = fxmlLoader.load();
                     cardBox.setId(String.valueOf(bestSellersList.get(i).getId()));
                    new CardController();
                    CardController cardController = fxmlLoader.getController();
                    
                    cardController.setCardData(bestSellersList.get(i),order,bestSellersHBox,cart);
                    bestSellersHBox.getChildren().add(cardBox);
                    cart.refresh();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    private void recommandedProductsUI(){
        orderItemService.basketMarketAnalysis(orderItemService.getOrderItems(order.getId()));
        List<produit> recommendedProductsList = new ArrayList<>();
        recommendedProductsList= orderItemService.basketMarketAnalysis(orderItemService.getOrderItems(order.getId()));
        if (!recommendedProductsList.isEmpty())
        {
            try{

                for (int i=0; i<recommendedProductsList.size();i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                    VBox cardBox = fxmlLoader.load();
                    cardBox.setId(String.valueOf(recommendedProductsList.get(i).getId()));
                    new CardController();
                    CardController cardController = fxmlLoader.getController();
                    cardController.setCardData(recommendedProductsList.get(i),order,recommendedProductsHBox,cart);
                    recommendedProductsHBox.getChildren().add(cardBox);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
   private void handlePasserCommande(ActionEvent event) {
        orderService.placeOrder(order);
        placeOrderBTN.setVisible(false);
        cart.getItems().clear();
        totalPrice.setText("0.0");
        totalPrice.setVisible(false);
        totalPrice.setVisible(true);
    }
    
    
    private void shoppingCartUI(){
        productColumn();
        quantityColumn();
        priceColumn();
    }
    
    
    private void productColumn(){
        product.setCellFactory(param -> {
            HBox hBox = new HBox();
            VBox vbox = new VBox();
            ImageView imageview = new ImageView();
            imageview.setFitWidth(150);
            imageview.setPreserveRatio(true);
            imageview.setSmooth(true);
            imageview.setCache(true);
            Label name = new Label();
            final Button deleteBtn = new Button("Supprimer");
            deleteBtn.setStyle("-fx-background: none;"+"-fx-cursor: hand;"+"-fx-text-fill: #ab2118;"+"-fx-border: none;"+"-fx-padding: 0;");
            name.setStyle("-fx-font-weight: bold");
            Label price = new Label();
            
            vbox.getChildren().addAll(name,price,deleteBtn);
            hBox.getChildren().addAll(imageview,vbox);

            TableCell<OrderItem, OrderItem> cell = new TableCell<OrderItem, OrderItem>() {
                public void updateItem(OrderItem item, boolean empty) {
                    super.updateItem(item,empty);
                    if (!empty) { 
                        name.setText(getTableView().getItems().get(getIndex()).getProduct().getNom());
                        price.setText("TND "+String.valueOf(getTableView().getItems().get(getIndex()).getProduct().getPrix()));
                        imageview.setImage(new Image(getClass().getResourceAsStream(getTableView().getItems().get(getIndex()).getProduct().getImage()))); 
                        
                        
                        //btn 
                        if (!cart.getItems().isEmpty())
                                            placeOrderBTN.setVisible(true);
                        deleteBtn.setOnAction(event -> {
                                        OrderItem orderItem = getTableView().getItems().get(getIndex());
                                        OrderItemService orderItemService = new OrderItemService();
                                        orderItemService.delete(orderItem);
                                        cart.getItems().remove(orderItem);

                                        float total=0;
                                        for (int i= 0;i<cart.getItems().size();i++){
                                            total += cart.getItems().get(i).getProduct().getPrix()*cart.getItems().get(i).getQuantity();
                                        }
                                        totalPrice.setText("Prix Total: TND "+String.valueOf(total));
                                        if (cart.getItems().isEmpty())
                                            placeOrderBTN.setVisible(false);
                                    });
                        setGraphic(hBox);
                    }
                    else {
                        setGraphic(null);
                        //imageview.setImage(null);
                        //name.setText("");
                       // price.setText("");
                       
                    }
                }
            };
           // cell.setGraphic(hBox);
            return cell;
        });
        product.setCellValueFactory(new PropertyValueFactory<OrderItem, OrderItem>("randomString??"));
    }
    
    private void quantityColumn(){
        quantity.setCellValueFactory(cd -> Bindings.createObjectBinding(() -> cd.getValue()));

        quantity.setCellFactory(new Callback<TableColumn<OrderItem, OrderItem>, TableCell<OrderItem, OrderItem>>() {
            @Override
            public TableCell<OrderItem, OrderItem> call(TableColumn<OrderItem, OrderItem> param) {
                TableCell<OrderItem, OrderItem> cell = new TableCell<OrderItem, OrderItem>() {

                    private final Spinner<Integer> count;
                    private final SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory;
                    private final ChangeListener<Number> valueChangeListener;

                    {
                        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0);
                        count = new Spinner<>(valueFactory);
                        count.setVisible(false);
                        count.setStyle("-fx-pref-width: 50px;");
                        setGraphic(count);
                        valueChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                            valueFactory.setValue(newValue.intValue());
                        };
                        count.valueProperty().addListener((obs, oldValue, newValue) -> {
                            if (getItem() != null) {
                                OrderItem orderItem = getTableView().getItems().get(getIndex());
                                orderItem.setQuantity(newValue);

                                orderItemService.update(orderItem);


                                getItem().setQuantity(newValue);
                                cart.getColumns().get(getIndex()).setVisible(false); //Update price Cell
                                cart.getColumns().get(getIndex()).setVisible(true);
                                float total=0;
                                for (int i= 0;i<cart.getItems().size();i++){
                                    total += cart.getItems().get(i).getProduct().getPrix()*cart.getItems().get(i).getQuantity();
                                }
                                totalPrice.setText("Prix Total: TND " + String.valueOf(total));
                            }
                        });
                    }

                    @Override
                    public void updateItem(OrderItem item, boolean empty) {
                        valueFactory.maxProperty().unbind();
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            count.setVisible(false);
                        } else {
                            valueFactory.setMin(1);
                            valueFactory.setMax(item.getProduct().getStock());
                            valueFactory.setValue(item.getQuantity());
                            count.setVisible(true);
                        }

                    }
                };
                return cell;
            }
        });
    }
    
   
    
    private void priceColumn(){
         price.setCellValueFactory(tf->new SimpleFloatProperty(tf.getValue().getQuantity()*tf.getValue().getProduct().getPrix()).asObject());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderItemService = new OrderItemService();
         orderService = new OrderService();
         user = new User();
         UserService userService = new UserService();
         user = userService.getOne(4567);
         //System.out.println(user);
         order = new Order();
        order = orderService.getOrInitUserCart(user);
        bestSellersUI();
        recommandedProductsUI();
        shoppingCartUI();
        placeOrderBTN.setOnAction(this::handlePasserCommande);
        
        ServiceProduit serviceProduit = new ServiceProduit();
         ObservableList<OrderItem> observableList = FXCollections.observableArrayList(orderItemService.getOrderItems(order.getId()));
         cart.setEditable(true);
         cart.setItems(observableList);
         if (cart.getItems().isEmpty())
             placeOrderBTN.setVisible(false);
        float total=0;
        for (int i= 0;i<cart.getItems().size();i++){
            total += cart.getItems().get(i).getProduct().getPrix()*cart.getItems().get(i).getQuantity();
        }
        totalPrice.setText("Prix Total: TND " + String.valueOf(total));
        
        motherVbox.prefWidthProperty().bind(motherScrollPane.widthProperty());
        motherVbox.prefHeightProperty().bind(motherScrollPane.heightProperty());
        
        cart.setFixedCellSize(100);
       
    cart.prefHeightProperty().bind(cart.fixedCellSizeProperty().multiply(Bindings.size(cart.getItems()).add(1.01)));
    cart.minHeightProperty().bind(cart.prefHeightProperty());
    cart.maxHeightProperty().bind(cart.prefHeightProperty());
  
    
    product.prefWidthProperty().bind(cart.widthProperty().divide(2)); // w * 1/4
quantity.prefWidthProperty().bind(cart.widthProperty().divide(4)); // w * 1/2
price.prefWidthProperty().bind(cart.widthProperty().divide(4)); // w * 1/4
    
    bestSellersScrollPane.minHeightProperty().bind(bestSellersHBox.heightProperty());
    }

}
