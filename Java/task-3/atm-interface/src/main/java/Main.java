import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000.0);
        atm atm = new atm(bankAccount);
        UserInterface ui = new UserInterface(atm);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ui.setVisible(true);
            }
        });
    }
}
