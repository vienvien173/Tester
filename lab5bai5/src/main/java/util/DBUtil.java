package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=RegisterAccount;"
                + "encrypt=true;trustServerCertificate=true";

        return DriverManager.getConnection(
                url,
                "sa",
                "vien123"
        );
    }
}
