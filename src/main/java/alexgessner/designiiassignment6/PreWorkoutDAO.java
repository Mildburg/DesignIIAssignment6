package alexgessner.designiiassignment6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PreWorkoutDAO  {
    private static final String url = "jdbc:mysql://localhost:3306/sys";
    private static final String user = "root";
    private static final String password = "Motherrussia62!";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(url, user, password);
        }
        catch(Exception e){
            System.out.println("Error Connecting to Database!");
            return null;
        }
    }

    public static ArrayList<PreWorkout> getPreWorkout(){
        ArrayList<PreWorkout> preWorkouts = new ArrayList<>();
        try(Connection con = getConnection()){
            String query = "SELECT * FROM sys.preworkouttable";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                preWorkouts.add(new PreWorkout(rs.getInt("preWorkoutID"), rs.getString("preName"),
                        rs.getInt("caffeineAmount"), rs.getInt("lCitrullineAmount"),
                        rs.getInt("betaAlanineAmount")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return preWorkouts;
    }
}
