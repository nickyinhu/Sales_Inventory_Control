package order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class OrderData {

    public ResultSet list;
    public ResultSet produto_id;
    Statement stm = null;

    public ResultSet listAllCustomers() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT id,name FROM client ORDER BY name;";
            list = stm.executeQuery(sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection reading stock");
            }

        }
        return list;
    }

    public ResultSet listAllProducts() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT t1.id, t1.product_id, t1.Quantity, t2.name, t2.price_cost, t2.price_sale "
                    + "FROM stock  AS t1 "
                    + "JOIN product AS t2 ON (t1.product_id = t2.id) "
                    + "ORDER BY t2.name "
                    + ";";

            list = stm.executeQuery(sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection reading stock");
            }

        }
        return list;
    }

    public ResultSet productsComboBox(String name) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();

            String sql = "SELECT t1.id, t1.product_id, t1.Quantity, t2.name, t2.price_cost, t2.price_sale"
                    + " FROM stock  AS t1"
                    + " JOIN product AS t2 ON (t1.product_id = t2.id)"
                    + " where UPPER(t2.name) like '" + name.toUpperCase() + "%' order by name";

            list = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OrderData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection");
            }
        }
        return list;
    }

    public ResultSet readUniqueProduct(String name) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();

            String sql = "SELECT t1.id, t1.product_id, t1.Quantity, t2.name, t2.price_cost, t2.price_sale "
                    + "FROM stock  AS t1 "
                    + "JOIN product AS t2 ON (t1.product_id = t2.id) "
                    + "WHERE t2.name = '" + name + "' ;";


            list = stm.executeQuery(sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection reading stock");
            }

        }
        return list;
    }

    /*UPDATES E INSERTS*/
    public void UpdateInventory(int newQuantity, int id) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();

            stm.executeUpdate("UPDATE stock SET Quantity= " + newQuantity + " WHERE id =" + id + ";");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection reading stock");
            }

        }
    }

    public void InsertCompras(int client, String total, String data) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();

            stm.executeUpdate("INSERT INTO compras (client_id,value,data_compra) VALUES ( " + client + " , " + total + " , '" + data + "' );");

            JOptionPane.showMessageDialog(null, "ORDER Successfully Saved!");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR SAVING  , CHECK DATA", null, JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR SAVING , CHECK DATA", null, JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "MAKE SURE IF THE DATA IS CORRECT", null, JOptionPane.WARNING_MESSAGE);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection reading stock");
            }

        }
    }
}
