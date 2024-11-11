package com.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;



public class App 
{

    public static void callAllMethods(Object obj) {
        // Get the class of the provided object
        Class<?> clazz = obj.getClass();
        
        // Get all methods (public, private, protected, and package-private)
        Method[] methods = clazz.getDeclaredMethods();
        
        // Loop through all methods
        for (Method method : methods) {
            try {
                // Set the method accessible (useful for private methods)
                method.setAccessible(true);
                
                if (method.getName().startsWith("lambda$") || method.getAnnotation(Test.class) == null) {
                continue; // Skip lambda or non-test methods
            }
                // Skip static methods, or invoke them if needed
                if (Modifier.isStatic(method.getModifiers())) {
                    // Invoke static method (no instance needed)
                    //method.invoke(null);
                } else {
                    // Invoke non-static method (need to pass the object instance)
                    method.invoke(obj);
                }

                // Optionally, you can print or log the method name if needed
                System.out.println("Successfully called method: " + method.getName());
            } catch (Exception e) {
                // Handle any exceptions during invocation
                System.out.println("Failed to call method " + method.getName() + " due to: " + e.getMessage());
            }
        }
    }
    public static void main( String[] args )
    {
        // new ChatBotTester(null)).main(new String[1]);
        try{
            System.out.println(ChatBot.class.getSimpleName());
            // new JUnitTest().testAssertions();
            ChatBotSimulationTest CBST = new ChatBotSimulationTest();
            System.out.println(CBST.getClass().getSimpleName());
            //CBST.testPlatformInitialization();
            //CBST.testInteractionAndOutput();
            //CBST.testChatBotPlatformInstance();
            //CBST.testChatBotPlatformInstantiation();
            URL classesUrl = Paths.get("C:\\Users\\craft\\Downloads\\javatest").toUri().toURL();
            //System.out.println(classesUrl);
            ChatBotTest CBT = new ChatBotTest(classesUrl);
            ChatBotPlatformTest CBPT = new ChatBotPlatformTest(classesUrl);
            ChatBotGeneratorTest CBGT = new ChatBotGeneratorTest(classesUrl);
            TestResults tt = new TestResults();
            CBGT.attach(tt);
            CBGT.testGenerateChatBotLLM();
            //callAllMethods(CBGT);
            System.out.println(tt);

            TestResults t = new TestResults();
            CBT.attach(t);
            CBT.signal(2, "Win");
            CBT.signal(2, "Win");
            System.out.println(t);


            //CBT.testGetMessageLimit();
            //callAllMethods(CBT);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("I am the best");
        }
        
        
        // CBT.testChatBotName();
        // CBT.testNumResponsesGenerated();
        // CBT.testMessageLimit();
        // try {
        //     // System.out.println(CBT.testGetChatBotName());
        //     System.out.println(CBT.testGetNumResponsesGenerated());
        //     // CBT.testMessageNumber();
        //     // System.out.println(CBT.testDefaultConstructor());
            
        // } catch (Exception e) {
        // }
        // try {
        //     System.out.println(CBT.testOverridedConstructor());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // ChatBotPlatformTest.testBots();
        // try {
        //     ChatBotPlatformTest.testChatBotPlatformConstructor();
        // } catch (Exception e) {
        // }
        

        
        // ZipExtractor ze = new ZipExtractor();
        // ZipExtractor.main(new String[1]);
        // ChatBotTester.main(new String[1]);
        // System.out.println( "Hello World!" );
    }
}
