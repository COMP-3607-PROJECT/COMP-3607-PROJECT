package com.example;

import java.awt.*;
import java.awt.print.*;

public class PDFReportGenerator {

    // Method to generate and print the PDF report
    public static void generatePDF(TestResults testResults) {

        // Create a PrinterJob and set up the print job
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }

                // Set up the Graphics2D object for better control
                Graphics2D g2d = (Graphics2D) g;
                g2d.setFont(new Font("Monospaced", Font.PLAIN, 12));
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                // Set up margins, line height, and page dimensions
                int margin = 50;
                int yPosition = margin;
                int lineHeight = g2d.getFontMetrics().getHeight();
                int pageWidth = (int) pageFormat.getImageableWidth() - 2 * margin;
                int pageHeight = (int) pageFormat.getImageableHeight();

                String title = "TEST RESULTS";
                int titleWidth = g2d.getFontMetrics().stringWidth(title);
                int titlePosition = pageWidth - titleWidth;
                g2d.drawString(title, titlePosition, yPosition);
                yPosition += lineHeight * 2;

                // Define column positions
                int testNamePosition = margin;
                int feedbackPosition = testNamePosition + 200; // Fixed width for test name
                int marksPosition = feedbackPosition + 300;   // Fixed width for feedback

                // Print header
                g2d.drawString("Test Name", testNamePosition, yPosition);
                g2d.drawString("Feedback", feedbackPosition, yPosition);
                g2d.drawString("Marks", marksPosition, yPosition);
                yPosition += lineHeight;

                // Print separator line
                g2d.drawString("-".repeat(pageWidth / 7), margin, yPosition);
                yPosition += lineHeight;

                // Loop through the test results and print each row
                for (int i = 0; i < testResults.getMarks().size(); i++) {
                    String testName = testResults.testNames.get(i);
                    String feedbackText = testResults.feedback.get(i);
                    String marksText = testResults.getMarks().get(i).toString();

                    // Wrap the feedback text to fit within the column width
                    String[] feedbackLines = wrapText(feedbackText, 300, g2d);

                    // Print the first line with all columns
                    g2d.drawString(testName, testNamePosition, yPosition);
                    g2d.drawString(feedbackLines[0], feedbackPosition, yPosition);
                    g2d.drawString(marksText, marksPosition, yPosition);
                    yPosition += lineHeight;

                    // Print any additional feedback lines (if wrapped)
                    for (int j = 1; j < feedbackLines.length; j++) {
                        if (yPosition + lineHeight > pageHeight) {
                            return NO_SUCH_PAGE; // Stop if it exceeds the page height
                        }
                        g2d.drawString(feedbackLines[j], feedbackPosition, yPosition);
                        yPosition += lineHeight;
                    }
                }

                yPosition += lineHeight;
                String totalMarksText = "Total Marks: " + testResults.getTotalMarks();
                g2d.drawString(totalMarksText, margin, yPosition);


                return PAGE_EXISTS;
            }
        });

        // Select a printer (Virtual PDF printer on the system)
        if (job.printDialog()) {
            try {
                // Print the content (this will trigger the PDF dialog on most systems)
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to wrap text to fit within the specified column width
    private static String[] wrapText(String text, int maxWidth, Graphics2D g2d) {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        java.util.List<String> lines = new java.util.ArrayList<>();

        for (String word : words) {
            int lineWidth = g2d.getFontMetrics().stringWidth(line + word + " ");
            if (lineWidth > maxWidth) {
                lines.add(line.toString().trim());
                line = new StringBuilder(word + " ");
            } else {
                line.append(word).append(" ");
            }
        }

        if (line.length() > 0) {
            lines.add(line.toString().trim());
        }

        return lines.toArray(new String[0]);
    }
}
