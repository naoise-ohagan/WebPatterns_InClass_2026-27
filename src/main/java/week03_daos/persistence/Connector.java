package week03_daos.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    public Connection getConnecton(){
        try {
            Class.forName(driver);

            try {
                Connection conn = DriverManager.getConnection(url,username, password);
                return conn;
            } catch (SQLException e) {
                System.out.println("Exception: " + e.getMessage() + "\"");
                System.out.println();
            }
        }
    }
}
