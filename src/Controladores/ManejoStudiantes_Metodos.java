/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import java.sql.*;
import javax.swing.table.*;
import jframe.DBConnection;
import jframe.ManageStudents;

/**
 *
 * @author tutu5
 */
public class ManejoStudiantes_Metodos extends ManageStudents {
    
    public static void setStudentDetailsToTable(){
                DefaultTableModel model;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery("select * from student_details");
            
           while(rs.next()){
            String studentId = rs.getString("student_id");
            String studentName = rs.getString("name");
            String course = rs.getString("course");
            String branch = rs.getString("branch");
            
            Object[] obj = {studentId, studentName, course, branch};
            model = (DefaultTableModel) tbl_studentsDetails.getModel();
            model.addRow(obj);
            
        }
           
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
        
        public static boolean addStudent() {
            
            String studentName,course, branch;
            int studentId;
            boolean isAdded = false;
            studentId = Integer.parseInt(txt_studentId.getText());
            studentName = txt_studentName.getText();
            course = combo_CourseName.getSelectedItem().toString();
            branch = combo_branch.getSelectedItem().toString();
            
            try{
                Connection con = DBConnection.getConnection();
                String sql = "insert into student_details value(?,?,?,?)";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setInt(1, studentId);
                pst.setString(2, studentName);
                pst.setString(3, course);
                pst.setString(4, branch);
                
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
    
    
    public static boolean updateStudents(){
            
            String studentName,course, branch;
            int studentId;
            boolean isUpdated = false;
            studentId = Integer.parseInt(txt_studentId.getText());
            studentName = txt_studentName.getText();
            course = combo_CourseName.getSelectedItem().toString();
            branch = combo_branch.getSelectedItem().toString();
            
            try{
                Connection con = DBConnection.getConnection();
                String sql = "update student_details set name = ?,course =?, branch =? where student_id = ?";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, studentName);
                pst.setString(2, course);
                pst.setString(3, branch);
                pst.setInt(4,studentId);
                
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
        
        //Metodo para borrar estudiante
        
        public static boolean deleteStudent(){
            int studentId;
            boolean isDeleted = false;
            studentId = Integer.parseInt(txt_studentId.getText());
            
            try{
                
                Connection con = DBConnection.getConnection();
                String sql ="delete from student_details where student_id =?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, studentId);
                
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
        
        
        
        public static void clearTable(){
            DefaultTableModel model = (DefaultTableModel) tbl_studentsDetails.getModel();
            model.setRowCount(0);
            
        }
    
    
    public static void ClickTable(){
            
             int rowNo = tbl_studentsDetails.getSelectedRow();
        TableModel model = tbl_studentsDetails.getModel();
        
        txt_studentId.setText(model.getValueAt(rowNo,0).toString());
        txt_studentName.setText(model.getValueAt(rowNo,1).toString());
        combo_CourseName.setSelectedItem(model.getValueAt(rowNo,2).toString());
        combo_branch.setSelectedItem(model.getValueAt(rowNo,3).toString());    
        }
    
}
