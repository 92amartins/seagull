package input;

import java.util.List;


public class StemmingMaster {
	public StemmingMaster(){

	}
	/*	colocando numa classe separata até saber aonde colocar for sure	*/
	public void StemmBag(List<List<List<Cell>>> bigBag){
		Stemmer stem;
		String s;
		for(int i=0;i<bigBag.size();i++){
			for(int j=0;j<bigBag.get(i).size(); j++){
				for(int k=0;k<bigBag.get(i).get(j).size(); k++){
					s = bigBag.get(i).get(j).get(k).getText();
					stem = new Stemmer();
					stem.setWord(s);
					stem.stem();
					s = stem.toString();
					bigBag.get(i).get(j).get(k).setText(s);
				}
			}
		}
		
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
