package service;

import entite.Order;
import entite.State;
import entite.User;
import java.net.PasswordAuthentication;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
            pst.setInt(1,order.getUser().getId());
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
                ordersList.add(new Order(rs.getInt(1),new User(rs.getInt(2)),rs.getString(3),rs.getDate(4)));
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
                Order order = new Order(rs.getInt(1),new User(rs.getInt(2)),rs.getString(3),rs.getDate(4));
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
                userOrdersList.add(new Order(rs.getInt(1),new User(rs.getInt(2)),rs.getString(3),rs.getDate(4)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userOrdersList;
    }

    public Order getOrInitUserCart(User user){
        Order order = new Order();
        if (getUserOrders(user.getId(), State.inCart.toString()).isEmpty()) {
            order.setUser(user);
            insert(order);
        }
        return getUserOrders(user.getId(),State.inCart.toString()).get(0); // reparcourir la BD pour extraire le champ orderID (PK autoincrement)
    }

    public void placeOrder(Order order){
        OrderItemService orderItemService = new OrderItemService();
        if (orderItemService.getOrderItems(order.getId()).isEmpty())
            System.out.println("Cannot proceed : empty cart!");
        else {
            order.setState(State.placed.toString());
            update(order);
            getOrInitUserCart(order.getUser());
            sendOrderEmail("yessine.rekik@esprit.tn",order);
        }
    }
        public static void sendOrderEmail(String recepient , Order order){
         System.out.println("Preparing to send email");
        Properties properties = new Properties();
         properties.put("mail.smtp.auth","true");
         properties.put("mail.smtp.starttls.enable","true");
         properties.put("mail.smtp.host","smtp.gmail.com");
         properties.put("mail.smtp.port","587");
         
         String myAccountEmail ="yessine.rekik@esprit.tn";
         String password ="191JMT0563";
         
         
         Session session = Session.getInstance(properties, new Authenticator() {
             @Override
             protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                 return new javax.mail.PasswordAuthentication(myAccountEmail,password);
             }  
         });
         
         Message message = prepareMessage( session,myAccountEmail,recepient,order);
        try {
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private static Message prepareMessage(Session session , String myAccountEmail, String recepient,Order order ){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("FTF ");
            message.setText("Cher Client\n Vous avez passé la commande #"+order.getId()+" le "+order.getDate());
            return message;
        } catch (Exception ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

}
