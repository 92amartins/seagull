package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

public class ChartFrame extends JFrame {

	JFreeChart accuracyChart;
	ChartPanel accuracyPanel;
	
	JFreeChart precisionChart;
	ChartPanel precisionPanel;
	
	JFreeChart recallChart;
	ChartPanel recallPanel;
	
	JFreeChart rocChart;
	ChartPanel rocPanel;
	
	JPanel panel;
	JTabbedPane tabbedPane;
	
	public ChartFrame() {
		super("Charts");

		setBounds(200, 200, 800, 600);
		create();
	}
	
	public void create() {
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		accuracyChart = createAccuracyChart();
		accuracyPanel = new ChartPanel(accuracyChart);
		
		precisionChart = createPrecisionChart();
		precisionPanel = new ChartPanel(precisionChart);
		
		recallChart = createRecallChart();
		recallPanel = new ChartPanel(recallChart);
		
		rocChart = createROCChart();
		rocPanel = new ChartPanel(rocChart);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(5, 5, 790, 575);
		
		tabbedPane.addTab("Accuracy", accuracyPanel);
		tabbedPane.addTab("Precision", precisionPanel);
		tabbedPane.addTab("Recall", recallPanel);
		tabbedPane.addTab("Roc", rocPanel);
		panel.add(tabbedPane);
		
		setResizable(false);
		setVisible(true);
	}
	
	public JFreeChart createAccuracyChart() {
		JFreeChart chart = ChartFactory.createBarChart("Accuracy", "Classifier", "Accuracy (%)", null, 
			PlotOrientation.VERTICAL,
	        true,                     
	        true,                     
	        false);
		
		return chart;
	}
	
	public JFreeChart createPrecisionChart() {
		JFreeChart chart = ChartFactory.createBarChart("Precision", "Classifier", "Precision (%)", null, 
			PlotOrientation.VERTICAL,
	        true,                     
	        true,                     
	        false);
		
		return chart;
	}
	
	public JFreeChart createRecallChart() {
		JFreeChart chart = ChartFactory.createBarChart("Recall", "Classifier", "Accuracy (%)", null, 
			PlotOrientation.VERTICAL,
	        true,                     
	        true,                     
	        false);
		
		return chart;
	}
	
	public JFreeChart createROCChart() {
		JFreeChart chart = ChartFactory.createBarChart("ROC", "Classifier", "Accuracy (%)", null, 
			PlotOrientation.VERTICAL,
	        true,                     
	        true,                     
	        false);
		
		return chart;
	}
	
}
