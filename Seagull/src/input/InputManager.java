package input;
import java.util.List;

public class InputManager {

	public static void main(String[] args) {
		Input haha = new Input();
		List<List<List<Cell>>> bigBag;
		
		
		
		bigBag = haha.read_subfolder("C:/wekatest");
		Weighter testetfidf = new Weighter();
		testetfidf.weightTFIDF(bigBag);
		
		
		
		
	}

}
