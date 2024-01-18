import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class QuizTimer {
    private Timer timer;
    private int secondsLeft;
    private ActionListener timerAction;

    public QuizTimer(int seconds, ActionListener timerAction) {
        this.secondsLeft = seconds;
        this.timerAction = timerAction;
        initializeTimer();
    }

    private void initializeTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsLeft--;
                timerAction.actionPerformed(e);
                if (secondsLeft <= 0) {
                    stopTimer();
                }
            }
        });
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}