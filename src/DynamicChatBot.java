import java.util.*;

public class DynamicChatBot {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();

            // Map of keyword to list of responses
            Map<String, List<String>> responses = new HashMap<>();

            // Add responses for "hello"
            responses.put("hello", Arrays.asList(
                    "Hi there!",
                    "Hello! How can I help you?",
                    "Hey! Nice to meet you."));

            // Add responses for "how are you"
            responses.put("how are you", Arrays.asList(
                    "I'm just a bunch of code, but I'm doing well!",
                    "I'm functioning as expected, thanks!",
                    "Great! What about you?"));

            // Add default responses
            List<String> defaultResponses = Arrays.asList(
                    "I'm not sure I understand that.",
                    "Could you rephrase that?",
                    "Let's talk about something else!");

            System.out.println("ChatBot: Hello! I'm your friendly chatbot. Type 'bye' to exit.");

            while (true) {
                System.out.print("You: ");
                String userInput = scanner.nextLine().toLowerCase();

                if (userInput.contains("bye")) {
                    System.out.println("ChatBot: Goodbye! Talk to you later.");
                    break;
                }

                boolean foundResponse = false;

                // Search for keyword match
                for (String keyword : responses.keySet()) {
                    if (userInput.contains(keyword)) {
                        List<String> possibleResponses = responses.get(keyword);
                        String reply = possibleResponses.get(random.nextInt(possibleResponses.size()));
                        System.out.println("ChatBot: " + reply);
                        foundResponse = true;
                        break;
                    }
                }

                if (!foundResponse) {
                    String reply = defaultResponses.get(random.nextInt(defaultResponses.size()));
                    System.out.println("ChatBot: " + reply);
                }
            }
        }
    }
}
