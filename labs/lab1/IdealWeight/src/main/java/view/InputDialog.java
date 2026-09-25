package view;

import model.WorkoutData;

import javax.swing.*;
import java.awt.*;

public class InputDialog extends JDialog {
    private final JTextField ageField = new JTextField(12);
    private final JTextField restingPulseField = new JTextField(12);
    private boolean confirmed;
    private WorkoutData data;
    public InputDialog(Frame owner, WorkoutData previousData) {
        super(owner, "Ввод данных", true);
        setSize(430, 240);
        setLocationRelativeTo(owner);
        if (previousData != null) {
            ageField.setText(String.valueOf(previousData.getAge()));
            restingPulseField.setText(String.valueOf(previousData.getRestingHeartRate()));
        }
        buildUi();
    }

    private void buildUi() {
        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        form.add(new JLabel("Возраст, лет:"));
        form.add(ageField);
        form.add(new JLabel("Пульс в покое, уд/мин:"));
        form.add(restingPulseField);
        JButton calculateButton = new JButton("Рассчитать");
        JButton cancelButton = new JButton("Отмена");
        calculateButton.addActionListener(e -> confirmInput());
        cancelButton.addActionListener(e -> dispose());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttons.add(calculateButton);
        buttons.add(cancelButton);
        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(calculateButton);
    }

    private void confirmInput() {
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            int restingPulse = Integer.parseInt(restingPulseField.getText().trim());
            data = new WorkoutData(age, restingPulse);
            confirmed = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Введите целые числовые значения возраста и пульса.", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public WorkoutData getData() {
        return data;
    }
}
