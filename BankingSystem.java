
import java.io.Console;
import java.util.*;
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String address;
    private String phoneNumber;
    private String email;
    private String pin;
    
    public BankAccount(String accountNumber, String accountHolderName, String address, String phoneNumber, String email, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pin = pin;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf( "%.2f deposited successfully.\n",amount );
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount. Withdrawal failed.");
        }
    }
    public double checkBalance() {
        return balance;
    }
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("E-Mail: " + email);
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public boolean checkPin(String pass){
        return pass.equals(pin);
    }
}
public class BankingSystem {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    static void initialize() {
        BankAccount newAccount = new BankAccount("12345", "uma", "avadi", "1234567890", "uma@avadi.com", "1234");
        accounts.put("12345", newAccount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        initialize();

        do {
            System.out.println("\n**** Banking System Menu ****");
            System.out.println("1. Create New Account");
            System.out.println("2. Select Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createNewAccount(scanner);
                    break;
                case 2:
                    selectAccount(scanner);
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void createNewAccount(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.next();
        System.out.print("Enter Address: ");
        String address = scanner.next();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter E-Mail: ");
        String email = scanner.next();
        Console console = System.console();
        char[] passwordArray = console.readPassword("Enter Password: ");
        String pin = new String(passwordArray);
        
        System.out.println(pin);
        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, address, phoneNumber, email, pin);
        accounts.put(accountNumber, newAccount);
        System.out.println("Account created successfully.");
    }

    private static void selectAccount(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        Console console = System.console();
        char[] passwordArray = console.readPassword("Enter Pin: ");
        String pin = new String(passwordArray);
        if (!account.checkPin(pin)){
            System.out.println("pin does not match");
            return;
        }

        int choice;
        do {
            System.out.println("\n**** Account Menu ****");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Display Account Information");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    passwordArray = console.readPassword("Enter Pin: ");
                    pin = new String(passwordArray);
                    if (!account.checkPin(pin)){
                        System.out.println("pin does not match");
                        continue;
                    }
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    double balance = account.checkBalance();
                    System.out.printf("Current Balance: %.2f\n" , balance);
                    break;
                case 4:
                    account.displayAccountDetails();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 5);
    }
}
