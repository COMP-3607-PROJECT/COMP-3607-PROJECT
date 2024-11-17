package com.example;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class TestCompiler {
    
    @Test
    public void testValidCompile() throws IOException {
        File testSub = new File("demo\\src\\test\\java\\com\\example\\Jaheim_Caesar_816035438_A1.zip");
        String dirLoc = "demo\\src\\test\\java\\com\\example\\Jaheim_Caesar_816035438_A1_extracted";
        
        ZipExtractor.extractSubmissionZip(testSub.getPath(), dirLoc);
        boolean result =Compiler.compile(dirLoc);
        assertTrue(result,"Compilation Failed");
    }

    @Test
    public void testInvailidCompile() throws IOException {
    File testSub = new File("demo\\src\\test\\java\\com\\example\\A_File_That_Doesnt_Exist_816034754_A1.zip");
    String dirLoc = "demo\\src\\test\\java\\com\\example\\A_File_That_Doesnt_Exist_816034754_A1d";

    ZipExtractor.extractSubmissionZip(testSub.getPath(), dirLoc);
    boolean result =Compiler.compile(dirLoc);

    assertFalse(result,"Compilation Successful");
    }


    public void testEmptySolution() throws IOException {
        File testSub = new File("demo\\src\\test\\java\\com\\example\\Nicholas_Mendez_816012345_A1.zip");
        String dirLoc = "demo\\src\\test\\java\\com\\example\\Nicholas_Mendez_816012345_A1.zip1_extracted";

        ZipExtractor.extractSubmissionZip(testSub.getPath(), dirLoc);
        boolean result =Compiler.compile(dirLoc);
        assertFalse(result,"Compilation Successful");
    }

}



    