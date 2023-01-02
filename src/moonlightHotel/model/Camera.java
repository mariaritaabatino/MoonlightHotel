package moonlightHotel.model;
import java.util.Random;

public abstract class Camera {
	
	// attributi
	private final int numeroPiano;
	private final int numeroCamera;
	protected final int numeroPosti;
	private final String nomeCamera;
	private Random random;
	private boolean stato;		// 1 indica che la camera è libera
								// 0 indica che la camera è occupata
	
	// costruttore
	public Camera(int piano, int camera, int posti){
		this.numeroPiano	= piano;
		this.numeroCamera	= camera;
		this.numeroPosti	= posti;
		this.nomeCamera		= ""+this.numeroPiano+String.format("%02d", this.numeroCamera);
		this.random			= new Random();
		this.stato			= random.nextBoolean();
	}
	// fine costrutttore
	
	// METODI CONCRETI
	
	// metodo per ottenere il numero della stanza
	public String getNomeCamera() {
		return this.nomeCamera;
	}
	// fine metodo
	
	// metodo per settare lo stato della camera
	public void setStato(boolean nuovoStato) {
		this.stato = nuovoStato;
	}
	// fine metodo
	
	// metodo per ottenre lo stato della camera
	public boolean getStato() {
		return this.stato;
	}
	// fine metodo
	
	// metodo per ottenere il numero dei posti della camera
	public int getNumeroPosti() {
		return this.numeroPosti;
	}
	// fine metodo
	
	
	// METODI ASTRATTI
	public abstract double getCostoCamera(Cliente cliente);
	
}
//fine classe camera