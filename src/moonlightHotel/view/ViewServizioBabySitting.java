package moonlightHotel.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import moonlightHotel.controller.ControllerPrenotazioni;
import moonlightHotel.controller.ControllerServizi;

@SuppressWarnings("serial")
public class ViewServizioBabySitting extends JFrame {
	
	// attributi
	private final String titolo = "Servizio Baby Sitting";
	private JButton avantiButton;
	private ControllerServizi controllerS;
	private ControllerPrenotazioni controllerP;
	
	// costruttore
	public ViewServizioBabySitting(ControllerServizi controllerS, ControllerPrenotazioni controllerP){
		this.controllerS = controllerS;
		this.controllerP = controllerP;
		this.Init();
		this.pack();
		this.setTitle(this.titolo);
		this.setSize(650, 150);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
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
		
		// dichiarazione e inizializzazione dell'area di testo che contiene info sulle camere
		JTextArea infoBabySitting = new JTextArea();
		// faccio in modo che l'area di testo non sia modificabile
		infoBabySitting.setEditable(false);
		infoBabySitting.append("Nel nostro hotel è presente un'area bimbi in cui i suoi figli possono diversirsi\n"
				+ "e giocare con altri bimbi. Per verificare che nell'area bimbi ci siano posti sufficienti\n"
				+ "per i suoi bambini vada avanti.");
			
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setFocusable(true);
		// inizializzazione del JButton per andare al passaggio successivo
		avantiButton = new JButton("Avanti"); 
		avantiButton.addActionListener(event -> {
			this.setVisible(false);
			// riservo già i posti nell'area bimbi
			if(controllerS.controllaDisponibilitaAreaBimbi()) {
				this.confermaDisponibilita();
			} else {
				JOptionPane.showMessageDialog(this,
						"Siamo spiacenti, non ci sono posti sufficienti nell'area bimbi...",
						"AREA BIMBI", JOptionPane.INFORMATION_MESSAGE);
				new ViewReview(controllerP, controllerS);
			}
		});
		// aggiungo al JPanel dei pulsunti quelli dichiarati
		buttonPanel.add(avantiButton);
		
		// aggiungo al JPanel principale i JPanel che contiene info sul
		// noleggio delle bici e quello che contiene il pulsante
		mainPanel.add(infoBabySitting);
		mainPanel.add(buttonPanel);
		
		// al frame aggiungo il JPanel principale
		this.getContentPane().add(mainPanel);
	}
	// fine metodo
	
	// metodo per ottenere la conferma della disponibilità
	private JFrame confermaDisponibilita() {
		
		// dichiarazione e inizializzazione del JFrame da ritornare
		JFrame frameConfermaDisponibilita = new JFrame();
		frameConfermaDisponibilita.pack();
		frameConfermaDisponibilita.setTitle("Area Bimbi");
		frameConfermaDisponibilita.setSize(400, 150);
		frameConfermaDisponibilita.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frameConfermaDisponibilita.setLocationRelativeTo(null);
		frameConfermaDisponibilita.setResizable(false); 
		frameConfermaDisponibilita.setVisible(true);
		
		// dichiarazione e inizializzazione del JPanel che comunica al cliente 
		// la disponibilità di posti nell'area bimbi
		JPanel disponibilitaPanel = new JPanel();
		disponibilitaPanel.setLayout(new BoxLayout(disponibilitaPanel, BoxLayout.Y_AXIS));
		disponibilitaPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		// dichiarazione e inizializzazione dell'area di testo che comunica al cliente la diponibilità
		JTextArea disponibilitaTextArea = new JTextArea(); 
		disponibilitaTextArea.setEditable(false);
		disponibilitaTextArea.append("Ci sono posti sufficienti per i suoi bimbi,\n"
									+ "può lasciarli ogni volta che vuole nell'area bimbi.");
				
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel confermaPanel = new JPanel();
		confermaPanel.setLayout(new BoxLayout(confermaPanel, BoxLayout.Y_AXIS));
		confermaPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		confermaPanel.setFocusable(true);
		
		// dichiarazione e inizializzazione del pulsante
		JButton confermaButton = new JButton("Ok"); 
		confermaButton.addActionListener(event -> {
			frameConfermaDisponibilita.setVisible(false);
			new ViewReview(controllerP, controllerS);	
		});
		confermaPanel.add(confermaButton);
		
		// aggiungo l'area di testo e il pulsante al JPanel principale
		disponibilitaPanel.add(disponibilitaTextArea);
		disponibilitaPanel.add(confermaPanel);
		
		// al frame aggiungo il JPanel principale
		frameConfermaDisponibilita.getContentPane().add(disponibilitaPanel);
						
		return frameConfermaDisponibilita;
	}
	// fine metodo
}
// fine classe