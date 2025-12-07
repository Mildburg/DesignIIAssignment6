package alexgessner.designiiassignment6.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Class for connecting to the database and doing anything with the records inside.
 * Updating, deleting, searching
 */

public class PreWorkoutDAO  {
    private static final String url = "jdbc:mysql://localhost:3306/sys";
    private static final String user = "root";
    private static final String password = "Motherrussia62!";

    /**
     * Method that gets the Connection to the database.
     * @return - returns a connection.
     */
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(url, user, password);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets all items in the database Currently
     * @return - returns an ObservableList<PreWorkout> for the ListView.
     */
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

    /**
     * Method for searching by name
     * @param preName - name String
     * @return - returns a list of all preWorkouts that contain the search in their name.
     */
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

    /**
     * Gets the last used ID for adding new PreWorkouts.
     * @return - returns the last used ID.
     */
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

    /**
     * Method for adding PreWorkouts to the database.
     * @param preWorkout - preWorkout to be added
     */
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

    /**
     * Removes the Preworkout from the database.
     * @param preWorkout - preWorkout to be removed.
     */
    public static void removePreWorkout(PreWorkout preWorkout){
        try(Connection con = getConnection()){
            String removeQuery = "DELETE FROM preworkouttable WHERE preWorkoutID = '" + preWorkout.preWorkoutID() + "'";
            PreparedStatement ps = con.prepareStatement(removeQuery);
            ps.executeUpdate();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
