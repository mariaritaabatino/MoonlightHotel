package moonlightHotel.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import moonlightHotel.controller.ControllerPrenotazioni;
import moonlightHotel.controller.ControllerServizi;

@SuppressWarnings("serial")
public class ViewServiziCamera extends JFrame {

	// attributi
	private final String TITOLO = "Servizi offerti dall'hotel";
	private ControllerServizi controllerS;
	private ControllerPrenotazioni controllerP;
	private JButton biciButton;
	private JButton spaButton;
	private JButton avantiButton;
	
	// costruttore
	public ViewServiziCamera(ControllerServizi controllerS, ControllerPrenotazioni controllerP){
		this.controllerS  	= controllerS;
		this.controllerP	= controllerP;
		this.Init();
		this.pack();
		this.setTitle(this.TITOLO);
		this.setSize(400, 200);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setVisible(true);
	}
	// fine costruttore
	
	
	// inizializzazione della GUI
	private void Init() {
					
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(20, 50, 50, 50));
			
		
		JPanel biciButtonPanel = new JPanel();
		biciButtonPanel.setLayout(new BoxLayout(biciButtonPanel, BoxLayout.Y_AXIS));
		biciButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		biciButtonPanel.setFocusable(true);
		biciButton = new JButton("Informazioni noleggio biciclette"); 
		biciButton.addActionListener(event -> {
			new ViewServizioBiciclette(controllerS, controllerP, this);
			this.setVisible(false);
			mainPanel.remove(biciButtonPanel);
		});
		biciButtonPanel.add(biciButton);
		
		
		JPanel spaButtonPanel = new JPanel();
		spaButtonPanel.setLayout(new BoxLayout(spaButtonPanel, BoxLayout.Y_AXIS));
		spaButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		spaButtonPanel.setFocusable(true);
		spaButton = new JButton("Informazioni spa privata"); 
		spaButton.addActionListener(event -> {
			new ViewServizioSpa(controllerS, controllerP, this);
			this.setVisible(false);
			mainPanel.remove(spaButtonPanel);
		});
		spaButtonPanel.add(spaButton);
			
		JPanel confermaButtonPanel = new JPanel();
		confermaButtonPanel.setLayout(new BoxLayout(confermaButtonPanel, BoxLayout.Y_AXIS));
		confermaButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		confermaButtonPanel.setFocusable(true);
		avantiButton = new JButton("Avanti"); 
		avantiButton.addActionListener(event -> {
			
			this.setVisible(false);
			if(controllerS.getNumeroBambini() > 0) {
				new ViewServizioBabySitting(controllerS, controllerP);
			}else {
				new ViewReview(controllerP, controllerS);	
			}
		});
		confermaButtonPanel.add(avantiButton);
		
		mainPanel.add(biciButtonPanel);
		mainPanel.add(spaButtonPanel);
		mainPanel.add(confermaButtonPanel);
			
		this.getContentPane().add(mainPanel);
	}
	// fine metodo
}	
// fine classe