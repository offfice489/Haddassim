package automationjar.testscript;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  


public class SQLClass {  

    private static Connection conn;  
    
    private Connection connect() {  

    	try {  
            // db parameters  
            String url = "jdbc:sqlite:C:/sqlite/JTP11.db";  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            try {  
                if (conn != null) {  
                    conn.close();  
                }  
            } catch (SQLException ex) {  
                System.out.println(ex.getMessage());  
            }  
        }
    	return conn;
    }
    
    public void createNewTable() {  
        // SQLite connection string  
        String url = "jdbc:sqlite:C:/sqlite/JTP1.db";  
          
        // SQL statement for creating a new table  
        // SQL statement for creating a new table  
        String sql = "CREATE TABLE IF NOT EXISTS user_details (\n"
                + " id integer PRIMARY KEY,\n"  
                + " password text NOT NULL\n"  
                + ");";  
          
          
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    
    
    public void exampleFunc(int id, String password) {  
        // SQLite connection string  
        String url = "jdbc:sqlite:C:/sqlite/JTP1.db";  
          
        // SQL statement for creating a new table  
        // SQL statement for creating a new table  
        String sql = "INSERT INTO user_details(id, password) VALUES(123,'passeclipse')";  
          
          
        try{  
        	
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement(); 
            //pstmt.executeUpdate();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    
    public void insert(int id, String password) {  
        String sql = "INSERT INTO user_details(id, password) VALUES(?,?)";  
   
        try{  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setInt(1, id);  
            pstmt.setString(2, password);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    
    public void selectAll(){  
    	String sql = "SELECT * FROM user_details";  
        
        try {  
            conn = this.connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            // loop through the result set  
            while (rs.next()) {  
                System.out.println(rs.getInt("id") +  "\t" +   
                                   rs.getString("password"));  
            }  
        } catch (SQLException e) {  
            System.out.println("faieled in select all: " + e.getMessage());  
        }  
    }
    
    public static void main(String[] args) {  
    	SQLClass db = new SQLClass();
  	db.connect();
    	
    	db.createNewTable();
//    	
//        db.createNewTable();
        //InsertRecords app = new InsertRecords();  
        // insert three new rows  
        //insert(1111, "passwd!1");  
        //insert(2222, "passwd!1"); 
//    	db.exampleFunc(1,"");
//        db.selectAll();       
//        System.out.println("after select");
    }  
}  







