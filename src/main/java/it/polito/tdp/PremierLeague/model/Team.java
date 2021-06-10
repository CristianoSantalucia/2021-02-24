package it.polito.tdp.PremierLeague.model;

import java.util.List;

public class Team
{
	Integer teamID;
	String name;
	List<Player> giocatori;  
	
	@Override public String toString()
	{
		return "Team [teamID=" + teamID + ", name=" + name + ", giocatori=" + giocatori + "]";
	}

	public Integer getTeamID()
	{
		return teamID;
	}

	public void setTeamID(Integer teamID)
	{
		this.teamID = teamID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Player> getGiocatori()
	{
		return giocatori;
	}
	
	public void addGiocatori(Player p)
	{
		if(!this.giocatori.contains(p))
			this.giocatori.add(p); 
	}

	public void setGiocatori(List<Player> giocatori)
	{
		this.giocatori = giocatori;
	}

	public Team(Integer teamID, String name, List<Player> giocatori)
	{
		this.teamID = teamID;
		this.name = name;
		this.giocatori = giocatori;
	}

	@Override public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teamID == null) ? 0 : teamID.hashCode());
		return result;
	}

	@Override public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Team other = (Team) obj;
		if (teamID == null)
		{
			if (other.teamID != null) return false;
		}
		else if (!teamID.equals(other.teamID)) return false;
		return true;
	}

}
