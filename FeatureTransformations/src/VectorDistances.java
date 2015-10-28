


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class VectorDistances {
	public static void main(String[] args) throws Exception{
		String arffFile1 ="/Users/Aylin/Desktop/"
            + "matrixtransOriginal.arff";

		String arffFile2 ="/Users/Aylin/Desktop/"
            + "matrixtransPredicted.arff";

		Instances instances1_notnormalized = new Instances(new FileReader(arffFile1));
		Instances instances2_notnormalized = new Instances(new FileReader(arffFile2));

		EuclideanDistance distance = new EuclideanDistance();
		System.out.println(instances1_notnormalized.get(1));
		
//		distance.setInstances(instances1_notnormalized);
		Normalize filter = new Normalize();
		filter.setInputFormat(instances1_notnormalized);
		Instances instances1 = Filter.useFilter(instances1_notnormalized, filter);
		System.out.println(instances1.get(1));

		Normalize filter1 = new Normalize();
		filter1.setInputFormat(instances2_notnormalized);
		Instances instances2 = Filter.useFilter(instances2_notnormalized, filter1);
	//	distance.setInstances(instances2);
	
		distance.setInstances(instances1);
		distance.setInstances(instances2);
		Instance first;
		Instance second;
		
		String arffFile ="/Users/Aylin/Desktop/Princeton/BAA/arffs/100authors/"
				+ "originalVSLO_MERGEDFEATURES100programmers_9filesALLcls_IG.arff";
		Instances instances_original = new Instances(new FileReader(arffFile));

		double total =0;
		for(int i=0; i< instances1.numInstances(); i++){

			System.out.println(instances_original.get(i).stringValue(0).substring(0, 
					instances_original.get(i).stringValue(0).length()-23));
			String filename = instances_original.get(i).stringValue(0).substring(0, 
					instances_original.get(i).stringValue(0).length()-23);
			

    	    	List<String> elements = Arrays.asList(filename.split("_"));
    	
			
				System.out.println("/Users/Aylin/Desktop/Princeton/BAA/datasets/c++/optimizations/Original_150authors/"
					+ 		elements.get(2)
					+"/"+filename + ".cpp");
		//	instances_original.get(1).stringValue(0).substring(25, instances_original.get(i).stringValue(0).length()-23);
			
			
			 FileInputStream in = new FileInputStream("/Users/Aylin/Desktop/Princeton/BAA/datasets/c++/optimizations/Original_150authors/"
						+ 		elements.get(2)
						+"/"+filename + ".cpp");
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));

			  
			  int lines=0;
				int loc = 0;

			  
			String line;
			while ((line = br.readLine()) != null) {
		//		System.out.println(line);
				lines = lines +1;
				if (!(line.trim().isEmpty())) {
			    loc =loc+1;
		
			}}
			System.out.println(loc);
			
			
			first = instances1.instance(i);
			second = instances2.instance(i);
			
			double[] array1=first.toDoubleArray();
			double[] array2=second.toDoubleArray();

			//this is Euclidean distance, check the others (cosine) as well
		//	double d = distance.distance(first, second);
			double d = Similarity.calculateSimilarity(array1, array2, 0);
			System.out.println("For instance:"+i +
					" Cos distance: "+Double.toString(d) );
			
/*	Util.writeFile("For instance:"+i +
			"Cos distance: "+Double.toString(d)  + " LOC:" + loc+" all lines:"+lines+"\n",
			"/Users/Aylin/Desktop/reconstructedCosDistancesFeaturesLOC4.txt",true);
*/			total = total + d;
		}
	System.out.println(total/900.000);
	}
}
