import java.util.regex.*;
import java.io.*;
public class emo17 {
	final static String emoticonDetect(String f) {
    Pattern patt1 = Pattern.compile(":\\-D|:C|:\\-\\)|:\\)|:o\\)|:\\]|:3|:c\\)|:>|=\\]|8\\)|=\\)|:\\}|:^\\)|😀|😂|😃|😄|😅|😆|😇|😈|😉|😊|😋|😌|�?|😎|😗|😘|😙|😚|😛|😜|😸|😹|😺|😻|😼|😽");
    Pattern patt2 = Pattern.compile(":\\-\\(|:\\(|:c|:<|:\\[|:\\{|:\\-P|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|Pos�|🙀");
	//Pattern patt3 = Pattern.compile("\"");
    String replace1 = "zxp";
    String replace2 = "zxn";
	//String replace3 = "\" QT";
    //BufferedReader r = new BufferedReader(new FileReader("input.txt"));
    //PrintWriter writer = new PrintWriter("input41.txt", "UTF-8");
    //while ((s = r.readLine()) != null) {
      Matcher m = patt1.matcher(f);
      while (m.find()) {
        f = m.replaceAll(replace1);
      }
      Matcher n = patt2.matcher(f);
      while (n.find()) {
        f = n.replaceAll(replace2);
      }
	  //Matcher c = patt3.matcher(f);
	//while(c.find()) {
		//f = c.replaceAll(replace3);
	//}
		return f;
    }
  }

