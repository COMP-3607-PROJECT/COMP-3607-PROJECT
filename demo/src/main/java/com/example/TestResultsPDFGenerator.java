package com.example;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestResultsPDFGenerator {

    public static void generatePDF(TestResults[] testResultsArray, String outputFilePath) throws DocumentException, IOException {
        Document document = new Document();
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream(outputFilePath);
            PdfWriter.getInstance(document, fos);
            document.open();
            
            document.add(new Paragraph("Test Results Report"));
            document.add(new Paragraph("\n"));

            for (TestResults testResults : testResultsArray) {
                String resultString = testResults.toString();

                // Add each line to the document
                for (String line : resultString.split("\n")) {
                    document.add(new Paragraph(line));
                }
                document.add(new Paragraph("\n"));
            }
        } finally {
            if (document.isOpen()) {
                document.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}
