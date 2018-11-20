package conexionBD;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conectar {
    private Connection con;

    public Conectar() {
        con=null;
    }
    
    
    public Connection conexion(){        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/t_plaza", "root", "");
            JOptionPane.showMessageDialog(null, "Conexion establecida .......");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en la conexion "+ex.getMessage());        
        }
        return con;        
    }   
}
