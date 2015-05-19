package com.buaa.KNN;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class KNN {

	private Comparator<KNNNode> comparator = new Comparator<KNNNode>(){
		public int compare(KNNNode o1, KNNNode o2){
			if(o1.getDist() >= o2.getDist()) return 1;
			else return 0;
		}
	};
	
	public List<Integer> getRandKNum(int k, int max){
		List<Integer> rand = new ArrayList<Integer>(k);
		for(int i =0; i<k; i++){
			int tmp = (int) (Math.random()*max);
//			System.out.println((Math.random()*max) + "xxxxxx");
			if(rand.contains(tmp)) i--;
			else rand.add(tmp);	
			System.out.println(tmp);
		}
		return rand;
	}
	
	public double calDist(List<Double> d1, List<Double> d2){
		double dist = 0.0;
		for(int i=0; i<d1.size(); i++){
			dist += (d1.get(i)-d2.get(i)) * (d1.get(i)-d2.get(i));
		}
		return dist;
	}
	
	public String knn(List<List<Double>> datas, List<Double> testData, int k){
//		PriorityQueue<KNNNode> pq = new PriorityQueue<KNNNode>(k, comparator);
		PriorityQueue<KNNNode> pq = new PriorityQueue<KNNNode>(k, comparator);
//		List<Integer> randNum = getRandKNum(k, datas.size());
//		for(int i = 0; i < k; i++){
//			int index = randNum.get(i);
//			List<Double> currData = datas.get(index);
//			String categ = currData.get(currData.size()-1).toString();
//			KNNNode node = new KNNNode(index, calDist(testData, currData), categ);
//			pq.add(node);
//		}
		
		for(int i=0; i<datas.size(); i++){
			List<Double> currData = datas.get(i);
			String categ = currData.get(currData.size()-1).toString();
			KNNNode node = new KNNNode(i, calDist(testData, currData), categ);
			pq.add(node);
		}
		
		for(int i = 0; i < datas.size(); i++){
			List<Double> t = datas.get(i);
			double dist = calDist(testData, t);
			KNNNode top = pq.peek();
			if(top.getDist() > dist){
				pq.remove();
				pq.add(new KNNNode(i, dist, t.get(t.size()-1).toString()));
			}
		}
		
		
		return getMostClass(pq);
	}

	
	public void knn2(List<Double> datas, List<Double> testData, int k, int i, PriorityQueue<KNNNode> pq){
//		PriorityQueue<KNNNode> pq = new PriorityQueue<KNNNode>(k, comparator);
		
			List<Double> currData = datas;
			String categ = currData.get(currData.size()-1).toString();
			KNNNode node = new KNNNode(i, calDist(testData, currData), categ);
			pq.add(node);
		
		
		
			List<Double> t = datas;
			double dist = calDist(testData, t);
			KNNNode top = pq.peek();
			if(top.getDist() > dist){
				pq.remove();
				pq.add(new KNNNode(i, dist, t.get(t.size()-1).toString()));
			}
		
	
		
	}
	
	
	public String getMostClass(PriorityQueue<KNNNode> pq) {
		Map<String, Integer> classCount = new HashMap<String, Integer>();
		for(int i=0; i<pq.size(); i++){
			KNNNode node = pq.remove();
			String categ = node.getCateg();
			if(classCount.containsKey(categ)){
				classCount.put(categ, classCount.get(categ)+1);
			}
			else{
				classCount.put(categ, 1);
			}
		}
		int maxIndex = 0;
		int maxCount = 0;
		Object[] classes = classCount.keySet().toArray();
		for(int i=0; i<classes.length; i++){
			if(classCount.get(classes[i]) > maxCount){
				maxIndex = i;
				maxCount = classCount.get(classes[i]);
			}
		}
		
		return classes[maxIndex].toString();
	}
	
	
}
