package com.lisichenko.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashing passwords by SHA-1
 */
public class SHA1 {
    private static final Logger LOG = Logger.getLogger(SHA1.class);

    public static String sha1(String input) {
        try {
            LOG.debug("Hashing password start");
            StringBuilder stringBuilder = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(input.getBytes());
            for (byte b : digest) {
                stringBuilder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException ==> " + e);
            throw new RuntimeException(e);
        }
    }
}
