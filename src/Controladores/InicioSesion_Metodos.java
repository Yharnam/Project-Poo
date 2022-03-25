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
import jframe.HomePage;
import static jframe.LoginPage.txt_password;
import static jframe.LoginPage.txt_username;

/**
 *
 * @author tutu5
 */
public class InicioSesion_Metodos extends HomePage{
    
        //Validadcion de datos en base de datos.
        public static boolean validateLogin(){
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        
        if (name.equals("")){
            JOptionPane.showMessageDialog(txt_username, "Please enter username");
            return false;
        }
        
                if (pwd.equals("")){
            JOptionPane.showMessageDialog(txt_password, "Please enter password");
            return false;
        }
        return true;
        
    }
        
        //Metodo para ingresar datos ya registrados, buscarlos en la base de datos y brindar una continuación al programa si se encuentran los datos.
        public static void login(){
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            PreparedStatement pst = con.prepareStatement("select * from users where name = ? and password = ?");
            
            pst.setString(1, name);
            pst.setString(2, pwd);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(txt_username, "Login successful");
                HomePage home = new HomePage();
                home.setVisible(true);
            }
            
        } catch(Exception e){
            
        }
        
    } 
    
}
