package moonlightHotel.model.impl;

import java.util.Random;

import moonlightHotel.model.Bicicletta;

public class BiciclettaImpl implements Bicicletta{

	// attributi
	private final int codiceBici;
	private final double costo; 
	private Random random;
	private boolean stato;	// con 1 è disponibile
							// con 0 è riservata
	
	// costruttore
	public BiciclettaImpl(int codice) {
		this.codiceBici = codice + 1;
		this.costo		= 10;
		this.random		= new Random();
		this.setStato();
	}
	// fine costruttore
	
	// metodo per settare lo sttao delle bici
	private void setStato() {
		if(this.codiceBici < 18) {
			this.stato	=  this.random.nextBoolean();
		} else {
			this.stato	= true;
		}
	}
	// fine metodo
	
	// metodi
	public int getCodiceBici() {
		return this.codiceBici;
	}
	// fine metodo 
	
	// metodo per settare lo stato della biclicetta
	public void setNuovoStato(boolean nuovoStato) {
		this.stato = nuovoStato;
	}
	// fine metodo
	
	// metodo per ottenere lo stato della bicicletta
	public boolean getStato() {
		return this.stato;
	}
	// fine metodo
	
	// metodo per ottenere il costo del noleggio di una bicicletta
	public double getCostoBicicletta() {
		return this.costo;
	}
	// fine metodo
	
}
// fine classe