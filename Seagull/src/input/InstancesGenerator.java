package input;
import java.util.ArrayList;
import java.util.List;

import preprocessing.Vocabulary;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import error.ExceptionsHandler;

public class InstancesGenerator {
	private List<List<String>> matrix;
	
	
	public InstancesGenerator(){
		
	}
	
	public List<List<String>> getMatrix(){
		return matrix;
	}
	
	
	
	
	public Instances generate(List<List<List<Cell>>> bigBag){
		Instances data;
		String test_text;
		FastVector atts = new FastVector(2);
		Vocabulary vocabulary = new Vocabulary(bigBag);
		String classBuffer = new String();
		List<String> strBuffer = new ArrayList<String>();
		matrix = new ArrayList<List<String>>();
		int has_term;
		int index = 0;
		double[] newInst;
		FastVector      attVals;
		//atts.addElement(new Attribute(".file"));
		System.out.println("generating instances");
		strBuffer.add("File");
		/*	create atribute list (vocabulary)	*/
		for(int i=0; i< vocabulary.getVocabulary().size(); i++){
			//atts.addElement(new Attribute(vocabulary.getVocabulary().get(i).getText(), )); //############# pode dar problema ###############
			atts.addElement(new Attribute(vocabulary.getVocabulary().get(i).getText()));
			strBuffer.add(vocabulary.getVocabulary().get(i).getText());
		}
		
		attVals = new FastVector();
	     for (int i = 0; i < bigBag.size(); i++)	/*	pode dar problema com pastas (classes) vazias	*/
	       attVals.addElement(bigBag.get(i).get(0).get(0).getClasse());
	     atts.addElement(new Attribute("classe", attVals));
		strBuffer.add("Class");
		data = new Instances("training_set", atts, 0);
		matrix.add(strBuffer);
		
		
		//System.out.println("maxi: " + bigBag.size());
		
		
	     
	     
		for(int i=0; i< bigBag.size(); i++){
			
			
			
			
			//System.out.println(" - maxj: " + bigBag.get(i).size());
			
			
			/*	para cada classe i	*/
			for(int j=0; j<bigBag.get(i).size(); j++){
				//System.out.println("vocab size + 2 = " + vocabulary.getVocabulary().size() + 2);
				newInst = new double[vocabulary.getVocabulary().size()+1];
				strBuffer = new ArrayList<String>();
				//System.out.println("criou vetor de double");
				/*	para cada arquivo j	*/
				/*	adicionar nome do arquivo no primeiro atributo	*/
				
				
				if(bigBag.get(i).get(j).size() != 0){
					strBuffer.add(bigBag.get(i).get(j).get(0).getOriginal_file());
				}else{
					continue;
				}
				
				
				
				
				
				
				/*	adicionar o nome da classe ao ultimo atributo	*/
				
				
				if(bigBag.get(i).get(j).size() != 0){
					newInst[vocabulary.getVocabulary().size()] = attVals.indexOf(bigBag.get(i).get(j).get(0).getClasse());
					classBuffer = bigBag.get(i).get(j).get(0).getClasse();
					//newInst[vocabulary.getVocabulary().size()] = (double)data.attribute(vocabulary.getVocabulary().size()).addStringValue(bigBag.get(i).get(j).get(0).getClasse());
				}else{
					continue;
				}
				
				
				
				
				
				//System.out.println("adicionou o nome da classe no ultimo slot");
				//System.out.println("para cada atributo");
				for(int k=0; k< vocabulary.getVocabulary().size(); k++){
					/*	para cada atributo	*/
					test_text = vocabulary.getVocabulary().get(k).getText();
					//System.out.println("pegou atributo");
					//System.out.println("procurando termo " + test_text);
					/*	procurar se o documento j da classe k possui o atributo	*/
					has_term=0;
					
					for(int m=0; m< bigBag.get(i).get(j).size(); m++){
						if(test_text.equals(bigBag.get(i).get(j).get(m).getText())){
							has_term = 1;
							index = m;
							break;
						}
					}
					/*	se tiver, adicionar o peso ao data.attribute(k)	*/
					
					if(has_term == 1){
						//System.out.println("encontrou");
						newInst[k/*+1*/] = bigBag.get(i).get(j).get(index).getWeight();
						strBuffer.add(String.valueOf(bigBag.get(i).get(j).get(index).getWeight()));
						//newInst[k] = 69;
					}else{
						/*	se n?o tiver, adicionar 0 ao data.attribute(k)	*/
						//System.out.println("nao encontrou");
						//newInst[k/*+1*/] = (double)data.attribute(k/*+1*/).addStringValue("?");
						newInst[k] = 0;
						strBuffer.add("0");
						//System.out.println("nao encontrou");
					}
					//System.out.println("proximo atributo");
				}
				data.add(new Instance(1, newInst));
				
				strBuffer.add(classBuffer);
				matrix.add(strBuffer);
				
				//System.out.println("Adicionou instance a Instances");
				//for(int n=0; n<vocabulary.getVocabulary().size(); n++ ) newInst[n] = 0;
			}
		}
		//System.out.println(data);
		return data;
	}
	
	public Instances generateFromFile(String path) {
		try {
			return DataSource.read(path);
		} catch (Exception e) {
			ExceptionsHandler.showUnsupportedFileDialog();
			return null;
		}
	}
	
	
	
	
	
}
