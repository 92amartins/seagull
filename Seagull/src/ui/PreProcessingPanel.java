package ui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PreProcessingPanel extends MasterPanel {
	
//	private JButton btnBrowse;
		
	private JLabel lblOptions;
//	private JPanel panelOptions;
	private JCheckBox checkBoxStemming;
	private JCheckBox checkBoxStopwords;
	
	private JLabel lblWeighting;  
	private JRadioButton radioBtnTF;
	private JRadioButton radioBtnTFIDF;
	private JRadioButton radioBtnIC;
	private ButtonGroup btnGroupWeighting;
	
	private JLabel lblFiles;
	private JScrollPane paneListFiles;
	private JList<String> listFiles;

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
		
		lblWeighting = new JLabel("Weighting: ");
		radioBtnTF = new JRadioButton("TF");
		radioBtnTFIDF = new JRadioButton("TF-IDF");
		radioBtnIC = new JRadioButton("Information Gain");
		btnGroupWeighting = new ButtonGroup();
		
		lblFiles = new JLabel("Imported files: ");
		listFiles = new JList<String>();
		paneListFiles = new JScrollPane(listFiles);
		
		btnProcess = new JButton("Process!");
		
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
		
		panelOptions.setBounds(10, 40, 165, 170);
		panelOptions.setLayout(null);
		
		lblOptions.setBounds(5, 0, 100, 25);
		checkBoxStemming.setBounds(10, 25, 140, 20);
		checkBoxStopwords.setBounds(10, 50, 160, 20);
		
		lblWeighting.setBounds(5, 75, 100, 25);
		radioBtnTF.setBounds(10, 100, 140, 20);
		radioBtnTF.setSelected(true);
		radioBtnTFIDF.setBounds(10, 125, 140, 20);
		radioBtnIC.setBounds(10, 150, 140, 20);
		btnGroupWeighting.add(radioBtnTF);
		btnGroupWeighting.add(radioBtnTFIDF);
		btnGroupWeighting.add(radioBtnIC);
		
		panelOptions.add(lblOptions);
		panelOptions.add(checkBoxStemming);
		panelOptions.add(checkBoxStopwords);
		
		panelOptions.add(lblWeighting);
		panelOptions.add(radioBtnTF);
		panelOptions.add(radioBtnTFIDF);
		panelOptions.add(radioBtnIC);
		
		add(panelOptions);
		
		lblFiles.setBounds(15, 215, 200, 25);
		add(lblFiles);
		paneListFiles.setBounds(30, 240, 150, 165);
		add(paneListFiles);
		
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

	public JRadioButton getRadioBtnTF() {
		return radioBtnTF;
	}

	public void setRadioBtnTF(JRadioButton radioBtnTF) {
		this.radioBtnTF = radioBtnTF;
	}

	public JRadioButton getRadioBtnTFIDF() {
		return radioBtnTFIDF;
	}

	public void setRadioBtnTFIDF(JRadioButton radioBtnTFIDF) {
		this.radioBtnTFIDF = radioBtnTFIDF;
	}

	public JRadioButton getRadioBtnIC() {
		return radioBtnIC;
	}

	public void setRadioBtnIC(JRadioButton radioBtnIC) {
		this.radioBtnIC = radioBtnIC;
	}

	public JList<String> getListFiles() {
		return listFiles;
	}

	public void setListFiles(JList<String> listFiles) {
		this.listFiles = listFiles;
	}
	
	public JLabel getLblFiles() {
		return lblFiles;
	}

	public void setLblFiles(JLabel lblFiles) {
		this.lblFiles = lblFiles;
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
