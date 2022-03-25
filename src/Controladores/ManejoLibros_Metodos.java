/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jframe.DBConnection;
import  jframe.ManageBooks;
/**
 *
 * @author tutu5
 */
public class ManejoLibros_Metodos extends ManageBooks{
    
 
        //Metodo para insertar datos dentro de la tabla, mostrar y que guarde en la base de datos.
            
        public static void setBookDetailsToTable(){
                DefaultTableModel model;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery("select * from book_details");
            
           while(rs.next()){
            String bookId = rs.getString("book_id");
            String bookName = rs.getString("book_name");
            String author = rs.getString("author");
            int quantity = rs.getInt("quantity");
            
            Object[] obj = {bookId, bookName, author, quantity};
            model = (DefaultTableModel) tbl_bookDetails.getModel();
            model.addRow(obj);
            
        }
           
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
        
        public static boolean addbook() {
            
            String bookName,author;
            int bookId, quantity;
            boolean isAdded = false;
            bookId = Integer.parseInt(txt_bookId.getText());
            bookName = txt_bookName.getText();
            author = txt_authorName.getText();
            quantity = Integer.parseInt(txt_quantity.getText());
            
            try{
                Connection con = DBConnection.getConnection();
                String sql = "insert into book_details value(?,?,?,?)";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setInt(1, bookId);
                pst.setString(2, bookName);
                pst.setString(3, author);
                pst.setInt(4, quantity);
                
                int rowCount = pst.executeUpdate();
                if (rowCount >0) {
                    isAdded = true;
                    
                }else{
                    isAdded = false;
                }
                                
            }catch (Exception e){
                
            }
            return isAdded;
        }
        
        //Metodo para actualizar detalles del libro
        
        public static boolean updateBooks(){
            
            String bookName,author;
            int bookId, quantity;
            boolean isUpdated = false;
            bookId = Integer.parseInt(txt_bookId.getText());
            bookName = txt_bookName.getText();
            author = txt_authorName.getText();
            quantity = Integer.parseInt(txt_quantity.getText());
            
            try{
                Connection con = DBConnection.getConnection();
                String sql = "update book_details set book_name = ?,author =?, quantity =? where book_id =?";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, bookName);
                pst.setString(2, author);
                pst.setInt(3, quantity);
                pst.setInt(4,bookId);
                
                int rowCount = pst.executeUpdate();
                if (rowCount >0) {
                    isUpdated = true;
                    
                }else{
                    isUpdated = false;
                }
                
            }catch(Exception e){
               e.printStackTrace();
            }
            return isUpdated;
        }
        
        //Metodo para borrar libro
        
        public static boolean deleteBook(){
            int bookId;
            boolean isDeleted = false;
            bookId = Integer.parseInt(txt_bookId.getText());
            
            try{
                
                Connection con = DBConnection.getConnection();
                String sql ="delete from book_details where boo_id =?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, bookId);
                
                int rowCount = pst.executeUpdate();
                if (rowCount > 0) {
                    isDeleted = true;
                }else{
                    isDeleted = false;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return isDeleted;
        }
        
        //Metodo para borrar tabla
        
        
        public static void clearTable(){
            DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
            model.setRowCount(0);
            
        }
        
        //Metodo para Mostrar los datos seleccionados en los cuadros de textos.
        public static void ClickTable(){
            
             int rowNo = tbl_bookDetails.getSelectedRow();
        TableModel model = tbl_bookDetails.getModel();
        
        txt_bookId.setText(model.getValueAt(rowNo,0).toString());
        txt_bookName.setText(model.getValueAt(rowNo,1).toString());
        txt_authorName.setText(model.getValueAt(rowNo,2).toString());
        txt_quantity.setText(model.getValueAt(rowNo,3).toString());
            
        }
        
       
    
}
