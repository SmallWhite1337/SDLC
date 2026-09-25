package controller;

import model.WorkoutData;
import model.WorkoutModel;
import view.InputDialog;
import view.MainFrame;

import java.beans.PropertyChangeEvent;

public class WorkoutController {
    private final WorkoutModel model;
    private final MainFrame view;

    public WorkoutController(WorkoutModel model, MainFrame view) {
        this.model = model;
        this.view = view;
        view.setOpenInputAction(e -> openInputDialog());
        model.addPropertyChangeListener(this::onModelChanged);
        WorkoutData saved = model.getData();
        view.showLastInput(saved);
    }

    private void openInputDialog() {
        InputDialog dialog = new InputDialog(view, model.getData());
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            WorkoutData data = dialog.getData();
            try {
                validate(data);
                model.setData(data);
                model.calculate();
            } catch (IllegalArgumentException ex) {
                view.showError(ex.getMessage());
            }
        }
    }

    private void validate(WorkoutData data) {
        if (data == null) {
            throw new IllegalArgumentException("Данные не введены.");
        }
        if (data.getAge() < 10 || data.getAge() > 100) {
            throw new IllegalArgumentException("Возраст должен находиться в диапазоне от 10 до 100 лет.");
        }
        if (data.getRestingHeartRate() < 30 || data.getRestingHeartRate() > 150) {
            throw new IllegalArgumentException("Пульс в состоянии покоя должен находиться в диапазоне от 30 до 150 уд/мин.");
        }
        int maxHeartRate = 220 - data.getAge();
        if (data.getRestingHeartRate() >= maxHeartRate) {
            throw new IllegalArgumentException("Пульс покоя должен быть меньше расчётного максимального пульса.");
        }
    }

    private void onModelChanged(PropertyChangeEvent event) {
        if ("startPulse".equals(event.getPropertyName()) || "targetPulse".equals(event.getPropertyName())) {
            view.showResults(model.getData(), model.getStartPulse(), model.getTargetPulse());
        }
        if ("data".equals(event.getPropertyName())) {
            view.showLastInput(model.getData());
        }
    }
}
