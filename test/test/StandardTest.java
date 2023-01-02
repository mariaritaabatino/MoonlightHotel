package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import moonlightHotel.model.Standard;
import moonlightHotel.model.impl.ClienteImpl;

class StandardTest {

	private Standard camera = new Standard(1, 02, 3);
	
	@Test
	void testCameraStanrdard() {
		assertNotNull(camera);
	}
	
	@Test
	void testNomeCameraStandard() {
		String nome = camera.getNomeCamera();
		
		assertEquals("102", nome);
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
		
		assertEquals(240, costoCamera, 0.001);
	}

}
