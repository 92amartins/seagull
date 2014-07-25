package model;

import input.Cell;

import java.util.ArrayList;
import java.util.List;

public class PreProcessingModel {
	
	public enum WeightingType{TF, TF_IDF, IG;}
	
	private String path;	
	private boolean stemming;
	private boolean stopwords;
	private WeightingType weightingType;
	private List<List<List<Cell>>> bigBag;
	private ArrayList<String> bagOfWords;
	private ArrayList<String> filesList = new ArrayList<String>();
	
	private static PreProcessingModel instance = null;
	
	protected PreProcessingModel() {};
	
	public static PreProcessingModel getInstance() {
		if(instance == null)
			instance = new PreProcessingModel();
		return instance;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isStemming() {
		return stemming;
	}
	
	public void setStemming(boolean stemming) {
		this.stemming = stemming;
	}
	
	public boolean isStopwords() {
		return stopwords;
	}
	
	public void setStopwords(boolean stopwords) {
		this.stopwords = stopwords;
	}
	
	public List<List<List<Cell>>> getBigBag() {
		return bigBag;
	}

	public WeightingType getWeightingType() {
		return weightingType;
	}

	public void setWeightingType(WeightingType weightingType) {
		this.weightingType = weightingType;
	}

	public void setBigBag(List<List<List<Cell>>> bigBag) {
		this.bigBag = bigBag;
	}

	public ArrayList<String> getBagOfWords() {
		return bagOfWords;
	}

	public void setBagOfWords(ArrayList<String> bagOfWords) {
		this.bagOfWords = bagOfWords;
	}

	public ArrayList<String> getFilesList() {
		return filesList;
	}
	
	public void setFilesList(ArrayList<String> filesList) {
		this.filesList = filesList;
	}
	
}
