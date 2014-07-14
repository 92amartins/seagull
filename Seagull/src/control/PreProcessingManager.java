package control;

import model.PreProcessingModel;

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
	
}
