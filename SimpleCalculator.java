import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField resultDisplay;
    private double firstNumber;
    private double secondNumber;
    private String selectedOperation;
    private boolean isNewInput;

    public SimpleCalculator() {
        setTitle("My Calculator");
        setSize(250, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        resultDisplay = new JTextField("0");
        resultDisplay.setHorizontalAlignment(JTextField.RIGHT);
        resultDisplay.setEditable(false);
        resultDisplay.setFont(new Font("Arial", Font.BOLD, 20));
        add(resultDisplay, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 4, 5, 5));
        buttonsPanel.setBackground(Color.LIGHT_GRAY);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "C", "+",
                "+/-", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            if (label.matches("[/*\\-+]")) {
                button.setBackground(new Color(255, 165, 0));
            } else if (label.equals("=")) {
                button.setBackground(new Color(0, 128, 0));
            } else if (label.equals("C") || label.equals("+/-")) {
                button.setBackground(new Color(220, 20, 60));
            }
            buttonsPanel.add(button);
        }

        add(buttonsPanel, BorderLayout.CENTER);
        isNewInput = true;
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String buttonText = event.getActionCommand();

        if (buttonText.matches("\\d") || buttonText.equals(".")) {
            if (isNewInput) {
                resultDisplay.setText(buttonText);
                isNewInput = false;
            } else {
                resultDisplay.setText(resultDisplay.getText() + buttonText);
            }
        } else if (buttonText.equals("C")) {
            resultDisplay.setText("0");
            firstNumber = 0;
            secondNumber = 0;
            selectedOperation = "";
            isNewInput = true;
        } else if (buttonText.equals("+/-")) {
            double currentValue = Double.parseDouble(resultDisplay.getText());
            resultDisplay.setText(String.valueOf(currentValue * -1));
        } else if (buttonText.equals("=")) {
            secondNumber = Double.parseDouble(resultDisplay.getText());
            double calculationResult = 0;
            if (selectedOperation.equals("+")) {
                calculationResult = firstNumber + secondNumber;
            } else if (selectedOperation.equals("-")) {
                calculationResult = firstNumber - secondNumber;
            } else if (selectedOperation.equals("*")) {
                calculationResult = firstNumber * secondNumber;
            } else if (selectedOperation.equals("/")) {
                if (secondNumber != 0) {
                    calculationResult = firstNumber / secondNumber;
                } else {
                    resultDisplay.setText("Error");
                    return;
                }
            }
            resultDisplay.setText(String.valueOf(calculationResult));
            isNewInput = true;
        } else {
            firstNumber = Double.parseDouble(resultDisplay.getText());
            selectedOperation = buttonText;
            isNewInput = true;
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}