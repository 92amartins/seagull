package input;

public class cell {
	private float weight;
	private String text;
	private String classe;
	private String original_file;
	
	public cell(){
		this.setText("");
		this.setWeight(0);
		
	}
	
	
	public float compareTo(cell o)
	{
	     return(this.weight - o.weight);
	}




	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}





	public float getWeight() {
		return weight;
	}




	public void setWeight(float weight) {
		this.weight = weight;
	}


	public String getClasse() {
		return classe;
	}


	public void setClasse(String classe) {
		this.classe = classe;
	}


	public String getOriginal_file() {
		return original_file;
	}


	public void setOriginal_file(String original_file) {
		this.original_file = original_file;
	}
	
	
	
	
	
	
	
	
}
