package com.example;

import java.net.URL;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ChatBotTester.main(new String[1]);
        try{
            URL classesUrl = Paths.get("C:\\Users\\craft\\Downloads\\").toUri().toURL();
            ChatBotTest CBT = new ChatBotTest(classesUrl);
            CBT.testChatBotName();
        }
        catch(Exception e){

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
