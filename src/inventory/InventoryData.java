package inventory;

/*  ACCESS STOCK   */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class InventoryData {

    public ResultSet list;
    public ResultSet product_id;
    Statement stm = null;
    boolean edited;

    /* Read all that is not in inventory */
    public ResultSet readAll() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String sql = "";

            sql += "SELECT t2.name,t2.id ";
            sql += "FROM stock  AS t1 ";
            sql += "RIGHT JOIN product AS t2 ON (t1.product_id = t2.id) ";
            sql += "WHERE NOT EXISTS ( ";
            sql += "SELECT * ";
            sql += "FROM ";
            sql += "product  ";
            sql += "WHERE ";
            sql += "t1.product_id = t2.id";
            sql += ") ";
            sql += "ORDER BY t2.name;";

            list = stm.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection of inventory database");
            }

        }
        return list;
    }

    public void register(String sql2) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            int resultado = stm.executeUpdate(sql2);
            if (resultado >= 1) {
                System.out.println("Stored product SUCCESSFULLY!");
                JOptionPane.showMessageDialog(null, "Product Stored  SUCCESSFULLY!");
            } else {
                System.out.println("ERROR STORING PRODUCT!");
                JOptionPane.showMessageDialog(null, "Error storing product, check the data!", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error storing product, check the data!", null, JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection");
            }

        }

    }

    public ResultSet getId(Object nome) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();

            String sql = "SELECT t1.id,t1.name,t1.price_sale"
                    + "	FROM product AS t1"
                    + "	WHERE (t1.name = '" + nome + "');";

            product_id = stm.executeQuery(sql);


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InventoryData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing the connection");
            }
        }
        return product_id;
    }

    public ResultSet listInventory(String nome) {

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();


            String sql = "SELECT t1.id,t1.quantity, t1.date_register,t2.name,t2.price_sale "
                    + "FROM stock  AS t1 "
                    + "JOIN product AS t2 ON (t1.product_id = t2.id) "
                    + "where UPPER(name) like '" + nome.toUpperCase() + "%' ORDER BY id DESC ;";

            list = stm.executeQuery(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InventoryData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error closing connection");
            }
        }
        return list;

    }

    public void remove(Object id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();

            String sql = "DELETE FROM stock WHERE id = " + id + "";

            stm.executeUpdate(sql);
            System.out.println("DELETED");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error removing !", null, JOptionPane.WARNING_MESSAGE);
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
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            int result = stm.executeUpdate(sql);
            if (result >= 1) {
                System.out.println("Product stored SUCCESSFULLY!");
                JOptionPane.showMessageDialog(null, "Stock updated successfully");
            } else {
                System.out.println("ERROR STORING PRODUCT!");
                JOptionPane.showMessageDialog(null, "Error updating the stock, verify the data !", null, JOptionPane.WARNING_MESSAGE);
            }
            edited = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating the stock, verify the data !", null, JOptionPane.WARNING_MESSAGE);
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
