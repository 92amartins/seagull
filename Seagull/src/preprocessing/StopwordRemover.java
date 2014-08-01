package preprocessing;

import input.Cell;

import java.util.List;

import weka.core.Stopwords;



public class StopwordRemover {

	public StopwordRemover(){
		
	}
	
	
	public static List<Cell> removePunct_list (List<Cell> l){
		String temp = new String();
		Cell c;
		
		for(int i=0; i< l.size(); i++){
			c = l.get(i);
			temp = c.getText();
			
			temp = temp.replaceAll("[^A-Za-z0-9]", "");
			c.setText(temp);
			if(temp.equals("")){
				l.remove(i);
				i--;
			}
		}
		
		
		return l;
	}
	
	public static List<Cell> removeStopwords_list (List<Cell> l){
		String temp = new String();
		Cell c;
		//int prev_size = l.size();
		//int new_size;
		Stopwords checker = new Stopwords();
		for(int i=0; i< l.size(); i++){
			c = l.get(i);
			temp = c.getText();

			
			if(checker.is(temp)){
				l.remove(i);
				i--;
				////tln("Removed stopword: " + temp);
				
			}else{
				////tln("## Didnt Remove stopword: " + temp);
			}
			
		}
		//new_size = l.size();
		
		
		return l;
	}
	
	
	
	
	public int removeAllStopwords(List<List<List<Cell>>> bigBag){
		int prev_size = 0;
		int new_size = 0;
		//tln("Removing stop words.");
		for(int i=0; i< bigBag.size(); i++){
			for(int j=0; j< bigBag.get(i).size(); j++){
				////tln("SWR - Classe: "+(i+1) + " - Arquivo: "+ (j+1) + "/" + bigBag.get(i).size());
				prev_size += (bigBag.get(i)).get(j).size();
				removePunct_list((bigBag.get(i)).get(j));
				removeStopwords_list((bigBag.get(i)).get(j));
				new_size += (bigBag.get(i)).get(j).size();
				
				
			}
			
		}
		
		//tln("Removed " + (prev_size - new_size) + " terms.");
		//tln("################");
		return prev_size - new_size;
	}
	
	
	
	
	
	
	
	
	
	
}
