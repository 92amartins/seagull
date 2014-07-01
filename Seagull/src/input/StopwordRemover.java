package input;

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
			}
			
		}
		
		
		return l;
	}
	
	public static List<Cell> removeStopwords_list (List<Cell> l){
		String temp = new String();
		Cell c;
		Stopwords checker = new Stopwords();
		for(int i=0; i< l.size(); i++){
			c = l.get(i);
			temp = c.getText();

			
			if(checker.is(temp)){
				l.remove(i);
			}
			
		}
		
		
		return l;
	}
	
	
	
	
	public List<List<List<Cell>>> removeAllStopwords(List<List<List<Cell>>> bigBag){

		for(int i=0; i< bigBag.size(); i++){
			for(int j=0; j< bigBag.get(i).size(); j++){
			
				
				removePunct_list((bigBag.get(i)).get(j));
				removeStopwords_list((bigBag.get(i)).get(j));
				
				
				
			}
			
		}
		
		
		
		return bigBag;
	}
	
	
	
	
	
	
	
	
	
	
}
