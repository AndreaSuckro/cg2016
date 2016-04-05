package model;


import java.util.Observable;

/**
 * 
 * @author Nicolas Neubauer
 *
 * Model, das einen Integer-Wert verwalten kann
 *
 */
public class ZaehlerModel extends Observable {

	private int wert;
	private int min;
	private int max;
	
	/**
	 * Legt ein neues Model an.
	 * 
	 * @param wert der initiale Zahlwert
	 * @param min das minimal erlaubte Zahlwert
	 * @param max der maximal erlaubte Zahlwert
	 */
	public ZaehlerModel(int wert, int min, int max){
		
		this.min = min;
		this.max = max;
			
		setWert(wert);
		
	}

		
	/**
	 * @return the wert
	 */
	public int getWert() {
		return wert;
	}

	/**
	 * Setzen des Zahlwerts
	 * @param wert der zu setzende Wert
	 */
	
	public void setWert(int wert) {
		if(wert <= max && wert >= min)
			this.wert = wert;
		else throw new IllegalArgumentException("Wert muss im Bereich zwischen " + min + " und " + max + " sein!");
		System.out.println("Neuer wert gesetzt auf: " + wert);
		notifyObservers();
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}
	
}
