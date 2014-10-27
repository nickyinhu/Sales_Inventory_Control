package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CustomerData {

    public ResultSet list;
    Statement stm = null;
    boolean edited;
    boolean isRegistered = false;

    public ResultSet search(String name) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();

            String sql = "select * from client where UPPER(name) like '" + name.toUpperCase() + "%' ORDER BY id DESC";

            list = stm.executeQuery(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error when closing the connection");
            }
        }
        return list;
    }

    public void register(String sql) {
        Connection connection = null;
        Statement stm = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            int resultado = stm.executeUpdate(sql);
            if (resultado >= 1) {
                System.out.println("User created!!");
                JOptionPane.showMessageDialog(null, "client successfully registered !");
                isRegistered = true;
            } else {
                System.out.println("Erro in customer's registration !");
                JOptionPane.showMessageDialog(null, "Error registering client, check the data !", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error registering client, SSN must be unique !", null, JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error when closing the connection");
            }
        }
    }

    public void readCustomer(String name) {
        Connection connection = null;
        Statement stm = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String SQL = "SELECT * FROM client WHERE id = " + name;
            ResultSet resultado = stm.executeQuery(SQL);
            while (resultado.next()) {
                System.out.println(resultado.getArray("id") + " " + resultado.getArray("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error when closing the connection from update");
                ex.printStackTrace();
            }
        }
    }

    public void edit(String sql) {
        Connection connection = null;
        Statement stm = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            int resultado = stm.executeUpdate(sql);
            if (resultado >= 1) {
                System.out.println("Updated client successfully!");
                JOptionPane.showMessageDialog(null, "Updated client successfully !");
            } else {
                System.out.println("Error in updating the customer !");
                JOptionPane.showMessageDialog(null, "ERROR, check the fields !", null, JOptionPane.WARNING_MESSAGE);
            }
            edited = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR, check the fields, SSN need be unique !", "ATTENTION", JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error when closing the connection from update");
            }

        }
    }
}
