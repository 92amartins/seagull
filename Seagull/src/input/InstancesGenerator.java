package input;
import java.util.List;

import preprocessing.Vocabulary;
import error.ExceptionsHandler;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class InstancesGenerator {
	
	public InstancesGenerator(){
		
	}
	
	
	
	
	
	
	public Instances generate(List<List<List<Cell>>> bigBag){
		Instances data;
		String test_text;
		FastVector atts = new FastVector(2);
		Vocabulary vocabulary = new Vocabulary(bigBag);
		int has_term;
		int index = 0;
		double[] newInst;
		FastVector      attVals;
		//atts.addElement(new Attribute(".file"));
		
		/*	create atribute list (vocabulary)	*/
		for(int i=0; i< vocabulary.getVocabulary().size(); i++){
			//atts.addElement(new Attribute(vocabulary.getVocabulary().get(i).getText(), )); //############# pode dar problema ###############
			atts.addElement(new Attribute(vocabulary.getVocabulary().get(i).getText()));
		}
		
		attVals = new FastVector();
	     for (int i = 0; i < bigBag.size(); i++)	/*	pode dar problema com pastas (classes) vazias	*/
	       attVals.addElement(bigBag.get(i).get(0).get(0).getClasse());
	     atts.addElement(new Attribute("classe", attVals));
		
		data = new Instances("training_set", atts, 0);
		
		
		
		//System.out.println("maxi: " + bigBag.size());
		
		
	     
	     
		for(int i=0; i< bigBag.size(); i++){
			
			
			
			
			//System.out.println(" - maxj: " + bigBag.get(i).size());
			
			
			/*	para cada classe i	*/
			for(int j=0; j<bigBag.get(i).size(); j++){
				//System.out.println("classe: " + i);
				//System.out.println("vocab size + 2 = " + vocabulary.getVocabulary().size() + 2);
				newInst = new double[vocabulary.getVocabulary().size()+1];
				//System.out.println("criou vetor de double");
				/*	para cada arquivo j	*/
				/*	adicionar nome do arquivo no primeiro atributo	*/
				
				/*
				if(bigBag.get(i).get(j).size() != 0){
					newInst[0] = (double)bigBag.get(i).get(j).get(0).getOriginal_file();
				}else{
					continue;
				}
				*/
				
				
				
				
				//System.out.println("adicionou a classe na primeira casa");
				/*	adicionar o nome da classe ao ultimo atributo	*/
				
				
				if(bigBag.get(i).get(j).size() != 0){
					newInst[vocabulary.getVocabulary().size()] = attVals.indexOf(bigBag.get(i).get(j).get(0).getClasse());
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
						//newInst[k] = 69;
					}else{
						/*	se n�o tiver, adicionar 0 ao data.attribute(k)	*/
						//System.out.println("nao encontrou");
						//newInst[k/*+1*/] = (double)data.attribute(k/*+1*/).addStringValue("?");
						newInst[k] = 0;
						//System.out.println("nao encontrou");
					}
					//System.out.println("proximo atributo");
				}
				data.add(new Instance(1, newInst));
				//System.out.println("Adicionou instance a Instances");
				//for(int n=0; n<vocabulary.getVocabulary().size(); n++ ) newInst[n] = 0;
			}
		}
		System.out.println(data);
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
