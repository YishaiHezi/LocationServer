package com.example.demo.controller;

import com.example.demo.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.lang.NonNull;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 * Used to encrypt things. currently able to encrypt mails and passwords.
 */
public class Encryptor {


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * the Algorithm used to encrypt mails.
     */
    private static final String ALGORITHM = "AES";


    /**
     * Encrypt the given email using the given secret key. In the real production environment, I will need to
     * generate a secret key using the generateSecretKey for *-every-* new user, and use this secret key
     * to encrypt and decrypt the mail. Need to read how to store these secret keys
     */
    public static String encryptEmail(String email, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedEmail = cipher.doFinal(email.getBytes());

        return Base64.getEncoder().encodeToString(encryptedEmail);
    }

    public static String decryptEmail(String encryptedEmail, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // todo: this line should be uncomment. I need to use the secret key argument.
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // todo: these 2 lines should be removed. I need to use the secret key argument.
        SecretKey temporarySecretKey = stringToSecretKey("xmsn/MuofvXV7hsBhHCsMYfRJwrYbQfXxN6qlRYSp3U=");
        cipher.init(Cipher.DECRYPT_MODE, temporarySecretKey);


        byte[] decryptedEmailBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedEmail));
        return new String(decryptedEmailBytes);
    }


    /**
     * This method needs to be used in the real environment (in aws or something).
     * currently I don't use it. used for the encryption of the mails.
     */
    public static SecretKey generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        return keyGenerator.generateKey();
    }


    /**
     * Just a method that convert SecretKey object to string.
     */
    private static String secretKeyToString(SecretKey secretKey) {
        // Convert SecretKey to byte array
        byte[] keyBytes = secretKey.getEncoded();

        // Encode the byte array to Base64 String
        return Base64.getEncoder().encodeToString(keyBytes);
    }


    /**
     * Just a method that convert a string representation of SecretKey to SecretKey object.
     */
    private static SecretKey stringToSecretKey(String keyAsString) {
        // Decode the Base64 String to byte array
        byte[] keyBytes = Base64.getDecoder().decode(keyAsString);

        // Reconstruct SecretKey from the byte array
        return new javax.crypto.spec.SecretKeySpec(keyBytes, ALGORITHM);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    /**
     * Method that encrypt the password using BCrypt (type of Hash algorithm)
     */
    public static String encryptPassword(@NonNull String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }


    /**
     * Method that decrypt the password using BCrypt (type of Hash algorithm).
     */
    public static boolean verifyPassword(@NonNull String plainTextPassword, @NonNull String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Here we encrypt all the needed fields of the user using the methods above.
     */
    @NonNull
    public static User encryptUser(@NonNull User user){

        // encrypt the password:
        String plainTextPassword = user.getPassword();
        String encryptedPassword = encryptPassword(plainTextPassword);
        user.setPassword(encryptedPassword);

        String mail = user.getMail();

        // encrypt the mail:
        try {
            SecretKey secretKey = stringToSecretKey("xmsn/MuofvXV7hsBhHCsMYfRJwrYbQfXxN6qlRYSp3U=");
            String encryptedMail = encryptEmail(mail, secretKey);
            user.setMail(encryptedMail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return user;
    }



    public static void main(String[] args) {

    }



}
