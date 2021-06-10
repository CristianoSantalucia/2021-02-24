package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
	private Map<Integer, Player> giocatori; 
	private Graph<Player, DefaultWeightedEdge> grafo; 
	private List<Team> teams;
	
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
		this.giocatori = new HashMap<>(); 
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class); 
		
		/// vertici 
		this.dao.getVertici(giocatori, m);  
		Graphs.addAllVertices(this.grafo, this.giocatori.values()); 
		
		/// archi
		this.teams = new ArrayList<>(); 
		this.dao.getGiocatoriInTeam(teams, giocatori, m);
		Team t1 = this.teams.get(0); 
		Team t2 = this.teams.get(1);
		
		for (Player	p1 : t1.getGiocatori())
		{
			for (Player p2 : t2.getGiocatori())
			{
				double diff = p1.getEfficienza()-p2.getEfficienza();
				if(diff > 0)
					Graphs.addEdge(this.grafo, p1, p2, Math.abs(diff));
				else if(diff < 0)
					Graphs.addEdge(this.grafo, p2, p1, Math.abs(diff));
			}
		}
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
	public String getMigliore()
	{
		double entranti = 0; 
		double uscenti = 0; 
		double best = 0; 
		Player besta = null; 
		for(Player p : this.grafo.vertexSet())
		{
			for (DefaultWeightedEdge e : this.grafo.outgoingEdgesOf(p))
				uscenti += this.grafo.getEdgeWeight(e);
			for (DefaultWeightedEdge e : this.grafo.incomingEdgesOf(p))
				entranti += this.grafo.getEdgeWeight(e);
			double diff = Math.abs(uscenti-entranti);
			if(diff > best)
			{
				best = diff; 
				besta = p; 
			}
		}
		return "\nGIOCATORE MIGLIORE: " + besta.getName() + " ( " + best + " )" ; 
	}
	
	///simulazione 
	
	public void simula (int N)
	{
		List<Evento> eventi = new ArrayList<>(); 
	}
}
