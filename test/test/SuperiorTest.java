package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import moonlightHotel.model.Superior;
import moonlightHotel.model.impl.ClienteImpl;

class SuperiorTest {

	private Superior camera = new Superior(2, 02, 3);
	
	@Test
	void testCameraStanrdard() {
		assertNotNull(camera);
	}
	
	@Test
	void testNomeCameraStandard() {
		String nome = camera.getNomeCamera();
		
		assertEquals("202", nome);
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
		
		assertEquals(480, costoCamera, 0.001);
	}
}
