package moonlightHotel.controller;
import java.util.ArrayList;
import java.util.List;

import moonlightHotel.model.Camera;
import moonlightHotel.model.Standard;
import moonlightHotel.model.Superior;
import moonlightHotel.model.impl.ClienteImpl;

public class ControllerPrenotazioni {

	// ATTRIBUTI
	private AcquisisciDati acquisitore;	
	private List<Camera> camere;
	private double costoSoggiorno;
	private List<Camera> camereLibere;
	private List<Camera> camereAdatte;
	private List<String> descrizioneCamereAdatte;
	private Camera cameraScelta;
	private ClienteImpl cliente;
	
	// COSTRUTTORE
	public ControllerPrenotazioni() {
		this.acquisitore	= new AcquisisciDati();
		this.camere			= acquisitore.camere();
		this.camereLibere	= new ArrayList<Camera>();
		this.camereAdatte	= new ArrayList<Camera>();
		this.descrizioneCamereAdatte	= new ArrayList<String>();
		this.costoSoggiorno	= 0;
	}
	// fine costruttore
	
	// METODI
	// metodo per settare i dati del cliente
	public void setCliente(String nome, String cognome, int adulti, int bambini, int permanenza) {
		this.cliente = new ClienteImpl(nome, cognome, adulti, bambini, permanenza);
	}
	// fine metodo
	
	// metodo per ottenere il cliente
	public ClienteImpl getCliente() {
		return this.cliente;
	}
	// fine metodo
	
	// metodo per ottenere il nome della camera
	public String getNomeCamera(Camera camera) {
		return camera.getNomeCamera();
	}
	// fine metodo
	
	// metodo che verifica se ci sono camere libere non considerando il budget
	public void checkCamereLibere() {
		
		// cerco le camere libere
		for(int i = 0; i < this.camere.size(); i++) {
			if(this.camere.get(i).getStato()) {
				this.camereLibere.add(this.camere.get(i));
			}
		}
		// fine for
	}
	// fine metodo
	
	// metodo per ottenere l'elenco delle camere libere
	public List<Camera> getCamereLibere(){
		return this.camereLibere;
	}
	// fine metodo
	
	// metodo che verifica se ci sono camere libere che rienstrano nel budget
	public void checkCamereAdatte(){
		
		String descrizione;
		
		// svuoto la lista delle camere adatte a quella delle descrizioni 
		this.camereAdatte.clear();
		this.descrizioneCamereAdatte.clear();
		
		// cerco le camere
		for(int i = 0; i < this.camereLibere.size(); i++) {
			if( (this.camereLibere.get(i).getNumeroPosti() >= this.cliente.getNumCompFam()) ) {
				this.camereAdatte.add(this.camereLibere.get(i));
				descrizione = this.getTipologiaCamera(this.camereLibere.get(i))
						+" "+this.camereLibere.get(i).getNomeCamera()
						+" a "
						+this.camereLibere.get(i).getCostoCamera(this.cliente)
						+"€";
				this.descrizioneCamereAdatte.add(descrizione);
			} // fine if
		} // fine for
		
	}
	// fine metodo
	
	// metodo per ottenere la descrizione delle camere adatte
	public List<String> getDescrizioneCamereAdatte(){
		return this.descrizioneCamereAdatte;
	}
	// fine metodo
	
	// metodo per ottenere la lista delle camere adatte
	public List<Camera> getCamereAdatte(){
		return this.camereAdatte;
	}
	// fine metodo

	// metodo che aggiorna il costo del soggiorno e salva la camera scelta
	public void aggiungiCostoCamera(Camera camera) {
		this.setCostoSoggiorno(camera.getCostoCamera(this.cliente));
		this.cameraScelta = camera;
	}
	// fine metodo
	
	// metodo per ottenere la tipologia di camera scelta
	public String getTipologiaCamera (Camera camera) {
		String tipologia;
		
		if (camera instanceof Standard)
			tipologia = "Standard";
		else if (camera instanceof Superior)
			tipologia = "Superior";
		else
			tipologia = "Deluxe";
			
		return tipologia;	
	}
	// fine metodo
	
	// metodo per ottenere il costo di una camera
	public double getCostoCamera(Camera camera) {
		return camera.getCostoCamera(this.cliente);
	}
	// fine metodo
	
	// metodo pubblico per riservare la camera scelta
	public void riservaCameraScelta() {
		this.cameraScelta.setStato(false);
	}
	// fine metodo
	
	// metodo per ottenere la camera riservata 
	public Camera getCameraScelta() {
		return this.cameraScelta;
	}
	
	// metodo per settare il costo del soggiorno
	public void setCostoSoggiorno(double nuovaSpesa) {
		this.costoSoggiorno += nuovaSpesa;
	}
	// fine metodo 
	
	// metodo per settare a zero il costo del soggiorno
	public void azzeraCostoSoggiorno() {
		this.costoSoggiorno = 0;
	}
	// fine metodo
	
	// metodo per ottenere il costo del soggiorno
	public double getCostoSoggiorno() {
		return this.costoSoggiorno;
	}
	// fine metodo
}
// fine classe