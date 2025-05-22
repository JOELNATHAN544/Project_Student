import java.util.Scanner; 

public class TemperatureConversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Temperature Conversion Program!\n");
        System.out.print("Enter the temperature units (K for Kelvin, oC for Degree celcius, oF for fahrenheit) : ");
        String unit = scanner.nextLine();
        System.out.println("\n");
        // double temperature = 0;
        switch (unit) {
            case "K":
                System.out.print("Enter the temperature in Kelvin: ");
                kelvin(scanner);
                break;
            case "oC":
                System.out.print("Enter the temperature in Celsius: ");
                celcius(scanner);
                break;
            case "oF":
                System.out.print("Enter the temperature in Fahrenheit: ");
                fahrenheit(scanner);
                break;
            default:
                System.out.println("Invalid unit. Please enter K, oC, or oF.");
                main(args);
        }
        scanner.close();
    }

    public static void celcius(Scanner mycelsius) {
        double celsius = verify(mycelsius);
        System.out.println("\n");
        if (celsius < -273.15) {
            System.out.println("Temperature cannot be below absolute zero (-273.15°C).\n");
            celcius(mycelsius);
            return;
        }
        double fahrenheit = (celsius * 9 / 5) + 32;
        double kelvin = celsius + 273.15;
        System.out.printf("Temperature in Kelvin: %.2fK%n", kelvin);
        System.out.printf("Temperature in Fahrenheit: %.2f°F%n", fahrenheit);
    }

    public static void fahrenheit(Scanner myfahrenheit) {
        double fahrenheit = verify(myfahrenheit);
        System.out.println("\n");
        if (fahrenheit < -459.67) {
            System.out.println("Temperature cannot be below absolute zero (-459.67°F).");
            fahrenheit(myfahrenheit);
            return;
        }
        double celsius = (fahrenheit - 32) * 5 / 9;
        double kelvin = celsius + 273.15;
        System.out.printf("Temperature in Celsius: %.2f°C%n", celsius);
        System.out.printf("Temperature in Kelvin: %.2fK%n", kelvin);
    }

    public static void kelvin(Scanner mykelvin) {
        double kelvin = verify(mykelvin);
        System.out.println("\n");
        if (kelvin < 0) {
            System.out.println("Temperature cannot be below absolute zero (0K).");
            kelvin(mykelvin);
            return;
        }
        double celsius = kelvin - 273.15;
        double fahrenheit = (celsius * 9 / 5) + 32;
        System.out.printf("Temperature in Celsius: %.2f°C%n", celsius);
        System.out.printf("Temperature in Fahrenheit: %.2f°F%n", fahrenheit);
    }

    public static double verify(Scanner scanner) {
        double temperature = 0;
        boolean valid = false;
        while (!valid) {
            try {
                temperature = scanner.nextDouble();
                valid = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return temperature;
    }
}
