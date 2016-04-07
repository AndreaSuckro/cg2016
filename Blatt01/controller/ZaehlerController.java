/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ZaehlerModel;
import view.ZaehlerView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Nicolas Neubauer
 * 
 *         Der Controller, der die ZaehlerView nutzt und verwaltet, so dass sich
 *         das ZaehlerModel ensprechend ändert oder dessen Änderungen an die
 *         ZaehlerView propagiert werden. (Noch zu implementieren)
 * 
 */
public class ZaehlerController {

	// Model und View
	private ZaehlerView view;
	private ZaehlerModel model;
	
 /**
 *  Hier soll die View und das Model angelegt werden. Außerdem müssen die
 *  View-Components mit über entsprechende Listener mit Funktionalität versehen werden. 
 */
	public ZaehlerController() {

		// Lege View an
		view = new ZaehlerView();
		// Lege Model an
		model = new ZaehlerModel(50, 0, 100);

		// Erstelle ActionListener
		// Hier ein Beispiel für den Erhoehe-Button, der das Model verändert,
		// jedoch die View nicht ändert. Dies muss noch mittels des Observer/Observable
		// Patterns implementiert werden. Außerdem müssen natürlich weitere Listener
		// für die anderen Aktionen implementiert werden. (Siehe Aufgabentext)
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int aktWert = model.getWert();

				// Button
				if (e.getSource() == view.getUp()) {
					try {
                        model.setWert(aktWert + 1);
                    }catch (IllegalArgumentException eMax){
                        view.getResult().setText("Wert darf nicht über " + model.getMax() + " erhöht werden.");
                    }

				}

				if (e.getSource() == view.getDown()){
					try {
                        model.setWert(aktWert - 1);
                    }catch (IllegalArgumentException eMin){
                        view.getResult().setText("Wert darf nicht unter " + model.getMin() + " fallen.");
                    }
				}

				//Label muss noch den neuen Wert erfahren
				view.getResult().setText(String.valueOf(model.getWert()));
			}
		};

		// Hänge die Listener an die View-Componenten
		view.getUp().addActionListener(a);
		view.getDown().addActionListener(a);

        ChangeListener slideAction = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                model.setWert(view.getSlider().getValue());
                //Label muss noch den neuen Wert erfahren
                view.getResult().setText(String.valueOf(model.getWert()));
            }
        };

        view.getSlider().addChangeListener(slideAction);

        ActionListener textA = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view.getEingabe()){
                    try {
                        
                    }catch (IllegalArgumentException illarg){
                        view.getResult().setText("Der eingegebene Wert ist ungültig");
                    }
                }
            }
        };
        view.getEingabe().addActionListener(textA);

	}

	/**
	 * Gibt die View zurück (JPanel)
	 * 
	 * @return the view
	 */
	public ZaehlerView getView() {
		return view;
	}

}
