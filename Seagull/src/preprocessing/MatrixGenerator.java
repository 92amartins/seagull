package preprocessing;
import input.Cell;

import java.util.ArrayList;
import java.util.List;

public class MatrixGenerator {
	private List<List<String>> matrix;


	
	public MatrixGenerator(){
		matrix = new ArrayList<List<String>>();
	}
	

	private void printMatrix(List<List<String>> bow){
		for(int i=0; i< bow.size(); i++){
			for(int j=0; j< bow.get(i).size(); j++){
				System.out.print(bow.get(i).get(j) + " ");
				
				
			}
			
			System.out.println("");
			
		}
		
		return;
		
	}
			
			
	
	
	
	public List<List<String>> GenerateMatrix(List<List<List<Cell>>> bigBag){
		List<String> bufferList;
		List<String> header = new ArrayList<String>();
		Vocabulary voc = new Vocabulary(bigBag);
		int found;
		
		header = voc.getMatrixHeader();
				
		matrix.add(header);
		
		
		for(int i=0; i< bigBag.size(); i++){
			/*	para cada classe	*/
			for(int j=0;j<bigBag.get(i).size(); j++){
				/*	para cada arquivo	*/
				bufferList = new ArrayList<String>();
				bufferList.add(bigBag.get(i).get(j).get(0).getOriginal_file());
				
				for(int m = 1; m< header.size() - 1; m++){
					/*	para cada termo no header	*/
					/*	found = 0	*/
					found = 0;
					for(int n=0; n< bigBag.get(i).get(j).size(); n++){
						/*	comparar com todos os termos n do arquivo	*/
						if(header.get(m).equals(bigBag.get(i).get(j).get(n).getText())){
							/*	ao encontrar, found = 1; adicionar peso na lista; break	*/
							found = 1;
							System.out.println("encontrou termo " + header.get(m) + " no arquivo ");
							
							
							bufferList.add(String.valueOf( bigBag.get(i).get(j).get(n).getWeight()  ));
							//break;
						}
						
						
					}
					/*	se apos comparacoes found == 0; adicionar "0" a lista 	*/
					if(found == 0){
						bufferList.add("0");
					}
					
				}
				
				bufferList.add(bigBag.get(i).get(j).get(0).getClasse());
				
				
				matrix.add(bufferList);
				
				
			}
			
		}

		
		
		
		//printMatrix(matrix);
		return matrix;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	

	
	
	
	
	
}
