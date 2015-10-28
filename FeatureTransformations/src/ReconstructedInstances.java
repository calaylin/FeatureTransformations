import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReconstructedInstances {

    public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new FileReader("/Users/Aylin/Desktop/Princeton/BAA/arffs/100authors/"
    			
    			+ "originalVSLO_MERGEDFEATURES100programmers_9filesALLcls_IG_predictions.arff")) ;
    	    String line;
    	    List<String> elements = null;
    	    while ((line = br.readLine()) != null) {
    	    	elements = Arrays.asList(line.split(","));
    	}
    	    
    	int columns = elements.size()/900;
    	System.out.println(elements.size());
    	System.out.println(columns);

    	int rows = 900; //number of instances
        // create N-by-N matrix
        double[][] matrix = new double[rows][columns];
		Util.writeFile( "@relation matrix" + "\n", "/Users/Aylin/Desktop/matrixtransPredicted.arff", true);
        for(int i=0; i< columns; i++){
    		Util.writeFile( "@attribute num" + i + " numeric \n", "/Users/Aylin/Desktop/matrixtransPredicted.arff", true);
        }
		Util.writeFile( "@data"+ "\n", "/Users/Aylin/Desktop/matrixtransPredicted.arff", true);

        for(int i=0; i< rows; i++){
        	for (int j=0; j< columns;j++){
        		matrix[i][j]= Double.parseDouble(elements.get((153*i)+j));
        		System.out.print(matrix[i][j] + ",");
        		Util.writeFile( matrix[i][j] + ",", 
        				"/Users/Aylin/Desktop/matrixtransPredicted.arff", true);
        		if(j==columns-1){
        			Util.writeFile( matrix[i][j] + "\n", 
            				"/Users/Aylin/Desktop/matrixtransPredicted.arff", true);
        		}
        	}
    		System.out.println("\n");


        }
        
        
    

    }
}