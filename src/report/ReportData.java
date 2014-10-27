package report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReportData {

    public ResultSet list;
    public ResultSet product_id;
    Statement stm = null;
    public boolean errorFilter;
    public boolean filterAll;

    public ResultSet listPurchaseToday(String date) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT t2.name, t1.value, t1.date_shopping"
                    + " FROM shopping  AS t1"
                    + " JOIN client AS t2 ON (t1.client_id = t2.id) "
                    + "WHERE t1.date_shopping = '" + date + "' "
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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT t2.name, t1.value, t1.date_shopping"
                    + " FROM shopping  AS t1"
                    + " JOIN client AS t2 ON (t1.client_id = t2.id) "
                    + " ORDER BY t1.date_shopping DESC; ";

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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT sum(value) AS soma"
                    + " FROM shopping"
                    + " WHERE date_shopping = '" + date + "' "
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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT sum(value) AS soma"
                    + " FROM shopping"
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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "solar", "solar");
            stm = connection.createStatement();
            String sql = "SELECT t2.name AS name, t1.value AS value, t1.date_shopping"
                    + " FROM shopping  AS t1"
                    + " JOIN client AS t2 ON (t1.client_id = t2.id) ";

            /*VENDO QUAL O FILTRO ESCOHIDO*/
            if (filter == "name" || filter == "name" ) {
                sql += " WHERE UPPER(name) like '" + valueFilter.toUpperCase() + "%' ;";
            } else {
                sql += " WHERE date_shopping = '" + valueFilter + "' ;";
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
