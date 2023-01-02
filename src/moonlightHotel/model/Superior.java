package moonlightHotel.model;

public class Superior extends Camera {

	// attributi
	private int costoAPersona;
	
	// costruttore
	public Superior(int piano, int camera, int posti) {
		super(piano, camera, posti);
		this.costoAPersona	= 80; 
	}	
	// fine costruttore

	// metodo per calcolare il prezzo totale delle camera
	@Override public double getCostoCamera(Cliente cliente) {
		double costoCamera;
		costoCamera = (this.costoAPersona * cliente.getNumAdulti()
				+ this.costoAPersona*cliente.getNumBambini()*(0.7))*cliente.getGiorniPermanenza();
		return costoCamera;
	}
	// fine metodo
	
}
// fine classe superior