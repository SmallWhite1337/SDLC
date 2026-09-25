package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.prefs.Preferences;

public class WorkoutModel {
    private static final double START_INTENSITY = 0.60;
    private static final double TARGET_INTENSITY = 0.70;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final Preferences preferences = Preferences.userNodeForPackage(WorkoutModel.class);

    private WorkoutData data;
    private int startPulse;
    private int targetPulse;

    public WorkoutModel() {
        int savedAge = preferences.getInt("age", 30);
        int savedRest = preferences.getInt("restingHeartRate", 70);
        data = new WorkoutData(savedAge, savedRest);
    }

    public void setData(WorkoutData newData) {
        WorkoutData oldData = this.data;
        this.data = newData;
        preferences.putInt("age", newData.getAge());
        preferences.putInt("restingHeartRate", newData.getRestingHeartRate());
        support.firePropertyChange("data", oldData, newData);
    }

    public void calculate() {
        int oldStart = startPulse;
        int oldTarget = targetPulse;
        int maxHeartRate = 220 - data.getAge();
        int heartRateReserve = maxHeartRate - data.getRestingHeartRate();
        startPulse = roundPulse(data.getRestingHeartRate() + heartRateReserve * START_INTENSITY);
        targetPulse = roundPulse(data.getRestingHeartRate() + heartRateReserve * TARGET_INTENSITY);
        support.firePropertyChange("startPulse", oldStart, startPulse);
        support.firePropertyChange("targetPulse", oldTarget, targetPulse);
    }

    private int roundPulse(double value) {
        return (int) Math.round(value);
    }

    public WorkoutData getData() {
        return data;
    }

    public int getStartPulse() {
        return startPulse;
    }

    public int getTargetPulse() {
        return targetPulse;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
