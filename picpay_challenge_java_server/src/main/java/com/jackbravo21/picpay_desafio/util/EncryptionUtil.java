package com.jackbravo21.picpay_desafio.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtil {

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean verifyPassword(String rawPassword, String encryptedPassword) {
        return encoder.matches(rawPassword, encryptedPassword);
    }
}
