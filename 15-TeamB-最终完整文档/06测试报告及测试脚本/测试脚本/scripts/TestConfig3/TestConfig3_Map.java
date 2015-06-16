package com.buaa.KNN;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TestConfig3_Map extends Mapper<LongWritable, Text, Text, Text> {

	private static String testfile = new File(".\\src\\datas\\testSet.txt").getAbsolutePath();
	private static List<List<Double>> testData = new ArrayList<List<Double>>();
	
	private Text keyout = new Text();
	private Text valout = new Text();
	static{
		Tools knn = new Tools();
		knn.read(testData, testfile);
	}
	
	public void map(LongWritable ikey, Text ivalue, Context context)
			throws IOException, InterruptedException {

		StringTokenizer itr = new StringTokenizer(ivalue.toString());
		List<Double> oneData = new ArrayList<Double>();
		while(itr.hasMoreElements()){
			String oneTest = itr.nextToken().toString();
			oneData.add(Double.parseDouble(oneTest));
		}
		Tools knn = new Tools();
		for(int i = 0; i < testData.size(); i++){
			String koStr = new String();
			String voStr = new String();
			List<Double> tmp = testData.get(i);
			double tmp2 = oneData.get(oneData.size() - 1);
			int tmp3 = (int) tmp2;
			double oneDist = knn.calDist(tmp, oneData);
			for(int j = 0; j < tmp.size(); j++){
				koStr = koStr.trim() + " " + tmp.get(j).toString().trim();
			}
			koStr = koStr + ":";
			voStr = Double.toString(oneDist) + ":" + Integer.toString(tmp3);
			keyout.set(koStr.trim());
			valout.set(voStr.trim());
			context.write(keyout, valout);
		}
	}

}
