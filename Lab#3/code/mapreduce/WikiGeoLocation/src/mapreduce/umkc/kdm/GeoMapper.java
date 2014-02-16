/**
 * 
 */
package mapreduce.umkc.kdm;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class GeoMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, Text> {

	public static String GEO_RSS_URI = "http://www.georss.org/georss/point";

	private Text geoLocationKey = new Text();
	private Text geoLocationName = new Text();

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> outputCollector, Reporter reporter)
			throws IOException {

		String dataRow = value.toString();

		StringTokenizer dataTokenizer = new StringTokenizer(dataRow, "\t");
		String articleName = dataTokenizer.nextToken();
		String pointType = dataTokenizer.nextToken();
		String geoPoint = dataTokenizer.nextToken();
		if (GEO_RSS_URI.equals(pointType)) {
			StringTokenizer st = new StringTokenizer(geoPoint, " ");
			String strLat = st.nextToken();
			String strLong = st.nextToken();
			double lat = Double.parseDouble(strLat);
			double lang = Double.parseDouble(strLong);
			long roundedLat = Math.round(lat);
			long roundedLong = Math.round(lang);
			String locationKey = "(" + String.valueOf(roundedLat) + ","
					+ String.valueOf(roundedLong) + ")";
			String locationName = URLDecoder.decode(articleName, "UTF-8");
			locationName = locationName.replace("_", " ");
			geoLocationKey.set(locationKey);
			geoLocationName.set(locationName);
			outputCollector.collect(geoLocationKey, geoLocationName);
		}

	}

}