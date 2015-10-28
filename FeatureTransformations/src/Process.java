import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Process {
    public static void main(String[] args) throws IOException {

 String filename= "/Users/Aylin/Desktop/"
			
    			+ "matrixtransPredictedtoadd.arff";
	BufferedReader br = new BufferedReader(new FileReader(filename)) ;
	
    	    String line;
    	    List<String> elements = null;
			int cls=0;
			int counter=0;
    	    while ((line = br.readLine()) != null) {
    	    	if (!(line.isEmpty())){
    	    		if(!(line.contains("@"))){
    	    	Util.writeFile(line +","+ cls+"\n" , "/Users/Aylin/Desktop/"
			
    			+ "matrixtransPredictedtoadded.arff", true);
    	    	counter++;
    	    	cls=counter/9;
    	    	
    	    		}}
    	    	else{
        	    	Util.writeFile(line + "\n", filename +"added", true);}

    	    	}
    	}
    	    
    
}
