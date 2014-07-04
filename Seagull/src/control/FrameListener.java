package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import processing.ClassificationTest;
import ui.MainFrame;

public class FrameListener implements ActionListener {
	
	private MainFrame mainFrame;
	private ClassificationManager classificatioManager = new ClassificationManager();
	private String pathClassify = "";
	private String pathPreProcess = "";
	
	public FrameListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(mainFrame.getBtnAbout()))
			showAboutDialog();
		else if(e.getSource().equals(mainFrame.getPreProcessingPanel().getBtnBrowse()))
			showAboutDialog();
		else if(e.getSource().equals(mainFrame.getPreProcessingPanel().getBtnProcess()))
			showAboutDialog();
		else if(e.getSource().equals(mainFrame.getPreProcessingPanel().getBtnClassify()))
			showAboutDialog();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnBrowse()))
			browseDirectory();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnProcess()))
			processClassification();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnSave()))
			showAboutDialog();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnChart()))
			showAboutDialog();
	}
	
	private void showAboutDialog() {
		String msg = "Tool done as a research project by Sciece without Borders students in University of Brighton. \n"
				+ "Students: \n"
				+ "- Andrei Martins Silva \n"
				+ "- Camilla Maciel Quitério de Oliveira \n"
				+ "- Humberto Politi de Oliveira \n"
				+ "Project Supervisor: Dr Gulden Uchyigit";
		JOptionPane.showMessageDialog(mainFrame, msg, "About Seagull Tool", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//TODO move these methods to some Classification manager class
	private void processClassification() {
		String summary = classificatioManager.classify(pathClassify);
		mainFrame.getClassificationPanel().getTxtAreaReport().setText(summary);
	}
	
	private void browseDirectory() {
		JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Browse the folder to process");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	pathClassify = chooser.getSelectedFile().toString();
        	mainFrame.getClassificationPanel().getTxtAreaReport().setText("Selected directory: "+ chooser.getCurrentDirectory() + "\n" + "Selected file: "+ chooser.getSelectedFile());
        } 
	}

}
