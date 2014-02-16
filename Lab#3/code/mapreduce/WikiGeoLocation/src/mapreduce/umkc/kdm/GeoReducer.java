package mapreduce.umkc.kdm;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class GeoReducer extends MapReduceBase implements
		Reducer<Text, Text, Text, Text> {

	private Text outputKey = new Text();
	private Text outputValue = new Text();

	@Override
	public void reduce(Text anagramKey, Iterator<Text> anagramValues,
			OutputCollector<Text, Text> results, Reporter reporter)
			throws IOException {

		String outputText = "";
		while (anagramValues.hasNext()) {
			Text locationName = anagramValues.next();
			outputText = outputText + locationName.toString() + " ,";
		}
		outputKey.set(anagramKey.toString());
		outputValue.set(outputText);
		results.collect(outputKey, outputValue);
	}

}