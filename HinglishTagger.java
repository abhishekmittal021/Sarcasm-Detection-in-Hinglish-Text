import java.io.*;
import java.util.regex.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class HinglishTagger {
//final static String Tagger(String l)
	//static String y = "";
		//String v ="";
	public static void main(String[] args) throws Exception {
        //String y = "";
	emo17 emo = new emo17();	
        MaxentTagger tagger = new MaxentTagger(
                "D:/java/java/project/bidirectional-distsim-wsj-0-18.tagger");
        BufferedReader r1;
        r1 = new BufferedReader(new FileReader("C:\\Users\\SWASH\\Desktop\\phase\\SARCASM\\ho1.txt"));
        PrintWriter writer1 = new PrintWriter("C:\\Users\\SWASH\\Desktop\\phase\\SARCASM\\final_dataset.txt");
        String line1;
        while(null != (line1 = r1.readLine()) ) {
            String g = emo17.emoticonDetect(line1);
            String t = tagger.tagString(g);
            //System.out.println("English Tagged :"+" "+t);
            writer1.println(t);
        }
        writer1.close();
        BufferedReader r2;
        r2 = new BufferedReader(new FileReader("C:\\Users\\SWASH\\Desktop\\phase\\SARCASM\\eng_tagged.txt"));
        PrintWriter writer2 = new PrintWriter("C:\\Users\\SWASH\\Desktop\\phase\\SARCASM\\sar_tagged.txt");
        String line2;
        while(null != (line2 = r2.readLine()) ) {
        String y = "";
	String[] r = line2.split("\\s");
	for(int i =0;i<r.length;i++) {
			String[] q = r[i].split("/");
				for(int j = 0;j<q.length;j++) {
					if("FW".equals(q[j])|"NNP".equals(q[j])|"NN".equals(q[j])) 
	{	
		Pattern patt1 = Pattern.compile("\\w+ta\\b+\\w+taa\\b+\\w+ti\\b+\\w+te\\b+\\w+tee\\b|raha|rha|rahi|rhi|rahe|rhe|\\w+unga\\b|\\w+ega\\b|\\w+oge\\b|\\w+enge\\b|le|lia|liya|la|\\w+ya\\b|\\w+ye\\b|\\w+yi\\b");
        Pattern patt2 = Pattern.compile("sarcasm|Sarcasm|sarcastic|Sarcastic");
		Pattern patt3 = Pattern.compile("wow|aha|aha|ahem|ahh|ahoy|alas|arg|aw|bam|bingo|blah|boo|bravo|eh|are");
        Pattern patt4 = Pattern.compile("zxp");
        Pattern patt5 = Pattern.compile("zxn");
		String p = "NN";
		String h = "CAPS";
		if(q[j-1] == null ? q[j-1].toUpperCase() == null : q[j-1].equals(q[j-1].toUpperCase()))
		{
		q[j] = q[j].replaceAll(q[j],h);
		}
		else {
		boolean check = Character.isUpperCase(q[j-1].codePointAt(0));
		if(true==check) {
		q[j] = q[j].replaceAll(q[j],p);
	}
	}
	String replace1 = "VB";
	String replace2 = "SAR";
	String replace3 = "UH";
        String replace4 = "HBP";
	String replace5 = "HBN";
	Matcher m = patt1.matcher(q[j-1]);
	while(m.find()) {
	q[j] = m.replaceAll(replace1);
	}
	Matcher n = patt2.matcher(q[j-1]);
	while(n.find()) {
	q[j] = n.replaceAll(replace2);
	}
	Matcher g = patt3.matcher(q[j-1]);
	while(g.find()) {
	q[j] = g.replaceAll(replace3);
	}
        Matcher d = patt4.matcher(q[j-1]);
	while(d.find()) {
	q[j] = d.replaceAll(replace4);
	}
        Matcher w = patt5.matcher(q[j-1]);
	while(w.find()) {
	q[j] = w.replaceAll(replace5);
	}
	}
            y = y+q[j]+" ";
	}
	}
                writer2.println(y);
	}
				System.out.println("****Tagged file created for Sarcasm Detection****");
                writer2.close();
}
}