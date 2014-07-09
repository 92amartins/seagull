package control;
import input.Cell;
import input.Input;
import input.InstancesGenerator;
import input.StopwordRemover;
import input.Weighter;
import input.BigBagReducer;

import java.util.List;

import weka.core.Instances;

public class InputManager {
	private List<List<List<Cell>>> bigBag;
	private Instances normalBag;

	public InputManager(){

	}

	public InputManager(String path){
		Input getIt = new Input();
		bigBag = getIt.read_subfolder(path);
	}


	public List<List<List<Cell>>> getBigBag() {
		return bigBag;
	}


	public void removeStopwords(){
		StopwordRemover cleaner = new StopwordRemover();
		int again = 1;
		while(again != 0)
			again = cleaner.removeAllStopwords(bigBag);
	}

	public void applyWeighting(int method){
		//work in progress
	}

	/*##########################################################################################################*/
	/*										main method for test only											*/
	/*##########################################################################################################*/

	public static void main(String[] args) {
		Input haha = new Input();
		StopwordRemover clear_stopwd = new StopwordRemover();
		Weighter testetfidf = new Weighter();
		BigBagReducer reducer = new BigBagReducer();
		InstancesGenerator gen1 = new InstancesGenerator();
		int again = 1;

		List<List<List<Cell>>> bigBag;

		bigBag = haha.read_subfolder("C:/wekatest/teste");
		//bigBag = haha.read_subfolder("C:/wekatest/datasets");
		//bigBag = haha.read_subfolder("C:/wekatest/simpletest");

		while(again != 0) 
			again = clear_stopwd.removeAllStopwords(bigBag);

		bigBag = reducer.reduceBag(bigBag);

		//Vocabulary dic = new Vocabulary(bigBag);
		//testetfidf.weightTFIDF(bigBag);
		//gen1.generate(bigBag);
	}

}
