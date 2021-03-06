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
import jframe.IssueBookDetails;

/**
 *
 * @author tutu5
 */
public class detalleLibros_Metodos extends IssueBookDetails{
    
    public static void setIssueBookDetailsToTable(){
        
                DefaultTableModel model;
                
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");//Ruta o link de nuestra base de dato
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery("select * from issue_book_details where status = '"+"Pendiente"+"'");//seleccion de verificación especifica en la tabla dentro de nuestra base de datos
            
           while(rs.next()){
            String id = rs.getString("id");
            String bookName = rs.getString("book_name");
            String StudentName = rs.getString("student_name");
            String issueDate = rs.getString("issue_date");
            String dueDate = rs.getString("due_date");
            String status = rs.getString("status");
            
            Object[] obj = {id,bookName,StudentName,issueDate,dueDate,status};
            model = (DefaultTableModel) tbl_IssueBookDetails.getModel();
            model.addRow(obj);
            
        }
           
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
}
