package ui_automation.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void openConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(ConfigurationReader.getProperty("ui-config.properties","mysql.url"),
                ConfigurationReader.getProperty("ui-config.properties","yollhrm.database.username"),
                ConfigurationReader.getProperty("ui-config.properties","yollhrm.database.password"));
        }

    public static List<Map<String, Object>> executeSQLQuery(String query) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<Map<String, Object>> table = new ArrayList<>();

        while(resultSet.next()){
            Map<String, Object> map = new HashMap<>();
            for(int column =1; column<=columnCount; column++){
                map.put(metaData.getColumnName(column), resultSet.getObject(column));
            }
            table.add(map);
        }
        return table;
    }

    public static void closeConnection(){

        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}








