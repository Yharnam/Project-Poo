/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import jframe.DBConnection;
import jframe.IssueBook;

/**
 *
 * @author tutu5
 */
public class PedirLibro_Metodos extends IssueBook {
    //Metodo para tomar datos del libro desde la base de datos que el usuario escoja
    public static void getBookDetails (){
        int bookId = Integer.parseInt(txt_bookId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id =?");//seleccion de verificación especifica en la tabla dentro de nuestra base de datos
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
            }else{
                lbl_bookError.setText("ID de libro invalido");
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        //Metodo para tomar datos del estudiante desde la base de datos que el usuario escoja
        public static void getStudentDetails (){
        int studentId = Integer.parseInt(txt_studentId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id =?");//seleccion de verificación especifica en la tabla dentro de nuestra base de datos
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_studentId.setText(rs.getString("student_id"));
                lbl_studentName.setText(rs.getString("name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
            }else{
                lbl_studentError.setText("ID estudiante invalido");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        
        
        //Insertar detalles de "pedir libro" en la base de datos        
        public static boolean issueBook(){
            
            boolean isIssued = false;
            
            int bookId = Integer.parseInt(txt_bookId.getText());
            int studentId = Integer.parseInt(txt_studentId.getText());
            String bookName = lbl_bookName.getText();
            String studentName = lbl_studentName.getText();
            
            Date uIssueDate = date_issueDate.getDatoFecha();
            Date uDueDate = date_dueDate.getDatoFecha();
            
            Long once = uIssueDate.getTime();
            long doce = uDueDate.getTime();
            
            
            java.sql.Date sIssueDate = new java.sql.Date(once);
            java.sql.Date sDueDate = new java.sql.Date(doce);
            
            try{
                Connection con = DBConnection.getConnection();
                String sql = "insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) values(?,?,?,?,?,?,?)";//seleccion de verificación especifica en la tabla dentro de nuestra base de datos
                
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, bookId);
                pst.setString(2, bookName);
                pst.setInt(3, studentId);
                pst.setString(4, studentName);
                pst.setDate(5, sIssueDate);
                pst.setDate(6, sDueDate);
                pst.setString(7, "Pendiente");
                
                int rowCount = pst.executeUpdate();
                if (rowCount > 0) {
                    isIssued = true;
                    
                }else{
                    isIssued = false;
                }
                
            }catch (Exception e){
                e.printStackTrace();
            }
            return isIssued;
            
        }
        
        
        
        // Metodo para actualizar conteo de libros
         public static void updateBookCount(){
             int bookId = Integer.parseInt(txt_bookId.getText());
             try {
                 Connection con = DBConnection.getConnection();
                 String sql = "update book_details set quantity = quantity -1 where book_id = ?";//seleccion de verificación especifica en la tabla dentro de nuestra base de datos
                 PreparedStatement pst = con.prepareStatement(sql);
                 pst.setInt(1, bookId);
                 
                 int rowCount = pst.executeUpdate();
                 
                 if (rowCount > 0) {
                    JOptionPane.showMessageDialog(txt_bookId, "Cantidad de libros disponibles actualizada");
                    int initialCount = Integer.parseInt(lbl_quantity.getText());
                    lbl_quantity.setText(Integer.toString(initialCount -1));
                    
                }else{
                    JOptionPane.showMessageDialog(txt_bookId, "No se pudo actualizar cantidad");
                }
                 
             }catch(Exception e){
                 e.printStackTrace();
             }
             
         }
         
        // Metodo para verificar si el libro ya esta asignado o no
         public static boolean isAlreadyIssued(){
             
             boolean isAlreadyIssued = false;
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
                 
                 if (rs.next ()) {
                     isAlreadyIssued = true;
                 }else{
                     isAlreadyIssued = false;
                 }
                 
                 
             }catch(Exception e){
                 e.printStackTrace();
             }
             return isAlreadyIssued;
         }
        
    
}
