import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {
    private atm atm;

    public UserInterface(atm atm) {
        this.atm = atm;

        setTitle("ATM Interface");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");

        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckBalance();
            }
        });
    }

    private void handleDeposit() {
        String amountStr = JOptionPane.showInputDialog("Enter deposit amount:");
        double amount = Double.parseDouble(amountStr);
        atm.deposit(amount);
        JOptionPane.showMessageDialog(this, "Deposit successful.\nNew balance: " + atm.checkBalance());
    }

    private void handleWithdraw() {
        String amountStr = JOptionPane.showInputDialog("Enter withdrawal amount:");
        double amount = Double.parseDouble(amountStr);
        if (atm.withdraw(amount)) {
            JOptionPane.showMessageDialog(this, "Withdrawal Successful.\nNew balance: " + atm.checkBalance());
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient funds.");
        }
    }

    private void handleCheckBalance() {
        double balance = atm.checkBalance();
        JOptionPane.showMessageDialog(this, "Current Balance: " + balance);
    }
}
