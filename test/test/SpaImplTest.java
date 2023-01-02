package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import moonlightHotel.model.impl.SpaImpl;

class SpaImplTest {

	private SpaImpl spa = new SpaImpl();
	
	@Test
	void testGetTurniLiberi() {
		assertNotNull(spa);
		List<String> turni = spa.getTurniDispGiornalieri();
		
		for(int i = 0; i < turni.size(); i++) {
			spa.setStatoTurno(i);
		}
		
		assertEquals(0, spa.getTurniDispGiornalieri().size());
	}
	
	
	@Test
	void testGetCostoSpa() {
		assertEquals("Il costo non corrisponde.", 90.0, spa.getCosto(), 0.0001);
	}
	
}
