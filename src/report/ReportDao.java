package report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReportDao {

    public ResultSet list;
    public ResultSet product_id;
    Statement stm = null;
    public boolean errorFilter;
    public boolean filterAll;

    public ResultSet listPurchaseToday(String date) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT t2.nome, t1.valor, t1.data_compra"
                    + " FROM compras  AS t1"
                    + " JOIN clientes AS t2 ON (t1.clientes_id = t2.id) "
                    + "WHERE t1.data_compra = '" + date + "' "
                    + " ORDER BY t1.id DESC; ";

            list = stm.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection of reading in database");
            }

        }
        return list;
    }

    public ResultSet listPurchaseOverall() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT t2.nome, t1.valor, t1.data_compra"
                    + " FROM compras  AS t1"
                    + " JOIN clientes AS t2 ON (t1.clientes_id = t2.id) "
                    + " ORDER BY t1.data_compra DESC; ";

            list = stm.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection of reading in database");
            }

        }
        return list;
    }

    public ResultSet sumToday(String date) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT sum(valor) AS soma"
                    + " FROM compras"
                    + " WHERE data_compra = '" + date + "' "
                    + " ;";

            list = stm.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection of reading in database");
            }

        }
        return list;
    }

    public ResultSet sumOverall() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT sum(valor) AS soma"
                    + " FROM compras"
                    + " ;";

            list = stm.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection of reading in database");
            }

        }
        return list;
    }

    public ResultSet filter(String filter, String valueFilter) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoivo", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT t2.nome AS nome, t1.valor AS valor, t1.data_compra"
                    + " FROM compras  AS t1"
                    + " JOIN clientes AS t2 ON (t1.clientes_id = t2.id) ";

            /*VENDO QUAL O FILTRO ESCOHIDO*/
            if (filter == "name" || filter == "nome" ) {
                sql += " WHERE UPPER(nome) like '" + valueFilter.toUpperCase() + "%' ;";
            } else {
                sql += " WHERE data_compra = '" + valueFilter + "' ;";
            }
            list = stm.executeQuery(sql);
        } catch (SQLException ex) {
            errorFilter = true;
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection of reading in database");
            }

        }
        return list;
    }
}
