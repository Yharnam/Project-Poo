/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import jframe.DBConnection;
import jframe.HomePage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author tutu5
 */
public class Homepage_Metodos extends HomePage{
    
    
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
    
    
        public static void setDatoCards(){
        Statement st = null;
        ResultSet rs = null;
        
        long l = System.currentTimeMillis();
        java.sql.Date todaysDate = new java.sql.Date(l);
        
        try{
            Connection con = DBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select * from book_details");
            rs.last();
            lbl_noOFBooks.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from student_details");
            rs.last();
            lbl_noOfStudent.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from issue_book_details");
            rs.last();
            lbl_IssueBooks.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from issue_book_details where due_date < '"+todaysDate+"' and status = '"+"Pendiente"+"'");
            rs.last();
            lbl_defaulterList.setText(Integer.toString(rs.getRow()));
                
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    public static void showPieChart(){
        
      //crear dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      try{
          Connection con = DBConnection.getConnection();
          String sql = "select book_name, count(*) as issue_count from issue_book_details group by book_id";
          Statement st = con.createStatement();
          ResultSet rs = st.executeQuery(sql);
          
          while(rs.next()){
              barDataset.setValue(rs.getString("book_name"), new Double(rs.getDouble("issue_count")));
          }
          
          
      }catch(Exception e){
          e.printStackTrace();
      }
      
      //crear chart
       JFreeChart piechart = ChartFactory.createPieChart("Issue Book Details",barDataset, true,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        
        //crear chartPanel para mostrar el grafico.
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelPieChart.removeAll();
        panelPieChart.add(barChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();
    }
    
}
