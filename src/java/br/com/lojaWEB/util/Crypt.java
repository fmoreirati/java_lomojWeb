package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypt {

    public static String md5(String psw) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(psw.getBytes(), 0, psw.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
}
