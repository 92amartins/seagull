package preprocessing;

import input.Cell;

import java.util.List;
public class BigBagReducer {
	
	public BigBagReducer(){
		
	}
	
	public List<List<List<Cell>>> reduceBag(List<List<List<Cell>>> bigBag){
		int w_counter;
		int remove_counter = 0;
		String term;
		String other_term;
		int flag;
		
			remove_counter = 0;
			//##########################
			//tln("Removing repeated terms.");
			
			for(int i=0;i<bigBag.size();i++){		/*	para cada classe	*/
				for(int j=0; j<bigBag.get(i).size(); j++){		/*	para cada arquivo	*/
					
					
					for(int m =0; m<bigBag.get(i).get(j).size(); m++){		/*	para cada termo	*/
						w_counter = 0;
						flag =0;
						term = bigBag.get(i).get(j).get(m).getText();
						////tln("Procurando termo: " + term + " de indice " + m);
						for(int n=0; n<bigBag.get(i).get(j).size(); n++){
	
							other_term = bigBag.get(i).get(j).get(n).getText();
							if(term.equals(other_term)){
								////tln("Encontrou equivalencia no indice " + n);
								
								if(n<m){
									//tln("Redundancia #######################################################################");
									
								}else{
								
								
									w_counter++;
									
									if(w_counter > 1){
										(bigBag.get(i)).get(j).remove(n);
										////tln("Removeu c�lula de indice " + n);
										n--;
										remove_counter++;
	
									}
								}
							}
							
						}
						
						if(flag == 0) bigBag.get(i).get(j).get(m).setQuantity((w_counter - 1) + bigBag.get(i).get(j).get(m).getQuantity() );
						////tln("nova quantidade de termos "+ bigBag.get(i).get(j).get(m).getQuantity() );
						
						
					}
					
					
					
				}
			}
			
			
			
			//tln("Remocao de " + remove_counter + " termos repetidos.");
			//tln("################");
			
			
			
			
		return bigBag;
	}
	
	
	
	
	
	
}
