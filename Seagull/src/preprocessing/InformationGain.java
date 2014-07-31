package preprocessing;

import javax.swing.JOptionPane;

import control.FrameListener;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;;

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
		search.setThreshold(0.0);
	}

	public Instances selectFeatures(){	
		
		Instances newData = new Instances(data);
		
		try {
			filter.setEvaluator(infoG);
			filter.setSearch(search);
			filter.setInputFormat(data);
			showInfo(data);
			
			// filter data
			newData = Filter.useFilter(data, filter);
			showInfo(newData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newData;
	}
	
	private static void showInfo(Instances data){
		JOptionPane.showMessageDialog(FrameListener.getMainFrame(), "Number of Attributes: " + (data.numAttributes() - 1 + "\n"));
	}
}
