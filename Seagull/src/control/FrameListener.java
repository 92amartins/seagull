package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import model.ClassificationModel.ClassifierType;
import model.ClassificationModel.EvaluationMethod;
import model.PreProcessingModel;
import ui.MainFrame;
import ui.MasterPanel;
import error.ExceptionsHandler;

public class FrameListener implements ActionListener {
	
	private static MainFrame mainFrame;
	
	private ClassificationManager classificationManager = new ClassificationManager();
	private InputManager inputManager = new InputManager();
	private PreProcessingManager preProcessingManager = new PreProcessingManager();
	
	public FrameListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public static MainFrame getMainFrame() {
		return mainFrame;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(mainFrame.getBtnAbout()))
			showAboutDialog();
		else if(e.getSource().equals(mainFrame.getPreProcessingPanel().getBtnBrowse()))
			inputPreProcessing();
		else if(e.getSource().equals(mainFrame.getPreProcessingPanel().getBtnProcess()))
			processPreProcessing();
		else if(e.getSource().equals(mainFrame.getPreProcessingPanel().getBtnClassify()))
			mainFrame.getTabbedPane().setSelectedIndex(1);
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnBrowse()))
			inputManager.browseFile();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnProcess()))
			processClassification();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnSave()))
			saveToFile();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnChart()))
			showAboutDialog();
	}
	
	private void showAboutDialog() {
		String msg = "Tool done as a research project by Sciece without Borders students in University of Brighton. \n"
				+ "Students: \n"
				+ "- Andrei Martins Silva \n"
				+ "- Camilla Maciel Quitério de Oliveira \n"
				+ "- Humberto Politi de Oliveira \n"
				+ "Project Supervisor: Dr Gulden Uchyigit";
		JOptionPane.showMessageDialog(mainFrame, msg, "About Seagull Tool", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void inputPreProcessing() {
		inputManager.browseDirectory();
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (String fileName : preProcessingManager.getPreProcessingModel().getFilesList())
			model.addElement(fileName);
		
		if(!model.isEmpty()) {
			mainFrame.getPreProcessingPanel().getLblTotalFiles().setText(model.size()-preProcessingManager.getPreProcessingModel().getBigBag().size()+" files imported.");
			mainFrame.getPreProcessingPanel().getListFiles().setModel(model);
		}
	}
	
	SwingWorker preProcessingWorker = new SwingWorker<Void, Void>() {
	    @Override
	    public Void doInBackground() {
	    	PreProcessingModel preProcessingModel = PreProcessingModel.getInstance();
			
			preProcessingModel.setStemming(mainFrame.getPreProcessingPanel().getCheckBoxStemming().isSelected());
			preProcessingModel.setStopwords(mainFrame.getPreProcessingPanel().getCheckBoxStopwords().isSelected());
			preProcessingModel.setNormalization(mainFrame.getPreProcessingPanel().getCheckBoxNormalization().isSelected());
			
			preProcessingManager.startPreProcessing();	        
			return null;
	    }
	    
	    protected void done() {
	    	mainFrame.getPreProcessingPanel().getBtnClassify().setEnabled(true);
	    	deactivateProgressBarEnableComponents(mainFrame.getPreProcessingPanel());
	    };
	};
	
	private void processPreProcessing() {
		mainFrame.getPreProcessingPanel().getBtnClassify().setEnabled(false);
		activateProgressBarDisableComponents(mainFrame.getPreProcessingPanel());
		//TODO how to deal if the user clicks the Process again? It only executes once. (The same for Classification)
		preProcessingWorker.execute();
	}
	
	SwingWorker classificationWorker = new SwingWorker<Void, Void>() {
	    @Override
	    public Void doInBackground() {
	    	mainFrame.getClassificationPanel().getTxtAreaReport().setText(classificationManager.classify());
			return null;
	    }
	    
	    protected void done() {
	    	mainFrame.getClassificationPanel().getBtnSave().setEnabled(true);
			mainFrame.getClassificationPanel().getBtnChart().setEnabled(true);
	    	deactivateProgressBarEnableComponents(mainFrame.getClassificationPanel());
	    };
	};
	
	private void processClassification() {
		//TODO create a Validation class or separated methods to validate the things below.
		ArrayList<ClassifierType> classifierTypes = new ArrayList<ClassifierType>();
		if(mainFrame.getClassificationPanel().getCheckBoxNaiveBayes().isSelected())
			classifierTypes.add(ClassifierType.NAIVE_BAYES);
		if(mainFrame.getClassificationPanel().getCheckBoxJ48().isSelected())
			classifierTypes.add(ClassifierType.J48);
		if(mainFrame.getClassificationPanel().getCheckBoxKnn().isSelected())
			classifierTypes.add(ClassifierType.IBK);
		
		if(mainFrame.getClassificationPanel().getRadioBtnCrossValidation().isSelected()) {
			classificationManager.getClassificationModel().setEvaluationMethod(EvaluationMethod.CROSS_VALIDATION);
			if(mainFrame.getClassificationPanel().getTxtFolds().getText().isEmpty()) {
				ExceptionsHandler.showInputEMParameterDialog();
				return;
			} else
				classificationManager.getClassificationModel().setAdditionalParamEM(Integer.valueOf(mainFrame.getClassificationPanel().getTxtFolds().getText()));
		} else if(mainFrame.getClassificationPanel().getRadioBtnPercentageSplit().isSelected()) {
			classificationManager.getClassificationModel().setEvaluationMethod(EvaluationMethod.PERCENTAGE_SPLIT);
			if(mainFrame.getClassificationPanel().getTxtSplit().getText().isEmpty()) {
				ExceptionsHandler.showInputEMParameterDialog();
				return;
			} else
				classificationManager.getClassificationModel().setAdditionalParamEM(Integer.valueOf(mainFrame.getClassificationPanel().getTxtSplit().getText()));
		} else {
			classificationManager.getClassificationModel().setEvaluationMethod(EvaluationMethod.LOOCV);
			classificationManager.getClassificationModel().setAdditionalParamEM(null);
		}
		
		if(classificationManager.getClassificationModel().getInstances() == null)
			ExceptionsHandler.showUploadProcessFileDialog();
		else {
			if(classifierTypes.isEmpty())
				ExceptionsHandler.showSelectClassifierDialog();
			else {
				classificationManager.getClassificationModel().setClassifierTypes(classifierTypes);
				mainFrame.getClassificationPanel().getBtnSave().setEnabled(false);
				mainFrame.getClassificationPanel().getBtnChart().setEnabled(false);
				activateProgressBarDisableComponents(mainFrame.getClassificationPanel());
				classificationWorker.execute();
			}
		}
	}
	
	public void activateProgressBarDisableComponents(MasterPanel panel) {
		for (Component c : panel.getPanelOptions().getComponents()) 
			c.setEnabled(false);
		
		mainFrame.getTabbedPane().setEnabled(false);
		panel.getProgressBar().setIndeterminate(true);
		panel.getBtnBrowse().setEnabled(false);
		panel.getBtnProcess().setEnabled(false);
	}
	
	public void deactivateProgressBarEnableComponents(MasterPanel panel) {
		for (Component c : panel.getPanelOptions().getComponents()) 
			c.setEnabled(true);
		
		mainFrame.getTabbedPane().setEnabled(true);
		panel.getProgressBar().setIndeterminate(false);
		panel.getBtnBrowse().setEnabled(true);
		panel.getBtnProcess().setEnabled(true);
	}
	
	//TODO where to put this method?
	public void saveToFile() {
        String text = mainFrame.getClassificationPanel().getTxtAreaReport().getText();

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File( "./"));
        int actionDialog = chooser.showSaveDialog(mainFrame);
        if (actionDialog == JFileChooser.APPROVE_OPTION) {
            File fileName = new File(chooser.getSelectedFile( ) + "" );

            if(fileName.exists()) {
                actionDialog = JOptionPane.showConfirmDialog(mainFrame, "Replace existing file?");
                if (actionDialog == JOptionPane.NO_OPTION)
                    return;
            }
            try {
            	BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            	out.write(text);
            	out.close();
            } catch(Exception e) {
                 System.err.println("Error: " + e.getMessage());
            }
        }
	}
}
