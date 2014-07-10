package model;

import java.util.ArrayList;

import weka.core.Instances;


public class ClassificationModel {

	public enum ClassifierType{ NAIVE_BAYES, J48, IBK, KSTAR;}
	public enum EvaluationMethod{ CROSS_VALIDATION, PERCENTAGE_SPLIT, LOOCV;}
	
	private String path;
	private Instances instances;
	private ArrayList<ClassifierType> classifierTypes;
	private EvaluationMethod evaluationMethod;	
	private Integer additionalParamEM;
	
	private static ClassificationModel instance = null;
	
	protected ClassificationModel() {};
	
	public static ClassificationModel getInstance() {
		if(instance == null)
			instance = new ClassificationModel();
		return instance;
	}
	
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
	
	public ArrayList<ClassifierType> getClassifierTypes() {
		return classifierTypes;
	}
	
	public void setClassifierTypes(ArrayList<ClassifierType> classifierType) {
		this.classifierTypes = classifierType;
	}
	
	public EvaluationMethod getEvaluationMethod() {
		return evaluationMethod;
	}
	
	public void setEvaluationMethod(EvaluationMethod evaluationMethod) {
		this.evaluationMethod = evaluationMethod;
	}

	public Integer getAdditionalParamEM() {
		return additionalParamEM;
	}

	public void setAdditionalParamEM(Integer additionalParamEM) {
		this.additionalParamEM = additionalParamEM;
	}
}
