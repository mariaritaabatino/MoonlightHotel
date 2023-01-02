package moonlightHotel.model.impl;

import java.util.Random;

import moonlightHotel.model.BabySitting;

public class BabySittingImpl implements BabySitting{
	
	// attributi
	private Random random;
	private final int capienzaMassima;
	private int bambiniPresenti;
	private int postiRimanenti;
	
	// costruttore
	public BabySittingImpl() {
		this.random				= new Random();
		this.capienzaMassima	= 35;
		this.bambiniPresenti	= this.random.nextInt(15);
		this.postiRimanenti		= this.capienzaMassima - this.bambiniPresenti; 
	}
	// fine costruttore
	
	// metodo per aggiornare i posti rimanenti nell'area bimbi
	public void setNuoviDatiBabySitting(int nuoviBambini) {
		this.bambiniPresenti += nuoviBambini;
		this.postiRimanenti	 -= nuoviBambini;
	}
	// fine metodo
	
	// metodo pubblico per ottenere i posti rimanenti nell'area bimbi
	public int getPostiRimanenti() {
		return this.postiRimanenti;
	}
	// fine metodo
}
// fine classe