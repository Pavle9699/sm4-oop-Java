package src.atm;

import src.atm.gui.GUI;

import static src.atm.util.Constants.INVALID_COMMAND_ARGS_CODE;


public final class Main {

	/**
	 * Nema instanciranja.
	 */
	private Main() {}


	public static void main(String[] args) {
		if (args.length == 0) {
			new GUI();
		} else {
			System.out.println("Parametri nisu validni.");
			System.exit(INVALID_COMMAND_ARGS_CODE);
		}
	}

}