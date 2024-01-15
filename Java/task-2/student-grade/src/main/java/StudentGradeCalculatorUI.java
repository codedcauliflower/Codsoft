import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculatorUI extends JFrame {
    private JTextField[] subjectFields;
    private JButton calculateButton;

    public StudentGradeCalculatorUI() {
        setTitle("Student Grade Calculator");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,2));

        initializeComponents();
        addComponents();
        attachListeners();
    }

    private void initializeComponents() {
        subjectFields = new JTextField[5];

        for(int i = 0; i < subjectFields.length; i++) {
            subjectFields[i] = new JTextField();
        }
        calculateButton = new JButton("Calculate");
    }

    private void addComponents() {
        add(new JLabel("Enter marks for each subject:"));

        add(new JPanel());

        for(int i = 1; i <= subjectFields.length; i++) {
            add(new JLabel("Subject " + i + ":"));
            add(subjectFields[i-1]);
        }
        add(calculateButton);
    }

    private void attachListeners() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayResults();
            }
        });
    }

    private void calculateAndDisplayResults() {
        int totalMarks = 0;
        for(JTextField subjectField : subjectFields) {
            totalMarks += Integer.parseInt(subjectField.getText());
        }

        double averagePercentage = (double) totalMarks / subjectFields.length;
        char grade = calculateGrade(averagePercentage);

        JOptionPane.showMessageDialog(this, "Total Marks: " + totalMarks
                + "\nAverage Percentage: " + averagePercentage + "%"
                + "\nGrade: " + grade);
    }

    private char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if(averagePercentage >= 70) {
            return 'C';
        } else if(averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}