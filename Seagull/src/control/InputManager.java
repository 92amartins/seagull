package control;
import input.Cell;
import input.Input;
import input.InstancesGenerator;

import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InputManager {

	private List<List<List<Cell>>> bigBag;
	private InstancesGenerator instancesGenerator = new InstancesGenerator();
	private ClassificationManager classificationManager = new ClassificationManager();
	private PreProcessingManager preProcessingManager = new PreProcessingManager();
	
	private Input input;

	public void loadFiles(String path){
		input = new Input();
		bigBag = input.readSubfolder(path);
	}


	public List<List<List<Cell>>> getBigBag() {
		return bigBag;
	}
 
	public void browseDirectory() {
		JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Browse the directory to process");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	preProcessingManager.getPreProcessingModel().setPath(chooser.getSelectedFile().toString());
        	loadFiles(preProcessingManager.getPreProcessingModel().getPath());
        	preProcessingManager.getPreProcessingModel().setFilesList(input.getFilesList());
        }
	}
	
	public void browseFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ARFF Files", "arff");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Browse the file to process");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	classificationManager.getClassificationModel().setPath(chooser.getSelectedFile().toString());
        	classificationManager.getClassificationModel().setInstances(instancesGenerator.generateFromFile(classificationManager.getClassificationModel().getPath()));
        }
	}

}
