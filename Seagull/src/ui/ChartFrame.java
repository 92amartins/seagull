package ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.ClassificationModel.ClassifierType;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import processing.Report;

public class ChartFrame extends JFrame {

	JFreeChart accuracyChart;
	ChartPanel accuracyPanel;

	JFreeChart precisionChart;
	ChartPanel precisionPanel;

	JFreeChart recallChart;
	ChartPanel recallPanel;

	JFreeChart rocAreaChart;
	ChartPanel rocAreaPanel;

	JPanel panel;
	JTabbedPane tabbedPane;

	Report reports[];
	ArrayList<String> categories = new ArrayList<String>();

	public ChartFrame(Report reports[]) {
		super("Charts");

		this.reports = reports;
		for (ClassifierType classifierType : reports[0].getCategories()) {
			categories.add(classifierType.getName());
		}

		setBounds(200, 150, 800, 600);
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

		rocAreaChart = createROCAreaChart();
		rocAreaPanel = new ChartPanel(rocAreaChart);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(5, 5, 790, 575);

		tabbedPane.addTab("Accuracy", accuracyPanel);
		tabbedPane.addTab("Precision", precisionPanel);
		tabbedPane.addTab("Recall", recallPanel);
		tabbedPane.addTab("ROC Area", rocAreaPanel);
		panel.add(tabbedPane);

		setResizable(false);
		setVisible(true);
	}

	public JFreeChart createAccuracyChart() {
		JFreeChart chart = ChartFactory.createBarChart("Accuracy", "Classifier", "Accuracy (%)", datasetAccuracy(), 
				PlotOrientation.VERTICAL,
				true,                     
				true,                     
				false);

		return chart;
	}

	public CategoryDataset datasetAccuracy() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < reports.length; i++) {
			dataset.addValue(reports[i].getAccuracy(), "Accuracy", categories.get(i));
		}

		return dataset;
	}

	public JFreeChart createPrecisionChart() {
		JFreeChart chart = ChartFactory.createBarChart("Precision", "Classifier", "Precision", datasetPrecision(), 
				PlotOrientation.VERTICAL,
				true,                     
				true,                     
				false);

		return chart;
	}

	public CategoryDataset datasetPrecision() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < reports[0].getSeries().length; i++) {
			for (int j = 0; j < reports.length; j++) {
				dataset.addValue(reports[j].getPrecision()[i], reports[0].getSeries()[i], categories.get(j));
			}
		}

		return dataset;
	}

	public JFreeChart createRecallChart() {
		JFreeChart chart = ChartFactory.createBarChart("Recall", "Classifier", "Recall", datasetRecall(), 
				PlotOrientation.VERTICAL,
				true,                     
				true,                     
				false);

		return chart;
	}

	public CategoryDataset datasetRecall() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < reports[0].getSeries().length; i++) {
			for (int j = 0; j < reports.length; j++) {
				dataset.addValue(reports[j].getRecall()[i], reports[0].getSeries()[i], categories.get(j));
			}
		}

		return dataset;
	}

	public JFreeChart createROCAreaChart() {
		JFreeChart chart = ChartFactory.createBarChart("ROC Area", "Classifier", "ROC Area", datasetROCArea(), 
				PlotOrientation.VERTICAL,
				true,                     
				true,                     
				false);

		return chart;
	}

	public CategoryDataset datasetROCArea() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i < reports[0].getSeries().length; i++) {
			for (int j = 0; j < reports.length; j++) {
				dataset.addValue(reports[j].getROCArea()[i], reports[0].getSeries()[i], categories.get(j));
			}
		}
        
        return dataset;
	}
}
