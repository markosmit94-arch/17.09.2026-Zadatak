package hr.java.hibernate.acid.test;

import java.sql.*;

public class SqlDatabaseConnection {

    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://localhost:8081;"
                        + "database=article_web_shop;"
                        + "user=sa;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT id, naziv, sifra, cijena, tip, kolicina from dbo.Hardware";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
