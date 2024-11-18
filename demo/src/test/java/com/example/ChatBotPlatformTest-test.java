public class ChatBotPlatformTest {
    private ChatBotPlatform platform;

    @Before
    public void setUp() {
        ChatBot.reset();  // Reset static variables before each test
        platform = new ChatBotPlatform();
    }

    @Test
    public void testAddChatBot() {
        // Test adding bots within limit
        assertTrue(platform.addChatBot(1));
        assertTrue(platform.addChatBot(2));
        assertTrue(platform.addChatBot(3));
        
        // Test adding bot beyond limit
        assertFalse(platform.addChatBot(4));
        
        // Verify number of bots
        assertEquals(3, platform.getBots().size());
    }

    @Test
    public void testGetBots() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        
        ArrayList<ChatBot> bots = platform.getBots();
        assertNotNull(bots);
        assertEquals(2, bots.size());
    }

    @Test
    public void testGetChatBotList() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        
        String list = platform.getChatBotList();
        
        // Verify the format and content
        assertTrue(list.contains("Your ChatBots"));
        assertTrue(list.contains("Bot Number: 0"));
        assertTrue(list.contains("Bot Number: 1"));
        assertTrue(list.contains("Total Messages Used:"));
        assertTrue(list.contains("Total Messages Remaining:"));
    }

    @Test
    public void testInteractWithBot() {
        platform.addChatBot(1);
        
        // Test valid interaction
        String response = platform.interactWithBot(0, "Hello");
        assertEquals("Response to: Hello", response);
        
        // Test invalid bot number (negative)
        response = platform.interactWithBot(-1, "Hello");
        assertTrue(response.contains("Incorrect Bot Number"));
        
        // Test invalid bot number (too large)
        response = platform.interactWithBot(1, "Hello");
        assertTrue(response.contains("Incorrect Bot Number"));
    }

    @Test
    public void testMessageCounting() {
        platform.addChatBot(1);
        
        // Send multiple messages and verify counting
        platform.interactWithBot(0, "Message 1");
        platform.interactWithBot(0, "Message 2");
        
        String list = platform.getChatBotList();
        assertTrue(list.contains("Total Messages Used: 2"));
        assertTrue(list.contains("Total Messages Remaining: 8")); // Assuming MAX_MESSAGES = 10
    }
}