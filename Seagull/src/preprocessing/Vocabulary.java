package preprocessing;
import input.Cell;

import java.util.*;
import java.io.*;

public class Vocabulary {
	private List<Cell> vocabulary;
	
	public Vocabulary(List<List<List<Cell>>> bigBag){
		System.out.println("Criando vocabulario.");
		vocabulary = new ArrayList<Cell>();
		
		String test;
		for(int i=0; i< bigBag.size();i++){
			/*	para cada Classe	*/
			for(int j =0; j<bigBag.get(i).size(); j++){
				/*	para cada arquivo	*/
				for(int k=0; k<bigBag.get(i).get(j).size(); k++ ){
					/*	para cada celula	*/
					test = bigBag.get(i).get(j).get(k).getText();
					
					if(!(belongs(test))){
						vocabulary.add(bigBag.get(i).get(j).get(k));
					}
					
				}
			}
			
		}
		
		
		System.out.println("Criou vocabulario com " + vocabulary.size()+ " termos.");
		System.out.println("################");
		
	}
	
	
	public List<Cell> getVocabulary(){
		return vocabulary;
	}
	

	public boolean belongs(String term){
		/*	Checks if a string belongs to the vocabulary	*/
		for(int i=0; i< vocabulary.size(); i++){
			if(term.equals(vocabulary.get(i).getText())) return true;
		}
		return false;
	}
	
	/*	método apenas para gerar matriz para exibição	*/
	public List<String> getMatrixHeader(){
		List<String> list = new ArrayList<String>();
		list.add("File");
		for(int i=0;i<vocabulary.size();i++){
			list.add(vocabulary.get(i).getText());
		}
		
		list.add("Class");
		
		return list;
	}
	
	
	
	
	
	
	
	
	
}
