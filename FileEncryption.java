
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class FileEncryption {
    private static final int SALT_SIZE = 16;
    private static final int IV_SIZE = 16;
    private static final int KEY_SIZE = 256;
    private static final int ITERATIONS = 200_000;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== File Encryption / Decryption ===");
        System.out.println("1. Encrypt message.txt");
        System.out.println("2. Decrypt encrypted.dat");
        System.out.print("Choose option: ");

        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter password (minimum 8 characters): ");
        char[] password = sc.nextLine().toCharArray();

        if (password.length < 8) {
            System.out.println("Password must contain at least 8 characters.");
            Arrays.fill(password, '\0');
            sc.close();
            return;
        }

        try {
            if (choice == 1) {
                byte[] plaintext = Files.readAllBytes(Path.of("message.txt"));
                byte[] salt = new byte[SALT_SIZE];
                byte[] iv = new byte[IV_SIZE];
                SecureRandom random = new SecureRandom();
                random.nextBytes(salt);
                random.nextBytes(iv);

                SecretKey key = deriveKey(password, salt);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
                byte[] ciphertext = cipher.doFinal(plaintext);

                byte[] output = new byte[SALT_SIZE + IV_SIZE + ciphertext.length];
                System.arraycopy(salt, 0, output, 0, SALT_SIZE);
                System.arraycopy(iv, 0, output, SALT_SIZE, IV_SIZE);
                System.arraycopy(ciphertext, 0, output, SALT_SIZE + IV_SIZE,
                        ciphertext.length);

                Files.write(Path.of("encrypted.dat"), output);
                System.out.println("Encrypted successfully: encrypted.dat");

            } else if (choice == 2) {
                byte[] input = Files.readAllBytes(Path.of("encrypted.dat"));

                if (input.length < SALT_SIZE + IV_SIZE + 16) {
                    throw new IllegalArgumentException("Encrypted file is invalid.");
                }

                byte[] salt = Arrays.copyOfRange(input, 0, SALT_SIZE);
                byte[] iv = Arrays.copyOfRange(input, SALT_SIZE, SALT_SIZE + IV_SIZE);
                byte[] ciphertext = Arrays.copyOfRange(input, SALT_SIZE + IV_SIZE,
                        input.length);

                SecretKey key = deriveKey(password, salt);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
                byte[] plaintext = cipher.doFinal(ciphertext);

                Files.write(Path.of("decrypted.txt"), plaintext);
                System.out.println("Decrypted successfully: decrypted.txt");

            } else {
                System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Operation failed. Check the file and password.");
        } finally {
            Arrays.fill(password, '\0');
            sc.close();
        }
    }

   
        private static SecretKey deriveKey(char[] password, byte[] salt)
        throws Exception {
    PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_SIZE);
    try {
        byte[] encoded = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(spec).getEncoded();

        return new javax.crypto.spec.SecretKeySpec(encoded, "AES");
    } finally {
        spec.clearPassword();
        }
     }
}


