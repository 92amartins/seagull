package preprocessing;
import input.Cell;

import java.util.*;

public class MatrixGenerator {
	private List<List<String>> matrix;

	
	public MatrixGenerator(){
		matrix = new ArrayList<List<String>>();
	}
	
	public List<List<String>> GenerateMatrix(List<List<List<Cell>>> bigBag){
		Vocabulary voc = new Vocabulary(bigBag);
		List<String> buffer;
		String check;
		int found;
		
		matrix.add(voc.getMatrixHeader());
		
		for(int i=0; i<bigBag.size();i++){
			buffer = new ArrayList<String>();	/*	POG	*/
			/*	para cada classe	*/
			for(int j=0;j<bigBag.get(i).size(); j++){
				/*	para cada arquivo	*/
				//System.out.println("classe " + i + " arquivo " + j);
				buffer = new ArrayList<String>();
				/*	add file name to first row	*/
				buffer.add(bigBag.get(i).get(j).get(0).getOriginal_file());
				
				for(int k=1;k<matrix.get(0).size() - 1; k++){
					found = 0;
					/*	for each item in the vocabulary	*/
					check = matrix.get(0).get(k);
					for(int m =0; m< bigBag.get(i).get(j).size(); m++){
						/*	search the file for the wanted cell	*/
						if(check.equals(bigBag.get(i).get(j).get(m).getText())){
							found = 1;
							buffer.add(String.valueOf(bigBag.get(i).get(j).get(m).getWeight()));
						}
					}
					if(found == 0) buffer.add("0");
					
				}
				
				buffer.add(bigBag.get(i).get(j).get(0).getClasse());
				matrix.add(buffer);
			}
			
			
		}
		
		
		return matrix;
	}
	
	public void printList(){
		for(int i=0; i< matrix.size();i++){
			for(int j=0; j< matrix.get(i).size(); j++){
				
				System.out.print(matrix.get(i).get(j) + " ");

			}
			
			System.out.println("");
			
		}
		
		
		
		return;
	}
	
	
	
	
	
	
}
