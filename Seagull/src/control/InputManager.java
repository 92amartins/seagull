package control;
import input.Cell;
import input.Input;
import input.InstancesGenerator;

import java.util.List;

import javax.swing.JFileChooser;

import preprocessing.BigBagReducer;
import preprocessing.MatrixGenerator;
import preprocessing.StemmingMaster;
import preprocessing.StopwordRemover;
import preprocessing.Vocabulary;
import preprocessing.Weighter;
import weka.core.Instances;

public class InputManager {
	private List<List<List<Cell>>> bigBag;
	
	
	private InstancesGenerator instancesGenerator = new InstancesGenerator();
	private ClassificationManager classificationManager = new ClassificationManager();

	
	public InputManager(){
		
	}

	public InputManager(String path){
		Input getIt = new Input();
		bigBag = getIt.read_subfolder(path);
	}


	public List<List<List<Cell>>> getBigBag() {
		return bigBag;
	}
 




	
	
	
	public void browseFile() {
		JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Browse the file to process");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	classificationManager.getClassificationModel().setPath(chooser.getSelectedFile().toString());
        	classificationManager.getClassificationModel().setInstances(instancesGenerator.generateFromFile(classificationManager.getClassificationModel().getPath()));
        }
	}

}
