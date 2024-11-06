package com.example;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ChatBotPlatformTester {

    public static void main(String[] args) {
        System.out.println("Testing ChatBotPlatform:");

        testChatBotPlatformConstructor();
        testAddChatBot();
        testGetBots();
        testGetChatBotList();
        testInteractWithBot();
    }

    // Test constructor
    static void testChatBotPlatformConstructor() {
        ChatBotPlatform platform = new ChatBotPlatform();
        try {
            // Using reflection to access the private "bots" field
            Field botsField = ChatBotPlatform.class.getDeclaredField("bots");
            botsField.setAccessible(true);
            ArrayList<ChatBot> bots = (ArrayList<ChatBot>) botsField.get(platform);

            // Check that bots list is initialized and empty
            assert bots != null : "bots should be initialized";
            assert bots.isEmpty() : "bots list should be empty after initialization";
            System.out.println("Constructor test passed: bots list initialized and empty.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Error in constructor test: " + e.getMessage());
        }
    }

    // Test addChatBot method
    static void testAddChatBot() {
        ChatBotPlatform platform = new ChatBotPlatform();

        // Test that addChatBot returns false if the limit is reached
        boolean limitReached = ChatBot.limitReached();
        if (!limitReached) {
            boolean result = platform.addChatBot(1);
            assert result : "addChatBot should return true when limit is not reached";
            System.out.println("addChatBot test passed: bot added successfully.");
        } else {
            System.out.println("addChatBot test skipped: limit reached.");
        }
    }

    // Test getBots method
    static void testGetBots() {
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(1);  // Add a bot to test
        ArrayList<ChatBot> bots = platform.getBots();
        assert bots.size() == 1 : "getBots should return a list with 1 bot";
        System.out.println("getBots test passed: returned bots list size is correct.");
    }

    // Test getChatBotList method
    static void testGetChatBotList() {
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(1);  // Add a bot to test
        String chatBotList = platform.getChatBotList();
        assert chatBotList.contains("Your ChatBots") : "getChatBotList should contain 'Your ChatBots'";
        assert chatBotList.contains("Total Messages Used") : "getChatBotList should contain 'Total Messages Used'";
        assert chatBotList.contains("Total Messages Remaining") : "getChatBotList should contain 'Total Messages Remaining'";
        System.out.println("getChatBotList test passed: output contains expected info.");
    }

    // Test interactWithBot method
    static void testInteractWithBot() {
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(1);  // Add a bot to test
        String response = platform.interactWithBot(0, "Hello");
        assert response != null : "interactWithBot should return a non-null response";
        System.out.println("interactWithBot test passed: interaction response is valid.");

        // Test for an invalid bot number
        String invalidResponse = platform.interactWithBot(10, "Hello");
        assert invalidResponse.equals("Incorrect Bot Number (10) Selected. Try again") : "interactWithBot should return error message for invalid bot number";
        System.out.println("interactWithBot test passed: invalid bot number returns error message.");
    }
}
