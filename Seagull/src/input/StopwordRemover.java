package input;

import java.util.List;

public class StopwordRemover {

	public StopwordRemover(){
		
	}
	
	
	public static List<Cell> remove_punct (List<Cell> l){
		String temp = new String();
		Cell c;
		
		for(int i=0; i< l.size(); i++){
			c = l.get(i);
			temp = c.getText();
			
			temp = temp.replaceAll("[^A-Za-z0-9]", "");
			c.setText(temp);
			if(temp.equals("")){
				l.remove(i);
			}
			
		}
		
		
		return l;
	}
	
	
	
	
	
}
