package sale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleData {

    public ResultSet list;

    public ResultSet listByName(String name) {
        Connection connection = null;
        Statement stm = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String sql = "";
            sql += "SELECT t1.id, t2.name, t1.value, t1.date_shopping ";
            sql += "FROM shopping AS t1 ";
            sql += "JOIN client AS t2 ON(t1.client_id = t2.id) ";
            sql += "WHERE UPPER(t2.name) like'" + name.toUpperCase() + "%' ORDER BY date_shopping DESC";

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
