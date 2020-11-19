package services;

import services.date.DataBaseProperties;

import java.sql.*;

public class GetSQLPassword {
    private static final String user = DataBaseProperties.getProperty("username");
    private static final String password = DataBaseProperties.getProperty("password");
    private static final String url = DataBaseProperties.getProperty("url");
    final private static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    private static String SaltAndHash() throws SQLException {
        Connection conn;
        Statement stmt;
        String result = "";

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");

            while (rs.next()) {
                result = rs.getString(2);
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getPassword() throws SQLException {
        return SaltAndHash();
    }
}
