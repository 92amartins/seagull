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
	
	/**
	 * Initializes classification parameters.
	 * @param trainingSet <br/>
	 * The training set from which the classifier will be built.
	 * @param incrementalBuild <br/>
	 * <i>True</i> will allow the classifier to be built <i>incrementally</i>. The default <i>False</i> will allow the classifier to be built at once. <br/>
	 * Incremental building consumes the data "step by step" instead of consume the whole data at once.
	 * This approach is recommended when the training set is too large to fit in the main memory.
	 * @throws Exception 
	 * 
	 */
	public Classification(ClassifierType cType) {
		this.cType = cType;
	}
	
	/**
	 * Returns a new instance of Classifier according to the ClassifierType requested
	 * @return
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
	
	public Instances randomizeSet(Instances trainingSet){
		trainingSet.randomize(new Random(1));
		return trainingSet;
	}
	
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
	
	public Evaluation performTestSetEvaluation(Instances trainingSet, Instances testingSet) throws Exception {
		this.trainingSet = trainingSet;
		this.testingSet = testingSet;
		
		evaluationHelper(trainingSet);
		
		this.trainingSet = randomizeSet(this.trainingSet);
		cls.buildClassifier(this.trainingSet);
		eval.evaluateModel(cls, this.testingSet);
		
		return eval;
	}
	
	public Evaluation performCrossValidation(Instances dataset, int folds) throws Exception{
		evaluationHelper(dataset);
		eval.crossValidateModel(cls, dataset, folds, new Random(1));
		
		return eval;		
	}
	
	public Evaluation performLOOCV(Instances dataset) throws Exception{
		return performCrossValidation(dataset, dataset.numInstances());
	}
	
	private void evaluationHelper(Instances trainingSet) throws Exception{
		this.cls = classifierFactory(cType);
		eval = new Evaluation(trainingSet);		
	}
}
