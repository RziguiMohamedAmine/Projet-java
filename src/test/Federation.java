/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import entite.Order;
import entite.OrderItem;
import service.OrderItemService;
import service.OrderService;

public class Federation {
    
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        int userId = 104;

        Order order = new Order();
        order = orderService.getOrInitUserCart(userId);
        System.out.println(order);

        OrderItemService orderItemService = new OrderItemService();
        System.out.println(orderItemService.getOrderItems(order.getId()));
        //orderService.placeOrder(order);
        //orderItemService.insert(new OrderItem(33,216,1));



        //List<Order> list = new ArrayList<>();
        //list = orderService.getAll();
       // System.out.println(list);
        //System.out.println(list.get(0));
        //list.get(0).setState("Shipping");
        //orderService.update(list.get(0));
        //System.out.println(orderService.getOne(2));
       // orderService.delete(orderService.getOne(2));

        OrderItem oi1 = new OrderItem(20,216,1);
        OrderItem oi2 = new OrderItem(2,9456,9);



        //orderItemService.insert(oi1);
        //orderService.delete(orderService.getOne(18));
        //System.out.println(orderItemService.getOrderItems(oi1.getOrder_id()));
        //orderItemService.insert(oi2);
        //orderItemService.delete(oi2);
        //System.out.println(orderItemService.getOrderItems(2));
    }
    
}
