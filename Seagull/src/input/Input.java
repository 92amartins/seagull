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
	
	private ArrayList<String> filesList = new ArrayList<String>();

	//#########################################
	// isDirectory from class File
	//#########################################
	public static List<Cell> readFile (String path, String classe, String original_file) {
		Stopwords checker = new Stopwords();
		List<Cell> wBag = new ArrayList<Cell>();
		Scanner sc2 = null;
		Cell s;
		int counter = 0;
		try {
			sc2 = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			System.out.println("erro");
			e.printStackTrace();
		}
		while (sc2.hasNextLine()) {
			Scanner s2 = new Scanner(sc2.nextLine());
			while (s2.hasNext()) {
				s = new Cell();
				
				/*		tratar espaços		*/
				s.setText((s2.next().toLowerCase()).replaceAll("[^A-Za-z]", ""));
				s.setClasse(classe);
				s.setOriginal_file(original_file);

				if((s.getText() != "") && !checker.is(s.getText())  )
					wBag.add(s);
				else
					counter++;
			}
			s2.close();
		}

		sc2.close();

		return wBag;
	}

	public List<List<List<Cell>>> readSubfolder(String path){
		/*	reads the classes	*/
		/*	Classe<arquivos<Bag of words>>	*/
		List<List<List<Cell>>> bigBag = new ArrayList<>();

		List<Cell> bag;
		List<List<Cell>> classe;

		int content_number;
		String fileName;
		List<String> sub_folders = new ArrayList<String>();
		String new_path = new String();
		
		File f = new File(path);
		File test_f;
		List<String> content = new ArrayList<String>(Arrays.asList(f.list()));

		/*	an ArrayList of all the names of the content in the "path" are in content variable	*/
		/*	Now the program will get only the sub folder names	*/
		content_number = content.size();

		for(int i=0; i<content_number;i++){
			fileName = content.get(i);
			test_f = new File(path + "/" + fileName);
			if( test_f.isDirectory() ){
				/*	add the sub folder to the list	*/
				sub_folders.add(fileName);
			}
		}
		
		/*	at this point there is a list of the sub folders in the sub_folders variable	*/
		/*	access all folders	*/
		for(int i=0; i < sub_folders.size(); i++){
			classe = new ArrayList<List<Cell>>();
			new_path = path + '/' + sub_folders.get(i);
			f = new File(new_path);

			content = new ArrayList<String>(Arrays.asList(f.list()));
			filesList.add(sub_folders.get(i));
			content_number = content.size();
			for(int j=0; j< content_number; j++){
				fileName = content.get(j);
				filesList.add("\t\t"+fileName);
				bag = readFile(new_path + '/' + fileName, sub_folders.get(i), fileName);
				classe.add(bag);
			}
			if(content_number != 0)
				bigBag.add(classe);
		}

		return bigBag;
	}

	public ArrayList<String> getFilesList() {
		return filesList;
	}

	public void setFilesList(ArrayList<String> filesList) {
		this.filesList = filesList;
	}
}
