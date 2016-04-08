package view;

import model.ZaehlerModel;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * 
 * @author Nicolas Neubauer
 *
 * View-Klasse für das MVC-Beispiel.
 *
 */

@SuppressWarnings("serial")
public class ZaehlerView extends JPanel implements Observer {

	// View-Components
	private JButton up;
	private JButton down;
	private JLabel result;
	private JSlider slider;
	private JTextField eingabe;
	private JLabel errorMsg;
	private JComboBox<String> auswahl;
	private UIManager.LookAndFeelInfo[] looks;

	/**
	 * Legt die View an.
	 */
	
	public ZaehlerView() {
		super();

		ZaehlerModel mod = new ZaehlerModel(42,0,100);
		mod.addObserver(this);

		//eigenes Layout setzen
		this.setLayout(new GridLayout(0,1));
		
		//View-Componenten initalisieren
		up = new JButton("Erhöhen");
	    down = new JButton("Verringern");
	    result = new JLabel("", JLabel.CENTER);
	    slider = new JSlider();
		eingabe = new JTextField();
		errorMsg = new JLabel("",JLabel.CENTER);
	    auswahl = new JComboBox<String>();

		
	    //Schriftart des JLabels
	    Font font = new Font("SansSerif", Font.BOLD, 30);
	    result.setFont(font);
	    
	    //hinzufügen der View-Components
		this.add(up);
		this.add(result);
		this.add(slider);
		this.add(down);
		this.add(eingabe);
		this.add(errorMsg);
		this.add(auswahl);
		
		looks = UIManager.getInstalledLookAndFeels(); 
		for(int i = 0; i < looks.length;i++){
		   auswahl.addItem(looks[i].getName());
		   }	

	}

	
	/* -------- Getter-Methoden -------- */
	
	/**
	 * @return the up
	 */
	public JButton getUp() {
		return up;
	}

	/**
	 * @return the down
	 */
	public JButton getDown() {
		return down;
	}

	/**
	 * @return the result
	 */
	public JLabel getResult() {
		return result;
	}

	/**
	 * @return the slider
	 */
	public JSlider getSlider() {
		return slider;
	}

	/**
	 * @return the eingabe
	 */
	public JTextField getEingabe() {
		return eingabe;
	}

	/**
	 * @return the errorMsg label
     */
	public JLabel getErrorMsg() { return errorMsg; }
	
	/**
	 * @return the ComboBox
	 */
	public JComboBox getComboBox() {
		return auswahl;
	}
	
	/**
	 * @return Look and Feel
	 */
	public LookAndFeelInfo[] getLooks(){
		  return looks;
		 }


	@Override
	public void update(Observable o, Object arg) {
		//magic for view update
	}
}
