package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import model.ClassificationModel.ClassifierType;
import model.ClassificationModel.EvaluationMethod;
import model.PreProcessingModel;
import ui.MainFrame;
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
			showAboutDialog();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnBrowse()))
			inputManager.browseFile();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnProcess()))
			processClassification();
		else if(e.getSource().equals(mainFrame.getClassificationPanel().getBtnSave()))
			showAboutDialog();
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
	
	private void processPreProcessing() {
		PreProcessingModel pModel = PreProcessingModel.getInstance();
		
		pModel.setStemming(mainFrame.getPreProcessingPanel()
				.getCheckBoxStemming().isSelected());
		pModel.setStopwords(mainFrame.getPreProcessingPanel()
				.getCheckBoxStopwords().isSelected());
		pModel.setNormalization(mainFrame.getPreProcessingPanel()
				.getCheckBoxNormalization().isSelected());
		
		preProcessingManager.startPreProcessing();
			
	}
	
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
				activateProgressBarDisableComponents();
				mainFrame.getClassificationPanel().getTxtAreaReport().setText(classificationManager.classify());
				deactivateProgressBarEnableComponents();
			}
		}
	}
	
	public void activateProgressBarDisableComponents() {
		for (Component c : mainFrame.getClassificationPanel().getPaneOptions().getComponents()) {
			c.setEnabled(false);
		}
		mainFrame.getClassificationPanel().getBtnBrowse().setEnabled(false);
		mainFrame.getClassificationPanel().getBtnProcess().setEnabled(false);
	}
	
	public void deactivateProgressBarEnableComponents() {
		for (Component c : mainFrame.getClassificationPanel().getPaneOptions().getComponents()) {
			c.setEnabled(true);
		}
		mainFrame.getClassificationPanel().getBtnBrowse().setEnabled(true);
		mainFrame.getClassificationPanel().getBtnProcess().setEnabled(true);
	}
}
