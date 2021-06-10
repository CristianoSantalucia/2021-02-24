package it.polito.tdp.PremierLeague.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model
{
	private PremierLeagueDAO dao;
	private Map<Integer, Player> idMap; 
	private Graph<Player, DefaultWeightedEdge> grafo; 
	
	public Model()
	{
		this.dao = new PremierLeagueDAO();
	}
	public List<Match> getAllMatches()
	{
		return this.dao.listAllMatches();
	}
	
	public void creaGrafo(Match m)
	{
		// ripulisco mappa e grafo
		this.idMap = new HashMap<>(); 
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class); 
		
		/// vertici 
		this.dao.getVertici(idMap, m);  
		Graphs.addAllVertices(this.grafo, this.idMap.values()); 
		
		/// archi
//		List<Adiacenza> adiacenze = new ArrayList<>(this.dao.getAdiacenze());
//		for (Adiacenza a : adiacenze)
//		{
//			//recupero gli Oggetti dalla chiave della mappa e faccio controlli
//			Object n1 = this.idMap.get(a.getNodo1());
//			Object n2 = this.idMap.get(a.getNodo2());
//			if (n1 != null && n2 != null)
//				Graphs.addEdge(this.grafo, n1, n2, a.getPeso());
//		}
	}
	public int getNumVertici()
	{
		return this.grafo.vertexSet().size();
	}
	public int getNumArchi()
	{
		return this.grafo.edgeSet().size();
	}
	public Collection<Player> getVertici()
	{
		return this.grafo.vertexSet();
	}
	public Collection<DefaultWeightedEdge> getArchi()
	{
		return this.grafo.edgeSet();
	}

}
