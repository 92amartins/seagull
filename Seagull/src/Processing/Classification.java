package Processing;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class Classification {

	private Instances trainingSet;
	private boolean incrementalBuild; 
	private Classifier cls;

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
	public Classification(Instances trainingSet, String classifier) throws Exception{
		this(trainingSet, classifier, false);
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
	public Classification(Instances trainingSet, String classifier, boolean incrementalBuild) throws Exception{
		this.trainingSet = trainingSet;
		this.incrementalBuild = incrementalBuild;

		classifier = treatStringParameter(classifier);

		switch(classifier){

		// TODO Will we use J48 or ID3 implementation of decision trees?
		case "j48":
			cls = new J48();
			break;

		case "naivebayes":
			cls = new NaiveBayes();
			break;

		default:
			throw new Exception("Classifier not provided!");

			// TODO Add other cases: Decision Rule, KNN and so on.
		}

		// TODO Randomize instances before classifying!
		buildClassifier();
		evaluateClassifier();
	}

	// TODO Eligible to be moved to Utils package.
	private String treatStringParameter(String untreatedString){
		String treatedString;

		untreatedString = untreatedString.trim();
		untreatedString = untreatedString.toLowerCase();

		treatedString = untreatedString;

		return treatedString;
	}

	private void buildClassifier() throws Exception{
		if(incrementalBuild){
			// TODO Incremental implementation is consistent with Naive Bayes original model?

		}
		else{
			cls.buildClassifier(trainingSet);
		}				
	}

	private void evaluateClassifier(Instances testSet){

	}

	private void evaluateClassifier(){

	}
}
