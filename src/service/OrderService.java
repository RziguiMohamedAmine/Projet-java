package service;

import entite.Order;
import entite.State;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderService implements IService<Order> {
    private Connection cnx;
    private PreparedStatement pst;
    public OrderService() {
        cnx = DataSource.getInstance().getCnx();
    }


    @Override
    public Boolean insert(Order order) {
        String req = "insert into orders (user_id) values (?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1,order.getUserId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(Order order) {
        String req = "update orders set state=? where id=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1,order.getState());
            pst.setInt(2,order.getId());
            pst.executeUpdate();
            System.out.println("Record updated");

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    @Override
    public Boolean delete(Order order) {
        String req = "delete from orders where id=?"; //delete order
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1,order.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public List<Order> getAll() {
        String req="select * from orders";
        List<Order> ordersList=new ArrayList<>();
        try {
            pst= cnx.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                ordersList.add(new Order(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ordersList;
    }

    @Override
    public Order getOne(int id) {
        String req="select * from orders where id=?";
        try {
            pst= cnx.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet rs= pst.executeQuery();
            if(rs.next()) {
                Order order = new Order(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4));
                return order;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Order> getUserOrders(int userId,String state) {
        List<Order> userOrdersList=new ArrayList<>();
        String req="select * from orders where user_id=? and lower(state)=lower(?)";
        try {
            pst= cnx.prepareStatement(req);
            pst.setInt(1,userId);
            pst.setString(2,state);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                userOrdersList.add(new Order(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userOrdersList;
    }

    public Order getOrInitUserCart(int userId){
        Order order = new Order();
        if (getUserOrders(userId, State.inCart.toString()).isEmpty()) {
            order.setUserId(userId);
            insert(order);
        }
        return getUserOrders(userId,State.inCart.toString()).get(0); // reparcourir la BD pour extraire le champ orderID (PK autoincrement)
    }

    public void placeOrder(Order order){
        OrderItemService orderItemService = new OrderItemService();
        if (orderItemService.getOrderItems(order.getId()).isEmpty())
            System.out.println("Cannot proceed : empty cart!");
        else {
            order.setState(State.placed.toString());
            update(order);
            getOrInitUserCart(order.getUserId());
        }
    }

}
