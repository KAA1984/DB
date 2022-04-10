import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class CreateTable {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "root";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //private static final DateFormat dateFormat = DateFormat.getDateInstance();
        ///dd-MMM-yyyy HH:mm:ss
    public static void main(String[] argv) {

        try {

            createDbUserTable();

        } catch (SQLException|ParseException e) {

            System.out.println(e.getMessage());

        }

    }

    private static void createDbUserTable() throws SQLException, ParseException {

        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE DBUSERS("
                + "USER_ID VARCHAR (5) NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "CREATED_BY VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, "
                + "PRIMARY KEY (USER_ID) "
                + "UPDATED_AT TIMESTAMP"
                + ")";

        String insert = "INSERT INTO DBUSERS"
                + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) "
                + "VALUES"
                + "('5','user5','admin', "
                + " str_to_date('"
                + getCurrentTimeStamp()
                + "','yyyy-MM-dd HH:mm:ss'))";

        //String select = "SELECT * from dbusers";

        String updateTableSQL = "UPDATE DBUSERS"
                + " SET USERNAME = 'user 2' "
                + " WHERE USER_ID = '2'";

        String deleteTableSQL = "DELETE from DBUSERS WHERE USER_ID = '1'";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            //ResultSet resultSet = statement.executeQuery(select);

            //for other statements:
            //statement.executeUpdate(updateTableSQL);
            statement.executeUpdate(insert);
            //statement.execute(createTableSQL);
            //statement.execute(deleteTableSQL);

           // while (resultSet.next()) {
            //   System.out.println(resultSet.getString("USERNAME"));
           // }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (statement != null) {
                statement.close();
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

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

    private static String getCurrentTimeStamp(){
        /*DateFormat dateFormat = DateFormat.getDateTimeInstance();
        DateFormat dateFormat = DateFormat.getDateInstance();
        Date today = new Date();
        return dateFormat.format(today.getTime());

         */
        java.util.Date today = new java.util.Date();
        return dateFormat.format(today.getTime());
    }
}