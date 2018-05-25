package edu.usach.lgbt.tweet.sentimentAnalyzer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SentimentAnalyzer {
    //Se almacenan todos los StopWords que provee el analyzer de Indexer
    private CharArraySet stopWords = new SpanishAnalyzer(Version.LUCENE_43).getStopwordSet();
    private HashSet<String> positiveWords = new HashSet<>();
    private HashSet<String> negativeWords = new HashSet<>();

    public SentimentAnalyzer(){
        loadWords();
    }

    private void loadWords() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            this.positiveWords.addAll(IOUtils.readLines(classLoader.getResourceAsStream("positiveWords.dat"), "UTF-8"));
            this.negativeWords.addAll(IOUtils.readLines(classLoader.getResourceAsStream("negativeWords.dat"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método que calcula el sentimiento de un tweet
    public int[] calculateSentiment(String tweet){

        //Tipos de sentimientos
        int negative = 0;
        int positive = 0;
        int neutral = 0;

        int wordsCount = 0;
        int[] connotaciones = new int[4];

        //Se tokeniza el tweet
        String[] tokens = tweet.split(" ");
        for(String token : tokens){
            //Se verifica que el token no sea un stopword
            if(!stopWords.contains(StringUtils.stripAccents(token.toLowerCase()))){
                if(this.positiveWords.contains(token)){
                    positive++;
                    wordsCount++;
                }
                if(this.negativeWords.contains(token)){
                    negative++;
                    wordsCount++;
                }
                if(!this.positiveWords.contains(token) && !this.negativeWords.contains(token)) {
                	neutral++;
                    wordsCount++;
                }
            }
        }
        //Formato: [0]: wordsCount, [1]: positive, [2]: neutral, [3]: negative
        connotaciones[0] = wordsCount;
        connotaciones[1] = positive;
        connotaciones[2] = neutral;
        connotaciones[3] = negative;
        return connotaciones;
    }
}
