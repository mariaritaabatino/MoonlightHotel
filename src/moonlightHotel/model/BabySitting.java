package moonlightHotel.model;

public interface BabySitting {
	
	// metodo per aggiornare i posti rimanenti nell'area bimbi
	void setNuoviDatiBabySitting(int nuoviBambini); 
	
	// metodo pubblico per ottenere i posti rimanenti nell'area bimbi
	int getPostiRimanenti();

}
// fine interfaccia