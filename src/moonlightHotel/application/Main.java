package moonlightHotel.application;

import moonlightHotel.controller.ControllerPrenotazioni;
import moonlightHotel.controller.ControllerServizi;
import moonlightHotel.view.ViewBenvenuto;

public class Main {

	public static void main(String[] args) {
		
		//new ViewBenvenuto();
		ControllerPrenotazioni cP = new ControllerPrenotazioni();
		cP.setCliente("Mario", "Rossi", 2, 0, 2);
		
		ControllerServizi cS = new ControllerServizi(cP);

		System.out.println(cS.getUtilizzoComeBackSpa());
		cS.setUtilizzoComeBackSpa();
		System.out.println(cS.getUtilizzoComeBackSpa());
		
	}
	// fine metodo
}
// fine classe