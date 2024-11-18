import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class ChatBotSimulationTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Random mockRandom;

    @Before
    public void setUp() {
        // Redirect System.out for testing
        System.setOut(new PrintStream(outContent));
        // Reset ChatBot static variables if needed
        ChatBot.reset();
        mockRandom = new MockRandom();
    }

    @Test
    public void testMainMethod() {
        // Run the main method
        ChatBotSimulation.main(new String[]{});
        
        // Get the output as string
        String output = outContent.toString();
        
        // Test initial output
        assertTrue(output.contains("Hello World!"));
        
        // Test that the bot list is printed twice
        int countBotLists = countOccurrences(output, "Your ChatBots");
        assertEquals(2, countBotLists);
        
        // Test that some interactions occurred
        assertTrue(output.contains("Response to: "));
        
        // Verify bot limit was enforced (should be less than 6 bots)
        assertTrue(countOccurrences(output, "Bot Number:") <= 3); // Assuming MAX_BOTS is 3
    }

    @Test
    public void testRandomInteractions() {
        // Create a ChatBotPlatform with controlled random numbers
        ChatBotPlatform platform = new ChatBotPlatform();
        Random controlledRandom = new Random(42); // Fixed seed for reproducibility
        
        // Add some bots
        for (int i = 1; i <= 3; i++) {
            platform.addChatBot(i);
        }
        
        String[] prompts = {"Hi", "Hello", "Hey"};
        
        // Test multiple random interactions
        for (int i = 0; i < 5; i++) {
            int botNumber = controlledRandom.nextInt(platform.getBots().size());
            String prompt = prompts[controlledRandom.nextInt(prompts.length)];
            String response = platform.interactWithBot(botNumber, prompt);
            assertNotNull(response);
            assertTrue(response.contains("Response to: ") || response.contains("Incorrect Bot Number"));
        }
    }

    @Test
    public void testBotAddition() {
        ChatBotPlatform platform = new ChatBotPlatform();
        
        // Test first loop (1 to 6)
        for (int i = 1; i <= 6; i++) {
            platform.addChatBot(i);
        }
        
        // Should not exceed MAX_BOTS (assumed to be 3)
        assertTrue(platform.getBots().size() <= 3);
        
        // Test second loop (random additions)
        Random controlledRandom = new Random(42);
        for (int i = 1; i <= 4; i++) {
            platform.addChatBot(controlledRandom.nextInt(6));
        }
        
        // Still should not exceed MAX_BOTS
        assertTrue(platform.getBots().size() <= 3);
    }

    @Test
    public void testPromptArray() {
        String[] prompts = {"Hi", "Hello", "Hey"};
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(1);
        
        // Test each prompt
        for (String prompt : prompts) {
            String response = platform.interactWithBot(0, prompt);
            assertTrue(response.contains("Response to: " + prompt));
        }
    }

    // Helper method to count string occurrences
    private int countOccurrences(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {
            lastIndex = str.indexOf(findStr, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }

    // Mock Random class for controlled testing
    private class MockRandom extends Random {
        private int counter = 0;
        
        @Override
        public int nextInt(int bound) {
            // Return predictable values for testing
            counter++;
            return counter % bound;
        }
    }

    // Clean up after tests
    public void tearDown() {
        System.setOut(originalOut);
    }
}