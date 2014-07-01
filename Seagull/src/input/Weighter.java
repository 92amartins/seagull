package input;
import java.util.ArrayList;
import java.util.List;



public class Weighter {

	public Weighter(){
		
	}
	
	
	public List<List<List<Cell>>> weightTFIDF(List<List<List<Cell>>> bigBag){
		System.out.println("Applying TF-IDF weighting method for the data-set.");
		for(int i=0; i< bigBag.size(); i++){
			tfIdf(bigBag.get(i));
			System.out.println((i+1) + "a classe processada.");
		}
		System.out.println("TD-IDF weighting complete for the entire data-set.");
		
		return bigBag;
	}
	
	
	
	
	
	
	public static List<List<Cell>> tfIdf(List<List<Cell>> classe){
		float tf=0;
		float idf=0;
		float weight = 0;
		int n_word_in_doc;
		int n_doc_has_word;

		String term;
		String other_term;
		
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
						n_word_in_doc++;
						
					}
				}
				
				tf  = (float) n_word_in_doc / (classe.get(i)).size();
				
				
				
				/*	fim-calcular-TF	*/
				
				/*	calcular IDF	*/
				idf = 0;
				n_doc_has_word = 0;
				for(int m=0;m<classe.size();m++){

					for(int n=0;n<(classe.get(m)).size();n++){
						
						if(term.equals((classe.get(m)).get(n).getText())){
							n_doc_has_word++;
							//System.out.println("achou um termo no documento");
							break;
						}
						//System.out.print("nope ");
					}
					//System.out.println("Next documento");

				}
				
				
				idf = (float)Math.log10((classe.size() / n_doc_has_word));
				
				
				/*	fim-calcular-IDF	*/
				weight = tf * idf;
				classe.get(i).get(j).setWeight(weight);
				
				
			}
		}
		
		
		
		
		
		
		return classe;
	}
	
	
	
	
	
	
	
	
	
}
