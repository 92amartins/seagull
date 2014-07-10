package control;

import model.ClassificationModel;
import processing.Classification;
import processing.Report;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class ClassificationManager {
	
	private ClassificationModel classificationModel;
	
	public ClassificationManager() {
		classificationModel = ClassificationModel.getInstance();
	}
	
	public String classify() {
		String report = "";
		try{
			Instances data = classificationModel.getInstances();
			data.setClassIndex(data.numAttributes()-1);
			Classification cls = new Classification(classificationModel.getClassifierTypes());
			switch (classificationModel.getEvaluationMethod()) {
				case CROSS_VALIDATION:
					cls.performCrossValidation(data, classificationModel.getAdditionalParamEM());
					break;
				case PERCENTAGE_SPLIT:
					cls.performTestSetEvaluation(data, classificationModel.getAdditionalParamEM());
					break;
				case LOOCV:
					cls.performLOOCV(data);
					break;
			}
						
			Evaluation[] evals = cls.getEvaluations();
			Report[] r = new Report[evals.length];
			StringBuffer sb = new StringBuffer();
			
			//TODO show which classifier and evaluation method are being shown
			for(int i = 0; i < evals.length;i++){
				r[i] = new Report(evals[i]);
				
				sb.append(r[i].summaryString());
				sb.append("\n \n \n");
				sb.append(r[i].classificationDetails());
				sb.append("\n \n \n");
				sb.append(r[i].confusionMatrix());
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
}
