package input;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import weka.core.Stopwords;
/*	ctrl+shift+o	*/


public class Input {

	public Input(){

	}

	public static void print_list(List<Cell> l){
		String pick = new String();
		int i = 0;
		System.out.println("Imprimindo lista:");
		while(l.size() > i){
			pick = (l.get(i)).getText();
			i++;
			System.out.println(pick);
		}

		return;
	}

	//#########################################
	// isDirectory from class File
	//#########################################
	public static List<Cell> read_file (String path, String classe, String original_file) {
		Stopwords checker = new Stopwords();
		List<Cell> wBag = new ArrayList<Cell>();
		Scanner sc2 = null;
		Cell s;
		int counter =0;
		try {
			sc2 = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			System.out.println("erro");
			e.printStackTrace();  
		}
		while (sc2.hasNextLine()) {
			Scanner s2 = new Scanner(sc2.nextLine());
			//boolean b;
			while (s2.hasNext()) {
				s = new Cell();
				
				/*		tratar espaços		*/
				s.setText((s2.next().toLowerCase()).replaceAll("[^A-Za-z0-9]", ""));
				s.setClasse(classe);
				s.setOriginal_file(original_file);

				if((s.getText() != "") && !checker.is(s.getText())){
					wBag.add(s);

				}else{
					counter++;
				}

			}
			s2.close();
		}

		sc2.close();


		//System.out.println("counter: " + counter);

		return wBag;

	}

	/*	
	public static List<Cell> read_file (String path) {

		List<Cell> wBag = new ArrayList<Cell>();
		Scanner sc2 = null;
		Cell s;			//###################################################
	    try {
	        sc2 = new Scanner(new File(path));
	    } catch (FileNotFoundException e) {
	    	System.out.println("erro");
	        e.printStackTrace();  
	    }
	    while (sc2.hasNextLine()) {
	            Scanner s2 = new Scanner(sc2.nextLine());
	        //boolean b;
	        while (s2.hasNext()) {
	        	s = new Cell();
	            s.setText(s2.next().replaceAll("[^A-Za-z0-9]", ""));

	            if(s.getText() != "") wBag.add(s);

	        }
	        s2.close();
	    }

	    sc2.close();




		return wBag;

	}
	 */

	public List<List<List<Cell>>> read_subfolder(String path){
		/*	reads the classes	*/
		/*	Classe<arquivos<Bag of words>>	*/
		List<List<List<Cell>>> bigBag = new ArrayList<>();

		List<Cell> bag;
		List<List<Cell>> classe;

		int content_number;
		String name_test;
		String txt = new String("txt");
		List<String> sub_folders = new ArrayList<String>();
		List<String> file_names = new ArrayList<String>();
		String new_path = new String();
		System.out.println("Lendo sub pastas:");
		
		File f = new File(path);
		File test_f;
		List<String> content = new ArrayList<String>(Arrays.asList(f.list()));

		/*	an ArrayList of all the names of the content in the "path" are in content variable	*/

		/*	Now the program will get only the sub folder names	*/

		content_number = content.size();

		for(int i=0; i<content_number;i++){

			name_test = content.get(i);
			test_f = new File(path + "/" + name_test);
			if( test_f.isDirectory() ){
				/*	add the sub folder to the list	*/
				sub_folders.add(name_test);
				continue;
			}

		}
		
		/*	at this point there is a list of the sub folders in the sub_folders variable	*/

		/*	access all folders	*/

		System.out.println("Obtendo arquivos nas sub pastas...");

		for(int i=0; i < sub_folders.size(); i++){
			//System.out.println("#####################");
			//System.out.println("Criando classe " + sub_folders.get(i));
			classe = new ArrayList<List<Cell>>();
			new_path = path + '/' + sub_folders.get(i);
			f = new File(new_path);
			//System.out.println("Populando classe:");

			content = new ArrayList<String>(Arrays.asList(f.list()));
			content_number = content.size();
			for(int j=0; j< content_number; j++){
				name_test = content.get(j);
				/* if((name_test.substring(name_test.lastIndexOf('.') + 1)).equals(txt)){	 */
				//System.out.println("Criando bag of words para :" + new_path + '/' + name_test);
				bag = read_file(new_path + '/' + name_test, sub_folders.get(i), name_test);
				//System.out.println("Adicionando   " + name_test + "   � classe.");
				classe.add(bag);
				/* } */
			}
			if(content_number != 0){
				//System.out.println("Adicionando classe � big bag of words.");
				bigBag.add(classe);
			}else{
				//System.out.println("Classe vazia, desconsiderar.");
			}
		}

		System.out.println("Isso eh tudo pessoal.");
		System.out.println("Big Bag of Words (BBW) mounted");
		System.out.println("################");
		return bigBag;
	}

}
