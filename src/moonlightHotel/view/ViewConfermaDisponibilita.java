package moonlightHotel.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import moonlightHotel.controller.ControllerPrenotazioni;

@SuppressWarnings("serial")
public class ViewConfermaDisponibilita extends JFrame {

	// attributi
	private final String titolo = "Moonlight Hotel";
	private JButton avantiButton;
	private ControllerPrenotazioni controllerP;
	
	// costruttore
	public ViewConfermaDisponibilita(ControllerPrenotazioni controllerP){
		this.controllerP = controllerP;
		this.Init();
		this.pack();
		this.setTitle(this.titolo);
		this.setSize(600, 150);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
		mainPanel.setBorder(new EmptyBorder(30, 50, 50, 50));
			
		// dichiarazione e inizializzazione dell'area di testo che contiene la conferma della
		// disponibilità di almeno una camera
		JTextArea infoConfermaDisponibilita = new JTextArea();
		// faccio in modo che l'area di testo non sia modificabile
		infoConfermaDisponibilita.setEditable(false);
		infoConfermaDisponibilita.append("Le comunico che c'è almeno una camera libera.\n"
						+"Per verificare che rispecchi i suoi bisogni devo prima acquisire qualche informazione.");
		
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setFocusable(true);
		// inizializzazione del JButton per andare al passaggio successivo
		avantiButton = new JButton("Avanti"); 
		avantiButton.addActionListener(event -> {
			
			this.setVisible(false);
			new ViewUser(controllerP);	
		});
		// aggiungo al JPanel dei pulsunti quelli dichiarati
		buttonPanel.add(avantiButton);
		
		// aggiungo al JPanel principale i JPanel che la conferma 
		// della disponibilità e quello che contiene il pulsante
		mainPanel.add(infoConfermaDisponibilita);
		mainPanel.add(buttonPanel);
		
		// al frame aggiungo il JPanel principale
		this.getContentPane().add(mainPanel);
	}
	// fine metodo
}
// fine classe