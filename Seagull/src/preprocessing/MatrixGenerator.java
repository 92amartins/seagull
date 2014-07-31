package preprocessing;
import input.Cell;

import java.util.ArrayList;
import java.util.List;

/*				MANUAL
 * 
 * Chamar o método GenerateMatrix(bigBag); para gerar a matriz
 * Esse método retorna um Array<Array<String>>, mas nao eh o melhor jeito para construir um JTable
 * 
 * Apos a construcao da matriz pelo metodo acima, os metodos genColumnNames e genDataMatrix sao usados
 * para pegar os parametros necessarios para o tableModel na hora de construir a tabela (pelo menos acho
 * que eh assim que funciona)
 * 
 * genColumnNames gera um vetor com os nomes das colunas
 * 
 * genDataMatrix gera uma matrix bidimensional de strings com o nome do arquivo, peso dos termos e classe
 * 
 * 
 * 
 */





public class MatrixGenerator {
	private List<List<String>> matrix;
	

	
	public MatrixGenerator(){
		
	}
	

	
	
	/*************************************/
	/**	keep this until fully tested	**/
	/*************************************/
	/*
	
	private void printMatrix(List<List<String>> bow){
		for(int i=0; i< bow.size(); i++){
			for(int j=0; j< bow.get(i).size(); j++){
				System.out.print(bow.get(i).get(j) + " ");
				
			}
			
			System.out.println("");
			
		}
		
		return;
		
	}
	*/	
	
	
	public static String[] genColumnNamesStatic(List<List<String>> matrix2){
		int size = matrix2.get(0).size();
		String[] columnNames = new String[size];
		
		for(int i=0; i< size; i++){
			columnNames[i] = matrix2.get(0).get(i);
		}
		return columnNames;
	}	
	
	
	public static String[][] genDataMatrixStatic(List<List<String>> matrix2){
		System.out.println("################");
		System.out.println("generating matrix");
		
		String[][] data;
		int maxi = matrix2.size() - 1;
		int maxj =  matrix2.get(0).size();
		data = new String[maxi][maxj];
		
		for(int i=0; i< maxi; i++){
			for(int j=0; j< maxj; j++){
				data[i][j] = matrix2.get(i+1).get(j);
			}
		}
		return data;
	}	
	
	
	
	
	
	
	
	public String[] genColumnNames(){
		int size = matrix.get(0).size();
		String[] columnNames = new String[size];
		
		for(int i=0; i< size; i++){
			columnNames[i] = matrix.get(0).get(i);
		}
		return columnNames;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
	public String[][] genDataMatrix(){
		System.out.println("################");
		System.out.println("generating matrix");
		
		String[][] data;
		int maxi = matrix.size() - 1;
		int maxj =  matrix.get(0).size();
		data = new String[maxi][maxj];
		
		for(int i=0; i< maxi; i++){
			for(int j=0; j< maxj; j++){
				data[i][j] = matrix.get(i+1).get(j);
			}
		}
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<List<String>> generateMatrix(List<List<List<Cell>>> bigBag){
		/*	generates a new matrix every time this method is called	*/
		matrix = new ArrayList<List<String>>();
		System.out.println("generating matrix base");
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
				if(bigBag.get(i).get(j).size() == 0) continue;
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
							//System.out.println("encontrou termo " + header.get(m) + " no arquivo ");
							
							
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
