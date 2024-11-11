package com.example;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


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

// public static void main(String[] args) {
// SwingUtilities.invokeLater(() -> {
// FileChooserDemo app = new FileChooserDemo();
// app.initFrameContent();
// JFrame frame = new JFrame("LoquetUP");
// frame.getContentPane().add(app);
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// frame.setSize(600, 400);
// frame.setResizable(false);
// frame.setLocationRelativeTo(null);
// frame.setVisible(true);
// });
// }
// }

// import javax.swing.*;

// import java.awt.BorderLayout;
// import java.awt.GridLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.File;
// import java.awt.event.MouseListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import javax.swing.TransferHandler;

// import javax.swing.BorderFactory;
// import javax.swing.DefaultListModel;
// import javax.swing.JButton;
// import javax.swing.JComponent;
// import javax.swing.JFileChooser;
// import javax.swing.JFrame;

// import javax.swing.JLabel;
// import javax.swing.JList;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.SwingUtilities;
// import javax.swing.filechooser.FileFilter;

// public class FileChooserDemo
// extends JPanel
// implements ActionListener {

// JLabel selectedFileLabel;
// JList selectedFilesList;
// JLabel returnCodeLabel;

// public FileChooserDemo() {
// super();
// createContent();
// }

// void initFrameContent() {
// JPanel closePanel = new JPanel();
// add(closePanel, BorderLayout.SOUTH);
// }

// private void createContent() {
// setLayout(new BorderLayout());

// JPanel NorthPanel = new JPanel();

// JMenuBar menuBar = new JMenuBar();
// JMenu menu = new JMenu("File");
// JMenuItem quit = new JMenuItem("Quit");

// menuBar.add(menu);
// menu.add(quit);

// NorthPanel.add(menu, BorderLayout.NORTH);

// JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
// JButton openButton = new JButton("Open...");
// openButton.setActionCommand("OPEN");
// openButton.addActionListener(this);
// buttonPanel.add(openButton);

// JButton saveButton = new JButton("Save...");
// saveButton.setActionCommand("SAVE");
// saveButton.addActionListener(this);
// buttonPanel.add(saveButton);

// JButton delete = new JButton("Delete");
// delete.addActionListener(this);
// delete.setActionCommand("DELETE");
// buttonPanel.add(delete);

// add(buttonPanel, BorderLayout.WEST);

// // create a panel to display the selected file(s) and the return code
// JPanel displayPanel = new JPanel(new BorderLayout());
// selectedFileLabel = new JLabel("-");

// selectedFileLabel.setBorder(BorderFactory.createTitledBorder("Selected
// File/Directory "));

// displayPanel.add(selectedFileLabel, BorderLayout.NORTH);

// selectedFilesList = new JList();
// JScrollPane sp = new JScrollPane(selectedFilesList);
// sp.setBorder(BorderFactory.createTitledBorder("Selected Files "));
// MouseListener listener = new MouseAdapter() {
// public void mousePressed(MouseEvent me) {
// JComponent comp = (JComponent) me.getSource();
// TransferHandler handler = comp.getTransferHandler();
// handler.exportAsDrag(comp, me, TransferHandler.MOVE);
// }
// };
// selectedFilesList.addMouseListener(listener);

// displayPanel.add(sp);

// returnCodeLabel = new JLabel("");
// returnCodeLabel.setBorder(BorderFactory.createTitledBorder("Return Code"));
// displayPanel.add(returnCodeLabel, BorderLayout.SOUTH);

// add(displayPanel);
// }

// public void actionPerformed(ActionEvent e) {
// int option = 0;
// File selectedFile = null;
// File[] selectedFiles = new File[0];

// if (e.getActionCommand().equals("CLOSE")) {
// System.exit(0);
// } else if (e.getActionCommand().equals("OPEN")) {
// JFileChooser chooser = new JFileChooser();
// chooser.setDragEnabled(true);
// chooser.setMultiSelectionEnabled(true);
// option = chooser.showOpenDialog(this);
// selectedFiles = chooser.getSelectedFiles();
// } else if (e.getActionCommand().equals("SAVE")) {
// JFileChooser chooser = new JFileChooser();
// option = chooser.showSaveDialog(this);
// selectedFiles = chooser.getSelectedFiles();
// }

// // display the selection and return code
// if (selectedFile != null)
// selectedFileLabel.setText(selectedFile.toString());
// else
// selectedFileLabel.setText("null");
// DefaultListModel listModel = new DefaultListModel();
// for (int i = 0; i < selectedFiles.length; i++)
// listModel.addElement(selectedFiles[i]);

// selectedFilesList.setModel(listModel);
// returnCodeLabel.setText(Integer.toString(option));
// }

// public static void main(String[] args) {
// SwingUtilities.invokeLater(new Runnable() {
// public void run() {
// FileChooserDemo app = new FileChooserDemo();
// app.initFrameContent();
// JFrame frame = new JFrame("LoquetUP");
// frame.getContentPane().add(app);
// frame.setDefaultCloseOperation(3);
// frame.setSize(600, 400);
// frame.setResizable(false);
// frame.setLocationRelativeTo(null);

// // frame.pack();
// frame.setVisible(true);
// }
// });
// }

// }