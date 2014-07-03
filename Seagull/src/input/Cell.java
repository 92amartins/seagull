package input;

public class Cell {
	private Float weight;
	private int quantity;
	private String text;
	private String classe;
	private String original_file;
	
	public Cell(){
		this.setText("");
		this.setWeight(0);
		this.setQuantity(1);
	}
	
	
	public float compareTo(Cell o)
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


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	
	
	
}
