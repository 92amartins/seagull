package processing;

import processing.Classification.ClassifierType;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassificationTest {
	
	public static void main(String[] args) {
		try{
			Instances data = DataSource.read("C:\\Program Files\\Weka-3-6\\data\\iris.arff");
			data.setClassIndex(data.numAttributes()-1);
			Classification cls = new Classification(ClassifierType.NAIVE_BAYES);
			Report r = new Report(cls.performTestSetEvaluation(data, 80));
			
			System.out.println(r.summaryString());
			System.out.println(r.classificationDetails());
			System.out.println(r.confusionMatrix());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
