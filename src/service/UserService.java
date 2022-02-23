/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import utils.DataSource;
import entite.User;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author hamdi
 */
public class UserService implements IService<User>{
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    Session newSession = null;
	MimeMessage mimeMessage = null;

    public UserService() {
        conn=DataSource.getInstance().getCnx();
    }
    
    
   public boolean insert(User u){
       String req="insert into user (nom,prenom,email,pass,tel,role,image) values (?,?,?,?,?,?,?)";
        boolean inserted=false;
        try {
            pst=conn.prepareStatement(req);
            pst.setString(1,u.getNom());
            pst.setString(2,u.getPrenom());
            pst.setString(3,u.getEmail());
            pst.setString(4,doHashing(u.getPass()));
            pst.setString(5,u.getTel());
            pst.setString(6,u.getRole());
            pst.setString(7,u.getImage());
            inserted=pst.executeUpdate()>0;
            envoyerMail(u.getEmail(), "");
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
  }


  

    @Override
    public boolean update(User u) {
        String req="UPDATE user SET nom=?,prenom=?,email=?,pass=?,tel=?,role=?,image=? WHERE id=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);
            
            pst.setString(1,u.getNom());
            pst.setString(2,u.getPrenom());
            pst.setString(3,u.getEmail());
            pst.setString(4,doHashing(u.getPass()));
            pst.setString(5,u.getTel());
            pst.setString(6,u.getRole());
            pst.setString(7,u.getImage());
            pst.setInt(8,u.getId());
            update=pst.executeUpdate()>0;

            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }return update;
}

    @Override
    public boolean delete(User u) {
         String req="DELETE FROM user WHERE id=?";
         boolean delete=false;
        try {
            pst =conn.prepareStatement(req);
            pst.setInt(1,u.getId());
            delete=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return delete;
    }

    @Override
    public List<User> getAll() {
        String req="select * from user";
         List<User> list=new ArrayList<>();
        try {
           
            ste=conn.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
              list.add(new User(rs.getInt("id"), rs.getString(2),rs.getString("prenom"),rs.getString("email"),"",rs.getString("tel"),rs.getString("role"),rs.getString("image")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    
    
    
    @Override
    public User getOne(int id)
    {
        String req="select * from user where id=?";
        User u = null;
        try 
        {
            pst=conn.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            if(rs.next())
            {
                u=new User(rs.getInt(1),rs.getString(2),rs.getString(3),
              rs.getString(4),"",rs.getString(6),
                        rs.getString(7),rs.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
     
    }
    public  String doHashing (String pass) {
  try {
   MessageDigest ms = MessageDigest.getInstance("MD5");
   
   ms.update(pass.getBytes());
   
   byte[] resultByteArray = ms.digest();
   
   StringBuilder sb = new StringBuilder();
   
   for (byte b : resultByteArray) {
    sb.append(String.format("%02x", b));
   }
   
   return sb.toString();
   
  } catch (NoSuchAlgorithmException e) {
   e.printStackTrace();
  }
  
  return "";
 }
    
   public boolean block(Timestamp date, User user){
       
       String req="UPDATE user SET block=? WHERE id=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);
            
            pst.setTimestamp(1,date);
            pst.setInt(2,user.getId());
           
            update=pst.executeUpdate()>0;

            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
       
   }
   
   public boolean sendMailForgetPass(String email){
        String req="UPDATE user SET forgetpassCode=? WHERE email=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);
            long code =(long) (Math.random() * ( 999999 -100000 ));

            pst.setLong(1,code);
            pst.setString(2,email);
            update=pst.executeUpdate()>0;
            
            envoyerMail(email,Long.toString(code));

            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
       
       
   }
   
   public boolean changePassForgetPassword(String email, String code, String newPass){
       String req="UPDATE user SET pass=?, forgetpassCode=null WHERE email=? and forgetpassCode=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);

            pst.setString(1,doHashing(newPass));
                        pst.setString(2,email);

            pst.setString(3,code);
           
            update=pst.executeUpdate()>0;
            

            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
   }
   public boolean ban(User user){
        String req="UPDATE user SET ban=1 WHERE id=?";
        boolean update=false;
        try {
            pst = conn.prepareStatement(req);
            
            pst.setInt(1,user.getId());
           
            update=pst.executeUpdate()>0;

            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
       
   }
public boolean login(String email, String pass){
    String hashedPass = doHashing(pass);
    int count = 0;
    Timestamp nowDate=Timestamp.from(Instant.now());
    String req="select * from user where email = '"+email+"' and pass= '"+hashedPass+"' and (block<'"+nowDate+"' or block is null) and ban = 0";
    
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            if(rs.next()){
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count!=0;
    
}    

	 public  void envoyerMail(String recepient, String code) throws Exception
   {
        String email ="formulamailone@gmail.com";
        String password ="formulaone1_";

       System.out.println("Preparing to send email");

     Properties properties = new Properties();

     properties.put("mail.smtp.auth", "true");
     properties.put("mail.smtp.starttls.enable", "true");
     properties.put("mail.smtp.host", "smtp.gmail.com");
     properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
     properties.put("mail.smtp.port", "587");
     properties.put("mail.smtp.ssl.protocols","TLSv1.2");




             Session session = Session.getDefaultInstance(properties, new Authenticator() {
               @Override
               protected PasswordAuthentication getPasswordAuthentication(){
                   return new PasswordAuthentication(email,password);
               }
             });
             Message message = prepareMessage(session, email, recepient,code);
             Transport.send(message);
             System.out.println("Message sent successfully");
   }

   private static Message prepareMessage(Session session, String email, String recepient, String code){
       try{
       String htmlCode;
       Message message = new MimeMessage(session);
       message.setFrom(new InternetAddress(email));
       message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
       if(code.length()==6){
                  message.setSubject("Mot de passe oublié");

           htmlCode ="<!doctype html>\n" +
"<html lang=\"en-US\">\n" +
"\n" +
"<head>\n" +
"    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
"    <title>mot de passe oublié</title>\n" +
"    <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
"    <style type=\"text/css\">\n" +
"        a:hover {text-decoration: underline !important;}\n" +
"    </style>\n" +
"</head>\n" +
"\n" +
"<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
"    <!--100% body table-->\n" +
"    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
"        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
"        <tr>\n" +
"            <td>\n" +
"                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
"                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                    <tr>\n" +
"                        <td style=\"height:80px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"text-align:center;\">\n" +
"                          <a href=\"https://www.ftf.tn\" title=\"logo\" target=\"_blank\">\n" +
"                            <img width=\"120\" src=\"https://upload.wikimedia.org/wikipedia/fr/thumb/3/33/Logo_federation_tunisienne_de_football.svg/1200px-Logo_federation_tunisienne_de_football.svg.png\" title=\"logo\" alt=\"logo\">\n" +
"                          </a>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"height:20px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
"                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
"                                <tr>\n" +
"                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
"                                </tr>\n" +
"                                <tr>\n" +
"                                    <td style=\"padding:0 35px;\">\n" +
"                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">Mot De Passe Oublié</h1>\n" +
"                                        <span\n" +
"                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
"                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
"                                          <strong>"+code+"</strong> est le code de réinitialisation de votre mot de passe, Si vous n'avez pas envoyé la demande, veuillez ignorer cet e-mail.<br>\n" +
" L'équipe FTF\n" +
"                                        </p>\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                                <tr>\n" +
"                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
"                                </tr>\n" +
"                            </table>\n" +
"                        </td>\n" +
"                    <tr>\n" +
"                        <td style=\"height:20px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"text-align:center;\">\n" +
"                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.ftf.tn</strong></p>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"height:80px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </td>\n" +
"        </tr>\n" +
"    </table>\n" +
"    <!--/100% body table-->\n" +
"</body>\n" +
"\n" +
"</html>";
       }else{
                  message.setSubject("verification e-mail FTF");

               htmlCode ="<!doctype html>\n" +
"<html lang=\"en-US\">\n" +
"\n" +
"<head>\n" +
"    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
"    <title>verification E-mail</title>\n" +
"    <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
"    <style type=\"text/css\">\n" +
"        a:hover {text-decoration: underline !important;}\n" +
"    </style>\n" +
"</head>\n" +
"\n" +
"<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
"    <!--100% body table-->\n" +
"    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
"        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
"        <tr>\n" +
"            <td>\n" +
"                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
"                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                    <tr>\n" +
"                        <td style=\"height:80px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"text-align:center;\">\n" +
"                          <a href=\"https://www.ftf.tn\" title=\"logo\" target=\"_blank\">\n" +
"                            <img width=\"120\" src=\"https://upload.wikimedia.org/wikipedia/fr/thumb/3/33/Logo_federation_tunisienne_de_football.svg/1200px-Logo_federation_tunisienne_de_football.svg.png\" title=\"logo\" alt=\"logo\">\n" +
"                          </a>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"height:20px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
"                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
"                                <tr>\n" +
"                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
"                                </tr>\n" +
"                                <tr>\n" +
"                                    <td style=\"padding:0 35px;\">\n" +
"                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">verification E-mail</h1>\n" +
"                                        <span\n" +
"                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
"                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
"             Salut ,Veuillez suivre ce lien pour vérifier votre adresse e-mail et la connecter à votre compte FTF :Vérifiez votre e-mail Si vous n'avez pas envoyé la demande, veuillez ignorer cet e-mail.<br>\n" +
" L'équipe FTF\n" +
"                                        </p>\n" +
"                                        <a href=\"javascript:void(0);\"\n" +
"                                            style=\"background:red;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">verifier E-mail</a>\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                                <tr>\n" +
"                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
"                                </tr>\n" +
"                            </table>\n" +
"                        </td>\n" +
"                    <tr>\n" +
"                        <td style=\"height:20px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"text-align:center;\">\n" +
"                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.ftf.tn</strong></p>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"height:80px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </td>\n" +
"        </tr>\n" +
"    </table>\n" +
"    <!--/100% body table-->\n" +
"</body>\n" +
"\n" +
"</html>";
 }
        
     message.setContent(htmlCode,"text/html");
     return message;
   }catch (Exception ex){
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
   }
       return null;
   }
}	
   

	



