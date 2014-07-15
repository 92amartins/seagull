package preprocessing;
import input.Cell;

import java.util.ArrayList;
import java.util.List;



public class Weighter {

	
	public Weighter(){
		
	}
	
	public List<List<List<Cell>>> weightTF(List<List<List<Cell>>> bigBag){
		for(int i=0; i< bigBag.size(); i++){
			for(int j=0; j<bigBag.get(i).size();j++){
				for(int k=0; k< bigBag.get(i).get(j).size(); k++){
					bigBag.get(i).get(j).get(k).setWeight(bigBag.get(i).get(j).get(k).getQuantity());
				}
			}
		}
		
		
		
		return bigBag;
	}
	
	
	
	public List<List<List<Cell>>> weightTFIDF(List<List<List<Cell>>> bigBag){
		System.out.println("Applying TF-IDF weighting.");
		return tfIdf(bigBag);
	}
	
	
	public static List<List<List<Cell>>> tfIdf(List<List<List<Cell>>> bigBag){
		float tf=0;
		float idf=0;
		float weight = 0;
		int n_word_in_doc;
		List<List<Cell>> classe;
		

		String term;
		String other_term;
		
		
		
		for(int a=0;a<bigBag.size();a++){
			classe = bigBag.get(a);
			System.out.println("Classe "+(a+1) + "/" + bigBag.size());
			for(int i=0;i<classe.size();i++){
				
				System.out.println("Arquivo "+(i+1) + "/" + classe.size());
				for(int j=0; j< (classe.get(i)).size(); j++){
					/*	calcular TF	*/
					tf=0;
					n_word_in_doc = 0;
					term = ((classe.get(i)).get(j)).getText();
					for(int k=0; k<(classe.get(i)).size();k++){
						other_term = ((classe.get(i)).get(k)).getText();
						if(term.equals(other_term)){
							n_word_in_doc = ((classe.get(i)).get(j)).getQuantity() ;
							break;
						}
					}
					
					tf  = (float) n_word_in_doc / (classe.get(i)).size();
					
					
					
					/*	fim-calcular-TF	*/
					
					/*	calcular IDF	*/
					
					idf = calculateIDF(bigBag, term);		//############################ select scope to calculate IDF
					
					/*	fim-calcular-IDF	*/
					
					weight = tf * idf;
					classe.get(i).get(j).setWeight(weight);
					//System.out.println("Peso: " + weight + " - quantity: " + classe.get(i).get(j).getQuantity());
					
				}
			}
		
		}
		
		
		System.out.println("Weights applied.");
		System.out.println("################");
		return bigBag;
	}
	
	
	
	
	private static float calculateIDF(List<List<List<Cell>>> bigBag, String term){
		int n_doc_has_word;
		int doc_count;
		
		n_doc_has_word =0;
		doc_count = 0;
		for(int i=0;i<bigBag.size();i++){
			for(int j=0; j<bigBag.get(i).size(); j++){
				doc_count++;
				for(int k=0; k<bigBag.get(i).get(j).size();k++){
					if(term.equals(bigBag.get(i).get(j).get(k).getText())){
						n_doc_has_word++;
						break;
					}
					
				}
			}
			
		}
		
		
		return  (float)Math.log10((doc_count / n_doc_has_word));
		
	}
	


	private static float calculateIDF2(List<List<Cell>> bigBag, String term){
		int n_doc_has_word;
		int doc_count;
		
		n_doc_has_word =0;
		doc_count = 0;
		
			for(int j=0; j<bigBag.size(); j++){
				doc_count++;
				for(int k=0; k<bigBag.get(j).size();k++){
					if(term.equals(bigBag.get(j).get(k).getText())){
						n_doc_has_word++;
						break;
					}
					
				}
			}
			
		
		
		
		return  (float)Math.log10((doc_count / n_doc_has_word));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
