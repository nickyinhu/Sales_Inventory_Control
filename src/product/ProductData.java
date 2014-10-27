package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProductData {

    public ResultSet list;
    Statement stm = null;
    int clean_texts;
    protected boolean edited; //To not closed the page even a error occur 

    public ResultSet search(String name) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();

            String sql = "select * from product where UPPER(name) like '" + name.toUpperCase() + "%' ORDER BY id DESC";

            list = stm.executeQuery(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection");
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
            int report = stm.executeUpdate(sql);
            if (report >= 1) {
                clean_texts = 1;
                System.out.println("product sucess!");
                JOptionPane.showMessageDialog(null, "Product successfully registered !");
            } else {
                System.out.println("Error registering the user !");
                JOptionPane.showMessageDialog(null, "Error registering the product, check the data !", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error registering the product, check the data !", null, JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection");
            }
        }
    }

    public void remove(Object id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();

            String sql = "DELETE FROM product WHERE id = " + id + "";

            stm.executeUpdate(sql);
            System.out.println("DELETED");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Product can being used in inventory! First remove it from the inventory!", null, JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection of reading in inventory");
            }

        }
    }

    public void edit(String sql) {
        Connection connection = null;
        Statement stm = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            int report = stm.executeUpdate(sql);
            if (report >= 1) {
                edited = true;
                System.out.println("Product updated with sucess !");
                JOptionPane.showMessageDialog(null, "Product Successfully Updated !");
            } else {
                edited = false;
                System.out.println("Error updating product !");
                JOptionPane.showMessageDialog(null, "Error updating the product, check the data !", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println("Error updating product !");
            JOptionPane.showMessageDialog(null, "Error updating the product, check the data !", null, JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection");
            }
        }
    }
}
