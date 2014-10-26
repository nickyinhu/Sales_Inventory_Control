package inventory;

/*  ACESSO AO ESTOQUE   */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class InventoryDao {

    public ResultSet list;
    public ResultSet product_id;
    Statement stm = null;
    boolean edited;

    /* Read all that is not in inventory */
    public ResultSet readAll() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();
            String sql = "";

            sql += "SELECT t2.nome,t2.id ";
            sql += "FROM estoque  AS t1 ";
            sql += "RIGHT JOIN produtos AS t2 ON (t1.produtos_id = t2.id) ";
            sql += "WHERE NOT EXISTS ( ";
            sql += "SELECT * ";
            sql += "FROM ";
            sql += "produtos  ";
            sql += "WHERE ";
            sql += "t1.produtos_id = t2.id";
            sql += ") ";
            sql += "ORDER BY t2.nome;";

            list = stm.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar a conexão de leitura do estoque");
            }

        }
        return list;
    }

    public void register(String sql2) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();
            int resultado = stm.executeUpdate(sql2);
            if (resultado >= 1) {
                System.out.println("Stored product SUCCESSFULLY!");
                JOptionPane.showMessageDialog(null, "Product Stored  SUCCESSFULLY!");
            } else {
                System.out.println("ERRO AO ESTOCAR PRODUTO!");
                JOptionPane.showMessageDialog(null, "Error storing product, check the data!", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error storing product, check the data!", null, JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar a conexão");
            }

        }

    }

    public ResultSet getId(Object nome) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();

            String sql = "SELECT t1.id,t1.nome,t1.preco_venda"
                    + "	FROM produtos AS t1"
                    + "	WHERE (t1.nome = '" + nome + "');";

            product_id = stm.executeQuery(sql);


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();


            String sql = "SELECT t1.id,t1.quantidade, t1.data_estoque,t2.nome,t2.preco_venda "
                    + "FROM estoque  AS t1 "
                    + "JOIN produtos AS t2 ON (t1.produtos_id = t2.id) "
                    + "where UPPER(nome) like '" + nome.toUpperCase() + "%' ORDER BY id DESC ;";

            list = stm.executeQuery(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();

            String sql = "DELETE FROM estoque WHERE id = " + id + "";

            stm.executeUpdate(sql);
            System.out.println("DELETADO");
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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();
            int resultado = stm.executeUpdate(sql);
            if (resultado >= 1) {
                System.out.println("Product stored SUCCESSFULLY!");
                JOptionPane.showMessageDialog(null, "Stock updated successfully");
            } else {
                System.out.println("ERRO AO ESTOCAR PRODUTO!");
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
