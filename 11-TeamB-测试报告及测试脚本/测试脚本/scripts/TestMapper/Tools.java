package com.buaa.KNN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Tools {

	private Comparator<Node> comparator = new Comparator<Node>() {
		public int compare(Node o1, Node o2){
			if(o1.getDist() >= o2.getDist()) return 1;
			else return 0;
		}
	};
	
	public double calDist(List<Double> d1, List<Double> d2){
		double dist = 0.0;
		for(int i = 0; i < d1.size(); i++){
			dist += (d1.get(i) - d2.get(i)) * (d1.get(i) - d2.get(i));
		}
		return dist;
	}
	
	
	public String getMostClass(PriorityQueue<Node> pq){
		Map<String, Integer> classCount = new HashMap<String, Integer>();
		for(int i = 0; i < pq.size(); i++){
			Node node = pq.remove();
			String categ = node.getCateg();
			if(classCount.containsKey(categ)){
				classCount.put(categ, classCount.get(categ) + 1);
			}else{
				classCount.put(categ, 1);
			}
		}
		int maxIndex = -1;
		int maxCount = 0;
		Object[] classes = classCount.keySet().toArray();
		for(int i = 0; i < classes.length; i++){
			if(classCount.get(classes[i]) > maxCount){
				maxIndex = i;
				maxCount = classCount.get(classes[i]);
			}
		}
		return classes[maxIndex].toString().trim();
	}
	
	public void read(List<List<Double>> datas, String path){
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String data = br.readLine();
			List<Double> l;
			while(data != null){
				String t[] = data.split("\\s");
				l = new ArrayList<Double>();
				for(int i = 0; i < t.length; i++){
					l.add(Double.parseDouble(t[i].trim()));
				}
				datas.add(l);
				data = br.readLine();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
