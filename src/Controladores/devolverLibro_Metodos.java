/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.sql.*;
import javax.swing.JOptionPane;
import jframe.DBConnection;
import jframe.ReturnBook;

/**
 *
 * @author tutu5
 */
public class devolverLibro_Metodos extends ReturnBook{
    
     public static void updateBookCount(){
             int bookId = Integer.parseInt(txt_bookId.getText());
             try {
                 Connection con = DBConnection.getConnection();
                 String sql = "update book_details set quantity = quantity +1 where book_id = ?";//seleccion de verificación especifica en la tabla dentro de nuestra base de datos
                 PreparedStatement pst = con.prepareStatement(sql);
                 pst.setInt(1, bookId);
                 
                 int rowCount = pst.executeUpdate();
                 
                 if (rowCount > 0) {
                    JOptionPane.showMessageDialog(txt_bookId, "Cantidad de libros disponibles actualizada");
                    
                    
                }else{
                    JOptionPane.showMessageDialog(txt_bookId, "No se pudo actualizar cantidad");
                }
                 
             }catch(Exception e){
                 e.printStackTrace();
             }
             
         }
   
     // Metodo para buscar los detalles de libros pedidos en la base de datos y mostrarlo en el panel
    
     public static void getIssueBookDetails(){
         
         int bookId = Integer.parseInt(txt_bookId.getText());
         int studentId = Integer.parseInt(txt_studentId.getText());
         
         try{
             Connection con = DBConnection.getConnection();
             String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";//seleccion de verificación especifica en la tabla dentro de nuestra base de datos
             
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1, bookId);
             pst.setInt(2, studentId);
             pst.setString(3, "Pendiente");
             
             ResultSet rs = pst.executeQuery();
             if (rs.next()) {
                 
                 lbl_IssueId.setText(rs.getString("id"));
                 lbl_bookName.setText(rs.getString("book_name"));
                 lbl_studentName.setText(rs.getString("student_name"));
                 lbl_issueDate.setText(rs.getString("issue_date"));
                 lbl_dueDate.setText(rs.getString("due_date"));
                 lbl_bookError.setText("");
             }else{
                 lbl_bookError.setText("Ningun dato encontrado");
                 
                 lbl_IssueId.setText("");
                 lbl_bookName.setText("");
                 lbl_studentName.setText("");
                 lbl_issueDate.setText("");
                 lbl_dueDate.setText("");
             }
             
         }catch(Exception e){
             e.printStackTrace();
         }
         
     }
     
     //Metodo para devolver libro
     public static boolean returnBook(){
         boolean isReturned = false;
         
         int bookId = Integer.parseInt(txt_bookId.getText());
         int studentId = Integer.parseInt(txt_studentId.getText());
         
         try{
             Connection con = DBConnection.getConnection();
             String sql = "update issue_book_details set status = ? where student_id = ? and book_id = ? and status = ?";
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1, "Regresado");
             pst.setInt(2, studentId);
             pst.setInt(3, bookId);
             pst.setString(4, "Pendiente");
             
             int rowCount =pst.executeUpdate();
             if (rowCount > 0) {
                 isReturned = true;
             }else{
                 isReturned = false;
             }
             
         }catch(Exception e){
             e.printStackTrace();
         }
         return isReturned;
     }
     
     
     
}
