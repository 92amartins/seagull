package preprocessing;

import input.Cell;

import java.util.List;


public class StemmingMaster {
	public StemmingMaster(){

	}
	/*	colocando numa classe separata até saber aonde colocar for sure	*/
	public void StemmBag(List<List<List<Cell>>> bigBag){
		//Stemmer stem = new Stemmer();
		//System.out.println("flag");
		Porter p = new Porter();
		int counter;
		String s;
		String n;
		
		
			counter=0;
			for(int i=0;i<bigBag.size();i++){
				for(int j=0;j<bigBag.get(i).size(); j++){
					for(int k=0;k<bigBag.get(i).get(j).size(); k++){
						s = bigBag.get(i).get(j).get(k).getText();
						//stem.createNew();
						//stem.setWord(s);
						//stem.stem();
						//s = stem.toString();
						n = p.stripAffixes(s);
						if(! s.equals(n))  counter++;
						//if(! s.equals(n)) System.out.println(s + " turned into " + n);
						bigBag.get(i).get(j).get(k).setText(n);
					}
				}
			}
			System.out.println("################");
			System.out.println("Stemmed " + counter + " terms");
			System.out.println("################");
		
			
			
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
