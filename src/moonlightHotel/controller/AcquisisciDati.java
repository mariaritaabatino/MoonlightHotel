package moonlightHotel.controller;

import java.util.ArrayList;
import java.util.List;

import moonlightHotel.model.Bicicletta;
import moonlightHotel.model.Camera;
import moonlightHotel.model.Deluxe;
import moonlightHotel.model.Spa;
import moonlightHotel.model.Standard;
import moonlightHotel.model.Superior;
import moonlightHotel.model.impl.BiciclettaImpl;
import moonlightHotel.model.impl.SpaImpl;

public class AcquisisciDati {
	// metodo per inizializzare le camere
	public List<Camera> camere(){
		List<Camera> camere	= new ArrayList<Camera>();
		camere.add(new Standard(1, 01, 4));
		camere.add(new Standard(1, 02, 8));
		camere.add(new Standard(1, 03, 6));
		camere.add(new Superior(2, 01, 5));
		camere.add(new Superior(2, 02, 4));
		camere.add(new Superior(2, 03, 2));
		camere.add(new Deluxe(3, 01, 4));
		camere.add(new Deluxe(3, 02, 5));
		camere.add(new Deluxe(3, 03, 2));
		
		return camere;
	}
	// fine metodo
	
	// metodo per inizializzare le Biciclette
	public Bicicletta[] biciclette() {
		
		Bicicletta[] bici = new Bicicletta[40];
		for(int i = 0; i < bici.length; i++) {
			bici[i] = new BiciclettaImpl(i);
		}
		
		return bici;
	}
	// fine metodo
	
	// metodo per inizializzare i turni della Spa
	public Spa[] spa(int giorniPermanenza) {
		
		Spa[] spa = new Spa[giorniPermanenza];
		for(int i = 0; i < spa.length; i++) {
			spa[i] = new SpaImpl ();
		}
		
		return spa;
	}
	// fine metodo
}
// fine classe