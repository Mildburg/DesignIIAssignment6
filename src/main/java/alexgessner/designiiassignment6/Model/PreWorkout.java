package alexgessner.designiiassignment6.Model;

public record PreWorkout(int preWorkoutID, String preName, int caffeineAmount, int lCitrullineAmount, int betaAlanineAmount) {

    @Override
    public String toString(){
        String info = "";

        info += "ID: " + preWorkoutID + " Name: " + preName + " Caffeine " + caffeineAmount + " L-Citrulline: " + lCitrullineAmount
                + " Beta Alanine " + betaAlanineAmount;

        return info;
    }
}
