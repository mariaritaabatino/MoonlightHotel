package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;

import moonlightHotel.controller.ControllerPrenotazioni;
import moonlightHotel.model.Deluxe;
import moonlightHotel.model.Standard;
import moonlightHotel.model.Superior;
import moonlightHotel.model.impl.ClienteImpl;

class ControllerPrenotazioniTest {

	private ControllerPrenotazioni cP = new ControllerPrenotazioni();
	
	@Test
	void testControllerPrenotazioni() {
		assertNotNull(cP);
	}
	
	@Test
	void testSetEGetCliente() {
		ClienteImpl cliente = new ClienteImpl("Mario", "Rossi", 2, 0, 2);
		
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		assertEquals(cliente.getNome(), cP.getCliente().getNome());
		assertEquals(cliente.getCognome(), cP.getCliente().getCognome());
		assertEquals(cliente.getNumAdulti(), cP.getCliente().getNumAdulti());
		assertEquals(cliente.getNumBambini(), cP.getCliente().getNumBambini());
		assertEquals(cliente.getNumCompFam(), cP.getCliente().getNumCompFam());
		assertEquals(cliente.getGiorniPermanenza(), cP.getCliente().getGiorniPermanenza());
	}
	
	@Test
	void testGetNomeCamera() {
		Standard camera = new Standard(1, 02, 4);
		String nomeCamera = "102";
		
		assertEquals(nomeCamera, cP.getNomeCamera(camera));
	}
	
	@Test
	void testGetTipologiaCamera() {

		Standard camera1 = new Standard(1, 02, 4);
		Superior camera2 = new Superior(2, 02, 4);
		Deluxe camera3 = new Deluxe(3, 02, 4);

		assertSame("Standard", cP.getTipologiaCamera(camera1));
		assertSame("Superior", cP.getTipologiaCamera(camera2));
		assertSame("Deluxe", cP.getTipologiaCamera(camera3));
	}
	
	@Test
	void testGetCostoCamera() {
		
		Deluxe camera = new Deluxe(3, 02, 4);
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		
		double costoSoggiorno = 130*3*2;
		
		double costoCamera = cP.getCostoCamera(camera);
		
		assertEquals("Calcolo non corretto", costoSoggiorno, costoCamera, 0.001);
	}
	
	@Test
	void testRiservaCameraScelta() {

		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		Deluxe camera = new Deluxe(3, 02, 4);
		camera.setStato(true);
		
		cP.aggiungiCostoCamera(camera);
		
		cP.riservaCameraScelta();
		assertFalse(camera.getStato());
	}
	
	@Test
	void testGetCameraScelta() {
		
		Deluxe camera = new Deluxe(3, 02, 4);
		camera.setStato(true);
		cP.setCliente("Mario", "Rossi", 2, 0, 1);
		
		cP.aggiungiCostoCamera(camera);
		
		assertEquals(camera, cP.getCameraScelta());
	}
	
	@Test
	void testSetCostoSoggiorno() {
		double costo = cP.getCostoSoggiorno();
		double nuovaSpesa = 10;
		
		double costoAggiornato = costo + nuovaSpesa;
		cP.setCostoSoggiorno(nuovaSpesa);
		
		double nuovoCosto = cP.getCostoSoggiorno();
		assertEquals("Differenza", costoAggiornato, nuovoCosto, 0.001);
	}
	
	@Test
	void testAzzeraCostoSoggiorno() {
		cP.azzeraCostoSoggiorno();
		assertEquals("Costo non azzerato", 0, cP.getCostoSoggiorno(), 0.001);
	}
}
