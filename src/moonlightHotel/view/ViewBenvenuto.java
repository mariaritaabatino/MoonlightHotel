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


@SuppressWarnings("serial")
public class ViewBenvenuto extends JFrame {
	
	// attributi
	private final String titolo = "BENVENUTI AL MOONLIGHT HOTEL";
	private JButton avantiButton;
	
	// costruttore
	public ViewBenvenuto(){
		this.Init();
		this.pack();
		this.setTitle(this.titolo);
		this.setSize(700, 350);
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
		mainPanel.setBorder(new EmptyBorder(25, 50, 50, 50));
		
		// dichiarazione e inizializzazione dell'area di testo che contiene info sulle camere
		JTextArea infoBenvenuto = new JTextArea();
		// faccio in modo che l'area di testo non sia modificabile
		infoBenvenuto.setEditable(false);
		infoBenvenuto.append("Buongiorno!\n"
						+"\nIl nostro Hotel ha diverse tipologie di camere:\n"
						+"- la camera Standard (40€ a persona), nella quella potrà usufruire gratuitamente della nostra area bimbi;\n"
						+"- la camera Superior (80€ a persona), in cui avrà in aggiunta alla Standard il noleggio bici gratuito;\n"
						+"- la camera Deluxe (130€ a persona), in cui in aggiunta alla Superior avrà l'ingresso spa compreso nel prezzo.\n"
						+"\nI bambini pagano il 70% del costo della camera a notte.\n"
						+"\nTutte le nostre camere sono confortevoli e tutti i nostri clienti sono sempre stati\n"
						+"soddisfatti del nostro servizio.\n"
						+"\nPer verificare che ci sia almeno una stanza libera vada avanti.");
			
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setFocusable(true);
		// inizializzazione del JButton per andare al passaggio successivo
		avantiButton = new JButton("Avanti"); 
		avantiButton.addActionListener(event -> {
			
			this.setVisible(false);
			
			// dichiarazione e inizializzazione del controller della prenotazioni
			ControllerPrenotazioni controllerP 	= new ControllerPrenotazioni();
			controllerP.checkCamereLibere();
			
			// verifico che ci siano camere libere
			if(controllerP.getCamereLibere().size() == 0) {
				JOptionPane.showMessageDialog(this,
						"Siamo spiacenti, momentaneamente non si sono camere libere..",
						"ATTENZIONE", JOptionPane.INFORMATION_MESSAGE);
			} else {
				new ViewConfermaDisponibilita(controllerP);
			}
		});
		// aggiungo al JPanel dei pulsunti quelli dichiarati
		buttonPanel.add(avantiButton);
		
		// aggiungo al JPanel principale i JPanel che contiene info sul
		// noleggio delle bici e quello che contiene il pulsante
		mainPanel.add(infoBenvenuto);
		mainPanel.add(buttonPanel);
		
		// al frame aggiungo il JPanel principale
		this.getContentPane().add(mainPanel);
	}
	// fine metodo
}
// fine classe