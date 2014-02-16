/**
 * 
 */
package mapreduce.umkc.kdm;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class WikiGeoLocation {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(WikiGeoLocation.class);
		conf.setJobName("geolocationgroup");

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		conf.setMapperClass(GeoMapper.class);
		conf.setReducerClass(GeoReducer.class);

		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);

		System.out.println(args[0]);
		System.out.println(args[1]);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		JobClient.runJob(conf);
	}

}
