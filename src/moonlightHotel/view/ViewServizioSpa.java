package moonlightHotel.view;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import moonlightHotel.controller.ControllerPrenotazioni;
import moonlightHotel.controller.ControllerServizi;

@SuppressWarnings("serial")
public class ViewServizioSpa extends JFrame{

	// attributi
	private final String TITOLO = "Riserva turno spa";
	private ControllerServizi controllerS;
	private ControllerPrenotazioni controllerP;
	private ViewServiziCamera viewServizi;
	private JButton turniLiberiButton;
	private JButton sceltaGiornoButton;
	private JButton sceltaTurnoButton;
	private JComboBox<LocalDate> giorniLiberiSpaCombo;
	private JComboBox<String> turniLiberiSpaCombo;
	private List<JFrame> frameAperti;
	
	
	// costruttore
	public ViewServizioSpa( ControllerServizi controllerS,
							ControllerPrenotazioni controllerP,
							ViewServiziCamera viewServizi){
		this.controllerS  = controllerS;
		this.controllerP  = controllerP;
		this.viewServizi  =	viewServizi;
		this.frameAperti  = new ArrayList<JFrame>();
		this.Init();
		this.pack();
		this.setTitle(this.TITOLO);
		this.setSize(650, 300);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocation(50,150);
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
		
		// dichiarazione e inizializzazione dell'area di testo che contiene info sulla spa				
		JTextArea infoSpaTextArea = new JTextArea();
		// faccio in modo che l'area di testo non sia modificabile
		infoSpaTextArea.setEditable(false);
		infoSpaTextArea.append("La nostra spa è una spa privata, "
				+ "sarà riservata per l'intero nucleo familiare al prezzo di "+ controllerS.getCostoSpa()+"€\n"
				+ "per le camere Standard e Superior, mentre per le camere Deluxe è incluso.\n"
				+"\nL'accesso alla spa è regolato da turni 5 di un'ora, "
				+ "che iniziano alle 15 e terminano alle 20.\n"
				+"\nIn particolare:\n"
				+ "- 1° turno dalle ore 15 alle ore 16;\n"
				+ "- 2° turno dalle ore 16 alle ore 17;\n"
				+ "- 3° turno dalle ore 17 alle ore 18;\n"
				+ "- 4° turno dalle ore 18 alle ore 19;\n"
				+ "- 5° turno dalle ore 19 alle ore 20.\n");
		
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonsPanel.setFocusable(true);
		
		// inizializzazione del JButton per andare al passaggio successivo
		turniLiberiButton = new JButton("Turni liberi"); 
		turniLiberiButton.addActionListener(event -> {
			this.frameAperti.add(this.sceltaGiorno());
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
		buttonsPanel.add(turniLiberiButton, BorderLayout.LINE_END);
		buttonsPanel.add(comeBackButton, BorderLayout.LINE_START);
			
		// aggiungo al JPanel principale L'area di testo 
		// e il JPanel che contiene i pulsanti	
		mainPanel.add(infoSpaTextArea);
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
		annullamentoPrenotazionePanel.add(new JLabel("Se torna indietro non potrà più prenotare la spa."));
		annullamentoPrenotazionePanel.add(new JLabel("Vuole tornare indietro? "));
		
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setFocusable(true);
		// inizializzazione del JButton per andare al passaggio successivo
		JButton avantiButton = new JButton("Si"); 
		avantiButton.addActionListener(event -> {
			controllerS.setUtilizzoComeBackSpa();
			frameConfermaComeBack.setVisible(false);
			
			if(controllerS.getUtilizzoComeBackBici() && controllerS.getUtilizzoComeBackSpa()) {
				if(controllerS.getNumeroBambini()>0) {	
					new ViewServizioBabySitting(controllerS, controllerP);
				} else {
					new ViewReview(controllerP, controllerS);
				}
			} else if (controllerS.getUtilizzoComeBackSpa() && controllerS.biciPrenotate()){
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
	
	// metodo privato per far scegliere il giorno della spa
	private JFrame sceltaGiorno() {
		
		// dichiarazione e inizializzazione del JFrame da ritornare
		JFrame frameSceltaGiorno = new JFrame();
		frameSceltaGiorno.pack();
		frameSceltaGiorno.setTitle("Scelta giorno spa");
		frameSceltaGiorno.setSize(500, 400);
		frameSceltaGiorno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameSceltaGiorno.setLocation(700,150);
		frameSceltaGiorno.setResizable(false); 
		frameSceltaGiorno.setVisible(true);
	
		// dichiarazione e inizializzazione della lista di tutti i turni liberi
		List<String> turniSpa = controllerS.getTurniLiberi();
		// dichiarazione e inizializzazione dell'area di testo che contiene l'elenco dei turni liberi
		JTextArea turniSpaTextArea = new JTextArea();
		turniSpaTextArea.setEditable(false);
		for(int i = 0; i < turniSpa.size(); i++) {
			turniSpaTextArea.append(turniSpa.get(i));
		}
		// dichiarazione e inizializzazione della sbarra per scorrere nell'area di testo 
		JScrollPane scrollPaneSpa = new JScrollPane(turniSpaTextArea);
		scrollPaneSpa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneSpa.setBounds(900, 900, 900, 900);
		
		// dichiarazione e inizializzazione della lista dei giorni con turni liberi
		List<LocalDate> giorniConTurniLiberi = controllerS.getGiorniConTurniLiberi();
		// inizializzazione della JCOmboBox con la lista dei giorni liberi
		giorniLiberiSpaCombo = new JComboBox<LocalDate>();
		for(int i = 0; i < giorniConTurniLiberi.size(); i++) {
			giorniLiberiSpaCombo.addItem(giorniConTurniLiberi.get(i));
		}
		
		// dichiarazione e inizializzazione del JPanel che permette 
		// la scelta del del giorno in cui riservare il turno nella spa
		JPanel sceltaGiornoPanel = new JPanel();
		sceltaGiornoPanel.setLayout(new BoxLayout(sceltaGiornoPanel, BoxLayout.Y_AXIS));
		sceltaGiornoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		sceltaGiornoPanel.add(new JLabel("I turni della spa sono: "));
		sceltaGiornoPanel.add(scrollPaneSpa);
		sceltaGiornoPanel.add(new JLabel("Scegliere il giorno in cui si vuole riservare il turno: "));
		sceltaGiornoPanel.add(giorniLiberiSpaCombo);
				
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonSceltaGiornoPanel = new JPanel();
		buttonSceltaGiornoPanel.setLayout(new BoxLayout(buttonSceltaGiornoPanel, BoxLayout.Y_AXIS));
		buttonSceltaGiornoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonSceltaGiornoPanel.setFocusable(true);
		// inizializzazione del pulsante
		sceltaGiornoButton = new JButton("Conferma giorno scelto"); 
		sceltaGiornoButton.addActionListener(event -> {
			frameSceltaGiorno.setVisible(false);
			
			int indiceGiornoScelto = giorniLiberiSpaCombo.getSelectedIndex();
			LocalDate dataGiornoScelto = (LocalDate) giorniLiberiSpaCombo.getSelectedItem();
			this.frameAperti.add(this.sceltaTurno(indiceGiornoScelto, dataGiornoScelto));
		});
		buttonSceltaGiornoPanel.add(sceltaGiornoButton);
			
		// aggiungo al JPanel principale il JPanel che contiene il pulsante
		sceltaGiornoPanel.add(buttonSceltaGiornoPanel);
		
		// al frame aggiungo il JPanel principale
		frameSceltaGiorno.getContentPane().add(sceltaGiornoPanel);
	
		// ritorno il JFrame
		return frameSceltaGiorno;	
	}
	// fine metodo 
	
	// metodo privato per far scegliere il giorno della spa
	private JFrame sceltaTurno(int indiceGiornoScelto, LocalDate dataGiornoScelto) {
			
		// dichiarazione e inizializzazione del JFrame da ritornare
		JFrame frameSceltaTurno = new JFrame();
		frameSceltaTurno.pack();
		frameSceltaTurno.setTitle("Scelta turno spa");
		frameSceltaTurno.setSize(450, 200);
		frameSceltaTurno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameSceltaTurno.setLocation(700,150);
		frameSceltaTurno.setResizable(false); 
		frameSceltaTurno.setVisible(true);
	
		// dichiarazione e inizializzazione del JPanel principale
		JPanel sceltaTurnoPanel = new JPanel();
		sceltaTurnoPanel.setLayout(new BoxLayout(sceltaTurnoPanel, BoxLayout.Y_AXIS));
		sceltaTurnoPanel.setBorder(new EmptyBorder(20, 50, 50, 50));
		
		// dichiarazione e inizializzazione della lista dei turni liberi nel giorno scelto
		List<String> turniSpa = controllerS.getTurniSingoloGiorno(indiceGiornoScelto);
		// inizializzazione della JCOmboBox con la lista dei turni liberi
		turniLiberiSpaCombo = new JComboBox<String>();
		for(int i = 0; i < turniSpa.size(); i++) {
			turniLiberiSpaCombo.addItem(turniSpa.get(i));
		}
		
		// dichiarazioen del JPanel che fa scegliere il turno da riservare 
		JPanel servizioSpaPanel = new JPanel();
		servizioSpaPanel.setLayout(new BoxLayout(servizioSpaPanel, BoxLayout.Y_AXIS));
		servizioSpaPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		servizioSpaPanel.add(new JLabel("I turni liberi nel giorno "+dataGiornoScelto+" sono: "));
		servizioSpaPanel.add(turniLiberiSpaCombo);
				
		
		// dichiarazione e inizializzazione del JPanel che contiene i codici delle bici riservate
		JPanel dispositionButtonPanel = new JPanel();
		dispositionButtonPanel.setLayout(new BorderLayout());
		dispositionButtonPanel.setFocusable(true);
		
		// inizializzazione del pulsante per andare al passo suuccessivo
		sceltaTurnoButton = new JButton("Conferma"); 
		sceltaTurnoButton.addActionListener(event -> {
			frameSceltaTurno.setVisible(false);
			this.setVisible(false);
			int turnoScelto = turniLiberiSpaCombo.getSelectedIndex();
			
			// setto il giorno e il turno scelto per la spa
			controllerS.setTurnoScelto(dataGiornoScelto, indiceGiornoScelto, turnoScelto);
			
			// ottengo la tipologia di camera che il cliente ha scelto
			String tipologiaCamera = controllerP.getTipologiaCamera(controllerP.getCameraScelta());
			
			// aggiungo il costo della spa solo alla camera standard e superior
			if( tipologiaCamera.equals("Standard") || tipologiaCamera.equals("Standard")) {
				controllerS.aggiungiCostoServizi(controllerS.getCostoSpa());
			}

			// conclusa la procedura sceglo quale gui mostrare
			if(controllerS.biciPrenotate() && controllerS.getNumeroBambini() > 0) {
				new ViewServizioBabySitting(controllerS, controllerP);
			}else if(controllerS.biciPrenotate()) {
				new ViewReview(controllerP, controllerS);
			}else if (controllerS.getUtilizzoComeBackBici()) {
				new ViewReview(controllerP, controllerS);
			}else {
				viewServizi.setVisible(true);
			}
		});
		

		// dichiarazione e inizializzazione del pulsante per tornare indietro
		JButton comeBackButton = new JButton("Torna indietro");
		comeBackButton.addActionListener(event -> {
			frameSceltaTurno.setVisible(false);
			this.frameAperti.add(this.sceltaGiorno());
		});
		// aggiungo i pulsanti dichiarati al JPanel li contiene 
		dispositionButtonPanel.add(sceltaTurnoButton, BorderLayout.LINE_END);
		dispositionButtonPanel.add(comeBackButton, BorderLayout.LINE_START);
			
		// aggiungo al JPanel principale il JPanel che fa scegliere il turno
		// da riservareche e quello che contiene i pulsanti
		sceltaTurnoPanel.add(servizioSpaPanel);
		sceltaTurnoPanel.add(dispositionButtonPanel);
			
		// al frame aggiungo il JPanel principale
		frameSceltaTurno.getContentPane().add(sceltaTurnoPanel);
		
		// ritorno il JFrame
		return frameSceltaTurno;	
	}
	// fine metodo 
}
// fine clsse