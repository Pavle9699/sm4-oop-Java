package src.atm.account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class BankAccount extends Account {


	static final NumberFormat WON = NumberFormat.getCurrencyInstance(Locale.KOREAN);


	BigDecimal accountBalance;

	
	public BankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) throws IllegalArgumentException {
		super(accountName, accountPin);
		try {
			if (IS_POSITIVE_AMOUNT(accountBalance.doubleValue())) {
				this.accountBalance = accountBalance.setScale(2, RoundingMode.HALF_UP);
			}
		} catch (IllegalArgumentException e) {
			
			throw new IllegalArgumentException("Molimo vas unesite pozitivan broj.");
		}
	}

	/**
	 * @param amount
	 *       Broj koji se formatira.
	 * @return String WONa u formatu currency.
	 */
	public static final String TO_CURRENCY_FORMAT(double amount) {
		return WON.format(amount);
	}

	
	public static final String TO_CURRENCY_FORMAT(BigDecimal amount) {
		return WON.format(amount);
	}


	
	public static final boolean IS_POSITIVE_AMOUNT(double amount) throws IllegalArgumentException {
		if (amount >= 0) {
			return true;
		} else {
			throw new IllegalArgumentException("Molimo vas unesite pozitivan broj.");
		}
	}

	public final boolean hasSufficientBalance(double amount) throws IllegalArgumentException {
		if (accountBalance.compareTo(new BigDecimal(amount)) >= 0) {
			return true;
		} else {
			throw new IllegalArgumentException("Nemate dovoljno sredstava za ovu transakciju.");
		}
	}

	

	public void deposit(double depositAmount) throws IllegalArgumentException {
		if (IS_POSITIVE_AMOUNT(depositAmount)) {
			accountBalance = accountBalance.add(new BigDecimal(depositAmount));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Uplaceno " + TO_CURRENCY_FORMAT(depositAmount) + ".\n";
		}
	}


	public void withdraw(double withdrawalAmount) throws IllegalArgumentException {
		if (IS_POSITIVE_AMOUNT(withdrawalAmount) && hasSufficientBalance(withdrawalAmount)) {
			accountBalance = accountBalance.subtract(new BigDecimal(withdrawalAmount));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Podignuto " + TO_CURRENCY_FORMAT(withdrawalAmount) + ".\n";
		}
	}

	@Override
	public boolean equals(Object bankAccount) {
		if (this.toString().equalsIgnoreCase(((BankAccount)bankAccount).toString())) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public String toString() {
		return "Broj naloga: " + ACCOUNT_NUMBER + " \nIme korisnika: " + accountName + " \nPIN: " + accountPin + " \nStanje na racunu: "
				+ TO_CURRENCY_FORMAT(accountBalance) + " \nIstorija transakcija: " + accountHistory;
	}

	
	public final BigDecimal getAccountBalance() {
		return accountBalance;
	}

	
	final void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance.setScale(2, RoundingMode.HALF_UP);	  // Sets two significant decimal places.
	}

}