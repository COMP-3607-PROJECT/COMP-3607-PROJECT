package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ChatBotTest CBT = new ChatBotTest();
        CBT.testChatBotName();
        CBT.testNumResponsesGenerated();
        CBT.testMessageLimit();

        ChatBotPlatformTest.testBots();

        
        // ZipExtractor ze = new ZipExtractor();
        // ZipExtractor.main(new String[1]);
        // ChatBotTester.main(new String[1]);
        // System.out.println( "Hello World!" );
    }
}
