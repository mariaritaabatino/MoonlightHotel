package moonlightHotel.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
public class ViewReview  extends JFrame {
	// attributi
	private final String titolo = "Riepilogo della prenotazione";
	private ControllerPrenotazioni controllerP;
	private ControllerServizi controllerS;
	
	// costruttore
	public ViewReview(ControllerPrenotazioni controllerP, ControllerServizi controllerS){
		this.controllerP = controllerP;
		this.controllerS = controllerS;
		this.Init();
		this.pack();
		this.setTitle(this.titolo);
		this.setSize(650, 250);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setVisible(true);
	}
	// fine costruttore
	
	// inizializzazione della GUI
	private void Init() {
		
		// dichiarazione e inizializzazione del JPanel principale
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(20, 50, 50, 50));
		
			
		// dichiarazione e inizializzazione dell'area di testo in cui comunico il costo
		JTextArea comunicazioni = new JTextArea();
		// rendo l'area di testo non modificabile
		comunicazioni.setEditable(false);		
		comunicazioni.append("La camera prenotata a nome "+controllerP.getCliente().getNome()
							+" "+controllerP.getCliente().getCognome()+" è la "
							+controllerP.getCameraScelta().getNomeCamera()+" "
							+controllerP.getTipologiaCamera(controllerP.getCameraScelta())+".\n");
		// controllo che siano state riservate le bici
		if(controllerS.biciPrenotate()) {
			comunicazioni.append("\nI codici delle bici riservate per lei sono:\n");
			for(int i = 0; i < controllerS.getCodiBiciDaRiservare().size(); i++)
				comunicazioni.append("\t"+String.valueOf(controllerS.getCodiBiciDaRiservare().get(i).getCodiceBici())+"\n");
			comunicazioni.append("\n");
		}
		// controllo che sia stato riservato un turno nella spa
		if(controllerS.spaPrenotata()) {
			comunicazioni.append(controllerS.getTurnoScelto());
			comunicazioni.append("\n");
		}
		
		// ottengo il costo dell'intero soggiorno
		comunicazioni.append("\nIl costo dell'intero soggiorno ammonta a "
				+controllerP.getCostoSoggiorno()+"€.");

		// dichiarazione e inizializzazione della sbarra per scorrere nell'area di testo 
		JScrollPane scrollPaneRiepilogo = new JScrollPane(comunicazioni);
		scrollPaneRiepilogo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneRiepilogo.setBounds(900, 900, 900, 900);
						
		// dichiarazioen del JPanel che fa scegliere il turno da riservare 
		JPanel riepilogoPanel = new JPanel();
		riepilogoPanel.setLayout(new BoxLayout(riepilogoPanel, BoxLayout.Y_AXIS));
		riepilogoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		riepilogoPanel.add(new JLabel("RIEPOLOGO PRENOTAZIONE"));
		riepilogoPanel.add(scrollPaneRiepilogo);
				
		// dichiarazione e inizializzazione del JPanel che contiene i codici delle bici riservate
		JPanel dispositionButtonPanel = new JPanel();
		dispositionButtonPanel.setLayout(new BorderLayout());
		dispositionButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		dispositionButtonPanel.setFocusable(true);
		
		// inizializzazione del pulsante per andare al passo suuccessivo
		JButton confermaButton = new JButton("Conferma"); 
		confermaButton.addActionListener(event -> {
			this.setVisible(false);
			this.confermaPrenotazione();
		});
		
		// dichiarazione e inizializzazione del pulsante per tornare indietro
		JButton comeBackButton = new JButton("Ricomincia");
		comeBackButton.addActionListener(event -> {
			this.setVisible(false);
			controllerP.azzeraCostoSoggiorno();
			new ViewUser(controllerP);
		});
		
		// dichiarazione e inizializzazione del pulsante per tornare indietro
		JButton annullaButton = new JButton("Annulla");
		annullaButton.addActionListener(event -> {
			this.setVisible(false);
			this.annullaPrenotazione();
		});	
		
		// aggiungo i pulsanti dichiarati al JPanel li contiene 
		dispositionButtonPanel.add(confermaButton, BorderLayout.LINE_END);
		dispositionButtonPanel.add(annullaButton, BorderLayout.LINE_START);
		// aggiungo il pulsante per tornare indetro solo se ho iù di una camera libera
		if(controllerP.getCamereLibere().size() > 1) {
			dispositionButtonPanel.add(comeBackButton, BorderLayout.CENTER);
		}
			
		// aggiungo al JPanel principale il JPanel che fa scegliere il turno
		// da riservareche e quello che contiene i pulsanti
		mainPanel.add(riepilogoPanel);
		mainPanel.add(dispositionButtonPanel);
			
		// al frame aggiungo il JPanel principale
		this.getContentPane().add(mainPanel);
	}
	// fine metodo
	
	// metodo per conferma l'avvenuta prenotazione 
	private JFrame annullaPrenotazione() {
		
		// dichiarazione e inizializzazione del JFrame da ritornare
		JFrame frameDiAnnullamento = new JFrame();
		frameDiAnnullamento.pack();
		frameDiAnnullamento.setTitle("Annullamento prenotazione");
		frameDiAnnullamento.setSize(500,150);
		frameDiAnnullamento.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameDiAnnullamento.setLocationRelativeTo(null);
		frameDiAnnullamento.setResizable(false); 
		frameDiAnnullamento.setVisible(true);
		
		// dichiarazione e inizializzazione del JPanel che permette 
		// la scelta del numero di biciclette da riservare
		JPanel annullamentoPrenotazionePanel = new JPanel();
		annullamentoPrenotazionePanel.setLayout(new BoxLayout(annullamentoPrenotazionePanel, BoxLayout.Y_AXIS));
		annullamentoPrenotazionePanel.setBorder(new EmptyBorder(20, 50, 50, 50));
		annullamentoPrenotazionePanel.add(new JLabel(("E' sicuro di voler annullare la prenotazione?")));
		
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setFocusable(true);
		// inizializzazione del JButton per andare al passaggio successivo
		JButton avantiButton = new JButton("Ok"); 
		avantiButton.addActionListener(event -> {
			frameDiAnnullamento.setVisible(false);
			this.confermaAnnullamento();
		});
		// inizializzazione del JButton per andare al passaggio successivo
		JButton comeBackButton = new JButton("Annulla"); 
		comeBackButton.addActionListener(event -> {
			frameDiAnnullamento.setVisible(false);
			this.setVisible(true);
		});
		// aggiungo al JPanel dei pulsunti quelli dichiarati
		buttonPanel.add(comeBackButton, BorderLayout.LINE_START);
		buttonPanel.add(avantiButton, BorderLayout.LINE_END);
				
		// aggiungo al JPanel principale i JPanel che contiene info sul
		// noleggio delle bici e quello che contiene il pulsante
		annullamentoPrenotazionePanel.add(buttonPanel);
				
		// al frame aggiungo il JPanel principale
		frameDiAnnullamento.getContentPane().add(annullamentoPrenotazionePanel);

		// ritorno il JFrame
		return frameDiAnnullamento;
	}
	// fine metodo
	
	// metodo per conferma l'avvenuta prenotazione 
	private JFrame confermaPrenotazione() {
			
		// dichiarazione e inizializzazione del JFrame da ritornare
		JFrame frameConferma = new JFrame();
		frameConferma.pack();
		frameConferma.setTitle("Conferma prenotazione");
		frameConferma.setSize(550,150);
		frameConferma.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameConferma.setLocationRelativeTo(null);
		frameConferma.setResizable(false); 
		frameConferma.setVisible(true);
		
		// dichiarazione e inizializzazione del JPanel che permette 
		// la scelta del numero di biciclette da riservare
		JPanel confermaPrenotazionePanel = new JPanel();
		confermaPrenotazionePanel.setLayout(new BoxLayout(confermaPrenotazionePanel, BoxLayout.Y_AXIS));
		confermaPrenotazionePanel.setBorder(new EmptyBorder(20, 50, 50, 50)); 
		confermaPrenotazionePanel.add(new JLabel("La prenotazione è avvenuta con successo."));
		confermaPrenotazionePanel.add(new JLabel("Può pagare al termine del soggiorno, le auguriamo una buona permanenza."));
		
		// riservo la camera scelta
		controllerP.riservaCameraScelta();
		// se la spa è stata prenotata cambio lo stato del turno scelto
		if(controllerS.spaPrenotata()) {
			controllerS.setStatoTurnoScelto();
		}
		// se la le bici sono state prenotate le riservo
		if(controllerS.biciPrenotate()) {
			controllerS.riservaBici();
		}
		// se ci sono i bambini gli riservo i posti nell'area bimbi 
		if(controllerS.controllaDisponibilitaAreaBimbi()) {
			controllerS.riservaPostiAreaBimbi();
		}
		
		// al frame aggiungo il JPanel principale
		frameConferma.getContentPane().add(confermaPrenotazionePanel);
		
		// ritorno il JFrame
		return frameConferma;
	}
	// fine metodo
	
	// metodo per conferma l'avvenuta prenotazione 
	private JFrame confermaAnnullamento() {
				
		// dichiarazione e inizializzazione del JFrame da ritornare
		JFrame frameAnnullamento = new JFrame();
		frameAnnullamento.pack();
		frameAnnullamento.setTitle("Conferma annullamento prenotazione");
		frameAnnullamento.setSize(550,150);
		frameAnnullamento.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameAnnullamento.setLocationRelativeTo(null);
		frameAnnullamento.setResizable(false); 
		frameAnnullamento.setVisible(true);
		
		// dichiarazione e inizializzazione del JPanel che permette 
		// la scelta del numero di biciclette da riservare
		JPanel confermaAnnullamentoPanel = new JPanel();
		confermaAnnullamentoPanel.setLayout(new BoxLayout(confermaAnnullamentoPanel, BoxLayout.Y_AXIS));
		confermaAnnullamentoPanel.setBorder(new EmptyBorder(20, 50, 50, 50)); 
		confermaAnnullamentoPanel.add(new JLabel("La prenotazione è stata annullata con successo."));
				
		// al frame aggiungo il JPanel principale
		frameAnnullamento.getContentPane().add(confermaAnnullamentoPanel);
		
		// ritorno il JFrame
		return frameAnnullamento;
	}
	// fine metodo
	
}
// fine classe