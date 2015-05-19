package com.buaa.KNN;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class TestKNN {

	private static final int k = 4;
	
	//读取数据文件
	public void read(List<List<Double>> datas, String path){
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					new File(path)));
			String data = br.readLine();
			List<Double> l = null;
			while (data != null) {
				String t[] = data.split("\\s");
				l = new ArrayList<Double>();
				for (int i = 0; i < t.length; i++) {
					l.add(Double.parseDouble(t[i]));
				}
				datas.add(l);
				data = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
    } 

	
	
	
//	public static void main(String[] args) {
//		long start = System.currentTimeMillis();
//		TestKNN t = new TestKNN();
//		String datafile = new File("C:\\Users\\acer\\Desktop\\datas\\datingTestSet3.txt").getAbsolutePath();
//		String testfile = new File("C:\\Users\\acer\\Desktop\\datas\\testSet2.txt").getAbsolutePath() ;
//		
//		List<List<Double>> datas = new ArrayList<List<Double>>();
//		List<List<Double>> testdata = new ArrayList<List<Double>>();
//		t.read(datas, datafile);
//		t.read(testdata, testfile);
//		KNN knn = new KNN();
//		for(int i=0; i<testdata.size(); i++){
//			List<Double> test = testdata.get(i);
//			System.out.print("测试元组：");
//			for(int j=0; j<test.size(); j++){
//				System.out.print(test.get(j) + " ");
//			}
//			System.out.print("类别为：");
//			System.out.println(Math.round(Float.parseFloat((knn.knn(datas, test, 50)))));
//		}
//		
//		long end = System.currentTimeMillis();
//		System.out.println("总共用时：" + (end-start)/1000 + " 秒");
//	}
	
	
	
	public static void main(String[] args) {
		Comparator<KNNNode> comparator = new Comparator<KNNNode>(){
			public int compare(KNNNode o1, KNNNode o2){
				if(o1.getDist() >= o2.getDist()) return 1;
				else return 0;
			}
		};
		long start = System.currentTimeMillis();
		TestKNN t = new TestKNN();
		String datafile = new File("C:\\Users\\acer\\Desktop\\datas\\datingTestSet3.txt").getAbsolutePath();
		String testfile = new File("C:\\Users\\acer\\Desktop\\datas\\testSet.txt").getAbsolutePath() ;
		
//		List<List<Double>> datas = new ArrayList<List<Double>>();
//		List<Double> datas = new ArrayList<Double>();
		List<List<Double>> testdata = new ArrayList<List<Double>>();
		
		t.read(testdata, testfile);
		KNN knn = new KNN();
		
		for(int i=0; i<testdata.size(); i++){
			try {
				BufferedReader br = new BufferedReader(new FileReader(
						new File(datafile)));
				String data = br.readLine();
				PriorityQueue<KNNNode> pq = new PriorityQueue<KNNNode>(k, comparator);
				List<Double> l = null;
				int count = 0;
				while (data != null) {
					String tt[] = data.split("\\s");
					l = new ArrayList<Double>();
					for (int j = 0; j < tt.length; j++) {
						l.add(Double.parseDouble(tt[j]));
					}
	//				datas.add(l);
					knn.calDist(testdata.get(i), l);
					knn.knn2(l, testdata.get(i), k, count, pq);
					
					
					count++;
					data = br.readLine();
				}
				
				
				System.out.print(testdata.get(i) + " ");
				System.out.print("类别为：");
				System.out.println(knn.getMostClass(pq));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("总共用时：" + (end-start)/1000 + " 秒");
		
		
		
	}
}
