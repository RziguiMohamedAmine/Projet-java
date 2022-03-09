/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
//import entite.Personne;
import entite.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 *
 * @author R I B
 */
public class RolesServices implements IService<Roles>{
      private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
     private PreparedStatement st;
    private ResultSet rs;
  public RolesServices() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Roles r) {
   boolean insert=false ;
        String req = "insert into role_arbitre (role,description) values ('" + r.getRole() + "','" + r.getDescription() + "')";
        try {
            ste = conn.createStatement();
            insert=ste.executeUpdate(req)>0;
        } catch (SQLException ex) {
            Logger.getLogger(RolesServices.class.getName()).log(Level.SEVERE, null, ex);
        } return insert;
            }

    @Override
    public boolean delete(Roles r) {
boolean del=false;
           String req="delete from role_arbitre where id = ?";
        try {
              pst = conn.prepareStatement(req);
             pst.setInt(1, r.getId());
             del=pst.executeUpdate()>0;
         } catch (SQLException ex) {
             Logger.getLogger(RolesServices.class.getName()).log(Level.SEVERE, null, ex);
         } return del;      }

    @Override
    public boolean update(Roles r) {
    boolean update=false;
        String req ="update role_arbitre set role = ? , description = ?  where id = ?";
         
        try {
             st = conn.prepareStatement(req);
             st.setString(1,r.getRole());
             st.setString(2, r.getDescription());
             st.setInt(3, r.getId());
             
             update=st.executeUpdate()>0;
             
         } catch (SQLException ex) {
             Logger.getLogger(RolesServices.class.getName()).log(Level.SEVERE, null, ex);
         } return update; }

    @Override
    public List<Roles> getAll() {
          String req="select * from role_arbitre";
                    List<Roles> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Roles(rs.getInt("id"), rs.getString("role"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Roles getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Roles read(int id) {
   String req="select * from role_arbitre where id=?";
        Roles r=null;
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();
             if (resultSet.next()) {              
                    r = new Roles(resultSet.getInt(1), resultSet.getString(2), 
                          resultSet.getString(3));
                }
        } catch (SQLException ex) {
            Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return r;  }
    
}
