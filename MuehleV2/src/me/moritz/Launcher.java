package me.moritz;
import java.awt.EventQueue;

public class Launcher {

    // MAIN
    public static void main(String[] args) {
	final Steuerung steuerung = new Steuerung();

	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    // Gui auf dem Event-Dispatch-Thread erstellen
		    steuerung.getDieOberflaeche().createAndShowGui();

		    // Thread für Spiellogik erstellen und starten
		    Thread thread = new Thread(steuerung);
		    thread.start();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }
}
