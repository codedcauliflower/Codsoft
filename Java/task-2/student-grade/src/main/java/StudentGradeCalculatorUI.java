import javax.swing.*;
import java.awt.*;

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

        for(int i = 1; i <= subjectFields.length; i++) {
            add(new JLabel("Subject " + i + ":"));
            add(subjectFields[i-1]);
        }

        add(calculateButton);
    }

    private void attachListeners() {

    }
}
