package moonlightHotel.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import moonlightHotel.model.Bicicletta;
import moonlightHotel.model.Cliente;
import moonlightHotel.model.Spa;
import moonlightHotel.model.impl.BabySittingImpl;


public class ControllerServizi {

	// attributi
	private Cliente cliente;
	private ControllerPrenotazioni controllerP;
	private LocalDate oggi;
	private AcquisisciDati acquisisci;
	private Bicicletta[] biciclette;
	private boolean biciPrenotate;
	private boolean utilizzoComeBackBici;
	private List<Bicicletta> biciDaRiservare;
	private Spa[] spa;
	private List<Spa> giorniDisponibili;
	private int indiceGiornoScelto;
	private LocalDate dataGiornoScelto;
	private int turnoScelto;
	private boolean spaPrenotata;
	private boolean utilizzoComeBackSpa;
	private BabySittingImpl babySitting;
	
	// costruttore
	public ControllerServizi(ControllerPrenotazioni controllerP) {
		this.cliente		 = controllerP.getCliente();
		this.controllerP	 = controllerP;
		this.oggi			 = LocalDate.now();
		this.acquisisci 	 = new AcquisisciDati();
		this.biciclette		 = this.acquisisci.biciclette();
		this.biciDaRiservare = new ArrayList<Bicicletta>();
		this.biciPrenotate	 = false;
		this.utilizzoComeBackBici	= false;
		this.spa			 	  	= this.acquisisci.spa(this.cliente.getGiorniPermanenza());
		this.giorniDisponibili 	 	= new ArrayList<Spa>();
		this.spaPrenotata	 	 	= false;
		this.utilizzoComeBackSpa 	= false;
		this.babySitting	 	 	= new BabySittingImpl();
	}
	// fine costruttore
	
	
	// metodo per riservare N biciclette
	public void codiBiciDaRiservare(int biciDaRiservare) {
		
		int biciRiservate = 0;
		int i = 0;
		
		while(biciRiservate <= biciDaRiservare) {
			if(this.biciclette[i].getStato()) {
				this.biciDaRiservare.add(this.biciclette[i]);
				biciRiservate ++;
				this.biciPrenotate = true;
			}
			i++;
		}// fine while
		
	}	
	// fine metodo
	
	// metodo per ottenere i codi delle bici da riservare
	public List<Bicicletta> getCodiBiciDaRiservare(){
		return this.biciDaRiservare;
	}
	// fine metodo
	
	// metodo per sapere se il turno della spa è stato prenotato
	public boolean biciPrenotate() {
		return this.biciPrenotate;
	}
	// fine metodo
	
	// metodo pubblico per riservare i turni delle bici
	public void riservaBici() {
		for(int i = 0; i < this.biciDaRiservare.size(); i++) {
			this.biciDaRiservare.get(i).setNuovoStato(false);
		}
	}
	// fine metodo
	
	// metodo per ottenere i turni libere della spa
	public List<String> getTurniLiberi(){

		List<String> turniSpa = new ArrayList<String>();
		
		for(int i = 0; i < this.cliente.getGiorniPermanenza(); i++) {
			if(this.spa[i].getTurniDispGiornalieri().size() > 0) {
				turniSpa.add("Nel giorno "+String.valueOf(this.oggi.plusDays(i))+" sono:\n");
				for(int j = 0; j < this.spa[i].getTurniDispGiornalieri().size(); j++) {
					turniSpa.add("\t"+this.spa[i].getTurniDispGiornalieri().get(j)+"\n");
				}
				turniSpa.add("\n");
			}
		}
		
		return turniSpa;
	}
	// fine metodo
	
	// metodo per ottenere i giorni con i turni liberi
	public List<LocalDate> getGiorniConTurniLiberi(){
		
		List<LocalDate> giorniTurniDisponibili = new ArrayList<LocalDate>();
		for(int i = 0; i < this.cliente.getGiorniPermanenza(); i++) {
			if(this.spa[i].getTurniDispGiornalieri().size() > 0) {
				giorniTurniDisponibili.add(this.oggi.plusDays(i));
			}
		}
		
		return giorniTurniDisponibili;
	}
	// fine metodo
	
	// metodo per ottenere i turni disponibili di un unico giorno
	public List<String> getTurniSingoloGiorno(int giornoScelto){
		
		this.listaGiorniDisponibili();	
		
		return this.giorniDisponibili.get(giornoScelto).getTurniDispGiornalieri();
	}
	// fine metodo
	
	// metodo privato per ottenere una lista dei giorni con i turni liberi della spa
	private void listaGiorniDisponibili(){
		
		for(int i = 0; i < this.cliente.getGiorniPermanenza(); i++) {
			if(this.spa[i].getTurniDispGiornalieri().size() > 0) {
				this.giorniDisponibili.add(this.spa[i]);
			}
		}		
	}
	// fine metodo
	
	// metodo per modificare lo stato di un turno spa
	public void setStatoTurnoScelto() {
		this.giorniDisponibili.get(this.indiceGiornoScelto).setStatoTurno(this.turnoScelto);
	}
	// fine metodo
	
	// metodo per salvare il giorno e il turno scelto 
	// dal cliente per accedere alla spa
	public void setTurnoScelto(LocalDate dataGiornoScelto, int indiceGiornoScelto, int turnoScelto) {
		this.dataGiornoScelto	= dataGiornoScelto;
		this.indiceGiornoScelto	= indiceGiornoScelto;
		this.turnoScelto		= turnoScelto;
		this.spaPrenotata		= true;
	}
	// fine metodo
	
	// metodo per sapere se il turno della spa è stato prenotato
	public boolean spaPrenotata() {
		return this.spaPrenotata;
	}
	// fine metodo
	
	// metodo per ottenere il giorno e il turno scelto 
	// dal cliente per accedere alla spa
	public String getTurnoScelto() {
		
		String descrizione = "Il turno spa è stato riservato nel giorno "
				+this.dataGiornoScelto+" nel turno "+(this.turnoScelto+1)+"°.";
		
		return descrizione;
	}
	// fine metodo
	
	// metodo per settare l'ultizzo del pulsante di comeBack durante il noleggio bici
	public void setUtilizzoComeBackSpa() {
		this.utilizzoComeBackSpa = true;
	}
	// fine metodo
	
	// metodo per ottenere l'ultizzo del pulsante di comeBack durante il noleggio bici
	public boolean getUtilizzoComeBackSpa() {
		return this.utilizzoComeBackSpa;
	}
	// fine metodo
	
	// metodo per ottenere il costo del noleggio delle bici 
	public double getCostoNoleggioSingolaBici() {
		return  biciclette[0].getCostoBicicletta();
	}
	// fine metodo
	
	// metodo per ottenere il costo di tutte le biciclette noleggiate
	public double getCostoBici(int biciNoleggiate) {
		return this.getCostoNoleggioSingolaBici()*(biciNoleggiate);
	}
	// fine metodo
	
	// metodo per settare l'ultizzo del pulsante di comeBack durante il noleggio bici
	public void setUtilizzoComeBackBici() {
		this.utilizzoComeBackBici = true;
	}
	// fine metodo
	
	// metodo per ottenere l'ultizzo del pulsante di comeBack durante il noleggio bici
	public boolean getUtilizzoComeBackBici() {
		return this.utilizzoComeBackBici;
	}
	// fine metodo
	
	// metodo pubblico per ottenere il costo della spa
	public double getCostoSpa() {
		return this.spa[0].getCosto();
	}
	// fine metodo
	
	
	// metodo pubblico per aggiungere al costo della prenotazioni
	// il prezzzo dei servizi aggiuntivi
	public void aggiungiCostoServizi(double nuovoPagamento) {
		this.controllerP.setCostoSoggiorno(nuovoPagamento);
	}
	// fine metodo
	
	// metodo pubblico per ottenere il numero degli adulti della prenotazione
	public int getNumeroAdulti() {
		return this.cliente.getNumAdulti();
	}
	// fine metodo
	
	// metodo pubblico per ottenere il numero degli adulti della prenotazione
	public int getNumeroBambini() {
		return this.cliente.getNumBambini();
	}
	// fine metodo
	
	// metodo pubblico per verificare che ci siano sufficienti posti liberi nell'area bimbi
	public boolean controllaDisponibilitaAreaBimbi() {
		// attributi
		boolean stato = false;
		
		if(this.getNumeroBambini() <= babySitting.getPostiRimanenti()) {
			stato = true;
		}
		
		return stato;
	}
	// fine metodo
	
	// metodo per riservare i posti nell'area
	public void riservaPostiAreaBimbi() {
		babySitting.setNuoviDatiBabySitting(this.getNumeroBambini());
	}
}
// fine classe