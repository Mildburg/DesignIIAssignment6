package alexgessner.designiiassignment6.Model;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<PreWorkout> getPreWorkout(){
        ObservableList<PreWorkout> preWorkouts = FXCollections.observableArrayList();
        try(Connection con = getConnection()){
            String query = "SELECT * FROM preworkouttable";
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

    public static ObservableList<PreWorkout> searchPreWorkouts(String preName){
        ObservableList<PreWorkout> searchedPreWorkouts = FXCollections.observableArrayList();
        try(Connection connection = getConnection()){
            String query = "SELECT * FROM preworkouttable WHERE preName LIKE '%" + preName + "%'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                searchedPreWorkouts.add(new PreWorkout(rs.getInt("preWorkoutID"), rs.getString("preName"),
                        rs.getInt("caffeineAmount"), rs.getInt("lCitrullineAmount"),
                        rs.getInt("betaAlanineAmount")));
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);

        }

        return searchedPreWorkouts;
    }

    public static int getLastID(){
        try(Connection conn = getConnection()){
            String getLastUsedID = "SELECT MAX(preWorkoutID) FROM preworkouttable";
            PreparedStatement ps = conn.prepareStatement(getLastUsedID);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt(1);
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return -1;
    }

    public static void addPreWorkout(PreWorkout preWorkout){
        try(Connection conn = getConnection()){
            String insertQuery = "INSERT INTO preworkouttable VALUES (" + preWorkout.preWorkoutID() + ", '" + preWorkout.preName()
                    + "', " + preWorkout.caffeineAmount() + ", " + preWorkout.lCitrullineAmount() + ", " +
                    preWorkout.lCitrullineAmount() + ");";

            PreparedStatement ps = conn.prepareStatement(insertQuery);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
