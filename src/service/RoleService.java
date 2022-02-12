/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import entite.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamdi
 */
public class RoleService implements IService<Role>{
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
public RoleService() {
        conn=DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Role r) {
        String req="insert into role (role) values (?)";
        boolean inserted=false;
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,r.getRole());
            inserted=pst.executeUpdate()>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    @Override
    public boolean update(Role r) {
        String req="UPDATE role SET role=? WHERE id=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);
            System.out.println(r);
            
            pst.setString(1,r.getRole());
            pst.setInt(2,r.getId());
            update=pst.executeUpdate()>0;

            
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }return update;
    }

    @Override
    public boolean delete(Role r) {
        String req="DELETE FROM role WHERE id=?";
         boolean delete=false;
        try {
            pst =conn.prepareStatement(req);
            pst.setInt(1,r.getId());
            delete=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return delete;
    }

    @Override
    public List<Role> getAll() {
        String req="select * from role";
         List<Role> list=new ArrayList<>();
        try {
           
            ste=conn.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
              list.add(new Role(rs.getInt("id"),rs.getString("role")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Role getOne(int id) {
           String req="select * from role where id=?";
        Role r = null;
        try 
        {
            pst=conn.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            if(rs.next())
            {
                r=new Role(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

           

    
}
