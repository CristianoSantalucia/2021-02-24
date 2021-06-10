package it.polito.tdp.PremierLeague.model;

public class Evento
{
	public enum Tipo{GOAL,ESPLUSIONE,INFORTUNIO}; 
	private Tipo tipoEvento;
	private Team team; 
}
