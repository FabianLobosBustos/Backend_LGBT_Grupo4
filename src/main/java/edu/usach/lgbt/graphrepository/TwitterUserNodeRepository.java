package edu.usach.lgbt.graphrepository;

import edu.usach.lgbt.graph.TwitterUserNode;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import antlr.collections.List;

public interface TwitterUserNodeRepository extends Neo4jRepository<TwitterUserNode, Long>  {
	TwitterUserNode findByIdTweet(Long id);
	TwitterUserNode findByIdUser(Long id);
	TwitterUserNode findByName(String name);
	
	@Query ("MATCH (n:TwitterUserNode) RETURN n ORDER BY n.influence DESC LIMIT 10")
	Iterable<TwitterUserNode> findTenMoreRelevants();
	
	@Query ("MATCH p= (n)-[r:`influye en`]->(x) WHERE n.idUser={id}  RETURN x")
	Set<TwitterUserNode> findAllRelatedNodes(@Param("id") Long id);
}