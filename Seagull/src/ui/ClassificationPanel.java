package ui;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClassificationPanel extends MasterPanel{
	
//	private JButton btnBrowse;
	
//	private JPanel panelOptions;
	
	private JLabel lblClassifier;
	private JCheckBox checkBoxNaiveBayes;
	private JCheckBox checkBoxJ48;
	private JCheckBox checkBoxKnn;
	private JCheckBox checkBoxCosSimilarity;
	
	private JLabel lblEvaluationMethod;
	private JRadioButton radioBtnCrossValidation;
	private JRadioButton radioBtnPercentageSplit;
	private JRadioButton radioBtnLOOCV;
	private ButtonGroup btnGroupEM;
	//TODO txtFolds and txtSplit should only allow positive Integers
	private JTextField txtFolds;
	private JLabel lblFolds;
	private JTextField txtSplit;
	private JLabel lblSplit;
	
//	private JButton btnProcess;

	private JLabel lblReport;
	private JScrollPane paneReport;
	private JTextArea txtAreaReport;
	
//	private JProgressBar progressBar;
	
	private JButton btnSave;
	private JButton btnChart;
	
	public ClassificationPanel() {
		setLayout(null);
		
		btnBrowse = new JButton("Browse...");
		
		panelOptions = new JPanel();	
		
		lblClassifier = new JLabel("Classifier:");
		checkBoxNaiveBayes = new JCheckBox("Naïve Bayes");
		checkBoxJ48 = new JCheckBox("J48");
		checkBoxKnn = new JCheckBox("K-nn");
		checkBoxCosSimilarity = new JCheckBox("Cosine Similarity");
		
		lblEvaluationMethod = new JLabel("Evaluation Method:");
		radioBtnCrossValidation = new JRadioButton("Cross-validation");
		radioBtnPercentageSplit = new JRadioButton("Percentage Split");
		radioBtnLOOCV = new JRadioButton("LOOCV");
		btnGroupEM = new ButtonGroup();
		txtFolds = new JTextField("10");
		lblFolds = new JLabel("folds");
		txtSplit = new JTextField("80");
		lblSplit = new JLabel("% training set");

		btnProcess = new JButton("Process!");
		
		lblReport = new JLabel("Report:");
		txtAreaReport = new JTextArea();
		paneReport = new JScrollPane(txtAreaReport);
		
		progressBar = new JProgressBar();
		
		btnSave = new JButton("Save to file");
		btnChart = new JButton("Generate chart");
		
		createPanel();
	}
	
	public void createPanel() {
		btnBrowse.setBounds(10, 10, 100, 25);
		add(btnBrowse);
		
		panelOptions.setBounds(10, 45, 160, 275);
		panelOptions.setLayout(null);
		
		lblClassifier.setBounds(5, 5, 140, 20);
		checkBoxNaiveBayes.setBounds(10, 25, 140, 20);
		checkBoxJ48.setBounds(10, 50, 140, 20);
		checkBoxKnn.setBounds(10, 75, 140, 20);
		checkBoxCosSimilarity.setBounds(10, 100, 140, 20);
		
		lblEvaluationMethod.setBounds(5, 125, 140, 20);
		radioBtnCrossValidation.setBounds(10, 150, 140, 20);
		radioBtnCrossValidation.setSelected(true);
		txtFolds.setBounds(30, 175, 30, 20);
		lblFolds.setBounds(60, 175, 100, 20);
		radioBtnPercentageSplit.setBounds(10, 200, 140, 20);
		txtSplit.setBounds(30, 225, 30, 20);
		lblSplit.setBounds(60, 225, 100, 20);
		radioBtnLOOCV.setBounds(10, 250, 140, 20);
		btnGroupEM.add(radioBtnCrossValidation);
		btnGroupEM.add(radioBtnPercentageSplit);
		btnGroupEM.add(radioBtnLOOCV);
		
		panelOptions.add(lblClassifier);
		panelOptions.add(checkBoxNaiveBayes);
		panelOptions.add(checkBoxJ48);
		panelOptions.add(checkBoxKnn);
		panelOptions.add(checkBoxCosSimilarity);
		
		panelOptions.add(lblEvaluationMethod);
		panelOptions.add(radioBtnCrossValidation);
		panelOptions.add(txtFolds);
		panelOptions.add(lblFolds);
		panelOptions.add(radioBtnPercentageSplit);
		panelOptions.add(txtSplit);
		panelOptions.add(lblSplit);
		panelOptions.add(radioBtnLOOCV);
		
		add(panelOptions);
		
		btnProcess.setBounds(10, 335, 100, 25);
		add(btnProcess);
		
		lblReport.setBounds(200, 10, 100, 25);
		add(lblReport);
		
		paneReport.setBounds(200, 40, 560, 370);
		txtAreaReport.setEditable(false);
		txtAreaReport.setFont(new Font("Courier New", Font.PLAIN, 12));
		add(paneReport);

		progressBar.setBounds(200, 415, 250, 25);
		add(progressBar);
		
		btnSave.setBounds(530, 415, 100, 25);
		add(btnSave);	
		
		btnChart.setBounds(640, 415, 120, 25);
		add(btnChart);		
	}

	public JButton getBtnBrowse() {
		return btnBrowse;
	}

	public void setBtnBrowse(JButton btnBrowse) {
		this.btnBrowse = btnBrowse;
	}

	public JPanel getPanelOptions() {
		return panelOptions;
	}

	public void setPanelOptions(JPanel panelOptions) {
		this.panelOptions = panelOptions;
	}
	
	public JCheckBox getCheckBoxNaiveBayes() {
		return checkBoxNaiveBayes;
	}

	public void setCheckBoxNaiveBayes(JCheckBox checkBoxNaiveBayes) {
		this.checkBoxNaiveBayes = checkBoxNaiveBayes;
	}

	public JCheckBox getCheckBoxJ48() {
		return checkBoxJ48;
	}

	public void setCheckBoxJ48(JCheckBox checkBoxJ48) {
		this.checkBoxJ48 = checkBoxJ48;
	}

	public JCheckBox getCheckBoxKnn() {
		return checkBoxKnn;
	}

	public void setCheckBoxKnn(JCheckBox checkBoxKnn) {
		this.checkBoxKnn = checkBoxKnn;
	}
	
	public JRadioButton getRadioBtnCrossValidation() {
		return radioBtnCrossValidation;
	}

	public void setRadioBtnCrossValidation(JRadioButton radioBtnCrossValidation) {
		this.radioBtnCrossValidation = radioBtnCrossValidation;
	}

	public JTextField getTxtFolds() {
		return txtFolds;
	}

	public void setTxtFolds(JTextField txtFolds) {
		this.txtFolds = txtFolds;
	}
	
	public JRadioButton getRadioBtnPercentageSplit() {
		return radioBtnPercentageSplit;
	}

	public void setRadioBtnPercentageSplit(JRadioButton radioBtnPercentageSplit) {
		this.radioBtnPercentageSplit = radioBtnPercentageSplit;
	}
	
	public JTextField getTxtSplit() {
		return txtSplit;
	}

	public void setTxtSplit(JTextField txtSplit) {
		this.txtSplit = txtSplit;
	}

	public JRadioButton getRadioBtnLOOCV() {
		return radioBtnLOOCV;
	}

	public void setRadioBtnLOOCV(JRadioButton radioBtnLOOCV) {
		this.radioBtnLOOCV = radioBtnLOOCV;
	}
	
	public JButton getBtnProcess() {
		return btnProcess;
	}

	public void setBtnProcess(JButton btnProcess) {
		this.btnProcess = btnProcess;
	}

	public JScrollPane getPaneReport() {
		return paneReport;
	}

	public void setPaneReport(JScrollPane paneReport) {
		this.paneReport = paneReport;
	}

	public JTextArea getTxtAreaReport() {
		return txtAreaReport;
	}

	public void setTxtAreaReport(JTextArea txtAreaReport) {
		this.txtAreaReport = txtAreaReport;
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnChart() {
		return btnChart;
	}

	public void setBtnChart(JButton btnChart) {
		this.btnChart = btnChart;
	}

}