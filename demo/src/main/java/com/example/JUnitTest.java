package com.example;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class JUnitTest {

    @Test
    public void testAssertions() {
       assertAll("Test multiple assertions",
            () -> {
                try{
                    assertEquals(2, 1 + 3, "WTF");
            }
                catch(Error e){
                    System.out.println("x");
                };
        },
            () ->{ 
                try{
            assertTrue("hello".equals("helloo"), "WTF");
            }
            catch(Error e){
                System.out.println("r");
            }
    },
            () -> {
                try {
                    assertFalse(1 == 1, "WTF");
                } catch (Error e) {
                    System.out.println("s");
                }
            }
        );
    }
}