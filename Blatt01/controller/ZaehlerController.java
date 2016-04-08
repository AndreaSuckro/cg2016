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

import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

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
                        view.getErrorMsg().setText("Wert darf nicht über " + model.getMax() + " erhöht werden.");
                    }

				}

				if (e.getSource() == view.getDown()){
					try {
                        model.setWert(aktWert - 1);
                    }catch (IllegalArgumentException eMin){
                        view.getErrorMsg().setText("Wert darf nicht unter " + model.getMin() + " fallen.");
                    }
				}

				//Label muss noch den neuen Wert erfahren
				view.getResult().setText(String.valueOf(model.getWert()));
			}
		};
		
		ActionListener l = new ActionListener() {
			
			@Override
				   public void actionPerformed(ActionEvent e) {

				    if(e.getSource() == view.getComboBox()){
				      try {
				       UIManager.setLookAndFeel(view.getLooks()[view.getComboBox().getSelectedIndex()].getClassName());
				       view.repaint();
				      } catch (UnsupportedLookAndFeelException e1) {
				       // TODO Auto-generated catch block
				       e1.printStackTrace();
				      } catch (ClassNotFoundException e1) {
				       // TODO Auto-generated catch block
				       e1.printStackTrace();
				      } catch (InstantiationException e1) {
				       // TODO Auto-generated catch block
				       e1.printStackTrace();
				      } catch (IllegalAccessException e1) {
				       // TODO Auto-generated catch block
				       e1.printStackTrace();
				      }
				    }
				    
				   }
	     };

		// Hänge die Listener an die View-Componenten
		view.getUp().addActionListener(a);
		view.getDown().addActionListener(a);
		view.getComboBox().addActionListener(l);


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
                        model.setWert(Integer.valueOf(view.getEingabe().getText()));
                        view.getResult().setText(String.valueOf(model.getWert()));
                    }catch (IllegalArgumentException illArg){ //illArg wird auch fuer fehleingabe als string geworfen
                        view.getErrorMsg().setText("Der eingegebene Wert ist ungültig.");
                    }
                }
                view.getResult().setText(String.valueOf(model.getWert()));

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
