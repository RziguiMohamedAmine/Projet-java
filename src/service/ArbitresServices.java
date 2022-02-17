/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import utils.Datasource;

import java.util.List;
import entite.Arbitres;
//import entite.Personne;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
        /**
 *
 * @author R I B
 */
public class ArbitresServices implements IService<Arbitres> {
 private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
     private PreparedStatement st;
    private ResultSet rs;

    public ArbitresServices() {
        conn = Datasource.getInstance().getCnx();
    }
    
    @Override
    public boolean insert(Arbitres a) {
            boolean insert=false ;
        String req = "insert into arbitre (id,nom,prenom,id_role,image,age) values ('"+a.getId()+"','" + a.getNom() + "','" + a.getPrenom() + "','" + a.getId_role() + "','" + a.getImage() + "','" + a.getAge() + "')";
        try {
            ste = conn.createStatement();
            insert=ste.executeUpdate(req)>0;
        } catch (SQLException ex) {
            Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
        } return insert;

    }

    @Override
    public boolean delete(Arbitres a) {
boolean del=false;
           String req="delete from arbitre where id = ?";
        try {
              pst = conn.prepareStatement(req);
             pst.setInt(1, a.getId());
             del=pst.executeUpdate()>0;
         } catch (SQLException ex) {
             Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
         } return del;     }

    @Override
    public boolean update(Arbitres a) {
   boolean update=false;
        String req ="update arbitre set nom = ? , prenom = ? , id_role = ? , image = ? , age = ?   where id = ?";
         
        try {
             st = conn.prepareStatement(req);
             st.setString(1, a.getNom());
             st.setString(2, a.getPrenom());
             st.setInt(3, a.getId_role());
             st.setString(4, a.getImage());
             st.setInt(5, a.getAge());
             st.setInt(6 , a.getId());
             
             update=st.executeUpdate()>0;
             
         } catch (SQLException ex) {
             Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
         } return update; }

    @Override
    public List<Arbitres> read() {
  String req="select * from arbitre";
                    List<Arbitres> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Arbitres(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("id_role"), rs.getString("image"), rs.getInt("age")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArbitresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    }

    @Override
    public Arbitres readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
