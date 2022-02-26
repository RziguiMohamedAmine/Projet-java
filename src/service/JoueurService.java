/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.Equipe;
import entite.Joueur;
import entite.JoueurMatch;
import entite.Match;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 *
 * @author moham
 */


public class JoueurService implements IService<Joueur>{//relation entre entite et base de données
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public JoueurService()
    {
        conn=DataSource.getInstance().getCnx();
    }
    
    
    
    
    
//    @Override
//    public Boolean insert(Joueur p) 
//    {
//      String req="insert into personne (nom,prenom) values ('"+p.getNom()+"','"+p.getPrenom()+"')";
//        try {
//             ste=conn.createStatement();
//             ste.executeUpdate(req);
//        } catch (SQLException ex) {
//            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//     return true;
//    }
    
    
    
    @Override
    public boolean insert(Joueur j)
    {
        String req="insert into joueur (nom,prenom,poste,nationalite,date_naiss,taille,poids,image,id_equipe) values (?,?,?,?,?,?,?,?,?)";
        Boolean inserted=false;
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,j.getNom());
            pst.setString(2,j.getPrenom());
            pst.setString(3,j.getPoste());
            pst.setString(4,j.getNationalite());
            pst.setDate(5, (Date) j.getDate_naiss());
            pst.setFloat(6,j.getTaille());
            pst.setFloat(7,j.getPoids());
            pst.setString(8,j.getImage());
            if(j.getEquipe()==null){
               pst.setNull(9,1);

            }else{
              pst.setInt(9,j.getEquipe().getId());

            }
            inserted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }
    
   
    
    @Override
     public boolean update(Joueur j) 
    {
      String req="UPDATE joueur SET nom=?,prenom=?,poste=?,nationalite=?,date_naiss=?,taille=?,poids=?,image=?,id_equipe=? WHERE id=?";
       Boolean updated=false;
        try {
            pst=conn.prepareStatement(req);
         
            pst.setString(1,j.getNom());
            pst.setString(2,j.getPrenom());
            pst.setString(3,j.getPoste());
            pst.setString(4,j.getNationalite());
            pst.setDate(5, (Date) j.getDate_naiss());
            pst.setFloat(6,j.getTaille());
            pst.setFloat(7,j.getPoids());
            pst.setString(8,j.getImage());
            pst.setInt(9,j.getEquipe().getId());
            pst.setInt(10,j.getId());
            
            updated=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return updated;
    }

    
    
    
    
    @Override
     public boolean delete(Joueur j) 
    {
      String req="DELETE FROM joueur WHERE id=?";
      Boolean deleted=false;
        try {
             pst=conn.prepareStatement(req);
             pst.setInt(1,j.getId());
             deleted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return deleted;
    }

  

    @Override
    public List<Joueur> getAll() {
        String req="select * from joueur";
        List<Joueur> list =new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs=ste.executeQuery(req);
            EquipeService es =new EquipeService();
            while(rs.next())
            { 
                list.add(new Joueur(rs.getInt("id"),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getFloat(7),rs.getFloat(8),rs.getString(9),es.getOne(rs.getInt("id_equipe"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
        
         }


    @Override
    public Joueur getOne(int id) {
        String req="select * from joueur where id=?";
        Joueur j=null;
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,id);
            EquipeService es =new EquipeService();
            rs = pst.executeQuery();
             if (rs.next())
             {              
                 j = new Joueur(rs.getInt(1), rs.getString(2), rs.getString(3), 
                 rs.getString(4),rs.getString(5),rs.getDate(6),rs.getFloat(7),
                 rs.getFloat(8),rs.getString(9),es.getOne(rs.getInt(1)));
                
             }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return j;
        
    }
    
    
    
      public List<Joueur> getjoueurbyequipe(Equipe ee) {
        String req="select * from joueur INNER JOIN equipe ON joueur.id_equipe = equipe.id where joueur.id_equipe=?";
        List<Joueur> list =new ArrayList<>();
       Equipe e=new Equipe();
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,ee.getId());
            rs=pst.executeQuery();
            while(rs.next())
            {
                e.setId(rs.getInt("id_equipe"));
                e.setNom(rs.getString("nom"));
                e.setLogo(rs.getString("logo"));
                e.setNom_entreneur(rs.getString("nom_entreneur"));
                e.setNiveau(rs.getString("niveau"));

                list.add(new Joueur(rs.getInt(1),rs.getString("nom"),rs.getString("prenom"), 
                        rs.getString("poste"),rs.getString("nationalite"),rs.getDate("date_naiss"),rs.getFloat("taille"),
                 rs.getFloat("poids"),rs.getString("image")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
        
         }
      
      
      
      public List<JoueurMatch> getScoreJoueur(Joueur ee) {
        String req="select * from (\n" +
        "select j.nom,j.prenom,j.poste,j.nationalite,j.date_naiss,j.taille,j.poids,j.image,j.id_equipe,j.nomeq,j.logo,\n" +
        "j.id,j.id_joueur,j.id_match,j.carton_jaune,j.carton_rouge,j.nb_but, m.equipe1, m.equipe2 from "+ 
        "(SELECT j.nom,j.prenom,j.poste,j.nationalite,j.date_naiss,j.taille,j.poids,j.image,j.id_equipe,j.nomeq,j.logo,\n" +
        "mj.id,mj.id_joueur,mj.id_match,mj.carton_jaune,mj.carton_rouge,mj.nb_but FROM (SELECT j.id id_joueur,j.nom,j.prenom,"+
        "j.poste,j.nationalite,j.date_naiss,j.taille,j.poids,j.image,e.id id_equipe,e.nomeq,e.logo " +
        "FROM joueur j INNER JOIN equipe e ON j.id_equipe = e.id  where j.id=?) j  INNER JOIN matchjoueur mj ON mj.id_joueur=j.id_joueur) j\n" +
        "INNER join matchs m on m.id=j.id_match\n" +
        ") j inner join equipe e1 on e1.id = j.equipe1 inner join equipe e2 on e2.id = j.equipe2";
        List<JoueurMatch> list =new ArrayList<>();
         List<Float> listscore =new ArrayList<>();
         
        JoueurMatch jm=new JoueurMatch();
         EquipeService es =new EquipeService();
        Match m=new Match();
        Joueur j=new Joueur();
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,ee.getId());
            rs=pst.executeQuery();
            while(rs.next())
            {
                j.setId(rs.getInt("id_joueur"));
                j.setNom(rs.getString("nom"));
                j.setPrenom(rs.getString("prenom"));
                j.setPoste(rs.getString("Poste"));
                j.setNationalite(rs.getString("nationalite"));
                j.setDate_naiss(rs.getDate("date_naiss"));
                j.setTaille(rs.getFloat("taille"));
                j.setPoids(rs.getFloat("poids"));
                j.setImage(rs.getString("image"));
                j.setEquipe(new Equipe(rs.getInt("id_equipe"),rs.getString("nomeq"),rs.getString("logo")));
                
                m.setId(rs.getInt("id_match"));
                m.setEquipe1(new Equipe(rs.getInt(20),rs.getString(21),rs.getString(22)));
                m.setEquipe2(new Equipe(rs.getInt(25),rs.getString(26),rs.getString(27)));
      
                jm.setId(rs.getInt(12));
                jm.setJoueur(j);
                jm.setMatch(m);
                jm.setJaune(rs.getInt(15));
                jm.setRouge(rs.getInt(16));
                jm.setNb_but(rs.getInt(17));
               
                list.add(new JoueurMatch(rs.getInt(12),j,m,rs.getInt(15),rs.getInt(16),rs.getInt(17)));
                int b=jm.getNb_but();
                int cj=jm.getJaune();
                int cr=jm.getRouge();
              
               
                float score=0;
                if(null!=j.getPoste())
                switch (j.getPoste()) {
                    case "arriere":
                        score=b*(float)2.5+(cj*-1)+(cr*-2)+2;
                        break;
                    case "milieu":
                        score=b*(float)2.5+(cj*-1)+(cr*-1)+(float)2.5;
                        break;
                    case "attaquant":
                        score=b*2+(cj*-1)+(cr*-1)+3;
                        break;
                    default:
                        break;
                }
                listscore.add(score);
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("le score de joueur "+j.getNom()+" dans les defferents matchs est:"+listscore);
        return list;
       
        
         }
      
      
      
      
       
    public List<JoueurMatch> TopScorer() {
        String req="select joueur.*, sum(m.nb_but) as "
             + "\"somme\" from joueur INNER join matchjoueur m on joueur.id=m.id_joueur GROUP by joueur.id order by somme DESC LIMIT 3";
        List<JoueurMatch> list =new ArrayList<>();
      
        
        try {
            pst=conn.prepareStatement(req);
            rs = pst.executeQuery();
               
            while(rs.next())
            { 
                  Match m=new Match();
                  Joueur j=new Joueur();
                  JoueurMatch jm=new JoueurMatch();
                  
                j.setId(rs.getInt("id"));
                j.setNom(rs.getString("nom"));
                j.setPrenom(rs.getString("prenom"));
                j.setPoste(rs.getString("Poste"));
                j.setNationalite(rs.getString("nationalite"));
                j.setDate_naiss(rs.getDate("date_naiss"));
                j.setTaille(rs.getFloat("taille"));
                j.setPoids(rs.getFloat("poids"));
                j.setImage(rs.getString("image"));   
                jm.setJoueur(j);
                
                jm.setNb_but(rs.getInt("somme"));
                list.add(new JoueurMatch(j,rs.getInt("somme")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
        
         }
      
      
    
    
    
     public List<Joueur> getJoueurParPoste(String poste) {
        String req="select * from joueur where poste=?";
        
        List<Joueur> list =new ArrayList<>();
         EquipeService es =new EquipeService();
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,poste);
            rs = pst.executeQuery();
             while (rs.next())
             {              
                 list.add(new Joueur(rs.getInt(1), rs.getString(2), rs.getString(3), 
                 rs.getString(4),rs.getString(5),rs.getDate(6),rs.getFloat(7),
                 rs.getFloat(8),rs.getString(9),es.getOne(rs.getInt(10))));
                
             }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return list;
        
    }
    
    
    
      public List<Joueur> getJoueurParNat(String nat) {
        String req="select * from joueur where nationalite=?";
        
        List<Joueur> list =new ArrayList<>();
        EquipeService es =new EquipeService();
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,nat);
            rs = pst.executeQuery();
             while (rs.next())
             {              
                 list.add(new Joueur(rs.getInt(1), rs.getString(2), rs.getString(3), 
                 rs.getString(4),rs.getString(5),rs.getDate(6),rs.getFloat(7),
                 rs.getFloat(8),rs.getString(9),es.getOne(rs.getInt(10))));
                
             }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return list;
        
    }
    
    
    
     public List<Joueur> getJoueurLibre() {
        String req="select * from joueur where id_equipe is null";
        
        List<Joueur> list =new ArrayList<>();
        try {
            pst=conn.prepareStatement(req);
            rs = pst.executeQuery();
             while (rs.next())
             {              
                 
                 list.add(new Joueur(rs.getInt(1), rs.getString(2), rs.getString(3), 
                 rs.getString(4),rs.getString(5),rs.getDate(6),rs.getFloat(7),
                 rs.getFloat(8) ,rs.getString(9)));
                
             }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return list;
        
    }
    
    
    
     
    
    
    
    
}
