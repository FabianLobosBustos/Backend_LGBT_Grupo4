package edu.usach.lgbt.tweet.sentimentAnalyzer;

import edu.usach.lgbt.tweet.objTweet.Tweet;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TweetIndexer {
    private List<Tweet> tweets;
    private Directory directory;

    public TweetIndexer(List<Tweet> tweets){
    	this.tweets = tweets;
    	
    }

    public void createIndex() throws IOException {
        this.directory = new RAMDirectory();
        Analyzer analyzer = new SpanishAnalyzer(Version.LUCENE_43);
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(this.directory, config);
        try{
            Document document;
            for(Tweet tweet : this.tweets){
                document = new Document();
                
               
                document.add(new TextField("tweet", tweet.getText(), Field.Store.YES));
                //document.add(new StringField("username", tweet.getTwitterUser().getName(), Field.Store.YES));
                document.add(new LongField("id", tweet.getId(), Field.Store.YES));
                writer.addDocument(document);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
    public List<Document> searchTweets(String textToSearch) throws IOException, ParseException{
    	IndexReader reader = DirectoryReader.open(this.directory);
    	IndexSearcher searcher = new IndexSearcher(reader);
    	QueryParser parser = new QueryParser(Version.LUCENE_43, "tweet", new SpanishAnalyzer(Version.LUCENE_43));
    	Query query = parser.parse(textToSearch);
    	TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
    	searcher.search(query, collector);
    	ScoreDoc[] hits = collector.topDocs().scoreDocs;
    	List <Document> foundTweets = new ArrayList<>();
    	for(ScoreDoc hit: hits){
    		foundTweets.add(searcher.doc(hit.doc));
    	}
    	//System.out.println(foundTweets.toString());
    	return foundTweets;
    }
  
}
