package processing;

import weka.classifiers.Evaluation;
import weka.core.Instances;

public class Report {

	Evaluation e;
	Instances data;
	final int NUM_CLASSES;
	double[] vectorP, vectorR, vectorROC;
	String[] names;
	
	public Report(Evaluation eval, Instances data){
		this.e = eval;
		this.data = data;
		NUM_CLASSES = this.data.numClasses();
		vectorP = new double[NUM_CLASSES];
		vectorR = new double[NUM_CLASSES];
		vectorROC = new double[NUM_CLASSES];		
	}

	public String summaryString(){
		return e.toSummaryString();
	}
	
	public String confusionMatrix() throws Exception{
		return e.toMatrixString();
	}
	
	public String classificationDetails() throws Exception{
		return e.toClassDetailsString();
	}
	
	public String correctAnswers(){
		return String.valueOf(e.correct());
	}
	
	public String incorrectAnswers(){
		return String.valueOf(e.incorrect());
	}
	
	public double accuracy(){
		return e.pctCorrect();
	}
	
	public double pctIncorrectAnswers(){
		return e.pctIncorrect();
	}
	
	public double[] precision(){		
		for(int i = 0; i < NUM_CLASSES;i++)
			vectorP[i] = e.precision(i);
		
		return vectorP;
	}
	
	public double[] recall(){
		for(int j = 0; j < NUM_CLASSES;j++)
			vectorR[j] = e.recall(j);
		
		return vectorR;
	}
	
	public double[] rocArea(){
		for(int k = 0; k < NUM_CLASSES;k++)
			vectorROC[k] = e.areaUnderROC(k);
		
		return vectorROC;		
	}
}
