/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.sql.*;
import utils.DataSource;
import entite.Avis;
import entite.User;
import entite.categorie;
import entite.produit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sof
 */
public class ServiceAvis implements IService<Avis>{
     private Connection conn;
     private Statement ste;
     PreparedStatement pst;
      ResultSet rs;

    public ServiceAvis() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Avis t) 
    {
            boolean insert=false;
        String req = "insert into avis (id_user,id_produit,avis) values ('" + t.getUser().getId() + "',"
        + "'" + t.getProduit().getId()+ "','" + t.getAvis()+ "')";
          
       try {
      
            ste = conn.createStatement();
            insert=ste.executeUpdate(req)>0;

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }  return insert;
    }

    
    
    
    @Override
    public boolean update(Avis t) {
        boolean update=false;
        String req ="update avis set avis = ? where id_user=? and id_produit=?";
         
       
           try {
             pst= conn.prepareStatement(req);
             pst.setFloat(1, t.getAvis());
             
             pst.setInt(2, t.getUser().getId());
                          pst.setInt(3, t.getProduit().getId());

             
             update=pst.executeUpdate()>0;
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
         } return update;
    }

    @Override
    public boolean delete(Avis t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avis> getAll() {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    public List<Avis> getAvisByProduit(int id){
        String  req="select * from (SELECT id_avis, avis, user.id id_user, user.nom user_nom,"
                + " prenom, email, pass, tel, role, user.image user_image, produit.id id_produit,"
                + " produit.nom produit_nom, produit.image image_produit, prix, description, "
                + "id_cat FROM `avis` INNER join user on avis.id_user = user.id inner "
                + "join produit on produit.id=avis.id_produit WHERE id_produit=?) p join categorie"
                + " c on c.id = p.id_cat";
        List<Avis> list = new ArrayList<>();
        try {
            pst= conn.prepareStatement(req);
            pst.setInt(1,id);
                       
            rs = pst.executeQuery();
            while (rs.next()){
                  Avis avis =new Avis();
                  
                 avis.setId(rs.getInt("id_avis"));
                 avis.setAvis(rs.getFloat("avis"));
                 produit p = new produit();
                 p.setId(rs.getInt("id_produit"));
                 p.setDescription(rs.getString("description"));
                 categorie c= new categorie(rs.getInt("id"), rs.getString("nom"));

                 p.setCat(c);
                 p.setImage(rs.getString("image_produit"));
                 p.setNom(rs.getString("produit_nom"));
                 p.setPrix(rs.getFloat("prix"));


                 avis.setProduit(p);
                 avis.setUser(new User(rs.getInt("id_user"), rs.getString("user_nom"), rs.getString("prenom"), rs.getString("email"), "", rs.getString("tel"), rs.getString("role"), rs.getString("user_image")));
                 list.add(avis);
            }
              System.out.println(list);            
               
             
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return list;
    }
    
    public List<Float> getCountAverageAvisByProduit(int id){
        String  req="SELECT COUNT(*) count, AVG(avis) average FROM `avis` WHERE id_produit=?";
        List<Float> list = new ArrayList<>();
        try {
            pst= conn.prepareStatement(req);
            pst.setInt(1,id);
                       
            rs = pst.executeQuery();
             if (rs.next())
             {           
                float average= rs.getFloat("average");
                 int count = rs.getInt("count");
                 list.add((float) count);
                 list.add(average);
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return list;
    }

    @Override
    public Avis getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean userExiste(Avis a){
         String  req="SELECT count(*) count FROM `avis` WHERE id_produit=? and id_user=?";
        List<Float> list = new ArrayList<>();
        try {
            pst= conn.prepareStatement(req);
            pst.setInt(1,a.getProduit().getId());
            pst.setInt(2,a.getUser().getId());

                       
            rs = pst.executeQuery();
             if (rs.next())
             {           
               return rs.getInt("count")>0;
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return false;
    }
    
    
    
    
}
