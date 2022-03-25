/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import jframe.DBConnection;
import jframe.LoginPage;
import jframe.SignupPage;


/**
 *
 * @author tutu5
 */
public class Registro_Metodos extends SignupPage{
    
    
    // Metodo para realizar la verificación de datos en el panel de registro.
    public static boolean validateSignup(){
        
        String name = txt_username.getText();
        String password = txt_password.getText();
        String email = txt_email.getText();
        String contact = txt_contact.getText();
        
        if (name.equals("")) {
            JOptionPane.showMessageDialog(txt_username, "Please enter Username");
            return false;
        }
        
        if (password.equals("")) {
            JOptionPane.showMessageDialog(txt_password, "Please enter Password");
            return false;
        }
        
        if (email.equals("") || !email.matches("^.+@.+\\..+$")) {
            JOptionPane.showMessageDialog(txt_email, "Please enter valid email");
            return false;
        }
        
        if (contact.equals("")) {
            JOptionPane.showMessageDialog(txt_contact, "Please enter contact No");
            return false;
        }
        
        return true;
    }
    
    
            //Metodo para ingresar los datos a el Database una vez verificados.
        public static void insertSingupDetails(){
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        String email = txt_email.getText();
        String contact = txt_contact.getText();
        
        
        try{
           Connection con =DBConnection.getConnection();
           String sql = "insert into users(name, password, email, contact) values(?,?,?,?)";
           PreparedStatement pst = con.prepareStatement(sql);
           

           pst.setString(1, name);
           pst.setString(2, pwd);
           pst.setString(3, email);
           pst.setString(4, contact);
           
           int updatedRowCount = pst.executeUpdate();
           
            if (updatedRowCount > 0) {
                JOptionPane.showMessageDialog(txt_username,"Recorded inserted Successfully");
                LoginPage page = new LoginPage();
                page.setVisible(true);
                
            }else{
                JOptionPane.showMessageDialog(txt_username, "Recorded inserted failed");
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        
        
        //Metodo para realizar un analizis en la base de datos y verificar si el nombre se repide o no.
        public static boolean checkDuplicateUser(){
        String name = txt_username.getText();
        boolean isExist = false;
        try{           
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            
            PreparedStatement pst = con.prepareStatement("select * from users where name =");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                isExist = true;
            }else{
                isExist = false;
               
            }
            
        } catch(Exception e){
        }
        return isExist;
    }
    
}
