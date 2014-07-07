package processing;

import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class Classification {

	private Instances trainingSet, testingSet; 
	private Classifier cls;
	private Evaluation eval;
	private ClassifierType cType;
	
	public enum ClassifierType{ NAIVE_BAYES, J48, IBK, KSTAR, IB1;}
	
	/**
	 * Initializes classification parameters.
	 * @param ClassifierType
	 * 	The classifier used for classification.<br/>
	 * 	E.g.: ClassifierType.NAIVE_BAYES 
	 * 
	 */
	public Classification(ClassifierType cType) {
		this.cType = cType;
	}
	
	/**
	 * Returns a new instance of Classifier according to the ClassifierType requested.
	 * @return Classifier
	 * @throws Exception 
	 */
	private Classifier classifierFactory(ClassifierType cType) throws Exception{
		Classifier classifier;
		
		switch(cType){

		// TODO Will we use J48 or ID3 implementation of decision trees?
		case J48:
			classifier = new J48();
			break;

		case NAIVE_BAYES:
			// If bType == Incremental then cls = new UpdateableNaiveBayes(); else
			classifier = new NaiveBayes();
			break;
		
		case IBK:
			classifier = new IBk();
			break;
		
		case KSTAR:
			classifier = new KStar();
			break;

		default:
			throw new Exception("ClassifierType not provided!");

			// TODO Add other cases: Decision Rule, KNN and so on.
		}
		
		return classifier;
	}
	
	/**
	 * Randomizes Instances using a fixed seed.
	 * @param trainingSet
	 * @return randomized trainingSet
	 */
	public Instances randomizeSet(Instances trainingSet){
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
	public Evaluation performTestSetEvaluation(Instances dataset, int percentageSplit) throws Exception{
		int trainSetSize = Math.round((dataset.numInstances() * percentageSplit)/100);
		int testSetSize = dataset.numInstances() - trainSetSize;
	
		dataset = randomizeSet(dataset);
		trainingSet = new Instances(dataset, 0, trainSetSize);
		testingSet = new Instances(dataset, trainSetSize, testSetSize);
		
		evaluationHelper(trainingSet);
		
		cls.buildClassifier(trainingSet);
		eval.evaluateModel(cls, testingSet);
		
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
	public Evaluation performCrossValidation(Instances dataset, int folds) throws Exception{
		evaluationHelper(dataset);
		eval.crossValidateModel(cls, dataset, folds, new Random(1));
		
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
	public Evaluation performLOOCV(Instances dataset) throws Exception{
		return performCrossValidation(dataset, dataset.numInstances());
	}
	
	/**
	 * This is a helper method used for classification.
	 * Instantiate a Classifier and an Evaluation object.
	 * @param trainingSet
	 * The training set which will be the basis for the Evaluation.
	 * @throws Exception
	 */
	private void evaluationHelper(Instances trainingSet) throws Exception{
		this.cls = classifierFactory(cType);
		eval = new Evaluation(trainingSet);		
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

	public Classifier getClassifier() {
		return cls;
	}

	public void setClassifier(Classifier cls) {
		this.cls = cls;
	}

	public Evaluation getEvaluation() {
		return eval;
	}

	public void setEvaluation(Evaluation eval) {
		this.eval = eval;
	}

	public ClassifierType getClassifierType() {
		return cType;
	}

	public void setClassifierType(ClassifierType cType) {
		this.cType = cType;
	}
}
