package com.company.tourAgency.utils.encoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password Encoder
 */
public final class PasswordEncoder {
    private static final String ENCRYPTION_METHOD = "SHA-1";
    private static final int BASE = 16;
    private static final int BIT_32 = 32;
    private static final char ADDITIONAL_SYMBOL = '0';

    /**
     * Encode password
     *
     * @param password original password
     * @return encoded password
     * @throws NoSuchAlgorithmException when requested algorithm is not found
     */
    public String encode(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTION_METHOD);
        byte[] hashValueBytes = messageDigest.digest(password.getBytes());

        StringBuilder encodedPassword = new StringBuilder((new BigInteger(1, hashValueBytes)).toString(BASE));

        while (encodedPassword.length() < BIT_32) {
            encodedPassword.insert(0, ADDITIONAL_SYMBOL);
        }

        return encodedPassword.toString();
    }
}
