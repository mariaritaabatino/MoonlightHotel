package moonlightHotel.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import moonlightHotel.controller.ControllerPrenotazioni;


/*
 * Finestra per ottenere le informazioni dal cliente
 */
@SuppressWarnings("serial")
public class ViewUser extends JFrame {
	
	// attributi
	private final String titolo = "Dati cliente";
	private ControllerPrenotazioni controllerP;
	private JComboBox<String> numAdultiCombo;
	private String[] opzioniAdulti = {"1", "2", "3", "4", "5"};
	private JComboBox<String> numBambiniCombo;
	private String[] opzioniBambini = {"0", "1", "2", "3"};
	private JButton proceedButton;
	
	// costruttore
	public ViewUser(ControllerPrenotazioni controllerP){
		this.controllerP = controllerP;
		this.Init();
		this.pack();
		this.setTitle(this.titolo);
		this.setSize(600, 350);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setVisible(true);
	}
	// fine costruttore
	
	// inizializzazione della GUI
	private void Init() {
		
		// dichiarazione e inizializzazione delle JTextField
		JTextField nameField			 = new JTextField();
		JTextField surnameField			 = new JTextField();
		JTextField giorniPermanenzaField = new JTextField();

		// inizializzazione delle JComboBox
		numAdultiCombo	= new JComboBox<String>(opzioniAdulti);
		numBambiniCombo = new JComboBox<String>(opzioniBambini);
				
		// dichiarazione e inizializzazione del Jpanel che acquisisce i dati del cliente
		JPanel datiClientePanel = new JPanel();
		datiClientePanel.setLayout(new BoxLayout(datiClientePanel, BoxLayout.Y_AXIS));
		datiClientePanel.setBorder(new EmptyBorder(20, 40, 40, 40));
		
		// aggiungo gli elementi al JPanel
	    datiClientePanel.add(new JLabel("Inserisca il suo nome: "));
		datiClientePanel.add(nameField);
		datiClientePanel.add(new JLabel("Inserisca il suo cognome: "));
		datiClientePanel.add(surnameField);
		datiClientePanel.add(new JLabel ("Inserisca il numero di adulti (da 14 anni in su): "));
		datiClientePanel.add(numAdultiCombo);
		datiClientePanel.add(new JLabel ("Inserisca il numero di bambini: "));
		datiClientePanel.add(numBambiniCombo);
		datiClientePanel.add(new JLabel("Inserisca il numero di giorni di permanenza: "));
		datiClientePanel.add(giorniPermanenzaField);
		
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		// inizializzazione del pulsante
		proceedButton = new JButton("Procedi"); 
		proceedButton.addActionListener(event -> {
			
			// verifico il corretto inserimento dei dati
			boolean nomeB		= verificaTesto(nameField);
			boolean cognomeB	= verificaTesto(surnameField);
			boolean permanenzaB = verificaNumero(giorniPermanenzaField);
			
			// se i campi non sono stati riempiti in maniera corretta lo comunico al cliente
			if(!nomeB || !cognomeB || !permanenzaB) {
				JOptionPane.showMessageDialog(datiClientePanel,
									"Ricontrolla, i campi non sono stati riempiti correttamente...",
									"ATTENZIONE", JOptionPane.ERROR_MESSAGE);
			}else {
				// i campi sono stati inseriti correttamente
				this.setVisible(false);
				
				// dichiaro e inizializzo il cliente
				controllerP.setCliente(nameField.getText(),
										surnameField.getText(),
										numAdultiCombo.getSelectedIndex(),
										numBambiniCombo.getSelectedIndex(),
										Integer.parseInt(giorniPermanenzaField.getText()));
				
				controllerP.checkCamereAdatte();
				// trovo la lista del camere che soddisfano i criteri del cliente
				if(controllerP.getCamereAdatte().size() == 0) {
					JOptionPane.showMessageDialog(this,
							"Siamo spiacenti, momentaneamente non abbiamo camere libere che soddisfano le sue esigenze.",
							"ATTENZIONE", JOptionPane.INFORMATION_MESSAGE);
				} else {
					new ViewSceltaCamera(controllerP);
				} // fine if-else interno
			} // fine if-else esterno
		});
		// aggiungo al JPanel dei pulsunti quelli dichiarati
		buttonsPanel.add(proceedButton);
		
		// aggiungo al JPanel principale il JPanel che contiene il pulsante
		datiClientePanel.add(buttonsPanel);
		
		// al frame aggiungo il JPanel principale
		this.getContentPane().add(datiClientePanel);
	}
	// fine metodo
	
	// metodo privato per verificare il corretto inserimento
	private boolean verificaTesto(JTextField testo) {

		// attributi
		String parola = testo.getText();
		int ascii;
		boolean stato = false; 
		
		// elimino eventuali spazi bianchi
		String parolaSpuntata = parola.strip();
		
		// verifico che ogni carattere inserito sia nei range del codice ascii 64-91 e 96-123
		for(int i = 0;  i < parolaSpuntata.length(); i++) {
			ascii = (int)parolaSpuntata.charAt(i);
			if((ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123))
				stato = true;
			 else 
				stato = false;
		}
		
		// ritorno lo stato
		return stato;
	}
	// fine metodo
	
	// metodo privato per verificare il corretto inserimento
	private boolean verificaNumero(JTextField testo) {
			
		// attributi
		String numero = testo.getText();
		int ascii;
		boolean stato = false; 
		
		// elimino eventuali spazi bianchi
		String numeroSpuntato = numero.strip();
		
		// verifico che ogni carattere inserito sia nei range del codice ascii 46-58
		for(int i = 0;  i < numeroSpuntato.length(); i++) {
			ascii = (int)numeroSpuntato.charAt(i);
			if(ascii > 47 && ascii < 58) 
				stato = true;
			else 
				stato = false;
		}
		
		// verifico che il numero inserito non sia minore di 1
		if(numeroSpuntato.length() == 1) {
			ascii = (int)numeroSpuntato.charAt(0);
			if(ascii == 48) {
				stato = false;
			}
		}
		
		// ritorno lo stato
		return stato;
	}
	// fine metodo
}
// fine classe
