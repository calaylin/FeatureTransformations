

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Range;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.Remove;

public class RegressionForFeatureAnalysis {
	public static void main(String[] args) throws Exception 
	{
	double accuracy=0;
	int endRelax = 1;
	int numberFiles=9;
	int numFeatures=0; //0 is the default logM+1
	int seedNumber;
	double total =0;
	double average =0;

	String fileName  ="/Users/Aylin/Desktop/Princeton/BAA/"
			+ "featureAnalysis/originalVSLO_MERGEDFEATURES100programmers_9filesALLcls_IG.txt";
	
	
	String arffFile ="/Users/Aylin/Desktop/Princeton/BAA/arffs/100authors/"
			+ "originalVSLO_MERGEDFEATURES100programmers_9filesALLcls_IG.arff";
	
	String arffFileToWrite ="/Users/Aylin/Desktop/Princeton/BAA/arffs/100authors/"
			+ "originalVSLO_MERGEDFEATURES100programmers_9filesALLcls_IG_predictions.arff";
	
	String arffFileToWriteORG ="/Users/Aylin/Desktop/Princeton/BAA/arffs/100authors/"
			+ "originalVSLO_MERGEDFEATURES100programmers_9filesALLcls_IG_originalsofpredictions.arff";

	for(int relaxPar = 1; relaxPar<=endRelax; relaxPar++){
			  total=0;				  
			  average=0;

			  for(seedNumber=1; seedNumber<2; seedNumber++){
				  int foldNumber=numberFiles;


				 	Instances       inst;
				    Instances       instNew;
				    Remove          remove;

	for (int i=256; i <1022; i++){

    inst   = new Instances(new BufferedReader(new FileReader(arffFile)));
    System.out.println(inst.numInstances());

    remove = new Remove();
    remove.setAttributeIndices("1-255, "+i);
    remove.setInvertSelection(true);
    remove.setInputFormat(inst);
    instNew = Filter.useFilter(inst, remove);
    instNew.setClassIndex(255);
 //   System.out.print("When the class is: "+instNew.classAttribute());
	
//    LinearRegression model = new LinearRegression();
  //  SMOreg model = new SMOreg();
//    System.out.println(" The model is"+ model);
    
    
	 String[] options = weka.core.Utils.splitOptions("-I 300 -K "+numFeatures+" -S "+seedNumber);
		RandomForest model = new RandomForest();
		model.setOptions(options);
	    model.buildClassifier(instNew); 


	
    
    
	Evaluation eval=null;
	eval = new Evaluation(instNew);
	eval.crossValidateModel(model, instNew,foldNumber , new Random(seedNumber));
	eval.crossValidateModel(model, instNew, 10,new Random(seedNumber));

	for(int i1=0; i1< instNew.numInstances(); i1++){
		
		 
/*		   System.out.print(", actual: " + testData.classAttribute().value((int) testData.instance(i).classValue()));
		   System.out.println(", predicted: " + testData.classAttribute().value((int) pred)+"\n");
	*/	
			if (i == instNew.numInstances()-1){
				Util.writeFile(  eval.predictions().get(i1).predicted()+ "\n", arffFileToWrite, true);
				Util.writeFile(  eval.predictions().get(i1).actual()+"\n", arffFileToWriteORG, true);		
			
		}
			else{ 
			Util.writeFile(eval.predictions().get(i1).predicted()+ ",", arffFileToWrite, true);
			Util.writeFile(eval.predictions().get(i1).actual()
					+ ",", arffFileToWriteORG, true);
			System.out.print(
					eval.predictions().get(i1).predicted()+ ","
					 +inst.get(i1).stringValue(inst.numAttributes()-1));
			System.out.println(eval.predictions().get(i1).actual()
					+ "," + inst.get(i1).stringValue(inst.numAttributes()-1));
			
			

		
	}

     }}	}
}}

}