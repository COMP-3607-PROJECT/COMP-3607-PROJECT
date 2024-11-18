package com.example;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class TestExtractor {
    
    @Test
    public void testValidSolution() throws IOException {
        File testSub = new File("demo\\src\\test\\java\\com\\example\\Jaheim_Caesar_816035438_A1.zip");
        String dirLoc = "demo\\src\\test\\java\\com\\example\\Jaheim_Caesar_816035438_A1_extracted";

        ZipExtractor.extractSubmissionZip(testSub.getPath(), dirLoc);
        File extractedDir = new File(dirLoc);
        assertTrue(extractedDir.exists() && extractedDir.isDirectory(), "File Extracted");
    }


    @Test 
    public void testInvailidSolution() throws IOException {
        File testSub = new File("demo\\src\\test\\java\\com\\example\\A_File_That_Doesnt_Exist_816034754_A1.zip");
        String dirLoc = "demo\\src\\test\\java\\com\\example\\A_File_That_Doesnt_Exist_816034754_A1d";

        ZipExtractor.extractSubmissionZip(testSub.getPath(), dirLoc);
    }


    public void testEmptySolution() throws IOException {
        File testSub = new File("demo\\src\\test\\java\\com\\example\\Nicholas_Mendez_816012345_A1.zip");
        String dirLoc = "demo\\src\\test\\java\\com\\example\\Nicholas_Mendez_816012345_A1.zip1_extracted";

        ZipExtractor.extractSubmissionZip(testSub.getPath(), dirLoc);
        File extractedDir = new File(dirLoc);
        assertFalse(extractedDir.exists() && extractedDir.isDirectory(), "File Extraction Failed");
         assertEquals(0, extractedDir.listFiles().length, "Submission should be Empty");
    }

}
