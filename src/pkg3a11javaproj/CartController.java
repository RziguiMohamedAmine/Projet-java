/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package pkg3a11javaproj;

import entite.*;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import service.OrderItemService;
import service.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import service.ServiceProduit;

/**
 *
 * @author Ghazi
 */
public class CartController implements Initializable {
    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Integer> orderIdCol;

    @FXML
    private TableColumn<Order, Order> orderProductCol;

    @FXML
    private TableColumn<Order, Order> orderDateCol;
    
    
    @FXML
    private Button button;

    @FXML
    private Label label;

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

    TableColumn actionCol = new TableColumn("Action");


    //--- best sellers
    @FXML
    private VBox cardLayout;
    @FXML
    private VBox recommendedProductsLayout;
    @FXML
    private VBox ordersVBox;

    @FXML
    private void handlePasserCommande(ActionEvent event) {
        orderService.placeOrder(order);
        button.setVisible(false);
        cart.getItems().clear();
        totalPrice.setText("0.0");
        totalPrice.setVisible(false);
        totalPrice.setVisible(true);
    }

    private OrderItemService orderItemService;
    private OrderService orderService;
    private User user;
    private Order order;

    private void bestSellersUI(){
        List<Product> bestSellersList = new ArrayList<>();

        bestSellersList=orderItemService.getBestSellers(3);
        if (!bestSellersList.isEmpty())
        {
            try{

                for (int i=0; i<bestSellersList.size();i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                    VBox cardBox = fxmlLoader.load();
                    new CardController();
                    CardController cardController = fxmlLoader.getController();
                    cardController.setCardData(bestSellersList.get(i),order,cart);
                    cardLayout.getChildren().add(cardBox);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    private void ordersUI(){
        List<Order> ordersList = new ArrayList<>();
        ordersList=orderService.getUserOrders(user.getId(),State.placed.toString());
        if (!ordersList.isEmpty())
        {
            try{

                for (int i=0; i<ordersList.size();i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("OrderVbox.fxml"));
                    VBox cardBox = fxmlLoader.load();
                    new OrderVboxController();
                    OrderVboxController cardController = fxmlLoader.getController();
                    cardController.setData(ordersList.get(i));
                    ordersVBox.getChildren().add(cardBox);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void recommandedProductsUI(){
        orderItemService.basketMarketAnalysis(orderItemService.getOrderItems(order.getId()));
        List<Product> recommendedProductsList = new ArrayList<>();
        recommendedProductsList= orderItemService.basketMarketAnalysis(orderItemService.getOrderItems(order.getId()));
        if (!recommendedProductsList.isEmpty())
        {
            try{

                for (int i=0; i<recommendedProductsList.size();i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                    VBox cardBox = fxmlLoader.load();
                    new CardController();
                    CardController cardController = fxmlLoader.getController();
                    cardController.setCardData(recommendedProductsList.get(i),order,cart);
                    recommendedProductsLayout.getChildren().add(cardBox);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    
    private void shoppingCartUI(){
        productColumn();
        quantityColumn();
        deleteButtonColumn();
        priceColumn();
    }
    private void productColumn(){
        product.setCellFactory(param -> {
            VBox vBox = new VBox();
            ImageView imageview = new ImageView();
            imageview.setFitHeight(125);
            imageview.setPreserveRatio(true);
            imageview.setSmooth(true);
            imageview.setCache(true);
            Label label = new Label();
            label.setStyle("-fx-font-weight: bold");
            vBox.getChildren().addAll(label,imageview);

            TableCell<OrderItem, OrderItem> cell = new TableCell<OrderItem, OrderItem>() {
                public void updateItem(OrderItem item, boolean empty) {
                    super.updateItem(item,empty);
                    if (!empty) { 
                        label.setText(getTableView().getItems().get(getIndex()).getProduct().getName());
                        imageview.setImage(new Image(getClass().getResourceAsStream(getTableView().getItems().get(getIndex()).getProduct().getImage()))); 
                    }
                    else {
                        imageview.setImage(null);
                        label.setText("");
                    }
                }
            };
            cell.setGraphic(vBox);
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
                        count.setStyle("-fx-width: 150px;");
                        count.setVisible(false);
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
                                    total += cart.getItems().get(i).getProduct().getPrice()*cart.getItems().get(i).getQuantity();
                                }
                                totalPrice.setText("TND " + String.valueOf(total));
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
                            valueFactory.setMax(item.getProduct().getQuantity());
                            valueFactory.setValue(item.getQuantity());
                            count.setVisible(true);
                        }

                    }
                };
                return cell;
            }
        });
    }
    
    private void deleteButtonColumn(){
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        actionCol.setSortable(false);
        
        Callback<TableColumn<OrderItem, String>, TableCell<OrderItem, String>> cellFactory
                = //
                new Callback<TableColumn<OrderItem, String>, TableCell<OrderItem, String>>() {
                    @Override
                    public TableCell call(final TableColumn<OrderItem, String> param) {
                        final TableCell<OrderItem, String> cell = new TableCell<OrderItem, String>() {

                            final Button btn = new Button("Delete");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        OrderItem orderItem = getTableView().getItems().get(getIndex());
                                        OrderItemService orderItemService = new OrderItemService();
                                        orderItemService.delete(orderItem);
                                        cart.getItems().remove(orderItem);

                                        float total=0;
                                        for (int i= 0;i<cart.getItems().size();i++){
                                            total += cart.getItems().get(i).getProduct().getPrice()*cart.getItems().get(i).getQuantity();
                                        }
                                        totalPrice.setText("TND "+String.valueOf(total));
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionCol.setCellFactory(cellFactory);
        cart.getColumns().addAll(actionCol);
    }
    
    private void priceColumn(){
         price.setCellValueFactory(tf->new SimpleFloatProperty(tf.getValue().getQuantity()*tf.getValue().getProduct().getPrice()).asObject());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         button.setOnAction(this::handlePasserCommande);
         orderItemService = new OrderItemService();
         orderService = new OrderService();
         user = new User(4567);
         
         order = new Order();
        order = orderService.getOrInitUserCart(user);
        
       ServiceProduit serviceProduit = new ServiceProduit();
         ObservableList<OrderItem> observableList = FXCollections.observableArrayList(orderItemService.getOrderItems(order.getId()));

        System.out.println("Shopping Cart :"+orderItemService.getOrderItems(order.getId()));
        System.out.println("Best Sellers :"+orderItemService.getBestSellers(4));
        
        
        cart.setEditable(true);
        

        bestSellersUI();
        recommandedProductsUI();
        ordersUI();
        shoppingCartUI();
       




        /*price.setCellValueFactory(cellData -> {
            String formattedCost = currency.format(cellData.getValue().getQuantity()*cellData.getValue().getProduct().getPrice());
            return new SimpleStringProperty(formattedCost);
        });*/


        cart.setItems(observableList);

        float total=0;
        for (int i= 0;i<cart.getItems().size();i++){
            total += cart.getItems().get(i).getProduct().getPrice()*cart.getItems().get(i).getQuantity();
        }
        totalPrice.setText("TND " + String.valueOf(total));






    }



}
