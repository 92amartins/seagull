package ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class MasterPanel extends JPanel {
	
	JButton btnBrowse;
	JPanel panelOptions;
	JProgressBar progressBar;
	JButton btnProcess;
	
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
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	
	public JButton getBtnProcess() {
		return btnProcess;
	}
	
	public void setBtnProcess(JButton btnProcess) {
		this.btnProcess = btnProcess;
	}
	
}
