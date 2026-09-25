import controller.WorkoutController;
import model.WorkoutModel;
import view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WorkoutModel model = new WorkoutModel();
            MainFrame view = new MainFrame();
            new WorkoutController(model, view);
            view.setVisible(true);
        });
    }
}
