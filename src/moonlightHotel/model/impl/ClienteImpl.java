package moonlightHotel.model.impl;

import moonlightHotel.model.Cliente;

public class ClienteImpl implements Cliente {

	// attributi
	private String nome;
	private String cognome;
	private int numAdulti;
	private int numBambini;
	private int numCompFam;
	private int giorniPermanenza;
	
	// costruttore
	public ClienteImpl(String nome, String cognome, int numAdulti, int numBambini, int permanenza) {
		this.nome				= nome;
		this.cognome			= cognome;
		this.numAdulti			= numAdulti;
		this.numBambini			= numBambini;
		this.numCompFam			= setNumCompFam();
		this.giorniPermanenza	= permanenza;
	}
	// fine costruttore
	
	// metodi
	
	public String getNome() {
		return this.nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public int getNumAdulti() {
		return this.numAdulti+1;
	}
	
	public int getNumBambini() {
		return this.numBambini;
	}

	private int setNumCompFam() {
		return this.numCompFam = this.numAdulti + this.numBambini;
	}
	
	public int getNumCompFam() {
		return this.numCompFam;
	}

	public int getGiorniPermanenza() {
		return this.giorniPermanenza;
	}
	
}
// fine classe