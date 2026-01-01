/* 
 * 
 * Author - The-R34per
 * Last Updated - October 25th, 2025
 * 
 * BankAccountNumberGenerator.java  © 2025 by The-R34per is licensed under CC BY-NC-SA 4.0. 
 * To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-sa/4.0/
 * 
 */

import java.util.Random;

public class BankAccountNumberGenerator {

    private static final int DEFAULT_ACCOUNT_NUMBER_LENGTH = 10; // Default length of the account number
    private static final Random random = new Random();

    // Public method to generate a random bank account number with default length
    public static String generateAccountNumber() {
        return generateAccountNumber(DEFAULT_ACCOUNT_NUMBER_LENGTH);
    }

    // Public method to generate a random bank account number with a specified length
    public static String generateAccountNumber(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Account number length must be greater than 0.");
        }

        StringBuilder accountNumber = new StringBuilder();

        // Ensure the first digit is non-zero
        accountNumber.append(random.nextInt(9) + 1);

        // Generate the remaining digits
        for (int i = 1; i < length; i++) {
            accountNumber.append(random.nextInt(10)); // Append random digit (0-9)
        }

        return accountNumber.toString();
    }
    
    
}
