package model;

import weka.core.Instances;


public class ClassificationModel {

	public enum ClassifierType{ NAIVE_BAYES, J48, IBK, KSTAR;}
	public enum EvaluationMethod{ CROSS_VALIDATION, PERCENTAGE_SPLIT, LOOCV;}
	
	private String path;
	private Instances instances;
	private ClassifierType[] classifierTypes;
	private EvaluationMethod evaluationMethod;	
	
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
	
	public ClassifierType[] getClassifierTypes() {
		return classifierTypes;
	}
	
	public void setClassifierTypes(ClassifierType[] classifierType) {
		this.classifierTypes = classifierType;
	}
	
	public int classifierTypesLength(){
		return classifierTypes.length;
	}
	
	public EvaluationMethod getEvaluationMethod() {
		return evaluationMethod;
	}
	
	public void setEvaluationMethod(EvaluationMethod evaluationMethod) {
		this.evaluationMethod = evaluationMethod;
	}
}
