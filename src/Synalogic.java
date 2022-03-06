import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Synalogic {
    static Synalogic rf = null;
    static final int  MAX_LENGTH_OF_WORD = 100;
    static final String ls = "\n";
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage java ReadFile <filename>");
            System.exit(0);
        }
        //Standard read file into a string with EOL characters
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        System.out.println(results(preprocess(content)));
    }
    protected static String preprocess (String input) {
        //Join up words using a -EOL
        input = input.replaceAll("-"+ls, "");
        //replace EOL with <space>
        input = input.replaceAll(ls, " ");
        //replace "
        //replace ,.()
        input = input.replaceAll("[,.')(\\\"]", "");
        //System.out.println("Input="+input);
        return input;
    }



    public static String results(String content) {
        rf = new Synalogic();
        String [] words = content.split("\\s+");
        StringBuilder sb = new StringBuilder();
        Map<Integer,Integer> counts = new HashMap<Integer,Integer>();
        int max = 0;
        for (String word : words) {
            if (word != null && word.length() > 0) {
                if (counts.get(word.length()) == null) {
                    counts.put(word.length(), 1);
                } else {
                    counts.put(word.length(), counts.get(word.length())+1);
                }
                max = (word.length() > max) ? word.length() : max;
            }
        }
        Long sum = 0l;
        Long wordCount = 0l;
        int length = 1;
        do {
            if (counts.get(length) != null) {
                sum += length * counts.get(length);
                wordCount += counts.get(length);
            }
        } while(length++ <= max);
        sb.append("Word count="+wordCount+ls);

        Double average = sum / (1.0*wordCount);
        sb.append("Average word length="+average+ls);
        length = 1;
        int mostFrequent = 0;
        int storeWordLength = 0;
        List<Integer> matches = new ArrayList<Integer>();
        do {
            if (counts.get(length) != null) {
                sb.append("Number of words of length "+length+" is "+counts.get(length)+ls);
                if (counts.get(length) > mostFrequent) {
                    mostFrequent = counts.get(length);
                    storeWordLength = length;
                    matches.clear();
                    matches.add(length);
                } else if (counts.get(length) == mostFrequent) {
                    matches.add(length);
                }
            }
        } while(length++ <= max); 

        sb.append("The most frequently occuring word length is "+mostFrequent+", for word lengths of "+storeWordLength);
        int i = 0;
        for (Integer len: matches) {
            if (i++ > 0) {
                sb.append(" & "+ len);
            }
        }
        sb.append(ls);
        return sb.toString();
    }

}
