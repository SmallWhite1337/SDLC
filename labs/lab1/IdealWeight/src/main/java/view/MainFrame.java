package view;

import model.WorkoutData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final JLabel ageValue = new JLabel("—");
    private final JLabel restingPulseValue = new JLabel("—");
    private final JLabel startPulseValue = new JLabel("—");
    private final JLabel targetPulseValue = new JLabel("—");
    private final JButton inputButton = new JButton("Ввод данных");

    public MainFrame() {
        setTitle("Идеальное похудение");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 420);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(560, 360));
        buildUi();
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(15, 15));
        root.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        JLabel title = new JLabel("Идеальное похудение", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel description = new JLabel("<html><center>Расчёт тренировочного пульса по формуле Карвонена<br>" + "для интенсивности 60–70%</center></html>", SwingConstants.CENTER);
        JPanel header = new JPanel(new BorderLayout(5, 10));
        header.add(title, BorderLayout.NORTH);
        header.add(description, BorderLayout.SOUTH);
        JPanel values = new JPanel(new GridLayout(4, 2, 12, 12));
        values.setBorder(BorderFactory.createTitledBorder("Результат"));
        addRow(values, "Возраст, лет:", ageValue);
        addRow(values, "Пульс в покое, уд/мин:", restingPulseValue);
        addRow(values, "Начальный пульс (60%), уд/мин:", startPulseValue);
        addRow(values, "Целевой пульс (70%), уд/мин:", targetPulseValue);
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputButton.setPreferredSize(new Dimension(180, 38));
        bottom.add(inputButton);
        root.add(header, BorderLayout.NORTH);
        root.add(values, BorderLayout.CENTER);
        root.add(bottom, BorderLayout.SOUTH);
        setContentPane(root);
    }

    private void addRow(JPanel panel, String label, JLabel value) {
        panel.add(new JLabel(label));
        value.setHorizontalAlignment(SwingConstants.RIGHT);
        value.setFont(new Font("SansSerif", Font.BOLD, 15));
        panel.add(value);
    }

    public void setOpenInputAction(ActionListener listener) {
        inputButton.addActionListener(listener);
    }

    public void showLastInput(WorkoutData data) {
        if (data != null) {
            ageValue.setText(String.valueOf(data.getAge()));
            restingPulseValue.setText(String.valueOf(data.getRestingHeartRate()));
        }
    }

    public void showResults(WorkoutData data, int startPulse, int targetPulse) {
        showLastInput(data);
        startPulseValue.setText(String.valueOf(startPulse));
        targetPulseValue.setText(String.valueOf(targetPulse));
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
    }
}
