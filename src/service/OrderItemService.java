package service;

import entite.*;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
                pst.setInt(1,item.getOrder().getId());
                pst.setInt(2,item.getProduct().getId());
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
            pst.setInt(2,item.getOrder().getId());
            pst.setInt(3,item.getProduct().getId());
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
            pst.setInt(1,item.getOrder().getId());
            pst.setInt(2,item.getProduct().getId());
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
                itemsList.add(new OrderItem(rs.getInt(1),new Order(rs.getInt(2)),new ServiceProduit().getOne (rs.getInt(3)),rs.getInt(4)));
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
                OrderItem item = new OrderItem(rs.getInt(1),new Order(rs.getInt(2)),new ServiceProduit().getOne (rs.getInt(3)),rs.getInt(4));
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
                orderItemsList.add(new OrderItem(rs.getInt(1),new Order(rs.getInt(2)),new ServiceProduit().getOne((rs.getInt(3))),rs.getInt(4)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderItemsList;
    }

    public List<Product> getBestSellers(int limit){
        String req="SELECT product_id,count(product_id)\n" +
                "FROM order_items\n" +
                "WHERE order_id in (SELECT id FROM orders WHERE LOWER(state)=LOWER(?))\n"+
                "GROUP BY product_id\n" +
                "ORDER BY count(product_id) DESC\n" +
                "LIMIT ?";
        List<Integer> orderItemsList=new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        try {
            pst= cnx.prepareStatement(req);
            pst.setString(1,State.placed.toString());
            pst.setInt(2,limit);
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                //orderItemsList.add(rs.getInt(1)); // new Integer () ????
                productList.add(new ServiceProduit().getOne(rs.getInt(1)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }

    public List<ItemSupport> getRecommendedItemsSupports(List<ItemSupport> recommendedProducts){
        if (!recommendedProducts.isEmpty())
        {
            String req=String.format("SELECT count(product_id) FROM order_items\n"+
                    "WHERE product_id IN (%s) AND order_id IN (SELECT id FROM orders WHERE LOWER(state)=LOWER(?))\n"+
                    "GROUP BY product_id\n"+
                    "ORDER BY product_id",recommendedProducts.stream().map(v -> "?").collect(Collectors.joining(", ")));

            try {
                pst= cnx.prepareStatement(req);
                for (int i = 1 ; i<= recommendedProducts.size();i++){
                    pst.setInt(i,recommendedProducts.get(i-1).getProductId());
                }
                pst.setString(recommendedProducts.size()+1,State.placed.toString());
                ResultSet rs= pst.executeQuery();
                int i=0;
                while(rs.next()){
                    recommendedProducts.get(i).setItemCount(rs.getInt(1));
                    i++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return recommendedProducts;
    }

    public List<Product> basketMarketAnalysis (List<OrderItem> cartOrderItems){
        List<Product> productList = new ArrayList<>();
        cartOrderItems.forEach(e-> System.out.print(e.getProduct()+" "));
        System.out.println("");
        AprioriSet recommendedItems = new AprioriSet();
        if (!cartOrderItems.isEmpty())
        {
            String req = String.format("SELECT product_id, count(product_id) FROM order_items\n"+
                    "WHERE order_id IN (Select id\n" +
                    "                   From orders \n" +
                    "                   Where LOWER(state)=LOWER(?) " +
                    "                   AND id IN (Select order_id From order_items\n" +
                    "                              Where product_id In(%s)\n" +
                    "                              Group By order_id\n" +
                    "                              Having Count(*) = ?\n" +
                    "                             )\n"+
                    "                    )\n"+
                    "GROUP BY product_id\n"+
                    "ORDER BY product_id", cartOrderItems.stream().map(v -> "?").collect(Collectors.joining(", ")));
            try {
                pst= cnx.prepareStatement(req);
                pst.setString(1,State.placed.toString());
                int cartSize= cartOrderItems.size();
                for (int i = 2 ; i<=cartSize+1;i++){
                    pst.setInt(i,cartOrderItems.get(i-2).getProduct().getId()); // WHERE product_id in (?,?,?,?,.....,?)
                }
                pst.setInt(cartSize+2,cartSize); // HAVING count(*) = ?
                ResultSet rs= pst.executeQuery();

                boolean b = false; // get initialSupport
                List<Integer> cartItemsIds = new ArrayList<>();
                cartOrderItems.forEach(e->cartItemsIds.add(e.getProduct().getId()));

                while(rs.next()){
                    if (!cartItemsIds.contains(rs.getInt(1))) //rs.getInt(2)>= minimumSupport &&
                    {
                        recommendedItems.getSuperSet().add(new ItemSupport(rs.getInt(1),rs.getInt(2)));
                        productList.add(new ServiceProduit().getOne(rs.getInt(1)));
                    }

                    else if (!b){
                        recommendedItems.setSubsetCount(rs.getInt(2));
                        b=true;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
            }


            getRecommendedItemsSupports(recommendedItems.getSuperSet());
            recommendedItems.setTotalOrdersNumber(getTotalOrdersNumber());
            recommendedItems.filterProducts();
            System.out.println("Recommended products :"+recommendedItems);
        }

        return productList;
    }

    public int getTotalOrdersNumber (){
        String req = "select count(*) from orders where LOWER(state) = LOWER(?)";
        try {
            pst= cnx.prepareStatement(req);
            pst.setString(1,State.placed.toString());
            ResultSet rs= pst.executeQuery();
            if (rs.next())
                return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }


}
