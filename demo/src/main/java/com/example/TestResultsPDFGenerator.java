package com.example;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class TestResultsPDFGenerator {

    public static void generatePDF(List<TestResults> testResultsList, String outputFilePath) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, 750);
        
        contentStream.showText("Test Results Report");
        contentStream.newLineAtOffset(0, -20);
        
        for (TestResults testResults : testResultsList) {
            String resultString = testResults.toString();
            contentStream.showText(resultString);
            contentStream.newLineAtOffset(0, -20);
        }
        
        contentStream.endText();
        contentStream.close();
        
        document.save(outputFilePath);
        document.close();
    }
}