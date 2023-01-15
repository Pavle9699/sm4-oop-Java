package src.atm.account;

public final class Pin {

	/**
	 * Pin od 4 cifre.
	 */
	private String pin;


	public Pin(String pin, String confirmPin) throws IllegalArgumentException {
		if (PINs_MATCH(pin, confirmPin)) {
			this.pin = String.format("%04d", Short.parseShort(pin));
		}
	}


	public static boolean IS_VALID_PIN(String pin) throws IllegalArgumentException {
		boolean onlyContainsNumbers = false;
		for (int i = 0; i < pin.length(); i++) {
			if (Character.isDigit(pin.charAt(i))) {
				onlyContainsNumbers = true;
			} else {
				onlyContainsNumbers = false;
				break;
			}
		}
		try {	
			if ((Short.parseShort(pin) >= 0) && (pin.length() == 4) && onlyContainsNumbers) {	
				return true;
			} else {
				throw new IllegalArgumentException("Unesite PIN od 4 cifre.");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Molimo vas unesite pin od 4 numericke cifre.", new NumberFormatException());
		}
	}


	public static boolean PINs_MATCH(String pin, String confirmPin) throws IllegalArgumentException {
		if ((IS_VALID_PIN(pin) && IS_VALID_PIN(confirmPin)) && pin.equals(confirmPin)) {
			return true;
		} else {
			throw new IllegalArgumentException("Uneti PINovi se ne poklapaju.");
		}
	}


	public static boolean IS_CORRECT_PIN(int accountNumber, String accountPin) throws IllegalArgumentException, NullPointerException {
		if (Account.GET_ACCOUNT_MAP().get(accountNumber).getAccountPin().getPin().equals(accountPin)) {
			return true;
		} else {
			throw new IllegalArgumentException("Pin koji ste uneli nije tacan.");
		}
	}


	@Override
	public boolean equals(Object confirmPin) {
		if (this.toString().equalsIgnoreCase(((Pin)confirmPin).toString())) {	// Kastujemo confirmPin u pin, tako ga forsiramo da korsiti pin to string.
			return true;
		} else {
			return false;
		}
	}


	@Override
	public String toString() {
		return pin;
	}


	public String getPin() {
		return pin;
	}

}