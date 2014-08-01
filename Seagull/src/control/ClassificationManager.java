package control;

import model.ClassificationModel;
import processing.Classification;
import processing.Report;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class ClassificationManager {
	
	private ClassificationModel classificationModel;
	private Report[] reports;
	
	public ClassificationManager() {
		classificationModel = ClassificationModel.getInstance();
	}
	
	public String classify() {
		String report = "";
		try{
			Instances data = classificationModel.getInstances();
			data.setClassIndex(data.numAttributes()-1);
			classificationModel.setInstances(data);
			Classification cls = new Classification(classificationModel.getClassifierTypes());
			
			String strEM = "";
			switch (classificationModel.getEvaluationMethod()) {
				case CROSS_VALIDATION:
					cls.performCrossValidation(classificationModel.getInstances(), classificationModel.getAdditionalParamEM());
					strEM = classificationModel.getEvaluationMethod().getName()+" ("+classificationModel.getAdditionalParamEM()+" folds)";
					break;
				case PERCENTAGE_SPLIT:
					cls.performTestSetEvaluation(classificationModel.getInstances(), classificationModel.getAdditionalParamEM());
					strEM = classificationModel.getEvaluationMethod().getName()+" ("+classificationModel.getAdditionalParamEM()+"% training set)";
					break;
				case LOOCV:
					cls.performLOOCV(classificationModel.getInstances());
					strEM = classificationModel.getEvaluationMethod().getName();
					break;
			}
						
			Evaluation[] evals = cls.getEvaluations();
			reports = new Report[evals.length];
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0; i < evals.length;i++){
				reports[i] = new Report(evals[i], data);
				
				sb.append("Classifier: "+classificationModel.getClassifierTypes().get(i).getName()+"\n");
				sb.append("Evaluation Method: "+strEM+"\n");
				sb.append(reports[i].summaryString());
				sb.append("\n \n \n");
				sb.append(reports[i].classificationDetails());
				sb.append("\n \n \n");
				sb.append(reports[i].confusionMatrix());
				sb.append("==============================================================================================\n");
			}
			
			report = sb.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return report;
	}

	public ClassificationModel getClassificationModel() {
		return classificationModel;
	}

	public void setClassificationModel(ClassificationModel classificationModel) {
		this.classificationModel = classificationModel;
	}

	public Report[] getReports() {
		return reports;
	}

	public void setReports(Report[] reports) {
		this.reports = reports;
	}
	
}
