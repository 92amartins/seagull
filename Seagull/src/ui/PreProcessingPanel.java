package ui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PreProcessingPanel extends JPanel {
	
	private JButton btnBrowse;
	private JButton btnProcess; 
	private JProgressBar progressBar;
	private JLabel lblOptions;
	private JPanel panelOptions;
	private JCheckBox checkBoxStemming;
	private JCheckBox checkBoxStopwords;
	private JCheckBox checkBoxNormalization;
	private JLabel lblFiles;
	private JScrollPane paneListFiles;
	private JList<String> listFiles;
	private JLabel lblTotalFiles;
	private JLabel lblBOW;
	private JTable tblBOW;
	private JButton btnClassify;
	
	
	public PreProcessingPanel() {
		setLayout(null);
		
		btnBrowse = new JButton("Browse...");
		btnProcess = new JButton("Process!");
		progressBar = new JProgressBar();
		lblOptions = new JLabel("Options:");
		panelOptions = new JPanel();
		checkBoxStemming = new JCheckBox("Stemming");
		checkBoxStopwords = new JCheckBox("Remove stopwords");
		checkBoxNormalization = new JCheckBox("Normalization");
		lblFiles = new JLabel("Imported files: ");
		listFiles = new JList<String>();
		paneListFiles = new JScrollPane(listFiles);
		lblTotalFiles = new JLabel();
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
		
		lblOptions.setBounds(10, 115, 100, 25);
		add(lblOptions);
		
		panelOptions.setBounds(10, 140, 165, 85);
		panelOptions.setLayout(null);
		checkBoxStemming.setBounds(5, 5, 140, 20);
		checkBoxStopwords.setBounds(5, 30, 160, 20);
		checkBoxNormalization.setBounds(5, 55, 140, 20);
		
		panelOptions.add(checkBoxStemming);
		panelOptions.add(checkBoxStopwords);
		panelOptions.add(checkBoxNormalization);
		add(panelOptions);
		
		lblFiles.setBounds(10, 220, 200, 25);
		add(lblFiles);
		
		paneListFiles.setBounds(10, 250, 150, 160);
		add(paneListFiles);
		
		lblTotalFiles.setBounds(10, 415, 200, 25);
		add(lblTotalFiles);
		
		lblBOW.setBounds(200, 10, 100, 25);
		add(lblBOW);
		
		tblBOW.setBounds(200, 40, 560, 370);
		add(tblBOW);
		
		btnClassify.setBounds(660, 420, 100, 25);
		add(btnClassify);		
	}

	public JButton getBtnBrowse() {
		return btnBrowse;
	}

	public void setBtnBrowse(JButton btnBrowse) {
		this.btnBrowse = btnBrowse;
	}

	public JButton getBtnProcess() {
		return btnProcess;
	}

	public void setBtnProcess(JButton btnProcess) {
		this.btnProcess = btnProcess;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
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

	public JLabel getLblTotalFiles() {
		return lblTotalFiles;
	}

	public void setLblTotalFiles(JLabel lblTotalFiles) {
		this.lblTotalFiles = lblTotalFiles;
	}

	public JList<String> getListFiles() {
		return listFiles;
	}

	public void setListFiles(JList<String> listFiles) {
		this.listFiles = listFiles;
	}

	public JTable getTblBOW() {
		return tblBOW;
	}

	public void setTblBOW(JTable tblBOW) {
		this.tblBOW = tblBOW;
	}

	public JButton getBtnClassify() {
		return btnClassify;
	}

	public void setBtnClassify(JButton btnClassify) {
		this.btnClassify = btnClassify;
	}
	
}
