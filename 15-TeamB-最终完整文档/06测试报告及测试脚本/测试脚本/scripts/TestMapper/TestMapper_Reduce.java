package com.buaa.KNN;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TestMapper_Reduce extends Reducer<Text, Text, Text, Text> {

	private Text keyout = new Text();
	private Text result = new Text();
	final static int k = 10;
	private static Comparator<Node> comparator = new Comparator<Node>() {
		public int compare(Node o1, Node o2) {
			if(o1.getDist() >= o2.getDist()) return 1;
			else return 0;
		};
	};
	
	public void reduce(Text _key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		Iterator<Text> itr = values.iterator();
		PriorityQueue<Node> pq = new  PriorityQueue<Node>(k, comparator);
		while(itr.hasNext()){
			String tmp = itr.next().toString();
			String[] ss = tmp.split(":");
			Node node = new Node(Double.parseDouble(ss[0].trim()), ss[1].trim());
			Node top = pq.peek();
			if(top==null){
				pq.add(node);
			}else if(top.getDist() > node.getDist()){
				pq.remove();
				pq.add(node);
			}
		}
		Tools knn = new Tools();
		keyout.set(_key);
		result.set(knn.getMostClass(pq));
		context.write(keyout, result);
	}

}
