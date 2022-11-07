package atmsystem;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ATMsystem {

	private Account currentAccount;

	public ATMsystem(Account account) {
		this.currentAccount = account;
		System.out.println("Ready to accept your bank card\n");
		pin();
		while (true) {
			selectTransaction();
		}
	}

	public void selectTransaction() {
		System.out.println("1.To deposit");
		System.out.println("2.To withdraw");
		System.out.println("3.To show your balance");
		System.out.println("4.To exit the program");
		int menuChoice = new Scanner(System.in).nextInt();
		switch (menuChoice) {
		case 1:
			deposit();
			break;
		case 2:
			withdraw();
			break;
		case 3:
			displayBalance();
			break;
		case 4:
			System.out.println("Exiting the program!");
			System.exit(0);
			break;
		}
	}

	public void deposit() {
		System.out.print("How much do you want to deposit?: ");
		int amount = new Scanner(System.in).nextInt();
		currentAccount.setBalance(currentAccount.getBalance() + amount);

		System.out.println(
				String.format("You have deposited: %d$ - Your Balance is: %d$\n", amount, currentAccount.getBalance()));
	}

	public void withdraw() {
		System.out.print("How much do you want to withdraw?: ");
		int amount = new Scanner(System.in).nextInt();
		if (currentAccount.getBalance() < amount) {
			System.out.println("You do not have enough money to make this transaction!");
		} else {
			currentAccount.setBalance(currentAccount.getBalance() - amount);
			System.out.println("The transaction was successful!");
		}
	}

	public void displayBalance() {
		System.out.println(String.format("You have: %d$ left in your account", currentAccount.getBalance()));
	}

	public void pin() {
		System.out.print("Enter your name: ");
		String pin = new Scanner(System.in).nextLine();
		// i guess you'll finish it later???
	}

	public static void main(String[] args) {
		ArrayList<Account> accounts = new ArrayList<>();

		// here could be duplicated pins tho
		System.out.print("Enter a new pin number: ");
		int newPin = new Scanner(System.in).nextInt();
		accounts.add(new Account(newPin));

		System.out.print("Search for your pin: ");
		int searchPin = new Scanner(System.in).nextInt();
		Optional<Account> optionalAccount = accounts.stream().filter(i -> i.getPin() == searchPin).findFirst();

		if (optionalAccount.isPresent()) {
			System.out.println("Your account found");
			new ATMsystem(optionalAccount.get());
		} else {
			System.out.println("Account not found :(");
		}
	}
}

class Account {
	private int pin;
	private int balance = 0;

	public Account() {
	}

	public Account(int pin) {
		this.pin = pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getPin() {
		return this.pin;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
