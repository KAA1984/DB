import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PreparedSelect {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://127.0.0.1:5432/mydb";
    private static final String DB_USER = "myuser";
    private static final String DB_PASSWORD = "mypass";

    public static void main(String[] argv) {
        try {

            selectRecordsFromTable();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    private static void selectRecordsFromTable() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT * FROM public.dbusers where USERNAME = ? AND USER_ID = ?";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1,"mkyong3");
            preparedStatement.setString(2,"3");

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("select is performed");
            while (rs.next()) {

                String username = rs.getString("username");

                System.out.println("username : " + username);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }


    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

            Class.forName(DB_DRIVER);

        }
        catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;

        }
        catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }
}