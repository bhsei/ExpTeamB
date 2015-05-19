package com.buaa.KNN;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConfigurable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class TestConfig2 {

	public static void main(String[] args) throws Exception {
		
		long start = System.currentTimeMillis();
		Configuration conf = new Configuration();  
		  
        String[] otherArgs = new GenericOptionsParser(conf, args)  
                .getRemainingArgs();  
        if (otherArgs.length != 2) {  
            System.err.println("Usage: KNN <in> <out>");  
            System.exit(2);  
        }
        // set job   
        Job job = Job.getInstance(conf, "HadoopKNN");
        TextInputFormat.setMinInputSplitSize(job, 1024L);
        TextInputFormat.setMaxInputSplitSize(job, 1024*1024*40L);
        job.setJarByClass(TestConfig2.class);  
        job.setMapperClass(TestConfig2_Map.class);  
//        job.setCombinerClass(KNNCombine.class);  
        job.setReducerClass(TestConfig2_Reduce.class);  
        job.setOutputKeyClass(Text.class);  
        job.setOutputValueClass(Text.class);  
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));  
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));  
        //submit job and wait for fininshing  
        if(!job.waitForCompletion(true))
        	return;
		long end = System.currentTimeMillis();
		System.out.println("总共用时：" + (end-start)/1000 + " 秒");
	}

}
