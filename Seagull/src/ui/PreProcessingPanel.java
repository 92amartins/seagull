package ui;

import java.awt.Button;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;

public class PreProcessingPanel extends JPanel {
	
	private JButton btnBrowse;
	private JButton btnProcess;
	private JProgressBar progressBar;
	private JLabel lblFiles;
	private JList<String> listFiles;
	private JLabel lblBOW;
	private JTable tblBOW;
	private JButton btnClassify;
	
	
	public PreProcessingPanel() {
		setLayout(null);
		
		btnBrowse = new JButton("Browse...");
		btnProcess = new JButton("Process!");
		progressBar = new JProgressBar();
		lblFiles = new JLabel("Imported files: ");
		listFiles = new JList<String>();
		lblBOW = new JLabel("Bag of Words: ");
		tblBOW = new JTable();
		btnClassify = new JButton("Classify!");
		
		createPanel();
	}
	
	public void createPanel() {
		btnBrowse.setBounds(10, 10, 100, 25);
		add(btnBrowse);
		
		btnProcess.setBounds(10, 45, 100, 25);
		add(btnProcess);
		
		progressBar.setBounds(10, 80, 150, 25);
		add(progressBar);
		
		lblFiles.setBounds(10, 115, 100, 25);
		add(lblFiles);
		
		listFiles.setBounds(10, 140, 150, 300);
		add(listFiles);
		
		lblBOW.setBounds(200, 10, 100, 25);
		add(lblBOW);
		
		//width, height
		tblBOW.setBounds(200, 40, 560, 370);
		add(tblBOW);
		
		btnClassify.setBounds(660, 420, 100, 25);
		add(btnClassify);		
	}
}
