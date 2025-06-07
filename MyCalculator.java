import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyCalculator extends JFrame implements ActionListener {
    JTextField T1;
    JButton[] NumButtons = new JButton[10];
    JButton Add, Sub, Mul, Div, Mod, Clear, EQ;
    int num1, num2, result;
    char Operation;

    public MyCalculator() {
        // Set up the frame
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Text field
        T1 = new JTextField();
        T1.setFont(new Font("Arial", Font.PLAIN, 24));
        T1.setHorizontalAlignment(JTextField.RIGHT);
        T1.setEditable(false);
        add(T1, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        // Create number buttons
        for (int i = 0; i < 10; i++) {
            NumButtons[i] = new JButton(String.valueOf(i));
            NumButtons[i].addActionListener(this);
            NumButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
        }

        // Create operation buttons
        Add = new JButton("+");
        Sub = new JButton("-");
        Mul = new JButton("*");
        Div = new JButton("/");
        Mod = new JButton("%");
        EQ = new JButton("=");
        Clear = new JButton("Clear");

        JButton[] ops = { Add, Sub, Mul, Div, Mod, EQ, Clear };
        for (JButton b : ops) {
            b.addActionListener(this);
            b.setFont(new Font("Arial", Font.BOLD, 18));
        }

        // Add buttons to the panel in layout
        buttonPanel.add(NumButtons[7]);
        buttonPanel.add(NumButtons[8]);
        buttonPanel.add(NumButtons[9]);
        buttonPanel.add(Add);
        buttonPanel.add(NumButtons[4]);
        buttonPanel.add(NumButtons[5]);
        buttonPanel.add(NumButtons[6]);
        buttonPanel.add(Sub);
        buttonPanel.add(NumButtons[1]);
        buttonPanel.add(NumButtons[2]);
        buttonPanel.add(NumButtons[3]);
        buttonPanel.add(Mul);
        buttonPanel.add(NumButtons[0]);
        buttonPanel.add(Mod);
        buttonPanel.add(Div);
        buttonPanel.add(EQ);
        buttonPanel.add(Clear); // Just for layout symmetry

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();

        if (str.matches("\\d")) {
            T1.setText(T1.getText() + str);
        } else if (str.equals("+") || str.equals("-") || str.equals("*") ||
                   str.equals("/") || str.equals("%")) {
            if (!T1.getText().isEmpty()) {
                num1 = Integer.parseInt(T1.getText());
                Operation = str.charAt(0);
                T1.setText("");
            }
        } else if (str.equals("=")) {
            if (!T1.getText().isEmpty()) {
                num2 = Integer.parseInt(T1.getText());
                switch (Operation) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        try {
                            result = num1 / num2;
                        } catch (ArithmeticException e) {
                            JOptionPane.showMessageDialog(this, "Divide by zero error");
                            result = 0;
                        }
                        break;
                    case '%':
                        try {
                            result = num1 % num2;
                        } catch (ArithmeticException e) {
                            JOptionPane.showMessageDialog(this, "Modulo by zero error");
                            result = 0;
                        }
                        break;
                }
                T1.setText(String.valueOf(result));
            }
        } else if (str.equalsIgnoreCase("Clear")) {
            T1.setText("");
        }
    }

    public static void main(String[] args) {
        // Run the calculator
        SwingUtilities.invokeLater(() -> new MyCalculator());
    }
}
