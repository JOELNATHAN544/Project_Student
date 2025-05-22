public class Calculator {
    public static double calculate(double a, double b, String op) {
        switch (op) {
            case "+":
                return a + b;
             case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Unsupported operation.");
        }
    }
}