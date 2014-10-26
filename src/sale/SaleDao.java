package sale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleDao {

    public ResultSet list;

    public ResultSet listByName(String nome) {
        Connection connection = null;
        Statement stm = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();
            String sql = "";
            sql += "SELECT t1.id, t2.nome, t1.valor, t1.data_compra ";
            sql += "FROM compras AS t1 ";
            sql += "JOIN clientes AS t2 ON(t1.clientes_id = t2.id) ";
            sql += "WHERE UPPER(t2.nome) like'" + nome.toUpperCase() + "%' ORDER BY data_compra DESC";

            list = stm.executeQuery(sql);

        } catch (SQLException ex) {
            Logger.getLogger(SaleView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaleView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection");
            }
        }
        return list;
    }
}
