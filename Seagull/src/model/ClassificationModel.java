package model;

import processing.Classification;
import weka.core.Instances;


public class ClassificationModel {
	
	private String path;
	private Instances instances;
	//TODO move Enum ClassifierType to this class!
	private Classification.ClassifierType classifierType;
	//TODO create Enum to evaluationMethod;
	private String evaluationMethod;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public Instances getInstances() {
		return instances;
	}
	
	public void setInstances(Instances instances) {
		this.instances = instances;
	}
	
	public Classification.ClassifierType getClassifierType() {
		return classifierType;
	}
	
	public void setClassifierType(Classification.ClassifierType classifierType) {
		this.classifierType = classifierType;
	}
	
	public String getEvaluationMethod() {
		return evaluationMethod;
	}
	
	public void setEvaluationMethod(String evaluationMethod) {
		this.evaluationMethod = evaluationMethod;
	}
}
