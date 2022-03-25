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
import java.sql.Date;
import javax.swing.table.DefaultTableModel;
import jframe.DefaulterList;


/**
 *
 * @author tutu5
 */
public class detalleRetrasos_Metodos extends DefaulterList{
    
    public static void setIssueBookDetailsToTable(){
        DefaultTableModel model;
        System.currentTimeMillis();
        
        long l = System.currentTimeMillis();
        Date todaysDate = new Date(l);
                
                
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            PreparedStatement pst = con.prepareStatement("select * from issue_book_details where due_date < ? and status = ? ");
            pst.setDate(1, todaysDate);
            pst.setString(2, "Pendiente");
            ResultSet rs = pst.executeQuery();
            
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
