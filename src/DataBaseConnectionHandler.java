import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DataBaseConnectionHandler {
    public DataBaseConnectionHandler() {
    }
    public ArrayList<Crime> createConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Cime_db","root","Alireza_1378");
           Statement statement = connection.createStatement();
           ResultSet res = statement.executeQuery("SELECT * from crimes");
            ArrayList<Crime>crimes = new ArrayList<Crime>();
           while (res.next()){
               double x = res.getDouble("x");
               double y = res.getDouble("y");
               int kind = res.getInt("kind");
               Date date = res.getDate("date");
               int userId = res.getInt("user_id");
               Point point = new Point(x,y);
               Crime crime = new Crime(point , kind , userId,date);
               crimes.add(crime);
           }
//           for (int i = 0 ; i < crimes.size(); i++){
//               System.out.println(crimes.get(i).getCoordinates().getLatitude());
//               System.out.println(crimes.get(i).getCoordinates().getLongitude());
//               System.out.println(crimes.get(i).getCrimeType());
//
//           }
            System.out.println("database connection successful");;

            return crimes;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
