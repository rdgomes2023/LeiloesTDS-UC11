package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class ConectaDAO {
    
    public Connection connectDB(){
        Connection conn = null;
        String URL = "jdbc:mysql://localhost/uc11";
        String usuario = "root";
        String senha = "lorobeleza";
        
        try {
        
            conn = DriverManager.getConnection(URL, usuario, senha);
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
