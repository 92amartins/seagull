package control;

import input.InstancesGenerator;
import model.ClassificationModel;
import model.PreProcessingModel;
import preprocessing.BigBagReducer;
import preprocessing.StemmingMaster;
import preprocessing.StopwordRemover;
import preprocessing.Weighter;
import preprocessing.MatrixGenerator;


public class PreProcessingManager {

	private PreProcessingModel preProcessingModel;
	
	public PreProcessingManager() {
		preProcessingModel = PreProcessingModel.getInstance();
	}

	public PreProcessingModel getPreProcessingModel() {
		return preProcessingModel;
	}

	public void setPreProcessingModel(PreProcessingModel preProcessingModel) {
		this.preProcessingModel = preProcessingModel;
	}
	
	public void startPreProcessing(){
		InstancesGenerator ig = new InstancesGenerator();
		BigBagReducer bbr = new BigBagReducer();
		
		// Removing repeated tokens
		preProcessingModel.setBigBag(bbr.reduceBag(preProcessingModel.getBigBag()));
		
		
		// STEMMING
		if(preProcessingModel.isStemming()){
			StemmingMaster sm = new StemmingMaster();
			sm.StemmBag(preProcessingModel.getBigBag());
		}
		
		// STOPWORDS
		if(preProcessingModel.isStopwords()){
			StopwordRemover swr = new StopwordRemover();
			swr.removeAllStopwords(preProcessingModel.getBigBag());
		}
		
		// NORMALIZATION
		if(preProcessingModel.isNormalization()){
			Weighter w = new Weighter();
			preProcessingModel.setBigBag(w.weightTFIDF(preProcessingModel.getBigBag()));
		}else{
			Weighter w = new Weighter();
			preProcessingModel.setBigBag(w.weightTF(preProcessingModel.getBigBag()));
		}
		
		
		ClassificationModel cm = ClassificationModel.getInstance();
		
		cm.setInstances(ig.generate(preProcessingModel.getBigBag()));
	}
	
}
