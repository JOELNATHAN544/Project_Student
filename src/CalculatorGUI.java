import javax.swing.*; // Import Swing components
import java.awt.*;    // Import AWT for layout managers and basic graphics
import java.awt.event.ActionEvent; // For handling button clicks
import java.awt.event.ActionListener; // Interface for action listeners

public class CalculatorGUI extends JFrame implements ActionListener {

    // GUI Components 
    private JTextField num1Field;
    private JTextField num2Field;
    private JLabel resultLabel;
    private JButton addButton, subtractButton, multiplyButton, divideButton;

    public CalculatorGUI() {
        // Set up the main window (JFrame)
        setTitle("Simple Calculator");
        setSize(400, 250); // Set initial size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns, with gaps

        // 1. First Number Input
        add(new JLabel("First Number:"));
        num1Field = new JTextField(10); // 10 columns wide
        add(num1Field);

        // 2. Second Number Input
        add(new JLabel("Second Number:"));
        num2Field = new JTextField(10);
        add(num2Field);

        // 3. Operation Buttons
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");

        // Add action listeners to buttons
        // 'this' refers to the CalculatorGUI instance itself, which implements ActionListener
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);

        // Add buttons to the frame
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);

        // 4. Result Display
        add(new JLabel("Result:"));
        resultLabel = new JLabel("0.0"); // Initial result
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Make result stand out
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text
        add(resultLabel);

        // Make the window visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double num1, num2;
        try {
            // Get text from input fields and parse to double
            num1 = Double.parseDouble(num1Field.getText());
            num2 = Double.parseDouble(num2Field.getText());
        } catch (NumberFormatException ex) {
            // Handle invalid number input
            resultLabel.setText("Error: Invalid number input!");
            return; // Stop processing
        }

        String operator = "";
        // Determine which button was clicked
        if (e.getSource() == addButton) {
            operator = "+";
        } else if (e.getSource() == subtractButton) {
            operator = "-";
        } else if (e.getSource() == multiplyButton) {
            operator = "*";
        } else if (e.getSource() == divideButton) {
            operator = "/";
        }

        try {
            // Perform the calculation using your existing Calculator logic
            double result = Calculator.calculate(num1, num2, operator);
            resultLabel.setText(String.valueOf(result)); // Display result
        } catch (ArithmeticException | IllegalArgumentException ex) {
            // Handle calculation errors (e.g., division by zero)
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculatorGUI();
            }
        });
    }
}