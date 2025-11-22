package marksheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Marksheet extends JFrame implements ActionListener {

    String[] courseCodes = {
        "CSE 2201", "CSE 2202", "CSE 2203", "CSE 2205",
        "CSE 2206", "CSE 2207", "MTH 2201", "BUS 2201", "HUM 2201"
    };

    String[] courseTitles = {
        "Web Engineering", "Web Engineering Sessional", "Data Communication",
        "Computer Architecture and Organization", "Computer Architecture and Organization Sessional",
        "Numerical Methods", "Complex Variable, Probability and Statistics",
        "Financial, Cost and Managerial Accounting", "Bangladesh Studies and History of Independence"
    };

    int[] credits = {3, 1, 3, 3, 1, 3, 3, 3, 2};

    JTextField[] codeFields = new JTextField[courseCodes.length];
    JTextField[] titleFields = new JTextField[courseCodes.length];
    JTextField[] creditFields = new JTextField[courseCodes.length];
    JTextField[] resultFields = new JTextField[courseCodes.length];
    JLabel[] creditXResultLabels = new JLabel[courseCodes.length];

    JButton calculateBtn, editBtn;

    JLabel totalCreditsLabel, totalWeightedLabel, gpaLabel, totalResultLabel;

    boolean isEditable = false;

    public Marksheet() {
        setTitle("CSE 4th Semester GPA Calculator");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(courseCodes.length + 7, 5, 5, 5));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add headers
        mainPanel.add(new JLabel("Course Code"));
        mainPanel.add(new JLabel("Course Title"));
        mainPanel.add(new JLabel("Credit"));
        mainPanel.add(new JLabel("Result (Point)"));
        mainPanel.add(new JLabel("Credit × Result"));

        // Add course rows
        for (int i = 0; i < courseCodes.length; i++) {
            codeFields[i] = new JTextField(courseCodes[i]);
            codeFields[i].setEditable(false);
            mainPanel.add(codeFields[i]);

            titleFields[i] = new JTextField(courseTitles[i]);
            titleFields[i].setEditable(false);
            mainPanel.add(titleFields[i]);

            creditFields[i] = new JTextField(String.valueOf(credits[i]));
            creditFields[i].setEditable(false);
            mainPanel.add(creditFields[i]);

            resultFields[i] = new JTextField();
            mainPanel.add(resultFields[i]);

            creditXResultLabels[i] = new JLabel("0.00");
            mainPanel.add(creditXResultLabels[i]);
        }

        // Empty row
        for (int i = 0; i < 5; i++) mainPanel.add(new JLabel(""));

        // Buttons: Edit and Calculate
        editBtn = new JButton("Edit");
        editBtn.addActionListener(e -> toggleEdit());
        mainPanel.add(editBtn);

        calculateBtn = new JButton("Calculate GPA");
        calculateBtn.addActionListener(this);
        mainPanel.add(calculateBtn);

        // Total Credits
        mainPanel.add(new JLabel("Total Credits:"));
        totalCreditsLabel = new JLabel(String.valueOf(getTotalCredits()));
        mainPanel.add(totalCreditsLabel);
        mainPanel.add(new JLabel(""));

        // Total Result
        mainPanel.add(new JLabel("Sum of Result (Points):"));
        totalResultLabel = new JLabel("0.00");
        mainPanel.add(totalResultLabel);

        // Credit × Result Total
        mainPanel.add(new JLabel("Sum of Credit × Result:"));
        totalWeightedLabel = new JLabel("0.00");
        mainPanel.add(totalWeightedLabel);

        // GPA
        mainPanel.add(new JLabel("GPA:"));
        gpaLabel = new JLabel("0.00");
        mainPanel.add(gpaLabel);
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));

        setContentPane(mainPanel);
        setVisible(true);
    }

    private int getTotalCredits() {
        int sum = 0;
        for (int i = 0; i < creditFields.length; i++) {
            try {
                sum += Integer.parseInt(creditFields[i].getText().trim());
            } catch (NumberFormatException e) {
                // Skip invalid entries
            }
        }
        return sum;
    }

    private void toggleEdit() {
        isEditable = !isEditable;

        for (int i = 0; i < titleFields.length; i++) {
            titleFields[i].setEditable(isEditable);
            creditFields[i].setEditable(isEditable);
            codeFields[i].setEditable(isEditable);
        }

        editBtn.setText(isEditable ? "Lock" : "Edit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double totalWeighted = 0.0;
        double totalResult = 0.0;
        int totalCredits = 0;

        for (int i = 0; i < resultFields.length; i++) {
            String inputPoint = resultFields[i].getText().trim();
            String inputCredit = creditFields[i].getText().trim();
            double point;
            int credit;

            try {
                point = Double.parseDouble(inputPoint);
                if (point < 0 || point > 4.0) throw new NumberFormatException();

                credit = Integer.parseInt(inputCredit);
                if (credit < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid input at course " + codeFields[i].getText().trim() + ": Make sure GPA (0.0 - 4.0) and Credits are valid.");
                return;
            }

            double creditXResult = credit * point;
            creditXResultLabels[i].setText(String.format("%.2f", creditXResult));

            totalWeighted += creditXResult;
            totalResult += point;
            totalCredits += credit;
        }

        double gpa = totalCredits > 0 ? totalWeighted / totalCredits : 0;

        totalCreditsLabel.setText(String.valueOf(totalCredits));
        totalWeightedLabel.setText(String.format("%.2f", totalWeighted));
        totalResultLabel.setText(String.format("%.2f", totalResult));
        gpaLabel.setText(String.format("%.2f", gpa));
    }

    public static void main(String[] args) {
        new Marksheet();
    }
}
