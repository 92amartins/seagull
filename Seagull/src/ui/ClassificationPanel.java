package ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClassificationPanel extends JPanel{
	
	private JButton btnBrowse;
	private JButton btnProcess;
	private JProgressBar progressBar;
	private JLabel lblOptions;
	private JScrollPane paneOptions;
	private JLabel lblReport;
	private JScrollPane paneReport;
	private JTextArea txtAreaReport;
	private JButton btnSave;
	private JButton btnChart;
	
	public ClassificationPanel() {
		setLayout(null);
		
		btnBrowse = new JButton("Browse...");
		btnProcess = new JButton("Process!");
		progressBar = new JProgressBar();
		lblOptions = new JLabel("Options: ");
		paneOptions = new JScrollPane();		
		lblReport = new JLabel("Report:");
		txtAreaReport = new JTextArea();
		paneReport = new JScrollPane(txtAreaReport);
		btnSave = new JButton("Save to file");
		btnChart = new JButton("Generate chart");
		
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
		
		paneOptions.setBounds(10, 140, 150, 300);
		add(paneOptions);
		
		lblReport.setBounds(200, 10, 100, 25);
		add(lblReport);
		
		paneReport.setBounds(200, 40, 560, 370);
		txtAreaReport.setEditable(false);
		txtAreaReport.setFont(new Font("Courier New", Font.PLAIN, 12));
		add(paneReport);
		
		btnSave.setBounds(530, 420, 100, 25);
		add(btnSave);	
		
		btnChart.setBounds(640, 420, 120, 25);
		add(btnChart);		
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

	public JScrollPane getPaneOptions() {
		return paneOptions;
	}

	public void setPaneOptions(JScrollPane paneOptions) {
		this.paneOptions = paneOptions;
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