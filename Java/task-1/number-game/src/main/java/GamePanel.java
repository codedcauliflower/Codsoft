import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private NumberGuessingGame game;

    private JTextField guessField;
    private JLabel resultLabel;
    private JButton guessButton;
    private JButton playAgainButton;

    private int generatedNumber;
    private int attempts;
    private int maxAttempts = 10;
    private int rounds;
    private int score;

    public GamePanel(NumberGuessingGame game){
        this.game = game;
        initializeGame();
    }

    private void initializeGame(){
        generatedNumber = generateRandomNumber();

        attempts = 0;
        rounds = 0;
        score = 0;

        setLayout(new FlowLayout());

        guessField = new JTextField(10);
        resultLabel = new JLabel("Guess a number between 1 and 100:");
        guessButton = new JButton("Guess");
        playAgainButton = new JButton("Play Again");

        add(guessField);
        add(resultLabel);
        add(guessButton);
        add(playAgainButton);

        guessButton.addActionListener(e -> checkGuess());
        playAgainButton.addActionListener(e -> playAgain());
    }

    private int generateRandomNumber(){
        return (int) (Math.random()*100)+1;
    }

    private void checkGuess(){
        try{
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if(userGuess == generatedNumber) {
                resultLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                score += maxAttempts - attempts + 1;
                rounds++;

                updateScore();

                guessButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            }
            else if(userGuess < generatedNumber) {
                resultLabel.setText("Too low. Try again.");
            } else{
                resultLabel.setText("Too high. Try again.");
            }

            if(attempts == maxAttempts){
                resultLabel.setText("Sorry, you've run out of attempts. The correct number was " + generatedNumber + ".");
                playAgainButton.setEnabled(true);
                guessButton.setEnabled(false);
            }
        } catch (NumberFormatException ex){
            resultLabel.setText("Invalid input. Please enter a number.");
        }
    }

    private void playAgain(){
        generatedNumber = generateRandomNumber();
        attempts = 0;
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
        resultLabel.setText("Guess a number between 1 and 100:");
    }

    private void updateScore() {
        resultLabel.setText(resultLabel.getText() + " Score: " + score);
    }
}