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
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jframe.DBConnection;
import static jframe.ManageStudents.tbl_studentsDetails;
import jframe.ViewAllRecord;

/**
 *
 * @author tutu5
 */
public class DatosDeEstado_Metodos extends ViewAllRecord{
    
    
    
    public static void setIssueBookDetailsToTable(){
        
                DefaultTableModel model;
                
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery("select * from issue_book_details");
            
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
    
    
    public static void clearTable(){
            DefaultTableModel model = (DefaultTableModel) tbl_IssueBookDetails.getModel();
            model.setRowCount(0);
            
        }
    
    public static void search(){
        
                DefaultTableModel model;
        
        Date uFromDate = date_fromDate.getDatoFecha();
        Date uToDate = date_toDate.getDatoFecha();
        
        long once = uFromDate.getTime();
        long doce = uToDate.getTime();
        
        java.sql.Date fromDate = new java.sql.Date(once);
        java.sql.Date toDate = new java.sql.Date(doce);
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_details where issue_date BETWEEN ? and ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, fromDate);
            pst.setDate(2, toDate);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next() == false) {
                JOptionPane.showMessageDialog(tbl_IssueBookDetails, "No se encontraron datos");
            }else{
               
                    while(rs.next()){
                    String id = rs.getString("id");
                    String bookName = rs.getString("book_name");
                    String StudentName = rs.getString("student_name");
                    String issueDate = rs.getString("issue_date");
                    String dueDate = rs.getString("due_date");
                    String status = rs.getString("status");

                    Object[] obj = {id, bookName, StudentName, issueDate, dueDate, status};
                    model = (DefaultTableModel) tbl_IssueBookDetails.getModel();
                    model.addRow(obj);                
                }                
            }
                  
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    
}
