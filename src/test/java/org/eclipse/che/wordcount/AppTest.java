package org.eclipse.che.wordcount;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit test for simple App.
 */
@RunWith(JUnit4.class)
public class AppTest {
	
	private static final String inputText = "Input.txt";
	private static final String outpuText = "target/TestOutput";
	
	private static Job job = null;
	
	@BeforeClass
	public static void setUp() throws IOException {
		job = new Job();
		job.setJarByClass(WordCount.class);
		job.setJobName("WordCounter");
		
		//Add input and output file paths to job based on the arguments passed
		FileInputFormat.addInputPath(job, new Path(inputText));
		FileOutputFormat.setOutputPath(job, new Path(outpuText));
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//Set the MapClass and ReduceClass in the job
		job.setMapperClass(MapClass.class);
		job.setReducerClass(ReduceClass.class);
	}

	// Simple test
	@Test
	public void testApp() {
		Assume.assumeNotNull(job);
		try {
			assertTrue(job.waitForCompletion(true));
		} catch (Exception ex) {
			fail("Something went wrong when waiting for job completion " + ex);
		}
	}
	
	@AfterClass
	public static void destroy() {
		try {
			if (job != null && !job.isComplete()) {
				job.killJob();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
