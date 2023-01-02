package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import moonlightHotel.model.impl.BabySittingImpl;

class BabySittingImplTest {

	private BabySittingImpl areaBimbi = new BabySittingImpl();
	
	@Test
	void setNuoviDatiEPostiRimanentiTest() {
		assertNotNull(areaBimbi);
		
		int postiLiberi = areaBimbi.getPostiRimanenti();
		areaBimbi.setNuoviDatiBabySitting(2);
		assertEquals((postiLiberi-2), areaBimbi.getPostiRimanenti());
		assertNotEquals(postiLiberi, areaBimbi.getPostiRimanenti());
	}
	
	
}
