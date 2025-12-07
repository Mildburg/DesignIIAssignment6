package alexgessner.designiiassignment6.Model;

/**
 * Record for the PreWorkouts stored in the database.
 * @param preWorkoutID - ID
 * @param preName - Name
 * @param caffeineAmount - amount of Caffeine
 * @param lCitrullineAmount - L-Citrulline amount.
 * @param betaAlanineAmount - Beta-Alanine amount.
 */
public record PreWorkout(int preWorkoutID, String preName, int caffeineAmount, int lCitrullineAmount, int betaAlanineAmount) {

    @Override
    public String toString(){
        String info = "";

        info += "ID: " + preWorkoutID + " Name: " + preName + " Caffeine: " + caffeineAmount + " L-Citrulline: " + lCitrullineAmount
                + " Beta Alanine: " + betaAlanineAmount;

        return info;
    }
}
