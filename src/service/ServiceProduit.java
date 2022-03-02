package service;

import entite.Product;
import utils.DataSource;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceProduit {

    private Connection cnx;
    private PreparedStatement pst;
    public ServiceProduit() {
        cnx = DataSource.getInstance().getCnx();
    }

    public Product getOne(int id) {
        String req="select * from products where id=?";
        try {
            pst= cnx.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet rs= pst.executeQuery();
            if(rs.next()) {
                Product product = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getFloat(4),rs.getString(5));
                return product;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
