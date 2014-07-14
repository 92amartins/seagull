package utils;

import java.util.ArrayList;

import weka.core.Instances;
import error.ExceptionsHandler;
import model.ClassificationModel.ClassifierType;

public class ValidateClassification {

	public static boolean isInstancesUploaded(Instances instances) {
		if(instances == null) {
			ExceptionsHandler.showUploadProcessFileDialog();
			return false;
		} else
			return true;
	}
	
	public static boolean isClassifierSelected(ArrayList<ClassifierType> classifierTypes) {
		if(classifierTypes.isEmpty()) {
			ExceptionsHandler.showSelectClassifierDialog();
			return false;
		} else
			return true;
	}
	
	public static boolean hasEvaluationMethodParameter() {
		//TODO how to check if the evaluation parameter corresponding to the radioButton selected was inputed
		if(true) {
			ExceptionsHandler.showInputEMParameterDialog();
			return false;
		} else
			return true;
	}
}
