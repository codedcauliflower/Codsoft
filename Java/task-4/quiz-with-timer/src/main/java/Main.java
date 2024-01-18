import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {
    private JFrame frame;
    private QuizQuestion currentQuestion;
    private int currentQuestionIndex;
    private int score;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JButton nextButton;
    private QuizTimer quizTimer;
    private QuizQuestion[] questions;

    public Main() {
        frame = new JFrame("Quiz Application");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        optionButtons = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup(); // Added ButtonGroup for radio button grouping
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            buttonGroup.add(optionButtons[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                loadNextQuestion();
            }
        });

        for (int i = 0; i < 4; i++) {
            optionButtons[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    checkOptionSelection();
                }
            });
        }

        JPanel panel = new JPanel();
        panel.add(questionLabel);
        for (int i = 0; i < 4; i++) {
            panel.add(optionButtons[i]);
        }
        panel.add(nextButton);

        frame.add(panel);

        initializeQuiz();
    }

    private void checkOptionSelection() {
        // Check if any option is selected
        boolean optionSelected = false;
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                optionSelected = true;
                break;
            }
        }

        nextButton.setEnabled(optionSelected);
    }

    private void initializeQuiz() {
        // Initialize quiz questions and timer
        questions = new QuizQuestion[]{
                new QuizQuestion("What is the capital of France?", new String[]{"Berlin", "Paris", "London", "Madrid"}, 1),
                new QuizQuestion("Which planet is known as the Red Planet?", new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 0),
                new QuizQuestion("Which planet is the biggest?", new String[]{"Saturn", "Venus", "Jupiter", "Mars"}, 2),
                // Add more questions as needed
        };

        currentQuestionIndex = 0;
        score = 0;
        loadQuestion(questions[currentQuestionIndex]);

        quizTimer = new QuizTimer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update timer display
                frame.setTitle("Time Left: " + quizTimer.getSecondsLeft() + " seconds");
            }
        });
        quizTimer.startTimer();
    }

    private void loadQuestion(QuizQuestion question) {
        // Load question and options into UI
        currentQuestion = question;
        questionLabel.setText(question.getQuestionText());
        String[] options = question.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
        }
        nextButton.setEnabled(false); // Disable Next button until an option is selected
    }

    private void checkAnswer() {
        // Check if any option is selected
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                if (i == currentQuestion.getCorrectOptionIndex()) {
                    score++;
                }
                break;
            }
        }
    }

    private void loadNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            QuizQuestion currentQuestion = questions[currentQuestionIndex];
            loadQuestion(currentQuestion);
        } else if(currentQuestionIndex == questions.length) {
            showResultScreen();
        }

        System.out.println(currentQuestionIndex);
    }




    private void showResultScreen() {
        // Display the final score and a summary of correct/incorrect answers
        frame.setTitle("Quiz Completed");
        JOptionPane.showMessageDialog(frame, "Quiz completed!\nYour Score: " + score + "/"+questions.length);
        frame.dispose(); // Close the application
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().frame.setVisible(true);
            }
        });
    }
}
