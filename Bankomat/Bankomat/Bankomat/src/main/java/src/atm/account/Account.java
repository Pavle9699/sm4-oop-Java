package src.atm.account;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public abstract class Account {

	/**
	 * Cuva naloge na osnovu njihivog broja.
	 */
	private static final HashMap<Integer, Account> ACCOUNT_MAP = new HashMap<>();

	/**
	 *Datum i vreme za uoptrebu kod naloga. Primer: 01/18/2017 8:58 PM.
	 */
	static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

	/**
	 * Unikatan sestocifren broj.
	 */
	final int ACCOUNT_NUMBER;

	/**
	 * Ime vlasnika naloga.
	 */
	String accountName;

	/**
	 * PIN.
	 */
	Pin accountPin;

	/**
	 * Varijabla za pregled istorije transkacija na nalogu.
	 */
	String accountHistory;

	/**
	 *
	 * @param accountName
	 *        Ime vlasnika.
	 * @param accountPin
	 *        PIN.
	 */
	Account(String accountName, Pin accountPin) {
		ACCOUNT_NUMBER = GENERATE_ACCOUNT_NUMBER();
		this.accountName = accountName;
		this.accountPin = accountPin;
		accountHistory = DATE_TIME.format(LocalDateTime.now()) + " - Nalog otvoren.\n";
		ACCOUNT_MAP.put(ACCOUNT_NUMBER, this);
	}

	/**
	 * Generisanje nasumicnog sestocifrenog broja koji nije u upotrebi.
	 * 
	 * @return Unique 6 digit account number.
	 */
	private static final int GENERATE_ACCOUNT_NUMBER() {
		int accountNumber;
		do {
			accountNumber = (int)(Math.random() * 1_000_000);
			if ((accountNumber < 100_000) || (accountNumber > 999_999)) {
				continue;
			} else if (ACCOUNT_MAP.containsKey(accountNumber)) {
				continue;
			} else {
				break;
			}
		} while (true);
		return accountNumber;
	}

	/**
	 * Vraca nalog sa specificnim brojem naloga, i proverava da li je PIN u redu.
	 * 
	 * @param accountNumber
	 *        Nalog koj se vraca.
	 * @param accountPin
	 *        PIN.
	 * @return Nalog sa kojim se poklapaju broj i PIN.
	 * @throws IllegalArgumentException
	 *         Ako je pin pogresan.
	 * @throws NullPointerException
	 *         Ako se nalog sa datim broj ne moze pronaci ili ne postoji.
	 */
	public static final Account GET_ACCOUNT(int accountNumber, String accountPin) throws IllegalArgumentException, NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber) && Pin.IS_CORRECT_PIN(accountNumber, accountPin)) {
			return ACCOUNT_MAP.get(accountNumber);
		} else {
			return null;
		}
	}

	/**
	 * Proverava da li se nalog sa specificnim brojem kreirao.
	 * 
	 * @param accountNumber
	 *        Broj naloga koji se proverava.
	 * @return True, ako je nalog pronadjen u hashmapu.
	 * @throws NullPointerException
	 *         Ako nije pronadjen ili ne postoji.
	 */
	public static final boolean ACCOUNT_EXISTS(int accountNumber) throws NullPointerException {
		if (ACCOUNT_MAP.containsKey(accountNumber)) {
			return true;
		} else {
			throw new NullPointerException("Ovaj nalog ne postoji, molimo vas proverite uneti broj naloga.");
		}
	}

	
	public static final HashMap<Integer, Account> GET_ACCOUNT_MAP() {
		return ACCOUNT_MAP;
	}


	@Override
	public abstract boolean equals(Object account);


	public abstract String toString();

	public final int getAccountNumber() {
		return ACCOUNT_NUMBER;
	}


	public final String getAccountName() {
		return accountName;
	}


	public final Pin getAccountPin() {
		return accountPin;
	}

	public final String getAccountHistory() {
		return accountHistory;
	}


	final void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	
	final void setAccountPin(Pin accountPin) {
		this.accountPin = accountPin;
	}


	final void setAccountHistory(String accountHistory) {
		this.accountHistory = accountHistory;
	}

}