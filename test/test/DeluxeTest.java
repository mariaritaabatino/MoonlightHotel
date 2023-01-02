package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import moonlightHotel.model.Deluxe;
import moonlightHotel.model.impl.ClienteImpl;

class DeluxeTest {
	
	private Deluxe camera = new Deluxe(3, 02, 3);
	
	@Test
	void testCameraStanrdard() {
		assertNotNull(camera);
	}
	
	@Test
	void testNomeCameraStandard() {
		String nome = camera.getNomeCamera();
		
		assertEquals("302", nome);
	}
	
	@Test
	void testSetEGetStato() {
		camera.setStato(false);
		
		assertFalse(camera.getStato());
	}
	
	@Test
	void testGetNumeroPosti() {
		int numeroPosti = camera.getNumeroPosti();
		
		assertEquals(3, numeroPosti);
	}
	
	@Test
	void testGetCostoCamera() {
		ClienteImpl cliente = new ClienteImpl("Mario", "Rossi", 2, 0, 2);
		double costoCamera = camera.getCostoCamera(cliente);
		double costo = 130 * 3 * 2;
		
		assertEquals(costo, costoCamera, 0.001);
	}
}
