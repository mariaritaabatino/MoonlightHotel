package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import moonlightHotel.controller.ControllerPrenotazioni;
import moonlightHotel.controller.ControllerServizi;

class ControllerServiziTest {

	private ControllerPrenotazioni cP = new ControllerPrenotazioni();
		
	
	@Test
	void testControllerServizi() {
		assertNotNull(cP);
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		ControllerServizi cS = new ControllerServizi(cP);
		assertNotNull(cS);
	}
	
	@Test
	void testComeBackSpa() {
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		ControllerServizi cS = new ControllerServizi(cP);
		
		assertFalse(cS.getUtilizzoComeBackSpa());	
		cS.setUtilizzoComeBackSpa();
		assertTrue(cS.getUtilizzoComeBackSpa());
	}
	
	
	@Test
	void testComeBackBici() {
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		ControllerServizi cS = new ControllerServizi(cP);
		
		assertFalse(cS.getUtilizzoComeBackBici());	
		cS.setUtilizzoComeBackBici();
		assertTrue(cS.getUtilizzoComeBackBici());
	}
	
	@Test
	void testGetCostoSpa() {
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		ControllerServizi cS = new ControllerServizi(cP);
		
		double costoSpa = cS.getCostoSpa();
		assertEquals(90, costoSpa, 0.001);
	}
	
	@Test
	void testGetCostoNoleggioBici() {
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		ControllerServizi cS = new ControllerServizi(cP);
	
		double costoBici = cS.getCostoNoleggioSingolaBici();
		assertEquals("Errore", 10, costoBici, 0.001);
		
		double costoQuattroBici = cS.getCostoBici(4);
		assertEquals("Errore", 40, costoQuattroBici, 0.001);
	}
	
	@Test
	void testAggiungiCostoServizi() {
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		ControllerServizi cS = new ControllerServizi(cP);
		
		double costoAttuale = cP.getCostoSoggiorno();
		
		cS.aggiungiCostoServizi(100);
		double costoAggiornato = costoAttuale + 100;
		
		assertEquals("Errore", costoAggiornato, cP.getCostoSoggiorno(), 0.001);
	}
	
	@Test
	void testCliente() {
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		ControllerServizi cS = new ControllerServizi(cP);
		
		int numAdulti = cS.getNumeroAdulti();
		assertEquals(3, numAdulti);
		
		int numBambini = cS.getNumeroBambini();
		assertEquals(0, numBambini);	
	}
	
	@Test
	void testDisponibilitaAreaBimbi() {
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		ControllerServizi cS = new ControllerServizi(cP);
		
		assertTrue(cS.controllaDisponibilitaAreaBimbi());
	}
	
}
