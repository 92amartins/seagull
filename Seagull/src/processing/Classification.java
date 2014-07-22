package processing;

import java.util.ArrayList;
import java.util.Random;

import model.ClassificationModel;
import model.ClassificationModel.ClassifierType;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Normalize;

public class Classification {

	private Instances trainingSet, testingSet; 
	private Classifier[] cls;
	private Evaluation eval[];

	public Classification(ArrayList<ClassifierType> cType) {

		cls = new Classifier[cType.size()];
		eval = new Evaluation[cType.size()];

		for(int i = 0; i < cType.size();i++){			
			switch(cType.get(i)){
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
			case COSINE:
				cls[i] = useCosine();
				// TODO Add other cases: Decision Rule, KNN and so on.
			}
		}
	}

	private IBk useCosine() {
		IBk ibk = new IBk();
		Instances data = ClassificationModel.getInstance().getInstances();
		Normalize normalizer = new Normalize(); 
		
		try {
			normalizer.setInputFormat(data);

			// Euclidean Distance working over normalized instances = Cosine Similarity according to Foundations of Statistical Natural Processing Language p.301
			// As long as attribute normalization is disabled.
			Instances normalizedInstances; 
			normalizedInstances = Filter.useFilter(data, normalizer); 
			ClassificationModel.getInstance().setInstances(normalizedInstances);
			DistanceFunction df = new EuclideanDistance(); 
			((EuclideanDistance) df).setDontNormalize(true); 		                        
			ibk.getNearestNeighbourSearchAlgorithm().setDistanceFunction(df); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ibk;
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
		Evaluation e;
		for(int i = 0;i < cls.length;i++) {
			e = new Evaluation(dataset);
			e.crossValidateModel(cls[i], dataset, folds, new Random(1));
			eval[i] = e;
		}
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
