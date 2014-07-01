package input;

import java.util.List;

public class stopword_remover {

	public stopword_remover(){
		
	}
	
	
	public static List<cell> remove_punct (List<cell> l){
		String temp = new String();
		cell c;
		
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
