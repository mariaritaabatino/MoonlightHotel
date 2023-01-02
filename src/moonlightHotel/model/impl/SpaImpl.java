package moonlightHotel.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import moonlightHotel.model.Spa;

public class SpaImpl implements Spa{

	// attributi
	private Random random;
	private final int turniSpa;
	private final double costoSpa;
	private boolean[] statoTurni;	// con 1 è libero
									// con 0 è occupato
	
	// costruttore
	public SpaImpl() {
		this.random 	= new Random();
		this.turniSpa 	= 5;
		this.costoSpa	= 90;
		this.statoTurni	= new boolean[this.turniSpa];
		this.setStatoTurni();
	}
	// fine metodi
	
	
	// metodo per settare tutti i turni della spa
	private void setStatoTurni() {
		for(int i = 0; i < this.turniSpa; i++) 
			statoTurni[i] = this.random.nextBoolean();
	}
	// fine metodo
	
	// metodo per ottenere il numero dei turni disponibili in un giorno di spa
	public List<String> getTurniDispGiornalieri() {
		
		List<String> turniDisponibili = new ArrayList<String>();
		
		// calcolo i turni disponibili
		for(int i = 0; i < this.turniSpa; i++) {
			if(this.statoTurni[i]) {
				turniDisponibili.add((i+1)+"° turno");
			}
		}
		
		return turniDisponibili;
	}
	// fine metodo
	
	// metodo che cambia lo stato di un unico turno
	public void setStatoTurno(int singoloTurno) {
		int nuovoStato = singoloTurno ;
		this.statoTurni[nuovoStato] = false;
	}
	// fine metodo
	
	// metodo per ottenere il costo dalla Spa
	public double getCosto() {
		return this.costoSpa;
	}
	// fine metodo
	
}
// fine classe