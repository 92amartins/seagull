package ui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PreProcessingPanel extends MasterPanel {
	
//	private JButton btnBrowse;
		
	private JLabel lblOptions;
//	private JPanel panelOptions;
	private JCheckBox checkBoxStemming;
	private JCheckBox checkBoxStopwords;
	private JCheckBox checkBoxNormalization;
	
	private JLabel lblFiles;
	private JScrollPane paneListFiles;
	private JList<String> listFiles;
	private JLabel lblTotalFiles;

//	private JButton btnProcess; 
	
	private JLabel lblBOW;
	private JScrollPane paneBOW;
	private JTable tblBOW;
	
//	private JProgressBar progressBar;
	
	private JButton btnClassify;
	
	public PreProcessingPanel() {
		setLayout(null);
		
		btnBrowse = new JButton("Browse...");
		
		lblOptions = new JLabel("Options:");
		panelOptions = new JPanel();
		checkBoxStemming = new JCheckBox("Stemming");
		checkBoxStopwords = new JCheckBox("Remove stopwords");
		checkBoxNormalization = new JCheckBox("Normalization");
		lblFiles = new JLabel("Imported files: ");
		listFiles = new JList<String>();
		paneListFiles = new JScrollPane(listFiles);
		
		btnProcess = new JButton("Process!");
		
		lblTotalFiles = new JLabel();
		lblBOW = new JLabel("Bag of Words: ");
		tblBOW = new JTable();
		paneBOW = new JScrollPane(tblBOW);
		
		progressBar = new JProgressBar();
		
		btnClassify = new JButton("Classify!");
		
		createPanel();
	}
	
	public void createPanel() {
		btnBrowse.setBounds(10, 10, 100, 25);
		add(btnBrowse);
		
		panelOptions.setBounds(10, 45, 165, 105);
		panelOptions.setLayout(null);
		
		lblOptions.setBounds(5, 5, 100, 25);
		checkBoxStemming.setBounds(10, 30, 140, 20);
		checkBoxStopwords.setBounds(10, 55, 160, 20);
		checkBoxNormalization.setBounds(10, 80, 140, 20);
		
		panelOptions.add(lblOptions);
		panelOptions.add(checkBoxStemming);
		panelOptions.add(checkBoxStopwords);
		panelOptions.add(checkBoxNormalization);
		add(panelOptions);
		
		lblFiles.setBounds(15, 160, 200, 25);
		add(lblFiles);
		
		paneListFiles.setBounds(15, 190, 150, 190);
		add(paneListFiles);
		
		lblTotalFiles.setBounds(15, 380, 200, 25);
		add(lblTotalFiles);
		
		btnProcess.setBounds(10, 415, 100, 25);
		add(btnProcess);
		
		lblBOW.setBounds(200, 10, 100, 25);
		add(lblBOW);
		
		paneBOW.setBounds(200, 40, 560, 370);
		add(paneBOW);
		
		progressBar.setBounds(200, 415, 250, 25);
		add(progressBar);
		
		btnClassify.setBounds(660, 415, 100, 25);
		add(btnClassify);		
	}

	public JButton getBtnBrowse() {
		return btnBrowse;
	}

	public void setBtnBrowse(JButton btnBrowse) {
		this.btnBrowse = btnBrowse;
	}

	public JCheckBox getCheckBoxStemming() {
		return checkBoxStemming;
	}

	public void setCheckBoxStemming(JCheckBox checkBoxStemming) {
		this.checkBoxStemming = checkBoxStemming;
	}

	public JCheckBox getCheckBoxStopwords() {
		return checkBoxStopwords;
	}

	public void setCheckBoxStopwords(JCheckBox checkBoxStopwords) {
		this.checkBoxStopwords = checkBoxStopwords;
	}

	public JCheckBox getCheckBoxNormalization() {
		return checkBoxNormalization;
	}

	public void setCheckBoxNormalization(JCheckBox checkBoxNormalization) {
		this.checkBoxNormalization = checkBoxNormalization;
	}

	public JList<String> getListFiles() {
		return listFiles;
	}

	public void setListFiles(JList<String> listFiles) {
		this.listFiles = listFiles;
	}
	
	public JLabel getLblTotalFiles() {
		return lblTotalFiles;
	}

	public void setLblTotalFiles(JLabel lblTotalFiles) {
		this.lblTotalFiles = lblTotalFiles;
	}

	public JButton getBtnProcess() {
		return btnProcess;
	}

	public void setBtnProcess(JButton btnProcess) {
		this.btnProcess = btnProcess;
	}

	
	public JTable getTblBOW() {
		return tblBOW;
	}

	public void setTblBOW(JTable tblBOW) {
		this.tblBOW = tblBOW;
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JButton getBtnClassify() {
		return btnClassify;
	}

	public void setBtnClassify(JButton btnClassify) {
		this.btnClassify = btnClassify;
	}
	
}
