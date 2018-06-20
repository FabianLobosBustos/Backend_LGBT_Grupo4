package edu.usach.lgbt.rest;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import edu.usach.lgbt.entities.Region;
import edu.usach.lgbt.entities.Stadistic;
import edu.usach.lgbt.graph.TwitterUserNode;
import edu.usach.lgbt.graphrepository.TwitterUserNodeRepository;
import edu.usach.lgbt.repository.RegionRepository;
import edu.usach.lgbt.repository.StadisticRepository;


@CrossOrigin
@RestController  
@RequestMapping("/node")
public class TwitterUserNodeService {
	
	@Autowired
	private TwitterUserNodeRepository twitterUserNodeRepository;
	

    @CrossOrigin
    @RequestMapping(value= "/byName/{name}",method = RequestMethod.GET)
    @ResponseBody
	public TwitterUserNode getUserByName(@PathVariable("name") String name) {
	
    	return twitterUserNodeRepository.findByName(name);
	}
	
	
    
    //Método GET que obtiene el grafo de influencia de los 10 mas influyentes
    @RequestMapping(value = "/neo4j", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getGraph(){

        // Nodo del prestador y los usuarios que tweetean sobre el
    	Iterable<TwitterUserNode> diezInfluyentes = twitterUserNodeRepository.findTenMoreRelevants();
        
        
        JSONObject graphJSON = new JSONObject();
        JSONArray nodes = new JSONArray();
        JSONArray edges = new JSONArray();
        
        JSONObject edge;

        // Para crear el JSON se agregan los 10 mas influyentes
        for(TwitterUserNode nodoInfluyente_nivel_1 : diezInfluyentes) {
           	JSONObject node = new JSONObject();  
            node.put("id", nodoInfluyente_nivel_1.getIdUser());
            node.put("name", nodoInfluyente_nivel_1.getName());
            node.put("influencia", nodoInfluyente_nivel_1.getInfluence());
            nodes.add(node);
            
            
            
            Set<TwitterUserNode> circuloInfluencia_nivel_2 =  twitterUserNodeRepository.findAllRelatedNodes(nodoInfluyente_nivel_1.getIdUser());
            if( circuloInfluencia_nivel_2 != null) {
        	  
            	for(TwitterUserNode nodoInfluyente_nivel_2 : circuloInfluencia_nivel_2) {
            		JSONObject node1 = new JSONObject();
            		node1.put("id", nodoInfluyente_nivel_2.getIdUser());
            		node1.put("name", nodoInfluyente_nivel_2.getName());
            		node1.put("influencia", nodoInfluyente_nivel_2.getInfluence());
            		nodes.add(node1);
                  
            		edge = new JSONObject();
            		edge.put("source", nodoInfluyente_nivel_1.getIdUser() );
            		edge.put("target", nodoInfluyente_nivel_2.getIdUser());
            		edges.add(edge);
                  
            		Set<TwitterUserNode> circuloInfluencia_nivel_3 = twitterUserNodeRepository.findAllRelatedNodes(nodoInfluyente_nivel_2.getIdUser());
                  
            		if(circuloInfluencia_nivel_3 != null) {
                		for(TwitterUserNode nodoInfluyente_nivel_3 : circuloInfluencia_nivel_3) {
                        	JSONObject node11 = new JSONObject();
                        	node11.put("id", nodoInfluyente_nivel_3.getIdUser());
                        	node11.put("name", nodoInfluyente_nivel_3.getName());
                        	node11.put("influencia", nodoInfluyente_nivel_3.getInfluence());
                        	nodes.add(node11);
                          
                        	 edge = new JSONObject();
                        	 edge.put("source", nodoInfluyente_nivel_2.getIdUser() );
                        	 edge.put("target", nodoInfluyente_nivel_3.getIdUser());
                        	 edges.add(edge);
                          
                          
                		}          			
            		}

            	}        	  
            }
        }
        
        
         
        graphJSON.put("nodes", nodes);
        
        graphJSON.put("edges", edges);

        return graphJSON;
    }
	
}