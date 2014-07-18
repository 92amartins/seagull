package model;

import java.util.ArrayList;

import weka.core.Instances;


public class ClassificationModel {

	public enum ClassifierType{
		NAIVE_BAYES("Naïve Bayes"), 
		J48("J48"),
		IBK("Knn"),
		KSTAR("Kstar"),
		COSINE("Cosine Similarity");
		
		private String name;
		private ClassifierType(String name) { this.name = name; }
		public String getName() { return this.name; }
	}
	
	public enum EvaluationMethod{
		CROSS_VALIDATION("Cross-validation"), 
		PERCENTAGE_SPLIT("Percentage Split"), 
		LOOCV("LOOCV");
		
		private String name;
		private EvaluationMethod(String name) { this.name = name; }
		public String getName() { return this.name; }
	}
	
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
