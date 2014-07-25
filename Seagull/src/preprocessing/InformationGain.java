package preprocessing;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class InformationGain {
	private InfoGainAttributeEval infoG;
	private Instances data;
	private AttributeSelection filter;
	private Ranker search;

	public InformationGain(Instances dataset) throws Exception{
		data = dataset;
		infoG = new InfoGainAttributeEval();
		infoG.buildEvaluator(data);
		filter = new AttributeSelection();
		search = new Ranker();
	}

	public Instances selectFeatures(){
		
		search.setThreshold(0.9);	
		
		Instances newData = data;

		try {
			filter.setEvaluator(infoG);
			filter.setSearch(search);
			filter.setInputFormat(data);
			// filter data
			newData = Filter.useFilter(data, filter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newData;
	}
	
	public static void main(String[] args){
		Instances data;
		try {
			data = DataSource.read("C:\\Program Files\\Weka-3-6\\data\\iris.arff");
			data.setClassIndex(data.numAttributes()-1);
			InformationGain ig = new InformationGain(data);
			
			System.out.println("BEFORE INFORMATION GAIN \n");
			showInfo(data);
			
			
			// Applying Information Gain
			data = ig.selectFeatures();
			
			System.out.println("AFTER INFORMATION GAIN \n");
			showInfo(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void showInfo(Instances data){
		System.out.println("Number of Attributes: " + (data.numAttributes() - 1));
		System.out.println("\n");
		for (int i = 0; i < data.numAttributes()-1; i++) {
			System.out.println(data.attribute(i).name());
			System.out.println("\t");
		}
	}
}
