package moonlightHotel.model;

public interface Bicicletta {

	// metodo per ottenere il codice della bicicletta
	public int getCodiceBici();
	
	// metodo per settare lo stato della biclicetta
	public void setNuovoStato(boolean nuovoStato);
	
	// metodo per ottenere lo stato della bicicletta
	public boolean getStato();
	
	// metodo per ottenere il costo del noleggio di una bicicletta
	public double getCostoBicicletta();
}
// fine classe