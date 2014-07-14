package error;

import javax.swing.JOptionPane;

import control.FrameListener;

public class ExceptionsHandler {
	
	public static void showSelectFoldersStructureDialog() {
		String msg = "Please, select a valid folders structure.";
		JOptionPane.showMessageDialog(FrameListener.getMainFrame(), msg, "Select valid folders structure", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showUploadProcessFileDialog() {
		String msg = "Please, upload a file to be processed, either using the Pre-processing tab or the browse button.";
		JOptionPane.showMessageDialog(FrameListener.getMainFrame(), msg, "Upload process file", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showUnsupportedFileDialog() {
		String msg = "The file choosen is not supported, please try again.";
		JOptionPane.showMessageDialog(FrameListener.getMainFrame(), msg, "Unsupported file", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showSelectClassifierDialog() {
		String msg = "Please, select at least one Classifier to process.";
		JOptionPane.showMessageDialog(FrameListener.getMainFrame(), msg, "Select classifier", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showInputEMParameterDialog() {
		String msg = "Please, input the value parameter for the evaluation method choosen.";
		JOptionPane.showMessageDialog(FrameListener.getMainFrame(), msg, "Input evaluation method parameter", JOptionPane.WARNING_MESSAGE);
	}
}
