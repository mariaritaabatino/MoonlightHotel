package moonlightHotel.model;

import java.util.List;

public interface Spa {
	
	// metodo per ottenere il numero dei turni
	// disponibili in un giorno di spa
	public List<String> getTurniDispGiornalieri();
	
	// metodo che cambia lo stato di un unico turno
	public void setStatoTurno(int singoloTurno);
	
	// metodo per ottenere il costo dalla Spa
	public double getCosto();
	
}
// fine classe