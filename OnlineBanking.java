/* 
 * 
 * Author - The-R34per
 * Last Updated - October 25th, 2025
 * 
 * OnlineBanking.java  © 2025 by The-R34per is licensed under CC BY-NC-SA 4.0. 
 * To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-sa/4.0/
 * 
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class OnlineBanking extends JFrame {

	private JButton CreateUser;
	private JButton Login;
	private JButton CheckBalance;
	private JButton With;
	private JButton Dep;
	private JButton Transf;
	private JTextField FName;
	private JTextField LName;
	private JTextField AccountPass;
	private JButton AdminLogin;
	private JLabel loggedInAccountLabel;
	private UserAccount loggedInUser; // Holds the currently logged-in user
	private JButton createNewAccNum;
	private JButton Logout;
	private JButton viewAccNums;
	private JButton forgotPass;

	private ArrayList<UserAccount> userAccounts;

	public OnlineBanking() {
	    super("Online Banking");
	    setVisible(true);
	    setSize(500, 500);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	    userAccounts = new ArrayList<>(); // Initialize the ArrayList
	    
	    CheckBalance = new JButton("Check Balance");
	    With = new JButton("Withdraw");
	    Dep = new JButton("Deposit");
	    Transf = new JButton("Transfer");
	    createNewAccNum = new JButton("Open a New Bank Account");
	    Logout = new JButton("Log Out");
	    viewAccNums = new JButton("View Accounts");
	    forgotPass = new JButton("Forgot Password");

	    // Use GridBagLayout for the frame
	    setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	 // Adjust insets for the top buttons
	    gbc.insets = new Insets(5, 5, 5, 5); // Reduce horizontal spacing for closer buttons
	    gbc.anchor = GridBagConstraints.CENTER;
	    
	    
	    // Row 1: Create Account and Log In buttons
	    CreateUser = new JButton("Create New User");
	    Login = new JButton("Log In");
	    gbc.gridx = 0; // Column 0
	    gbc.gridy = 0; // Row 0
	    //gbc.anchor = GridBagConstraints.WEST;
	    this.add(CreateUser, gbc);
	    
	    gbc.gridy = 1;
	    this.add(viewAccNums, gbc);
	   

	    gbc.gridx = 0; // Column 1
	    gbc.gridy = 4;
	    //gbc.anchor = GridBagConstraints.EAST;
	    this.add(Login, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(createNewAccNum, gbc);
	    
	    gbc.gridy++;
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(forgotPass, gbc);

	    // Row 2 & 3: Check Balance, Withdraw, Deposit, Transfer in a 2x2 grid
	  
	    
	    

	    gbc.gridy = 3; // Row 1
	    gbc.gridx = 1; // Column 1
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(CheckBalance, gbc);

	    gbc.gridx = 1; // Column 1
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(With, gbc);

	    gbc.gridy = 1; // Row 2
	    gbc.gridx = 1; // Column 0
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(Dep, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(Logout, gbc);

	    gbc.gridx = 1; // Column 1
	    gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(Transf, gbc);
	    
	   

	    // Row 4: Admin Log In in the bottom right corner
	    AdminLogin = new JButton("Admin Login");
	    gbc.gridy = 4; // Row 4
	    gbc.gridx = 1; // Column 1
	    gbc.anchor = GridBagConstraints.SOUTHEAST;
	    gbc.weightx = 1.0; // Push to the right
	    gbc.weighty = 1.0; // Push to the bottom
	    this.add(AdminLogin, gbc);

	    // Row 4: "No user logged in" label in the bottom left corner
	    loggedInAccountLabel = new JLabel("No user logged in.");
	    gbc.gridx = 0; // Column 0
	    gbc.anchor = GridBagConstraints.SOUTHWEST;
	    gbc.weightx = 0.0; // Reset weight
	    gbc.weighty = 0.0; // Reset weight
	    this.add(loggedInAccountLabel, gbc);

	    // Add action listeners
	    CreateUser.addActionListener(new CreateAccListener());
	    Login.addActionListener(new LoginListener());
	    CheckBalance.addActionListener(new CheckBalanceListener());
	    With.addActionListener(new WithListener());
	    Dep.addActionListener(new DepListener());
	    Transf.addActionListener(new TransfListener());
	    AdminLogin.addActionListener(new AdminLoginListener());
	    Logout.addActionListener(new LogoutListener());
	    createNewAccNum.addActionListener(new NewAccountNum());
	    forgotPass.addActionListener(new ForgotPassListener());
	    viewAccNums.addActionListener(new showAccountsListener());
	
	    

	
	
	}


	private double getAccountBalanceByNumber(String accountNumber) {
        for (UserAccount account : userAccounts) {
            for (String accNum : account.getAccountNumbers()) {
                if (accNum.equals(accountNumber)) {
                    return account.getBalance();
                }
            }
        }
        return 0.0; // Account not found
    }

	private UserAccount findAccountByNumber(String accountNumber) {
	    for (UserAccount account : userAccounts) {
	        if (account.getAccountNumber().equals(accountNumber)) {
	            return account;
	        }
	    }
	    return null;
	}

	
	


	private class AdminLoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String adminUsername = "TEST";
			String adminPassword = "TEST";


			JPanel panel = new JPanel(new GridBagLayout());
			panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Add padding

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5); // Add space between components
			gbc.anchor = GridBagConstraints.WEST; // Align components to the left
			gbc.fill = GridBagConstraints.HORIZONTAL;

			JLabel userLabel = new JLabel("Username:");
			JTextField User = new JTextField(10);
			JLabel passLabel = new JLabel("Password");
			JPasswordField Pass = new JPasswordField(10);


			gbc.gridx = 0;
			gbc.gridy = 0;
			panel.add(userLabel, gbc);
			gbc.gridx = 1;
			panel.add(User, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			panel.add(passLabel, gbc);
			gbc.gridx = 1;
			panel.add(Pass, gbc);



			// Show the panel in a JOptionPane
			int result = JOptionPane.showConfirmDialog(
					OnlineBanking.this,
					panel,
					"Admin Login",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE
					);

			if (result == JOptionPane.OK_OPTION) {
				String username = User.getText();
				String password = new String(Pass.getPassword());

				// Check admin credentials
				if (username.equals(adminUsername) && password.equals(adminPassword)) {
					JOptionPane.showMessageDialog(
							OnlineBanking.this,
							"Admin Login Successful!",
							"Success",
							JOptionPane.INFORMATION_MESSAGE
							);
					// Show admin panel (for example, calling a method to display the panel)
					showAdminPanel();
				} else {
					JOptionPane.showMessageDialog(
							OnlineBanking.this,
							"Invalid username or password.",
							"Error",
							JOptionPane.ERROR_MESSAGE
							);
				}
			}
		}
	}
	
	

	private void showAdminPanel() {

		//JOptionPane.showMessageDialog(this, "Welcome to the Admin Panel!", "Admin Panel", JOptionPane.INFORMATION_MESSAGE);

		JButton showPatrons = new JButton("Show Patrons");
		showPatrons.addActionListener(new showPatronsListener());
		
		JButton showBalances = new JButton("Show Patron Balances");
		showBalances.addActionListener(new showBalancesListener());

		// Adding showPatrons button to the admin panel
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(showPatrons);
		panel.add(showBalances);
		JOptionPane.showMessageDialog(this, panel, "Admin Panel", JOptionPane.INFORMATION_MESSAGE);
	}


	private class showPatronsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			printAccounts();
		}
	}

	
	private class showBalancesListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        StringBuilder balancesInfo = new StringBuilder("User Account Balances:\n");
	        for (UserAccount account : userAccounts) {
	            balancesInfo.append(account.getFirstName())
	                        .append(" ")
	                        .append(account.getLastName())
	                        .append(": ");
	            for (String accountNumber : account.getAccountNumbers()) {
	                balancesInfo.append("Account ")
	                            .append(accountNumber)
	                            .append(" - $")
	                            .append(getAccountBalanceByNumber(accountNumber))
	                            .append(", ");
	            }
	            balancesInfo.deleteCharAt(balancesInfo.length() - 2); // Remove trailing comma and space
	            balancesInfo.append("\n");
	        }
	        JOptionPane.showMessageDialog(OnlineBanking.this, balancesInfo.toString(), "User Account Balances", JOptionPane.INFORMATION_MESSAGE);
	    }

	    private double getAccountBalanceByNumber(String accountNumber) {
	        for (UserAccount account : userAccounts) {
	            for (String accNum : account.getAccountNumbers()) {
	                if (accNum.equals(accountNumber)) {
	                    return account.getBalance();
	                }
	            }
	        }
	        return 0.0; // Account not found
	    }
	}
	
	
	private class LoginListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JPanel panel = new JPanel(new GridBagLayout());
	        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	        gbc.anchor = GridBagConstraints.WEST;
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        JLabel FNameLabel = new JLabel("First Name:");
	        JTextField fName = new JTextField(10);
	        JLabel LNameLabel = new JLabel("Last Name:");
	        JTextField lName = new JTextField(10);
	        JLabel passLabel = new JLabel("Password:");
	        JPasswordField Pass = new JPasswordField(10);

	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        panel.add(FNameLabel, gbc);
	        gbc.gridx = 1;
	        panel.add(fName, gbc);

	        gbc.gridx = 0;
	        gbc.gridy++;
	        panel.add(LNameLabel, gbc);
	        gbc.gridx = 1;
	        panel.add(lName, gbc);

	        gbc.gridx = 0;
	        gbc.gridy++;
	        panel.add(passLabel, gbc);
	        gbc.gridx = 1;
	        panel.add(Pass, gbc);

	        int result = JOptionPane.showConfirmDialog(
	                OnlineBanking.this,
	                panel,
	                "Login",
	                JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.PLAIN_MESSAGE
	        );

	        if (result == JOptionPane.OK_OPTION) {
	            String firstName = fName.getText();
	            String lastName = lName.getText();
	            String password = new String(Pass.getPassword());

	            for (UserAccount account : userAccounts) {
	                if (account.getFirstName().equals(firstName) &&
	                        account.getLastName().equals(lastName) &&
	                        account.getPassword().equals(password)) {
	                    JOptionPane.showMessageDialog(
	                            OnlineBanking.this,
	                            "Login Successful!",
	                            "Success",
	                            JOptionPane.INFORMATION_MESSAGE
	                    );
	                    loggedInUser = account; // Set the logged-in user
	                    StringBuilder accountNumbersString = new StringBuilder();
	                    for (String accountNumber : loggedInUser.getAccountNumbers()) {
	                        accountNumbersString.append(accountNumber).append(", ");
	                    }
	                    // Remove the trailing comma and space
	                    if (accountNumbersString.length() > 0) {
	                        accountNumbersString.deleteCharAt(accountNumbersString.length() - 2);
	                    }
	                    loggedInAccountLabel.setText("Logged in as: " + account.getFirstName() + " " + account.getLastName() + " - Accounts: " + accountNumbersString.toString());
	                    revalidate();
	                    repaint();
	                    return;
	                }
	            }

	            JOptionPane.showMessageDialog(
	                    OnlineBanking.this,
	                    "Invalid username or password.",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	            );
	        }
	        revalidate();
	        repaint();
	    }
	}

	private class CheckBalanceListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (loggedInUser == null) {
	            JOptionPane.showMessageDialog(
	                    OnlineBanking.this,
	                    "You must be logged in to check your balance.",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	            );
	            return;
	        }

	        // Check balance functionality here if logged in
	        JOptionPane.showMessageDialog(OnlineBanking.this,
	                "Your current balance is: $" + loggedInUser.getBalance(),
	                "Balance", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

	private class TransfListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (loggedInUser == null) {
	            JOptionPane.showMessageDialog(
	                    OnlineBanking.this,
	                    "You must be logged in to transfer money.",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	            );
	            return;
	        }

	        // Perform transfer functionality here if logged in
	        // Example: Ask for account number, amount, etc.
	        String targetAccountNumber = JOptionPane.showInputDialog(OnlineBanking.this,
	                "Enter the target account number:", "Transfer", JOptionPane.PLAIN_MESSAGE);
	        
	        if (targetAccountNumber != null) {
	            String amountStr = JOptionPane.showInputDialog(OnlineBanking.this,
	                    "Enter amount to transfer:", "Transfer", JOptionPane.PLAIN_MESSAGE);

	            try {
	                double amount = Double.parseDouble(amountStr);
	                UserAccount targetAccount = findAccountByNumber(targetAccountNumber);
	                if (targetAccount != null && loggedInUser.withdraw(amount)) {
	                    targetAccount.deposit(amount);
	                    JOptionPane.showMessageDialog(OnlineBanking.this,
	                            "Transfer Successful! New Balance: $" + loggedInUser.getBalance(),
	                            "Success", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(OnlineBanking.this,
	                            "Transfer failed. Ensure the account number is valid and you have sufficient funds.",
	                            "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(OnlineBanking.this,
	                        "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	}

	private class WithListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (loggedInUser == null) {
	            JOptionPane.showMessageDialog(
	                    OnlineBanking.this,
	                    "You must be logged in to make a withdrawal.",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	            );
	            return;
	        }

	        // Perform withdrawal functionality here if logged in
	        String amountStr = JOptionPane.showInputDialog(OnlineBanking.this,
	                "Enter amount to withdraw:", "Withdrawal", JOptionPane.PLAIN_MESSAGE);

	        if (amountStr != null) {
	            try {
	                double amount = Double.parseDouble(amountStr);
	                if (amount > 0) {
	                    if (loggedInUser.getBalance() >= amount) {
	                        loggedInUser.withdraw(amount);
	                        JOptionPane.showMessageDialog(OnlineBanking.this,
	                                "Withdrawal Successful! New Balance: $" + loggedInUser.getBalance(),
	                                "Success", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        JOptionPane.showMessageDialog(OnlineBanking.this,
	                                "Insufficient funds for this withdrawal.",
	                                "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(OnlineBanking.this,
	                            "Please enter a positive amount.",
	                            "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(OnlineBanking.this,
	                        "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	}

	
	private class LogoutListener implements ActionListener {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (loggedInUser != null) {
	            // Clear the logged-in user
	            loggedInUser = null;
	            
	            // Update the label to reflect no user is logged in
	            loggedInAccountLabel.setText("No user logged in.");
	            
	            // Optionally show a message to the user
	            JOptionPane.showMessageDialog(
	                OnlineBanking.this,
	                "You have successfully logged out.",
	                "Logout Successful",
	                JOptionPane.INFORMATION_MESSAGE
	            );
	            
	            // Revalidate and repaint the frame
	            revalidate();
	            repaint();
	        } else {
	            // Inform the user that no one is logged in
	            JOptionPane.showMessageDialog(
	                OnlineBanking.this,
	                "No user is currently logged in.",
	                "Logout Error",
	                JOptionPane.WARNING_MESSAGE
	            );
	        }
	    }
	}
	
	private class DepListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (loggedInUser == null) {
	            JOptionPane.showMessageDialog(
	                    OnlineBanking.this,
	                    "You must be logged in to make a deposit.",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	            );
	            return;
	        }

	        // Check if the user has multiple accounts
	        List<String> accountNumbers = loggedInUser.getAccountNumbers();
	        if (accountNumbers.size() > 1) {
	            // Get the account number for the deposit
	            String selectedAccountNumber = (String) JOptionPane.showInputDialog(
	                    OnlineBanking.this,
	                    "Select account number for deposit:",
	                    "Deposit",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null, // Use default icon
	                    accountNumbers.toArray(), // Provide account numbers as options
	                    accountNumbers.get(0) // Default selection
	            );

	            if (selectedAccountNumber == null) {
	                return; // User canceled the selection
	            }

	            // Get the deposit amount
	            String amountStr = JOptionPane.showInputDialog(OnlineBanking.this,
	                    "Enter amount to deposit:",
	                    "Deposit",
	                    JOptionPane.PLAIN_MESSAGE);

	            if (amountStr != null) {
	                try {
	                    double amount = Double.parseDouble(amountStr);
	                    if (amount > 0) {
	                        // Find the account and deposit the amount
	                        for (UserAccount account : userAccounts) {
	                            if (account.getAccountNumbers().contains(selectedAccountNumber)) {
	                                account.deposit(amount);
	                                JOptionPane.showMessageDialog(OnlineBanking.this,
	                                        "Deposit Successful! New Balance for Account " + selectedAccountNumber + ": $" + account.getBalance(),
	                                        "Success",
	                                        JOptionPane.INFORMATION_MESSAGE);
	                                break;
	                            }
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(OnlineBanking.this,
	                                "Please enter a positive amount.",
	                                "Error",
	                                JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(OnlineBanking.this,
	                            "Invalid amount entered!",
	                            "Error",
	                            JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        } else {
	            // User has only one account
	            String amountStr = JOptionPane.showInputDialog(OnlineBanking.this,
	                    "Enter amount to deposit:",
	                    "Deposit",
	                    JOptionPane.PLAIN_MESSAGE);

	            if (amountStr != null) {
	                try {
	                    double amount = Double.parseDouble(amountStr);
	                    if (amount > 0) {
	                        loggedInUser.deposit(amount);
	                        JOptionPane.showMessageDialog(OnlineBanking.this,
	                                "Deposit Successful! New Balance: $" + loggedInUser.getBalance(),
	                                "Success",
	                                JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        JOptionPane.showMessageDialog(OnlineBanking.this,
	                                "Please enter a positive amount.",
	                                "Error",
	                                JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(OnlineBanking.this,
	                            "Invalid amount entered!",
	                            "Error",
	                            JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }
	    }
	}



	private class ForgotPassListener implements ActionListener {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JPanel panel = new JPanel(new GridBagLayout());
	        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	        gbc.anchor = GridBagConstraints.WEST;
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        JLabel RFNameLabel = new JLabel("First Name:");
	        JTextField RFName = new JTextField(10);
	        JLabel RLNameLabel = new JLabel("Last Name:");
	        JTextField RLName = new JTextField(10);
	        JLabel RaccEmailLabel = new JLabel("Email:");
	        JTextField RaccEmail = new JTextField(10);
	        JLabel RpassLabel = new JLabel("New Password:");
	        JPasswordField RPass = new JPasswordField(10);
	        JLabel Rpass1Label = new JLabel("Confirm Password:");
	        JPasswordField RPassConfirm = new JPasswordField(10);

	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        panel.add(RFNameLabel, gbc);
	        gbc.gridx = 1;
	        panel.add(RFName, gbc);

	        gbc.gridx = 0;
	        gbc.gridy++;
	        panel.add(RLNameLabel, gbc);
	        gbc.gridx = 1;
	        panel.add(RLName, gbc);

	        gbc.gridx = 0;
	        gbc.gridy++;
	        panel.add(RaccEmailLabel, gbc);
	        gbc.gridx = 1;
	        panel.add(RaccEmail, gbc);

	        gbc.gridx = 0;
	        gbc.gridy++;
	        panel.add(RpassLabel, gbc);
	        gbc.gridx = 1;
	        panel.add(RPass, gbc);

	        gbc.gridx = 0;
	        gbc.gridy++;
	        panel.add(Rpass1Label, gbc);
	        gbc.gridx = 1;
	        panel.add(RPassConfirm, gbc);

	        int result = JOptionPane.showConfirmDialog(
	                OnlineBanking.this,
	                panel,
	                "Forgot Password",
	                JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.PLAIN_MESSAGE
	        );

	        if (result == JOptionPane.OK_OPTION) {
	            String resetFName = RFName.getText();
	            String resetLName = RLName.getText();
	            String Remail = RaccEmail.getText();
	            String Rpassword = new String(RPass.getPassword());
	            String RconfirmPassword = new String(RPassConfirm.getPassword());

	            // Validate input
	            if (resetFName.isEmpty() || resetLName.isEmpty() || Remail.isEmpty() || 
	                    Rpassword.isEmpty() || RconfirmPassword.isEmpty()) {
	                JOptionPane.showMessageDialog(OnlineBanking.this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Check if passwords match
	            if (!Rpassword.equals(RconfirmPassword)) {
	                JOptionPane.showMessageDialog(
	                        OnlineBanking.this,
	                        "Passwords do not match.",
	                        "Error",
	                        JOptionPane.ERROR_MESSAGE
	                );
	                return;
	            }

	            // Find the user account based on first name, last name, and email
	            UserAccount userToReset = null;
	            for (UserAccount account : userAccounts) {
	                if (account.getFirstName().equals(resetFName) && 
	                        account.getLastName().equals(resetLName) && 
	                        account.getEmail().equals(Remail)) {
	                    userToReset = account;
	                    break;
	                }
	            }

	            if (userToReset != null) {
	                // Update the user's password
	                userToReset.setPassword(Rpassword); 
	                JOptionPane.showMessageDialog(OnlineBanking.this, "Password Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(OnlineBanking.this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	}



	private class CreateAccListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Panel with GridBagLayout for better alignment and spacing
			JPanel panel = new JPanel(new GridBagLayout());
			panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Add padding

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5); // Add space between components
			gbc.anchor = GridBagConstraints.WEST; // Align components to the left
			gbc.fill = GridBagConstraints.HORIZONTAL;

			// Components
			JLabel firstNameLabel = new JLabel("First Name:");
			FName = new JTextField(20);
			JLabel lastNameLabel = new JLabel("Last Name:");
			LName = new JTextField(20);
			JLabel emailLabel = new JLabel("Email:");
			JTextField accEmail = new JTextField(20);
			JLabel passwordLabel = new JLabel("Password:");
			AccountPass = new JPasswordField(20);
			JLabel confirmPassLabel = new JLabel("Confirm Password:");
			JPasswordField confirmPassField = new JPasswordField(20);

			// Add components to the panel
			gbc.gridx = 0;
			gbc.gridy = 0;
			panel.add(firstNameLabel, gbc);
			gbc.gridx = 1;
			panel.add(FName, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			panel.add(lastNameLabel, gbc);
			gbc.gridx = 1;
			panel.add(LName, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			panel.add(emailLabel, gbc);
			gbc.gridx = 1;
			panel.add(accEmail, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			panel.add(passwordLabel, gbc);
			gbc.gridx = 1;
			panel.add(AccountPass, gbc);

			gbc.gridx = 0;
			gbc.gridy++;
			panel.add(confirmPassLabel, gbc);
			gbc.gridx = 1;
			panel.add(confirmPassField, gbc);

			// Show the panel in a JOptionPane
			int result = JOptionPane.showConfirmDialog(
					OnlineBanking.this,
					panel,
					"Create New User",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE
					);

			if (result == JOptionPane.OK_OPTION) {
				String firstName = FName.getText();
				String lastName = LName.getText();
				String email = accEmail.getText();
				String password = new String(((JPasswordField) AccountPass).getPassword());
				String confirmPassword = new String(confirmPassField.getPassword());

				// Validate input
				if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
					JOptionPane.showMessageDialog(
							OnlineBanking.this,
							"All fields are required.",
							"Error",
							JOptionPane.ERROR_MESSAGE
							);
				} else if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(
							OnlineBanking.this,
							"Passwords do not match.",
							"Error",
							JOptionPane.ERROR_MESSAGE
							);
				} else {
					String accountNumber = BankAccountNumberGenerator.generateAccountNumber();
					userAccounts.add(new UserAccount(firstName, lastName, email, accountNumber, password)); // Store the new account
					JOptionPane.showMessageDialog(
							OnlineBanking.this,
							"Account Created!\nName: " + firstName + " " + lastName +
							"\nAccount Number: " + accountNumber,
							"Success",
							JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		}
	}

	private class NewAccountNum implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (loggedInUser == null) {
	            JOptionPane.showMessageDialog(
	                    OnlineBanking.this,
	                    "You must be logged in to generate a new account number.",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	            );
	            return;
	        }

	        // Generate a new account number and add it to the logged-in user's account list
	        loggedInUser.generateNewAccountNumber();

	        // Show the new account number to the user
	        JOptionPane.showMessageDialog(OnlineBanking.this,
	                "New account number generated: " + loggedInUser.getAccountNumbers().get(loggedInUser.getAccountNumbers().size() - 1),
	                "New Account Number", JOptionPane.INFORMATION_MESSAGE);

	        // Display the list of all account numbers for the user
	        JOptionPane.showMessageDialog(OnlineBanking.this,
	                "All your account numbers: " + loggedInUser.getAccountNumbers(),
	                "Account Numbers", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

	private class showAccountsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			
			if (loggedInUser == null) {
				JOptionPane.showMessageDialog(
						OnlineBanking.this,
						"You must be logged in to view accounts.",
						"Error",
						JOptionPane.ERROR_MESSAGE
						);
			} else {
				StringBuilder accountsInfo = new StringBuilder("Your Accounts:\n");
				for (String accountNumber1 : loggedInUser.getAccountNumbers()) {
					accountsInfo.append(accountNumber1).append(" - Balance: $").append(getAccountBalanceByNumber(accountNumber1)).append("\n");
				}
				JOptionPane.showMessageDialog(OnlineBanking.this, accountsInfo.toString(), "Your Accounts", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	
	public void printAccounts() {
		StringBuilder accountsInfo = new StringBuilder("User Accounts:\n");
		for (UserAccount account : userAccounts) {
			accountsInfo.append(account.toString()).append("\n");
		}
		JOptionPane.showMessageDialog(OnlineBanking.this,accountsInfo.toString(),"All User Accounts",JOptionPane.INFORMATION_MESSAGE
				);
	}

	// UserAccount class to store account details
	public class UserAccount {

	    private String firstName;
	    private String lastName;
	    private String email;
	    private List<String> accountNumbers;  // List to store account numbers
	    private String password;
	    private double balance;
	    private List<Double> balances;

	    
	    //create a user
	    public UserAccount(String firstName, String lastName, String email, String accountNumber, String password) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.password = password;
	        this.balance = 0.0;
	        
	        // Initialize the list with the original account number
	        this.accountNumbers = new ArrayList<>();
	        this.accountNumbers.add(accountNumber);  // Add the original account number to the list
	    }
	    
	    // Forgot password
	    public UserAccount(String firstName, String lastName, String email, String password) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.password = password;
	        //this.balance = balance;
	        
	       
	    }
	    
	    private List<String> accountNumbers1;
	    private List<Double> balances1;

	    private class ShowAccountsListener implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (loggedInUser == null) {
	                JOptionPane.showMessageDialog(
	                        OnlineBanking.this,
	                        "You must be logged in to view accounts.",
	                        "Error",
	                        JOptionPane.ERROR_MESSAGE
	                );
	            } else {
	                StringBuilder accountsInfo = new StringBuilder("Your Accounts:\n");
	                for (String accountNumber : loggedInUser.getAccountNumbers()) {
	                    // Get balance for the specific account
	                    double balance = loggedInUser.getBalance();
	                    accountsInfo.append(accountNumber).append(" - Balance: $").append(String.format("%.2f", balance)).append("\n");
	                }
	                JOptionPane.showMessageDialog(
	                        OnlineBanking.this,
	                        accountsInfo.toString(),
	                        "Your Accounts",
	                        JOptionPane.INFORMATION_MESSAGE
	                );
	            }
	        }
	    }

	    // Assuming the UserAccount class has this method:
	    private double getAccountBalance(String accountNumber) {
	        if (accountNumbers1 != null && balances1 != null) {
	            for (int i = 0; i < accountNumbers1.size(); i++) {
	                if (accountNumbers1.get(i).equals(accountNumber)) {
	                    return balances1.get(i);
	                }
	            }
	        }
	        return 0.0; // Account not found
	    }

        
	    public UserAccount() {
	    	this.balances = new ArrayList<>();
	    }
	    
	    

	    // Getters and toString method for easy display
	    public double getBalance() {
	        return balance;
	    }

	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	        }
	    }

	    public boolean withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance = balance - amount;
	            return true;
	        }
	        return false;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    // Getter for the list of account numbers
	    public List<String> getAccountNumbers() {
	        return accountNumbers;  // Return the list of all account numbers
	    }

	    // Getter for the first account number (original account number)
	    public String getAccountNumber() {
	        return accountNumbers.get(0); // The first account number is considered the primary one
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    // Method to generate a new account number and add it to the user's account list
	    public void generateNewAccountNumber() {
	        String newAccountNumber = generateAccountNumber();  // Generate new account number
	        accountNumbers.add(newAccountNumber);  // Add it to the list of account numbers
	    }

	    // Private method to generate a new account number and ensure it's positive
	    private String generateAccountNumber() {
	        Random random = new Random();
	        // Generate a positive random number
	        long number = 1000000000L + Math.abs(random.nextLong() % 9000000000L);
	        return String.valueOf(number);
	    }

	    @Override
	    public String toString() {
	        return "UserAccount{" +
	                "firstName='" + firstName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", email='" + email + '\'' +
	                ", accountNumbers=" + accountNumbers +
	                ", password='" + password + '\'' +
	                '}';
	    }
	}

	public static void main(String[] args) {
		new OnlineBanking();
	}

}
