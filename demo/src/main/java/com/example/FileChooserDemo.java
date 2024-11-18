package com.example;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class FileChooserDemo extends JPanel implements ActionListener {

   JLabel selectedFileLabel;
   JList<File> selectedFilesList;
   JLabel returnCodeLabel;
   File selectedZipFile;

   public FileChooserDemo() {
      super();
      createContent();
   }

   void initFrameContent() {
      JPanel closePanel = new JPanel();
      add(closePanel, BorderLayout.SOUTH);
   }

   private void createContent() {
      setLayout(new BorderLayout());

      JPanel northPanel = new JPanel();

      JMenuBar menuBar = new JMenuBar();
      JMenu menu = new JMenu("File");
      JMenuItem quit = new JMenuItem("Quit");
      quit.addActionListener(e -> System.exit(0));

      menuBar.add(menu);
      menu.add(quit);
      northPanel.add(menuBar, BorderLayout.NORTH);

      JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
      JButton openButton = new JButton("Open...");
      openButton.setActionCommand("OPEN");
      openButton.addActionListener(this);
      buttonPanel.add(openButton);

      add(buttonPanel, BorderLayout.WEST);

      // Create a panel to display the selected file(s) and return code
      JPanel displayPanel = new JPanel(new BorderLayout());
      selectedFileLabel = new JLabel("-");
      selectedFileLabel.setBorder(BorderFactory.createTitledBorder("Selected File/Directory"));
      displayPanel.add(selectedFileLabel, BorderLayout.NORTH);

      selectedFilesList = new JList<>();
      JScrollPane sp = new JScrollPane(selectedFilesList);
      sp.setBorder(BorderFactory.createTitledBorder("Selected Files"));
      displayPanel.add(sp, BorderLayout.CENTER);

      returnCodeLabel = new JLabel("");
      returnCodeLabel.setBorder(BorderFactory.createTitledBorder("Return Code"));
      displayPanel.add(returnCodeLabel, BorderLayout.SOUTH);

      add(displayPanel, BorderLayout.CENTER);
   }

   public void actionPerformed(ActionEvent e) {
      int option = 0;
      File[] selectedFiles = new File[0];

      if (e.getActionCommand().equals("OPEN")) {
         JFileChooser chooser = new JFileChooser();
         chooser.setDragEnabled(true);
         chooser.setMultiSelectionEnabled(false);
         chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Zip files", "zip"));
         option = chooser.showOpenDialog(this);
         selectedFiles = chooser.getSelectedFiles();

         if (option == JFileChooser.APPROVE_OPTION) {
            selectedZipFile = chooser.getSelectedFile();
            selectedFileLabel.setText(selectedZipFile.getName());

            // Trigger the extraction process
            extractZipFile(selectedZipFile);
            System.out.println(ZipExtractor.getStudentDirectories());
            List<String> folders = ZipExtractor.getStudentDirectories();
            int current = 0;
            for (String folderPath : folders) {
               // Now, iterate through the subdirectories inside the folder
               File folder = new File(folderPath);
               if (folder.exists() && folder.isDirectory()) {
                   File[] subfolders = folder.listFiles(File::isDirectory); // Get subdirectories only
                   
                   // If there are subdirectories, iterate through them
                   if (subfolders != null) {
                       for (File subfolder : subfolders) {
                           String subfolderPath = subfolder.getPath();
                           System.out.println("Entering subfolder: " + subfolderPath);
           
                           // Compile and run tests on the subfolder
                           Compiler.compile(subfolderPath);
                           App.runTests(subfolderPath);
                       }
                   }
               }
           }
            // DirectoryIterator studentFolders = new ZipExtractor().createDirectoryIterator();
            // System.out.println(studentFolders.next());
            // while(studentFolders.hasNext()){
            //    String folder = stud
            //    Compiler.compile()
            //    App.runTests(studentFolders.next());
            // }
         }
      }

      // Display return code for debugging
      returnCodeLabel.setText("Return Code: " + option);
   }

   private void extractZipFile(File zipFile) {
      String parentDir = zipFile.getParent();

      // Update the GUI to show extraction progress or success
      try {
         ZipExtractor.extractSubmissionZip(zipFile.getPath(), parentDir);
         JOptionPane.showMessageDialog(this, "Extraction complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
      } catch (IOException e) {
         JOptionPane.showMessageDialog(this, "Error during extraction: " + e.getMessage(), "Error",
               JOptionPane.ERROR_MESSAGE);
         e.printStackTrace();
      }
   }
}
