package control;

import processing.Classification;
import processing.Report;
import processing.Classification.ClassifierType;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassificationManager {
	
	public String classify(String path) {
		String report = "";
		try{
			Instances data = DataSource.read(path);
			data.setClassIndex(data.numAttributes()-1);
			Classification cls = new Classification(ClassifierType.NAIVE_BAYES);
			Report r = new Report(cls.performCrossValidation(data, 10));
			
			StringBuffer sb = new StringBuffer();
			sb.append(r.summaryString());
			sb.append("\n \n \n");
			sb.append(r.classificationDetails());
			sb.append("\n \n \n");
			sb.append(r.confusionMatrix());
			
			report = sb.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return report;
	}
}
