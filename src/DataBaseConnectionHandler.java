import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DataBaseConnectionHandler {
    Connection connection;
    Statement statement;
    int readRows;
    public DataBaseConnectionHandler() {
        readRows = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Crime_db", "root", "hassan@0111Aqa");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ArrayList<Crime> createConnection() {
        try {
            ResultSet res = statement.executeQuery("SELECT * from crimes LIMIT " + String.valueOf(readRows) + ", 1000000000");
            ArrayList<Crime> crimes = new ArrayList<>();
            while (res.next()) {
                double x = res.getDouble("longitude");
                double y = res.getDouble("latitude");
                int kind = res.getInt("kind");
                Date date = res.getDate("date");
                int userId = res.getInt("user_id");
                Point point = new Point(x, y);
                Crime crime = new Crime(point, kind, userId, date);
                crimes.add(crime);
            }
            readRows += crimes.size();
//           for (int i = 0 ; i < crimes.size(); i++){
//               System.out.println(crimes.get(i).getCoordinates().getLatitude());
//               System.out.println(crimes.get(i).getCoordinates().getLongitude());
//               System.out.println(crimes.get(i).getCrimeType());
//
//           }
            System.out.println("database connection successful");
            ;

            return crimes;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
