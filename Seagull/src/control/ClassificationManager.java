package control;

import model.ClassificationModel;
import processing.Classification;
import processing.Report;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassificationManager {
	
	private ClassificationModel classificationModel;
	
	public String classify(String path) {
		String report = "";
		try{
			Instances data = DataSource.read(path);
			data.setClassIndex(data.numAttributes()-1);
			Classification cls = new Classification(classificationModel.getClassifierTypes());
			
			Evaluation[] evals = cls.getEvaluations();
			Report[] r = new Report[evals.length];
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0; i < evals.length;i++){
				r[i] = new Report(evals[i]);
				
				sb.append(r[i].summaryString());
				sb.append("\n \n \n");
				sb.append(r[i].classificationDetails());
				sb.append("\n \n \n");
				sb.append(r[i].confusionMatrix());
				sb.append("==========================================================");
			}
			
			report = sb.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return report;
	}
}
