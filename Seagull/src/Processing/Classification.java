package Processing;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class Classification {

	private Instances trainingSet, testSet; 
	private Classifier cls;
	
	
	public enum ClassifierType {
	    NAIVE_BAYES, J48;
	}
	
	public enum BuildType {
		BATCH, INCREMENTAL;
	}
	
	public enum EvaluationType {
		CROSS_VALIDATION, DEDICATED_TEST_SET; 
	}

	/**
	 * Standard constructor. Initializes the training set and assumes the buildType as Batch. 
	 * @param trainingSet <br/>
	 * The training set from which the classifier will be built.
	 * @param classifier <br/>
	 * The classifier to be built. Possible values for that: <br/>
	 * <ul>
	 * 	<li>J48</li>
	 * 	<li>Naive Bayes</li>
	 * </ul>
	 * @throws Exception <br/>
	 * Throws exception if the String parameter is not properly supplied. 
	 */
	public Classification(Instances trainingSet, ClassifierType cType) throws Exception{
		this(trainingSet, cType, BuildType.BATCH, EvaluationType.DEDICATED_TEST_SET);
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
	public Classification(Instances trainingSet, ClassifierType cType, BuildType bType, EvaluationType eType) throws Exception{
		this.trainingSet = trainingSet;
		
		switch(cType){

		// TODO Will we use J48 or ID3 implementation of decision trees?
		case NAIVE_BAYES:
			cls = new NaiveBayes();
			break;

		case J48:
			cls = new J48();
			break;

		default:
			throw new Exception("Classifier not provided!");

			// TODO Add other cases: Decision Rule, KNN and so on.
		}

		// TODO Randomize instances before classifying!
		buildClassifier(bType);
		evaluateClassifier(eType);
	}

	private void buildClassifier(BuildType bType) throws Exception{
		if(bType == BuildType.INCREMENTAL){
			// TODO Incremental implementation is consistent with Naive Bayes original model?

		}
		else{
			cls.buildClassifier(trainingSet);
		}				
	}
	
	private void evaluateClassifier(EvaluationType eType){

	}

	private void evaluateClassifier(Instances data, EvaluationType eType){

	}
}
