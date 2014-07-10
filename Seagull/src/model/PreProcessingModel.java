package model;

import java.util.ArrayList;

public class PreProcessingModel {
	
	private String path;	
	private boolean tokenization;
	private boolean stemming;
	private boolean stopwords;
	private boolean normalization;
	//TODO private ??? bagOfWords;
	private ArrayList<String> filesList;
	
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
	
	public boolean isTokenization() {
		return tokenization;
	}
	
	public void setTokenization(boolean tokenization) {
		this.tokenization = tokenization;
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
	
	public boolean isNormalization() {
		return normalization;
	}
	
	public void setNormalization(boolean normalization) {
		this.normalization = normalization;
	}
	
	public ArrayList<String> getFilesList() {
		return filesList;
	}
	
	public void setFilesList(ArrayList<String> filesList) {
		this.filesList = filesList;
	}
	
}
