package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import moonlightHotel.model.impl.BiciclettaImpl;

class BiciclettaImplTest {

	private BiciclettaImpl bicicletta = new BiciclettaImpl(18);
	
	@Test
	void testBicicletta() {
		assertNotNull(bicicletta);
	}
	
	@Test
	void testGetCodiceBicicletta() {
		assertEquals("Errore", 19, bicicletta.getCodiceBici(), 0);
	}
	
	@Test
	void testSetEGetStatoBicicletta() {
		assertTrue(bicicletta.getStato());
		bicicletta.setNuovoStato(false);
		assertFalse(bicicletta.getStato());
	}
	
	@Test
	void testGetCostoBicicletta() {
		assertEquals("Il costo non corrisponde.", 10.0, bicicletta.getCostoBicicletta(), 0.0001);
	}
	
	
	
}
