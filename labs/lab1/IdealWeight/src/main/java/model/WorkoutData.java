package model;

public final class WorkoutData {
    private final int age;
    private final int restingHeartRate;

    public WorkoutData(int age, int restingHeartRate) {
        this.age = age;
        this.restingHeartRate = restingHeartRate;
    }

    public int getAge() {
        return age;
    }

    public int getRestingHeartRate() {
        return restingHeartRate;
    }
}
