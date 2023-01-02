package moonlightHotel.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import moonlightHotel.controller.ControllerPrenotazioni;
import moonlightHotel.controller.ControllerServizi;

@SuppressWarnings("serial")
public class ViewServizioBiciclette extends JFrame {

	// attributi
	private final String TITOLO = "Informazioni noleggio biclette";
	private ControllerServizi controllerS;
	private ControllerPrenotazioni controllerP;
	private JButton avantiButton;
	private ViewServiziCamera viewServizi;
	private List<JFrame> frameAperti;
	
	// costruttore
	public ViewServizioBiciclette(  ControllerServizi controllerS,
									ControllerPrenotazioni controllerP,
									ViewServiziCamera viewServizi){
		this.controllerS = controllerS;
		this.controllerP = controllerP;
		this.viewServizi = viewServizi;
		this.frameAperti = new ArrayList<JFrame>();
		this.Init();
		this.pack();
		this.setTitle(this.TITOLO);
		this.setSize(450, 200);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocation(200, 150);
		this.setResizable(false); 
		this.setVisible(true);
	}
	// fine costruttore
	
	// inizializzazione della GUI
	private void Init() {
		
		// dichiarazione e  inizializzazione del JPanel principale			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(20, 50, 50, 50));
			
		// dichiarazione e inizializzazione dell'area di testo che contiene info sul noleggio bici
		JTextArea infoNoleggioTextArea = new JTextArea();
		// faccio in modo che l'area di testo non sia modificabile
		infoNoleggioTextArea.setEditable(false);
		infoNoleggioTextArea.append("Le biciclette possono essere noleggiate SOLO per i\n"
				+"componenti adulti della famiglia.\n"
				+"\nIl costo del noleggio di una bici è gratuito per le camere\n"
				+ "Superior e Deluxe, mentre per le camere Standard è di "+controllerS.getCostoNoleggioSingolaBici()+"€\n"
				+ "per tutto il periodo del soggiorno.");

		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonsPanel.setFocusable(true);

		// dichiarazione e inizializzazione del JButton per andare al passaggio successivo
		JButton noleggioButton = new JButton("Procedi al noleggio"); 
		noleggioButton.addActionListener(event -> {
			this.frameAperti.add(this.sceltaNumBiciDaRiservare());
		});
		
		// dichiarazione e inizializzazione del JButton per tornare indietro
		JButton comeBackButton = new JButton("Torna indietro");
		comeBackButton.addActionListener(event -> {
			for(int i = 0; i < this.frameAperti.size(); i++) {
				this.frameAperti.get(i).setVisible(false);
			}
			this.setVisible(false);
			this.confermaComeBack();
		});
		
		// aggiungo al JPanel dei pulsunti quelli dichiarati
		buttonsPanel.add(noleggioButton, BorderLayout.LINE_END);
		buttonsPanel.add(comeBackButton, BorderLayout.LINE_START);
	
		// aggiungo al JPanel principale i JPanel che contiene info sul
		// noleggio delle bici e quello che contiene il pulsante
		mainPanel.add(infoNoleggioTextArea);
		mainPanel.add(buttonsPanel);
	
		// al frame aggiungo il JPanel principale
		this.getContentPane().add(mainPanel);		
	}
	// fine metodo
	
	// metodo per verificare che il cliente voglia effettuvamento tornare indietro
	private JFrame confermaComeBack() {

		// dichiarazione e inizializzazione del JFrame da ritornare
		JFrame frameConfermaComeBack = new JFrame();
		frameConfermaComeBack.pack();
		frameConfermaComeBack.setTitle("ATTENZIONE");
		frameConfermaComeBack.setSize(500,150);
		frameConfermaComeBack.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameConfermaComeBack.setLocationRelativeTo(null);
		frameConfermaComeBack.setResizable(false); 
		frameConfermaComeBack.setVisible(true);
		
		// dichiarazione e inizializzazione del JPanel che permette 
		// la scelta del numero di biciclette da riservare
		JPanel annullamentoPrenotazionePanel = new JPanel();
		annullamentoPrenotazionePanel.setLayout(new BoxLayout(annullamentoPrenotazionePanel, BoxLayout.Y_AXIS));
		annullamentoPrenotazionePanel.setBorder(new EmptyBorder(20, 50, 50, 50));
		annullamentoPrenotazionePanel.add(new JLabel("Se torna indietro non potrà più prenotare le biciclette."));
		annullamentoPrenotazionePanel.add(new JLabel("Vuole tornare indietro? "));
		
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setFocusable(true);
		// inizializzazione del JButton per andare al passaggio successivo
		JButton avantiButton = new JButton("Si"); 
		avantiButton.addActionListener(event -> {
			controllerS.setUtilizzoComeBackBici();
			frameConfermaComeBack.setVisible(false);
			
			if(controllerS.getUtilizzoComeBackBici() && controllerS.getUtilizzoComeBackSpa()) {
				if(controllerS.getNumeroBambini()>0) {	
					new ViewServizioBabySitting(controllerS, controllerP);
				} else {
					new ViewReview(controllerP, controllerS);
				}
			} else if (controllerS.getUtilizzoComeBackBici() && controllerS.spaPrenotata()){
				if(controllerS.getNumeroBambini()>0) {	
					new ViewServizioBabySitting(controllerS, controllerP);
				} else {
					new ViewReview(controllerP, controllerS);
				}
			} else {
				viewServizi.setVisible(true);
			}
		});
		// inizializzazione del JButton per andare al passaggio successivo
		JButton comeBackButton = new JButton("Annulla"); 
		comeBackButton.addActionListener(event -> {
			frameConfermaComeBack.setVisible(false);
			this.setVisible(true);
		});
		// aggiungo al JPanel dei pulsunti quelli dichiarati
		buttonPanel.add(comeBackButton, BorderLayout.LINE_START);
		buttonPanel.add(avantiButton, BorderLayout.LINE_END);
				
		// aggiungo al JPanel principale i JPanel che contiene info sul
		// noleggio delle bici e quello che contiene il pulsante
		annullamentoPrenotazionePanel.add(buttonPanel);
				
		// al frame aggiungo il JPanel principale
		frameConfermaComeBack.getContentPane().add(annullamentoPrenotazionePanel);
		// ritorno il JFrame
		return frameConfermaComeBack;
	}
	// fine metodo

	
	// mnetodo per far scegliere al cliente il numero di bici da riservare
	private JFrame sceltaNumBiciDaRiservare() {
		
		// dichiarazione e inizializzazione del JFrame da ritornare
		JFrame sceltaNumeroBici = new JFrame();
		sceltaNumeroBici.pack();
		sceltaNumeroBici.setTitle("Servizio noleggio biciclette");
		sceltaNumeroBici.setSize(450, 200);
		sceltaNumeroBici.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		sceltaNumeroBici.setLocation(700, 150);
		sceltaNumeroBici.setResizable(false); 
		sceltaNumeroBici.setVisible(true);
		
		// dichiarazione e inizializzazione del JPanel che permette 
		// la scelta del numero di biciclette da riservare
		JPanel servizioBiciPanel = new JPanel();
		servizioBiciPanel.setLayout(new BoxLayout(servizioBiciPanel, BoxLayout.Y_AXIS));
		servizioBiciPanel.setBorder(new EmptyBorder(20, 50, 50, 50)); 
		servizioBiciPanel.add(new JLabel("Scelga il numero di biciclette che vuole riservare: "));
	
		// dichiarazione e inizializzazione del JComboBox che 
		// contiene i codici delle bici riservate 
		JComboBox<String> opzioniNumeriCombo = new JComboBox<String>();
		for(int i = 0; i < controllerS.getNumeroAdulti(); i++) {
			opzioniNumeriCombo.addItem(String.valueOf(i+1));
		}
		
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setFocusable(true);
		// inizializzazione del pulsante
		avantiButton = new JButton("Conferma"); 
		avantiButton.addActionListener(event -> {
			sceltaNumeroBici.setVisible(false);
			this.setVisible(false);
			// seleziono i codici delle biciclette
			controllerS.codiBiciDaRiservare(opzioniNumeriCombo.getSelectedIndex());
			
			this.nolaggiaBici();
		});
		
		// aggiungo al JPanel dei pulsanti i JButton
		buttonPanel.add(avantiButton);
			
		// aggiungo al JPanel principale i JPanel che acquisisce i dati del
		// cliente e quello che contiene il pulsante
		servizioBiciPanel.add(opzioniNumeriCombo);
		servizioBiciPanel.add(buttonPanel);
		
		// al frame aggiungo il JPanel principale
		sceltaNumeroBici.getContentPane().add(servizioBiciPanel);
		
		// ritorno il JFrame
		return sceltaNumeroBici;
	}
	// fine metodo
	
	// metodo per tiservare le bici e ottenere i codici di quelle riservate
	private JFrame nolaggiaBici() {
		
		// dichiarazione e inizializzazione del JFrame da ritornare
		JFrame frameNoleggio = new JFrame();
		frameNoleggio.pack();
		frameNoleggio.setTitle("Servizio noleggio biciclette");
		frameNoleggio.setSize(400, 200);
		frameNoleggio.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameNoleggio.setLocationRelativeTo(null);
		frameNoleggio.setResizable(false); 
		frameNoleggio.setVisible(true);
		
		// dichiarazione e inizializzazione dell'area di testo che contiene l'elenco
		// dei codici delle biciclette riservate
		JTextArea elencoBiciRiservate = new JTextArea();
		// rendo il testo non modificabile
		elencoBiciRiservate.setEditable(false);
		for(int i = 0; i < controllerS.getCodiBiciDaRiservare().size(); i++)
			elencoBiciRiservate.append(String.valueOf(controllerS.getCodiBiciDaRiservare().get(i).getCodiceBici())+"\n");
			
		// dichiarazione e inizializzazione del JPanel che contiene i codici delle bici riservate
		JPanel servizioBiciPanel = new JPanel();
		servizioBiciPanel.setLayout(new BoxLayout(servizioBiciPanel, BoxLayout.Y_AXIS));
		servizioBiciPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		servizioBiciPanel.add(new JLabel("I codici delle biciclette riservate per lei sono: "));
		servizioBiciPanel.add(elencoBiciRiservate);

		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setFocusable(true);
		// inizializzazione del pulsante per andare al passo suuccessivo
		avantiButton = new JButton("Ok"); 
		avantiButton.addActionListener(event -> {
			frameNoleggio.setVisible(false);
			this.setVisible(false);
			
			// aggiungo il costo del noleggio alla camere Standard
			if(controllerP.getTipologiaCamera(controllerP.getCameraScelta()).equals("Standard")) {
				controllerS.aggiungiCostoServizi(controllerS.getCostoBici(controllerS.getCodiBiciDaRiservare().size()));
			}
			
			// conclusa la procedura scelgo quale gui mostrare
			if(controllerS.spaPrenotata() && controllerS.getNumeroBambini() > 0) {
				new ViewServizioBabySitting(controllerS, controllerP);
			}else if(controllerS.spaPrenotata()) {
				new ViewReview(controllerP, controllerS);
			}else if(controllerS.getUtilizzoComeBackSpa()){
				new ViewReview(controllerP, controllerS);
			}else {
				viewServizi.setVisible(true);
			}
		});
		// aggiungo al JPanel dei pulsunti quelli dichiarati
		buttonPanel.add(avantiButton);
			
		// aggiungo al JPanel principale il JPanle che contiene il pulsante
		servizioBiciPanel.add(buttonPanel);
		
		// al frame aggiungo il JPanel principale
		frameNoleggio.getContentPane().add(servizioBiciPanel);
		
		// ritorno il JFrame
		return frameNoleggio;
	}
	// fine metodo
	
}
// fine classe