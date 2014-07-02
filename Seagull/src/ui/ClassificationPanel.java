package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

public class ClassificationPanel extends JPanel{
	
	private JButton btnBrowse;
	private JButton btnProcess;
	private JProgressBar progressBar;
	private JLabel lblOptions;
	private JScrollPane paneOptions;
	private JLabel lblReport;
	private JScrollPane paneReport;
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
		paneReport = new JScrollPane();
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
		add(paneReport);
		
		btnSave.setBounds(530, 420, 100, 25);
		add(btnSave);	
		
		btnChart.setBounds(640, 420, 120, 25);
		add(btnChart);		
	}

}
