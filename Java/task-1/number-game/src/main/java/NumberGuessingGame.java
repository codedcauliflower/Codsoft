import javax.swing.*;

public class NumberGuessingGame extends JFrame {
    private GamePanel gamePanel;

    public NumberGuessingGame(){
        setTitle("Number Guessing Game");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel(this);
        add(gamePanel);
    }
}