package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import moonlightHotel.controller.AcquisisciDati;
import moonlightHotel.model.Bicicletta;
import moonlightHotel.model.Camera;
import moonlightHotel.model.Deluxe;
import moonlightHotel.model.Spa;
import moonlightHotel.model.Standard;
import moonlightHotel.model.Superior;
import moonlightHotel.model.impl.BiciclettaImpl;
import moonlightHotel.model.impl.ClienteImpl;
import moonlightHotel.model.impl.SpaImpl;

class AcquisisciDatiTest {
	
	private AcquisisciDati acquisitore = new AcquisisciDati();
	
	@Test
	void testAcquisitore() {
		assertNotNull(acquisitore);
	}
	
	@Test
	void testGetCamere() {
		
		ClienteImpl cliente = new ClienteImpl("Mario", "Rossi", 2, 1, 1);
		
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
			
		List<Camera> camereAcquisitore =  new ArrayList<Camera>();	
		camereAcquisitore = acquisitore.camere();
		
		assertEquals(camere.size(), camereAcquisitore.size());
		
		for(int i = 0; i < camere.size(); i++) {
			assertEquals(camere.get(i).getNomeCamera(), camereAcquisitore.get(i).getNomeCamera());
			assertEquals(camere.get(i).getNumeroPosti(), camereAcquisitore.get(i).getNumeroPosti());
			assertEquals("Errore", camere.get(i).getCostoCamera(cliente), camereAcquisitore.get(i).getCostoCamera(cliente), 0.001);
		}
	}
	
	@Test
	void testGetSpa() {
		
		Spa[] spa = new Spa[5];
		for(int i = 0; i < spa.length; i++) {
			spa[i] = new SpaImpl ();
		}
		
		Spa[] spaAcquisitore = acquisitore.spa(5);
		
		assertSame(spa.length, spaAcquisitore.length);
		for(int i = 0; i < spa.length; i++) {
			assertEquals(spa[i].getCosto(), spaAcquisitore[i].getCosto(), 0.001);
		}
	}
	
	@Test
	void testGetBiciclette() {
		
		Bicicletta[] bici = new Bicicletta[40];
		for(int i = 0; i < bici.length; i++) {
			bici[i] = new BiciclettaImpl(i);
		}
				
		Bicicletta[] biciAcquisitore = acquisitore.biciclette();
		
		assertEquals(bici.length, biciAcquisitore.length);
		for(int i = 0; i < bici.length; i++) {
			assertEquals(bici[i].getCodiceBici(), biciAcquisitore[i].getCodiceBici());
			assertEquals(bici[i].getCostoBicicletta(), biciAcquisitore[i].getCostoBicicletta(), 00.001);
			bici[i].setNuovoStato(false);
			biciAcquisitore[i].setNuovoStato(false);
			assertEquals(bici[i].getStato(), biciAcquisitore[i].getStato());
		}
	}
	
}
