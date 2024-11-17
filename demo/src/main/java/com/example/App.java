package com.example;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.tests.ChatBotPlatformTest;
import com.example.tests.ChatBotSimulationTest;
import com.example.tests.ChatBotTest;
public class App {
    public static void main(String[] args) throws Exception {
        // SwingUtilities.invokeLater(() -> {
        //     FileChooserDemo app = new FileChooserDemo();
        //     app.initFrameContent();
        //     JFrame frame = new JFrame("Assignment Grader");
        //     frame.getContentPane().add(app);
        //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //     frame.setSize(600, 400);
        //     frame.setResizable(false);
        //     frame.setLocationRelativeTo(null);
        //     frame.setVisible(true);
        // });
        Path filePath = Paths.get("demo\\javafiles");
        URL fileUrl = filePath.toUri().toURL();
        ChatBotTest cbt = new ChatBotTest(fileUrl);
        TestResults t = new TestResults();
        cbt.attach(t);
        
        cbt.testGetTotalNumMessagesRemaining();
        cbt.testGenerateResponse();
        cbt.testGetTotalNumResponsesGenerated();
        cbt.testPrompt();
        cbt.testToString();
        ChatBotPlatformTest cbpt = new ChatBotPlatformTest(fileUrl);
        ChatBotSimulationTest cbst = new ChatBotSimulationTest(fileUrl);
        cbpt.attach(t);
        cbst.attach(t);
        cbpt.testGetChatBotList();
        cbpt.testBotsField();
        cbpt.testAddChatBot();
        cbpt.testInteractWithBot();
        // System.out.println(cbst.setUpOutput());
        cbst.testHelloWorld();
        cbst.testChatBotList();
        cbst.testChatBotInteractions();  
        System.out.println(t);
    }
}
