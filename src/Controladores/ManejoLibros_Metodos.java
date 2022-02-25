/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static jframe.ManageBooks.tbl_bookDetails;
import static jframe.ManageBooks.txt_authorName;
import static jframe.ManageBooks.txt_bookId;
import static jframe.ManageBooks.txt_bookName;
import static jframe.ManageBooks.txt_quantity;

/**
 *
 * @author tutu5
 */
public class ManejoLibros_Metodos {
    
    
            String book_name,author;
            int book_id, quantity;
            
            //Metodo para insertar datos dentro de la tabla, mostrar y que guarde en la base de datos.
            
        public static void setBookDeailsToTable(){
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
