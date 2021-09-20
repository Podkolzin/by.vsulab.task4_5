//package by.vgulab.epam.util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Connector {
//
//    private static String jdbcUrl;
//    private static String jdbcUser;
//    private static String jdbcPassword;
//    private static int minSize;
//    private static int maxSize;
//    private static int connectTimeOut;

//    public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword, int minSize, int maxSize, int connectTimeOut) throws ClassNotFoundException {
//        Class.forName(jdbcDriver);
//        Connector.jdbcUrl = jdbcUrl;
//        Connector.jdbcUser = jdbcUser;
//        Connector.jdbcPassword = jdbcPassword;
//        Connector.minSize = minSize;
//        Connector.maxSize = maxSize;
//        Connector.connectTimeOut = connectTimeOut;
//
//
//    }
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
//    }




//}
