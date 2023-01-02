package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import moonlightHotel.model.impl.ClienteImpl;

class ClienteImplTest {
	
	private ClienteImpl cliente = new ClienteImpl("Mario", "Rossi", 2, 0, 2);
	
	@Test
	void testNomeClienteImpl() {
		assertNotNull(cliente);
		assertEquals("Mario", cliente.getNome());
		assertNotEquals("uytviu", cliente.getNome());
		assertNotEquals(1, cliente.getNome());
	}
	
	@Test
	void testCognomeClienteImpl() {
		assertEquals("Rossi", cliente.getCognome());
		assertNotEquals("Mario", cliente.getCognome());
		assertNotEquals(32, cliente.getCognome());
	}
	
	@Test
	void testNumAdultiClienteImpl() {
		assertNotEquals("Mario", cliente.getNumAdulti());
		assertEquals(3, cliente.getNumAdulti());
	}
	
	@Test
	void testNumBambiniClienteImpl() {
		assertEquals(0, cliente.getNumBambini());
		assertNotEquals("eegerg", cliente.getNumBambini());
		assertNotEquals(2, cliente.getNumBambini());
	}
	
	@Test
	void testNumTotaleClienteImpl() {
		assertEquals(2, cliente.getNumCompFam());
		assertNotEquals("eegerg", cliente.getNumCompFam());
	}
	
	@Test
	void testPermanenzaClienteImpl() {
		assertEquals(2, cliente.getGiorniPermanenza());
		assertNotEquals("eegerg", cliente.getGiorniPermanenza());
		assertNotEquals(0, cliente.getGiorniPermanenza());
	}
	
}
