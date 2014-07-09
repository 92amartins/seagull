package processing;

import java.util.Random;

import model.ClassificationModel;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class Classification {

	private Instances trainingSet, testingSet; 
	private Classifier[] cls;
	private Evaluation eval[];
	
	public Classification(ClassificationModel.ClassifierType[] cType) {
		
		cls = new Classifier[cType.length];
		eval = new Evaluation[cType.length];
		
		for(int i = 0; i < cType.length;i++){			
			switch(cType[i]){
			// TODO Will we use J48 or ID3 implementation of decision trees?
			case J48:
				cls[i] = new J48();
				break;

			case NAIVE_BAYES:
				// If bType == Incremental then cls = new UpdateableNaiveBayes(); else
				cls[i] = new NaiveBayes();
				break;
			
			case IBK:
				cls[i] = new IBk();
				break;
			
			case KSTAR:
				cls[i] = new KStar();
				break;
				// TODO Add other cases: Decision Rule, KNN and so on.
			}
		}
	}
	
	/**
	 * Randomizes Instances using a fixed seed.
	 * @param trainingSet
	 * @return randomized trainingSet
	 */
	private Instances randomizeSet(Instances trainingSet){
		trainingSet.randomize(new Random(1));
		return trainingSet;
	}
	
	/**
	 * Splits the dataset between training set and test set according with the percentage given.
	 * < br/>Then, build the classifier based on the training set and apply to predict the test set.
	 * @param dataset
	 * Dataset to be divided
	 * @param percentageSplit
	 * Rate of split
	 * @return
	 * An Evaluation Object with the results
	 * @throws Exception
	 */
	public Evaluation[] performTestSetEvaluation(Instances dataset, int percentageSplit) throws Exception{
		int trainSetSize = Math.round((dataset.numInstances() * percentageSplit)/100);
		int testSetSize = dataset.numInstances() - trainSetSize;
	
		dataset = randomizeSet(dataset);
		trainingSet = new Instances(dataset, 0, trainSetSize);
		testingSet = new Instances(dataset, trainSetSize, testSetSize);
		
		for(int i = 0;i < cls.length;i++){
			cls[i].buildClassifier(trainingSet);
			eval[i] = new Evaluation(trainingSet);
			eval[i].evaluateModel(cls[i], testingSet);
		}
		
		return eval;
	}
	
	/**
	 * Perform the cross validation evaluation method.
	 * @param dataset
	 * Dataset to be tested
	 * @param folds
	 * Number of folds used in the evaluation
	 * @return
	 * An Evaluation object with the results.
	 * @throws Exception
	 */
	public Evaluation[] performCrossValidation(Instances dataset, int folds) throws Exception{
		for(int i = 0;i < cls.length;i++)
			eval[i].crossValidateModel(cls[i], dataset, folds, new Random(1));
		
		return eval;		
	}
	
	/**
	 * This performs a special kind of cross validation. It is called Leave-One-Out cross validation.
	 * In this evaluation, instead of dividing the dataset into n folds, only one instance is tested.
	 * The process is repeated for each instance in the dataset.
	 * @param dataset
	 * @return
	 * @throws Exception
	 */
	public Evaluation[] performLOOCV(Instances dataset) throws Exception{
		return performCrossValidation(dataset, dataset.numInstances());
	}
	
	// GETTERS AND SETTERS
	
	public Instances getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(Instances trainingSet) {
		this.trainingSet = trainingSet;
	}

	public Instances getTestingSet() {
		return testingSet;
	}

	public void setTestingSet(Instances testingSet) {
		this.testingSet = testingSet;
	}

	public Classifier[] getClassifiers() {
		return cls;
	}

	public void setClassifiers(Classifier[] cls) {
		this.cls = cls;
	}

	public Evaluation[] getEvaluations() {
		return eval;
	}

	public void setEvaluations(Evaluation[] eval) {
		this.eval = eval;
	}
}
