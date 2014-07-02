package processing;

import weka.classifiers.Evaluation;

public class Report {

	Evaluation e;

	public Report(Evaluation eval){
		this.e = eval;
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
	
	// TODO I prefered to return this one in double as someone might want to compare magnitudes instead of display.
	public double pctCorrectAnswers(){
		return e.pctCorrect();
	}
	
	public double pctIncorrectAnswers(){
		return e.pctIncorrect();
	}
	
	
}
