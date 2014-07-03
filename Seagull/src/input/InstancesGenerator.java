package input;
import java.io.*;

import weka.core.*;

import java.util.*;



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
		
		
		
		
		atts.addElement(new Attribute(".file", (FastVector) null));
		
		/*	create atribute list (vocabulary)	*/
		for(int i=0; i< vocabulary.getVocabulary().size(); i++){
			
			
			
			//atts.addElement(new Attribute(vocabulary.getVocabulary().get(i).getText(), )); //############# pode dar problema ###############
			atts.addElement(new Attribute(vocabulary.getVocabulary().get(i).getText(), (FastVector) null));
			
			
		}
		
		atts.addElement(new Attribute(".class", (FastVector) null));
		
		data = new Instances("training_set", atts, 0);
		
		for(int i=0; i< bigBag.size(); i++){
			/*	para cada classe i	*/
			for(int j=0; j<bigBag.get(i).size(); j++){
				double[] newInst = new double[vocabulary.getVocabulary().size() + 2];
				/*	para cada arquivo j	*/
				/*	adicionar nome do arquivo no primeiro atributo	*/
				newInst[0] = (double)data.attribute(0).addStringValue(bigBag.get(i).get(j).get(0).getOriginal_file());
				/*	adicionar o nome da classe ao ultimo atributo	*/
				newInst[vocabulary.getVocabulary().size() + 1] = (double)data.attribute(vocabulary.getVocabulary().size() + 1).addStringValue(bigBag.get(i).get(j).get(0).getClasse());
				
				for(int k=0; k< vocabulary.getVocabulary().size(); k++){
					/*	para cada atributo	*/
					test_text = vocabulary.getVocabulary().get(k).getText();
					//System.out.println("procurando termo " + test_text);
					/*	procurar se o documento j da classe k possui o atributo	*/
					has_term=0;
					for(int m=0; m< bigBag.get(i).get(j).size(); m++){
						
						if(test_text.equals(   bigBag.get(i).get(j).get(m).getText()                    )){
							has_term = 1;
							index = m;
							break;
						}
						
					}
					/*	se tiver, adicionar o peso ao data.attribute(k)	*/
					
					if(has_term == 1){
						//System.out.println("encontrou");
						newInst[k+1] = (double)data.attribute(k+1).addStringValue(String.valueOf(bigBag.get(i).get(j).get(index).getWeight()));
						
						
					}else{
						/*	se não tiver, adicionar 0 ao data.attribute(k)	*/
						
						newInst[k+1] = (double)data.attribute(k+1).addStringValue("0");
						//System.out.println("nao encontrou");
					}
					
					

					
				}
				
				
				
				data.add(new Instance(1, newInst));
				//for(int n=0; n<vocabulary.getVocabulary().size(); n++ ) newInst[n] = 0;
				
				
				
				
			}
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println(data);
		
		
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
