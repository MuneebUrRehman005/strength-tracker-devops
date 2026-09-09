public class Exercise {
    private String name;       // e.g. "Bicep Curl"
    private int sets;
    private int reps;
    private double weight;     // in kg or lbs, your choice

    public Exercise(String name, int sets, int reps, double weight) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public String getName() { return name; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public double getWeight() { return weight; }

    @Override
    public String toString() {
        return name + " - " + sets + " sets x " + reps + " reps @ " + weight + " kg";
    }
}