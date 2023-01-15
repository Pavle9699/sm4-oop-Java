package src.atm.gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

final class AccountMenu extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private static Font labelFont = new Font("Dialog", Font.BOLD, 14);
	private JLabel accountMenuLabel;
	private JButton makeDepositButton, makeWithdrawalButton, checkBalanceButton, logoutButton;

	public AccountMenu() {
		super(null);

		accountMenuLabel = new JLabel("Meni naloga");
		accountMenuLabel.setFont(labelFont);
		accountMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountMenuLabel.setVerticalAlignment(SwingConstants.CENTER);
		accountMenuLabel.setBounds(150, 10, 200, 50);

		makeDepositButton = new JButton("Uplata");
		makeDepositButton.setToolTipText("Napravite uplatu na racun.");
		makeDepositButton.setBounds(150, 71, 200, 50);

		makeWithdrawalButton = new JButton("Podizanje novca");
		makeWithdrawalButton.setToolTipText("Podignite novac sa vaseg racuna.");
		makeWithdrawalButton.setBounds(150, 132, 200, 50);

		checkBalanceButton = new JButton("Provera stanja");
		checkBalanceButton.setToolTipText("Proverite stanje na vasem racunu.");
		checkBalanceButton.setBounds(150, 212, 200, 50);;



		logoutButton = new JButton("Izlogujte se");
		logoutButton.setToolTipText("Izlogujte se sa naloga.");
		logoutButton.setBounds(150, 334, 200, 50);

		this.add(accountMenuLabel);
		this.add(makeDepositButton);
		this.add(makeWithdrawalButton);
		this.add(checkBalanceButton);
		this.add(logoutButton);
	}

	public JLabel getAccountMenuLabel() {
		return this.accountMenuLabel;
	}

	public JButton getMakeDepositButton() {
		return this.makeDepositButton;
	}

	public JButton getMakeWithdrawalButton() {
		return this.makeWithdrawalButton;
	}

	public JButton getCheckBalanceButton() {
		return this.checkBalanceButton;
	}

	public JButton getLogoutButton() {
		return this.logoutButton;
	}

}