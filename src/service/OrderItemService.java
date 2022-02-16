package service;

import entite.OrderItem;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderItemService implements IService<OrderItem>{
    private Connection cnx;
    private PreparedStatement pst;
    public OrderItemService() {
        cnx = DataSource.getInstance().getCnx();
    }


    @Override
    public Boolean insert(OrderItem item) {
        if (!getAll().contains(item)){ // if it doesn't exist then insert it
            String req = "insert into order_items (order_id,product_id,quantity) values (?,?,?)";
            try {
                pst = cnx.prepareStatement(req);
                pst.setInt(1,item.getOrderId());
                pst.setInt(2,item.getProductId());
                pst.setInt(3,item.getQuantity());
                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
        }
        return update(item); // else update quantity
    }

    @Override
    public Boolean update(OrderItem item) {
        String req = "update order_items set quantity=? where order_id=? and product_id=?";
        try {

            pst = cnx.prepareStatement(req);
            pst.setInt(1,item.getQuantity());
            pst.setInt(2,item.getOrderId());
            pst.setInt(3,item.getProductId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    @Override
    public Boolean delete(OrderItem item) {
        String req = "delete from order_items where order_id=? and product_id=?"; //remove product from cart
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1,item.getOrderId());
            pst.setInt(2,item.getProductId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public List<OrderItem> getAll() {
        String req="select * from order_items";
        List<OrderItem> itemsList=new ArrayList<>();

        try {
            pst= cnx.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                itemsList.add(new OrderItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemsList;
    }

    @Override
    public OrderItem getOne(int id) {
        String req="select * from order_items where id=?";
        try {
            pst= cnx.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet rs= pst.executeQuery();
            if(rs.next()) {
                OrderItem item = new OrderItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
                return item;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public List<OrderItem> getOrderItems(int orderId) {
        String req="select * from order_items where order_id=?";
        List<OrderItem> orderItemsList=new ArrayList<>();

        try {
            pst= cnx.prepareStatement(req);
            pst.setInt(1,orderId);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                orderItemsList.add(new OrderItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderItemsList;
    }

}
