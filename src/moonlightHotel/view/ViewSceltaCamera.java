package moonlightHotel.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import moonlightHotel.controller.ControllerPrenotazioni;
import moonlightHotel.controller.ControllerServizi;

@SuppressWarnings("serial")
public class ViewSceltaCamera extends JFrame {
	
	// attributi
	private final String titolo = "Scelta Camera";
	private ControllerPrenotazioni controllerP;
	private JButton avantiButton;
	private JComboBox<String> camereAdatteCombo;
	
	// costruttore
	public ViewSceltaCamera(ControllerPrenotazioni controllerP){
		this.controllerP  = controllerP;
		this.Init();
		this.pack();
		this.setTitle(this.titolo);
		this.setSize(420, 150);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setVisible(true);
	}
	// fine costruttore
	
	// inizializzazione della GUI
	private void Init() {
				
		// inizializzazione della JComboBox
		camereAdatteCombo = new JComboBox<String>();
		for(int i = 0; i < controllerP.getDescrizioneCamereAdatte().size(); i++) {
			camereAdatteCombo.addItem(controllerP.getDescrizioneCamereAdatte().get(i));
		} // fine for
		
		// dichiarazione e  inizializzazione del JPanel principale			
		JPanel informazioniPanel = new JPanel();
		informazioniPanel.setLayout(new BoxLayout(informazioniPanel, BoxLayout.Y_AXIS));
		informazioniPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
		informazioniPanel.add(new JLabel ("Le camere libere sono: "));
		informazioniPanel.add(camereAdatteCombo);	
		
		// dichiarazione e inizializzazione del JPanel che contiene i pulsanti
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setFocusable(true);
		// inizializzazione del pulsante
		avantiButton = new JButton("Conferma"); 
		avantiButton.addActionListener(event -> {
			this.setVisible(false);
			for(int i = 0; i < controllerP.getCamereAdatte().size(); i++) {
				if(camereAdatteCombo.getSelectedIndex() == i) {
					// cambio lo stato della camera scelta
					controllerP.aggiungiCostoCamera(controllerP.getCamereAdatte().get(i));
				}
			}// fine for
						
			// dichiarazione e inizializzazione del controller dei servizi
			ControllerServizi controllerS = new ControllerServizi(controllerP);
			
			new ViewServiziCamera(controllerS, controllerP);
			
		});
		// aggiungo al JPanel dei pulsunti quelli dichiarati
		buttonPanel.add(avantiButton);
			
		// aggiungo al JPanel principale il JPanel che contiene il pulsante
		informazioniPanel.add(buttonPanel);
			
		// al frame aggiungo il JPanel principale
		this.getContentPane().add(informazioniPanel);
	}
	// fine metodo	
}
// fine classe