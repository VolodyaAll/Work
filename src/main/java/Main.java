import java.sql.*;

/**
 * Created by Energetik on 03.05.2016.
 */

public class Main {
    public static final String DB_URL = "jdbc:h2:file:D:/Install/test";
    public static final String LOGIN = "sa";
    public static final String PASSWORD = "";
    public static final String COLUMN_FAMILE = "Famile";
    public static final String COLUMN_NAME = "Name";
    public static final String H2_DRIVER = "org.h2.Driver";
    public static void main(String[] args)
    {
        String keyFamile = "Литвинов";
        try {
            Class.forName(H2_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM workers WHERE famile = ?"))
        {
            statement.setString(1, keyFamile);

            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next()) {
                    String famile = resultSet.getString(COLUMN_FAMILE);
                    String name = resultSet.getString(COLUMN_NAME);
                    System.out.println(famile + " " + name);
                }
            }
        }

        catch (SQLException e)
        {
            System.out.println("DB acces denided unable" + e.getMessage());
        }

    }
}
