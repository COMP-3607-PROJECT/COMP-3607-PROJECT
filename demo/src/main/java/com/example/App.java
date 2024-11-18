package com.example;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;

import com.example.tests.ChatBotGeneratorTest;
import com.example.tests.ChatBotPlatformTest;
import com.example.tests.ChatBotSimulationTest;
import com.example.tests.ChatBotTest;
public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            FileChooserDemo app = new FileChooserDemo();
            app.initFrameContent();
            JFrame frame = new JFrame("Assignment Grader");
            frame.getContentPane().add(app);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        DirectoryIterator studentFolders = new ZipExtractor().createDirectoryIterator();
        System.out.println(studentFolders.next());
        System.out.println(1);
        while(studentFolders.hasNext()){
            runTests(studentFolders.next());
        }
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
        TestResults[] ts = new TestResults[]{
            t
        };
        TestResultsPDFGenerator.generatePDF(ts, "C:\\Users\\craft\\Documents\\TestResults.pdf");
        System.out.println(t);
    }

    public static void runTests(String folder){
        try{
        Path filePath = Paths.get(folder);
        URL fileUrl = filePath.toUri().toURL();
        ChatBotTest cbt = new ChatBotTest(fileUrl);
        TestResults cbtT = new TestResults();
        cbt.attach(cbtT);
        runAllTests(cbt);
        ChatBotPlatformTest cbpt = new ChatBotPlatformTest(fileUrl);
        TestResults cbptT = new TestResults();
        cbpt.attach(cbptT);
        runAllTests(cbpt);
        ChatBotSimulationTest cbst = new ChatBotSimulationTest(fileUrl);
        TestResults cbstT = new TestResults();
        cbst.attach(cbstT);
        runAllTests(cbst);
        ChatBotGeneratorTest cbgt = new ChatBotGeneratorTest(fileUrl);
        TestResults cbgtT = new TestResults();
        cbgt.attach(cbgtT);
        runAllTests(cbgt);
        TestResults[] results = new TestResults[]{
            cbtT,
            cbptT,
            cbgtT,
            cbstT
        };
        System.out.println(cbgtT);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void runAllTests(Object testObject) {
    Class<?> testClass = testObject.getClass();
    Method[] methods = testClass.getDeclaredMethods();

    for (Method method : methods) {
        if (method.isAnnotationPresent(Test.class)) {
            try {
                System.out.println("Running test: " + method.getName());
                method.setAccessible(true);
                method.invoke(testObject);
                System.out.println("Test passed: " + method.getName());
            } catch (Exception e) {
                System.out.println("Test failed: " + method.getName());
                e.printStackTrace();
            }
        }
    }
}

}
