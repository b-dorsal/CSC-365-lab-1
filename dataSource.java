package pkg365ass1;
//Brian Dorsey 2016
//Class to keep track of each datasource's info, URL, and word frequency hashmap.

import java.net.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class dataSource {

    private String url;
    private URL sourceURL;
    private bHashMap wordMap = new bHashMap(10000); //custom map of String word, int frequency <key, value>.
    private int wordCount = 0; //the amount of different words this source contains.
    private int sourceSize = 0; //the total number of words contained in this source.

    public dataSource(String URLstring) throws Exception { //Constuctor for the dataSource object.
        URL constructURL = new URL(URLstring);
        sourceURL = constructURL;
        url = URLstring;
        loadSourceData();
    }

    public int compareSource(String url) throws Exception { // Compares this objects source to the URL provided.
        int score = 0;
        bHashMap unionMap = new bHashMap(10000);
        
        Document doc = Jsoup.connect(url).get();
        Elements paragraphs = doc.select("p");
        for (Element p : paragraphs) {
            String inputLine = p.text();
            String nextWord;
            while (inputLine.contains(" ")) {
                nextWord = (inputLine.substring(0, inputLine.indexOf(' ')).trim()).toLowerCase();
                inputLine = inputLine.substring(inputLine.indexOf(' ') + 1, inputLine.length()).trim();
                
                if (wordMap.containsKey(nextWord) && !unionMap.containsKey(nextWord)) {
                    unionMap.put(nextWord);
                    score++;
                }
            }
        }

        return score;
    }

    private void loadSourceData() throws Exception { // Loads this sources data into a custom HashMap.
        Document doc = Jsoup.connect(url).get();
        Elements paragraphs = doc.select("p");
        for (Element p : paragraphs) {
            String inputLine = p.text();
            String nextWord;
            while (inputLine.contains(" ")) {
                nextWord = (inputLine.substring(0, inputLine.indexOf(' ')).trim()).toLowerCase();
                inputLine = inputLine.substring(inputLine.indexOf(' ') + 1, inputLine.length()).trim();
                addToWordMap(nextWord);
            }

        }
    }

    private void addToWordMap(String word) { // add the word to the map.
        if (wordMap.containsKey(word)) {
            wordMap.put(word);
        } else {
            wordMap.put(word);
            wordCount++;
        }
        sourceSize++;
    }


    public String getURL() {	// returns the source URL string
        return url;
    }

    public boolean hasWord(String word) { // returns boolean true if the source contains the word provided, else false.
        if (wordMap.containsKey(word)) {
            return true;
        } else {
            return false;
        }
    }

    public int getWordUsage(String word) {//returns the frequency of the inputed string.
        return wordMap.get(word);
    }
}
