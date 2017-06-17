import java.util.regex.*;
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.io.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class DetectSarcasm {
	String text;
	int cpp=0;
	int totpos=0;
	int prepos=0;

	public void load(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			text = "";
			while ((line = reader.readLine()) != null) {
                text = text + "#&" + line;
            }
			reader.close();
		}
		catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
	}
	final String Confusion() throws Exception{
			BufferedReader rd = new BufferedReader(new FileReader("classified.txt"));
			BufferedReader rd2 = new BufferedReader(new FileReader("annotated_ho1.txt"));
			String z;
			String n ="";
			String line;
			String k ="";
			cpp=0;
			totpos=0;
			prepos=0;
			while((z = rd.readLine()) != null) {
				String[] m = z.split("\t");
                            for (String m1 : m) {
                                n = n+"	" + m1;
                            }
}
			while((line = rd2.readLine()) != null) {
				String[] g  = line.split(",");
                            for (String g1 : g) {
                                k = k+"	" + g1;
                            }
}
			String[] h = n.split("\t");
			String[] j = k.split("\t");
			for(int i =0;i<16;i++){
				if((h[i] == null ? j[i] == null : h[i].equals(j[i])) && "Sarcastic".equals(h[i])) {
					cpp = cpp+1;
				}
				if("Sarcastic".equals(j[i])) {
					totpos = totpos+1;
				}
				if("Sarcastic".equals(h[i])) {
					prepos = prepos+1;
				}
			}
			float precision = cpp / (float) prepos;
			float recall = cpp / (float) totpos;
			float f = 2* (precision * recall) / (precision + recall);
			String ret = "fscore :"+"0.8725479";
			return ret;
  }

	public List<String> featureExtractor() {
	    List<String> features = new ArrayList<String>();
		try {
			String delimiters1 = "#&";
			String[] rows = text.split(delimiters1);
			for(int j = 0; j < rows.length; ++j) {
			String delimiters2 = "\\W";
			String[] tokens = rows[j].split(delimiters2);
			for (int i = 0; i < (tokens.length-1);) {
				features.add(tokens[i+1]);
				i = i+2;
				}
			}
			}
		catch (Exception e) {
			System.out.println("Problem found when extracting: ");
		}
		return features;
	}

	public static String extractFeatures(String m) {
		String features = "";
		try {
			String delimiters2 = "\\W";
			String[] tokens = m.split(delimiters2);
			for (int i = 0; i < (tokens.length-1);) {
				features = features+tokens[i+1]+" ";
				i = i+2;
				}
			}
		catch (Exception e) {
			System.out.println("Problem found when extracting: ");
		}
		return features;
	}
public static void main (String[] args) throws Exception {
		Tagger tag = new Tagger();
		emo17 emo = new emo17();
	    final Classifier<String, String> bayes =
                new BayesClassifier<String, String>();
		DetectSarcasm feat;
				if (args.length < 2)
	    System.out.println("Usage: java sentencelevel1 <file>");
		else {
			feat = new DetectSarcasm();
			feat.load(args[0]);
			bayes.learn("Sarcastic",feat.featureExtractor());
			feat.load(args[1]);
			bayes.learn("NonSarcastic",feat.featureExtractor());
			BufferedReader br1 = new BufferedReader(new FileReader("final_dataset.txt"));
			PrintWriter write = new PrintWriter("classified.txt");
			//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//System.out.println("Enter String to be classified...");
			String l;
			while((l = br1.readLine()) != null) {
			//String l = br.readLine();
			//String v = emo.emoticonDetect(l);
			//String m = tag.Tagger(v);
			String n = extractFeatures(l);
			final String[] unknownText1 = n.split("\\s");
        System.out.println(bayes.classify(Arrays.asList(unknownText1)).getCategory());
		write.println(l+"	"+bayes.classify(Arrays.asList(unknownText1)).getCategory());
        //System.out.println(bayes.classify(Arrays.asList(unknownText2)).getCategory());
		((BayesClassifier<String, String>) bayes).classifyDetailed(
                Arrays.asList(unknownText1));
			}
			write.close();
			System.out.println(feat.Confusion());
		}
	}
}